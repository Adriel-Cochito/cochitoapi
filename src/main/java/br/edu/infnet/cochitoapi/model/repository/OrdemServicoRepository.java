package br.edu.infnet.cochitoapi.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cochitoapi.model.domain.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    List<OrdemServico> findByStatus(String status);
    
    List<OrdemServico> findByClienteIdAndStatus(Integer clienteId, String status);
    
    List<OrdemServico> findByFuncionarioIdAndStatus(Integer funcionarioId, String status);
    
    List<OrdemServico> findByDataCriacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    List<OrdemServico> findByServicoTituloContainingIgnoreCase(String titulo);
    
    List<OrdemServico> findByClienteNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT os FROM OrdemServico os WHERE os.cliente.cpf = :cpf")
    List<OrdemServico> findByClienteCpf(@Param("cpf") String cpf);
    
    @Query("SELECT COUNT(os) FROM OrdemServico os WHERE os.status = :status")
    long countByStatus(@Param("status") String status);
    
    @Query("SELECT os FROM OrdemServico os WHERE os.status = 'PENDENTE' AND os.dataCriacao BETWEEN :inicio AND :fim")
    List<OrdemServico> findOrdensPendentesPorPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}