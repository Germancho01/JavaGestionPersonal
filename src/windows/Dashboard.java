package windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import classes.Persona;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Dashboard(ArrayList<Persona> personas) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		int countArtigas = 0;
		int countCanelones = 0;
		int countCerroLargo = 0;
		int countColonia = 0;
		int countDurazno = 0;
		int countFlores = 0;
		int countFlorida = 0;
		int countLavalleja = 0;
		int countMaldonado = 0;
		int countMontevideo = 0;
		int countPaysandu = 0;
		int countRioNegro = 0;
		int countRivera = 0;
		int countRocha = 0;
		int countSalto = 0;
		int countSanJose = 0;
		int countSoriano = 0;
		int countTacuarembo = 0;
		int countTreintaYTres = 0;

		for (Persona persona : personas) {

			switch (persona.getDptoResidencia()) {
			case "Artigas": {
				countArtigas++;
				break;
			}
			case "Canelones": {
				countCanelones++;
				break;
			}
			case "Cerro Largo": {
				countCerroLargo++;
				break;
			}
			case "Colonia": {
				countColonia++;
				break;
			}
			case "Durazno": {
				countDurazno++;
				break;
			}
			case "Flores": {
				countFlores++;
				break;
			}
			case "Florida": {
				countFlorida++;
				break;
			}
			case "Lavalleja": {
				countLavalleja++;
				break;
			}
			case "Maldonado": {
				countMaldonado++;
				break;
			}
			case "Montevideo": {
				countMontevideo++;
				break;
			}
			case "Paysandú": {
				countPaysandu++;
				break;
			}
			case "Rio Negro": {
				countRioNegro++;
				break;
			}
			case "Rivera": {
				countRivera++;
				break;
			}
			case "Rocha": {
				countRocha++;
				break;
			}
			case "Salto": {
				countSalto++;
				break;
			}
			case "San José": {
				countSanJose++;
				break;
			}
			case "Soriano": {
				countSoriano++;
				break;
			}
			case "Tacuarembó": {
				countTacuarembo++;
				break;
			}
			case "Treinta y Tres": {
				countTreintaYTres++;
				break;
			}

			}

		}

		DefaultCategoryDataset datos = new DefaultCategoryDataset();
		datos.setValue(countArtigas, "Personas", "Artigas");
		datos.setValue(countCanelones, "Personas", "Canelones");
		datos.setValue(countCerroLargo, "Personas", "Cerro Largo");
		datos.setValue(countColonia, "Personas", "Colonia");
		datos.setValue(countDurazno, "Personas", "Durazno");
		datos.setValue(countFlores, "Personas", "Flores");
		datos.setValue(countFlorida, "Personas", "Florida");
		datos.setValue(countLavalleja, "Personas", "Lavalleja");
		datos.setValue(countMaldonado, "Personas", "Maldonado");
		datos.setValue(countMontevideo, "Personas", "Montevideo");
		datos.setValue(countPaysandu, "Personas", "Paysandú");
		datos.setValue(countRioNegro, "Personas", "Rio Negro");
		datos.setValue(countRivera, "Personas", "Rivera");
		datos.setValue(countRocha, "Personas", "Rocha");
		datos.setValue(countSalto, "Personas", "Salto");
		datos.setValue(countSanJose, "Personas", "San José");
		datos.setValue(countSoriano, "Personas", "Soriano");
		datos.setValue(countTacuarembo, "Personas", "Tacuarembó");
		datos.setValue(countTreintaYTres, "Personas", "Treinta y Tres");

		JFreeChart chartPuntos = ChartFactory.createBarChart(

				"Personas vs Departamentos", "Departamentos", "Personas", datos, PlotOrientation.HORIZONTAL, true, true,
				false);

		ChartPanel chartPanel = new ChartPanel(chartPuntos);
		chartPanel.setPreferredSize(new Dimension(600, 400));
		add(chartPanel);
	}

}
