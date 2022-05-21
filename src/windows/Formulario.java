package windows;

import javax.swing.JFrame;

import classes.Persona;
import swing.PanelAviones;
import swing.PanelBarcos;
import swing.PanelPersonas;
import swing.PanelVehiculos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import java.awt.SystemColor;

import javax.swing.JTabbedPane;

public class Formulario extends JFrame {

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

		JFrame frmFormulario = new JFrame(); // inicializar el frame
		frmFormulario.setTitle("Formulario"); // poner título al frame
		// poner icono en el frame
		frmFormulario.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Formulario.class.getResource("/images/logoAzulPerson.png")));
		frmFormulario.getContentPane().setForeground(SystemColor.textHighlight); // color del JPane
		frmFormulario.setBounds(0, 0, 677, 700); // tamaño del frame
		frmFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esconder ventana al apretar cerrar
		frmFormulario.setLocationRelativeTo(null); // aparecer frame en el centro de la pantalla
		frmFormulario.setVisible(true); // hacer visible el frame
		frmFormulario.setResizable(false);
		frmFormulario.getContentPane().setLayout(null);

		// --------------- Panel --------------------

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(26, 11, 613, 588);
		frmFormulario.getContentPane().add(tabbedPane);

		PanelPersonas panelPersonas = new PanelPersonas(personas);
		tabbedPane.addTab("Personas", null, panelPersonas, null);

		PanelVehiculos panelVehiculos = new PanelVehiculos();
		tabbedPane.addTab("Vehículos", null, panelVehiculos, null);

		// --------------- Panel 1 --------------------

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(26, 278, 553, 238);
		panelVehiculos.add(tabbedPane_1);

		PanelBarcos panelBarcos = new PanelBarcos();
		tabbedPane_1.addTab("Barcos", null, panelBarcos, null);

		PanelAviones panelAviones = new PanelAviones();
		tabbedPane_1.addTab("Aviones", null, panelAviones, null);

		// --------------- Botón Volver --------------------

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(546, 610, 89, 23);
		frmFormulario.getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inicio(personas);
				frmFormulario.setVisible(false);
			}
		});
		btnVolver.setFocusable(false);

	}

}
