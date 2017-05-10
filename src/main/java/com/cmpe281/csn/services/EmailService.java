package com.cmpe281.csn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	  private JavaMailSender javaMailSender;

	    @Autowired
	    public EmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    public void sendMail(String toEmail, String subject, String message) {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(toEmail);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(message);
	        mailMessage.setFrom("socialneighborhoodemail@gmail.com");
	        javaMailSender.send(mailMessage);
	}
}