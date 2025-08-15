package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.cochitoapi.model.domain.Endereco;
import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.service.ServicoService;

@Component
public class ServicoLoader implements ApplicationRunner {
	
	private final ServicoService servicoService;
	
	public ServicoLoader(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("servico.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();

		String[] campos = null;


		System.out.println("---------------------------: ");
		System.out.println("Servicos carregados: ");
		
		while(linha != null) {
			
			campos = linha.split(";");
			
			
			Servico servico = new Servico();				
			servico.setTitulo(campos[0]);
			servico.setPreco(Double.valueOf(campos[1]));
			servico.setDescricao(campos[2]);

			
			servicoService.incluir(servico);

			System.out.println(servico);
			
			linha = leitura.readLine();
		}
		
		System.out.println("- Total: " + servicoService.obterLista().size());

		leitura.close();
	}
}
