package br.edu.infnet.cochitoapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;
import br.edu.infnet.cochitoapi.model.repository.ServicoRepository;
import jakarta.transaction.Transactional;

@Service
public class ServicoService implements CrudService<Servico, Integer> {

	private final ServicoRepository servicoRepository;

	public ServicoService(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}

	@Override
	@Transactional
	public Servico incluir(Servico servico) {
		validarServico(servico);
		return servicoRepository.save(servico);
	}

	@Override
	@Transactional
	public Servico alterar(Integer id, Servico servico) {
		// Verifica se o serviço existe
		obterPorId(id);
		
		validarServico(servico);
		
		// Define o ID e salva
		servico.setId(id);
		return servicoRepository.save(servico);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		Servico servico = obterPorId(id);
		servicoRepository.delete(servico);
	}

	@Override
	public List<Servico> obterLista() {
		return servicoRepository.findAll();
	}

	@Override
	public Servico obterPorId(Integer id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID não pode ser nulo ou menor/igual a zero!");
		}
		
		return servicoRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("Serviço com ID " + id + " não encontrado")
		);
	}

	private void validarServico(Servico servico) {
		if (servico == null) {
			throw new RecursoInvalidoException("Serviço não pode ser nulo");
		}

		if (servico.getTitulo() == null || servico.getTitulo().trim().isEmpty()) {
			throw new RecursoInvalidoException("O título do serviço é uma informação obrigatória!");
		}
	}
}