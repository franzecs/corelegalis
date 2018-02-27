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

import com.ikytus.ak.domain.Faculdade;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.services.FaculdadeService;
import com.ikytus.ak.util.image.ImageService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/faculdades")
public class FaculdadeController {
			
	@Autowired
	private FaculdadeService service;
	
	@Autowired
	private ImageService is;
		
	@Autowired
	private pageConfig pconfig;
			
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
					
		return pconfig.montarPagina("administrador/consultaFaculdade", filter,service.findByName(filter.getNome(), pageSize, page, pageable, ordem));
	}
						
	@GetMapping("/novo")
	public ModelAndView novo(Faculdade faculdade){
		ModelAndView mv = new ModelAndView("administrador/cadastroFaculdade");
		mv.addObject(faculdade);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file,
								@Valid Faculdade faculdade, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(faculdade);
		}
		is.gravaImagemBase64Service(file,service,faculdade);
		atributos.addFlashAttribute("mensagem","Ies salva com sucesso!");
		return new ModelAndView("redirect:/faculdades/novo").addObject(faculdade);
	}
		
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public String deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","IES removida com sucesso!");
		return "redirect:/faculdades";
	}
}