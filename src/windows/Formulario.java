package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import classes.Persona;
import exceptions.ItemNoSelectedException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class Formulario extends JFrame {

	/**
	 * 
	 * Variables.
	 * 
	 */

	private JFrame frmGestinDePersonas;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDptoResidencia;
	private JTextField textFieldCantHijos;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;
	private ArrayList<Persona> personas = new ArrayList<Persona>();

	private String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fecNacimiento;
	private Integer anio, mes, dia;
	private Integer id = -1;
	private JTextField textFieldNombreVehiculo;
	private JTextField textFieldColor;
	private JLabel lblTipoVehiculo;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSi;
	private JLabel lblAtributo1;
	private JLabel lblAtributo2;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;
	private JButton btnAgregar;

	/**
	 * 
	 * Create the application.
	 * 
	 */
	public Formulario() {
		initialize();
	}

	public Formulario(int i) {

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {

		// --------------- Frame --------------------

		frmGestinDePersonas = new JFrame(); // inicializar el frame
		frmGestinDePersonas.setTitle("Gesti\u00F3n de Personas"); // poner título al frame
		// poner icono en el frame
		frmGestinDePersonas.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/images/logoPerson.png")));
		frmGestinDePersonas.getContentPane().setForeground(SystemColor.textHighlight); // color del JPane
		frmGestinDePersonas.setBounds(0, 0, 600, 300); // tamaño del frame
		frmGestinDePersonas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // purgar el frame al apretar x
		frmGestinDePersonas.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmGestinDePersonas.setVisible(true); // hacer visible el frame
		frmGestinDePersonas.setResizable(false); // impide que se cambie el tamaño del frame
		frmGestinDePersonas.getContentPane().setLayout(null); // colocar absolute layout al panel

		JPanel panel = new JPanel();
		panel.setBounds(20, 209, 539, 238);
		frmGestinDePersonas.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre"); // nueva JLabel
		lblNombre.setBounds(29, 33, 46, 14); // setear coordenadas y tamaño
		frmGestinDePersonas.getContentPane().add(lblNombre); // agregar label al panel

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(29, 82, 46, 14);
		frmGestinDePersonas.getContentPane().add(lblApellido);

		JLabel lblDptoResidencia = new JLabel("Dpto Residencia");
		lblDptoResidencia.setBounds(256, 82, 98, 14);
		frmGestinDePersonas.getContentPane().add(lblDptoResidencia);

		JLabel lblCantHijos = new JLabel("Cantidad Hijos");
		lblCantHijos.setBounds(256, 33, 98, 14);
		frmGestinDePersonas.getContentPane().add(lblCantHijos);

		JLabel lblFecNacimiento = new JLabel("Fecha de nacimiento");
		lblFecNacimiento.setBounds(256, 128, 124, 14);
		frmGestinDePersonas.getContentPane().add(lblFecNacimiento);

		lblTipoVehiculo = new JLabel("Tipo de Veh\u00EDculo");
		lblTipoVehiculo.setBounds(258, 14, 94, 14);
		panel.add(lblTipoVehiculo);

		JLabel lblNombreVehiculo = new JLabel("Nombre");
		lblNombreVehiculo.setBounds(10, 14, 46, 14);
		panel.add(lblNombreVehiculo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(10, 63, 46, 14);
		panel.add(lblColor);

		JLabel lblVehiculo = new JLabel("Veh\u00EDculo");
		lblVehiculo.setBounds(29, 128, 60, 14);
		frmGestinDePersonas.getContentPane().add(lblVehiculo);

		lblAtributo1 = new JLabel("Nombre");
		lblAtributo1.setBounds(10, 106, 77, 14);
		panel.add(lblAtributo1);

		lblAtributo2 = new JLabel("Color");
		lblAtributo2.setBounds(10, 172, 139, 14);
		panel.add(lblAtributo2);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField(); // nuevo JTextField
		textFieldNombre.setBounds(85, 30, 139, 20); // setear tamaño y coordenadas
		frmGestinDePersonas.getContentPane().add(textFieldNombre); // añadir al Panel
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(85, 79, 139, 20);
		frmGestinDePersonas.getContentPane().add(textFieldApellido);

		textFieldDptoResidencia = new JTextField();
		textFieldDptoResidencia.setColumns(10);
		textFieldDptoResidencia.setBounds(391, 79, 168, 20);
		frmGestinDePersonas.getContentPane().add(textFieldDptoResidencia);

		textFieldCantHijos = new JTextField();
		textFieldCantHijos.setColumns(10);
		textFieldCantHijos.setBounds(391, 30, 168, 20);
		frmGestinDePersonas.getContentPane().add(textFieldCantHijos);

		textFieldNombreVehiculo = new JTextField();
		textFieldNombreVehiculo.setColumns(10);
		textFieldNombreVehiculo.setBounds(66, 11, 139, 20);
		panel.add(textFieldNombreVehiculo);

		textFieldColor = new JTextField();
		textFieldColor.setColumns(10);
		textFieldColor.setBounds(66, 60, 139, 20);
		panel.add(textFieldColor);

		textFieldAtributo1 = new JTextField();
		textFieldAtributo1.setBounds(10, 131, 46, 20);
		panel.add(textFieldAtributo1);
		textFieldAtributo1.setColumns(10);

		textFieldAtributo2 = new JTextField();
		textFieldAtributo2.setBounds(10, 197, 46, 20);
		panel.add(textFieldAtributo2);
		textFieldAtributo2.setColumns(10);

		/**
		 * 
		 * Components actions.
		 * 
		 */

		// --------------- Radio Button --------------------

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSi.setSelected(false);
				panel.setVisible(false);
				frmGestinDePersonas.setBounds(0, 0, 600, 300);
				frmGestinDePersonas.setLocationRelativeTo(null);
				btnAgregar.setBounds(470, 185, 89, 23);
			}
		});
		rdbtnNo.setBounds(133, 124, 46, 23);
		rdbtnNo.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(rdbtnNo);

		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				panel.setVisible(true);
				frmGestinDePersonas.setBounds(0, 0, 600, 600);
				frmGestinDePersonas.setLocationRelativeTo(null);
				btnAgregar.setBounds(470, 500, 89, 23);
			}
		});
		rdbtnSi.setBounds(85, 124, 46, 23);
		rdbtnSi.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(rdbtnSi);

		// --------------- Botón Agregar --------------------

		btnAgregar = new JButton("Agregar"); // nuevo botón

		// Agregar función al botón
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ingresar datos con las excepciones de text fields no completados y comboboxes
				// no seleccionados
				try {
					ingresarDatos();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.");
				} catch (ItemNoSelectedException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnAgregar.setBounds(470, 185, 89, 23); // setea las coordenadas y tamaño del botón
		btnAgregar.setFocusable(false); // quita el recuadro en el texto cuando se selecciona el botón
		frmGestinDePersonas.getContentPane().add(btnAgregar); // agregar botón al panel

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Listado(personas);
			}
		});
		btnListar.setFocusable(false);
		btnListar.setBounds(20, 185, 89, 23);
		frmGestinDePersonas.getContentPane().add(btnListar);

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}
		comboBoxDia = new JComboBox<String>(); // nuevo comboBox con Items tipo String
		comboBoxDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el dato seleccionado en el comboBoxDia con un Exception,
				// en caso de que se seleccione el valor nullo.
				// el valor se almacena en la variable entera "dia", despues de pasar el dato
				// tipo
				// objeto a String y luego a Integer.
				try {
					dia = Integer.parseInt(comboBoxDia.getSelectedItem().toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dato inválido");
				}
			}
		});
		comboBoxDia.setModel(new DefaultComboBoxModel<String>(dias));
		comboBoxDia.setSelectedIndex(0);
		comboBoxDia.setBounds(391, 124, 46, 22);
		comboBoxDia.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(comboBoxDia);

		// --------------- ComboBox Mes --------------------

		String[] meses = new String[12];
		for (int i = 0; i < meses.length; i++) {
			meses[i] = Integer.toString(i + 1);
		}
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mes = Integer.parseInt(comboBoxMes.getSelectedItem().toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dato inválido");
				}
			}
		});
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(meses));
		comboBoxMes.setSelectedIndex(0);
		comboBoxMes.setFocusable(false);
		comboBoxMes.setBounds(447, 124, 46, 22);
		frmGestinDePersonas.getContentPane().add(comboBoxMes);

		// --------------- ComboBox Año --------------------

		String[] anios = new String[100];
		for (int i = 0; i < anios.length; i++) {
			anios[i] = Integer.toString(2022 - i);
		}
		comboBoxAnio = new JComboBox<String>();
		comboBoxAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anio = Integer.parseInt(comboBoxAnio.getSelectedItem().toString());
			}
		});
		comboBoxAnio.setModel(new DefaultComboBoxModel<String>(anios));
		comboBoxAnio.setSelectedIndex(0);
		comboBoxAnio.setFocusable(false);
		comboBoxAnio.setBounds(503, 124, 56, 22);
		frmGestinDePersonas.getContentPane().add(comboBoxAnio);

		// --------------- ComboBox Tipo de Vehículo --------------------

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTipo.getSelectedIndex() == 0) {
					lblAtributo1.setText("Eslora: ");
					lblAtributo2.setText("Manga: ");
				}
				if (comboBoxTipo.getSelectedIndex() == 1) {
					lblAtributo1.setText("Longitud: ");
					lblAtributo2.setText("Cantidad de Pasajeros: ");
				} else {

				}
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "Barco", "Avi\u00F3n" }));
		comboBoxTipo.setSelectedIndex(0);
		comboBoxTipo.setBounds(362, 10, 120, 22);
		comboBoxTipo.setFocusable(false);
		panel.add(comboBoxTipo);

	}

	/**
	 * 
	 * Methods.
	 * 
	 */

	// --------------- Método Ingresar Datos --------------------

	public void ingresarDatos() throws ItemNoSelectedException {

		// extraer datos de los textField y almacenarlo en variables
		nombre = textFieldNombre.getText();
		apellido = textFieldApellido.getText();
		dptoResidencia = textFieldDptoResidencia.getText();
		cantHijos = Byte.parseByte(textFieldCantHijos.getText()); // se transforma el dato de tipo String a Byte

		fecNacimiento = LocalDate.of(anio, mes, dia); // se genera una fecha LocalDate a partir de los datos ingresados

		// aumentar id
		id++;

		// crear instancia de persona y almcanearla en una lista de objetos con su ID
		personas.add(new Persona(id, nombre, apellido, dptoResidencia, cantHijos, fecNacimiento));

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
	}
}
