package main;

import java.util.ArrayList;

import classes.Persona;
import windows.Inicio;

/**
 * 
 * ProyectoS1 v2.0 Grupo TIX: Anna Benitez, Germ�n Dom�nguez, Leonardo Cutraro,
 * Agustina Mart�nez, Pedro Serna
 * 
 */

public class Main {

	/**
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {
		ArrayList<Persona> personas = new ArrayList<Persona>();

		new Inicio(personas);
	}
}
