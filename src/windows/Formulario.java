package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

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
	private JButton btnListar;

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
				Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/images/logoPerson.png")));
		frmFormulario.getContentPane().setForeground(SystemColor.textHighlight); // color del JPane
		frmFormulario.setBounds(0, 0, 600, 284); // tamaño del frame
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esconder ventana al apretar cerrar
		frmFormulario.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmFormulario.setVisible(true); // hacer visible el frame
		frmFormulario.setResizable(false); // impide que se cambie el tamaño del frame
		frmFormulario.getContentPane().setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre"); // nueva JLabel
		lblNombre.setBounds(29, 33, 46, 14); // setear coordenadas y tamaño
		frmFormulario.getContentPane().add(lblNombre); // agregar label al panel

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(29, 82, 46, 14);
		frmFormulario.getContentPane().add(lblApellido);

		JLabel lblDptoResidencia = new JLabel("Dpto Residencia");
		lblDptoResidencia.setBounds(256, 82, 98, 14);
		frmFormulario.getContentPane().add(lblDptoResidencia);

		JLabel lblCantHijos = new JLabel("Cantidad Hijos");
		lblCantHijos.setBounds(256, 33, 98, 14);
		frmFormulario.getContentPane().add(lblCantHijos);

		JLabel lblFecNacimiento = new JLabel("Fecha de nacimiento");
		lblFecNacimiento.setBounds(256, 128, 124, 14);
		frmFormulario.getContentPane().add(lblFecNacimiento);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField(); // nuevo JTextField
		textFieldNombre.setBounds(85, 30, 139, 20); // setear tamaño y coordenadas
		frmFormulario.getContentPane().add(textFieldNombre); // añadir al Panel
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(85, 79, 139, 20);
		frmFormulario.getContentPane().add(textFieldApellido);

		textFieldDptoResidencia = new JTextField();
		textFieldDptoResidencia.setColumns(10);
		textFieldDptoResidencia.setBounds(391, 79, 168, 20);
		frmFormulario.getContentPane().add(textFieldDptoResidencia);

		textFieldCantHijos = new JTextField();
		textFieldCantHijos.setColumns(10);
		textFieldCantHijos.setBounds(391, 30, 168, 20);
		frmFormulario.getContentPane().add(textFieldCantHijos);

		// --------------- Botón Agregar --------------------

		btnAgregar = new JButton("Agregar"); // nuevo botón

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
		btnAgregar.setBounds(470, 185, 89, 23); // setea las coordenadas y tamaño del botón
		btnAgregar.setFocusable(false); // quita el recuadro en el texto cuando se selecciona el botón
		frmFormulario.getContentPane().add(btnAgregar); // agregar botón al panel

		// --------------- Botón Listar --------------------

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Listado(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnListar.setFocusable(false);
		btnListar.setBounds(371, 185, 89, 23);
		frmFormulario.getContentPane().add(btnListar);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inicio(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);
		btnVolver.setBounds(29, 185, 89, 23);
		frmFormulario.getContentPane().add(btnVolver);

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}
		comboBoxDia = new JComboBox<String>(); // nuevo comboBox con Items tipo String
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
		comboBoxDia.setBounds(391, 124, 46, 22);
		comboBoxDia.setFocusable(false);
		frmFormulario.getContentPane().add(comboBoxDia);

		// --------------- ComboBox Mes --------------------

		String[] meses = new String[12];
		for (int i = 0; i < meses.length; i++) {
			meses[i] = Integer.toString(i + 1);
		}
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mes = Integer.parseInt(comboBoxMes.getSelectedItem().toString());
			}
		});
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(meses));
		comboBoxMes.setSelectedIndex(0);
		comboBoxMes.setFocusable(false);
		comboBoxMes.setBounds(447, 124, 46, 22);
		frmFormulario.getContentPane().add(comboBoxMes);

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
		frmFormulario.getContentPane().add(comboBoxAnio);

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
		personas.add(new Persona(idPersona, nombre, apellido, dptoResidencia, cantHijos, fecNacimiento, vehiculos));

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
