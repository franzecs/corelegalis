package com.ikytus.ak.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.Faculdade;
import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.domain.Funcionario;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.repositories.CursoRepository;
import com.ikytus.ak.repositories.FaculdadeRepository;
import com.ikytus.ak.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
			
	public void instantiateUserInitial() throws ParseException {
		
		Usuario user = new Funcionario(null, "Francisco José C. Silva", "616.498.643-53", new Date(), TipoUsuario.FUNCIONARIO, pe.encode("123"), "Av. de Contorno Oeste, bl 30 Apto 22A", "Nova Metrópole", "franzecs@gmail.com", "65795-6");
		user.getTelefones().addAll(Arrays.asList("(85) 988951038", "(85) 4009-3421"));
		user.addPerfil(Perfil.ADMIN);
		usuarioRepository.save(Arrays.asList(user));
		
		Faculdade fac1 = new Faculdade(null, null, "Faculdade de Ensino e Cultura do Ceará", "FAECE", "Associação Cearense de Ensino e Cultura - ASCEC");
		Faculdade fac2 = new Faculdade(null, null, "Faculdade de Fortaleza", "FAFOR", "ASSUPERO");
		faculdadeRepository.save(Arrays.asList(fac1,fac2));
		
		Curso c1 = new Curso(null, null, "Direito", null, "Reconhecido", null, null, null, null, null, fac1);
		Curso c2 = new Curso(null, null, "Direito", null, "Reconhecido", null, null, null, null, null, fac2);
		cursoRepository.save(Arrays.asList(c1,c2));
	}
}
