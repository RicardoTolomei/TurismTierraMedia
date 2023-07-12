package model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import model.Enum.*;
import model.utils.InterfaceModel;

public class Atraccion implements Comparable<Atraccion>, InterfaceModel {

	private int id;
	private String nombre;
	private double costo;
	private double duracion;

	private int cupoActual = 0;
	private int cupoMaximo;

	private int posicionX;
	private int posicionY;
	private Map<String, String> errors;

	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	// CONSTRUCTOR SOLO USADO POR DAO
	public Atraccion(int id, String nombre, double costo, double duracion, int cupoActual, int cupoMaximo,
			int posicionX, int posicionY) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoActual = cupoActual;
		this.cupoMaximo = cupoMaximo;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	// Constructor por defecto
	public Atraccion(String nombre, double costo, double duracion, int cupoMaximo, int posicionX, int posicionY) {
		this.id = 0;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoMaximo = cupoMaximo;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

// Getters and Setters. ////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public int getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(int cupoActual) {
		this.cupoActual = cupoActual;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public ENUMTIPO getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO preferencia) {
		this.preferencia = preferencia;
	}
	
	public String getPreferenciaString() {
		if(this.preferencia.equals(ENUMTIPO.PAISAJE))
			return "Paisaje";
		if(this.preferencia.equals(ENUMTIPO.AVENTURA))
			return "Aventura";
		if(this.preferencia.equals(ENUMTIPO.DEGUSTACION))
			return "Degustacion";;
		return "SinDefinir";
	}
	
	public void setPreferenciaString(String preferencia) {
		if(preferencia.equals("PAISAJE")||preferencia.equals("paisaje")||preferencia.equals("Paisaje"))
			this.preferencia = ENUMTIPO.PAISAJE;
		if(preferencia.equals("AVENTURA")||preferencia.equals("aventura")||preferencia.equals("Aventura"))
			this.preferencia = ENUMTIPO.AVENTURA;
		if(preferencia.equals("DEGUSTACION")||preferencia.equals("degustacion")||preferencia.equals("Degustacion"))
			this.preferencia = ENUMTIPO.DEGUSTACION;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

// Calculos de Distancia. //////////////////////////////////////////////////////

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	@Override
	public int getPosicionX() {
		return posicionX;
	}

	@Override
	public int getPosicionY() {
		return posicionY;
	}

	@Override
	public double distance(InterfaceModel otro) {
		return sqrt(pow(this.posicionX - otro.getPosicionX(), 2) + pow(this.posicionY - otro.getPosicionY(), 2));
	}

// Funciones y utiles. /////////////////////////////////////////////////////////

	public boolean hayEspacio() {
		return cupoActual < cupoMaximo;
	}

	public void ocuparUnLugar() {
		cupoActual++;
	}

	public void liberarUnLugar() {
		if (cupoActual > 0) {
			cupoActual--;
		}
	}

	public boolean canHost(int i) {
		return cupoMaximo >= i;
	}

	public void host(int i) {
		this.cupoMaximo -= i;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duracion <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (cupoMaximo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}

// Overrides and comparate /////////////////////////////////////////////////////

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Atraccion atraccion = (Atraccion) o;
		return costo == atraccion.costo && Double.compare(atraccion.duracion, duracion) == 0
				&& cupoMaximo == atraccion.cupoMaximo && Objects.equals(nombre, atraccion.nombre)
				&& preferencia == atraccion.preferencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, costo, duracion, cupoMaximo, preferencia);
	}

	@Override
	public int compareTo(Atraccion atraccion) {
		if (this.costo == atraccion.getCosto()) {
			return Double.compare(this.duracion, atraccion.getDuracion());
		}
		return Double.compare(this.costo, atraccion.getCosto());
	}

	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", duracion=" + duracion
				+ ", cupoActual=" + cupoActual + ", cupoMaximo=" + cupoMaximo + ", posicionX=" + posicionX
				+ ", posicionY=" + posicionY + ", preferencia=" + preferencia + "]";
	}

}
