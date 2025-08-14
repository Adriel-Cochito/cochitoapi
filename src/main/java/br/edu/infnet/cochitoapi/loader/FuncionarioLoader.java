package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.cochitoapi.model.domain.Endereco;
import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.service.FuncionarioService;

@Component
public class FuncionarioLoader implements ApplicationRunner {
	
	private final FuncionarioService funcionarioService;
	
	public FuncionarioLoader(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("funcionario.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();

		String[] campos = null;

		System.out.println("---------------------------: ");
		System.out.println("Profisisonais carregados: ");
		
		while(linha != null) {
			
			campos = linha.split(";");
			
			
			Funcionario funcionario = new Funcionario();				
			funcionario.setNome(campos[0]);
			funcionario.setMatricula(Integer.valueOf(campos[1]));
			funcionario.setSalario(Double.valueOf(campos[2]));
			funcionario.setEhAtivo(Boolean.valueOf(campos[3]));

			funcionario.setCpf(campos[4]);
			funcionario.setEmail(campos[5]);
			funcionario.setTelefone(campos[6]);

			Endereco endereco = new Endereco();			
			endereco.setCep(campos[7]);
			endereco.setLocalidade(campos[8]);
			
			funcionario.setEndereco(endereco);
			
			funcionarioService.salvar(funcionario);

			System.out.println(funcionario);
			
			linha = leitura.readLine();
		}
		
		System.out.println("- Total: " + funcionarioService.obterLista().size());

		leitura.close();
	}
}
