package com.jsp.springboot_email.dto;

import lombok.Data;

@Data
public class UserEmail {
	private String to;
	private String subject;
	private String body;
	private String attach;
}
