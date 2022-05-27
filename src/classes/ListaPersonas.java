package classes;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ListaPersonas {

	public ArrayList<Persona> lista = new ArrayList<Persona>();

	public ListaPersonas(String filename) {
		cargarLista(filename);
	}

	private void cargarLista(String filename) {

		try {
			ArrayList<String> listaResgistro = null;

			// Aqui se leen todas las lineas del archivo.
			try {
				listaResgistro = new ArrayList<String>(Files.readAllLines(Paths.get(filename)));
				cargarPersona(listaResgistro);
			} catch (MalformedInputException e) {
				JOptionPane.showMessageDialog(null, "El formato del archivo no es correcto.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Aqui se parsea cada linea buscando la coma que separa cada dato
	// y se crea una instancia de producto para cada linea del archivo
	private void cargarPersona(ArrayList<String> listaRegistro) {
		for (String s : listaRegistro) {
			int ind = s.indexOf(",");
			String nombre = s.substring(0, ind);

			int ultimo = s.length();
			s = s.substring(ind + 1, ultimo);

			ind = s.indexOf(",");
			String apellido = s.substring(0, ind);

			ultimo = s.length();
			s = s.substring(ind + 1, ultimo);

			ind = s.indexOf(",");
			String dptoResidencia = s.substring(0, ind);

			ultimo = s.length();
			s = s.substring(ind + 1, ultimo);

			ind = s.indexOf(",");
			Byte cantHijos = Byte.parseByte(s.substring(0, ind));

			LocalDate fechaNacimiento = LocalDate.parse(s.substring(ind + 1));

			ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
			ArrayList<Barco> barcos = new ArrayList<Barco>();
			ArrayList<Avion> aviones = new ArrayList<Avion>();

			lista.add(new Persona(Persona.getId(), nombre, apellido, dptoResidencia, cantHijos, fechaNacimiento,
					vehiculos, barcos, aviones));

		}
	}

}
