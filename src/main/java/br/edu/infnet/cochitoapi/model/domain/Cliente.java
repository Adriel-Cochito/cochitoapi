package br.edu.infnet.cochitoapi.model.domain;
public class Cliente extends Pessoa {

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
		return String.format("%s - Fidelidade: %s", super.toString(), fidelidade);
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
