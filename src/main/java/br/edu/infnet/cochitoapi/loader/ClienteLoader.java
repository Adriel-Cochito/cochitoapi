package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.cochitoapi.model.domain.Cliente;
import br.edu.infnet.cochitoapi.model.service.ClienteService;

@Component
public class ClienteLoader implements ApplicationRunner {
	
	private final ClienteService clienteService;
	
	public ClienteLoader(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("cliente.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;

		System.out.println("---------------------------: ");
		System.out.println("Clientes carregados: ");
		
		while(linha != null) {
			
			campos = linha.split(";");
			
			Cliente cliente = new Cliente();				
			cliente.setNome(campos[0]);
			cliente.setCpf(campos[1]);
			cliente.setEmail(campos[2]);
			cliente.setTelefone(campos[3]);
			cliente.setFidelidade(campos[4]);
			
			clienteService.salvar(cliente);

			System.out.println(cliente);
			
			linha = leitura.readLine();
		}
		
		System.out.println("- Total: " + clienteService.obterLista().size());

		leitura.close();
	}
}