package classes;

import java.time.LocalDate;

public class Persona {
	
	public String nombre, apellido, dptoResidencia;
	private Byte cantHijos;
	private LocalDate fechaNacimiento;
	public int idPersona;
	
	public Persona(Integer idPersona,String nombre, String apellido, String dptoResidencia, 
			Byte cantHijos, LocalDate fechaNacimiento) {
		super();
		
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dptoResidencia = dptoResidencia;
		this.cantHijos = cantHijos;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona  + ", dptoResidencia=" + dptoResidencia + ", cantHijos=" + cantHijos
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
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

	
	
}
