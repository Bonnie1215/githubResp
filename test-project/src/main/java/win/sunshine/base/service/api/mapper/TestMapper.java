package win.sunshine.base.service.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import win.sunshine.base.service.api.domain.Test;
import win.sunshine.base.service.api.domain.User;

@Mapper
public interface TestMapper {

//    @Select("SELECT name FROM user WHERE id=#{id}")
    String find(@Param("id") Integer id);

	void insert(List<User> userList);

	void update(List<User> userList);
	
	void insertTestDate(Test test);

}