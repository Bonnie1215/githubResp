package win.sunshine.base.service.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import win.sunshine.base.service.api.service.PersonService;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/findPerson")
	public String findPerson() {
		personService.findPerson();
		return "";
	}
	
}
