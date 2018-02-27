package com.ikytus.ak.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ikytus.ak.domain.enums.TipoUsuario;;

@Entity
@JsonTypeName("Professor")
public class Professor extends Usuario{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 7)
	private String funcional;
        
    private String titulo;

    private String emailsecundario;
   
    private String status;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date admissao;
    
    private boolean coordenador; 
   
    @ManyToMany(mappedBy = "professores")
    private List<Curso> cursos;

	public Professor() {
		super();
	}

	public Professor(Long id, String nome, String cpf, Date dtnasc, TipoUsuario tipo, String senha, String endereco,
			String bairro, String email, String funcional, String titulo, String emailSecundario, String status, Date admissao) {
		super(id,nome, cpf, dtnasc, tipo, senha, endereco, bairro, email);
		this.funcional = funcional;
		this.titulo = titulo;
		this.emailsecundario = emailSecundario;
		this.status = status;
		this.admissao = admissao;
	}

	public String getFuncional() {
		return funcional;
	}

	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmailsecundario() {
		return emailsecundario;
	}

	public void setEmailsecundario(String emailsecundario) {
		this.emailsecundario = emailsecundario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}
		
	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
