package classes;

import javax.swing.JOptionPane;
import javax.swing.JTable;
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

	public void modificarDatos(JTable table) {

		int columna = table.getSelectedColumn();

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			if (nuevoNombre != null) {
				this.setNombre(nuevoNombre);
			}
		} else if (columna == 2) {
			String nuevoColor = JOptionPane.showInputDialog("Ingrese nuevo color: ");
			if (nuevoColor != null) {
				this.setColor(nuevoColor);
			}
		} else if (columna == 3) {
			try {
				String nuevoEsloraString = JOptionPane.showInputDialog("Ingrese nueva longitud: ");
				if (nuevoEsloraString != null) {
					this.setLongitud(Double.parseDouble(nuevoEsloraString));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			try {
				String nuevoCantPasajerosString = JOptionPane.showInputDialog("Ingrese nueva Cantidad de Pasajeros: ");
				if (nuevoCantPasajerosString != null) {
					this.setCantPasajeros(Integer.parseInt(nuevoCantPasajerosString));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}
		}
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
