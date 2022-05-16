package classes;

public class Avion extends Vehiculo {

	public Double longitud;
	public Integer cantPasajeros;

	public Avion(Integer idVehiculo, String nombre, String color, Double longitud, Integer cantPasajeros) {
		super(idVehiculo, nombre, color);
		this.longitud = longitud;
		this.cantPasajeros = cantPasajeros;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Integer getCantPasajeros() {
		return cantPasajeros;
	}

	public void setCantPasajeros(Integer cantPasajeros) {
		this.cantPasajeros = cantPasajeros;
	}

}
