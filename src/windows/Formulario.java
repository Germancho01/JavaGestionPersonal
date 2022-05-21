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
import swing.PanelAviones;
import swing.PanelBarcos;
import swing.PanelPersonas;
import swing.PanelVehiculos;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

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

	// --- Button
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnEliminarTodo;

	// --- Scroll Pane, Table & List
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JList list;

	/*
	 * --- Panel Vehículos
	 */

	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	private ArrayList<Barco> barcos = new ArrayList<Barco>();
	private ArrayList<Avion> aviones = new ArrayList<Avion>();

	private String nombreVehiculo, color;
	private Integer cantPasajeros;
	private Integer idVehiculo;
	private double manga, eslora, longitud;

	private JTextField textFieldNombreVehiculo;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;
	private JComboBox comboBoxTipo;

	/*
	 * --- Panel Barcos
	 */

	JTable tableBarcos;
	JScrollPane scrollPaneBarcos;

	/*
	 * --- Panel Aviones
	 */

	JTable tableAviones;
	JScrollPane scrollPaneAviones;

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
		frmFormulario.setBounds(0, 0, 677, 700); // tamaño del frame
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esconder ventana al apretar cerrar
		frmFormulario.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmFormulario.setVisible(true); // hacer visible el frame
		frmFormulario.setResizable(false);
		frmFormulario.getContentPane().setLayout(null);

		// --------------- Panel --------------------

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(26, 11, 613, 588);
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
		btnAgregar.setBounds(490, 184, 89, 23);
		panelPersonas.add(btnAgregar);

		// Agregar función al botón
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ingresar datos con las excepciones de text fields no completados
				try {
					ingresarDatos(personas);

					limpiarTabla();

					for (int i = 0; i < personas.size(); i++) {
						cargarPersona(personas, i);
					}

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
					modificarDatos(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(26, 184, 89, 23);
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
		btnEliminar.setBounds(125, 184, 89, 23);
		panelPersonas.add(btnEliminar);
		btnEliminar.setFocusable(false);

		// --------------- Botón Eliminar Todo --------------------

		btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				personas.clear(); // limpia el arrayList de personas
			}
		});
		btnEliminarTodo.setBounds(224, 184, 116, 23);
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

		// --------------- Tabla --------------------

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 218, 553, 200);
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

		limpiarTabla();

		// --------------- Lista --------------------

		list = new JList();
		list.setBounds(26, 436, 553, 89);
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
		lblNombre.setBounds(34, 32, 46, 14);
		panelVehiculos.add(lblNombreVehiculo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(34, 82, 46, 14);
		panelVehiculos.add(lblColor);

		JLabel lblAtributo1 = new JLabel("Cantidad de pasajeros");
		lblAtributo1.setBounds(34, 132, 145, 14);
		panelVehiculos.add(lblAtributo1);

		JLabel lblAtributo2 = new JLabel("Longitud");
		lblAtributo2.setBounds(34, 182, 145, 14);
		panelVehiculos.add(lblAtributo2);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(280, 82, 30, 14);
		panelVehiculos.add(lblTipo);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(280, 32, 64, 14);
		panelVehiculos.add(lblPropietario);

		// --------------- TexFields --------------------

		textFieldNombreVehiculo = new JTextField();
		textFieldNombre.setBounds(90, 30, 140, 20);
		panelVehiculos.add(textFieldNombreVehiculo);
		textFieldNombre.setColumns(10);

		textFieldColor = new JTextField();
		textFieldColor.setBounds(90, 79, 140, 20);
		textFieldColor.setColumns(10);
		panelVehiculos.add(textFieldColor);

		textFieldAtributo1 = new JTextField();
		textFieldAtributo1.setBounds(184, 129, 46, 20);
		textFieldAtributo1.setColumns(10);
		panelVehiculos.add(textFieldAtributo1);

		textFieldAtributo2 = new JTextField();
		textFieldAtributo2.setBounds(184, 179, 46, 20);
		textFieldAtributo2.setColumns(10);
		panelVehiculos.add(textFieldAtributo2);

		// --------------- ComboBox Tipo --------------------

		comboBoxTipo = new JComboBox();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTipo.getSelectedIndex() == 0) {
					lblAtributo1.setText("Eslora: ");
					lblAtributo2.setText("Manga: ");
				}
				if (comboBoxTipo.getSelectedIndex() == 1) {
					lblAtributo1.setText("Cantidad de Pasajeros: ");
					lblAtributo2.setText("Longitud: ");
				} else {

				}
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "Barco", "Avi\u00F3n" }));
		comboBoxTipo.setSelectedIndex(0);
		comboBoxTipo.setBounds(396, 78, 86, 22);
		panelVehiculos.add(comboBoxTipo);

		// --------------- ComboBox Propietario --------------------

		String[] personasString = new String[personas.size()];

		for (int i = 0; i < personas.size(); i++) {
			personasString[i] = personas.get(i).getNombre() + personas.get(i).getApellido();
		}

		JComboBox<String> comboBoxPropietario = new JComboBox<String>();
		comboBoxPropietario.setModel(new DefaultComboBoxModel<String>(personasString));
		comboBoxPropietario.setBounds(396, 28, 168, 22);
		panelVehiculos.add(comboBoxPropietario);

		// --------------- Botón Agregar --------------------

		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingresarDatos(personas, comboBoxPropietario.getSelectedIndex());
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.");
				}

			}
		});
		btnAgregar_1.setBounds(54, 207, 89, 23);
		btnAgregar_1.setBounds(141, 231, 89, 23);
		btnAgregar_1.setFocusable(false);
		panelVehiculos.add(btnAgregar_1);

		// --------------- Botón Modificar --------------------

		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBounds(30, 524, 89, 23);
		btnModificar_1.setFocusable(false);
		panelVehiculos.add(btnModificar_1);

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setBounds(129, 524, 89, 23);
		btnEliminar_1.setFocusable(false);
		panelVehiculos.add(btnEliminar_1);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.setBounds(228, 524, 116, 23);
		btnEliminarTodo_1.setFocusable(false);
		panelVehiculos.add(btnEliminarTodo_1);

		// --------------- Panel 1 --------------------

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(26, 278, 553, 238);
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
		tableBarcos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));

		scrollPaneBarcos.setViewportView(tableBarcos);

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
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPaneAviones.setViewportView(tableAviones);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(546, 610, 89, 23);
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
	 * Métodos Panel Personas
	 * 
	 */

	// --------------- Método Ingresar Datos --------------------

	public void ingresarDatos(ArrayList<Persona> personas) throws FieldNoCompletedException {

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
		personas.add(new Persona(idPersona, nombre, apellido, dptoResidencia, cantHijos, fecNacimiento, vehiculos,
				barcos, aviones));

		// resetear los componentes
		resetearCampos();
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

			if (nuevoNombre != null) {
				personas.get(fila).setNombre(nuevoNombre); // cambia el valor de la persona en el arrayList personas
				table.setValueAt(nuevoNombre, fila, 1); // setea el nuevo valor en la tabla, en la fila y columna
														// seleccionadas
			}

		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");

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

	/*
	 * 
	 * Métodos Panel Vehículos
	 * 
	 */

	// ----------- Método Ingresar Datos ----------

	private void ingresarDatos(ArrayList<Persona> personas, int i) throws FieldNoCompletedException {
		nombre = textFieldNombre.getText();
		color = textFieldColor.getText();
		// idVehiculo = Vehiculo.getId();

		if (nombre.isEmpty() || color.isEmpty()) {
			throw new FieldNoCompletedException();
		}

		if (comboBoxTipo.getSelectedIndex() == 0) {
			eslora = Double.parseDouble(textFieldAtributo1.getText());
			manga = Double.parseDouble(textFieldAtributo2.getText());
			idVehiculo = Barco.getId();

			Barco barco = new Barco(idVehiculo, nombre, color, personas.get(i), eslora, manga);
			personas.get(i).getBarcos().add(barco);
			personas.get(i).getVehiculos().add(barco);

		} else if (comboBoxTipo.getSelectedIndex() == 1) {
			cantPasajeros = Integer.parseInt(textFieldAtributo1.getText());
			longitud = Double.parseDouble(textFieldAtributo2.getText());
			idVehiculo = Avion.getId();

			Avion avion = new Avion(idVehiculo, nombre, color, null, longitud, cantPasajeros);
			personas.get(i).getAviones().add(avion);
			personas.get(i).getVehiculos().add(avion);
		}

		vaciarCampos();
	}

	// ----------- Método Vaciar Campos ----------
	private void vaciarCampos() {
		textFieldAtributo1.setText("");
		textFieldAtributo2.setText("");
		textFieldColor.setText("");
		textFieldNombre.setText("");

	}

}
