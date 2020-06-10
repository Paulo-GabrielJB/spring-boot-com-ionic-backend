package com.curso.spring.services;

import org.springframework.mail.SimpleMailMessage;

import com.curso.spring.models.entities.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
}
