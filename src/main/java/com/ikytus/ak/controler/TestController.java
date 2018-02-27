package com.ikytus.ak.controler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.enums.StatusAluno;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.repositories.AlunoRepository;
import com.ikytus.ak.repositories.CursoRepository;
import com.ikytus.ak.repositories.UsuarioRepository;

@Controller
@RequestMapping("/testes")
public class TestController {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CursoRepository rep;
	@Autowired
	private AlunoRepository repal;
	
	@Autowired
	private UsuarioRepository repuser;
	
	@PostMapping("/gravarcsv")
	public ModelAndView gravaCSV(@RequestParam("file") Part arquivo) throws IOException {
		
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			Curso curso = new Curso();
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\,");
				String fac = dados[4];
				if(fac.equals("FAECE")) {
					curso = rep.findOne(1L);
				}else {
					curso = rep.findOne(2L);
				}
				if(repuser.findByEmail(dados[2])==null) {
					Aluno al = new Aluno(null, dados[7], null, null, TipoUsuario.ALUNO, 
							pe.encode(dados[6]), null, null, dados[2], dados[6], null, curso, StatusAluno.ATIVO);
					al.getTelefones().addAll(Arrays.asList("(00)00000-0000","(00)00000-0000","(00)00000-0000"));
					repal.save(al);
				};
			
				/*System.out.println("nome: " + dados[7] + " e o RA: " + dados[6] +" email: " + dados[2] + " Curso: " + curso.getNome() +" Faculdade: " + curso.getFaculdade().getNome() + dados[4]);*/
			}
		}
		scanner.close();
						
		return new ModelAndView("redirect:/testes");
	}

}
