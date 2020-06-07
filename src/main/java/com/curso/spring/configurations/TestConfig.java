package com.curso.spring.configurations;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.models.entities.Cidade;
import com.curso.spring.models.entities.Cliente;
import com.curso.spring.models.entities.Endereco;
import com.curso.spring.models.entities.Estado;
import com.curso.spring.models.entities.Pagamento;
import com.curso.spring.models.entities.PagamentoComBoleto;
import com.curso.spring.models.entities.PagamentoComCartao;
import com.curso.spring.models.entities.Pedido;
import com.curso.spring.models.entities.Produto;
import com.curso.spring.models.entities.enums.EstadoPagamento;
import com.curso.spring.models.entities.enums.TipoCliente;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.repositories.CidadeRepository;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.repositories.EnderecoRepository;
import com.curso.spring.repositories.EstadoRepository;
import com.curso.spring.repositories.PagamentoRepository;
import com.curso.spring.repositories.PedidoRepository;
import com.curso.spring.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		Categoria c3 = new Categoria(null, "Livros");
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Produto p1 = new Produto(null, "Computador", 2250.0);
		Produto p2 = new Produto(null, "Andre Matos - O Maestro do Heavy Metal", 79.9);
		Produto p3 = new Produto(null, "Impressora", 800.0);
		Produto p4 = new Produto(null, "Mouse", 80.0);
		Produto p5 = new Produto(null, "Cadeira", 250.0);
	
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c3));
		p3.getCategorias().addAll(Arrays.asList(c1, c2));
		p4.getCategorias().addAll(Arrays.asList(c1));
		p5.getCategorias().addAll(Arrays.asList(c2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		
		Cidade ci1 = new Cidade(null, "Uberlandia", e1);
		Cidade ci2 = new Cidade(null, "São Paulo", e2);
		Cidade ci3 = new Cidade(null, "Osasco", e2);
		
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
		
		Cliente cl1 = new Cliente(null, "Maria Brown", "maria@gmail.com", "00000000191", TipoCliente.PESSOA_FISICA);
		cl1.getTelefones().addAll(Arrays.asList("11999999999", "11988888888"));
		
		clienteRepository.save(cl1);
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300",  "Apto 203", "Jardim", "38220834", cl1, ci1);
		Endereco en2 = new Endereco(null, "Avenida Matos", "105",  "Sala 800", "Centro", "38777012", cl1, ci2);
		Endereco en3 = new Endereco(null, "Joao Ventura dos Santos", "850",  null, "Baronesa", "0625070", cl1, ci2);
		
		enderecoRepository.saveAll(Arrays.asList(en1, en2, en3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, en2);
		
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/20/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}

}
