package com.ikytus.ak.controler;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.services.CursoService;
import com.ikytus.ak.util.image.ImageService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/cursos")
public class CursoController{
	
	@Autowired
	private CursoService service;
	
	@Autowired
	private ImageService is;
	
	@Autowired
	private pageConfig pconfig;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		return pconfig.montarPagina("administrador/consultaCurso", filter, service.findByName(filter.getNome(), pageSize, page, pageable, ordem));
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Curso curso){
		ModelAndView mv = new ModelAndView("administrador/cadastroCurso");
		mv.addObject(curso);
		mv.addObject("listaies", service.getFaculdades());
		mv.addObject("coordenadores", service.getCoordenadores());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file,
								@Valid Curso curso, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(curso);
		}
		is.gravaImagemBase64Service(file,service,curso);
		atributos.addFlashAttribute("mensagem","Curso salvo com sucesso!");
		return new ModelAndView("redirect:/cursos/novo");
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","Curso removido com sucesso!");
		return new ModelAndView("redirect:/cursos");
	}
}
