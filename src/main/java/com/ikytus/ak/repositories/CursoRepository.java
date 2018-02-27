package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.Faculdade;

@Repository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {
	
	public Page<Curso>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	public List<Curso>findByFaculdade(Faculdade faculdade);
	
	public List<Curso>findAll();

}
