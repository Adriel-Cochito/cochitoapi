package br.edu.infnet.cochitoapi.model.domain;

public class Funcionario extends Pessoa {

	private int matricula;
	private double salario;
	private boolean ehAtivo;
	private Endereco endereco;
	
	//TODO construtor do vendedor
	
	@Override
	public String toString() {

		return String.format("%s - %d - %.2f - %s - %s", 
				super.toString(), matricula, salario, ehAtivo ? "ativo" : "inativo", endereco);
	}

	@Override
	public String obterTipo() {
		return "Funcionario";
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isEhAtivo() {
		return ehAtivo;
	}

	public void setEhAtivo(boolean ehAtivo) {
		this.ehAtivo = ehAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
