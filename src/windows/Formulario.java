package windows;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import classes.Avion;
import classes.Barco;
import classes.ListaPersonas;
import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import exceptions.FieldNoCompletedException;
import exceptions.ItemNoSelectedException;

public class Formulario extends JFrame {

	/*
	 * --- Panel Personas
	 */

	private String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fecNacimiento;
	private Integer anio, mes, dia;
	private Integer idPersona;

	// ---TextFields
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCantHijos;

	// ---ComboBox
	private JComboBox<String> comboBoxDptoResidencia;
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

	/*
	 * --- Panel Vehículos
	 */

	ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	ArrayList<Barco> barcos = new ArrayList<Barco>();
	ArrayList<Avion> aviones = new ArrayList<Avion>();

	private JTabbedPane tabbedPaneTablas;

	private String nombreVehiculo, color;
	private Integer cantPasajeros;
	private Integer idVehiculo;
	private double manga, eslora, longitud;

	// ---TextFields
	private JTextField textFieldNombreVehiculo;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;

	// ---ComboBox
	private JComboBox<String> comboBoxTipo;
	private JComboBox<String> comboBoxPropietario;
	private JComboBox<String> comboBoxPropietario_1;

	// ---Table
	private JTable tableVehiculos;
	private JScrollPane scrollPaneVehiculos;
	private DefaultTableModel modelVehiculos;

	/*
	 * --- Tabla Barcos
	 */

	private JTable tableBarcos;
	private JScrollPane scrollPaneBarcos;
	private DefaultTableModel modelBarcos;

	/*
	 * --- Tabla Aviones
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

		JFrame frmFormulario = new JFrame();
		frmFormulario.getContentPane().setBackground(SystemColor.window);
		frmFormulario.setTitle("Formulario");
		frmFormulario.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/images/logoAzulPerson.png")));
		frmFormulario.getContentPane().setForeground(SystemColor.textHighlight);
		frmFormulario.setBounds(0, 0, 677, 740);
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFormulario.setLocationRelativeTo(null);
		frmFormulario.setVisible(true);
		frmFormulario.setResizable(false);
		frmFormulario.getContentPane().setLayout(null);

		// --------------- Panel con perstañas principal--------------------

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(26, 11, 613, 614);
		tabbedPane.setFocusable(false);
		frmFormulario.getContentPane().add(tabbedPane);

		/*
		 * 
		 * 
		 * Panel Personas (pestaña de personas)
		 * 
		 * 
		 * 
		 */

		JPanel panelPersonas = new JPanel();
		panelPersonas.setBackground(SystemColor.window);
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

		// --------------- Botón Agregar --------------------

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(475, 184, 89, 23);
		panelPersonas.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ingresarPersona(personas);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Algo ha salido mal.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAgregar.setFocusable(false);

