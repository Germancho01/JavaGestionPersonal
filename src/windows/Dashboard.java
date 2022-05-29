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

		// --- Frame ---
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

		// Se cuentan la cantidad de vehículos ingresados recorriendo la lista de
		// personas
		// y sumando al contador el tamaño de la lista de vehículos de la persona.
		int contadorVehiculos = 0;
		for (Persona persona : personas) {
			contadorVehiculos += persona.getVehiculos().size();
		}

		// Promedio vehículos
		double promedioVehiculos = (double) contadorVehiculos / (double) personas.size();

		// --- Labels ---

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

		// ------- Gráfica de barras ---------------

		JFreeChart barChart = getBarChart(departamentos, personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(SystemColor.activeCaption);
		chartPanel.setBounds(10, 11, 680, 659);
		chartPanel.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel);
		chartPanel.setLayout(null);

		// ------- Gráfica de torta ---------------

		JFreeChart pieChart = getPieChart(personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel1 = new ChartPanel(pieChart);
		chartPanel1.setBackground(SystemColor.activeCaption);
		chartPanel1.setBounds(750, 68, 377, 336);
		chartPanel1.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel1);
		chartPanel.setLayout(null);
	}

	/*
	 * Método crear un gráfico de barras con la cantidad de personas y vehículos por
	 * departamento.
	 * 
	 * El método tiene como parámetros de entrada un comboBox con los datos de los
	 * departamentos y una lista de personas.
	 * 
	 * Para graficar las personas por departamento. Se recorre la lista de personas
	 * y guarda el índice del departamento de cada persona en una lista contador de
	 * departamentos. Luego se recorre un array de enteros con 19 elementos y se
	 * ingresa la frecuencia de cada departamento obtenida del contador de
	 * departamentos. Después se grafican los datos de departamento y frecuencia.
	 * 
	 * Para graficar los vegículos por departamento. Se inicializa un array de
	 * enteros con 19 valores. Luego se recorre la lista de personas y se suma la
	 * cantidad de vehículos de la misma al array frecuenciaVehículos en el índice
	 * del departamento correspondiente. Después se grafican los datos de
	 * departamento y frecuencia vehículo.
	 */
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

	/*
	 * Método crear un gráfico de tortas con el porcentaje de barcos y aviones.
	 * 
	 * Elmétodo tiene como parámetro de entrada la lista de personas.
	 * 
	 * Se recorre la lista de personas y se suma al contador de barcos con la
	 * cantidad de barcos que tiene esa persona en la lista de barcos. Lo mismo
	 * ocurre con los aviones. Luego se grafican los dos contadores con le etiqueta
	 * correspondiente.
	 */
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
