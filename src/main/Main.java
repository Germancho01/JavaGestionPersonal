package main;

import java.time.LocalDate;
import java.util.ArrayList;

import classes.Avion;
import classes.Barco;
import classes.Persona;
import classes.Vehiculo;
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
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		ArrayList<Avion> aviones = new ArrayList<Avion>();
		ArrayList<Barco> barcos = new ArrayList<Barco>();
		
		
		Persona p1 = new Persona(0, "Juan", "Perez", "Montevideo", (byte) 0, LocalDate.of(1996, 3, 1), vehiculos, barcos, aviones);
		Persona p2 = new Persona(1, "Roberto", "Salomez", "Colonia", (byte) 1, LocalDate.of(2001, 8, 5), vehiculos, barcos, aviones);

		
		Avion a1 = new Avion(0, "HGO-098", "Azul", p1, 567.43, 345);
		Avion a2 = new Avion(1, "YUI-056", "Rojo", p1, 462.53, 278);
		Avion a3 = new Avion(2, "RYU-234", "Verde", p1, 321.13, 106);
		Barco b1 = new Barco(0, "Barquito", "Rojo", p2, 567.89, 432.89);
		
		personas.add(p1);
		personas.add(p2);
		personas.get(0).getAviones().add(a1);
		personas.get(0).getAviones().add(a2);
		personas.get(0).getAviones().add(a3);
		personas.get(1).getBarcos().add(b1);

		new Inicio(personas);
	}
}
