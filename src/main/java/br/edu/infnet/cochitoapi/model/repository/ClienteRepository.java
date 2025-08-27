package br.edu.infnet.cochitoapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cochitoapi.model.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Query Method para buscar por CPF (necessário para OrdemServicoLoader)
    Cliente findByCpf(String cpf);
}