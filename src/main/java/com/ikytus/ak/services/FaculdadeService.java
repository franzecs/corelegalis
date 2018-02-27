package com.ikytus.ak.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Faculdade;
import com.ikytus.ak.repositories.FaculdadeRepository;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class FaculdadeService implements AbstractService<Faculdade> {
	
	@Autowired
	private FaculdadeRepository repository;
	
	@Autowired
	private pageConfig pconfig;
	
	public void salvar(Faculdade faculdade){
		faculdade.setNome(faculdade.getNome().toUpperCase());
		faculdade.setSigla(faculdade.getSigla().toUpperCase());
		faculdade.setMantenedora(faculdade.getMantenedora().toUpperCase());
		this.repository.save(faculdade);
	}
		
	public void salvar(Faculdade faculdade, String foto){
		faculdade.setImg(foto);
		faculdade.setNome(faculdade.getNome().toUpperCase());
		faculdade.setSigla(faculdade.getSigla().toUpperCase());
		faculdade.setMantenedora(faculdade.getMantenedora().toUpperCase());
		this.repository.save(faculdade);
	}
	
	public Page<Faculdade> findByName(String nome, Optional<Integer> pageSize,	Optional<Integer> page, Pageable pageable, String ordem) {
		return repository.findByNomeContainingIgnoreCase(Optional.ofNullable(nome).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
	}
	
	public Faculdade findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
	
}
