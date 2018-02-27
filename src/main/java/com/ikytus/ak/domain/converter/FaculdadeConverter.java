package com.ikytus.ak.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ikytus.ak.domain.Faculdade;

public class FaculdadeConverter implements Converter<String, Faculdade> {
	
	@Override
	public Faculdade convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Faculdade faculdade = new Faculdade();
			faculdade.setId(Long.valueOf(codigo));
			return faculdade;
		}
		
		return null;
	}

}
