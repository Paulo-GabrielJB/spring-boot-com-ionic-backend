package com.curso.spring.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.models.entities.ItemPedido;
import com.curso.spring.models.entities.PagamentoComBoleto;
import com.curso.spring.models.entities.Pedido;
import com.curso.spring.models.entities.enums.EstadoPagamento;
import com.curso.spring.repositories.ItemPedidoRepository;
import com.curso.spring.repositories.PagamentoRepository;
import com.curso.spring.repositories.PedidoRepository;
import com.curso.spring.services.ClienteService;
import com.curso.spring.services.EmailService;
import com.curso.spring.services.PedidoService;
import com.curso.spring.services.ProdutoService;
import com.curso.spring.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	
	@Override
	public Pedido find(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma pedido com o id " + id + " encontrada"));
	}

	@Override
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		emailService.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;
	}
	
}