		// --------------- Botón Modificar --------------------

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modificarPersona(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
				try {
					eliminarPersona(personas);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
				// Se limpian todas las tablas y el arrayList personas.
				// Se actualizan los propietarios en los comboBox para coincidir con las
				// personas en la lista.

				limpiarTabla(0);
				limpiarTabla(1);
				limpiarTabla(2);
				personas.clear();

				actualizarPropietarios(personas);

			}
		});
		btnEliminarTodo.setBounds(224, 227, 116, 23);
		panelPersonas.add(btnEliminarTodo);
		btnEliminarTodo.setFocusable(false);

		// --------------- ComboBox Dpto Residencia --------------------

		comboBoxDptoResidencia = new JComboBox<String>();
		comboBoxDptoResidencia.setModel(
				new DefaultComboBoxModel(new String[] { "Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno",
						"Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysand\u00FA", "Rio Negro",
						"Rivera", "Rocha", "Salto", "San Jos\u00E9", "Soriano", "Tacuaremb\u00F3", "Treinta y Tres" }));
		comboBoxDptoResidencia.setSelectedIndex(0);
		comboBoxDptoResidencia.setFocusable(false);
		comboBoxDptoResidencia.setBounds(396, 78, 168, 22);
		panelPersonas.add(comboBoxDptoResidencia);

		// --------------- ComboBox Dia --------------------

		// Cargar array con los días del mes para ser ingresados en el comboBox
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}

		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setBounds(396, 123, 46, 22);
		panelPersonas.add(comboBoxDia);
		comboBoxDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Obtiene el dato seleccionado en el comboBox y lo almcaena en una variable
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
		chckbxMayoresDeEdad.setBackground(SystemColor.window);
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxMayoresDeEdad.setBounds(26, 552, 276, 23);
		chckbxMayoresDeEdad.setSelected(false);
		chckbxMayoresDeEdad.setFocusable(false);
		panelPersonas.add(chckbxMayoresDeEdad);

		// --------------- CheckBox Personas con hijos --------------------

		chckbxConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxConHijos.setBackground(SystemColor.window);
		chckbxConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros(personas);
			}
		});
		chckbxConHijos.setBounds(304, 552, 275, 23);
		chckbxConHijos.setSelected(false);
		chckbxConHijos.setFocusable(false);
		panelPersonas.add(chckbxConHijos);

		// --------------- Tabla --------------------

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 261, 553, 284);
		panelPersonas.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// Al dar doble click sobre un registro
					// la pestaña se cambia a la de vehículos
					// los comboBox filtran los vehículos por la persona seleccionada

					int fila = table.getSelectedRow();
					tabbedPane.setSelectedIndex(1);
					comboBoxPropietario_1.setSelectedIndex(fila + 1);
					comboBoxPropietario.setSelectedIndex(fila);
				}

			}
		});
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

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);

		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();

		limpiarTabla(0);

		// Se cargan todas las personas en la tabla
		for (Persona persona : personas) {
			persona.cargarPersona(model);
		}

		/*
		 * 
		 * 
		 * Panel Vehículos (pestaña de vehículos)
		 * 
		 * 
		 */

		JPanel panelVehiculos = new JPanel();
		panelVehiculos.setBackground(SystemColor.window);
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
				// Cambia los label de atributo según el tipo de vehículo ingresado

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
		comboBoxPropietario_1.setBounds(23, 506, 190, 22);
		comboBoxPropietario_1.setFocusable(false);
		panelVehiculos.add(comboBoxPropietario_1);

		actualizarPropietarios(personas);

		// --------------- Botón Agregar --------------------

		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingresarVehiculo(personas);
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
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama a diferentes métodos según la tabla en la que se encuentre el usuario.
				// En la tabla de vehículos no es posible modificar datos.

				if (tabbedPaneTablas.getSelectedIndex() == 1) {
					try {
						modificarBarco(personas);
					} catch (CellNoSelectedException | ItemNoSelectedException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else if (tabbedPaneTablas.getSelectedIndex() == 2) {
					try {
						modificarAvion(personas);
					} catch (CellNoSelectedException | ItemNoSelectedException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnModificar_1.setBounds(255, 506, 89, 23);
		btnModificar_1.setFocusable(false);
		panelVehiculos.add(btnModificar_1);

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama al método correspondiete dependiendo en la tabla que se encuentre el
				// usuario.

				try {
					if (tabbedPaneTablas.getSelectedIndex() == 0) {
						eliminarVehiculo(personas);
					} else if (tabbedPaneTablas.getSelectedIndex() == 1) {
						eliminarBarco(personas);
					} else {
						eliminarAvion(personas);
					}

				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar_1.setBounds(361, 506, 89, 23);
		btnEliminar_1.setFocusable(false);
		panelVehiculos.add(btnEliminar_1);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTodoVehiculos(personas);
			}
		});
		btnEliminarTodo_1.setBounds(460, 506, 116, 23);
		btnEliminarTodo_1.setFocusable(false);
		panelVehiculos.add(btnEliminarTodo_1);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(26, 636, 89, 23);
		frmFormulario.getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inicio(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);

		// --------------- Botón Estadísticas --------------------

		JButton btnEstadsticas = new JButton("Estad\u00EDsticas");
		btnEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard(comboBoxDptoResidencia, personas);
			}
		});
		btnEstadsticas.setFocusable(false);
		btnEstadsticas.setBounds(531, 636, 108, 23);
		frmFormulario.getContentPane().add(btnEstadsticas);

		// --------------- Barra de menú --------------------

		JMenuBar menuBar = new JMenuBar();
		frmFormulario.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se despliega un menú para seleccionar archivos.
				// En caso de que un archivo sea seleccionado se obtiene la ruta del documento
				// Se llama a la clase lista de personas con la ruta del archivo
				// Se cargan todas las personas ingresadas en la lista.

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));
				int response = fileChooser.showOpenDialog(null);

				if (response == JFileChooser.APPROVE_OPTION) {

					String archivo = fileChooser.getSelectedFile().getAbsolutePath();

					ListaPersonas listaPersonas = null;
					try {
						listaPersonas = new ListaPersonas(archivo);

						for (Persona persona : listaPersonas.lista) {
							personas.add(persona);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No es posible ingresar sus datos.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

					filtros(personas);
					actualizarPropietarios(personas);
				}

			}
		});
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se despliega un menú para guardar archivos.
				// En caso de que se agrege un nombre de archivo y presione el botón aceptar
				// se obtiene la ruta del documento.

				// Se transforma el documento de texto al formato UTF-8
				// Se guardan todas las personas en un documento .txt

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("."));

				int response = fileChooser.showSaveDialog(null);

				if (response == JFileChooser.APPROVE_OPTION) {
					File file;
					PrintWriter fileOut = null;

					file = new File(fileChooser.getSelectedFile().getAbsolutePath());

					try {
						fileOut = new PrintWriter(file, "UTF-8");
						for (Persona persona : personas) {
							String datos = persona.getNombre() + "," + persona.getApellido() + ","
									+ persona.getDptoResidencia() + "," + persona.getCantHijos() + ","
									+ persona.getFechaNacimiento().toString();
							fileOut.println(datos);
						}

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} finally {
						fileOut.close();
					}
				}
			}
		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnApariencia = new JMenu("Apariencia");
		menuBar.add(mnApariencia);

		// Cambia el diseño de la interfaz utilizando la librería LookAndFeel

		JMenuItem mntmNormal = new JMenuItem("Normal");
		mntmNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					frmFormulario.setVisible(false);
					new Formulario(personas);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnApariencia.add(mntmNormal);

		JMenuItem mntmClasica = new JMenuItem("Cl\u00E1sica");
		mntmClasica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
					frmFormulario.setVisible(false);
					new Formulario(personas);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnApariencia.add(mntmClasica);

		JMenuItem mntmModerna = new JMenuItem("Moderna");
		mntmModerna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					frmFormulario.setVisible(false);
					new Formulario(personas);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnApariencia.add(mntmModerna);

		JMenuItem mntmNimbus = new JMenuItem("Nimbus");
		mntmNimbus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					frmFormulario.setVisible(false);
					new Formulario(personas);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}

			}
		});
		mnApariencia.add(mntmNimbus);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercade = new JMenuItem("Acerca de");
		mntmAcercade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Creado por el grupo TIX: \n\nAnna Benitez \nGermán Domínguez \nLeonardo Cutraro \nAgustina Martínez \nPedro Serna";
				JOptionPane.showMessageDialog(null, message, "v2.0", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAyuda.add(mntmAcercade);

		// --------------- Panel Tablas Vehiculos --------------------

		tabbedPaneTablas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneTablas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Si el usuario se encuentra en la pestaña vehículos se desactiva el botón
				// modificar

				if (tabbedPaneTablas.getSelectedIndex() == 0) {
					btnModificar_1.setEnabled(false);
				} else if (tabbedPaneTablas.getSelectedIndex() == 1) {
					btnModificar_1.setEnabled(true);
				} else if (tabbedPaneTablas.getSelectedIndex() == 2) {
					btnModificar_1.setEnabled(true);
				}

			}
		});
		tabbedPaneTablas.setBounds(23, 219, 553, 279);
		btnModificar_1.setEnabled(false);
		panelVehiculos.add(tabbedPaneTablas);

		/*
		 * 
		 * 
		 * Tabla Vehiculos
		 * 
		 * 
		 */

		JPanel panel = new JPanel();
		tabbedPaneTablas.addTab("Vehículos", null, panel, null);
		panel.setLayout(null);

		scrollPaneVehiculos = new JScrollPane();
		scrollPaneVehiculos.setBounds(0, 0, 548, 251);
		panel.add(scrollPaneVehiculos);

		tableVehiculos = new JTable();
		tableVehiculos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "ID", "Tipo", "Nombre", "Color" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVehiculos.getColumnModel().getColumn(0).setPreferredWidth(41);
		tableVehiculos.getColumnModel().getColumn(1).setPreferredWidth(109);
		tableVehiculos.getColumnModel().getColumn(2).setPreferredWidth(122);
		tableVehiculos.getColumnModel().getColumn(3).setPreferredWidth(111);

		scrollPaneVehiculos.setViewportView(tableVehiculos);

		modelVehiculos = (DefaultTableModel) tableVehiculos.getModel();

		limpiarTabla(1);

		/*
		 * 
		 * 
		 * Tabla Barcos
		 * 
		 * 
		 */

		JPanel panelBarcos = new JPanel();
		panelBarcos.setBounds(26, 278, 553, 238);
		panelBarcos.setLayout(null);
		tabbedPaneTablas.addTab("Barcos", null, panelBarcos, null);

		scrollPaneBarcos = new JScrollPane();
		scrollPaneBarcos.setBounds(0, 0, 553, 238);
		panelBarcos.add(scrollPaneBarcos);

		tableBarcos = new JTable();

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

		limpiarTabla(2);

		/*
		 * 
		 * 
		 * Tabla Aviones
		 * 
		 * 
		 */

		JPanel panelAviones = new JPanel();
		panelAviones.setBounds(26, 278, 553, 238);
		panelAviones.setLayout(null);
		tabbedPaneTablas.addTab("Aviones", null, panelAviones, null);

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

		limpiarTabla(3);

		filtroVehiculos(personas);
	}

	/*
	 * 
	 * Métodos (métodos generales)
	 * 
	 */

	/*
	 * --------------- Método Limpiar Tabla --------------------
	 * 
	 * Se borran todos los registros en la tabla dependiendo del argumento de
	 * entrada. Como parametro de entrada recibe un entero y no retorna nada.
	 */

	public void limpiarTabla(int tabla) {
		if (tabla == 0) {

			int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
			for (int i = filas - 1; i >= 0; i--) {
				model.removeRow(i); // eliminar fila de la tabal en i
			}

		} else if (tabla == 1) {

			int filas = tableVehiculos.getRowCount();
			for (int i = filas - 1; i >= 0; i--) {
				modelVehiculos.removeRow(i);
			}
		} else if (tabla == 2) {

			int filas = tableBarcos.getRowCount();
			for (int i = filas - 1; i >= 0; i--) {
				modelBarcos.removeRow(i);
			}

		} else if (tabla == 3) {
			int filas = tableAviones.getRowCount();
			for (int i = filas - 1; i >= 0; i--) {
				modelAviones.removeRow(i);
			}
		}

	}

	/*
	 * 
	 * Métodos Panel Personas (métodos usados en la pestaña de personas)
	 * 
	 */

	/*
	 * --------------- Método Ingresar Persona --------------------
	 * 
	 * Se crea una instancia de persona a partir de los datos ingresados en los
	 * campos en la pestaña personas. Esta instancia es almacenada en el arrayList
	 * personas. En caso de que no se rellenen todos los campos se lanza la
	 * excepción correspondiente. La persona ingresada se carga a la tabla, se
	 * resetean los campos y se actualizan los comboBox de propietario para que
	 * coincida con la lista. El método recibe como parámetro un arrayList de
	 * personas y no retorna nada.
	 */

	public void ingresarPersona(ArrayList<Persona> personas) throws FieldNoCompletedException {

		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		ArrayList<Barco> barcos = new ArrayList<Barco>();
		ArrayList<Avion> aviones = new ArrayList<Avion>();

		nombre = textFieldNombre.getText();
		apellido = textFieldApellido.getText();
		dptoResidencia = (String) comboBoxDptoResidencia.getSelectedItem();
		cantHijos = Byte.parseByte(textFieldCantHijos.getText());
		idPersona = Persona.getId();

		fecNacimiento = LocalDate.of(anio, mes, dia);

		boolean estaVacio = nombre.isEmpty() || apellido.isEmpty() || dptoResidencia.isEmpty();

		if (estaVacio) {
			throw new FieldNoCompletedException();
		}

		Persona persona = new Persona(idPersona, nombre, apellido, dptoResidencia, cantHijos, fecNacimiento, vehiculos,
				barcos, aviones);

		personas.add(persona);

		persona.cargarPersona(model);

		resetearCampos();
		actualizarPropietarios(personas);
	}

	/*
	 * --------------- Método Resetear Campos --------------------
	 * 
	 * Se resetean todos los campos y comboBox en el formulario. El método no tiene
	 * parámetros y no retorna nada.
	 */

	public void resetearCampos() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCantHijos.setText("");
		comboBoxDptoResidencia.setSelectedIndex(0);
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAnio.setSelectedIndex(0);
	}

	/*
	 * --------------- Método Modificar Datos Personas--------------------
	 * 
	 * Se modifican los datos de la persona dependiendo la fila seleccionada, en
	 * caso de que no se seleccione una fila se lanza la excepción correspondiente.
	 * Se actulizan los propietarios para que los datos coindidan con los listados
	 * en el arrayList de personas.
	 */

	public void modificarPersona(ArrayList<Persona> personas)
			throws CellNoSelectedException, FieldNoCompletedException {

		int fila = table.getSelectedRow();

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		personas.get(fila).modificarDatos(table, fila);

		actualizarPropietarios(personas);
	}

	/*
	 * --------------- Método Eliminar Persona Seleccionada --------------------
	 * 
	 * Se elimina la persona seleccionada de la tabla y el arrayList de personas. Se
	 * actualizan los propietarios y las tablas de vehículos para que coincidan con
	 * la lista de personas. En caso de que no se seleccione ninguna fila se lanza
	 * la excepción correspondiente. El método recibe un arrayList de personas y no
	 * retorna nada.
	 */

	public void eliminarPersona(ArrayList<Persona> personas) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		model.removeRow(fila); // elimina fila de la tabla
		personas.remove(fila); // elima persona en el arrayList

		actualizarPropietarios(personas);
		actualizarTablasVehiculos(personas);

	}

	/*
	 * --------------- Método Filtros de Personas --------------------
	 * 
	 * Se limpia las tablas, luego se filtran las personas dependiendo si son
	 * mayores de edad o si tienen hijos. Se realizan las diferentes combinaciones
	 * posibles entre los checkBox. Y luego se cargan las personas correspondientes
	 * en la tabla. El método recibe un arrayList de personas como parámetro y no
	 * retorna nada.
	 */

	// filtros de los checkBox
	public void filtros(ArrayList<Persona> personas) {
		limpiarTabla(0);

		if (chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (Persona persona : personas) {
				boolean esMayor = persona.getFechaNacimiento().isBefore(hoy.plusYears(-18));
				boolean tieneHijos = persona.getCantHijos() > 0;

				if (esMayor && tieneHijos) {
					persona.cargarPersona(model);
				}
			}

		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {
			LocalDate hoy = LocalDate.now();

			for (Persona persona : personas) {

				if (persona.getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
					persona.cargarPersona(model);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxConHijos.isSelected()) {

			for (Persona persona : personas) {
				if (persona.getCantHijos() > 0) {
					persona.cargarPersona(model);
				}
			}

		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxConHijos.isSelected()) {

			for (Persona persona : personas) {
				persona.cargarPersona(model);
			}
		}
	}

	/*
	 * 
	 * Métodos Panel Vehículos
	 * 
	 */

	/*
	 * ----------- Método Ingresar Vehiculo ----------
	 * 
	 * Se crean una instancia de vehículo a partir de los datos ingresados en los
	 * diferentes cambos del formulario en la pestaña vehículos. Dependiendo del
	 * tipo de vehículo que se seleccione será el constructor a llamar. Además, el
	 * vehículo es ingresado al array de vehículos, barcos y/o aviones de la persona
	 * correspondiente. Se actualizan las tablas de vehículos y se vacían los
	 * campos. En caso de que no se rellenen todos los campos se lanza la excepción
	 * correspondiente.
	 * 
	 * El método tiene como parámetro de entrada un arrayList de personas y no
	 * retorna ningún valor.
	 */

	private void ingresarVehiculo(ArrayList<Persona> personas) throws FieldNoCompletedException {
		int i = comboBoxPropietario.getSelectedIndex();

		nombreVehiculo = textFieldNombreVehiculo.getText();
		color = textFieldColor.getText();
		idVehiculo = Vehiculo.getId();

		if (nombreVehiculo.isEmpty() || color.isEmpty()) {
			throw new FieldNoCompletedException();
		}

		// Si el comboBox tipo de vehiculo es un barco:
		if (comboBoxTipo.getSelectedIndex() == 0) {
			eslora = Double.parseDouble(textFieldAtributo1.getText());
			manga = Double.parseDouble(textFieldAtributo2.getText());

			Barco barco = new Barco(idVehiculo, nombreVehiculo, color, personas.get(i), eslora, manga);

			personas.get(i).getBarcos().add(barco);
			personas.get(i).getVehiculos().add(barco);

			// Si el comboBox tipo de vehiculo es un avion:

		} else if (comboBoxTipo.getSelectedIndex() == 1) {
			cantPasajeros = Integer.parseInt(textFieldAtributo1.getText());
			longitud = Double.parseDouble(textFieldAtributo2.getText());

			Avion avion = new Avion(idVehiculo, nombreVehiculo, color, personas.get(i), longitud, cantPasajeros);

			personas.get(i).getAviones().add(avion);
			personas.get(i).getVehiculos().add(avion);
		}

		actualizarTablasVehiculos(personas);
		vaciarCampos();
	}

	/*
	 * --------------- Método Eliminar Todos los Vehiculo --------------------
	 * 
	 * En caso de que la tabla sea la de vehículos y no se haya filtrado por
	 * propietrario, se recorre la lista de personas y se borran todos los
	 * vehículos, aviones y barcos asociado a la misma. En el caso de que se haya
	 * filtrado por persona simplemente se borran los vehículos asociados a la
	 * misma.
	 * 
	 * En caso de que la tabla sea de aviones o barcos y no se filtre por personas
	 * se recorre la lista de personas, se recorren lso vehículos de la persona. En
	 * caso de que la instancia del vehículo sea de tipo barco o avión (según
	 * corresponda), se elimina el registro. En el caso de que se haya filtrado por
	 * persona simplemente se recorren los vehículos de la persona seleccioanda y en
	 * caso de que sea una instancia de tipo barco o avión (según corresponda) se
	 * elimina el registro. Por último se actualizan las listas y tablas para que se
	 * sincronicen todos los datos.
	 * 
	 * El método tiene como parámetro de entrada una lista de personas y no retorna
	 * ningún valor.
	 * 
	 */

	public void eliminarTodoVehiculos(ArrayList<Persona> personas) {
		int propietario = comboBoxPropietario_1.getSelectedIndex() - 1;

		if (tabbedPaneTablas.getSelectedIndex() == 0) {
			if (propietario == -1) {
				for (Persona persona : personas) {
					persona.getVehiculos().clear();
					persona.getBarcos().clear();
					persona.getAviones().clear();
				}
			} else {
				personas.get(propietario).getVehiculos().clear();
				personas.get(propietario).getBarcos().clear();
				personas.get(propietario).getAviones().clear();
			}

		} else if (tabbedPaneTablas.getSelectedIndex() == 1) {
			if (propietario == -1) {
				for (Persona persona : personas) {
					persona.getBarcos().clear();

					for (int i = persona.getVehiculos().size() - 1; i >= 0; i--) {
						if (persona.getVehiculos().get(i) instanceof Barco) {
							persona.getVehiculos().remove(i);
						}
					}

				}
			} else {
				personas.get(propietario).getBarcos().clear();

				for (int i = personas.get(propietario).getVehiculos().size() - 1; i >= 0; i--) {
					if (personas.get(propietario).getVehiculos().get(i) instanceof Barco) {
						personas.get(propietario).getVehiculos().remove(i);
					}
				}
			}
		} else {
			if (propietario == -1) {
				for (Persona persona : personas) {
					persona.getAviones().clear();

					for (int i = persona.getVehiculos().size() - 1; i >= 0; i--) {
						if (persona.getVehiculos().get(i) instanceof Avion) {
							persona.getVehiculos().remove(i);
						}
					}

				}
			} else {
				personas.get(propietario).getAviones().clear();

				for (int i = personas.get(propietario).getVehiculos().size() - 1; i >= 0; i--) {
					if (personas.get(propietario).getVehiculos().get(i) instanceof Avion) {
						personas.get(propietario).getVehiculos().remove(i);
					}
				}
			}
		}

		filtroVehiculos(personas);
	}

	/*
	 * --------------- Método Eliminar Vehiculo Seleccionado--------------------
	 * 
	 * Se obtiene la fila seleccionada de la tabla vehículos, en caso de que no se
	 * seleccione ninguna fila se lanza la excepción correspondiente. Se obtiene el
	 * propietario del vehículo y se recorren todos los vehículos del mismo hasta
	 * que la ID del vehículo sea igual a la ID de la tabla y se guarda su posición
	 * dentro de la lista de vehículos de la persona correspondiente.
	 * 
	 * Para eliminar el vehículo de la lista de barcos o aviones se realiza el paso
	 * anterior solo que se obtiene la posición dentro de la lista correspondiente.
	 * 
	 * Por último se elimina el vehículo en la lista de vehículos de esa persona y
	 * se actualizan las tablas para que se sincronicen todos los datos.
	 * 
	 * El método tiene como parámetro de entrada un arrayList de personas y no
	 * retorna ningún valor.
	 */

	public void eliminarVehiculo(ArrayList<Persona> personas) throws CellNoSelectedException {

		int fila = tableVehiculos.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int idTabla = Integer.parseInt((String) tableVehiculos.getValueAt(fila, 0));

		Persona persona = vehiculos.get(fila).getDuenio();

		int posicion = 0;
		for (int i = 0; i < persona.getVehiculos().size(); i++) {

			if (persona.getVehiculos().get(i).getIdVehiculo() == idTabla) {
				posicion = i;
				break;
			}
		}

		if (vehiculos.get(fila) instanceof Barco) {
			int posicionBarco = 0;
			for (int i = 0; i < persona.getBarcos().size(); i++) {

				if (persona.getBarcos().get(i).getIdVehiculo() == idTabla) {
					posicionBarco = i;
					break;
				}
			}
			persona.getBarcos().remove(posicionBarco);
		}

		if (vehiculos.get(fila) instanceof Avion) {
			int posicionAvion = 0;
			for (int i = 0; i < persona.getAviones().size(); i++) {

				if (persona.getAviones().get(i).getIdVehiculo() == idTabla) {
					posicionAvion = i;
					break;
				}
			}

			persona.getAviones().remove(posicionAvion);
		}

		persona.getVehiculos().remove(posicion);

		filtroVehiculos(personas);
	}

	/*
	 * --------------- Método Eliminar Barco Seleccioando--------------------
	 * 
	 * El método funciona igual que el método de eliminar un vehículo seleccionado
	 * solo que en este caso la tabla en la que se obtiene la fila es en la de
	 * barcos. Además no es necesario obtener el tipo de clase del vehículo ya que
	 * solo tenemos barcos.
	 */

	public void eliminarBarco(ArrayList<Persona> personas) throws CellNoSelectedException {

		int fila = tableBarcos.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int idTabla = Integer.parseInt((String) tableBarcos.getValueAt(fila, 0));

		Persona persona = barcos.get(fila).getDuenio();

		int posicion = 0;
		for (int i = 0; i < persona.getVehiculos().size(); i++) {

			if (persona.getVehiculos().get(i).getIdVehiculo() == idTabla) {
				posicion = i;
				break;
			}
		}

		int posicionBarco = 0;
		for (int i = 0; i < persona.getBarcos().size(); i++) {

			if (persona.getBarcos().get(i).getIdVehiculo() == idTabla) {
				posicionBarco = i;
				break;
			}
		}

		persona.getBarcos().remove(posicionBarco);

		persona.getVehiculos().remove(posicion);

		filtroVehiculos(personas);

	}

	/*
	 * --------------- Método Eliminar Avión Seleccionado--------------------
	 * 
	 * Este método funciona igual que el método para eliminar un barco seleccionada
	 * con la diferencia que la fila obtenida es de la tabla de aviones.
	 */

	public void eliminarAvion(ArrayList<Persona> personas) throws CellNoSelectedException {

		int fila = tableAviones.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int idTabla = Integer.parseInt((String) tableAviones.getValueAt(fila, 0));

		Persona persona = aviones.get(fila).getDuenio();

		int posicion = 0;
		for (int i = 0; i < persona.getVehiculos().size(); i++) {

			if (persona.getVehiculos().get(i).getIdVehiculo() == idTabla) {
				posicion = i;
				break;
			}
		}

		int posicionAvion = 0;
		for (int i = 0; i < persona.getAviones().size(); i++) {

			if (persona.getAviones().get(i).getIdVehiculo() == idTabla) {
				posicionAvion = i;
				break;
			}
		}

		persona.getAviones().remove(posicionAvion);

		persona.getVehiculos().remove(posicion);

		filtroVehiculos(personas);

	}

	/*
	 * ----------- Método Vaciar Campos ----------
	 * 
	 * Se vacían los campos del formulario en la pestaña de vehículos además se
	 * setea la posición inicial del comboBox propietarios.
	 */

	private void vaciarCampos() {
		textFieldAtributo1.setText("");
		textFieldAtributo2.setText("");
		textFieldColor.setText("");
		textFieldNombreVehiculo.setText("");
		comboBoxPropietario_1.setSelectedIndex(0);

	}

	/*
	 * ----------- Método Actualizar Propietarios en comboBox ----------
	 * 
	 * Se cargan los nombres y apellidos de las personas en los comboBox de la
	 * pestaña vehículos. En el caso del comboBox que filtra los propietarios se
	 * agregó este texto al mismo.
	 */

	public void actualizarPropietarios(ArrayList<Persona> personas) {

		String[] propietarios = new String[personas.size()];
		String[] propietarios1 = new String[personas.size() + 1];
		propietarios1[0] = "Filtrar por propietario";

		for (int i = 0; i < personas.size(); i++) {
			propietarios[i] = personas.get(i).getNombre() + " " + personas.get(i).getApellido();
			propietarios1[i + 1] = personas.get(i).getNombre() + " " + personas.get(i).getApellido();
		}

		comboBoxPropietario.setModel(new DefaultComboBoxModel<String>(propietarios));
		comboBoxPropietario_1.setModel(new DefaultComboBoxModel<String>(propietarios1));
	}

	/*
	 * ----------- Método Filtro Vehiculos ----------
	 * 
	 * Para filtrar los vehículos, en caso de que no se seleccione ningún
	 * propietario en el comboBox se cargan todos los vehículos en las tablas. En
	 * caso de que se seleccione una persona, se limpian las listas de vehículos,
	 * barcos y aviones (general) y se cargan con los vehículos de la persona
	 * seleccionada. Luego, se limpian las tablas y se cargan los vehículos de las
	 * listas generales.
	 */

	public void filtroVehiculos(ArrayList<Persona> personas) {
		int i = comboBoxPropietario_1.getSelectedIndex();

		if (i == 0) {
			actualizarTablasVehiculos(personas);

		} else {

			// actualizar lista

			vehiculos.clear();
			aviones.clear();
			barcos.clear();

			for (Vehiculo vehiculo : personas.get(i - 1).getVehiculos()) {
				vehiculos.add(vehiculo);
			}

			for (Avion avion : personas.get(i - 1).getAviones()) {
				aviones.add(avion);
			}

			for (Barco barco : personas.get(i - 1).getBarcos()) {
				barcos.add(barco);
			}

			// actualizar tabla

			limpiarTabla(1);
			limpiarTabla(2);
			limpiarTabla(3);

			for (Vehiculo vehiculo : personas.get(i - 1).getVehiculos()) {
				vehiculo.cargarVehiculo(modelVehiculos);
			}

			for (Barco barco : personas.get(i - 1).getBarcos()) {
				barco.cargarBarco(modelBarcos);
			}

			for (Avion avion : personas.get(i - 1).getAviones()) {
				avion.cargarAvion(modelAviones);
			}
		}
	}

	/*
	 * ----------- Método Actualizar Tablas de Vehiculos ----------
	 * 
	 * Para actualizar las tablas con los vehículos de todas las personas, primero
	 * se limpian las tablas. Se cargan las listas con los vehículos, se ordenan las
	 * mismas con respecto a las ID y por último se recorren las listas cargando los
	 * vehículos.
	 */

	public void actualizarTablasVehiculos(ArrayList<Persona> personas) {
		limpiarTabla(1);
		limpiarTabla(2);
		limpiarTabla(3);

		actualizarListaVehiculos(personas);

		sortVehiculos(vehiculos);

		for (Vehiculo vehiculo : vehiculos) {
			vehiculo.cargarVehiculo(modelVehiculos);
		}

		sortBarcos(barcos);

		for (Barco barco : barcos) {
			barco.cargarBarco(modelBarcos);
		}

		sortAviones(aviones);

		for (Avion avion : aviones) {
			avion.cargarAvion(modelAviones);
		}
	}

	/*
	 * ----------- Método Actualizar Listas de Vehiculos ----------
	 * 
	 * Para actualizar las listas de vehículos con los vehículos de todas las
	 * personas. Primero se deben limpiar las listas. Luego se recorren las listas
	 * de vehículos de cada persona agregando las instancias del mismo a las listas
	 * generales.
	 */

	public void actualizarListaVehiculos(ArrayList<Persona> personas) {

		vehiculos.clear();
		aviones.clear();
		barcos.clear();

		for (Persona persona : personas) {

			for (Vehiculo vehiculo : persona.getVehiculos()) {
				vehiculos.add(vehiculo);
			}

			for (Avion avion : persona.getAviones()) {
				aviones.add(avion);
			}

			for (Barco barco : persona.getBarcos()) {
				barcos.add(barco);
			}
		}
	}

	/*----------- Método Modificar Barco ---------- 
	 * Se obtiene la fila seleccionada  en la tabla de barcos, 
	 * en caso de que no se seleccione ninguna fila se lanza la excepción correspondiente. 
	 * Se llama al método modificar datos del barco en la lista general
	 * de barcos en la fila correspondiente.
	 */

	public void modificarBarco(ArrayList<Persona> personas) throws CellNoSelectedException, ItemNoSelectedException {

		int fila = tableBarcos.getSelectedRow(); // obtener celda seleccionada

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		barcos.get(fila).modificarDatos(tableBarcos);

		filtroVehiculos(personas);
	}

	/*
	 * ----------- Método Modificar Avion ----------
	 * 
	 * Lo mismo que en método modificar barco solo que en la tabla de aviones.
	 */
	public void modificarAvion(ArrayList<Persona> personas) throws CellNoSelectedException, ItemNoSelectedException {

		int fila = tableAviones.getSelectedRow(); // obtener celda seleccionada

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		aviones.get(fila).modificarDatos(tableAviones);

		filtroVehiculos(personas);
	}

	/*
	 * ----------- Método Ordenar Vehiculos ----------
	 * 
	 * En las diferentes listas se compara la ID del vehículo anterior con el
	 * siguiente y ordena la misma de forma decreciente.
	 */

	public static void sortVehiculos(ArrayList<Vehiculo> vehiculos) {

		vehiculos.sort((vehiculo1, vehiculo2) -> vehiculo1.getIdVehiculo().compareTo(vehiculo2.getIdVehiculo()));

	}

	public static void sortBarcos(ArrayList<Barco> barcos) {

		barcos.sort((barco1, barco2) -> barco1.getIdVehiculo().compareTo(barco2.getIdVehiculo()));

	}

	public static void sortAviones(ArrayList<Avion> aviones) {

		aviones.sort((avion1, avion2) -> avion1.getIdVehiculo().compareTo(avion2.getIdVehiculo()));

	}
}
