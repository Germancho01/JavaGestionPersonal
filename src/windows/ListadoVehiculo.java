package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoVehiculo extends JFrame {

	private String[] datos = new String[6];

	private JFrame frame;

	private JTable table;
	private DefaultTableModel model;

	private JCheckBox chckbxAviones;
	private JCheckBox chckbxBarcos;
	private JButton btnVolver;

	/**
	 * 
	 * Create the application.
	 * 
	 */

	public ListadoVehiculo(ArrayList<Vehiculo> vehiculos, ArrayList<Persona> personas) {

		initialize(vehiculos, personas);

		limpiarTabla();

		// Cargar todos los vehiculos de la persona en la tabla
		for (int i = 0; i < vehiculos.size(); i++) {
			cargarPersona(vehiculos, i);
		}

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */

	private void initialize(ArrayList<Vehiculo> vehiculos, ArrayList<Persona> personas) {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// --------------- Table --------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 75, 530, 154); // setea las coordenadas de la tabla y su tamaño
		frame.getContentPane().add(scrollPane); // agrega el scroll pane al panel

		table = new JTable(); // nueva tabla
		// action doble click del mouse
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (e.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, vehiculos.get(fila).toString());
				}
			}
		});

		// se define el modelo por defecto de la tabla,
		// se agrega nombre a las columnas y preferencias de la tabla
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Color", "Tipo", "Eslora / Longitud", "Manga / Cant Pasajeros"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(73);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);

		scrollPane.setViewportView(table); // se agrega la tabla al scroll pane

		model = (DefaultTableModel) table.getModel(); // obtener el modelo de la tabla para poder añadir datos a la
														// misma

		// --------------- Botón Eliminar --------------------

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarPersona(vehiculos);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar.setBounds(360, 41, 89, 23);
		btnEliminar.setFocusable(false);
		frame.getContentPane().add(btnEliminar);

		// --------------- Botón Eliminar Todo --------------------

		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				vehiculos.clear(); // limpia el arrayList vehiculos
			}
		});
		btnEliminarTodo.setBounds(235, 41, 115, 23);
		btnEliminarTodo.setFocusable(false);
		frame.getContentPane().add(btnEliminarTodo);

		// --------------- Botón Modificar --------------------

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatos(vehiculos);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});
		btnModificar.setBounds(459, 41, 89, 23);
		btnModificar.setFocusable(false);
		frame.getContentPane().add(btnModificar);

		// --------------- Botón Volver --------------------
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Listado(personas);
				frame.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);
		btnVolver.setBounds(29, 291, 115, 23);
		frame.getContentPane().add(btnVolver);

		// --------------- CheckBox Mayores de Edad --------------------

		chckbxBarcos = new JCheckBox("Barcos");
		chckbxBarcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// filtros(vehiculos);
			}
		});
		chckbxBarcos.setBounds(451, 236, 97, 23);
		chckbxBarcos.setFocusable(false);
		frame.getContentPane().add(chckbxBarcos);

		// --------------- CheckBox Solo con Hijos --------------------

		chckbxAviones = new JCheckBox("Aviones");
		chckbxAviones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// filtros(vehiculos);
			}
		});
		chckbxAviones.setBounds(451, 262, 97, 23);
		chckbxAviones.setFocusable(false);
		frame.getContentPane().add(chckbxAviones);

	}

	/**
	 * 
	 * Create methods.
	 * 
	 */

	// --------------- Método Cargar Vehiculo --------------------

	public void cargarPersona(ArrayList<Vehiculo> vehiculos, int i) {
		String tipo;
		String eslora;
		String manga;
		String longitud;
		String cantPasajeros;

		if (vehiculos.get(i) instanceof Barco) {
			tipo = "Barco";
		} else {
			tipo = "Avión";
		}

		datos[0] = Integer.toString(vehiculos.get(i).getIdVehiculo());
		datos[1] = vehiculos.get(i).getNombre();
		datos[2] = vehiculos.get(i).getColor();
		datos[3] = tipo;
		datos[4] = null;
		datos[5] = null;
		model.addRow(datos);
	}

	// --------------- Método Modificar Dato --------------------

	public void modificarDatos(ArrayList<Vehiculo> vehiculos) throws CellNoSelectedException {

		int fila = table.getSelectedRow(); // obtener celda seleccionada
		int columna = table.getSelectedColumn(); // obtener columna seleccionada

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			vehiculos.get(fila).setNombre(nuevoNombre);
			table.setValueAt(nuevoNombre, fila, 1);
		} else if (columna == 2) {
			String nuevoColor = JOptionPane.showInputDialog("Ingrese nuevo color: ");
			vehiculos.get(fila).setNombre(nuevoColor);
			table.setValueAt(nuevoColor, fila, 2);
		} else if (columna == 3) {
			JOptionPane.showMessageDialog(null, "No es posible modificar el tipo de vehículo.");

		} else if (columna == 4) {
			// String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto
			// de residencia: ");
			// vehiculos.get(fila).setNombre(nuevoDptoResidencia);
			// table.setValueAt(nuevoDptoResidencia, fila, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			// try {
			// String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha:
			// ");
			// LocalDate nuevoDate = LocalDate.parse(nuevoDateString); // transforma el
			// String ingresado en un tipo
			// LocalDate
			// vehiculos.get(fila).setFechaNacimiento(nuevoDate);
			// table.setValueAt(nuevoDate, fila, 5);
			// } catch (Exception e2) {
			// JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use:
			// yyyy-mm-dd");
			// }
		}
	}

	// --------------- Método Eliminar Vehiculo Seleccionado --------------------

	public void eliminarPersona(ArrayList<Vehiculo> vehiculos) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		model.removeRow(fila); // elimina fila de la tabla
		vehiculos.remove(fila); // elima vehiculo en el arrayList
	}

	// --------------- Método Limpiar Tabla --------------------

	public void limpiarTabla() {
		int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i); // eliminar fila de la tabal en i
		}
	}

}
