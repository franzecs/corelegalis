package com.ikytus.ak.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.ikytus.ak.domain.enums.TipoEstagio;

@Entity
@Table(name = "turma_estagio")
public class TurmaEstagio extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(length=6)
	@NotBlank(message = "Informe o semestre")
	private String semestre;
	
	private Integer tipoEstagio;
	
	@ElementCollection
	@CollectionTable(name="disponivel")
	private List<String> disponivel= new ArrayList<>();

	@Column(length=14)
	@NotBlank(message = "Informe o dia do estágio")
	private String dia;

	@Column(length=13)
	@NotBlank(message = "Informe o horário do estágio")
	private String horario;
	
	@Column(length=14)
	private String cargahoraria;

	@Column(length=80)
	@NotBlank(message = "Informe o nome do Professor")
	private String professor;
	
	private String observacao;

	public TurmaEstagio() {
		super();
	}

	public TurmaEstagio(Long id, String img, String semestre,TipoEstagio tipoEstagio,
						String dia,String horario,String cargahoraria, String professor, String observacao) {
		super(id, img);
		this.semestre=semestre;
		this.tipoEstagio=(tipoEstagio==null)? null : tipoEstagio.getCod();
		this.dia=dia;
		this.horario=horario;
		this.cargahoraria=cargahoraria;
		this.professor=professor;
		this.observacao=observacao;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public TipoEstagio getTipoEstagio() {
		return TipoEstagio.toEnum(tipoEstagio);
	}

	public void setTipoEstagio(TipoEstagio tipoEstagio) {
		this.tipoEstagio = tipoEstagio.getCod();
	}

	public List<String> getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(List<String> disponivel) {
		this.disponivel = disponivel;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(String cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
