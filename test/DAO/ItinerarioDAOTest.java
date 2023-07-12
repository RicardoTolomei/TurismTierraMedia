package DAO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Itinerario;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class ItinerarioDAOTest {

	@Test
	public void comprobarExistenciaDeItinerarioDAOTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		int cantidad = itinerarioDAO.countAll();
		assertNotNull(cantidad);
	}

	@Test
	public void comprobarLaExistenciaDeTodosLosItinerariosTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		for (@SuppressWarnings("unused")
		Itinerario i : itinerarios) {
			assertNotNull(itinerarios);
		}
	}

	@Test
	public void comprobarLaCantidadDeItinerariosTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		int cantidadObtenida = itinerarioDAO.countAll();
		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Itinerario i : itinerarios) {
			cantidadEsperada++;
		}

		assertEquals(cantidadEsperada, cantidadObtenida);
	}

	@Test
	public void comprobarLosValoresDel1erItinerarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		Itinerario itinerario1 = itinerarioDAO.findByID(1);

		int idUsuarioObtendio = itinerario1.getIdUsuario();
		int idAtraccionObtendio = itinerario1.getIdAtraccion();

		int idUsuarioEsperado = 1;
		int idAtraccionEsperado = 1;

		assertEquals(idUsuarioEsperado, idUsuarioObtendio);
		assertEquals(idAtraccionObtendio, idAtraccionEsperado);
	}

	@Test
	public void comprobarTodasLasAtraccionesDel1erUsuarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		List<Itinerario> itinerarios = itinerarioDAO.findAll();
		List<Itinerario> itinerariosU1 = itinerarioDAO.findAllAttractionsOfUser(1);

		int cantidadItinerariosEsperados = 0;
		int cantidadItinerariosObtenidos = 0;

		for (Itinerario i : itinerarios) {
			int idUsuario = i.getIdUsuario();
			if (idUsuario == 1) {
				cantidadItinerariosEsperados++;
			}
		}

		for (@SuppressWarnings("unused")
		Itinerario i : itinerariosU1) {
			cantidadItinerariosObtenidos++;
		}

		assertEquals(cantidadItinerariosEsperados, cantidadItinerariosObtenidos);
	}

	@Test
	public void buscarLasAtracionesDeUnUsuarioYagregarlasTest() {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		Itinerario itinerario = itinerarioDAO.findByID(1);

		int idUsuario = itinerario.getIdUsuario();
		int idAtraccion = itinerario.getIdAtraccion();
		int cantidadDeAtraccionesEncontradas = 0;
		int cantidadDeAtraccionesEnElUsuario = 0;

		Usuario usuario = usuarioDAO.findById(idUsuario);
		List<Atraccion> atracciones = new ArrayList<>();

		List<Itinerario> itinerarios = itinerarioDAO.findAll();
		for (Itinerario i : itinerarios) {
			int id = i.getIdUsuario();
			if (id == idUsuario) {
				cantidadDeAtraccionesEncontradas++;
				atracciones.add(atraccionDAO.findByID(idAtraccion));
			}
		}
		usuario.agregarAtracciones(atracciones);

		for (@SuppressWarnings("unused")
		Atraccion i : usuario.getAtracciones()) {
			cantidadDeAtraccionesEnElUsuario++;
		}

		assertNotNull(itinerario);
		assertNotNull(usuario);
		assertNotNull(atracciones);
		assertTrue(cantidadDeAtraccionesEncontradas > 0);
		assertEquals(cantidadDeAtraccionesEncontradas, cantidadDeAtraccionesEnElUsuario);
	}

	@Test
	public void insertarItinerarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		int cantidadDeAtraccionInicial = itinerarioDAO.countAll();

		Itinerario itinerariolocal = new Itinerario(3, 3);
		itinerarioDAO.insert(itinerariolocal);

		int cantidadDeAtraccionesActual = itinerarioDAO.countAll();

		assertNotEquals(cantidadDeAtraccionInicial, cantidadDeAtraccionesActual);
		assertEquals(cantidadDeAtraccionInicial + 1, cantidadDeAtraccionesActual);

		int idUsuario = 3;
		int idAtraccion = 3;
		boolean seEncuentraEnLaBaseDeDatos = false;
		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		for (Itinerario i : itinerarios) {
			if (i.getIdUsuario() == idUsuario) {
				if (i.getIdAtraccion() == idAtraccion) {
					seEncuentraEnLaBaseDeDatos = true;
					itinerarioDAO.delete(i);
				}
			}
		}

		assertTrue(seEncuentraEnLaBaseDeDatos);
	}

	@Test
	public void deletearItinerarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		int cantidadDeAtraccionInicial = itinerarioDAO.countAll();

		Itinerario itinerariolocal = new Itinerario(3, 3);
		itinerarioDAO.insert(itinerariolocal);

		int cantidadDeAtraccionesActual = itinerarioDAO.countAll();

		assertNotEquals(cantidadDeAtraccionInicial, cantidadDeAtraccionesActual);
		assertEquals(cantidadDeAtraccionInicial + 1, cantidadDeAtraccionesActual);

		int idUsuario = 3;
		int idAtraccion = 3;
		boolean seEncuentraEnLaBaseDeDatos = false;
		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		for (Itinerario i : itinerarios) {
			if (i.getIdUsuario() == idUsuario) {
				if (i.getIdAtraccion() == idAtraccion) {
					seEncuentraEnLaBaseDeDatos = true;
				}
			}
		}
		assertTrue(seEncuentraEnLaBaseDeDatos);

		itinerarios = itinerarioDAO.findAll();
		for (Itinerario i : itinerarios) {
			if (i.getIdUsuario() == idUsuario) {
				if (i.getIdAtraccion() == idAtraccion) {
					seEncuentraEnLaBaseDeDatos = false;
					itinerarioDAO.delete(i);
				}
			}
		}
		assertFalse(seEncuentraEnLaBaseDeDatos);
	}

	@Test
	public void actualizarDescripcionItinerario1Test() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		Itinerario itinerario = itinerarioDAO.findByID(1);
		String descripcionInicial = itinerario.getDescripcion();

		itinerario.setDescripcion("testeo");
		itinerarioDAO.update(itinerario);

		itinerario = itinerarioDAO.findByID(1);
		String descripcionActual = itinerario.getDescripcion();
		assertNotEquals(descripcionInicial, descripcionActual);

		itinerario.setDescripcion("promocion1");
		itinerarioDAO.update(itinerario);

		itinerario = itinerarioDAO.findByID(1);
		descripcionActual = itinerario.getDescripcion();
		assertEquals(descripcionInicial, descripcionActual);
	}

}
