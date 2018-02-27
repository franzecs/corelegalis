package com.ikytus.ak.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="curso")
public class Curso extends AbstractEntity {
	private static final long serialVersionUID = 1L;
		
	@NotNull(message="Favor informar o nome do Curso")
	private String nome;
	
	@Column(length=80)
	private String coordenador;
	
	@NotNull(message="Favor informar a situação junto ao MEC")
	private String mec;
	
	@Column(length=24)
	private String horarioseg;
	
	@Column(length=24)
	private String horarioter;
	
	@Column(length=24)
	private String horarioqua;
	
	@Column(length=24)
	private String horarioqui;
	
	@Column(length=24)
	private String horariosex;
			
	@NotNull(message="Favor informar a IES")
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "faculdade")
	private Faculdade faculdade;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "curso_professor", 
    			joinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"), 
    			inverseJoinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"))
    private List<Professor> professores;
	
	@OneToMany(mappedBy = "curso", cascade=CascadeType.ALL)
    private List<Aluno> alunos;
		

	public Curso() {
		super();
	}

	public Curso(Long id, String img, String nome, String coordenador, String mec, 
				String hseg, String hter, String hqua,String hqui,String hsex, Faculdade faculdade) {
		super(id, img);
		this.nome = nome;
		this.coordenador = coordenador;
		this.mec = mec;
		this.horarioseg = hseg;
		this.horarioseg = hter;
		this.horarioseg = hqua;
		this.horarioseg = hqui;
		this.horarioseg = hsex;
		this.faculdade = faculdade;
				
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

	public String getMec() {
		return mec;
	}

	public void setMec(String mec) {
		this.mec = mec;
	}

	public String getHorarioseg() {
		return horarioseg;
	}

	public void setHorarioseg(String horarioseg) {
		this.horarioseg = horarioseg;
	}

	public String getHorarioter() {
		return horarioter;
	}

	public void setHorarioter(String horarioter) {
		this.horarioter = horarioter;
	}

	public String getHorarioqua() {
		return horarioqua;
	}

	public void setHorarioqua(String horarioqua) {
		this.horarioqua = horarioqua;
	}

	public String getHorarioqui() {
		return horarioqui;
	}

	public void setHorarioqui(String horarioqui) {
		this.horarioqui = horarioqui;
	}

	public String getHorariosex() {
		return horariosex;
	}

	public void setHorariosex(String horariosex) {
		this.horariosex = horariosex;
	}

	public Faculdade getFaculdade() {
		return faculdade;
	}

	public void setFaculdade(Faculdade faculdade) {
		this.faculdade = faculdade;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
