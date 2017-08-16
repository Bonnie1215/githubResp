package win.sunshine.base.service.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import win.sunshine.base.service.api.domain.SysResult;
import win.sunshine.base.service.api.domain.Test;
import win.sunshine.base.service.api.domain.User;
import win.sunshine.base.service.api.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController extends BaseController{

    @Autowired
    private TestService testService;
	
    /**
     * 正常的Controller
     */
    @RequestMapping("/demo1")
    public SysResult demo(Integer id){
        return testService.find(id);
    }
    
    /**
     * 批量插入数据
     * @return
     */
    @RequestMapping("/insert")
    public SysResult insert() {
//    	List<User> userList = new ArrayList<User>();
//    	userList.add(new User("aaa",12));
//    	userList.add(new User("bbb",13));
//    	testService.insert(userList);
    	System.out.println(SysResult.ok());
    	System.out.println(SysResult.ok("success", "msg"));
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("total", 190);
    	map.put("pageIndex", 1);
    	map.put("pageSize", 20);
    	List<User> userList01 = new ArrayList<User>();
    	userList01.add(new User(1, "bonnie", 16));
    	userList01.add(new User(2, "Jack", 17));
    	userList01.add(new User(3, "Tom", 18));
    	userList01.add(new User(4, "Hello", 19));
    	map.put("list", userList01);
    	System.out.println(SysResult.ok(map));
    	System.out.println(SysResult.ok(10000, "请再次访问！！！！"));
    	System.out.println(SysResult.ok("登陆成功", new User("bonnie", 17)));
    	System.out.println(SysResult.ok(9000, "nihao", new User("Jack", 90)));
    	
    	System.out.println("================================================");
    	System.out.println("================================================");
    	System.out.println(SysResult.error());
    	System.out.println(SysResult.error("FAILED", "msg"));
    	System.out.println(SysResult.error(map));
    	System.out.println(SysResult.error(10000, "请再次访问！！！！"));
    	System.out.println(SysResult.error("登陆成功", new User("bonnie", 17)));
    	System.out.println(SysResult.error(9000, "nihao", new User("Jack", 90)));
    	return SysResult.ok("success", "msg");
    }
    
    /**
     * 批量更新
     * @return
     */
    @RequestMapping("/update")
    public String update() {
    	List<User> userList = new ArrayList<User>();
    	userList.add(new User(8, "hhh",99));
    	userList.add(new User(9, "ll",99));
    	testService.update(userList);
    	return "";
    }
    
    @RequestMapping("/insertTestDate")
    public String insertTestDate(Test test) {
    	System.out.println("保存有日期~~~~~~");
    	testService.insertTestDate(test);
    	return "";
    }
    
    @RequestMapping(value="/", produces={"application/json;charset=UTF-8"})
    public String test() {
    	return "hello";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public String login(@RequestBody User user) {
    	testService.findUserByNameAndPasword(user);
    	return "";
    }
    
//    /**
//     * RESTFul风格的Controller
//     */
//    @RequestMapping("/demo2/{id}")
//    public SysResult demo(@PathVariable("id") Integer id){
//        return testService.find(id);
//    }
    
}