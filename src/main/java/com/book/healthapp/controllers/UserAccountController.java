package com.book.healthapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.healthapp.domain.User;
import com.book.healthapp.exceptions.UnmatchingUserCredentialsException;
import com.book.healthapp.exceptions.UserNotFoundException;
import com.book.healthapp.helpers.ExecutionStatus;
import com.book.healthapp.services.UserService;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {
	
	@Autowired UserService userService;
	
	@GetMapping
	public String login() {
		return "login";
    }
	
	@PostMapping(value="/login/process", produces="application/json")
	public @ResponseBody ExecutionStatus processLogin(ModelMap model, @RequestBody User reqUser) {
		
		User user = null;
		try {
			user = userService.isValidUser(reqUser.getEmail(), reqUser.getPassword());
		} catch (UnmatchingUserCredentialsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user == null) {
			return new ExecutionStatus("USER_LOGIN_UNSUCCESSFUL", "Username or password is incorrect. Please try again!");
		}
		return new ExecutionStatus("USER_LOGIN_SUCCESSFUL", "Login Successful!");
	}

	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
    }	

	@RequestMapping(value="/signup/process", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ExecutionStatus processSignup(ModelMap model, @RequestBody User reqUser) {
		
		User user = null;
		try {
			user = userService.doesUserExist(reqUser.getEmail());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null) {
			return new ExecutionStatus("USER_ACCOUNT_EXISTS", "User account with same email address exists. Please try again!");
		}
		user = new User();
		user.setEmail(reqUser.getEmail());
		user.setPassword(reqUser.getPassword());
		user.setFirstName(reqUser.getFirstname());
		user.setLastname(reqUser.getLastname());
		user.setContactNumber(reqUser.getContactNumber());
		user.setAlternateContactNumber(reqUser.getAlternateContactNumber());
		user.setCityCode(reqUser.getCityCode());
		user.setStateCode(reqUser.getStateCode());
		user.setCountryCode(reqUser.getCountryCode());
		user.setAge(reqUser.getAge());
		user.setGender(reqUser.getGender());
		User persistedUser = userService.save(user);
		return new ExecutionStatus("USER_ACCOUNT_CREATED", "User account successfully created");
	}
	
	@RequestMapping(value="/user/update", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ExecutionStatus updateUser(ModelMap model, @RequestBody User reqUser) {
		User user = new User();
		user.setId(reqUser.getId());
		user.setFirstName(reqUser.getFirstname());
		user.setLastname(reqUser.getLastname());
		user.setContactNumber(reqUser.getContactNumber());
		user.setAlternateContactNumber(reqUser.getAlternateContactNumber());
		user.setCityCode(reqUser.getCityCode());
		user.setStateCode(reqUser.getStateCode());
		user.setCountryCode(reqUser.getCountryCode());
		user.setAge(reqUser.getAge());
		user.setGender(reqUser.getGender());
		userService.update(user);
		return new ExecutionStatus("USER_ACCOUNT_UPDATED", "User account successfully updated");
	}

	@PostMapping(value="/update", produces="application/json")
	public ModelAndView updateProfile(ModelMap model, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("address") String address, 
			@RequestParam("contact_number") String contactNumber) {
		return new ModelAndView("update", model);
	}

	
	@GetMapping("/forgotpassword")
	public String forgotpassword() {
		return "forgotpassword";
    }
	
	@PostMapping(value="/forgotpassword/process", produces="application/json")
	public ModelAndView processForgotPassword(ModelMap model, @RequestParam("emailaddress") String email) {
		
		User user = null;
		try {
			user = userService.doesUserExist(email);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null) {
			
		}	
		model.addAttribute("message", "An email notification is sent to the registered email address.");
		return new ModelAndView("forgotpassword", model);
	}
}
