package test;

import java.util.Date;
import org.junit.Test;

import domain.User;
import service.impl.ServiceImpl;

public class ServiceTest {
	
	@Test
	public void testRegister() {
		User user= new User();
		user.setUsername("wssdf");
		user.setBirthday(new Date());
		user.setEmail("ddsabdsad@sina.com");
		user.setId("54dsad564566");
		user.setPassword("wsf");
			
		ServiceImpl service = new ServiceImpl();
		try {
			service.register(user);
			System.out.println("ע��ɹ�!");
			
		} catch (Exception e) {
			System.out.println("�û��Ѵ���");
		}
		
	}
	
	@Test
	public void testlogin() {
		
		ServiceImpl service = new ServiceImpl();
		User user = service.login("wssdf", "wsf");
		System.out.println(user);
		
	}
	
	
	

}
