package DAO;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionDAOTest {

	@Test
	public void comprobarExistenciaDeAtraccionesCargadasTets() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		int cantidad = attrctionDAO.countAll();

		assertNotNull(cantidad);
	}

	@Test
	public void comprobarCantidadDeAtraccionesCargadasTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		int cantidadObetenida = attrctionDAO.countAll();

		List<Atraccion> atraccionesDB = attrctionDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Atraccion i : atraccionesDB) {
			cantidadEsperada++;
		}

		assertEquals(cantidadObetenida, cantidadEsperada);
	}

	@Test
	public void encontrarTodasLasAtraccionesCargadasTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		List<Atraccion> atraccionesDB = attrctionDAO.findAll();

		for (Atraccion i : atraccionesDB) {
			assertNotNull(i);
		}
	}

	@Test
	public void encontrarPrimeraAtraccionPorMedioDelIdTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		Atraccion atraccionDB = attrctionDAO.findByID(1);

		assertNotNull(atraccionDB);
	}

	@Test
	public void encontrarPrimeraAtraccionPorMedioDelNombreTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		Atraccion atraccionDB = attrctionDAO.findByName("Atraccion1");

		assertNotNull(atraccionDB);
	}

	@Test
	public void comprobarValoresCorrectosDeLaPrimeraAtraccionTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		Atraccion atraccionLocal = new Atraccion(1, "Atraccion1", 10, 2, 0, 20, 5, 5);
		Atraccion atraccionDB = attrctionDAO.findByName("Atraccion1");

		assertTrue(atraccionLocal.equals(atraccionDB));
	}

	@Test
	public void buscarElIdDeLaPrimeraAtraccionTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccionDB = attrctionDAO.findByName("Atraccion1");

		int idObtenida = atraccionDB.getId();
		int idEsperada = 1;

		assertEquals(idObtenida, idEsperada);
	}

	@Test
	public void insertarAtraccionTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		int cantidadDeAtraccionInicial = attrctionDAO.countAll();
		Atraccion atraccionLocal = new Atraccion("AtraccionTest", 10, 2, 20, 5, 5);
		attrctionDAO.insert(atraccionLocal);
		int cantidadDeAtraccionesActual = attrctionDAO.countAll();

		assertNotEquals(cantidadDeAtraccionInicial, cantidadDeAtraccionesActual);
		assertEquals(cantidadDeAtraccionInicial + 1, cantidadDeAtraccionesActual);

		int idDB = attrctionDAO.findAttractionID("AtraccionTest");
		atraccionLocal.setId(idDB);

		Atraccion atraccionDB = attrctionDAO.findByName("AtraccionTest");

		assertNotNull(atraccionDB);
		assertEquals(atraccionLocal.getId(), atraccionDB.getId());
		assertTrue(atraccionLocal.equals(atraccionDB));

		attrctionDAO.delete(atraccionLocal);
	}

	@Test
	public void deletearAtraccionTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		int cantidadDeAtraccionInicial = attrctionDAO.countAll();

		Atraccion atraccionTemporal = new Atraccion("AtraccionTemporal", 2, 2, 2, 2, 2);

		attrctionDAO.insert(atraccionTemporal);

		int cantidadDeAtraccionesActual = attrctionDAO.countAll();

		assertNotEquals(cantidadDeAtraccionInicial, cantidadDeAtraccionesActual);
		assertEquals(cantidadDeAtraccionInicial + 1, cantidadDeAtraccionesActual);

		int idDB = attrctionDAO.findAttractionID("AtraccionTemporal");
		atraccionTemporal.setId(idDB);

		attrctionDAO.delete(atraccionTemporal);
		cantidadDeAtraccionesActual--;

		assertNull(attrctionDAO.findByID(idDB));
		assertEquals(cantidadDeAtraccionInicial, cantidadDeAtraccionesActual);
	}

	@Test
	public void actualizarAtraccionTest() {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();

		int valorLocal;
		int valorDataBase;

		Atraccion atraccionLocal = new Atraccion("ActualizarTest", 2, 2, 2, 2, 2);
		valorLocal = atraccionLocal.getCupoActual(); // 0
		attrctionDAO.insert(atraccionLocal);

		// hago esto para traer el id asignado en sql
		atraccionLocal = attrctionDAO.findByName("ActualizarTest");

		Atraccion atraccionDataBase = attrctionDAO.findByName("ActualizarTest");
		valorDataBase = atraccionDataBase.getCupoActual();

		assertEquals(valorLocal, valorDataBase);

		atraccionDataBase.setCupoActual(valorDataBase + 1);
		attrctionDAO.update(atraccionDataBase);

		Atraccion atraccionDataBase2 = attrctionDAO.findByName("ActualizarTest");
		valorDataBase = atraccionDataBase2.getCupoActual();

		assertNotEquals(valorLocal, valorDataBase);

		attrctionDAO.delete(atraccionDataBase);
	}

}
