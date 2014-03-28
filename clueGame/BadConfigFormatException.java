package clueGame;

public class BadConfigFormatException extends RuntimeException {
	private String message = "";

	public BadConfigFormatException(String s){
		//message is given depending on why the exception is thrown
		message = s;
	}

	@Override
	public String toString() {
		return ("BadConfigFormatException thrown for the following reason: " + message);
	}
}