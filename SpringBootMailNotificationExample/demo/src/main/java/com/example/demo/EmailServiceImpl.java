package com.example.demo;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    public JavaMailSender emailSender;

	public void sendSimpleMessage(String fileName) {
		SimpleMailMessage message = new SimpleMailMessage();
		//JavaMailSenderImpl mailSenderIml =  new JavaMailSenderImpl();
		//Properties props = mailSenderIml.getJavaMailProperties();
		 
				//props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        message.setTo("recipient mailID"); 
        message.setSubject("TestMail"); 
        message.setText("Iam Testing this hope this should work and file found with name "+fileName);
        
        emailSender.send(message);
		
	}

	
	

	
}
