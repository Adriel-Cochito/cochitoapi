package br.edu.infnet.cochitoapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.cochitoapi.model.domain.Funcionario;
import br.edu.infnet.cochitoapi.model.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
	
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@GetMapping
	public Funcionario obterVendedor() {
		
		return funcionarioService.obter();
	}
}