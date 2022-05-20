package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Listado extends JFrame {

	/*
	 * 
	 * Declare variables.
	 * 
	 */

	private String[] datos = new String[6];

	// --- Frame ---
	private JFrame frame;

	// --- Table ---
	private JTable table;
	private DefaultTableModel model;

	// --- Button ----
	private JButton btnAgregarVehculo;
	private JButton btnListarVehculo;

	// --- Check Box ----
	private JCheckBox chckbxConHijos;
	private JCheckBox chckbxMayoresDeEdad;
	private JButton btnVolver;

	/**
	 * 
	 * Create the application.
	 * 
	 */
	public Listado(ArrayList<Persona> personas) {

		initialize(personas);

		limpiarTabla();

		// Cargar todas las personas en la tabla
		for (int i = 0; i < personas.size(); i++) {
			cargarPersona(personas, i);
		}

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */

	private void initialize(ArrayList<Persona> personas) {
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

		// action doble click del mouse
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (e.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, personas.get(fila).toString());
				}
			}
		});

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
					eliminarPersona(personas);
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
				personas.clear(); // limpia el arrayList de personas
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
					modificarDatos(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(459, 41, 89, 23);
		btnModificar.setFocusable(false);
		frame.getContentPane().add(btnModificar);

		// --------------- Boton Agregar Vehiculos --------------------

		btnAgregarVehculo = new JButton("Agregar Veh\u00EDculo");
		btnAgregarVehculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				new FormularioVehiculo(personas, fila);
			}
		});
		btnAgregarVehculo.setFocusable(false);
		btnAgregarVehculo.setBounds(192, 41, 148, 23);
		frame.getContentPane().add(btnAgregarVehculo);

		// --------------- Boton Listar Vehiculos --------------------

		btnListarVehculo = new JButton("Listar Veh\u00EDculo");
		btnListarVehculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listarVehiculo(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnListarVehculo.setFocusable(false);
		btnListarVehculo.setBounds(39, 41, 130, 23);
		frame.getContentPane().add(btnListarVehculo);

		// --------------- CheckBox Mayores de Edad --------------------

		chckbxMayoresDeEdad = new JCheckBox("Personas mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxMayoresDeEdad.setBounds(360, 400, 199, 23);
		chckbxMayoresDeEdad.setFocusable(false);
		frame.getContentPane().add(chckbxMayoresDeEdad);

		// --------------- CheckBox Solo con Hijos --------------------

		chckbxConHijos = new JCheckBox("Personas con hijos");
		chckbxConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxConHijos.setBounds(360, 436, 199, 23);
		chckbxConHijos.setFocusable(false);
		frame.getContentPane().add(chckbxConHijos);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Formulario(personas);
				frame.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);
		btnVolver.setBounds(444, 549, 115, 23);
		frame.getContentPane().add(btnVolver);
	}

	/**
	 * 
	 * Create methods.
	 * 
	 */

	// --------------- Método Listar Vehiculos --------------------

	public void listarVehiculo(ArrayList<Persona> personas) throws CellNoSelectedException{

		int fila = table.getSelectedRow();
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		new ListadoVehiculo(personas.get(fila).getVehiculos(), personas.get(fila).getBarcos(),
				personas.get(fila).getAviones(), personas);
		frame.setVisible(false);
	}
	
	// --------------- Método Cargar Persona --------------------

		public void cargarPersona(ArrayList<Persona> personas, int i) {

			datos[0] = Integer.toString(personas.get(i).getIdPersona());
			datos[1] = personas.get(i).getNombre();
			datos[2] = personas.get(i).getApellido();
			datos[3] = personas.get(i).getCantHijos().toString();
			datos[4] = personas.get(i).getDptoResidencia();
			datos[5] = personas.get(i).getFechaNacimiento().toString();
			model.addRow(datos);
		}

	// --------------- Método Modificar Dato --------------------

	public void modificarDatos(ArrayList<Persona> personas) throws CellNoSelectedException {

		int fila = table.getSelectedRow(); // obtener fila seleccionada
		int columna = table.getSelectedColumn(); // obtener columna seleccionada

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			personas.get(fila).setNombre(nuevoNombre); // cambia el valor de la persona en el arrayList personas
			table.setValueAt(nuevoNombre, fila, 1); // setea el nuevo valor en la tabla, en la fila y columna
													// seleccionadas
		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");
			personas.get(fila).setApellido(nuevoApellido);
			table.setValueAt(nuevoApellido, fila, 2);
		} else if (columna == 3) {
			try {
				Byte nuevoCantHijos = Byte.parseByte(JOptionPane.showInputDialog("Ingrese cantidad de hijos: "));
				personas.get(fila).setCantHijos(nuevoCantHijos);
				table.setValueAt(nuevoCantHijos, fila, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");
			personas.get(fila).setDptoResidencia(nuevoDptoResidencia);
			table.setValueAt(nuevoDptoResidencia, fila, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			try {
				String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");
				LocalDate nuevoDate = LocalDate.parse(nuevoDateString); // transforma el String ingresado en un tipo
																		// LocalDate
				personas.get(fila).setFechaNacimiento(nuevoDate);
				table.setValueAt(nuevoDate, fila, 5);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}
	}

	// --------------- Método Eliminar Persona Seleccionada --------------------

	public void eliminarPersona(ArrayList<Persona> personas) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		model.removeRow(fila); // elimina fila de la tabla
		personas.remove(fila); // elima persona en el arrayList
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
	public void filtros(ArrayList<Persona> personas) {
		limpiarTabla();

		if (chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (int i = 0; i < personas.size(); i++) {
				boolean esMayor = personas.get(i).getFechaNacimiento().isBefore(hoy.plusYears(-18));
				boolean tieneHijos = personas.get(i).getCantHijos() > 0;

				if (esMayor && tieneHijos) {
					cargarPersona(personas, i);
				}
			}

		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (int i = 0; i < personas.size(); i++) {

				if (personas.get(i).getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
					cargarPersona(personas, i);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {
			for (int i = 0; i < personas.size(); i++) {

				if (personas.get(i).getCantHijos() > 0) {
					cargarPersona(personas, i);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {
			for (int i = 0; i < personas.size(); i++) {
				cargarPersona(personas, i);
			}
		}
	}

}
