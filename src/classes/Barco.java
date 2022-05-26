package classes;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Barco extends Vehiculo {
	public double eslora, manga;
	public static int id;

	public Barco(Integer idVehiculo, String nombre, String color, Persona duenio, double eslora, double manga) {
		super(idVehiculo, nombre, color, duenio);

		id++;
		this.eslora = eslora;
		this.manga = manga;
	}

	// --------------- Método Cargar Barco --------------------

	public void cargarBarco(DefaultTableModel model) {

		String[] datos = new String[5];
		datos[0] = Integer.toString(this.getIdVehiculo());
		datos[1] = this.getNombre();
		datos[2] = this.getColor();
		datos[3] = Double.toString(this.getEslora());
		datos[4] = Double.toString(this.getManga());

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
				String nuevoEsloraString = JOptionPane.showInputDialog("Ingrese nuevo eslora: ");
				if (nuevoEsloraString != null) {
					this.setEslora(Double.parseDouble(nuevoEsloraString));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			try {
				String nuevoMangaString = JOptionPane.showInputDialog("Ingrese nuevo manga: ");
				if (nuevoMangaString != null) {
					this.setManga(Double.parseDouble(nuevoMangaString));
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
