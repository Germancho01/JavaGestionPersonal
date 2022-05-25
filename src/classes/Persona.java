package classes;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exceptions.CellNoSelectedException;
import exceptions.FieldNoCompletedException;

public class Persona {

	public String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fechaNacimiento;
	public static int id;
	public int idPersona;

	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	private ArrayList<Barco> barcos = new ArrayList<Barco>();
	private ArrayList<Avion> aviones = new ArrayList<Avion>();

	public Persona(Integer idPersona, String nombre, String apellido, String dptoResidencia, Byte cantHijos,
			LocalDate fechaNacimiento, ArrayList<Vehiculo> vehiculos, ArrayList<Barco> barcos,
			ArrayList<Avion> aviones) {
		super();

		id++;
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dptoResidencia = dptoResidencia;
		this.cantHijos = cantHijos;
		this.fechaNacimiento = fechaNacimiento;
		this.vehiculos = vehiculos;
		this.barcos = barcos;
		this.aviones = aviones;
	}

	public Persona() {
		super();
	}

	// --------------- Método Cargar Persona --------------------

	public void cargarPersona(DefaultTableModel model) {

		String[] datos = new String[6];

		datos[0] = Integer.toString(this.getIdPersona());
		datos[1] = this.getNombre();
		datos[2] = this.getApellido();
		datos[3] = this.getCantHijos().toString();
		datos[4] = this.getDptoResidencia();
		datos[5] = this.getFechaNacimiento().toString();

		model.addRow(datos);
	}

	// --------------- Método Modificar Datos --------------------

	public void modificarDatos(JTable table, int fila) throws FieldNoCompletedException {

		int columna = table.getSelectedColumn();

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");

			if (nuevoNombre.isEmpty()) {
				throw new FieldNoCompletedException();
			}

			if (nuevoNombre != null) {

				// cambia el valor de la persona en el arrayList personas
				this.setNombre(nuevoNombre);

				// setea el nuevo valor en la tabla, en la fila y columna seleccionadas
				table.setValueAt(nuevoNombre, fila, 1);
			}

		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");

			if (nuevoApellido.isEmpty()) {
				throw new FieldNoCompletedException();
			}

			if (nuevoApellido != null) {
				this.setApellido(nuevoApellido);
				table.setValueAt(nuevoApellido, fila, 2);
			}

		} else if (columna == 3) {
			try {
				String nuevoCantHijosString = JOptionPane.showInputDialog("Ingrese cantidad de hijos: ");

				if (nuevoCantHijosString != null) {
					Byte nuevoCantHijos = Byte.parseByte(nuevoCantHijosString);
					this.setCantHijos(nuevoCantHijos);
					table.setValueAt(nuevoCantHijos, fila, 3);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");

			if (nuevoDptoResidencia.isEmpty()) {
				throw new FieldNoCompletedException();
			}

			if (nuevoDptoResidencia != null) {
				this.setDptoResidencia(nuevoDptoResidencia);
				table.setValueAt(nuevoDptoResidencia, fila, 4);
			}

		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			try {
				String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");

				if (nuevoDateString != null) {
					// transforma el String ingresado en un tipo LocalDate
					LocalDate nuevoDate = LocalDate.parse(nuevoDateString);
					this.setFechaNacimiento(nuevoDate);
					table.setValueAt(nuevoDate, fila, 5);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}
	}

	@Override
	public String toString() {
		return "---Persona--- " + "\nID Persona: " + idPersona + "\nDpto de Residencia: " + dptoResidencia
				+ "\nCantidad de Hijos: " + cantHijos + "\nFecha de Nacimiento: " + fechaNacimiento + "\n"
				+ this.getVehiculos().toString();
	}

	public String getNombre() {
		return nombre;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Persona.id = id;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDptoResidencia() {
		return dptoResidencia;
	}

	public void setDptoResidencia(String dptoResidencia) {
		this.dptoResidencia = dptoResidencia;
	}

	public Byte getCantHijos() {
		return cantHijos;
	}

	public void setCantHijos(Byte cantHijos) {
		this.cantHijos = cantHijos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public ArrayList<Barco> getBarcos() {
		return barcos;
	}

	public void setBarcos(ArrayList<Barco> barcos) {
		this.barcos = barcos;
	}

	public ArrayList<Avion> getAviones() {
		return aviones;
	}

	public void setAviones(ArrayList<Avion> aviones) {
		this.aviones = aviones;
	}

}
