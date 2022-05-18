package windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Persona;
import exceptions.CellNoSelectedException;
import exceptions.ItemNoSelectedException;

public class Listado {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	Formulario formulario = new Formulario(0);
	HashMap<Integer, Persona> mapaPersonas = formulario.getMapaPersonas();
	private String[] datos;
	private JCheckBox chckbxSoloConHijos;
	private JCheckBox chckbxMayoresDeEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listado window = new Listado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Listado() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarPersonaSeleccionada();
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar.setBounds(169, 178, 89, 23);
		btnEliminar.setFocusable(false);
		frame.getContentPane().add(btnEliminar);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTodo();
				mapaPersonas.clear();
				; // limpia el arrayList de personas
			}
		});
		btnEliminarTodo.setBounds(306, 178, 115, 23);
		btnEliminarTodo.setFocusable(false);
		frame.getContentPane().add(btnEliminarTodo);

		// --------------- Botón Modificar --------------------

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatos();
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(470, 178, 89, 23);
		btnModificar.setFocusable(false);
		frame.getContentPane().add(btnModificar);

		// --------------- CheckBox Mayores de Edad --------------------

		chckbxMayoresDeEdad = new JCheckBox("Mostrar solo mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros();
			}
		});
		chckbxMayoresDeEdad.setBounds(29, 535, 229, 23);
		chckbxMayoresDeEdad.setFocusable(false);
		frame.getContentPane().add(chckbxMayoresDeEdad);

		// --------------- CheckBox Solo con Hijos --------------------

		chckbxSoloConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxSoloConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros();
			}
		});
		chckbxSoloConHijos.setBounds(283, 535, 229, 23);
		chckbxSoloConHijos.setFocusable(false);
		frame.getContentPane().add(chckbxSoloConHijos);

		// --------------- Table --------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 227, 530, 280); // setea las coordenadas de la tabla y su tamaño
		frame.getContentPane().add(scrollPane); // agrega el scroll pane al panel

		table = new JTable(); // nueva tabla

		// se define el modelo por defecto de la tabla,
		// se agrega nombre a las columnas y preferencias de la tabla
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "ID", "Nombre", "Apellido", "Cantidad Hijos", "Dpto Residencia", "Fecha Nacimiento" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		// ancho por defecto de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);

		scrollPane.setViewportView(table); // se agrega la tabla al scroll pane

		model = (DefaultTableModel) table.getModel(); // obtener el modelo de la tabla para poder añadir datos a la
														// misma

		// limpiar la tabla
		// eliminarTodo();
	}

	// --------------- Método Modificar Datos --------------------

	public void modificarDatos() throws CellNoSelectedException {
		// obtener celda seleccionada
		int fila = table.getSelectedRow();

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int columna = table.getSelectedColumn(); // obtener columna seleccionada

		// obtener ID de la persona a partir de la tabla y luego obtener la posición de
		// esa persona
		// en el arrayList de las IDs
		int idTabla = Integer.parseInt((String) table.getValueAt(fila, 0));

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			mapaPersonas.get(idTabla).setNombre(nuevoNombre); // cambia el valor de la persona en el arrayList personas
			table.setValueAt(nuevoNombre, fila, 1); // setea el nuevo valor en la tabla, en la fila y columna
													// seleccionadas
		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");
			mapaPersonas.get(idTabla).setNombre(nuevoApellido);
			table.setValueAt(nuevoApellido, fila, 2);
		} else if (columna == 3) {
			try {
				Byte nuevoCantHijos = Byte.parseByte(JOptionPane.showInputDialog("Ingrese cantidad de hijos: "));
				mapaPersonas.get(idTabla).setCantHijos(nuevoCantHijos);
				table.setValueAt(nuevoCantHijos, fila, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");
			mapaPersonas.get(idTabla).setNombre(nuevoDptoResidencia);
			table.setValueAt(nuevoDptoResidencia, fila, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			try {
				String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");
				LocalDate nuevoDate = LocalDate.parse(nuevoDateString); // transforma el String ingresado en un tipo
																		// LocalDate
				mapaPersonas.get(idTabla).setFechaNacimiento(nuevoDate);
				table.setValueAt(nuevoDate, fila, 5);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}
	}

	// --------------- Método Eliminar Persona Seleccionada --------------------

	public void eliminarPersonaSeleccionada() throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int idTabla = Integer.parseInt((String) table.getValueAt(fila, 0));
		mapaPersonas.get(idTabla);

		model.removeRow(fila); // elimina fila de la tabla
		mapaPersonas.remove(idTabla); // elimina persona en el arrayList personas
	}

	// --------------- Método Eliminar Todo --------------------

	public void eliminarTodo() {
		int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i); // eliminar fila de la tabal en i
		}
	}

	// --------------- Método Cargar Todas Las Personas --------------------

	public void cargarTodasLasPersonas() {
		// limpiar la tabla
		eliminarTodo();

		// recorrer la lista de personas e irlas agregando a la tabla

		// for mapas entrySet
		for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
			datos[0] = Integer.toString(entry.getKey());
			datos[1] = entry.getValue().getNombre();
			datos[2] = entry.getValue().getApellido();
			datos[3] = entry.getValue().getCantHijos().toString();
			datos[4] = entry.getValue().getDptoResidencia();
			datos[5] = entry.getValue().getFechaNacimiento().toString();
			model.addRow(datos);
		}
	}

	// --------------- Método Cargar Solo Personas Con Hijos --------------------

	public void cargarSoloPersonasConHijos() {
		eliminarTodo();

		for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {

			if (entry.getValue().getCantHijos() > 0) {
				datos[0] = Integer.toString(entry.getKey());
				datos[1] = entry.getValue().getNombre();
				datos[2] = entry.getValue().getApellido();
				datos[3] = entry.getValue().getCantHijos().toString();
				datos[4] = entry.getValue().getDptoResidencia();
				datos[5] = entry.getValue().getFechaNacimiento().toString();
				model.addRow(datos);
			}
		}
	}

	// --------------- Método Cargar Solo Mayores de Edad --------------------

	public void cargarSoloMayoresDeEdad() {
		eliminarTodo();
		LocalDate hoy = LocalDate.now();

		for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {

			if (entry.getValue().getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
				datos[0] = Integer.toString(entry.getKey());
				datos[1] = entry.getValue().getNombre();
				datos[2] = entry.getValue().getApellido();
				datos[3] = entry.getValue().getCantHijos().toString();
				datos[4] = entry.getValue().getDptoResidencia();
				datos[5] = entry.getValue().getFechaNacimiento().toString();
				model.addRow(datos);
			}

		}
	}

	// --------------- Método Cargar Personas Mayores de Edad Con Hijos
	// --------------------

	public void cargarPersonasMayoresDeEdadConHijos() {
		eliminarTodo();
		LocalDate hoy = LocalDate.now();

		for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
			boolean esMayor = entry.getValue().getFechaNacimiento().isBefore(hoy.plusYears(-18));
			boolean tieneHijos = entry.getValue().getCantHijos() > 0;

			if (esMayor && tieneHijos) {
				datos[0] = Integer.toString(entry.getKey());
				datos[1] = entry.getValue().getNombre();
				datos[2] = entry.getValue().getApellido();
				datos[3] = entry.getValue().getCantHijos().toString();
				datos[4] = entry.getValue().getDptoResidencia();
				datos[5] = entry.getValue().getFechaNacimiento().toString();
				model.addRow(datos);
			}
		}

	}

	// --------------- Método Filtros --------------------

	// filtros de los checkBox
	public void filtros() {
		if (chckbxMayoresDeEdad.isSelected() && chckbxSoloConHijos.isSelected()) {
			cargarPersonasMayoresDeEdadConHijos();
		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxSoloConHijos.isSelected()) {
			cargarSoloMayoresDeEdad();
		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxSoloConHijos.isSelected()) {
			cargarTodasLasPersonas();
		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxSoloConHijos.isSelected()) {
			cargarSoloPersonasConHijos();
		}
	}

}
