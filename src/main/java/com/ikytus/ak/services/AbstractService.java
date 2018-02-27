package com.ikytus.ak.services;

import com.ikytus.ak.domain.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	void salvar(T entidade);

	void salvar(T entidade, String imagem);
}
