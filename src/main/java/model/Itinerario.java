package model;

import java.util.Objects;

public class Itinerario {
	private int Id = 0;
	private final int IdUsuario;
	private final int IdAtraccion;

	String descripcion = "";

	// Constructor Standard
	public Itinerario(int IdUsuario, int IdAtraccion) {
		this.IdUsuario = IdUsuario;
		this.IdAtraccion = IdAtraccion;
	}

	// Constructor Standard
	public Itinerario(int IdUsuario, int IdAtraccion, String descripcion) {
		this.IdUsuario = IdUsuario;
		this.IdAtraccion = IdAtraccion;
	}

	// Constructor Utilizado por DAO
	public Itinerario(int Id, int IdUsuario, int IdAtraccion, String descripcion) {
		this.Id = Id;
		this.IdUsuario = IdUsuario;
		this.IdAtraccion = IdAtraccion;
		this.descripcion = descripcion;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public int getIdAtraccion() {
		return IdAtraccion;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, IdAtraccion, IdUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Id == other.Id && IdAtraccion == other.IdAtraccion && IdUsuario == other.IdUsuario;
	}

	// En el Sql la Id inicial es 1, por lo tnato si alguna Id de Itinerario ...
	// ... es Igual a 0 quiere decir que no se actualizo.

}
