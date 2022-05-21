package classes;

public class Barco extends Vehiculo {
	public double eslora, manga;
	public static int id;

	public Barco(Integer idVehiculo, String nombre, String color, Persona duenio, double eslora, double manga) {
		super(idVehiculo, nombre, color, duenio);

		id++;
		this.eslora = eslora;
		this.manga = manga;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Barco.id = id;
	}

	public double getEslora() {
		return eslora;
	}

	public void setEslora(double eslora) {
		this.eslora = eslora;
	}

	public double getManga() {
		return manga;
	}

	public void setManga(double manga) {
		this.manga = manga;
	}

	@Override
	public String toString() {
		return "\n --- Barco ---" + "\neslora=" + eslora + "\nmanga=" + manga + "\nidVehiculo=" + idVehiculo
				+ "\nnombre=" + nombre + "\ncolor=" + color;
	}

}
