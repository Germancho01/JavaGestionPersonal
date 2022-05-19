package windows;

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

public class Listado extends JFrame{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String[] datos = new String[6];
	private JCheckBox chckbxConHijos;
	private JCheckBox chckbxMayoresDeEdad;

	/**
	 * Create the application.
	 */
	public Listado(HashMap<Integer, Persona> mapaPersonas) {

		initialize(mapaPersonas);

		limpiarTabla();

		// Cargar todas las personas en la tabla
		for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
			int id = entry.getKey();
			cargarPersona(mapaPersonas, id);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashMap<Integer, Persona> mapaPersonas) {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// --------------- Table --------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 75, 530, 280); // setea las coordenadas de la tabla y su tamaño
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

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarPersona(mapaPersonas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar.setBounds(360, 41, 89, 23);
		btnEliminar.setFocusable(false);
		frame.getContentPane().add(btnEliminar);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				mapaPersonas.clear(); // limpia el mapa de personas
			}
		});
		btnEliminarTodo.setBounds(39, 366, 115, 23);
		btnEliminarTodo.setFocusable(false);
		frame.getContentPane().add(btnEliminarTodo);

		// --------------- Botón Modificar --------------------

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatos(mapaPersonas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(459, 41, 89, 23);
		btnModificar.setFocusable(false);
		frame.getContentPane().add(btnModificar);

		// --------------- CheckBox Mayores de Edad --------------------

		chckbxMayoresDeEdad = new JCheckBox("Mostrar solo mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(mapaPersonas);
			}
		});
		chckbxMayoresDeEdad.setBounds(29, 535, 229, 23);
		chckbxMayoresDeEdad.setFocusable(false);
		frame.getContentPane().add(chckbxMayoresDeEdad);

		// --------------- CheckBox Solo con Hijos --------------------

		chckbxConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(mapaPersonas);
			}
		});
		chckbxConHijos.setBounds(283, 535, 229, 23);
		chckbxConHijos.setFocusable(false);
		frame.getContentPane().add(chckbxConHijos);

	}

	/**
	 * Create methods.
	 */
	
	// --------------- Método Cargar Persona --------------------

	public void cargarPersona(HashMap<Integer, Persona> mapaPersonas, int id) {

		datos[0] = Integer.toString(id);
		datos[1] = mapaPersonas.get(id).getNombre();
		datos[2] = mapaPersonas.get(id).getApellido();
		datos[3] = mapaPersonas.get(id).getCantHijos().toString();
		datos[4] = mapaPersonas.get(id).getDptoResidencia();
		datos[5] = mapaPersonas.get(id).getFechaNacimiento().toString();
		model.addRow(datos);
	}

	// --------------- Método Modificar Dato --------------------

	public void modificarDatos(HashMap<Integer, Persona> mapaPersonas) throws CellNoSelectedException {
		// obtener celda seleccionada
		int fila = table.getSelectedRow();

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int columna = table.getSelectedColumn(); // obtener columna seleccionada

		// obtener ID de la persona a partir de la tabla
		int id = Integer.parseInt((String) table.getValueAt(fila, 0));

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			mapaPersonas.get(id).setNombre(nuevoNombre); // cambia el valor de la persona en el mapa personas
			table.setValueAt(nuevoNombre, fila, 1); // setea el nuevo valor en la tabla, en la fila y columna
													// seleccionadas
		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");
			mapaPersonas.get(id).setNombre(nuevoApellido);
			table.setValueAt(nuevoApellido, fila, 2);
		} else if (columna == 3) {
			try {
				Byte nuevoCantHijos = Byte.parseByte(JOptionPane.showInputDialog("Ingrese cantidad de hijos: "));
				mapaPersonas.get(id).setCantHijos(nuevoCantHijos);
				table.setValueAt(nuevoCantHijos, fila, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");
			mapaPersonas.get(id).setNombre(nuevoDptoResidencia);
			table.setValueAt(nuevoDptoResidencia, fila, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			try {
				String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");
				LocalDate nuevoDate = LocalDate.parse(nuevoDateString); // transforma el String ingresado en un tipo
																		// LocalDate
				mapaPersonas.get(id).setFechaNacimiento(nuevoDate);
				table.setValueAt(nuevoDate, fila, 5);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}
	}

	// --------------- Método Eliminar Persona Seleccionada --------------------

	public void eliminarPersona(HashMap<Integer, Persona> mapaPersonas) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int id = Integer.parseInt((String) table.getValueAt(fila, 0));

		model.removeRow(fila); // elimina fila de la tabla
		mapaPersonas.remove(id); // elimina persona en el mapa personas
	}

	// --------------- Método Limpiar Tabla --------------------

	public void limpiarTabla() {
		int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i); // eliminar fila de la tabal en i
		}
	}

	// --------------- Método Filtros --------------------

	// filtros de los checkBox
	public void filtros(HashMap<Integer, Persona> mapaPersonas) {
		limpiarTabla();
		
		if (chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {
			
			LocalDate hoy = LocalDate.now();

			for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
				int id = entry.getKey();
				
				boolean esMayor = mapaPersonas.get(id).getFechaNacimiento().isBefore(hoy.plusYears(-18));
				boolean tieneHijos = mapaPersonas.get(id).getCantHijos() > 0;

				if (esMayor && tieneHijos) {
					cargarPersona(mapaPersonas, id);
				}
			}
		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {

			LocalDate hoy = LocalDate.now();

			for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
				int id = entry.getKey();

				if (mapaPersonas.get(id).getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
					cargarPersona(mapaPersonas, id);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {

			for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
				int id = entry.getKey();

				if (mapaPersonas.get(id).getCantHijos() > 0) {
					cargarPersona(mapaPersonas, id);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {
			for (Entry<Integer, Persona> entry : mapaPersonas.entrySet()) {
				int id = entry.getKey();
				cargarPersona(mapaPersonas, id);
			}
		}
	}

}
