package win.sunshine.base.service.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import win.sunshine.base.service.api.domain.Person;

@Mapper
public interface PersonMapper {
	public List<Person> selectPersonById(String personId);
}
