package br.edu.infnet.cochitoapi.model.service;

import java.util.List;

public interface CrudService<T,ID> {

	T salvar(T entity);
	T obter();
	void excluir(ID id);
	List<T> obterLista();
}
