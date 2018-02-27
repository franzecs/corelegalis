package com.ikytus.ak.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ikytus.ak.domain.Curso;

public class CursoConverter implements Converter<String, Curso> {
	
	@Override
	public Curso convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Curso curso = new Curso();
			curso.setId(Long.valueOf(codigo));
			return curso;
		}
		
		return null;
	}

}
