package test;

import java.util.Date;

import org.junit.Test;

import dao.impl.UserDataImpl;
import domain.User;

public class UserDaoTest {
	
	@Test
	public void testAdd(){
		
		User user= new User();
		user.setUsername("wsf");
		user.setBirthday(new Date());
		user.setEmail("bb@sina.com");
		user.setId("54564566");
		user.setPassword("123");
		
		UserDataImpl dao = new UserDataImpl();
		dao.add(user);
	}
	
	@Test
	public void testFind() {
		UserDataImpl dao = new UserDataImpl();
		dao.find("aaa","123");
	}
	
	@Test
	public void testFindbyname() {
		UserDataImpl dao = new UserDataImpl();
		System.out.println(dao.find("aaasdsad"));
	}
	
	
	
}
