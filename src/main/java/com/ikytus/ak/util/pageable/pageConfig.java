package com.ikytus.ak.util.pageable;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.ModelAndView;

import com.ikytus.ak.dto.Filter;

@Configuration
public class pageConfig {
	
	public static final int BUTTONS_TO_SHOW = 5;
	public static final int INITIAL_PAGE = 0;
	public static final int INITIAL_PAGE_SIZE = 10;
	public static final int[] PAGE_SIZES = { 2, 5, 10, 20 };
	public int evalPageSize;
	public int evalPage;
		
	public Pageable showPage(Optional<Integer> pageSize,Optional<Integer> page, Pageable pageable, String campoOrder) {
		evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		pageable = new PageRequest(evalPage, evalPageSize, Sort.Direction.ASC, campoOrder);
		
		return pageable;
	}
			
	public Pager pagina(@SuppressWarnings("rawtypes") Page page){
		Pager pager = new Pager(page.getTotalPages(), page.getNumber(),BUTTONS_TO_SHOW);
		return pager;
	}
	
	public ModelAndView montarPagina( String url, Filter filter, Page<?> listaObjetos) {
								
		ModelAndView mv = new ModelAndView(url)
							.addObject("lista", listaObjetos)
							.addObject("filtro", filter)
							.addObject("selectedPageSize", evalPageSize)
							.addObject("pageSizes", PAGE_SIZES)
							.addObject("pager", pagina(listaObjetos));
		return mv;
	}	
}
