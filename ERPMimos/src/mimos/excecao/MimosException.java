package mimos.excecao;

public class MimosException extends Exception{
	public MimosException(String message) {
        super(message);
    }

    public MimosException() {
        super("erro:desconhecido");
    }

}
