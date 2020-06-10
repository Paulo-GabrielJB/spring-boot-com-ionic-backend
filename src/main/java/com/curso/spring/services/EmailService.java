package com.curso.spring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.curso.spring.models.entities.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	void sendHtmlEmail(MimeMessage msg);
	
}
