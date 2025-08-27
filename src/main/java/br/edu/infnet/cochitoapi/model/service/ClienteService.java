package br.edu.infnet.cochitoapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Cliente;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;
import br.edu.infnet.cochitoapi.model.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService implements CrudService<Cliente, Integer> {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	@Transactional
	public Cliente incluir(Cliente cliente) {
		validarCliente(cliente);
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public Cliente alterar(Integer id, Cliente cliente) {
		// Verifica se o cliente existe
		obterPorId(id);
		
		validarCliente(cliente);
		
		// Define o ID e salva
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente atualizarFidelidade(Integer id, String novaFidelidade) {
		if (novaFidelidade == null || novaFidelidade.trim().isEmpty()) {
			throw new RecursoInvalidoException("Fidelidade não pode ser vazia");
		}
		
		Cliente clienteExistente = obterPorId(id);
		clienteExistente.setFidelidade(novaFidelidade);

		return clienteRepository.save(clienteExistente);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		Cliente cliente = obterPorId(id);
		clienteRepository.delete(cliente);
	}

	@Override
	public List<Cliente> obterLista() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente obterPorId(Integer id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID não pode ser nulo ou menor/igual a zero!");
		}
		
		return clienteRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Cliente com ID " + id + " não encontrado")
		);
	}

	private void validarCliente(Cliente cliente) {
		if (cliente == null) {
			throw new RecursoInvalidoException("Cliente não pode ser nulo");
		}
		
		if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
			throw new RecursoInvalidoException("O nome do cliente é uma informação obrigatória!");
		}
	}

	// Método específico necessário para OrdemServicoLoader (Feature 4)
	public Cliente buscarPorCpf(String cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf);
		if (cliente == null) {
			throw new RecursoNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado");
		}
		return cliente;
	}
}