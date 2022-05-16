package exceptions;

public class ItemNoSelectedException extends Exception{
	public ItemNoSelectedException() {
		super("No se han ingresado todos los campos en fecha de nacimiento.");
	}

}
