package service.impl;

import dao.UserDao;
import dao.impl.UserDataImpl;
import domain.User;
import exception.UserExistException;
import sun.java2d.d3d.D3DSurfaceDataProxy;
import utils.ServiceUtils;

//web 层提供所有的业务服务
public class ServiceImpl {
	
	private UserDao dao = new UserDataImpl();
	
	public void register(User user) throws UserExistException {
		
		boolean judgment = dao.find(user.getUsername());
		if (judgment) {
			throw new UserExistException();
		} else{
			
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			dao.add(user);
			
		}
		
	}
	
	 public User login(String username, String password) {
		
		 password = ServiceUtils.md5(password);
		 return dao.find(username, password);
		 
	}
	 
	public User useapp(String username) {
		
		return dao.pay(username);
	}
	
	
	 
	
	
}
