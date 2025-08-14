package br.edu.infnet.cochitoapi.model.domain.exceptions;

public class ServicoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
