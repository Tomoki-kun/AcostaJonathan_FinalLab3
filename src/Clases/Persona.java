package Clases;

import java.io.Serializable;
import java.util.Objects;

public class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idStatic;
	private int ID;
	private String Apellido;
	private String Nombre;
	private String Patente;
	public Persona(String apellido, String nombre, String patente) {
		ID =  idStatic++;
		Apellido = apellido;
		Nombre = nombre;
		Patente = patente;
	}
	
	public Persona() {
		super();
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPatente() {
		return Patente;
	}
	public void setPatente(String patente) {
		Patente = patente;
	}

	public static void setIdStatic(int idStatic) {
		Persona.idStatic = idStatic;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Apellido, ID, Nombre, Patente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(Apellido, other.Apellido) && ID == other.ID && Objects.equals(Nombre, other.Nombre)
				&& Objects.equals(Patente, other.Patente);
	}
	
	
}
