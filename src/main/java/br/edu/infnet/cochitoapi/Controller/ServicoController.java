package br.edu.infnet.cochitoapi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;
	
	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@GetMapping
	public List<Servico> obterServico() {
		return servicoService.obterLista();
	}

	@GetMapping(value = "/{id}")
	public Servico obterPorId(@PathVariable Integer id) {
		return servicoService.obterPorId(id);
	}
}