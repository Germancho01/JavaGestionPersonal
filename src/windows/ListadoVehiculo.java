package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoVehiculo extends JFrame {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String[] datos = new String[6];
	private JCheckBox chckbxConHijos;
	private JCheckBox chckbxMayoresDeEdad;

	/**
	 * Create the application.
	 */
	public ListadoVehiculo(ArrayList<Vehiculo> vehiculos) {

		initialize(vehiculos);

		limpiarTabla();

		// Cargar todas las personas en la tabla
		for (int i = 0; i < vehiculos.size(); i++) {
			cargarPersona(vehiculos, i);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Vehiculo> vehiculos) {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 622);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// --------------- Table --------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 75, 530, 280); // setea las coordenadas de la tabla y su tamaño
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
				"ID", "Nombre", "Color", "Tipo", "Atributo 1", "Atributo 2"
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
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);

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
				vehiculos.clear(); // limpia el mapa de personas
			}
		});
		btnEliminarTodo.setBounds(39, 366, 115, 23);
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

		// --------------- CheckBox Mayores de Edad --------------------

		chckbxMayoresDeEdad = new JCheckBox("Mostrar solo mayores de edad");
		chckbxMayoresDeEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//filtros(vehiculos);
			}
		});
		chckbxMayoresDeEdad.setBounds(29, 535, 229, 23);
		chckbxMayoresDeEdad.setFocusable(false);
		frame.getContentPane().add(chckbxMayoresDeEdad);

		// --------------- CheckBox Solo con Hijos --------------------

		chckbxConHijos = new JCheckBox("Mostrar solo personas con hijos");
		chckbxConHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//filtros(vehiculos);
			}
		});
		chckbxConHijos.setBounds(283, 535, 229, 23);
		chckbxConHijos.setFocusable(false);
		frame.getContentPane().add(chckbxConHijos);
	}

	/**
	 * Create methods.
	 */

	// --------------- Método Cargar Vehiculo --------------------

	public void cargarPersona(ArrayList<Vehiculo> vehiculos, int i) {

		datos[0] = Integer.toString(vehiculos.get(i).getIdVehiculo());
		datos[1] = vehiculos.get(i).getNombre();
		datos[2] = vehiculos.get(i).getColor();
		datos[3] = null;
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
			vehiculos.get(fila).setNombre(nuevoNombre); // cambia el valor de la persona en el mapa personas
			table.setValueAt(nuevoNombre, fila, 1); // setea el nuevo valor en la tabla, en la fila y columna
													// seleccionadas
		} else if (columna == 2) {
			JOptionPane.showMessageDialog(null, "No es posible cambiar el tipo de vehículo.");
		} else if (columna == 3) {
			try {
				//Byte nuevoCantHijos = Byte.parseByte(JOptionPane.showInputDialog("Ingrese cantidad de hijos: "));
				//vehiculos.get(fila).setCantHijos(nuevoCantHijos);
				//table.setValueAt(nuevoCantHijos, fila, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			//String nuevoDptoResidencia = JOptionPane.showInputDialog("Ingrese nuevo dpto de residencia: ");
			//vehiculos.get(fila).setNombre(nuevoDptoResidencia);
			//table.setValueAt(nuevoDptoResidencia, fila, 4);
		} else if (columna == 5) {
			// modifica la fecha de nacimiento con un Exeption,
			// en caso de que el formato de la fecha no sea correcto
			//try {
				//String nuevoDateString = JOptionPane.showInputDialog("Ingrese nueva fecha: ");
				//LocalDate nuevoDate = LocalDate.parse(nuevoDateString); // transforma el String ingresado en un tipo
																		// LocalDate
				//vehiculos.get(fila).setFechaNacimiento(nuevoDate);
				//table.setValueAt(nuevoDate, fila, 5);
			//} catch (Exception e2) {
				//JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use: yyyy-mm-dd");
			//}
		}
	}

	// --------------- Método Eliminar Vehiculo Seleccionado --------------------

	public void eliminarPersona(ArrayList<Vehiculo> vehiculos) throws CellNoSelectedException {
		int fila = table.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		model.removeRow(fila); // elimina fila de la tabla
		vehiculos.remove(fila); // elima persona en el arrayList
	}

	// --------------- Método Limpiar Tabla --------------------

	public void limpiarTabla() {
		int filas = table.getRowCount(); // obtener cantidad de filas de la tabla
		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i); // eliminar fila de la tabal en i
		}
	}

}
