package com.book.healthapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.healthapp.domain.User;
import com.book.healthapp.repositories.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserDAO userDAO;
	
	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	public User doesUserExist(String email) {
		List<User> users = (List<User>) userDAO.findByEmail(email);
		if(users == null || users.size() == 0) {
			return null;
		} 
		return users.get(0);
	}

	@Override
	public User isValidUser(String email, String password) {
		List<User> users = (List<User>) userDAO.findByEmailAndPassword(email, password);
		if(users == null || users.size() == 0) {
			return null;
		} 
		return users.get(0);
	}

}
