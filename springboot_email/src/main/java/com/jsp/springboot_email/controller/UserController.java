package com.jsp.springboot_email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.springboot_email.dto.UserEmail;
import com.jsp.springboot_email.service.UserService;

import jakarta.mail.MessagingException;
@RestController
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/mail")
	public void sendEmail(@RequestBody UserEmail email) {
		service.sendMsg(email);
	}
	
	@GetMapping("/otp")
	public void sendOtp(@RequestBody UserEmail email) {
		service.sendOTP(email);
	}
	
	@GetMapping("/attach")
	public void sendAttachment(@RequestBody UserEmail email) {
		try {
			service.sendAttachment(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
