package com.ikytus.ak.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ak")
public class HomeController {
	
	@GetMapping()
	public ModelAndView home (){
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

}