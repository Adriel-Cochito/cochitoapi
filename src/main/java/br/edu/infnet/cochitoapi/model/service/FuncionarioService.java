package br.edu.infnet.cochitoapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.domain.exceptions.FuncionarioInvalidoException;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

	private final Map<Integer, Funcionario> mapa = new ConcurrentHashMap<Integer, Funcionario>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Funcionario salvar(Funcionario funcionario) {
		
		if(funcionario.getNome() == null) {
			throw new FuncionarioInvalidoException("O nome do funcionario é uma informação obrigatória!");
		}
		
		funcionario.setId(nextId.getAndIncrement());
		mapa.put(funcionario.getId(), funcionario);
		
		return funcionario;
	}


	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

	@Override
	public List<Funcionario> obterLista() {
		
		return new ArrayList<Funcionario>(mapa.values());
	}

	@Override
	public Funcionario obterPorId(Integer id) {

		Funcionario funcionario = mapa.get(id);
		
		if(funcionario == null) {
			throw new IllegalArgumentException("Imposível obter o funcionario pelo ID " + id);
		}
		
		return funcionario;
	}
} 