package swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelVehiculos extends JPanel {

	private JTextField textFieldNombre;
	private JTextField textFieldColor;
	private JTextField textFieldAtributo1;
	private JTextField textFieldAtributo2;

	public PanelVehiculos() {

		setBounds(26, 11, 613, 588);
		setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(34, 32, 46, 14);
		add(lblNewLabel);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(34, 82, 46, 14);
		add(lblColor);

		JLabel lblNewAtributo1 = new JLabel("Cantidad de pasajeros");
		lblNewAtributo1.setBounds(34, 132, 145, 14);
		add(lblNewAtributo1);

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(34, 182, 145, 14);
		add(lblLongitud);

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

		JComboBox comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(396, 78, 86, 22);
		add(comboBoxTipo);

		// --------------- ComboBox Propietario --------------------

		JComboBox comboBoxPropietario = new JComboBox();
		comboBoxPropietario.setBounds(396, 28, 168, 22);
		add(comboBoxPropietario);
		
		// --------------- Botón Agregar --------------------
		
		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.setBounds(141, 231, 89, 23);
		btnAgregar_1.setFocusable(false);
		add(btnAgregar_1);
		
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

}
