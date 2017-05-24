package com.book.healthapp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.book.healthapp.domain.User;
import com.book.healthapp.exceptions.UserNotFoundException;
import com.book.healthapp.repositories.UserDAO;
import com.book.healthapp.services.UserService;
import com.book.healthapp.services.UserServiceImpl;

public class UserServiceTests {

	@MockBean UserDAO userDAO;
	
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		this.userService = new UserServiceImpl(this.userDAO);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Throws exception if user with given email does not exist")
	void Should_throwException_When_UserDoesNotExist() {
		String email = "foo@bar.com";
		Mockito.when(this.userDAO.findByEmail(email)).thenReturn(new ArrayList<User>());
		assertThatThrownBy(() -> this.userService.doesUserExist(email)).isInstanceOf(UserNotFoundException.class)
				.hasMessage("User does not exist in the database.");
	}

	@Test
	@DisplayName("Throws exception if user with given email & password is not found in the database")
	void Should_throwException_When_UnmatchingUserCredentialsFound() {
		fail("Not yet implemented");
	}
}
