package dao;

import org.dom4j.DocumentException;

import domain.User;

public interface UserDao {

	void add(User user);

	User find(String username, String password);

	boolean find(String username);

	User pay(String username) ;




}