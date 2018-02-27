package com.ikytus.ak.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="faculdade")
public class Faculdade extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotBlank(message="Favor informar o nome da IES")
	private String nome;
	
	@Column
	@NotBlank(message="Favor informar a Sigla da IES")
	private String sigla;
	
	@Column
	@NotBlank(message="Favor informar o nome da Mantenedora")
	private String mantenedora;
		 
	@OneToMany(mappedBy = "faculdade", cascade=CascadeType.ALL)
	private List<Curso> cursos;
			
	public Faculdade() {
		super();
	}

	public Faculdade(Long id, String img, String nome, String sigla, String mantenedora) {
		super(id, img);
		this.nome = nome;
		this.sigla = sigla;
		this.mantenedora = mantenedora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getMantenedora() {
		return mantenedora;
	}

	public void setMantenedora(String mantenedora) {
		this.mantenedora = mantenedora;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
}
