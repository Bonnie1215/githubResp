package win.sunshine.base.service.api;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication  //申明让spring boot自动给程序进行必要的配置
@ServletComponentScan   //开启后支持自定义Servlet和Filter
@EnableScheduling       //支持定时任务
@MapperScan(
        basePackages = {"win.sunshine.base.service.api.mapper"}
//        ,sqlSessionFactoryRef = ""
)
@EnableTransactionManagement(proxyTargetClass = true) // 启注解事务管理
//@ImportResource(locations = {""})
/**
 * 此项目的核心初始化类
 */
public class AppStart extends WebMvcConfigurerAdapter {
    
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
        init(false);
    }

    /**
     * 判断是否为服务器环境
     * @param isServer  true:服务器环境 false:本地环境
     */
    public static void init(boolean isServer){
    
    }

    /**
     * 添加静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 注入连接池
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


    @Bean
    public SqlSessionFactory registerSqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        bean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/logout","/api/**");
        super.addInterceptors(registry);
    }*/
}