package com.ikytus.ak.util.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.ikytus.ak.domain.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Mensagem mensagem);
	
	void sendOrderConfirmationHtmlEmail(Mensagem mensagem);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage mimeMessage);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);

	
	
}
