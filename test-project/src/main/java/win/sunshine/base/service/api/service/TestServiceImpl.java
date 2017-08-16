package win.sunshine.base.service.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import win.sunshine.base.service.api.domain.SysResult;
import win.sunshine.base.service.api.domain.Test;
import win.sunshine.base.service.api.domain.User;
import win.sunshine.base.service.api.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService{
    
    @Autowired
    private TestMapper testMapper;

    @Transactional//开启事物
    public SysResult find(Integer id) {
        String name = testMapper.find(id);
        return SysResult.ok("success", name);
    }

    /**
     * 批量插入数据
     */
    @Transactional//开启事物
	@Override
	public void insert(List<User> userList) {
		testMapper.insert(userList);
	}

    /**
     * 用户登录
     */
	@Override
	public void findUserByNameAndPasword(User user) {
		// ..... 登录成功，用户名和密码正确
		String token = UUID.randomUUID().toString();
		String key = "USER_SESSION:"+token;
	}

	/**
	 * 批量更新
	 */
	@Override
	public void update(List<User> userList) {
		testMapper.update(userList);
	}

	/**
	 * 保存有日期
	 */
	@Override
	public void insertTestDate(Test test) {
		testMapper.insertTestDate(test);
	}
	
}