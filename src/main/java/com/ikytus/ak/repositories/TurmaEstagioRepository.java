package com.ikytus.ak.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.TurmaEstagio;

@Repository
public interface TurmaEstagioRepository extends PagingAndSortingRepository <TurmaEstagio, Long> {
	
	public Page<TurmaEstagio>findBySemestre(String semestre, Pageable pageable);
}
