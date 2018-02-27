package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.ak.domain.Professor;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {
	
	public List<Professor> findByCoordenadorTrueOrderByNome();
}
