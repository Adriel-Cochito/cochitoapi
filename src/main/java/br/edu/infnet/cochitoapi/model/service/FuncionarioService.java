package br.edu.infnet.cochitoapi.model.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;
import br.edu.infnet.cochitoapi.model.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	private final Map<Integer, Funcionario> mapa = new ConcurrentHashMap<Integer, Funcionario>();

	@Override
	public Funcionario incluir(Funcionario funcionario) {

		validar(funcionario);

		if (funcionario.getNome() == null) {
			throw new RecursoInvalidoException("O nome do funcionario é uma informação obrigatória!");
		}

		return funcionarioRepository.save(funcionario);
	}

	@Override
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

		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}

		return funcionarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("O funcionario com ID " + id + " não foi encontrado!"));
	}

	public Funcionario alterar(Integer id, Funcionario funcionario) {
		// Verifica se o funcionário existe
		obterPorId(id);

		// Valida os dados do funcionário
		validar(funcionario);

		// Define o ID e salva
		funcionario.setId(id);
		mapa.put(id, funcionario);

		return funcionario;
	}

	private void validar(Funcionario funcionario) {
		if (funcionario == null) {
			throw new RecursoInvalidoException("Funcionário não pode ser nulo");
		}

		if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
			throw new RecursoInvalidoException("O nome do funcionário é uma informação obrigatória!");
		}
	}

	public Funcionario inativar(Integer id) {
		// Busca o funcionário existente
		Funcionario funcionarioExistente = obterPorId(id);

		// Altera apenas o status ativo
		funcionarioExistente.setAtivo(false);

		// Salva a alteração
		mapa.put(id, funcionarioExistente);

		return funcionarioExistente;
	}

}