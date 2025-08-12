package br.edu.infnet.cochitoapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.Endereco;
import br.edu.infnet.cochitoapi.model.domain.Funcionario;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

	private final Map<Integer, Funcionario> mapa = new ConcurrentHashMap<Integer, Funcionario>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public Funcionario salvar(Funcionario funcionario) {
		
		funcionario.setId(nextId.getAndIncrement());
		
		mapa.put(funcionario.getId(), funcionario);
		
		return funcionario;
	}

	@Override
	public Funcionario obter() {
		Endereco endereco = new Endereco();			
		endereco.setCep("38180000");
		endereco.setLocalidade("Minas Gerais");

		Funcionario vendedor = new Funcionario();				
		vendedor.setNome("Adriel Cochito");
		vendedor.setMatricula(123);
		vendedor.setSalario(5000);
		vendedor.setEhAtivo(true);
		
		vendedor.setEndereco(endereco);

		return vendedor;
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

	@Override
	public List<Funcionario> obterLista() {
		
		return new ArrayList<Funcionario>(mapa.values());
	}
} 