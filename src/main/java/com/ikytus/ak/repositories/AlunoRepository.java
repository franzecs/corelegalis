package com.ikytus.ak.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.Aluno;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository <Aluno, Long> {
	
	public Page<Aluno>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
}
