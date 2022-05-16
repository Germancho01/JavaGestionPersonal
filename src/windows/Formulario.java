package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Persona;
import exceptions.CellNoSelectedException;
import exceptions.ItemNoSelectedException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class Formulario {
	
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
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private String[] datos;
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

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {

		// ---------------  Frame  --------------------
		
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

		// ---------------  Labels  --------------------
		
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

		// ---------------  TexFields  --------------------

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

		// ---------------  Table  --------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 227, 530, 280); // setea las coordenadas de la tabla y su tamaño
		frmGestinDePersonas.getContentPane().add(scrollPane); // agrega el scroll pane al panel
		
		table = new JTable(); // nueva tabla
		// se define el modelo por defecto de la tabla, se agrega nombre a las columnas y preferencias de la tabla
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Cantidad Hijos", "Dpto Residencia", "Fecha Nacimiento"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//ancho por defecto de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);

		scrollPane.setViewportView(table); // se agrega la tabla al scroll pane

		model = (DefaultTableModel) table.getModel(); // obtener el modelo de la tabla para poder añadir datos a la misma

		// limpiar la tabla
		eliminarTodo();

		/**
		 * 
		 * Components actions.
		 * 
		 */

		// ---------------  Botón Agregar  --------------------
		
		JButton btnAgregar = new JButton("Agregar"); // nuevo botón
		
		// Agregar función al botón
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ingresar datos con las excepciones de text fields no completados y comboboxes no seleccionados
				try {
					ingresarDatos();
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

		// ---------------  Botón Eliminar  --------------------
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarPersonaSeleccionada();
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar.setBounds(169, 178, 89, 23);
		btnEliminar.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(btnEliminar);

		// ---------------  Botón Eliminar Todo  --------------------
		
		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTodo();
				personas.clear(); // limpia el arrayList de personas
			}
		});
		btnEliminarTodo.setBounds(306, 178, 115, 23);
		btnEliminarTodo.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(btnEliminarTodo);

		// ---------------  Botón Modificar  --------------------
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatos();
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(470, 178, 89, 23);
		btnModificar.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(btnModificar);

		// ---------------  ComboBox Dia  --------------------
		
		// cargar array con la cantidad de días del mes
		String[] dias = new String[32];
		for (int i = 1; i < dias.length; i++) {
			dias[i] = Integer.toString(i);
		}
		comboBoxDia = new JComboBox<String>(); //nuevo comboBox con Items tipo String
		comboBoxDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Obtener el dato seleccionado en el comboBoxDia con un Exception,
				// en caso de que se seleccione el valor nullo. 
				// el valor se almacena en la variable entera "dia", despues de pasar el dato tipo
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

		// ---------------  ComboBox Mes  --------------------
		
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

		// ---------------  CheckBox Mayores de Edad  --------------------
		
		chckbxMayoresDeEdad = new JCheckBox("Mostrar solo mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros();
			}
		});
		chckbxMayoresDeEdad.setBounds(29, 535, 229, 23);
		chckbxMayoresDeEdad.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(chckbxMayoresDeEdad);

		// ---------------  CheckBox Solo con Hijos  --------------------
		
		chckbxSoloConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxSoloConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros();
			}
		});
		chckbxSoloConHijos.setBounds(283, 535, 229, 23);
		chckbxSoloConHijos.setFocusable(false);
		frmGestinDePersonas.getContentPane().add(chckbxSoloConHijos);
	}
	
	/**
	 * 
	 * Methods.
	 * 
	 */

	// ---------------  Método Ingresar Datos  --------------------
	
	public void ingresarDatos() throws ItemNoSelectedException {
		
		datos = new String[6]; // almacena los datos que irán en las celdas

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

		ids.add(Persona.getId()); // se agrea la ID de la persona a la lista de IDs

		// crear instancia de persona y almcanearla en una lista de objetos
		personas.add(new Persona(nombre, apellido, dptoResidencia, cantHijos, fecNacimiento));

		cargarTodasLasPersonas();

		// resetear los componentes
		resetearCampos();
	}

	// ---------------  Método Modificar Datos  --------------------

	public void modificarDatos() throws CellNoSelectedException {
		// obtener celda seleccionada
		int fila = table.getSelectedRow();

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int columna = table.getSelectedColumn(); // obtener columna seleccionada
		
		// obtener ID de la persona a partir de la tabla y luego obtener la posición de esa persona
		// en el arrayList de las IDs
		int idTabla = Integer.parseInt((String) table.getValueAt(fila, 0));
		int index = ids.indexOf(idTabla);

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			personas.get(index).setNombre(nuevoNombre); // cambia el valor de la persona en el arrayList personas
			table.setValueAt(nuevoNombre, index, 1); // setea el nuevo valor en la tabla, en la fila y columna seleccionadas
		} else if (columna == 2) {
			String nuevoApellido = JOptionPane.showInputDialog("Ingrese nuevo apellido: ");
			personas.get(index).setNombre(nuevoApellido);
			table.setValueAt(nuevoApellido, index, 2);
		} else if (columna == 3) {
			try {
				Byte nuevoCantHijos = Byte.parseByte(JOptionPane.showInputDialog("Ingrese cantidad de hijos: "));
				personas.get(index).setCantHijos(nuevoCantHijos);
				table.setValueAt(nuevoCantHijos, index, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");
			personas.get(index).setNombre(nuevoDptoResidencia);
			table.setValueAt(nuevoDptoResidencia, index, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption, 
			// en caso de que el formato de la fecha no sea correcto
			try {
				String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");
				LocalDate nuevoDate = LocalDate.parse(nuevoDateString); //transforma el String ingresado en un tipo LocalDate
				personas.get(index).setFechaNacimiento(nuevoDate);
				table.setValueAt(nuevoDate, index, 5);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			}
		}
	}
	
	// ---------------  Método Eliminar Persona Seleccionada  --------------------

	public void eliminarPersonaSeleccionada() throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		int idTabla = Integer.parseInt((String) table.getValueAt(fila, 0));
		int index = ids.indexOf(idTabla);

		model.removeRow(fila); // elimina fila de la tabla
		personas.remove(index); // elimina persona en el arrayList personas
		ids.remove(index); // elimina el ID de la persona en el arrayList ids
	}
	
	// ---------------  Método Eliminar Todo  --------------------
	
	public void eliminarTodo() {
		int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i); // eliminar fila de la tabal en i
		}
	}
	
	// ---------------  Método Cargar Todas Las Personas  --------------------

	public void cargarTodasLasPersonas() {
		// limpiar la tabla
		eliminarTodo();

		// recorrer la lista de personas e irlas agregando a la tabla
		for (int i = 0; i < personas.size(); i++) {
			datos[0] = Integer.toString(personas.get(i).getIdPersona());
			datos[1] = personas.get(i).getNombre();
			datos[2] = personas.get(i).getApellido();
			datos[3] = personas.get(i).getCantHijos().toString();
			datos[4] = personas.get(i).getDptoResidencia();
			datos[5] = personas.get(i).getFechaNacimiento().toString();
			model.addRow(datos);
		}
	}

	// ---------------  Método Cargar Solo Personas Con Hijos  --------------------
	
	public void cargarSoloPersonasConHijos() {
		eliminarTodo();
		
		for (int i = 0; i < personas.size(); i++) {

			if (personas.get(i).getCantHijos() > 0) {
				datos[0] = Integer.toString(personas.get(i).getIdPersona());
				datos[1] = personas.get(i).getNombre();
				datos[2] = personas.get(i).getApellido();
				datos[3] = personas.get(i).getCantHijos().toString();
				datos[4] = personas.get(i).getDptoResidencia();
				datos[5] = personas.get(i).getFechaNacimiento().toString();
				model.addRow(datos);
			}

		}
	}

	// ---------------  Método Cargar Solo Mayores de Edad  --------------------
	
	public void cargarSoloMayoresDeEdad() {
		eliminarTodo();
		LocalDate hoy = LocalDate.now();

		for (int i = 0; i < personas.size(); i++) {

			if (personas.get(i).getFechaNacimiento().isBefore(hoy.plusYears(-18))) {
				datos[0] = Integer.toString(personas.get(i).getIdPersona());
				datos[1] = personas.get(i).getNombre();
				datos[2] = personas.get(i).getApellido();
				datos[3] = personas.get(i).getCantHijos().toString();
				datos[4] = personas.get(i).getDptoResidencia();
				datos[5] = personas.get(i).getFechaNacimiento().toString();
				model.addRow(datos);
			}

		}
	}
	
	// ---------------  Método Cargar Personas Mayores de Edad Con Hijos  --------------------

	public void cargarPersonasMayoresDeEdadConHijos() {
		eliminarTodo();
		LocalDate hoy = LocalDate.now();

		for (int i = 0; i < personas.size(); i++) {
			boolean esMayor = personas.get(i).getFechaNacimiento().isBefore(hoy.plusYears(-18));
			boolean tieneHijos = personas.get(i).getCantHijos() > 0;

			if (esMayor && tieneHijos) {
				datos[0] = Integer.toString(personas.get(i).getIdPersona());
				datos[1] = personas.get(i).getNombre();
				datos[2] = personas.get(i).getApellido();
				datos[3] = personas.get(i).getCantHijos().toString();
				datos[4] = personas.get(i).getDptoResidencia();
				datos[5] = personas.get(i).getFechaNacimiento().toString();
				model.addRow(datos);
			}

		}
	}
	
	// ---------------  Método Filtros  --------------------

	//filtros de los checkBox
	public void filtros() {
		if (chckbxMayoresDeEdad.isSelected() && chckbxSoloConHijos.isSelected()) {
			cargarPersonasMayoresDeEdadConHijos();
		} else if (chckbxMayoresDeEdad.isSelected() && !chckbxSoloConHijos.isSelected()) {
			cargarSoloMayoresDeEdad();
		} else if (!chckbxMayoresDeEdad.isSelected() && !chckbxSoloConHijos.isSelected()) {
			cargarTodasLasPersonas();
		} else if (!chckbxMayoresDeEdad.isSelected() && chckbxSoloConHijos.isSelected()) {
			cargarSoloPersonasConHijos();
		}
	}

	// ---------------  Método Resetear Campos  --------------------
	
	public void resetearCampos() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCantHijos.setText("");
		textFieldDptoResidencia.setText("");
		textFieldAnio.setText("");
		comboBoxDia.setSelectedIndex(1);
		comboBoxMes.setSelectedIndex(1);
		chckbxMayoresDeEdad.setSelected(false);
		chckbxSoloConHijos.setSelected(false);
	}
}
