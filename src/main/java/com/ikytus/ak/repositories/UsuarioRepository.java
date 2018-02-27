package com.ikytus.ak.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ikytus.ak.domain.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

	public Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	/*public Page<Usuario> findByNomeContainingIgnoreCaseAndGruposNotContaining(String nome,Grupo grupo,Pageable pageable);*/

}
