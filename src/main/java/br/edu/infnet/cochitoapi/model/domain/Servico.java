package br.edu.infnet.cochitoapi.model.domain;

public class Servico {

    private Integer id;

    private String titulo;
    private double preco;
	private String descricao;


    @Override
	public String toString() {
		return titulo + " - " + preco + " - " + descricao;
	}

    public Integer getId() {
		return id;
	}

    public void setId(Integer id) {
		this.id = id;
	}


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public double getPreco() {
        return preco;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

}
