package com.antonromanov.jdbctemplate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class JdbcTemplateTest {

	@Autowired
	private DaoService personDao;

	@Test
	public void testCrudOperations() {
		JdbcTestUtils.deleteFromTables(personDao.getJdbcTemplate(), "public.user");

		//insert
		User user = new User("Dana", "email@test.com");
		long generatedId = (personDao.addNew(user)).getId();
		System.out.println("Generated Id: " + generatedId);

		//loading all
		List<User> list = personDao.getAllUsers();
		System.out.println("All loaded: ");
		list.forEach(System.out::println);
		Assert.assertTrue(list.size() == 1);
	}
	}
