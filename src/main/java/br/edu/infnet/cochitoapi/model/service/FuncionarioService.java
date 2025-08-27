package br.edu.infnet.cochitoapi.model.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;
import br.edu.infnet.cochitoapi.model.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	@Transactional
	public Funcionario incluir(Funcionario funcionario) {

		validar(funcionario);

		if (funcionario.getNome() == null) {
			throw new RecursoInvalidoException("O nome do funcionario é uma informação obrigatória!");
		}

		return funcionarioRepository.save(funcionario);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {

		Funcionario funcionario = obterPorId(id);

		funcionarioRepository.delete(funcionario);
	}

	@Override
	public List<Funcionario> obterLista() {

		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario obterPorId(Integer id) {

		if (id == null || id <= 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
		}

		return funcionarioRepository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("O funcionario com ID " + id + " não foi encontrado!"));
	}


	@Override
	@Transactional
	public Funcionario alterar(Integer id, Funcionario funcionario) {

		if (id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
		}

		// Valida os dados do funcionário
		validar(funcionario);

		// Verifica se o funcionário existe
		obterPorId(id);

		// Define o ID e salva
		funcionario.setId(id);

		return funcionarioRepository.save(funcionario);
	}

	@Transactional
	public Funcionario inativar(Integer id) {

		if (id == null || id == 0) {
			throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");
		}

		// Busca o funcionário existente
		Funcionario funcionarioExistente = obterPorId(id);

		if (!funcionarioExistente.isAtivo()) {
			System.out.println("Funcionario " + funcionarioExistente.getNome() + " já está inativo!");
			return funcionarioExistente;
		}

		// Altera apenas o status ativo
		funcionarioExistente.setAtivo(false);

		return funcionarioRepository.save(funcionarioExistente);
	}

	private void validar(Funcionario funcionario) {
		if (funcionario == null) {
			throw new IllegalArgumentException("Funcionário não pode ser nulo");
		}

		if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
			throw new RecursoInvalidoException("O nome do funcionário é uma informação obrigatória!");
		}
	}

}