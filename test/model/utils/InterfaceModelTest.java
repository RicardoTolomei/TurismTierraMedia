package model.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.Usuario;

public class InterfaceModelTest {

	@Test
	public void calcularLaDistanciaEntreUsuariostest() {

		double distanciaEsperada;
		double distanciaObtenida;

		Usuario usuario1 = new Usuario("", "", 0, 0, 0, 0);
		Usuario usuario2 = new Usuario("", "", 0, 0, 2, 2);
		distanciaEsperada = 2.82;
		distanciaObtenida = usuario1.distance(usuario2);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Usuario usuario3 = new Usuario("", "", 0, 0, 3, 4);
		Usuario usuario4 = new Usuario("", "", 0, 0, 3, 6);
		distanciaEsperada = 2.00;
		distanciaObtenida = usuario3.distance(usuario4);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Usuario usuario5 = new Usuario("", "", 0, 0, 8, 0);
		Usuario usuario6 = new Usuario("", "", 0, 0, 0, 8);
		distanciaEsperada = 11.31;
		distanciaObtenida = usuario5.distance(usuario6);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);
	}

	@Test
	public void calcularLaDistanciaEntreAtraccionestest() {

		double distanciaEsperada;
		double distanciaObtenida;

		Atraccion atraccion1 = new Atraccion("", 0, 0, 0, 0, 0);
		Atraccion atraccion2 = new Atraccion("", 0, 0, 0, 2, 2);
		distanciaEsperada = 2.82;
		distanciaObtenida = atraccion1.distance(atraccion2);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Atraccion atraccion3 = new Atraccion("", 0, 0, 0, 3, 4);
		Atraccion atraccion4 = new Atraccion("", 0, 0, 0, 3, 6);
		distanciaEsperada = 2.00;
		distanciaObtenida = atraccion3.distance(atraccion4);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Atraccion atraccion5 = new Atraccion("", 0, 0, 0, 8, 0);
		Atraccion atraccion6 = new Atraccion("", 0, 0, 0, 0, 8);
		distanciaEsperada = 11.31;
		distanciaObtenida = atraccion5.distance(atraccion6);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);
	}

	@Test
	public void calcularLaDistanciaEntreUsuariosYatraccionestest() {

		double distanciaEsperada;
		double distanciaObtenida;

		Usuario usuario1 = new Usuario("", "", 0, 0, 0, 0);
		Atraccion atraccion1 = new Atraccion("", 0, 0, 0, 2, 2);
		distanciaEsperada = 2.82;
		distanciaObtenida = usuario1.distance(atraccion1);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Usuario usuario2 = new Usuario("", "", 0, 0, 3, 4);
		Atraccion atraccion2 = new Atraccion("", 0, 0, 0, 3, 6);
		distanciaEsperada = 2.00;
		distanciaObtenida = usuario2.distance(atraccion2);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);

		Usuario usuario3 = new Usuario("", "", 0, 0, 8, 0);
		Atraccion atraccion3 = new Atraccion("", 0, 0, 0, 0, 8);
		distanciaEsperada = 11.31;
		distanciaObtenida = usuario3.distance(atraccion3);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);
	}

	@Test
	public void calcularLaDistanciaMasCercanaEntreLasAtraccionesDePromocionestest() {

		double distanciaEsperada;
		double distanciaObtenida;

		Atraccion atraccion1 = new Atraccion("", 0, 0, 0, 8, 8);
		Atraccion atraccion2 = new Atraccion("", 0, 0, 0, 0, 0);

		List<Atraccion> atracciones1 = new ArrayList<>();
		atracciones1.add(atraccion1);
		atracciones1.add(atraccion2);

		Promocion promocion1 = new Promocion(1, "", 1, 0, 0, atracciones1);

		Atraccion atraccion3 = new Atraccion("", 0, 0, 0, 8, 8);
		Atraccion atraccion4 = new Atraccion("", 0, 0, 0, 2, 2);

		List<Atraccion> atracciones2 = new ArrayList<>();
		atracciones2.add(atraccion3);
		atracciones2.add(atraccion4);

		Promocion promocion2 = new Promocion(2, "", 1, 0, 0, atracciones2);

		distanciaEsperada = 2.82;
		distanciaObtenida = promocion1.distance(promocion2);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);
	}

	@Test
	public void calcularLaDistanciaMasCercanaEntreUnUsuarioYunaAtraccionDeUnaPromocionstest() {

		double distanciaEsperada;
		double distanciaObtenida;

		Atraccion atraccion1 = new Atraccion("", 0, 0, 0, 8, 8);
		Atraccion atraccion2 = new Atraccion("", 0, 0, 0, 0, 0);

		List<Atraccion> atracciones1 = new ArrayList<>();
		atracciones1.add(atraccion1);
		atracciones1.add(atraccion2);

		Promocion promocion1 = new Promocion(1, "", 1, 0, 0, atracciones1);

		Usuario usuario1 = new Usuario("", "", 0, 0, 2, 2);

		distanciaEsperada = 2.82;
		distanciaObtenida = promocion1.distance(usuario1);
		assertEquals(distanciaEsperada, distanciaObtenida, 0.01);
	}

}
