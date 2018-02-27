package com.ikytus.ak.util.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMailService extends AbstractEmailService{
		
    private JavaMailSender javaMailSender;
          
    private static final Logger LOG = LoggerFactory.getLogger(JavaMailService.class);

    public JavaMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

  	@Override
	public void sendEmail(SimpleMailMessage msg) {
	}

	@Override
	public void sendHtmlEmail(MimeMessage mimeMessage) {
		LOG.info("Enviando Email...");
		javaMailSender.send(mimeMessage);
		LOG.info("Email enviado");
	}
}
