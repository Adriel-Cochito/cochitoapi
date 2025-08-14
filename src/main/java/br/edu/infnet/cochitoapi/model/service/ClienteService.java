package br.edu.infnet.cochitoapi.model.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Cliente;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;

@Service
public class ClienteService implements CrudService<Cliente, Integer> {

	private final Map<Integer, Cliente> mapa = new ConcurrentHashMap<Integer, Cliente>();
	private final AtomicInteger nextId = new AtomicInteger(1);

	@Override
	public Cliente salvar(Cliente cliente) {

		validarCliente(cliente);
		cliente.setId(nextId.getAndIncrement());

		mapa.put(cliente.getId(), cliente);

		return cliente;
	}

	public Cliente incluir(Cliente cliente) {

		validarCliente(cliente);
		cliente.setId(nextId.getAndIncrement());
        
		mapa.put(cliente.getId(), cliente);

		return cliente;
	}

	public Cliente alterar(Integer id, Cliente cliente) {

		obterPorId(id);
		validarCliente(cliente);
		cliente.setId(id);

		mapa.put(id, cliente);
		
		return cliente;
	}

	public Cliente atualizarFidelidade(Integer id, String novaFidelidade) {

		Cliente clienteExistente = obterPorId(id);
		clienteExistente.setFidelidade(novaFidelidade);

		mapa.put(id, clienteExistente);
		
		return clienteExistente;
	}

	@Override
	public void excluir(Integer id) {

		if (!mapa.containsKey(id)) {
			throw new RecursoNaoEncontradoException("Cliente com ID " + id + " não encontrado");
		}
		mapa.remove(id);
	}

	@Override
	public List<Cliente> obterLista() {
		return new ArrayList<Cliente>(mapa.values());
	}

	@Override
	public Cliente obterPorId(Integer id) {
		Cliente cliente = mapa.get(id);
		
		if (cliente == null) {
			throw new RecursoNaoEncontradoException("Cliente com ID " + id + " não encontrado");
		}
		
		return cliente;
	}

	private void validarCliente(Cliente cliente) {
		if (cliente == null) {
			throw new RecursoInvalidoException("Cliente não pode ser nulo");
		}
		
		if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
			throw new RecursoInvalidoException("O nome do cliente é uma informação obrigatória!");
		}
	}
}
