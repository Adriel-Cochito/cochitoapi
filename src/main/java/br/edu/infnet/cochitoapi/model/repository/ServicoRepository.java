package br.edu.infnet.cochitoapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cochitoapi.model.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}