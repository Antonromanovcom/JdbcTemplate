package com.antonromanov.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JdbcController {

	@Autowired
	private DaoService service;

	@GetMapping("/count")
	public int getUsersCount(){
		return service.getCount();
	}

	@GetMapping("/all")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}

	@GetMapping("/add")
	public List<User> addUser(@RequestParam("name") String name,
	                          @RequestParam("email") String email){
		service.addNew(new User(name, email));
		return service.getAllUsers();
	}


}
