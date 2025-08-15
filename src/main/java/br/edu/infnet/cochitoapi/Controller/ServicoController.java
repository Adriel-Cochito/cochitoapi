package br.edu.infnet.cochitoapi.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.domain.Servico;
import br.edu.infnet.cochitoapi.model.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;
	
	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@PostMapping
	public ResponseEntity<Servico> incluirServico(@RequestBody Servico servico) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.incluir(servico));
	}
	
	@GetMapping
	public List<Servico> obterServico() {
		return servicoService.obterLista();
	}

	@GetMapping(value = "/{id}")
	public Servico obterPorId(@PathVariable Integer id) {
		return servicoService.obterPorId(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Servico> alterarServico(@PathVariable Integer id,
			@RequestBody Servico servico) {
		return ResponseEntity.ok(servicoService.alterar(id, servico));
	}
}