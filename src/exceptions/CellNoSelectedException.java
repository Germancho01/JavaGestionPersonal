package exceptions;

public class CellNoSelectedException extends Exception{
	public CellNoSelectedException() {
		super("No se ha seleccionado ninguna celda.");
	}

}
