package br.edu.infnet.cochitoapi.model.domain;

public abstract class Pessoa {

	private Integer id;
	
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
	//TODO criação do construtor
	
	@Override
	public String toString() {

		return String.format("%d - %s - %s - %s - %s", id, nome, email, cpf, telefone);
	}
	
	public abstract String obterTipo();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}