package com.ikytus.ak.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.TipoUsuario;;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Nome obrigatório")
	@Column(length = 80)
	private String nome;
	
	@Column(length = 15)
	private String cpf;
		
    @Past(message="Data de Nascimento incorreta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dtnasc;
	
	private Integer tipo;
	
	@Column(length = 100)
	private String senha;
	
	@Column(length = 100)
	private String endereco;
	
	@Column(length = 50)
	private String bairro;
	
	@Column(unique=true,length=150)
	@NotBlank(message="E-mail obrigatório")
	private String email;
	
	@ElementCollection
	@CollectionTable(name="telefone")
	private List<String> telefones = new ArrayList<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="perfis")
	private Set<Integer> perfis = new HashSet<>();
	
				
	public Usuario() {
		addPerfil(Perfil.USUARIO);
	}
	
	public Usuario(Long id,String nome, String cpf, Date dtnasc, TipoUsuario tipo, String senha, String endereco, String bairro,
			String email) {
		super();
		super.setId(id);
		this.nome = nome;
		this.cpf = cpf;
		this.dtnasc = dtnasc;
		this.tipo = (tipo==null)?null: tipo.getCod();
		this.senha = senha;
		this.endereco = endereco;
		this.bairro = bairro;
		this.email = email;
		addPerfil(Perfil.USUARIO);
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

	public TipoUsuario getTipo() {
		return TipoUsuario.toEnum(tipo);
	}
	
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo.getCod();
	}
			
	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
			
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(Date dtnasc) {
		this.dtnasc = dtnasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Set<Perfil> getPerfis(){
		return  perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	
}
