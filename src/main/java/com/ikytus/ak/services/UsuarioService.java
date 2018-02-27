package com.ikytus.ak.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.repositories.UsuarioRepository;

@Service
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioRepository usuarios;

	
	public void salvar(Usuario usuario){
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setNome(usuario.getNome().toUpperCase());
		this.usuarios.save(usuario);
	}
}
