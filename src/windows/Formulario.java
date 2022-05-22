package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import exceptions.FieldNoCompletedException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import java.awt.SystemColor;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument.BranchElement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JCheckBox;

public class Formulario extends JFrame {

	/*
	 * --- Panel Personas
	 */

	private String[] datos = new String[6];

	private String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fecNacimiento;
	private Integer anio, mes, dia;
	private Integer idPersona;

	// ---TextFields
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDptoResidencia;
	private JTextField textFieldCantHijos;

	// ---ComboBox
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;

	// ---CheckBox
	private JCheckBox chckbxMayoresDeEdad;
	private JCheckBox chckbxConHijos;

	// --- Button
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnEliminarTodo;

	// --- Scroll Pane, Table & List
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JList<String> list;

	/*
	 * --- Panel Vehículos
	 */

	private String[] datosVehiculos = new String[6];

	private JTabbedPane tabbedPane_1;

	private String nombreVehiculo, color;
	private Integer cantPasajeros;
	private Integer idVehiculo;
	private double manga, eslora, longitud;

	private JTextField textFieldNombreVehiculo;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;

	private JComboBox<String> comboBoxTipo;
	private JComboBox<String> comboBoxPropietario;
	private JComboBox<String> comboBoxPropietario_1;

	/*
	 * --- Panel Barcos
	 */

	private JTable tableBarcos;
	private JScrollPane scrollPaneBarcos;
	private DefaultTableModel modelBarcos;

	/*
	 * --- Panel Aviones
	 */

	private JTable tableAviones;
	private JScrollPane scrollPaneAviones;
	private DefaultTableModel modelAviones;

