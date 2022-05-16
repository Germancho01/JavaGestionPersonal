package classes;

public class Vehiculo {
	public Integer idVehiculo;
	public String nombre, color;

	public Vehiculo(Integer idVehiculo, String nombre, String color) {
		super();
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.color = color;
	}

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", nombre=" + nombre + ", color=" + color + "]";
	}

}
