package com.ikytus.ak.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ikytus.ak.domain.enums.StatusAluno;
import com.ikytus.ak.domain.enums.TipoUsuario;;

@Entity
@JsonTypeName("Aluno")
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 12)
	@NotBlank(message="Matrícula obrigatória")
	private String matricula;
	
	@Column(length = 3)
	@NotBlank(message="Semestre obrigatório")
	private String semestre;
	
	@Column(length = 100)
	private String emailsec;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "curso")
	@NotNull(message="Curso obrigatório")
    private Curso curso;
	
	@OneToMany(mappedBy = "aluno", cascade=CascadeType.ALL)
	private List<Estagio> estagios;
	
	private Integer status;

	public Aluno() {
		super();
	}

	public Aluno(Long id, String nome, String cpf, Date dtnasc, TipoUsuario tipo, String senha, String endereco,
			String bairro, String email, String matricula, String emailsec, Curso curso, StatusAluno status) {
		super(id,nome, cpf, dtnasc, tipo, senha, endereco, bairro, email);
		this.matricula = matricula;
		this.emailsec = emailsec;
		this.curso = curso;
		this.status = (status==null)?null: status.getCod();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getEmailsec() {
		return emailsec;
	}

	public void setEmailsec(String emailsec) {
		this.emailsec = emailsec;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
		
	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public StatusAluno getStatus() {
		return StatusAluno.toEnum(status);
	}

	public void setStatus(StatusAluno status) {
		this.status = status.getCod();
	}
}
