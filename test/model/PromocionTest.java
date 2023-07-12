package model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.Enum.ENUMTIPO;

public class PromocionTest {

	@Test
	public void creacionTest() {
		Promocion promo1 = new Promocion();
		assertNotNull(promo1);

		Atraccion atraccion1 = new Atraccion(1, "Atraccion1", 20, 2, 1, 20, 0, 0);
		Atraccion atraccion2 = new Atraccion(1, "Atraccion2", 20, 2, 1, 20, 0, 0);

		List<Atraccion> atracciones = new ArrayList<>();

		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		Promocion promo2 = new Promocion(2, "promo2", 1, 30, 0, atracciones);
		assertNotNull(promo2);
	}

	@Test
	public void comprobarValoresPromocionAbsolutaTest() {

		Atraccion atraccion1 = new Atraccion(1, "Atraccion1", 20, 2, 1, 20, 0, 0);
		atraccion1.setPreferencia(ENUMTIPO.PAISAJE);

		Atraccion atraccion2 = new Atraccion(1, "Atraccion2", 20, 2, 1, 20, 0, 0);
		atraccion2.setPreferencia(ENUMTIPO.PAISAJE);

		List<Atraccion> atracciones = new ArrayList<>();

		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		Promocion promoA = new Promocion(99, "promoAbsoluta", 1, 30, 0, atracciones);
		assertNotNull(promoA);

		int tipoDePormocionEsperado = 1;
		int tipoDePormocionObtenido = promoA.getTipoDePromocion();
		assertEquals(tipoDePormocionEsperado, tipoDePormocionObtenido);

		double costoEsperado = 30;
		double costoObtenido = promoA.getCosto();
		assertEquals(costoEsperado, costoObtenido, 0);

		double duracionEsperada = 4;
		double duracionObtenida = promoA.getDuracion();
		assertEquals(duracionEsperada, duracionObtenida, 0);

		int descuentoEsperado = 0;
		int descuentoObtenido = promoA.getDescuentoPorcentual();
		assertEquals(descuentoEsperado, descuentoObtenido);

		ENUMTIPO preferenciaEsperada = ENUMTIPO.PAISAJE;
		ENUMTIPO preferenciaObtenida = promoA.getPreferencia();
		assertEquals(preferenciaEsperada, preferenciaObtenida);
	}

	@Test
	public void comprobarValoresPromocionPorcentualTest() {

		Atraccion atraccion1 = new Atraccion(1, "Atraccion1", 20, 2, 1, 20, 0, 0);
		atraccion1.setPreferencia(ENUMTIPO.DEGUSTACION);

		Atraccion atraccion2 = new Atraccion(1, "Atraccion2", 20, 2, 1, 20, 0, 0);
		atraccion2.setPreferencia(ENUMTIPO.DEGUSTACION);

		List<Atraccion> atracciones = new ArrayList<>();

		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		Promocion promoP = new Promocion(99, "promoPorcentual", 2, 0, 5, atracciones);
		assertNotNull(promoP);

		int tipoDePormocionEsperado = 2;
		int tipoDePormocionObtenido = promoP.getTipoDePromocion();
		assertEquals(tipoDePormocionEsperado, tipoDePormocionObtenido);

		double costoEsperado = 38;
		double costoObtenido = promoP.getCosto();
		assertEquals(costoEsperado, costoObtenido, 0.2);

		double duracionEsperada = 4;
		double duracionObtenida = promoP.getDuracion();
		assertEquals(duracionEsperada, duracionObtenida, 0);

		int descuentoEsperado = 5;
		int descuentoObtenido = promoP.getDescuentoPorcentual();
		assertEquals(descuentoEsperado, descuentoObtenido);

		ENUMTIPO preferenciaEsperada = ENUMTIPO.DEGUSTACION;
		ENUMTIPO preferenciaObtenida = promoP.getPreferencia();
		assertEquals(preferenciaEsperada, preferenciaObtenida);
	}

	@Test
	public void comprobarValoresPromocionABTest() {

		Atraccion atraccion1 = new Atraccion(1, "Atraccion1", 20, 2, 2, 20, 0, 0);
		atraccion1.setPreferencia(ENUMTIPO.AVENTURA);

		Atraccion atraccion2 = new Atraccion(2, "Atraccion2", 20, 2, 2, 20, 0, 0);
		atraccion2.setPreferencia(ENUMTIPO.AVENTURA);

		Atraccion atraccion3 = new Atraccion(3, "AtraccionBonus", 20, 2, 0, 20, 0, 0);
		atraccion3.setPreferencia(ENUMTIPO.AVENTURA);

		List<Atraccion> atracciones = new ArrayList<>();

		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);

		Promocion promoP = new Promocion(99, "promoAB", 3, 0, 0, atracciones);
		assertNotNull(promoP);

		int tipoDePormocionEsperado = 3;
		int tipoDePormocionObtenido = promoP.getTipoDePromocion();
		assertEquals(tipoDePormocionEsperado, tipoDePormocionObtenido);

		double costoEsperado = 40;
		double costoObtenido = promoP.getCosto();
		assertEquals(costoEsperado, costoObtenido, 0);

		double duracionEsperada = 6;
		double duracionObtenida = promoP.getDuracion();
		assertEquals(duracionEsperada, duracionObtenida, 0);

		int descuentoEsperado = 0;
		int descuentoObtenido = promoP.getDescuentoPorcentual();
		assertEquals(descuentoEsperado, descuentoObtenido);

		ENUMTIPO preferenciaEsperada = ENUMTIPO.AVENTURA;
		ENUMTIPO preferenciaObtenida = promoP.getPreferencia();
		assertEquals(preferenciaEsperada, preferenciaObtenida);
	}
}
