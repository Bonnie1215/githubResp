package win.sunshine.base.service.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import win.sunshine.base.service.api.domain.Person;
import win.sunshine.base.service.api.mapper.PersonMapper;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public void findPerson() {
		personMapper.selectPersonById("001");
//		System.out.println("===============personService==============");
//		for (Person person : personList) {
//			System.out.println(person);
//			System.out.println(person.getOrders());
//		}
//		System.out.println("================end======================");
	}

}
