package com.jsp.springboot_email.service;

import java.io.File;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.springboot_email.dto.UserEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {
	@Autowired
	private JavaMailSender emailsender;
	
	public void sendMsg(UserEmail email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
		emailsender.send(message);
	}
	
	public void sendOTP(UserEmail email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email.getTo());
		message.setSubject("you have recieved an otp");
		Random random=new Random();
		int otp=random.nextInt(100000);
		message.setText("your otp for login as:"+otp+"\nplease dont share otp for anyone");
		emailsender.send(message);
	}
	
	public void sendAttachment(UserEmail email) throws MessagingException{
		MimeMessage message=emailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom("aarvindkosana@gmail.com");
		helper.setTo(email.getTo());
		helper.setSubject(email.getSubject());
		helper.setText(email.getBody());
		
		FileSystemResource file=new FileSystemResource(new File(email.getAttach()));
		helper.addAttachment(file.getFilename(), file);
		emailsender.send(message);
	}
}
