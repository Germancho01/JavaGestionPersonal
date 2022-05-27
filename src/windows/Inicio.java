package windows;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Persona;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Inicio(ArrayList<Persona> personas) {

		// ----- Panel -----
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ----- Label con imagen -----
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(Inicio.class.getResource("/images/logoAzulPerson.png")));
		lblImage.setBounds(106, 31, 186, 142);
		contentPane.add(lblImage);

		// ----- Botón Ingresar-----
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Formulario(personas);
				setVisible(false);
			}
		});
		btnIngresar.setBounds(118, 190, 130, 31);
		btnIngresar.setFocusable(false);
		contentPane.add(btnIngresar);

		// ----- Botón Salir-----
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(118, 274, 130, 31);
		btnSalir.setFocusable(false);
		contentPane.add(btnSalir);

		JButton btnEstadstica = new JButton("Estad\u00EDstica");
		btnEstadstica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard(personas);
				setVisible(false);
			}
		});
		btnEstadstica.setFocusable(false);
		btnEstadstica.setBounds(118, 232, 130, 31);
		contentPane.add(btnEstadstica);

		setTitle("Inicio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/images/logo_azulUTEC.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 367);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
