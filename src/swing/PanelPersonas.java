package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import exceptions.FieldNoCompletedException;

public class PanelPersonas extends JPanel {
	
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

	public PanelPersonas(ArrayList<Persona> personas) {

		setBounds(26, 11, 613, 588);
		setLayout(null);

		// --------------- Labels --------------------

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 32, 46, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(34, 82, 46, 14);
		add(lblApellido);

		JLabel lblCantHijos = new JLabel("Cantidad Hijos");
		lblCantHijos.setBounds(260, 32, 98, 14);
		add(lblCantHijos);

		JLabel lblDptoResidencia = new JLabel("Dpto Residencia");
		lblDptoResidencia.setBounds(260, 81, 98, 14);
		add(lblDptoResidencia);

		JLabel lblFecNacimiento = new JLabel("Fecha de nacimiento");
		lblFecNacimiento.setBounds(261, 127, 124, 14);
		add(lblFecNacimiento);

		// --------------- TexFields --------------------

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(90, 30, 140, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(90, 79, 139, 20);
		add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldCantHijos = new JTextField();
		textFieldCantHijos.setBounds(396, 29, 168, 20);
		add(textFieldCantHijos);
		textFieldCantHijos.setColumns(10);

		textFieldDptoResidencia = new JTextField();
		textFieldDptoResidencia.setBounds(396, 78, 168, 20);
		add(textFieldDptoResidencia);
		textFieldDptoResidencia.setColumns(10);

		// --------------- Botón Agregar --------------------

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(490, 184, 89, 23);
		add(btnAgregar);

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
		add(btnModificar);
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
		add(btnEliminar);
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
		add(btnEliminarTodo);
		btnEliminarTodo.setFocusable(false);

		// --------------- ComboBox Dia --------------------

		// cargar array con la cantidad de días del mes
		String[] dias = new String[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = Integer.toString(i + 1);
		}

		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setBounds(396, 123, 46, 22);
		add(comboBoxDia);
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
		add(comboBoxMes);
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
		add(comboBoxAnio);
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
		add(scrollPane);

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
		add(list);
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

}
