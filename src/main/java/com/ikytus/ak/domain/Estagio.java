package com.ikytus.ak.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "estagio")
public class Estagio extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "aluno")
	@NotNull(message="Aluno obrigatório")
	private Aluno aluno;
	
	private TurmaEstagio turma;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date datainscricao;

	@Column
	private boolean dp;

	@Column
	private double nota;

	@Column
	private String situacao;

	public Estagio() {
		super();
	}

	public Estagio(Long id, String img, Aluno aluno, TurmaEstagio turma, Date datainscricao, boolean dp, double nota, String situacao) {
		super(id, img);
		this.aluno=aluno;
		this.turma=turma;
		this.datainscricao=datainscricao;
		this.dp=dp;
		this.nota=nota;
		this.situacao=situacao;
	}
		
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public TurmaEstagio getTurma() {
		return turma;
	}

	public void setTurma(TurmaEstagio turma) {
		this.turma = turma;
	}

	public Date getDatainscricao() {
		return datainscricao;
	}

	public void setDatainscricao(Date datainscricao) {
		this.datainscricao = datainscricao;
	}

	public boolean isDp() {
		return dp;
	}

	public void setDp(boolean dp) {
		this.dp = dp;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
