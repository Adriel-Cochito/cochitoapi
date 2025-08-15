package br.edu.infnet.cochitoapi.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@PostMapping
	public ResponseEntity<Funcionario> incluirFuncionario(@RequestBody Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.incluir(funcionario));
	}

	@GetMapping
	public List<Funcionario> obterFuncionario() {
		return funcionarioService.obterLista();
	}

	@GetMapping(value = "/{id}")
	public Funcionario obterPorId(@PathVariable Integer id) {
		return funcionarioService.obterPorId(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer id) {
		funcionarioService.excluir(id);
		return ResponseEntity.noContent().build(); // 204 NO CONTENT
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Funcionario> alterarFuncionario(@PathVariable Integer id,
			@RequestBody Funcionario funcionario) {
		return ResponseEntity.ok(funcionarioService.alterar(id, funcionario));
	}

	@PatchMapping(value = "/{id}/inativar")
	public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable Integer id) {
		return ResponseEntity.ok(funcionarioService.inativar(id));
	}

}