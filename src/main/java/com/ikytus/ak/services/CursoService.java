package com.ikytus.ak.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.Faculdade;
import com.ikytus.ak.domain.Professor;
import com.ikytus.ak.repositories.CursoRepository;
import com.ikytus.ak.repositories.FaculdadeRepository;
import com.ikytus.ak.repositories.ProfessorRepository;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class CursoService implements AbstractService<Curso> {
	
	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private pageConfig pconfig;
			
	public void salvar(Curso curso){
		curso.setNome(curso.getNome().toUpperCase());
		this.repository.save(curso);
	}
	
	public void salvar(Curso curso, String imagem) {
		curso.setImg(imagem);
		curso.setNome(curso.getNome().toUpperCase());
		this.repository.save(curso);
	}
	
	public Page<Curso> findByName(String nome, Optional<Integer> pageSize,	Optional<Integer> page, Pageable pageable, String ordem) {
		return repository.findByNomeContainingIgnoreCase(Optional.ofNullable(nome).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
	}
	
	public Curso findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
	
	public List<Faculdade> getFaculdades(){
		return (List<Faculdade>) faculdadeRepository.findAll();
	}
	
	public List<Professor> getCoordenadores(){
		return professorRepository.findByCoordenadorTrueOrderByNome();
	}
	
}
