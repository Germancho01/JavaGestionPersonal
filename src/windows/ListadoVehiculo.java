package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
import exceptions.CellNoSelectedException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ListadoVehiculo extends JFrame {

	private String[] datos = new String[5];

	private JFrame frame;

	private JTable tableBarcos;
	private JTable tableAviones;
	private DefaultTableModel modelBarcos;
	private DefaultTableModel modelAviones;
	private JButton btnVolver;

	/**
	 * 
	 * Create the application.
	 * 
	 */

	public ListadoVehiculo(ArrayList<Vehiculo> vehiculos, ArrayList<Barco> barcos, ArrayList<Avion> aviones,  ArrayList<Persona> personas) {

		initialize(vehiculos, barcos, aviones, personas);

		limpiarTabla(0);
		limpiarTabla(1);

		// Cargar todos los barcos de la persona en la tabla
		for (int i = 0; i < barcos.size(); i++) {
			cargarBarco(barcos, i);
		}
		
		// Cargar todos los aviones de la persona en la tabla
		for (int i = 0; i < aviones.size(); i++) {
			cargarAvion(aviones, i);
		}

	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * 
	 */

	private void initialize(ArrayList<Vehiculo> vehiculos, ArrayList<Barco> barcos, ArrayList<Avion> aviones,  ArrayList<Persona> personas) {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		// --------------- Label --------------------
		
		JLabel lblNewLabel = new JLabel("Barcos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(29, 32, 130, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAviones = new JLabel("Aviones");
		lblAviones.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblAviones.setBounds(29, 261, 130, 32);
		frame.getContentPane().add(lblAviones);

		// --------------- Table Barcos--------------------

		JScrollPane scrollPane = new JScrollPane(); // nuevo scroll pane
		scrollPane.setBounds(29, 75, 530, 154); // setea las coordenadas de la tabla y su tamaño
		frame.getContentPane().add(scrollPane); // agrega el scroll pane al panel

		tableBarcos = new JTable(); // nueva tabla
		// action doble click del mouse
		tableBarcos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableBarcos.getSelectedRow();
				if (e.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, vehiculos.get(fila).toString());
				}
			}
		});

		// se define el modelo por defecto de la tabla,
		// se agrega nombre a las columnas y preferencias de la tabla
		tableBarcos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Color", "Eslora", "Manga"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableBarcos.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableBarcos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableBarcos.getColumnModel().getColumn(3).setPreferredWidth(78);
		tableBarcos.getColumnModel().getColumn(4).setPreferredWidth(76);

		scrollPane.setViewportView(tableBarcos); // se agrega la tabla al scroll pane

		modelBarcos = (DefaultTableModel) tableBarcos.getModel(); // obtener el modelo de la tabla para poder añadir datos a la
														// misma
		
		// --------------- Table Aviones --------------------
		
		JScrollPane scrollPane1 = new JScrollPane(); // nuevo scroll pane
		scrollPane1.setBounds(29, 304, 530, 154); // setea las coordenadas de la tabla y su tamaño
		frame.getContentPane().add(scrollPane1); // agrega el scroll pane al panel
		
		tableAviones = new JTable();
		tableAviones.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Color", "Longitud", "Cantidad Pasajeros"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableAviones.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableAviones.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableAviones.getColumnModel().getColumn(3).setPreferredWidth(105);
		tableAviones.getColumnModel().getColumn(4).setPreferredWidth(140);
		scrollPane1.setViewportView(tableAviones);

		modelAviones = (DefaultTableModel) tableAviones.getModel(); // obtener el modelo de la tabla para poder añadir
																	// datos a la misma

		// --------------- Botón Eliminar Barcos--------------------

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarBarco(barcos);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar.setBounds(360, 41, 89, 23);
		btnEliminar.setFocusable(false);
		frame.getContentPane().add(btnEliminar);
		
		// --------------- Botón Eliminar Aviones--------------------
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar registros con la excepción de celda no seleccionada
				try {
					eliminarAvion(aviones);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnEliminar_1.setFocusable(false);
		btnEliminar_1.setBounds(360, 270, 89, 23);
		frame.getContentPane().add(btnEliminar_1);

		// --------------- Botón Eliminar Todo Barcos--------------------
		
		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(0);
				barcos.clear(); // limpia el arrayList vehiculos
			}
		});
		btnEliminarTodo.setBounds(235, 41, 115, 23);
		btnEliminarTodo.setFocusable(false);
		frame.getContentPane().add(btnEliminarTodo);
		
		// --------------- Botón Eliminar Todo Aviones--------------------
		
		JButton btnEliminarTodo_1 = new JButton("Eliminar Todo");
		btnEliminarTodo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(1);
				aviones.clear(); // limpia el arrayList vehiculos
			}
		});
		btnEliminarTodo_1.setFocusable(false);
		btnEliminarTodo_1.setBounds(235, 270, 115, 23);
		frame.getContentPane().add(btnEliminarTodo_1);

		// --------------- Botón Modificar Barcos--------------------

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatosBarco(barcos);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnModificar.setBounds(459, 41, 89, 23);
		btnModificar.setFocusable(false);
		frame.getContentPane().add(btnModificar);
		
		// --------------- Botón Modificar Aviones--------------------
		
		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modificar datos con la excepción de celda no seleccionada
				try {
					modificarDatosAvion(aviones);
				} catch (CellNoSelectedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnModificar_1.setFocusable(false);
		btnModificar_1.setBounds(459, 270, 89, 23);
		frame.getContentPane().add(btnModificar_1);

		// --------------- Botón Volver --------------------
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Listado(personas);
				frame.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);
		btnVolver.setBounds(29, 491, 115, 23);
		frame.getContentPane().add(btnVolver);
		
		

	}

	/**
	 * 
	 * Create methods.
	 * 
	 */

	// --------------- Método Cargar Barcos --------------------

	public void cargarBarco(ArrayList<Barco> barcos, int i) {
			
			datos[0] = Integer.toString(barcos.get(i).getIdVehiculo());
			datos[1] = barcos.get(i).getNombre();
			datos[2] = barcos.get(i).getColor();
			datos[3] = Double.toString(barcos.get(i).getEslora());
			datos[4] = Double.toString(barcos.get(i).getManga());
			
			modelBarcos.addRow(datos);
		}

	//--------------- Método Cargar Aviones --------------------

	public void cargarAvion(ArrayList<Avion> aviones, int i) {
			
		datos[0] = Integer.toString(aviones.get(i).getIdVehiculo());
		datos[1] = aviones.get(i).getNombre();
		datos[2] = aviones.get(i).getColor();
		datos[3] = Double.toString(aviones.get(i).getLongitud());
		datos[4] = Double.toString(aviones.get(i).getCantPasajeros());
		
		modelAviones.addRow(datos);
		}
		
	// --------------- Método Modificar Dato Barcos--------------------

	public void modificarDatosBarco(ArrayList<Barco> barcos) throws CellNoSelectedException {

		int fila = tableBarcos.getSelectedRow(); // obtener celda seleccionada
		int columna = tableBarcos.getSelectedColumn(); // obtener columna seleccionada

		// Excepción en caso de que no se seleccione ninguna fila
		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		// modificar los datos dependiendo de la columna seleccionada
		if (columna == 1) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
			if (nuevoNombre != null) {
				barcos.get(fila).setNombre(nuevoNombre);
				tableBarcos.setValueAt(nuevoNombre, fila, 1);
			}
			
		} else if (columna == 2) {
			String nuevoColor = JOptionPane.showInputDialog("Ingrese nuevo color: ");
			if (nuevoColor != null) {
				barcos.get(fila).setNombre(nuevoColor);
				tableBarcos.setValueAt(nuevoColor, fila, 2);
			}
			
		} else if (columna == 3) {
			try {
				String nuevoEsloraString = JOptionPane.showInputDialog("Ingrese nuevo eslora: ");
				if (nuevoEsloraString != null) {
					barcos.get(fila).setEslora(Double.parseDouble(nuevoEsloraString));
					tableBarcos.setValueAt(nuevoEsloraString, fila, 3);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}

		} else if (columna == 4) {
			try {
				String nuevoMangaString = JOptionPane.showInputDialog("Ingrese nuevo manga: ");
				if (nuevoMangaString != null) {
					barcos.get(fila).setEslora(Double.parseDouble(nuevoMangaString));
					tableBarcos.setValueAt(nuevoMangaString, fila, 4);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
			}
		} 
	}
	
	// --------------- Método Modificar Dato Aviones--------------------

		public void modificarDatosAvion(ArrayList<Avion> aviones) throws CellNoSelectedException {

			int fila = tableAviones.getSelectedRow(); // obtener celda seleccionada
			int columna = tableAviones.getSelectedColumn(); // obtener columna seleccionada

			// Excepción en caso de que no se seleccione ninguna fila
			if (fila < 0) {
				throw new CellNoSelectedException();
			}

			// modificar los datos dependiendo de la columna seleccionada
			if (columna == 1) {
				String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre: ");
				if (nuevoNombre != null) {
					aviones.get(fila).setNombre(nuevoNombre);
					tableAviones.setValueAt(nuevoNombre, fila, 1);
				}
			} else if (columna == 2) {
				String nuevoColor = JOptionPane.showInputDialog("Ingrese nuevo color: ");
				if (nuevoColor != null) {
					aviones.get(fila).setNombre(nuevoColor);
					tableAviones.setValueAt(nuevoColor, fila, 2);
				}
			} else if (columna == 3) {
				try {
					String nuevoEsloraString = JOptionPane.showInputDialog("Ingrese nueva longitud: ");
					if (nuevoEsloraString != null) {
						aviones.get(fila).setLongitud(Double.parseDouble(nuevoEsloraString));
						tableAviones.setValueAt(nuevoEsloraString, fila, 3);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
				}

			} else if (columna == 4) {
				try {
					String nuevoCantPasajerosString = JOptionPane.showInputDialog("Ingrese nueva Cantidad de Pasajeros: ");
					if (nuevoCantPasajerosString != null) {
						aviones.get(fila).setCantPasajeros(Integer.parseInt(nuevoCantPasajerosString));
						tableAviones.setValueAt(nuevoCantPasajerosString, fila, 4);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Formato de dato inválido.");
				}
			} 
		}

	// --------------- Método Eliminar Barco Seleccionado --------------------

	public void eliminarBarco(ArrayList<Barco> barcos) throws CellNoSelectedException {
		int fila = tableBarcos.getSelectedRow();

		if (fila < 0) {
			throw new CellNoSelectedException();
		}

		modelBarcos.removeRow(fila); // elimina fila de la tabla
		barcos.remove(fila); // elima barco en el arrayList
	}
	
	// --------------- Método Eliminar Avion Seleccionado --------------------

		public void eliminarAvion(ArrayList<Avion> aviones) throws CellNoSelectedException {
			int fila = tableAviones.getSelectedRow();

			if (fila < 0) {
				throw new CellNoSelectedException();
			}

			modelAviones.removeRow(fila); // elimina fila de la tabla
			aviones.remove(fila); // elima vehiculo en el arrayList
		}

	// --------------- Método Limpiar Tabla --------------------

	public void limpiarTabla(int tabla) {
		int filas = tableBarcos.getRowCount(); // obtener cantidad de filas de la tabla
		int filas1 = tableAviones.getRowCount(); // obtener cantidad de filas de la tabla

		if (tabla == 0) {
			for (int i = filas - 1; i >= 0; i--) {
				modelBarcos.removeRow(i); // eliminar fila de la tabal en i
			}
		} else if (tabla == 1) {
			for (int i = filas1 - 1; i >= 0; i--) {
				modelAviones.removeRow(i); // eliminar fila de la tabal en i
			}
		}

	}
}
