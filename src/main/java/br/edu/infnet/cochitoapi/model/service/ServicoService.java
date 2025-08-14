package br.edu.infnet.cochitoapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.domain.exceptions.ServicoInvalidoException;

@Service
public class ServicoService implements CrudService<Servico, Integer> {

	private final Map<Integer, Servico> mapa = new ConcurrentHashMap<Integer, Servico>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Servico salvar(Servico servico) {
		
		if(servico.getTitulo() == null) {
			throw new ServicoInvalidoException("O nome do servico é uma informação obrigatória!");
		}
		
		servico.setId(nextId.getAndIncrement());
		mapa.put(servico.getId(), servico);
		
		return servico;
	}


	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

	@Override
	public List<Servico> obterLista() {
		
		return new ArrayList<Servico>(mapa.values());
	}

	@Override
	public Servico obterPorId(Integer id) {

		Servico servico = mapa.get(id);
		
		if(servico == null) {
			throw new IllegalArgumentException("Imposível obter o servico pelo ID " + id);
		}
		
		return servico;
	}
} 