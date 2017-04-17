package com.book.healthapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.healthapp.domain.User;
import com.book.healthapp.helpers.HelloMessage;

@Controller
public class RestDemoController {
	
//	@RequestMapping(value="/hello", method=RequestMethod.POST, produces="application/json")
//	@ResponseBody
//	public HelloMessage getHelloMessage(@RequestBody User user) {
//		HelloMessage helloMessage = new HelloMessage();
//		String name = user.getName();
//		helloMessage.setMessage( "Hello " + name + "! How are you doing?");
//		helloMessage.setName(name);
//		return helloMessage;
//	}
}
