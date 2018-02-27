package com.ikytus.ak.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.repositories.AlunoRepository;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class AlunoService implements AbstractService<Aluno> {
	
	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private pageConfig pconfig;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void salvar(Aluno aluno){
		setAtributes(aluno);
		this.repository.save(aluno);
	}
		
	public void salvar(Aluno aluno, String foto){
		aluno.setImg(foto);
		setAtributes(aluno);
		this.repository.save(aluno);
	}
	
	public Page<Aluno> findByName(String nome, Optional<Integer> pageSize,	Optional<Integer> page, Pageable pageable, String ordem) {
		return repository.findByNomeContainingIgnoreCase(Optional.ofNullable(nome).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
	}
	
	public Aluno findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
	
	private void setAtributes(Aluno aluno) {
		aluno.setNome(aluno.getNome().toUpperCase());
		aluno.setEndereco(aluno.getEndereco().toUpperCase());
		aluno.setBairro(aluno.getBairro().toUpperCase());
		aluno.setEmail(aluno.getEmail().toLowerCase());
		if(!aluno.getSenha().trim().isEmpty()) {
			aluno.setSenha(pe.encode(aluno.getSenha()));
		}else {
			aluno.setSenha(pe.encode(aluno.getMatricula()));
		}
		aluno.setTipo(TipoUsuario.ALUNO);
		if(!aluno.getPerfis().contains(Perfil.ALUNO)) {
			aluno.addPerfil(Perfil.ALUNO);
		}
		
	}
}
