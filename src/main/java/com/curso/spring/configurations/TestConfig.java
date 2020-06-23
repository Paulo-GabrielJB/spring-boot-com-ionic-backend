package com.curso.spring.configurations;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.curso.spring.models.entities.Categoria;
import com.curso.spring.models.entities.Cidade;
import com.curso.spring.models.entities.Cliente;
import com.curso.spring.models.entities.Endereco;
import com.curso.spring.models.entities.Estado;
import com.curso.spring.models.entities.ItemPedido;
import com.curso.spring.models.entities.Pagamento;
import com.curso.spring.models.entities.PagamentoComBoleto;
import com.curso.spring.models.entities.PagamentoComCartao;
import com.curso.spring.models.entities.Pedido;
import com.curso.spring.models.entities.Produto;
import com.curso.spring.models.entities.enums.EstadoPagamento;
import com.curso.spring.models.entities.enums.Perfil;
import com.curso.spring.models.entities.enums.TipoCliente;
import com.curso.spring.repositories.CategoriaRepository;
import com.curso.spring.repositories.CidadeRepository;
import com.curso.spring.repositories.ClienteRepository;
import com.curso.spring.repositories.EnderecoRepository;
import com.curso.spring.repositories.EstadoRepository;
import com.curso.spring.repositories.ItemPedidoRepository;
import com.curso.spring.repositories.PagamentoRepository;
import com.curso.spring.repositories.PedidoRepository;
import com.curso.spring.repositories.ProdutoRepository;
import com.curso.spring.services.EmailService;
import com.curso.spring.services.impl.MockEmailService;

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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {
		
		
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		Categoria c4 = new Categoria(null, "Cama, mesa e banho");
		Categoria c5 = new Categoria(null, "Eletorinicos");
		Categoria c6 = new Categoria(null, "Jardinagem");
		Categoria c7 = new Categoria(null, "Perfumaria");
		Categoria c8 = new Categoria(null, "Decoracao");
		
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c4, c5, c6, c7, c8));
		
		Produto p1 = new Produto(null, "Computador", 2250.0);
		Produto p3 = new Produto(null, "Impressora", 800.0);
		Produto p4 = new Produto(null, "Mouse", 80.0);
		Produto p5 = new Produto(null, "Mesa de escritório", 250.0);
		Produto p6 = new Produto(null, "Toalha", 250.0);
		Produto p7 = new Produto(null, "Colcha", 250.0);
		Produto p8 = new Produto(null, "TV true color", 250.0);
		Produto p9 = new Produto(null, "Roçadeira", 250.0);
		Produto p10 = new Produto(null, "Abajuor", 250.0);
		Produto p11 = new Produto(null, "Pendente", 250.0);
		Produto p12 = new Produto(null, "Shampoo", 250.0);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
	
		produtoRepository.saveAll(Arrays.asList(p1, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		
		
		p1.getCategorias().addAll(Arrays.asList(c1, c5));
		p3.getCategorias().addAll(Arrays.asList(c1, c2, c5));
		p4.getCategorias().addAll(Arrays.asList(c1));
		p5.getCategorias().addAll(Arrays.asList(c2));
		p6.getCategorias().addAll(Arrays.asList(c2));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c8));
		p11.getCategorias().addAll(Arrays.asList(c8));
		p12.getCategorias().addAll(Arrays.asList(c7));
		p12.getCategorias().add(c1);
		p13.getCategorias().add(c1);
		p14.getCategorias().add(c1);
		p15.getCategorias().add(c1);
		p16.getCategorias().add(c1);
		p17.getCategorias().add(c1);
		p18.getCategorias().add(c1);
		p19.getCategorias().add(c1);
		p20.getCategorias().add(c1);
		p21.getCategorias().add(c1);
		p22.getCategorias().add(c1);
		p23.getCategorias().add(c1);
		p24.getCategorias().add(c1);
		p25.getCategorias().add(c1);
		p26.getCategorias().add(c1);
		p27.getCategorias().add(c1);
		p28.getCategorias().add(c1);
		p29.getCategorias().add(c1);
		p30.getCategorias().add(c1);
		p31.getCategorias().add(c1);
		p32.getCategorias().add(c1);
		p33.getCategorias().add(c1);
		p34.getCategorias().add(c1);
		p35.getCategorias().add(c1);
		p36.getCategorias().add(c1);
		p37.getCategorias().add(c1);
		p38.getCategorias().add(c1);
		p39.getCategorias().add(c1);
		p40.getCategorias().add(c1);
		p41.getCategorias().add(c1);
		p42.getCategorias().add(c1);
		p43.getCategorias().add(c1);
		p44.getCategorias().add(c1);
		p45.getCategorias().add(c1);
		p46.getCategorias().add(c1);
		p47.getCategorias().add(c1);
		p48.getCategorias().add(c1);
		p49.getCategorias().add(c1);
		p50.getCategorias().add(c1);
		
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		
		Cidade ci1 = new Cidade(null, "Uberlandia", e1);
		Cidade ci2 = new Cidade(null, "São Paulo", e2);
		Cidade ci3 = new Cidade(null, "Osasco", e2);
		
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
		
		Cliente cl1 = new Cliente(null, "Maria Brown", "maria@gmail.com", "00000000191", TipoCliente.PESSOA_FISICA, pe.encode("maria123"));
		Cliente cl2 = new Cliente(null, "Paulo", "paulo@gmail.com", "60514319003", TipoCliente.PESSOA_FISICA, pe.encode("paulo123"));
		cl2.getTelefones().addAll(Arrays.asList("11977777777", "11966666666"));
		cl2.addPerfil(Perfil.ADMIN);
		cl1.getTelefones().addAll(Arrays.asList("11999999999", "11988888888"));
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300",  "Apto 203", "Jardim", "38220834", cl1, ci1);
		Endereco en2 = new Endereco(null, "Avenida Matos", "105",  "Sala 800", "Centro", "38777012", cl1, ci2);
		Endereco en3 = new Endereco(null, "Joao Ventura dos Santos", "850",  null, "Baronesa", "0625070", cl2, ci2);
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, p3.getPreco());
		ItemPedido ip3 = new ItemPedido(ped2, p4, 100.00, 1, p4.getPreco());
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
