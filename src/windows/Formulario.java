package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Persona;
import exceptions.ItemNoSelectedException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class Formulario extends JFrame{

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
	private JTextField textFieldAnio;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JCheckBox chckbxSoloConHijos;
	private JCheckBox chckbxMayoresDeEdad;
	private HashMap<Integer, Persona> mapaPersonas = new HashMap<Integer, Persona>();

	private String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fecNacimiento;
	private Integer anio, mes, dia;

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
		frmGestinDePersonas.setBounds(100, 100, 600, 622); // tamaño del frame
		frmGestinDePersonas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // purgar el frame al apretar x
		frmGestinDePersonas.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmGestinDePersonas.setVisible(true); // hacer visible el frame
		frmGestinDePersonas.getContentPane().setLayout(null); // colocar absolute layout al panel

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

		textFieldAnio = new JTextField();
		textFieldAnio.setBounds(499, 125, 60, 20);
		frmGestinDePersonas.getContentPane().add(textFieldAnio);
		textFieldAnio.setColumns(10);

		// --------------- Botón Agregar --------------------

		JButton btnAgregar = new JButton("Agregar"); // nuevo botón

		// Agregar función al botón
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ingresar datos con las excepciones de text fields no completados y comboboxes
				// no seleccionados
				try {
					ingresarDatos();
					new Listado(mapaPersonas);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.");
				} catch (ItemNoSelectedException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnAgregar.setBounds(29, 178, 89, 23); // setea las coordenadas y tamaño del botón
		btnAgregar.setFocusable(false); // quita el recuadro en el texto cuando se selecciona el botón
		frmGestinDePersonas.getContentPane().add(btnAgregar); // agregar botón al panel

		/**
		 * 
		 * Components actions.
		 * 
		 */

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[32];
		for (int i = 1; i < dias.length; i++) {
			dias[i] = Integer.toString(i);
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
		comboBoxDia.setBounds(391, 124, 46, 22);
		comboBoxDia.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(comboBoxDia);

		// --------------- ComboBox Mes --------------------

		String[] meses = new String[13];
		for (int i = 1; i < meses.length; i++) {
			meses[i] = Integer.toString(i);
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
		comboBoxMes.setFocusable(false);
		comboBoxMes.setBounds(447, 124, 46, 22);
		frmGestinDePersonas.getContentPane().add(comboBoxMes);
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
		anio = Integer.parseInt(textFieldAnio.getText()); // se transforma el dato de tipo String a Integer

		// Excepción si no se selecciona ningún item del comboBox
		if (comboBoxDia.getSelectedIndex() == -1 || comboBoxMes.getSelectedIndex() == -1) {
			throw new ItemNoSelectedException();
		}

		fecNacimiento = LocalDate.of(anio, mes, dia); // se genera una fecha LocalDate a partir de los datos ingresados

		// crear instancia de persona y almcanearla en una lista de objetos con su ID
		mapaPersonas.put(Persona.getId(), new Persona(nombre, apellido, dptoResidencia, cantHijos, fecNacimiento));

		// resetear los componentes
		resetearCampos();
	}

	// --------------- Método Resetear Campos --------------------

	public void resetearCampos() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCantHijos.setText("");
		textFieldDptoResidencia.setText("");
		textFieldAnio.setText("");
		comboBoxDia.setSelectedIndex(1);
		comboBoxMes.setSelectedIndex(1);
	}
	
	public HashMap<Integer, Persona> getMapaPersonas(){
		return mapaPersonas;
	}

}
