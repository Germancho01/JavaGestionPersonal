package classes;

import javax.swing.table.DefaultTableModel;

public class Avion extends Vehiculo {

	public Double longitud;
	public Integer cantPasajeros;
	public static int id;

	public Avion(Integer idVehiculo, String nombre, String color, Persona duenio, Double longitud,
			Integer cantPasajeros) {
		super(idVehiculo, nombre, color, duenio);

		id++;
		this.longitud = longitud;
		this.cantPasajeros = cantPasajeros;
	}

	// --------------- Método Cargar Avion --------------------

	public void cargarAvion(DefaultTableModel model) {

		String[] datos = new String[5];

		datos[0] = Integer.toString(this.getIdVehiculo());
		datos[1] = this.getNombre();
		datos[2] = this.getColor();
		datos[3] = Double.toString(this.getLongitud());
		datos[4] = Double.toString(this.getCantPasajeros());

		model.addRow(datos);

	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Avion.id = id;
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

	@Override
	public String toString() {
		return "\n--- Avion ---" + "\nlongitud=" + longitud + "\ncantPasajeros=" + cantPasajeros + "\nidVehiculo="
				+ idVehiculo + "\nnombre=" + nombre + "\ncolor=" + color;
	}

}
