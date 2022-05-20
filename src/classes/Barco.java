package classes;

public class Barco extends Vehiculo {
	public double eslora, manga;

	public Barco(Integer idVehiculo, String nombre, String color, Persona duenio, double eslora, double manga) {
		super(idVehiculo, nombre, color, duenio);
		this.eslora = eslora;
		this.manga = manga;
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
		return "\n --- Barco ---"
				+ "\neslora=" + eslora 
				+ "\nmanga=" + manga
				+ "\nidVehiculo=" + idVehiculo 
				+ "\nnombre=" + nombre
				+ "\ncolor=" + color;
	}

}
