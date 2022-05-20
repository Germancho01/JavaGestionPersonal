package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Persona {
	
	public String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fechaNacimiento;
	public int idPersona;
	private ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	public Persona(Integer idPersona,String nombre, String apellido, String dptoResidencia, 
			Byte cantHijos, LocalDate fechaNacimiento, ArrayList<Vehiculo> vehiculos) {
		super();
		
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dptoResidencia = dptoResidencia;
		this.cantHijos = cantHijos;
		this.fechaNacimiento = fechaNacimiento;
		this.vehiculos = vehiculos;
	}
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "---Persona--- "
				+ "\nID Persona: " + idPersona  
				+ "\nDpto de Residencia: " + dptoResidencia 
				+ "\nCantidad de Hijos: " + cantHijos
				+ "\nFecha de Nacimiento: " + fechaNacimiento
				+ "\n"
				+ this.getVehiculos().toString()
				;
	}

	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDptoResidencia() {
		return dptoResidencia;
	}
	public void setDptoResidencia(String dptoResidencia) {
		this.dptoResidencia = dptoResidencia;
	}
	public Byte getCantHijos() {
		return cantHijos;
	}
	public void setCantHijos(Byte cantHijos) {
		this.cantHijos = cantHijos;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getId() {
		return idPersona;
	}
	public void setId(int id) {
		this.idPersona = id;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	
	
}
