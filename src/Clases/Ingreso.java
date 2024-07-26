package Clases;

import java.io.Serializable;
import java.util.Objects;

public class Ingreso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idStatic;
	private int IDIngreso;
	private String Patente;
	private String FechaHora;
	public Ingreso( String patente, String fechaHora) {
		IDIngreso =  idStatic++;
		Patente = patente;
		FechaHora = fechaHora;
	}
	
	public Ingreso() {
		super();
	}

	public int getIDIngreso() {
		return IDIngreso;
	}
	public void setIDIngreso(int iDIngreso) {
		IDIngreso = iDIngreso;
	}
	public String getPatente() {
		return Patente;
	}
	public void setPatente(String patente) {
		Patente = patente;
	}
	public String getFechaHora() {
		return FechaHora;
	}
	public void setFechaHora(String fechaHora) {
		FechaHora = fechaHora;
	}

	public static void setIdStatic(int idStatic) {
		Ingreso.idStatic = idStatic;
	}
	@Override
	public int hashCode() {
		return Objects.hash(FechaHora, IDIngreso, Patente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingreso other = (Ingreso) obj;
		return Objects.equals(FechaHora, other.FechaHora) && IDIngreso == other.IDIngreso
				&& Objects.equals(Patente, other.Patente);
	}
	
	
}
