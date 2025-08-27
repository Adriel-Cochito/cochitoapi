package br.edu.infnet.cochitoapi.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.cochitoapi.model.domain.OrdemServico;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoInvalidoException;
import br.edu.infnet.cochitoapi.model.domain.exceptions.RecursoNaoEncontradoException;
import br.edu.infnet.cochitoapi.model.repository.OrdemServicoRepository;
import jakarta.transaction.Transactional;

@Service
public class OrdemServicoService implements CrudService<OrdemServico, Integer> {

    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    @Override
    @Transactional
    public OrdemServico incluir(OrdemServico ordemServico) {
        validarOrdemServico(ordemServico);
        
        // Define data de criação automaticamente se não informada
        if (ordemServico.getDataCriacao() == null) {
            ordemServico.setDataCriacao(LocalDateTime.now());
        }
        
        // Define status padrão se não informado
        if (ordemServico.getStatus() == null || ordemServico.getStatus().trim().isEmpty()) {
            ordemServico.setStatus("PENDENTE");
        }
        
        return ordemServicoRepository.save(ordemServico);
    }

    @Override
    @Transactional
    public OrdemServico alterar(Integer id, OrdemServico ordemServico) {
        // Verifica se existe
        obterPorId(id);
        
        validarOrdemServico(ordemServico);
        
        ordemServico.setId(id);
        return ordemServicoRepository.save(ordemServico);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        OrdemServico ordemServico = obterPorId(id);
        ordemServicoRepository.delete(ordemServico);
    }

    @Override
    public List<OrdemServico> obterLista() {
        return ordemServicoRepository.findAll();
    }

    @Override
    public OrdemServico obterPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou menor/igual a zero!");
        }
        
        return ordemServicoRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Ordem de Serviço com ID " + id + " não encontrada")
        );
    }

    // Métodos específicos usando Query Methods (Feature 4)
    
    public List<OrdemServico> buscarPorStatus(String status) {
        return ordemServicoRepository.findByStatus(status);
    }
    
    public List<OrdemServico> buscarPorClienteEStatus(Integer clienteId, String status) {
        return ordemServicoRepository.findByClienteIdAndStatus(clienteId, status);
    }
    
    public List<OrdemServico> buscarPorFuncionarioEStatus(Integer funcionarioId, String status) {
        return ordemServicoRepository.findByFuncionarioIdAndStatus(funcionarioId, status);
    }
    
    public List<OrdemServico> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return ordemServicoRepository.findByDataCriacaoBetween(dataInicio, dataFim);
    }
    
    public List<OrdemServico> buscarPorServicoTitulo(String titulo) {
        return ordemServicoRepository.findByServicoTituloContainingIgnoreCase(titulo);
    }
    
    public List<OrdemServico> buscarPorClienteNome(String nome) {
        return ordemServicoRepository.findByClienteNomeContainingIgnoreCase(nome);
    }
    
    public List<OrdemServico> buscarPorClienteCpf(String cpf) {
        return ordemServicoRepository.findByClienteCpf(cpf);
    }
    
    public long contarPorStatus(String status) {
        return ordemServicoRepository.countByStatus(status);
    }
    
    public List<OrdemServico> buscarPendentesPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return ordemServicoRepository.findOrdensPendentesPorPeriodo(inicio, fim);
    }

    private void validarOrdemServico(OrdemServico ordemServico) {
        if (ordemServico == null) {
            throw new RecursoInvalidoException("Ordem de Serviço não pode ser nula");
        }
        
        if (ordemServico.getCliente() == null) {
            throw new RecursoInvalidoException("Cliente é obrigatório na Ordem de Serviço");
        }
        
        if (ordemServico.getFuncionario() == null) {
            throw new RecursoInvalidoException("Funcionário é obrigatório na Ordem de Serviço");
        }
        
        if (ordemServico.getServico() == null) {
            throw new RecursoInvalidoException("Serviço é obrigatório na Ordem de Serviço");
        }
        
    }
}