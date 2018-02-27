package com.ikytus.ak.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.TurmaEstagio;
import com.ikytus.ak.repositories.TurmaEstagioRepository;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class TurmaEstagioService implements AbstractService<TurmaEstagio> {
	
	@Autowired
	private TurmaEstagioRepository repository;
		
	@Autowired
	private pageConfig pconfig;
			
	public void salvar(TurmaEstagio turmaEstagio){
		this.repository.save(turmaEstagio);
	}
	
	public void salvar(TurmaEstagio turmaEstagio, String imagem) {
		turmaEstagio.setImg(imagem);
		this.repository.save(turmaEstagio);
	}
	
	public Page<TurmaEstagio> findBySemestre(String semestre, Optional<Integer> pageSize,	Optional<Integer> page, Pageable pageable, String ordem) {
		return repository.findBySemestre(Optional.ofNullable(semestre).orElse("2018.1"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("tipoEstagio")));
	}
	
	public TurmaEstagio findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
}
