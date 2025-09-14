package br.edu.infnet.cochitoapi.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente extends Pessoa {

    @NotBlank(message = "A fidelidade é obrigatória.")
    @Size(min = 3, max = 20, message = "Fidelidade deve ter entre 3 e 20 caracteres.")
    private String fidelidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    private Endereco endereco;

    // Construtor padrão
    public Cliente() {}

    // Construtor com parâmetros básicos
    public Cliente(String nome, String email, String cpf, String telefone, String fidelidade) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setTelefone(telefone);
        this.fidelidade = fidelidade;
    }

    // Construtor completo com endereço
    public Cliente(String nome, String email, String cpf, String telefone, String fidelidade, Endereco endereco) {
        this(nome, email, cpf, telefone, fidelidade);
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return String.format("Cliente{%s, fidelidade='%s', endereco=%s}", 
                           super.toString(), fidelidade, endereco != null ? endereco.toString() : "null");
    }

    @Override
    public String obterTipo() {
        return "Cliente";
    }

    // Getters e Setters
    public String getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(String fidelidade) {
        this.fidelidade = fidelidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Método utilitário para verificar se tem endereço
    public boolean temEndereco() {
        return endereco != null;
    }

    // Método para obter endereço completo como string
    public String getEnderecoCompleto() {
        if (endereco == null) {
            return "Endereço não informado";
        }
        return endereco.getLogradouro() + ", " + endereco.getBairro() + " - " + 
               endereco.getLocalidade() + "/" + endereco.getUf() + " - CEP: " + endereco.getCep();
    }
}