package com.ikytus.ak.config;

import java.text.ParseException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ikytus.ak.services.DBService;
import com.ikytus.ak.util.email.EmailService;
import com.ikytus.ak.util.email.JavaMailService;

@Configuration
public class AppConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
		
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateUserInitial();
		return true;
	}
	
	@Bean(name = "javaMailSender")
	public JavaMailSender getJavaMailSender() {
	   
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("fafor.edu@gmail.com");
	    mailSender.setPassword("senha1234");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "false");
	     
	    return mailSender;
	}
  		
	@Bean
	public EmailService emailService() {
		return new JavaMailService(getJavaMailSender());
	}
}
