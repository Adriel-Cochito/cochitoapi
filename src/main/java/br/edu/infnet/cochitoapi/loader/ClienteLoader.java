package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.cochitoapi.model.domain.Cliente;
import br.edu.infnet.cochitoapi.model.domain.Endereco;
import br.edu.infnet.cochitoapi.model.service.ClienteService;

@Component
@Order(3)
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
            
            // Criar e associar endereço (seguindo o padrão do FuncionarioLoader)
            Endereco endereco = new Endereco();
            endereco.setCep(campos[5]);
            endereco.setLogradouro(campos[6]);
            endereco.setComplemento(campos[7]);
            endereco.setUnidade(campos[8]);
            endereco.setBairro(campos[9]);
            endereco.setLocalidade(campos[10]);
            endereco.setUf(campos[11]);
            endereco.setEstado(campos[12]);
            
            cliente.setEndereco(endereco);
            
            try {
                clienteService.incluir(cliente);
                System.out.println("  [OK] Cliente " + cliente.getNome() + " incluído com sucesso.");
            } catch (Exception e) {
                System.err.println("Erro ao incluir cliente " + cliente.getNome() + ": " + e.getMessage());
            }
            
            linha = leitura.readLine();
        }
        
        System.out.println("- Total de Clientes: " + clienteService.obterLista().size());

        leitura.close();
    }
}