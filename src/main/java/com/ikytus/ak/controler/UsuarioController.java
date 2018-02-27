package com.ikytus.ak.controler;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.repositories.UsuarioRepository;
import com.ikytus.ak.services.UsuarioService;
import com.ikytus.ak.util.pageable.pageConfig;
import com.ikytus.ak.util.security.services.UserService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private pageConfig pconfig;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
			
	@Autowired
	private UserService us;
		
	@SuppressWarnings({ "unlikely-arg-type", "static-access" })
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
								
		 if(us.authenticated().getAuthorities().contains(Perfil.ADMIN)) {
			Page<Usuario> listUsuarios = usuarioRepository.findByNomeContainingIgnoreCase(
					Optional.ofNullable(filter.getNome()).orElse("%"),
					pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			return pconfig.montarPagina("security/consultaUsuario", filter,listUsuarios);
		 }
		 Page<Usuario> listUsuarios = usuarioRepository.findByNomeContainingIgnoreCase(
					Optional.ofNullable(filter.getNome()).orElse("%"),
					pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			return pconfig.montarPagina("security/consultaUsuario", filter, listUsuarios);				
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView mv = new ModelAndView("security/cadastroUsuario");
		mv.addObject(usuario);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(usuario);
		}
		usuarioService.salvar(usuario);
		atributos.addFlashAttribute("mensagem","Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/administrador/usuarios/novo").addObject(usuario);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Usuario usuario = usuarioRepository.findOne(codigo); 
		usuario.setSenha(usuario.getSenha());
		return novo(usuario);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		
		usuarioRepository.delete(codigo);
		
		atributos.addFlashAttribute("mensagem","Usuário removido com sucesso!");
		return new ModelAndView("redirect:/administrador/usuarios");
	}
}
