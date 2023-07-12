package model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.HashMap;

import model.Enum.*;
import model.utils.InterfaceModel;

public class Promocion implements Comparable<Promocion>, InterfaceModel {

	private final int Id;
	private String nombre;
	private int tipoDePromocion;
	private int descuentoPorcentual;

	private double costoTotal;
	private double duracionTotal;
	private Map<String, String> errors;

	List<Atraccion> atracciones = new ArrayList<>();
	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	// TIPO DE PROMOCION 1)PAbsoluta, 2)PPorcentual, 3) PAxB

	// EL ID SOLO SE UTILIZA POR DAO
	public Promocion(int Id, String nombre, int tipoDePromocion, double costo, int descuentoPorcentual,
			List<Atraccion> atracciones) {

		this.Id = Id;
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.descuentoPorcentual = descuentoPorcentual;

		this.atracciones = atracciones;

		calcularDuracion();
		buscarPreferencia();
		calcularCosto(costo);
	}

	public Promocion() {
		this.Id = 0;
		this.tipoDePromocion = 0;
		this.nombre = "SinNombre";
		this.descuentoPorcentual = 0;
	}

////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return Id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTipoDePromocion() {
		return tipoDePromocion;
	}

	public int getDescuentoPorcentual() {
		return descuentoPorcentual;
	}

	public double getCosto() {
		return costoTotal;
	}

	public void setCosto(double costo) {
		this.costoTotal = costo;
	}

	public double getDuracion() {
		return duracionTotal;
	}

	public void setDuracion(double duracion) {
		this.duracionTotal = duracion;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public ENUMTIPO getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO preferencia) {
		this.preferencia = preferencia;
	}

////////////////////////////////////////////////////////////////////////////////

	public void agregarAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
		calcularDuracion();
		buscarPreferencia();
		calcularCosto(costoTotal);
	}

	private void calcularCosto(double costo) {

		double costoTotal = 0;
		double bonificacion = 0;

		switch (this.tipoDePromocion) {
		case 1:
			this.costoTotal = costo;
			break;

		case 2:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
			}
			bonificacion = (this.descuentoPorcentual * costoTotal) / 100;
			this.costoTotal = costoTotal - bonificacion;
			break;

		case 3:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
				bonificacion = i.getCosto();
			}
			this.costoTotal = costoTotal - bonificacion;
			break;

		default:
			throw new Error("Entrada Invalida.");
		}
	}

	private void calcularDuracion() {
		if (this.atracciones == null) {
			this.duracionTotal = 0;
		} else {
			double duracionTotal = 0;
			for (Atraccion atraccion : this.atracciones) {
				duracionTotal += atraccion.getDuracion();
			}
			this.duracionTotal = duracionTotal;
		}
	}

	private void buscarPreferencia() {
		if (this.atracciones == null) {
			this.preferencia = ENUMTIPO.SinDefinir;
		} else {
			boolean first = true;
			for (Atraccion atraccion : this.atracciones) {
				if (first) {
					this.preferencia = atraccion.getPreferencia();
					first = false;
				}
				if (this.preferencia != atraccion.getPreferencia()) {
					this.preferencia = ENUMTIPO.SinDefinir;
				}
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////

	@Override
	public int hashCode() {
		return Objects.hash(Id, atracciones, costoTotal, descuentoPorcentual, duracionTotal, nombre, preferencia,
				tipoDePromocion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Id == other.Id && Objects.equals(atracciones, other.atracciones)
				&& Double.doubleToLongBits(costoTotal) == Double.doubleToLongBits(other.costoTotal)
				&& descuentoPorcentual == other.descuentoPorcentual
				&& Double.doubleToLongBits(duracionTotal) == Double.doubleToLongBits(other.duracionTotal)
				&& Objects.equals(nombre, other.nombre) && preferencia == other.preferencia
				&& tipoDePromocion == other.tipoDePromocion;
	}

	@Override
	public String toString() {
		return "Promocion2 [Id=" + Id + ", nombre=" + nombre + ", tipoDePromocion=" + tipoDePromocion
				+ ", descuentoPorcentual=" + descuentoPorcentual + ", costo=" + costoTotal + ", duracion="
				+ duracionTotal + ", atracciones=" + atracciones + ", preferencia=" + preferencia + "]";
	}

	@Override
	public int compareTo(Promocion o) {
		if (this.costoTotal == o.getCosto()) {
			return Double.compare(this.duracionTotal, o.getDuracion());
		}
		return Double.compare(this.costoTotal, o.getCosto());
	}

	@Override
	public int getPosicionX() {
		int x = 0;
		for (Atraccion a : this.atracciones) {
			x += a.getPosicionX();
		}
		return x;
	}

	@Override
	public int getPosicionY() {
		int y = 0;
		for (Atraccion a : this.atracciones) {
			y += a.getPosicionX();
		}
		return y;
	}

	@Override
	public double distance(InterfaceModel otro) {
		double distancia = Double.MAX_VALUE;
		for (Atraccion a : this.atracciones) {
			double aux = sqrt(
					pow(a.getPosicionX() - otro.getPosicionX(), 2) + pow(a.getPosicionX() - otro.getPosicionY(), 2));
			if (aux < distancia)
				distancia = aux;
		}
		return distancia;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (costoTotal <= 0) {
			errors.put("costoTotal", "Debe ser positivo");
		}
		if (duracionTotal <= 0) {
			errors.put("duracionTotal", "Debe ser positivo");
		}

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public void setDescuentoPorcentual(Integer descuentoPorcentual) {
		this.descuentoPorcentual = descuentoPorcentual;

	}

	public void setTipoDePromocion(int tipoDePromocion) {
		this.tipoDePromocion = tipoDePromocion;

	}

}
