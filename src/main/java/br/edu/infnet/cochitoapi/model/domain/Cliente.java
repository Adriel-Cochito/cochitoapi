package br.edu.infnet.cochitoapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente extends Pessoa {

	@NotBlank(message = "A fidelidade é obrigatória.")
	@Size(min = 3, max = 20, message = "Fidelidade deve ter entre 3 e 20 caracteres.")
	private String fidelidade;

	// Construtor padrão
	public Cliente() {}
	
	// Construtor com parâmetros
	public Cliente(String nome, String email, String cpf, String telefone, String fidelidade) {
		this.setNome(nome);
		this.setEmail(email);
		this.setCpf(cpf);
		this.setTelefone(telefone);
		this.fidelidade = fidelidade;
	}
	
	@Override
	public String toString() {
		return String.format("Cliente{%s, fidelidade='%s'}", super.toString(), fidelidade);
	}
	
	@Override
	public String obterTipo() {
		return "Cliente";
	}

	public String getFidelidade() {
		return fidelidade;
	}

	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}
}