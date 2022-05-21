package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.FieldNoCompletedException;

public class PanelVehiculos extends JPanel {
	
	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	private ArrayList<Barco> barcos = new ArrayList<Barco>();
	private ArrayList<Avion> aviones = new ArrayList<Avion>();
	
	private String nombre, color;
	private Integer cantPasajeros;
	private Integer idVehiculo;
	private double manga, eslora, longitud;
	

	private JTextField textFieldNombre;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;
	private JComboBox comboBoxTipo;

	public PanelVehiculos(ArrayList<Persona> personas) {

		setBounds(26, 11, 613, 588);
		setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 32, 46, 14);
		add(lblNombre);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(34, 82, 46, 14);
		add(lblColor);

		JLabel lblAtributo1 = new JLabel("Cantidad de pasajeros");
		lblAtributo1.setBounds(34, 132, 145, 14);
		add(lblAtributo1);

		JLabel lblAtributo2 = new JLabel("Longitud");
		lblAtributo2.setBounds(34, 182, 145, 14);
		add(lblAtributo2);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(280, 82, 30, 14);
		add(lblTipo);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(280, 32, 64, 14);
		add(lblPropietario);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(90, 30, 140, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldColor = new JTextField();
		textFieldColor.setBounds(90, 79, 140, 20);
		textFieldColor.setColumns(10);
		add(textFieldColor);

		textFieldAtributo1 = new JTextField();
		textFieldAtributo1.setBounds(184, 129, 46, 20);
		textFieldAtributo1.setColumns(10);
		add(textFieldAtributo1);

		textFieldAtributo2 = new JTextField();
		textFieldAtributo2.setBounds(184, 179, 46, 20);
		textFieldAtributo2.setColumns(10);
		add(textFieldAtributo2);

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
		add(comboBoxTipo);

		// --------------- ComboBox Propietario --------------------
		
		String[] personasString = new String[personas.size()];
		
		for (int i = 0; i < personas.size(); i++) {
			personasString[i] = personas.get(i).getNombre() + personas.get(i).getApellido();
		}
		
		JComboBox<String> comboBoxPropietario = new JComboBox<String>();
		comboBoxPropietario.setModel(new DefaultComboBoxModel<String>(personasString));
		comboBoxPropietario.setBounds(396, 28, 168, 22);
		add(comboBoxPropietario);
		
		// --------------- Botón Agregar --------------------
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingresarDatos(personas, comboBoxPropietario.getSelectedIndex());
				}catch (FieldNoCompletedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Revise que haya ingresado correctamente todos los datos.");
				}
				
			}
		});
		btnAgregar.setBounds(54, 207, 89, 23);
		btnAgregar.setBounds(141, 231, 89, 23);
		btnAgregar.setFocusable(false);
		add(btnAgregar);
		
		// --------------- Botón Modificar --------------------
		
		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBounds(30, 524, 89, 23);
		btnModificar_1.setFocusable(false);
		add(btnModificar_1);

		// --------------- Botón Eliminar --------------------
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setBounds(129, 524, 89, 23);
		btnEliminar_1.setFocusable(false);
		add(btnEliminar_1);

		// --------------- Botón Eliminar Todo --------------------
		
		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.setBounds(228, 524, 116, 23);
		btnEliminarTodo_1.setFocusable(false);
		add(btnEliminarTodo_1);

	}
	
	/*
	 * 
	 * Methods
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
