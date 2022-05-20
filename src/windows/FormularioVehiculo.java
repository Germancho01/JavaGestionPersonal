package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class FormularioVehiculo extends JFrame {

	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;
	JComboBox comboBoxTipo;
	private String nombre, color;
	private Integer cantPasajeros, idVehiculo = 0;
	private double manga, eslora, longitud;

	/**
	 * Create the frame.
	 */
	public FormularioVehiculo(ArrayList<Persona> personas, int fila) {

		// --------------- Frame --------------------

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(54, 26, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(246, 26, 46, 14);
		contentPane.add(lblColor);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(54, 77, 46, 14);
		contentPane.add(lblTipo);

		JLabel lblAtributo1 = new JLabel("Atributo1");
		lblAtributo1.setBounds(246, 73, 142, 14);
		contentPane.add(lblAtributo1);

		JLabel lblAtributo2 = new JLabel("Atributo2");
		lblAtributo2.setBounds(246, 132, 142, 14);
		contentPane.add(lblAtributo2);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(110, 23, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldColor = new JTextField();
		textFieldColor.setColumns(10);
		textFieldColor.setBounds(302, 23, 86, 20);
		contentPane.add(textFieldColor);

		textFieldAtributo1 = new JTextField();
		textFieldAtributo1.setColumns(10);
		textFieldAtributo1.setBounds(246, 98, 86, 20);
		contentPane.add(textFieldAtributo1);

		textFieldAtributo2 = new JTextField();
		textFieldAtributo2.setColumns(10);
		textFieldAtributo2.setBounds(246, 157, 86, 20);
		contentPane.add(textFieldAtributo2);

		// --------------- Botón Agregar --------------------

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarDatos(personas, fila);
				vaciarCampos();
			}
		});
		btnAgregar.setBounds(54, 207, 89, 23);
		contentPane.add(btnAgregar);

		// --------------- CheckBox Tipo de Vehiculo--------------------

		comboBoxTipo = new JComboBox();
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
		comboBoxTipo.setBounds(110, 73, 86, 22);
		contentPane.add(comboBoxTipo);

	}

	/*
	 * 
	 * Methods
	 * 
	 */

	// ----------- Método Ingresar Datos ----------

	private void ingresarDatos(ArrayList<Persona> personas, int fila) {
		nombre = textFieldNombre.getText();
		color = textFieldColor.getText();

		if (comboBoxTipo.getSelectedIndex() == 0) {
			eslora = Double.parseDouble(textFieldAtributo1.getText());
			manga = Double.parseDouble(textFieldAtributo2.getText());
			
			Vehiculo barco = new Barco(idVehiculo, nombre, color, personas.get(fila), eslora, manga);
			personas.get(fila).getVehiculos().add(barco);
			
		} else if (comboBoxTipo.getSelectedIndex() == 1) {
			longitud = Double.parseDouble(textFieldAtributo1.getText());
			cantPasajeros = Integer.parseInt(textFieldAtributo2.getText());

			Vehiculo avion = new Avion(idVehiculo, nombre, color, null, longitud, cantPasajeros);
			personas.get(fila).getVehiculos().add(avion);
		}
		
		idVehiculo++;
		System.out.println(idVehiculo);
	}

	// ----------- Método Vaciar Campos ----------
	private void vaciarCampos() {
		textFieldAtributo1.setText("");
		textFieldAtributo2.setText("");
		textFieldColor.setText("");
		textFieldNombre.setText("");

	}
}
