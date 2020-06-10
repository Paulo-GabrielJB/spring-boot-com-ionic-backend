package com.curso.spring.services.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.curso.spring.models.entities.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
		//em situações reais é necessario chamar um webservice de geração de boletos
	}

}
