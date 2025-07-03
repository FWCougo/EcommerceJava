package br.sistema.exceptions;
import java.util.InputMismatchException;

public class DadoErradoException extends InputMismatchException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DadoErradoException(String mensagemErro) {
		super("Um dado inválido foi digitado durante a execução do programa. Por favor, tente novamente.");
		
	}

}
