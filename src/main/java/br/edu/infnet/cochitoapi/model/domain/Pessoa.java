package br.edu.infnet.cochitoapi.model.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 3, max = 50, message = "O nome deve estar entre 3 e 50 caracteres.")
	private String nome;

	@NotBlank(message = "O e-mail é obrigatório.")
	@Email(message = "O e-mail está inválido.")
	private String email;
	@NotBlank(message = "O CPF é obrigatório")
	
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", 
	         message = "CPF deve estar no formato XXX.XXX.XXX-XX")
	private String cpf;
	
	@NotBlank(message = "O telefone é obrigatório")
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", 
	         message = "Telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
	private String telefone;
	
	//TODO Criação do construtor de pessoa com nome, email, cpf e telefone
	
	@Override
	public String toString() {

		return String.format("id=%d, nome=%s, email=%s, cpf=%s, telefone=%s",
                id, nome, email, cpf, telefone);
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