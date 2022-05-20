package exceptions;

public class FieldNoCompletedException extends Exception {
	public FieldNoCompletedException() {
		super("Todos los campos son obligatorios.");
	}

}
