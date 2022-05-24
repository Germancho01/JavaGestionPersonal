package classes;

public class Vehiculo {
	public Integer idVehiculo;
	public String nombre, color;
	public Persona duenio;
	public static int id;

	public Vehiculo(Integer idVehiculo, String nombre, String color, Persona duenio) {
		super();

		id++;
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.color = color;
		this.duenio = duenio;
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

	public Persona getDuenio() {
		return duenio;
	}

	public void setDuenio(Persona duenio) {
		this.duenio = duenio;
	}

	@Override
	public String toString() {
		return "\n --- Vehiculo ---" + "\nidVehiculo=" + idVehiculo + "\nnombre=" + nombre + "\ncolor=" + color;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Vehiculo.id = id;
	}

}
