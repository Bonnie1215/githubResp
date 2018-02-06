package com.buleocean_health.springboot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.buleocean_health.springboot.interceprot.AuthHandleInterface;
import com.buleocean_health.springboot.interceprot.ValidateInterceptor;

@SpringBootApplication //申明让spring boot自动给程序进行必要的配置
@ServletComponentScan //开启后支持自定义Servlet和Filter
@EnableScheduling  //支持定时任务
@MapperScan(basePackages={"com.buleocean_health.springboot.mapper"}) // 扫描mapper文件
@EnableTransactionManagement  // 启注解事务管理
/**
 * 项目核心启动类
 * @author huyanqiu
 *
 */
public class AppStart extends WebMvcConfigurerAdapter {
	
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
		init(false); // TODO 暂时为本地环境
	}
	
	/**
	 * 判断是否为服务器环境
	 * @param isServer true:服务器环境  false:本地环境
	 */
	public static void init(boolean isServer) {
	}
	
	/**
     * 注入数据连接池
     */
    @Bean
    public DataSource registerDataSource(){
        DataSource ds = null;
        Properties prop = new Properties();
        try (InputStream in = AppStart.class.getClassLoader().getResourceAsStream("druid.properties")){
            prop.load(in);
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    @Autowired
    private ValidateInterceptor validateInterceptor;
    @Autowired
    private AuthHandleInterface authHandleInterface;
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(validateInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/error");
    	registry.addInterceptor(authHandleInterface).addPathPatterns("/**");
    	super.addInterceptors(registry);
    }
    
    @Value("${picture.filePath}")
    private String pictureFilePath; // http://localhost:8080/1.png
    /**
     * 添加静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "file:"+pictureFilePath);
    	super.addResourceHandlers(registry);
    }
    
    /**
     * 跨域问题处理
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.addAllowedHeader("/**");
    	// 允许访问的方式
    	configuration.addAllowedMethod("/**");
    	// 允许所有来源访问
    	configuration.addAllowedOrigin("/**");
    	source.registerCorsConfiguration("/**", configuration);
    	return new CorsFilter(source);
    }
    
    /**
     * 替换json框架为fastjson
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
       FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullStringAsEmpty);
       fastConverter.setFastJsonConfig(fastJsonConfig);
       
       HttpMessageConverter<?> converter = fastConverter;
       //处理中文乱码问题
       List<MediaType> fastMediaTypes = new ArrayList<>();
       fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
       fastConverter.setSupportedMediaTypes(fastMediaTypes);
       
       return new HttpMessageConverters(converter);
    }
    
    /**
     * 限制上传文件大小
     * @return
     */
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
    	MultipartConfigFactory factory = new MultipartConfigFactory();  
    	// 设置单个文件大小
    	factory.setMaxFileSize("102400KB");
    	// 设置总上传数据总大小  
    	factory.setMaxRequestSize("102400KB");  
    	return factory.createMultipartConfig();  
    }
    
    /**
     * mybatis SqlSessionFactory配置
     * @param dataSource 数据源
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory registerSqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //设置Mapper文件的路径
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        //设置mybatis的配置文件
        bean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
        //设置别名包
        bean.setTypeAliasesPackage("com.buleocean_health.springboot.domain");
        bean.setVfs(SpringBootVFS.class);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
}
