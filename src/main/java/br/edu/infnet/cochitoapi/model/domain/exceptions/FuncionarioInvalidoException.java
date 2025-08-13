package br.edu.infnet.cochitoapi.model.domain.exceptions;

public class FuncionarioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FuncionarioInvalidoException(String mensagem) {
		super(mensagem);
	}
}
