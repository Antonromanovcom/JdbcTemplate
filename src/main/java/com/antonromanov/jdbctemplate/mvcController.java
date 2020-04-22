package com.antonromanov.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mvcController {
	@Autowired
	DaoService service;

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		String message =  "Hello Spring Boot + JSP";
		model.addAttribute("message", message);
		return "index";
	}

	@RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
	public String viewUserList(Model model) {
		model.addAttribute("users", service.getAllUsers());
		return "userList";
	}
}
