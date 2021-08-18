package exceptions;

public class MeusException extends RuntimeException{
	
	public MeusException(String msg) {
		super(msg);
	}

	public MeusException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
