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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.ak.domain.TurmaEstagio;
import com.ikytus.ak.domain.enums.Dia;
import com.ikytus.ak.domain.enums.Semestres;
import com.ikytus.ak.domain.enums.TipoEstagio;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.repositories.TurmaEstagioRepository;
import com.ikytus.ak.services.TurmaEstagioService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/turmas_estagio")
public class TurmaEstagioController{
	
	@Autowired
	private TurmaEstagioService service;
			
	@Autowired
	private pageConfig pconfig;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		System.out.println(service.findBySemestre(filter.getNome(), pageSize, page, pageable, ordem));
		return pconfig.montarPagina("npj/consultaTurmaEstagio", filter, service.findBySemestre(filter.getNome(), pageSize, page, pageable, ordem));
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(TurmaEstagio turmaEstagio){
		ModelAndView mv = new ModelAndView("npj/cadastroTurmaEstagio");
		mv.addObject("tipos",TipoEstagio.values());
		mv.addObject("dias",Dia.values());
		mv.addObject("semestres",Semestres.values());
		mv.addObject(turmaEstagio);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TurmaEstagio turmaEstagio, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(turmaEstagio);
		}
		service.salvar(turmaEstagio);
		atributos.addFlashAttribute("mensagem","Turma de Estágio salva com sucesso!");
		return new ModelAndView("redirect:/turmas_estagio/novo");
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","Turma de Estágio removida com sucesso!");
		return new ModelAndView("redirect:/turmas_estagio");
	}
}
