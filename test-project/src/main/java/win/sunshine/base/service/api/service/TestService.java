package win.sunshine.base.service.api.service;

import java.util.List;

import win.sunshine.base.service.api.domain.SysResult;
import win.sunshine.base.service.api.domain.Test;
import win.sunshine.base.service.api.domain.User;

public interface TestService {

    public SysResult find(Integer id);

	public void insert(List<User> userList);

	public void findUserByNameAndPasword(User user);

	public void update(List<User> userList);

	public void insertTestDate(Test test);
}