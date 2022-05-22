package exceptions;

public class ItemNoSelectedException extends Exception{
	public ItemNoSelectedException(){
		super("Debe seleccionar al propietario del vehículo.");
	}

}
