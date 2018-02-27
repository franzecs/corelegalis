package com.ikytus.ak.controler;

import java.util.Arrays;
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

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.dto.Filter;
import com.ikytus.ak.services.AlunoService;
import com.ikytus.ak.util.image.ImageService;
import com.ikytus.ak.util.pageable.pageConfig;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
			
	@Autowired
	private AlunoService service;
	
	@Autowired
	private ImageService is;
		
	@Autowired
	private pageConfig pconfig;
	
			
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		return pconfig.montarPagina("geral/consultaAluno", filter,service.findByName(filter.getNome(), pageSize, page, pageable, ordem));
	}
						
	@GetMapping("/novo")
	public ModelAndView novo(Aluno aluno){
		ModelAndView mv = new ModelAndView("geral/cadastroAluno");
		if(aluno.getId()!=null) {
			mv.addObject("tel1", aluno.getTelefones().get(0));
			mv.addObject("tel2", aluno.getTelefones().get(1));
			mv.addObject("tel3", aluno.getTelefones().get(2));
		}
		
		mv.addObject(aluno);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file,
								@RequestParam(name="tel1", required=false, defaultValue="(00)00000-0000") String tel1,
								@RequestParam(name="tel2", required=false, defaultValue="(00)00000-0000") String tel2,
								@RequestParam(name="tel3", required=false, defaultValue="(00)00000-0000") String tel3,
								@Valid Aluno aluno, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(aluno);
		}
		aluno.getTelefones().addAll(Arrays.asList(tel1,tel2,tel3));
		is.gravaImagemBase64Service(file,service,aluno);
		atributos.addFlashAttribute("mensagem","Aluno salvo com sucesso!");
		return new ModelAndView("redirect:/alunos/novo").addObject(aluno);
	}
		
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		return novo(service.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public String deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		service.delete(codigo);
		atributos.addFlashAttribute("mensagem","Aluno(a) removido(a) com sucesso!");
		return "redirect:/alunos";
	}
}