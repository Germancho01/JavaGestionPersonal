package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.FieldNoCompletedException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;

public class Formulario extends JFrame {

	/**
	 * 
	 * Declare Variables.
	 * 
	 */

	private String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fecNacimiento;
	private Integer anio, mes, dia;
	private Integer idPersona;

	// --- Frame
	private JFrame frmFormulario;

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
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnEliminarTodo;
	private JTable table_1;
	private JPanel panelVehiculos;
	private JTextField textFieldNombreVehiculo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_2;
	private JTable table_3;

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

		frmFormulario = new JFrame(); // inicializar el frame
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

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}

		// --------------- ComboBox Mes --------------------

		String[] meses = new String[12];
		for (int i = 0; i < meses.length; i++) {
			meses[i] = Integer.toString(i + 1);
		}

		// --------------- ComboBox Año --------------------

		String[] anios = new String[100];
		for (int i = 0; i < anios.length; i++) {
			anios[i] = Integer.toString(2022 - i);
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(26, 11, 613, 588);
		frmFormulario.getContentPane().add(tabbedPane);

		JPanel panelPersonas = new JPanel();
		tabbedPane.addTab("Personas", null, panelPersonas, null);
		panelPersonas.setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 32, 46, 14);
		panelPersonas.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(34, 82, 46, 14);
		panelPersonas.add(lblApellido);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(90, 30, 140, 20);
		panelPersonas.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(90, 79, 139, 20);
		panelPersonas.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		JLabel lblCantHijos = new JLabel("Cantidad Hijos");
		lblCantHijos.setBounds(260, 32, 98, 14);
		panelPersonas.add(lblCantHijos);

		JLabel lblDptoResidencia = new JLabel("Dpto Residencia");
		lblDptoResidencia.setBounds(260, 81, 98, 14);
		panelPersonas.add(lblDptoResidencia);

		JLabel lblFecNacimiento = new JLabel("Fecha de nacimiento");
		lblFecNacimiento.setBounds(261, 127, 124, 14);
		panelPersonas.add(lblFecNacimiento);

		textFieldCantHijos = new JTextField();
		textFieldCantHijos.setBounds(396, 29, 168, 20);
		panelPersonas.add(textFieldCantHijos);
		textFieldCantHijos.setColumns(10);

		textFieldDptoResidencia = new JTextField();
		textFieldDptoResidencia.setBounds(396, 78, 168, 20);
		panelPersonas.add(textFieldDptoResidencia);
		textFieldDptoResidencia.setColumns(10);
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

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(26, 184, 89, 23);
		panelPersonas.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setFocusable(false);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(125, 184, 89, 23);
		panelPersonas.add(btnEliminar);
		btnEliminar.setFocusable(false);

		btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.setBounds(224, 184, 116, 23);
		panelPersonas.add(btnEliminarTodo);
		btnEliminarTodo.setFocusable(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 218, 553, 200);
		panelPersonas.add(scrollPane);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table_1);

		JList list = new JList();
		list.setBounds(26, 429, 553, 89);
		panelPersonas.add(list);

		panelVehiculos = new JPanel();
		tabbedPane.addTab("Vehículos", null, panelVehiculos, null);
		panelVehiculos.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 32, 46, 14);
		panelVehiculos.add(lblNewLabel);

		textFieldNombreVehiculo = new JTextField();
		textFieldNombreVehiculo.setBounds(90, 30, 140, 20);
		panelVehiculos.add(textFieldNombreVehiculo);
		textFieldNombreVehiculo.setColumns(10);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(34, 82, 46, 14);
		panelVehiculos.add(lblColor);

		textField = new JTextField();
		textField.setBounds(90, 79, 140, 20);
		textField.setColumns(10);
		panelVehiculos.add(textField);

		JLabel lblNewAtributo1 = new JLabel("Cantidad de pasajeros");
		lblNewAtributo1.setBounds(34, 132, 145, 14);
		panelVehiculos.add(lblNewAtributo1);

		textField_1 = new JTextField();
		textField_1.setBounds(184, 129, 46, 20);
		textField_1.setColumns(10);
		panelVehiculos.add(textField_1);

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(34, 182, 145, 14);
		panelVehiculos.add(lblLongitud);

		textField_2 = new JTextField();
		textField_2.setBounds(184, 179, 46, 20);
		textField_2.setColumns(10);
		panelVehiculos.add(textField_2);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(280, 82, 30, 14);
		panelVehiculos.add(lblTipo);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(396, 78, 86, 22);
		panelVehiculos.add(comboBox);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(280, 32, 64, 14);
		panelVehiculos.add(lblPropietario);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(396, 28, 168, 22);
		panelVehiculos.add(comboBox_1);

		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBounds(265, 509, 89, 23);
		btnModificar_1.setFocusable(false);
		panelVehiculos.add(btnModificar_1);

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setBounds(364, 509, 89, 23);
		btnEliminar_1.setFocusable(false);
		panelVehiculos.add(btnEliminar_1);

		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.setBounds(463, 509, 116, 23);
		btnEliminarTodo_1.setFocusable(false);
		panelVehiculos.add(btnEliminarTodo_1);

		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.setBounds(130, 226, 100, 23);
		btnAgregar_1.setFocusable(false);
		panelVehiculos.add(btnAgregar_1);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(26, 260, 553, 238);
		panelVehiculos.add(tabbedPane_1);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Barcos", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 548, 210);
		panel.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_2.setViewportView(table_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane_1.addTab("Aviones", scrollPane_1);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPane_1.setViewportView(table_2);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(26, 615, 89, 23);
		frmFormulario.getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inicio(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);

	}

	/**
	 * 
	 * Create Methods.
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
}
