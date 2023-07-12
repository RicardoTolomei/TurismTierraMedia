package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItinerarioTest {

	@Test
	public void crearItinerarioTest() {

		Itinerario itinerario1 = new Itinerario(1, 2);
		assertNotNull(itinerario1);

		Itinerario itinerario2 = new Itinerario(1, 3, 4, "");
		assertNotNull(itinerario2);
	}

	@Test
	public void verificarValoresDeItinerariosTest() {

		Itinerario itinerario1 = new Itinerario(1, 2);
		int obtenido1 = itinerario1.getIdUsuario();
		int obtenido2 = itinerario1.getIdAtraccion();
		int esperado1 = 1;
		int esperado2 = 2;
		assertEquals(obtenido1, esperado1);
		assertEquals(obtenido2, esperado2);

		Itinerario itinerario2 = new Itinerario(1, 3, 4, "");
		int obtenido3 = itinerario2.getId();
		int obtenido4 = itinerario2.getIdUsuario();
		int obtenido5 = itinerario2.getIdAtraccion();
		int esperado3 = 1;
		int esperado4 = 3;
		int esperado5 = 4;
		assertEquals(obtenido3, esperado3);
		assertEquals(obtenido4, esperado4);
		assertEquals(obtenido5, esperado5);
	}

	@Test
	public void verificacionDe2ItinerariosIgualesTest() {

		Itinerario itinerario1 = new Itinerario(1, 2);
		Itinerario itinerario2 = new Itinerario(1, 1, 2, "");
		assertFalse(itinerario1.equals(itinerario2));

		Itinerario itinerario3 = new Itinerario(1, 2);
		Itinerario itinerario4 = new Itinerario(0, 1, 2, "");
		assertTrue(itinerario3.equals(itinerario4));

	}

}
