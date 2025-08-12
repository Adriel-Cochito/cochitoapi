package br.edu.infnet.cochitoapi.model.domain;

public class Cliente extends Pessoa {

	private String fidelidade;

	//TODO construtor do comprador
	
	//TODO toString do comprador
	
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