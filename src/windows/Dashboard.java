package windows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import classes.Persona;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Dashboard(JComboBox<String> departamentos, ArrayList<Persona> personas) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/logo_barras.png")));
		setTitle("Estad\u00EDsticas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1172, 720);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		int contadorVehiculos = 0;
		for (Persona persona : personas) {
			contadorVehiculos += persona.getVehiculos().size();
		}
		double promedioVehiculos = (double) contadorVehiculos / (double) personas.size();

		// ------- Graficas ---------------

		JFreeChart barChart = getBarChart(departamentos, personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(SystemColor.activeCaption);
		chartPanel.setBounds(10, 11, 680, 659);
		chartPanel.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel);
		chartPanel.setLayout(null);

		JFreeChart pieChart = getPieChart(personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel1 = new ChartPanel(pieChart);
		chartPanel1.setBackground(SystemColor.activeCaption);
		chartPanel1.setBounds(750, 68, 377, 336);
		chartPanel1.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel1);
		chartPanel.setLayout(null);

		JLabel lblCantidadPersonas = new JLabel("Cantidad de personas ingresadas: " + personas.size());
		lblCantidadPersonas.setBounds(769, 453, 272, 21);
		lblCantidadPersonas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblCantidadPersonas);

		JLabel lblCantidadVehiculos = new JLabel("Cantidad de vehículos ingresados: " + contadorVehiculos);
		lblCantidadVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidadVehiculos.setBounds(769, 485, 272, 21);
		contentPane.add(lblCantidadVehiculos);

		JLabel lblPromedioVehiculos = new JLabel("Promedio de vehículos por persona: 0");
		lblPromedioVehiculos.setBounds(769, 517, 272, 21);
		lblPromedioVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPromedioVehiculos.setText("Promedio de vehículos por persona: " + String.format("%.2f", promedioVehiculos));
		contentPane.add(lblPromedioVehiculos);
	}

	public JFreeChart getBarChart(JComboBox<String> departamentos, ArrayList<Persona> personas) {

		ArrayList<Integer> contadorDepartamentos = new ArrayList<Integer>();

		DefaultCategoryDataset datos = new DefaultCategoryDataset();

		Integer[] frecuencia = new Integer[19];
		Integer[] frecuenciaVehiculos = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (Persona persona : personas) {
			departamentos.setSelectedItem(persona.getDptoResidencia());
			int i = departamentos.getSelectedIndex();
			contadorDepartamentos.add(i);

			frecuenciaVehiculos[i] += persona.getVehiculos().size();

		}

		for (int i = 0; i < frecuencia.length; i++) {
			frecuencia[i] = Collections.frequency(contadorDepartamentos, i);
			datos.setValue(frecuencia[i], "Personas", departamentos.getItemAt(i));
			datos.setValue(frecuenciaVehiculos[i], "Vehiculos", departamentos.getItemAt(i));

		}

		JFreeChart chart = ChartFactory.createBarChart("Personas y vehículos por departamento", "Departamentos",
				"Personas", datos, PlotOrientation.HORIZONTAL, true, false, false);

		chart.setBorderVisible(false);

		CategoryPlot cplot = (CategoryPlot) chart.getPlot();
		cplot.setBackgroundPaint(SystemColor.window); // change background color

		// set bar chart color

		((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());

		BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();
		r.setSeriesPaint(0, SystemColor.activeCaption);

		return chart;
	}

	public JFreeChart getPieChart(ArrayList<Persona> personas) {

		var datos = new DefaultPieDataset();

		int contadorBarcos = 0;
		int contadorAviones = 0;

		for (Persona persona : personas) {
			contadorBarcos += persona.getBarcos().size();
			contadorAviones += persona.getAviones().size();
		}

		datos.setValue("Barcos", contadorBarcos);
		datos.setValue("Aviones", contadorAviones);

		JFreeChart chart = ChartFactory.createPieChart("Aviones vs Barcos", datos, true, false, false);

		chart.setBorderVisible(false);

		Plot plot = chart.getPlot();
		plot.setBackgroundPaint(SystemColor.window);

		return chart;
	}
}
