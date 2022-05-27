package windows;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.time.LocalDate;
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
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 843, 712);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblPromedioVehiculos = new JLabel("Promedio Vehiculos");
		lblPromedioVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPromedioVehiculos.setBounds(72, 409, 272, 21);
		contentPane.add(lblPromedioVehiculos);

		int contadorVehiculos = 0;
		for (Persona persona : personas) {
			contadorVehiculos += persona.getVehiculos().size();
		}
		int promedioVehiculos = contadorVehiculos / personas.size();

		lblPromedioVehiculos.setText("Promedio Vehículos por persona: " + promedioVehiculos);

		// ------- Graficas ---------------

		JFreeChart barChart = getBarChart(departamentos, personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(SystemColor.activeCaption);
		chartPanel.setBounds(10, 11, 574, 351);
		chartPanel.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel);
		chartPanel.setLayout(null);

		JFreeChart pieChart = getPieChart(personas);
		contentPane.setLayout(null);

		ChartPanel chartPanel1 = new ChartPanel(pieChart);
		chartPanel1.setBackground(SystemColor.activeCaption);
		chartPanel1.setBounds(393, 388, 424, 230);
		chartPanel1.setPreferredSize(new Dimension(400, 200));
		getContentPane().add(chartPanel1);
		chartPanel.setLayout(null);

	}

	public JFreeChart getBarChart(JComboBox<String> departamentos, ArrayList<Persona> personas) {

		ArrayList<Integer> contadorDepartamentos = new ArrayList<Integer>();

		DefaultCategoryDataset datos = new DefaultCategoryDataset();

		Integer[] frecuencia = new Integer[19];

		for (Persona persona : personas) {
			departamentos.setSelectedItem(persona.getDptoResidencia());
			contadorDepartamentos.add(departamentos.getSelectedIndex());
		}

		for (int i = 0; i < frecuencia.length; i++) {
			frecuencia[i] = Collections.frequency(contadorDepartamentos, i);
			datos.setValue(frecuencia[i], "Personas", departamentos.getItemAt(i));

		}

		JFreeChart chart = ChartFactory.createBarChart("Personas por departamento", "Departamentos", "Personas", datos,
				PlotOrientation.HORIZONTAL, false, false, false);

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

	public JFreeChart getLineChart(ArrayList<Persona> personas) {

		ArrayList<Integer> edades = new ArrayList<Integer>();

		var series = new XYSeries("Vehiculos");
		for (Persona persona : personas) {
			edades.add(persona.getFechaNacimiento().getYear() - LocalDate.now().getYear());

		}

		// series.add(edad, persona.getVehiculos().size());

		var dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		JFreeChart chart = ChartFactory.createXYLineChart("Vehiculos promedio por edad", "Edad", "Vehiculos", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		var renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Vehiculos promedios por edad", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}
}
