package br.edu.infnet.cochitoapi.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.cochitoapi.model.domain.Cliente;
import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.domain.OrdemServico;
import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.service.ClienteService;
import br.edu.infnet.cochitoapi.model.service.FuncionarioService;
import br.edu.infnet.cochitoapi.model.service.OrdemServicoService;
import br.edu.infnet.cochitoapi.model.service.ServicoService;

@Component
@Order(4) // Executa por último (após Funcionario, Cliente e Servico)
public class OrdemServicoLoader implements ApplicationRunner {

    private final OrdemServicoService ordemServicoService;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final ServicoService servicoService;

    public OrdemServicoLoader(OrdemServicoService ordemServicoService, 
                             ClienteService clienteService,
                             FuncionarioService funcionarioService,
                             ServicoService servicoService) {
        this.ordemServicoService = ordemServicoService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.servicoService = servicoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            FileReader arquivo = new FileReader("ordemservico.txt");
            BufferedReader leitura = new BufferedReader(arquivo);

            String linha = leitura.readLine();
            String[] campos = null;

            System.out.println("---------------------------: ");
            System.out.println("Ordens de Serviço carregadas: ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (linha != null) {

                campos = linha.split(";");

                OrdemServico ordemServico = new OrdemServico();

                // Associação dinâmica por CPF (conforme Feature 4)
                String cpfCliente = campos[0];
                try {
                    Cliente cliente = clienteService.buscarPorCpf(cpfCliente);
                    ordemServico.setCliente(cliente);
                } catch (Exception e) {
                    System.err.println("Cliente com CPF " + cpfCliente + " não encontrado. Pulando linha.");
                    linha = leitura.readLine();
                    continue;
                }

                // Associação dinâmica por ID do Funcionário
                Integer funcionarioId = Integer.valueOf(campos[1]);
                try {
                    Funcionario funcionario = funcionarioService.obterPorId(funcionarioId);
                    ordemServico.setFuncionario(funcionario);
                } catch (Exception e) {
                    System.err.println("Funcionário com ID " + funcionarioId + " não encontrado. Pulando linha.");
                    linha = leitura.readLine();
                    continue;
                }

                // Associação dinâmica por ID do Serviço
                Integer servicoId = Integer.valueOf(campos[2]);
                try {
                    Servico servico = servicoService.obterPorId(servicoId);
                    ordemServico.setServico(servico);
                } catch (Exception e) {
                    System.err.println("Serviço com ID " + servicoId + " não encontrado. Pulando linha.");
                    linha = leitura.readLine();
                    continue;
                }

                // Outros campos
                ordemServico.setDataCriacao(LocalDateTime.parse(campos[3], formatter));
                ordemServico.setDataExecucao(LocalDateTime.parse(campos[4], formatter));
                ordemServico.setStatus(campos[5]);

                try {
                    ordemServicoService.incluir(ordemServico);
                    System.out.println("  [OK] Ordem de Serviço incluída: " + ordemServico.toString());
                } catch (Exception e) {
                    System.err.println("Erro ao incluir ordem de serviço: " + e.getMessage());
                }

                linha = leitura.readLine();
            }

            System.out.println("- Total Ordens de Serviço: " + ordemServicoService.obterLista().size());
            leitura.close();

        } catch (java.io.FileNotFoundException e) {
            System.out.println("---------------------------: ");
            System.out.println("Arquivo ordemservico.txt não encontrado. Pulando carregamento de Ordens de Serviço.");
            System.out.println("Para carregar dados, crie o arquivo ordemservico.txt na raiz do projeto.");
        }
    }
}