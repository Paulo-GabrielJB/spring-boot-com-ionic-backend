package com.curso.spring.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.curso.spring.models.dto.ClienteDTO;
import com.curso.spring.models.dto.ClienteNewDTO;
import com.curso.spring.models.entities.enums.Perfil;
import com.curso.spring.models.entities.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_CLIENTE")
	private Long id;
	@Column(name = "NM_CLIENTE")
	private String nome;
	@Column(name = "DS_EMAIL")
	private String email;
	@Column(name = "NM_CPF_CNPJ")
	private String cpfOuCnpj;
	@Column(name = "CD_TIPO_CLIENTE")
	private Integer tipoCliente;
	@JsonIgnore
	@Column(name = "PW_CLIENTE")
	private String senha;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "TB_TELEFONE", joinColumns = @JoinColumn(name = "CD_CLIENTE"))
	@Column(name = "NM_TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "TB_PEFIL", joinColumns = @JoinColumn(name = "CD_CLIENTE"))
	@Column(name = "CD_PERFIL")
	private Set<Integer> perfis = new HashSet<>();

	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(ClienteDTO objDto) {
		id = objDto.getId();
		nome = objDto.getNome();
		email = objDto.getEmail();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(ClienteNewDTO objDto) {
		id = null;
		nome = objDto.getNome();
		email = objDto.getEmail();
		cpfOuCnpj = objDto.getCpfOuCnpj();
		tipoCliente = objDto.getTipoCliente();
		senha = objDto.getSenha();
		Cidade cid = new Cidade(objDto.getCodigoCidade(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), this, cid);
		enderecos.add(end);
		telefones.add(objDto.getTelefone1());
		addPerfil(Perfil.CLIENTE);
		if (objDto.getTelefone2() != null)
			telefones.add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			telefones.add(objDto.getTelefone3());
	}

	public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente == null ? null : tipoCliente.getCodigo();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCodigo();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCodigo());
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
