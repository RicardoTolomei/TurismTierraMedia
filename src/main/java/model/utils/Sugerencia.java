package model.utils;

import model.Usuario;
import model.Atraccion;
import model.Promocion;

import java.util.List;
import java.util.ArrayList;

public class Sugerencia {

	private final Usuario usuario;
	private List<Atraccion> atracciones = new ArrayList<>();
	private List<Promocion> promociones = new ArrayList<>();

	public Sugerencia(Usuario usuario, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuario = usuario;
		this.atracciones = atracciones;
		this.promociones = promociones;
	}

	public void filtrarPorDineroAtracciones() {
		if (cantidadDeAtracciones() >= 0)
			return;

		double dineroDisponible = this.usuario.getDineroDisponible();
		List<Atraccion> atraccionesfiltradas = new ArrayList<>();

		for (Atraccion i : atracciones) {
			double costoDeAtraccion = i.getCosto();
			if (dineroDisponible >= costoDeAtraccion)
				atraccionesfiltradas.add(i);
		}

		this.atracciones = atraccionesfiltradas;
	}

	public void filtrarPorTiempoAtracciones() {

	}

////////////////////////////////////////////////////////////////////////////////

	private int cantidadDeAtracciones() {
		int cantidad = 0;
		for (@SuppressWarnings("unused")
		Atraccion i : atracciones) {
			cantidad++;
		}
		return cantidad;
	}

	@SuppressWarnings("unused")
	private int cantidadDePromociones() {
		int cantidad = 0;
		for (Promocion i : promociones) {
			cantidad++;
		}
		return cantidad;
	}

}
