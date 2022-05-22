package main;

import java.util.ArrayList;

import classes.Barco;
import classes.Persona;
import windows.Inicio;

/**
 * 
 * ProyectoS1 v1.8 Grupo TIX: Anna Benitez, Germán Domínguez, Leonardo Cutraro,
 * Agustina Martínez, Pedro Serna
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
