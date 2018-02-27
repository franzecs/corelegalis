package com.ikytus.ak.util.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ikytus.ak.domain.Usuario;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("default.sender")
	private String sender;
		
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Mensagem mensagem) {
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Mensagem mensagem){
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareHtmlMailMessageFromPedido(mensagem);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareNewPasswordEmail(usuario, newPass);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	@Profile("dev")
	private MimeMessage prepareHtmlMailMessageFromPedido(Mensagem mensagem) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(mensagem.getRemetente());
			helper.setTo((InternetAddress) mensagem.getDestinatarios());
			helper.setSubject(mensagem.getAssunto());
			helper.setSentDate(new Date());
			
			StringBuilder builder = new StringBuilder();
				builder.append("<html>");
				builder.append("<body>");
				builder.append("<p>" + mensagem.getCorpo() + "</p>");
				builder.append("</body>");
				builder.append("</html>");
			helper.setText(builder.toString(), true);
		return mimeMessage;
	}
	
	@Profile("dev")
	private MimeMessage prepareNewPasswordEmail(Usuario usuario, String newPass) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
	
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(sender);
			helper.setTo(usuario.getEmail());
			helper.setSubject("Solicitação de nova senha");
			helper.setSentDate(new Date());
			
			StringBuilder builder = new StringBuilder();
				builder.append("<html>");
				builder.append("<body>");
				builder.append("<h1>" + "Nova Senha: " + newPass + "</h1>");
				builder.append("</body>");
				builder.append("</html>");
			helper.setText(builder.toString(), true);
		return mimeMessage;
	}
}