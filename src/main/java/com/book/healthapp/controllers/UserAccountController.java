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
	public ModelAndView processLogin(ModelMap model, @RequestParam("emailaddress") String email, 
			@RequestParam("password") String password) {
		
		User user = userService.isValidUser(email, password);
		if(user == null) {
			model.addAttribute("message", "Username or password is incorrect. Please try again!");
			return new ModelAndView("login", model);
		}
		model.addAttribute("user", user);
		return new ModelAndView("home", model);
	}

	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
    }	

	@RequestMapping(value="/signup/process", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ExecutionStatus processSignup(ModelMap model, @RequestBody User reqUser) {
		
		User user = userService.doesUserExist(reqUser.getEmail());
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
		
		User user = userService.doesUserExist(email);
		if(user != null) {
			
		}	
		model.addAttribute("message", "An email notification is sent to the registered email address.");
		return new ModelAndView("forgotpassword", model);
	}
}
