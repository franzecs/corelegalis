package com.ikytus.ak.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.Faculdade;

@Repository
public interface FaculdadeRepository extends PagingAndSortingRepository <Faculdade, Long> {
	
	public Page<Faculdade>findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}