	/**
	 * 
	 * Create the application.
	 * 
	 */
	public Formulario(ArrayList<Persona> personas) {
		initialize(personas);
	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize(ArrayList<Persona> personas) {

		// --------------- Frame --------------------

		JFrame frmFormulario = new JFrame(); // inicializar el frame
		frmFormulario.setTitle("Formulario"); // poner título al frame
		// poner icono en el frame
		frmFormulario.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/images/logoAzulPerson.png")));
		frmFormulario.getContentPane().setForeground(SystemColor.textHighlight); // color del JPane
		frmFormulario.setBounds(0, 0, 677, 740); // tamaño del frame
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esconder ventana al apretar cerrar
		frmFormulario.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmFormulario.setVisible(true); // hacer visible el frame
		frmFormulario.setResizable(false);
		frmFormulario.getContentPane().setLayout(null);

		// --------------- Panel --------------------

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(26, 11, 613, 645);
		frmFormulario.getContentPane().add(tabbedPane);

		/*
		 * 
		 * 
		 * Panel Personas
		 * 
		 * 
		 * 
		 */

		JPanel panelPersonas = new JPanel();
		panelPersonas.setBounds(26, 11, 613, 588);
		panelPersonas.setLayout(null);
		tabbedPane.addTab("Personas", null, panelPersonas, null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 32, 46, 14);
		panelPersonas.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(34, 82, 46, 14);
		panelPersonas.add(lblApellido);

		JLabel lblCantHijos = new JLabel("Cantidad Hijos");
		lblCantHijos.setBounds(260, 32, 98, 14);
		panelPersonas.add(lblCantHijos);

		JLabel lblDptoResidencia = new JLabel("Dpto Residencia");
		lblDptoResidencia.setBounds(260, 81, 98, 14);
		panelPersonas.add(lblDptoResidencia);

		JLabel lblFecNacimiento = new JLabel("Fecha de nacimiento");
		lblFecNacimiento.setBounds(261, 127, 124, 14);
		panelPersonas.add(lblFecNacimiento);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(90, 30, 140, 20);
		panelPersonas.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(90, 79, 139, 20);
		panelPersonas.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldCantHijos = new JTextField();
		textFieldCantHijos.setBounds(396, 29, 168, 20);
		panelPersonas.add(textFieldCantHijos);
		textFieldCantHijos.setColumns(10);

		textFieldDptoResidencia = new JTextField();
		textFieldDptoResidencia.setBounds(396, 78, 168, 20);
		panelPersonas.add(textFieldDptoResidencia);
		textFieldDptoResidencia.setColumns(10);

		// --------------- Botón Agregar --------------------

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(475, 184, 89, 23);
		panelPersonas.add(btnAgregar);

		// Agregar función al botón
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ingresar datos con las excepciones de text fields no completados
				try {
					Persona persona = ingresarPersona(personas);

					cargarPersona(persona);

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.");
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR!\nAlgo ha salido mal.");
				}
			}
		});
		btnAgregar.setFocusable(false);

		// --------------- Botón Modificar --------------------

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarPersona(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(26, 227, 89, 23);
		panelPersonas.add(btnModificar);
		btnModificar.setFocusable(false);

		// --------------- Botón Eliminar --------------------

		btnEliminar = new JButton("Eliminar");
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
		btnEliminar.setBounds(125, 227, 89, 23);
		panelPersonas.add(btnEliminar);
		btnEliminar.setFocusable(false);

		// --------------- Botón Eliminar Todo --------------------

		btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(0);
				limpiarTabla(1);
				limpiarTabla(2);
				personas.clear(); // limpia el arrayList de personas

				actualizarPropietarios(personas);

			}
		});
		btnEliminarTodo.setBounds(224, 227, 116, 23);
		panelPersonas.add(btnEliminarTodo);
		btnEliminarTodo.setFocusable(false);

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}

		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setBounds(396, 123, 46, 22);
		panelPersonas.add(comboBoxDia);
		comboBoxDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el dato seleccionado en el comboBoxDia
				// el valor se almacena en la variable entera "dia", despues de pasar el dato
				// tipo objeto a String y luego a Integer.
				dia = Integer.parseInt(comboBoxDia.getSelectedItem().toString());
			}
		});
		comboBoxDia.setModel(new DefaultComboBoxModel<String>(dias));
		comboBoxDia.setSelectedIndex(0);
		comboBoxDia.setFocusable(false);

		// --------------- ComboBox Mes --------------------

		String[] meses = new String[12];
		for (int i = 0; i < meses.length; i++) {
			meses[i] = Integer.toString(i + 1);
		}

		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setBounds(452, 123, 46, 22);
		panelPersonas.add(comboBoxMes);
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mes = Integer.parseInt(comboBoxMes.getSelectedItem().toString());
			}
		});
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(meses));
		comboBoxMes.setSelectedIndex(0);
		comboBoxMes.setFocusable(false);

		// --------------- ComboBox Año --------------------

		String[] anios = new String[100];
		for (int i = 0; i < anios.length; i++) {
			anios[i] = Integer.toString(2022 - i);
		}

		comboBoxAnio = new JComboBox<String>();
		comboBoxAnio.setBounds(508, 123, 56, 22);
		panelPersonas.add(comboBoxAnio);
		comboBoxAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anio = Integer.parseInt(comboBoxAnio.getSelectedItem().toString());
			}
		});
		comboBoxAnio.setModel(new DefaultComboBoxModel<String>(anios));
		comboBoxAnio.setSelectedIndex(0);
		comboBoxAnio.setFocusable(false);

		// --------------- CheckBox Personas Mayores de edad --------------------

		chckbxMayoresDeEdad = new JCheckBox("Mostrar solo personas mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxMayoresDeEdad.setBounds(26, 468, 276, 23);
		chckbxMayoresDeEdad.setSelected(false);
		panelPersonas.add(chckbxMayoresDeEdad);

		// --------------- CheckBox Personas con hijos --------------------

		chckbxConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxConHijos.setBounds(304, 468, 275, 23);
		chckbxConHijos.setSelected(false);
		panelPersonas.add(chckbxConHijos);

		// --------------- Tabla --------------------

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 261, 553, 200);
		panelPersonas.add(scrollPane);

		table = new JTable();
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

		limpiarTabla(0);

		for (Persona persona : personas) {
			cargarPersona(persona);
		}

		// --------------- Lista --------------------

		list = new JList();
		list.setBounds(26, 517, 553, 78);
		panelPersonas.add(list);

		/*
		 * 
		 * 
		 * Panel Vehículos
		 * 
		 * 
		 */

		JPanel panelVehiculos = new JPanel();
		panelVehiculos.setBounds(26, 11, 613, 588);
		panelVehiculos.setLayout(null);
		tabbedPane.addTab("Vehículos", null, panelVehiculos, null);

		// --------------- Labels --------------------

		JLabel lblNombreVehiculo = new JLabel("Nombre");
		lblNombreVehiculo.setBounds(34, 32, 46, 14);
		panelVehiculos.add(lblNombreVehiculo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(280, 32, 46, 14);
		panelVehiculos.add(lblColor);

		JLabel lblAtributo1 = new JLabel("Cantidad de pasajeros");
		lblAtributo1.setBounds(34, 82, 145, 14);
		panelVehiculos.add(lblAtributo1);

		JLabel lblAtributo2 = new JLabel("Longitud");
		lblAtributo2.setBounds(34, 132, 145, 14);
		panelVehiculos.add(lblAtributo2);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(280, 132, 30, 14);
		panelVehiculos.add(lblTipo);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(280, 82, 64, 14);
		panelVehiculos.add(lblPropietario);

		// --------------- TexFields --------------------

		textFieldNombreVehiculo = new JTextField();
		textFieldNombreVehiculo.setBounds(90, 30, 140, 20);
		panelVehiculos.add(textFieldNombreVehiculo);
		textFieldNombre.setColumns(10);

		textFieldColor = new JTextField();
		textFieldColor.setBounds(396, 29, 168, 20);
		textFieldColor.setColumns(10);
		panelVehiculos.add(textFieldColor);

		textFieldAtributo1 = new JTextField();
		textFieldAtributo1.setBounds(184, 79, 46, 20);
		textFieldAtributo1.setColumns(10);
		panelVehiculos.add(textFieldAtributo1);

		textFieldAtributo2 = new JTextField();
		textFieldAtributo2.setBounds(184, 129, 46, 20);
		textFieldAtributo2.setColumns(10);
		panelVehiculos.add(textFieldAtributo2);

		// --------------- ComboBox Tipo --------------------

		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTipo.getSelectedIndex() == 0) {
					lblAtributo1.setText("Eslora ");
					lblAtributo2.setText("Manga ");
				}
				if (comboBoxTipo.getSelectedIndex() == 1) {
					lblAtributo1.setText("Cantidad de Pasajeros ");
					lblAtributo2.setText("Longitud ");
				}
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Barco", "Avi\u00F3n" }));
		comboBoxTipo.setSelectedIndex(0);
		comboBoxTipo.setFocusable(false);
		comboBoxTipo.setBounds(396, 128, 168, 22);
		panelVehiculos.add(comboBoxTipo);

		// --------------- ComboBox Propietario --------------------

		comboBoxPropietario = new JComboBox<String>();
		comboBoxPropietario.setBounds(396, 78, 168, 22);
		comboBoxPropietario.setFocusable(false);
		panelVehiculos.add(comboBoxPropietario);

		comboBoxPropietario_1 = new JComboBox<String>();
		comboBoxPropietario_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtroVehiculos(personas);
			}
		});
		comboBoxPropietario_1.setBounds(27, 548, 190, 22);
		comboBoxPropietario_1.setFocusable(false);
		panelVehiculos.add(comboBoxPropietario_1);

		actualizarPropietarios(personas);

		// --------------- Botón Agregar --------------------

		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingresarVehiculo(personas, comboBoxPropietario.getSelectedIndex());
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el propietario del vehículo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAgregar_1.setBounds(475, 184, 89, 23);
		btnAgregar_1.setFocusable(false);
		panelVehiculos.add(btnAgregar_1);

		// --------------- Botón Modificar --------------------

		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBounds(27, 506, 89, 23);
		btnModificar_1.setFocusable(false);
		panelVehiculos.add(btnModificar_1);

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnEliminar_1.setBounds(126, 506, 89, 23);
		btnEliminar_1.setFocusable(false);
		panelVehiculos.add(btnEliminar_1);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.setBounds(460, 506, 116, 23);
		btnEliminarTodo_1.setFocusable(false);
		panelVehiculos.add(btnEliminarTodo_1);

		// --------------- Panel 1 --------------------

		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(23, 219, 553, 279);
		panelVehiculos.add(tabbedPane_1);

		/*
		 * 
		 * 
		 * Panel Barcos
		 * 
		 * 
		 */

		JPanel panelBarcos = new JPanel();
		panelBarcos.setBounds(26, 278, 553, 238);
		panelBarcos.setLayout(null);
		tabbedPane_1.addTab("Barcos", null, panelBarcos, null);

		scrollPaneBarcos = new JScrollPane();
		scrollPaneBarcos.setBounds(0, 0, 553, 238);
		panelBarcos.add(scrollPaneBarcos);

		tableBarcos = new JTable();

		tableBarcos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// int fila = tableBarcos.getSelectedRow();
				if (e.getClickCount() == 2) {

				}
			}
		});

		tableBarcos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "ID", "Nombre", "Color", "Eslora", "Manga" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tableBarcos.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableBarcos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableBarcos.getColumnModel().getColumn(3).setPreferredWidth(78);
		tableBarcos.getColumnModel().getColumn(4).setPreferredWidth(76);

		scrollPaneBarcos.setViewportView(tableBarcos);

		modelBarcos = (DefaultTableModel) tableBarcos.getModel();

		limpiarTabla(1);

		/*
		 * 
		 * 
		 * Panel Aviones
		 * 
		 * 
		 */

		JPanel panelAviones = new JPanel();
		panelAviones.setBounds(26, 278, 553, 238);
		panelAviones.setLayout(null);
		tabbedPane_1.addTab("Aviones", null, panelAviones, null);

		scrollPaneAviones = new JScrollPane();
		scrollPaneAviones.setBounds(0, 0, 553, 238);
		panelAviones.add(scrollPaneAviones);

		tableAviones = new JTable();
		tableAviones.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "ID", "Nombre", "Color", "Longitud", "Cantidad Pasajeros" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tableAviones.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableAviones.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableAviones.getColumnModel().getColumn(3).setPreferredWidth(105);
		tableAviones.getColumnModel().getColumn(4).setPreferredWidth(140);

		scrollPaneAviones.setViewportView(tableAviones);

		modelAviones = (DefaultTableModel) tableAviones.getModel();

		limpiarTabla(2);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(550, 667, 89, 23);
		frmFormulario.getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inicio(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);

	}

	/*
	 * 
	 * Métodos
	 * 
	 */

	// --------------- Método Limpiar Tabla --------------------

	public void limpiarTabla(int tabla) {
		if (tabla == 0) {

			int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
			for (int i = filas - 1; i >= 0; i--) {
				model.removeRow(i); // eliminar fila de la tabal en i
			}

		} else if (tabla == 1) {

			int filas = tableBarcos.getRowCount();
			for (int i = filas - 1; i >= 0; i--) {
				modelBarcos.removeRow(i);
			}

		} else if (tabla == 2) {
			int filas = tableAviones.getRowCount();
			for (int i = filas - 1; i >= 0; i--) {
				modelAviones.removeRow(i);
			}
		}

	}

	/*
	 * 
	 * Métodos Panel Personas
	 * 
	 */

	// --------------- Método Ingresar Persona --------------------

	public Persona ingresarPersona(ArrayList<Persona> personas) throws FieldNoCompletedException {

		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		ArrayList<Barco> barcos = new ArrayList<Barco>();
		ArrayList<Avion> aviones = new ArrayList<Avion>();

		// extraer datos de los textField y almacenarlo en variables
		nombre = textFieldNombre.getText();
		apellido = textFieldApellido.getText();
		dptoResidencia = textFieldDptoResidencia.getText();
		cantHijos = Byte.parseByte(textFieldCantHijos.getText()); // se transforma el dato de tipo String a Byte
		idPersona = Persona.getId();

		fecNacimiento = LocalDate.of(anio, mes, dia); // se genera una fecha LocalDate a partir de los datos ingresados

		boolean estaVacio = nombre.isEmpty() || apellido.isEmpty() || dptoResidencia.isEmpty();

		if (estaVacio) {
			throw new FieldNoCompletedException();
		}

		// crear instancia de persona y la almacena en un arrayList
		Persona persona = new Persona(idPersona, nombre, apellido, dptoResidencia, cantHijos, fecNacimiento, vehiculos,
				barcos, aviones);

		personas.add(persona);

		// resetear los componentes
		resetearCampos();
		actualizarPropietarios(personas);

		return persona;
	}

	// --------------- Método Resetear Campos --------------------

	public void resetearCampos() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCantHijos.setText("");
		textFieldDptoResidencia.setText("");
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAnio.setSelectedIndex(0);
	}

	// --------------- Método Cargar Persona --------------------

	public void cargarPersona(Persona persona) {

		datos[0] = Integer.toString(persona.getIdPersona());
		datos[1] = persona.getNombre();
		datos[2] = persona.getApellido();
		datos[3] = persona.getCantHijos().toString();
		datos[4] = persona.getDptoResidencia();
		datos[5] = persona.getFechaNacimiento().toString();
		model.addRow(datos);
	}

	// --------------- Método Modificar Dato --------------------

	public void modificarPersona(ArrayList<Persona> personas)
			throws CellNoSelectedException, FieldNoCompletedException {

		int fila = table.getSelectedRow(); // obtener fila seleccionada
		int columna = table.getSelectedColumn(); // obtener columna seleccionada

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");

			if (nuevoNombre.isEmpty()) {
				throw new FieldNoCompletedException();
			}

			if (nuevoNombre != null) {

				// cambia el valor de la persona en el arrayList personas
				personas.get(fila).setNombre(nuevoNombre);

				// setea el nuevo valor en la tabla, en la fila y columna seleccionadas
				table.setValueAt(nuevoNombre, fila, 1);
			}

		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");

			if (nuevoApellido.isEmpty()) {
				throw new FieldNoCompletedException();
			}

			if (nuevoApellido != null) {
				personas.get(fila).setApellido(nuevoApellido);
				table.setValueAt(nuevoApellido, fila, 2);
			}

		} else if (columna == 3) {
			try {
				String nuevoCantHijosString = JOptionPane.showInputDialog("Ingrese cantidad de hijos: ");

				if (nuevoCantHijosString != null) {
					Byte nuevoCantHijos = Byte.parseByte(nuevoCantHijosString);
					personas.get(fila).setCantHijos(nuevoCantHijos);
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
				personas.get(fila).setDptoResidencia(nuevoDptoResidencia);
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
					personas.get(fila).setFechaNacimiento(nuevoDate);
					table.setValueAt(nuevoDate, fila, 5);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}

		actualizarPropietarios(personas);
	}

	// --------------- Método Eliminar Persona Seleccionada --------------------

	public void eliminarPersona(ArrayList<Persona> personas) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		model.removeRow(fila); // elimina fila de la tabla
		personas.remove(fila); // elima persona en el arrayList

		actualizarPropietarios(personas);

		for (Persona persona : personas) {
			for (Barco barco : persona.getBarcos()) {
				cargarVehiculo(barco);
			}
		}

		for (Persona persona : personas) {
			for (Avion avion : persona.getAviones()) {
				cargarVehiculo(avion);
			}
		}

	}

	// --------------- Método Filtros --------------------

	// filtros de los checkBox
	public void filtros(ArrayList<Persona> personas) {
		limpiarTabla(0);

		if (chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (Persona persona : personas) {
				boolean esMayor = persona.getFechaNacimiento().isBefore(hoy.plusYears(-18));
				boolean tieneHijos = persona.getCantHijos() > 0;

				if (esMayor && tieneHijos) {
					cargarPersona(persona);
				}
			}

		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (Persona persona : personas) {

				if (persona.getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
					cargarPersona(persona);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {

			for (Persona persona : personas) {
				if (persona.getCantHijos() > 0) {
					cargarPersona(persona);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {

			for (Persona persona : personas) {
				cargarPersona(persona);
			}
		}
	}

	/*
	 * 
	 * Métodos Panel Vehículos
	 * 
	 */

	// ----------- Método Ingresar Vehiculo ----------

	private void ingresarVehiculo(ArrayList<Persona> personas, int i) throws FieldNoCompletedException {
		nombreVehiculo = textFieldNombreVehiculo.getText();
		color = textFieldColor.getText();

		if (nombreVehiculo.isEmpty() || color.isEmpty()) {
			throw new FieldNoCompletedException();
		}

		// Si el comboBox tipo de vehiculo es un barco:
		if (comboBoxTipo.getSelectedIndex() == 0) {
			eslora = Double.parseDouble(textFieldAtributo1.getText());
			manga = Double.parseDouble(textFieldAtributo2.getText());
			idVehiculo = Barco.getId();

			Barco barco = new Barco(idVehiculo, nombreVehiculo, color, personas.get(i), eslora, manga);
			personas.get(i).getBarcos().add(barco);
			personas.get(i).getVehiculos().add(barco);

			cargarVehiculo(barco);

			// Si el comboBox tipo de vehiculo es un avion:

		} else if (comboBoxTipo.getSelectedIndex() == 1) {
			cantPasajeros = Integer.parseInt(textFieldAtributo1.getText());
			longitud = Double.parseDouble(textFieldAtributo2.getText());
			idVehiculo = Avion.getId();

			Avion avion = new Avion(idVehiculo, nombreVehiculo, color, null, longitud, cantPasajeros);
			personas.get(i).getAviones().add(avion);
			personas.get(i).getVehiculos().add(avion);

			cargarVehiculo(avion);
		}

		vaciarCampos();
	}

	// --------------- Método Cargar Barcos --------------------

	public void cargarVehiculo(Barco barco) {

		datosVehiculos[0] = Integer.toString(barco.getIdVehiculo());
		datosVehiculos[1] = barco.getNombre();
		datosVehiculos[2] = barco.getColor();
		datosVehiculos[3] = Double.toString(barco.getEslora());
		datosVehiculos[4] = Double.toString(barco.getManga());

		modelBarcos.addRow(datosVehiculos);
	}

	// --------------- Método Cargar Aviones --------------------

	public void cargarVehiculo(Avion avion) {

		datosVehiculos[0] = Integer.toString(avion.getIdVehiculo());
		datosVehiculos[1] = avion.getNombre();
		datosVehiculos[2] = avion.getColor();
		datosVehiculos[3] = Double.toString(avion.getLongitud());
		datosVehiculos[4] = Double.toString(avion.getCantPasajeros());

		modelAviones.addRow(datosVehiculos);
	}

	// --------------- Método Eliminar Vehiculo --------------------

	public void eliminarVehiculo(ArrayList<Persona> personas) throws CellNoSelectedException {

		if (tabbedPane_1.getSelectedIndex() == 0) {

			int fila = tableBarcos.getSelectedRow();
			int propitario = comboBoxPropietario_1.getSelectedIndex();

			if (fila < 0) {
				throw new CellNoSelectedException();
			}

			modelBarcos.removeRow(fila); // elimina fila de la tabla
			personas.get(propitario).getBarcos().remove(fila);
		}

	}

	// ----------- Método Vaciar Campos ----------

	private void vaciarCampos() {
		textFieldAtributo1.setText("");
		textFieldAtributo2.setText("");
		textFieldColor.setText("");
		textFieldNombreVehiculo.setText("");

	}

	// ----------- Método Actualizar Propietarios ----------

	public void actualizarPropietarios(ArrayList<Persona> personas) {

		String[] propietarios = new String[personas.size()];
		String[] propietarios1 = new String[personas.size() + 1];
		propietarios1[0] = " ";

		for (int i = 0; i < personas.size(); i++) {
			propietarios[i] = personas.get(i).getNombre() + " " + personas.get(i).getApellido();
			propietarios1[i + 1] = personas.get(i).getNombre() + " " + personas.get(i).getApellido();
		}

		comboBoxPropietario.setModel(new DefaultComboBoxModel<String>(propietarios));
		comboBoxPropietario_1.setModel(new DefaultComboBoxModel<String>(propietarios1));
	}

	// ----------- Método Filtro Vehiculos ----------

	public void filtroVehiculos(ArrayList<Persona> personas) {
		int i = comboBoxPropietario_1.getSelectedIndex();

		limpiarTabla(1);
		limpiarTabla(2);

		if (i == 0) {

			for (Persona persona : personas) {
				for (Barco barco : persona.getBarcos()) {
					cargarVehiculo(barco);
				}

				for (Avion avion : persona.getAviones()) {
					cargarVehiculo(avion);
				}
			}

		} else {

			for (Barco barco : personas.get(i - 1).getBarcos()) {
				cargarVehiculo(barco);
			}

			for (Avion avion : personas.get(i - 1).getAviones()) {
				cargarVehiculo(avion);
			}
		}
	}
}
