package com.book.healthapp.services;

import com.book.healthapp.domain.User;

public interface UserService {
	
	User save(User user);
	
	User doesUserExist(String email);
	
	User isValidUser(String email, String password);
}
