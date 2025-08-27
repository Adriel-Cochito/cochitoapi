package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

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

		while (linha != null) {

			campos = linha.split(";");

			Funcionario funcionario = new Funcionario();
			funcionario.setNome(campos[0]);
			funcionario.setEmail(campos[1]);
			funcionario.setCpf(campos[2]);
			funcionario.setTelefone(campos[3]);
			funcionario.setMatricula(Integer.valueOf(campos[4]));
			funcionario.setSalario(Double.valueOf(campos[5]));
			funcionario.setAtivo(Boolean.valueOf(campos[6]));

			Endereco endereco = new Endereco();
			endereco.setCep(campos[7]);
			endereco.setLogradouro(campos[8]);
			endereco.setComplemento(campos[9]);
			endereco.setUnidade(campos[10]);
			endereco.setBairro(campos[11]);
			endereco.setLocalidade(campos[12]);
			endereco.setUf(campos[13]);
			endereco.setEstado(campos[14]);

			funcionario.setEndereco(endereco);

			try {
				funcionarioService.incluir(funcionario);
				System.out.println("  [OK] Funcionario " + funcionario.getNome() + " incluído com sucesso.");
			} catch (Exception e) {
				System.err.println("Deu erro! " + e.getMessage());
			}

			linha = leitura.readLine();
		}

		System.out.println("---------------------------: ");
		System.out.println("Profisisonais carregados: ");

		List<Funcionario> funcionarios = funcionarioService.obterLista();
		funcionarios.forEach(System.out::println);

		System.out.println("- Total: " + funcionarios.size());

		leitura.close();
	}
}
