package DAO;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import model.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class UsuarioDAOTest {

	@Test
	public void comprobarSiExistenUsuariosCargadosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidad = userDAO.countAll();

		assertNotNull(cantidad);
	}

	@Test
	public void comprobarCantidadDeUsuariosCargadosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidadObetenida = userDAO.countAll();

		List<Usuario> usuariosBD = userDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Usuario i : usuariosBD) {
			cantidadEsperada++;
		}

		assertEquals(cantidadObetenida, cantidadEsperada);
	}

	@Test
	public void encontrarATodosLosUsuariosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		List<Usuario> usuariosBD = userDAO.findAll();

		for (Usuario i : usuariosBD) {
			assertNotNull(i);
		}
	}

	@Test
	public void comprobarExisteciaDelPrimerUsuarioPorIdTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario hobbit1 = userDAO.findById(1);

		assertNotNull(hobbit1);
	}

	@Test
	public void comprobarExisteciaDelPrimerUsuarioPorUsernameTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario hobbit1 = userDAO.findByUsername("hobbit1");

		assertNotNull(hobbit1);
	}

	@Test
	public void comprobarLosValoresCorrectosDelPrimerUsuarioTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario hobbitBD = userDAO.findById(1);
		Usuario hobbitLocal = new Usuario(1, "hobbit1", "aaaa", 12, 10, 1, 1, 0);

		assertTrue(hobbitBD.equals(hobbitLocal));
	}

	@Test
	public void buscarIdPorMedioDeNombreDeUsuarioYContraseniaTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		String username = "hobbit1";
		String password = "aaaa";

		int idObtenida = userDAO.findUserId(username, password);
		int idEsperada = 1;

		assertEquals(idObtenida, idEsperada);
	}

	@Test
	public void insertarUsuarioDBTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidadUsuariosBDInicial = userDAO.countAll();

		Usuario userLocal = new Usuario("UsuarioTest", "abcd", 0, 0, 0, 0);
		userDAO.insert(userLocal);

		int cantidadUsuariosBDActual = userDAO.countAll();

		assertNotEquals(cantidadUsuariosBDInicial, cantidadUsuariosBDActual);
		assertEquals(cantidadUsuariosBDInicial + 1, cantidadUsuariosBDActual);

		Usuario usuarioDB = userDAO.findByUsername("UsuarioTest");

		assertNotNull(usuarioDB);

		userDAO.delete(usuarioDB);
	}

	@Test
	public void deletearUsuarioDBTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario usuarioLocal = new Usuario("UsuarioTest", "abcd", 0, 0, 0, 0);
		userDAO.insert(usuarioLocal);
		int cantidadUsuariosBDInicial = userDAO.countAll();

		Usuario usuarioDB1 = userDAO.findByUsername("UsuarioTest");
		assertNotNull(usuarioDB1);

		userDAO.delete(usuarioDB1);
		int cantidadUsuariosDBActual = userDAO.countAll();

		assertNotEquals(cantidadUsuariosBDInicial, cantidadUsuariosDBActual);
		assertEquals(cantidadUsuariosBDInicial - 1, cantidadUsuariosDBActual);

		Usuario usuarioDB2 = userDAO.findByUsername("UsuarioTest");
		assertNull(usuarioDB2);
	}

	@Test
	public void actualizarUsuarioDBTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		Usuario usuarioLocal = new Usuario("UsuarioTest", "abcd", 0, 0, 0, 0);
		userDAO.insert(usuarioLocal);

		Usuario usuarioDB = userDAO.findByUsername("UsuarioTest");
		double monedasLocales = 0;
		double tiempoDisponibleLocal = 0;
		double monedasDB = usuarioDB.getDineroDisponible();
		double tiempoDisponibleDB = usuarioDB.getTiempoDisponible();
		assertEquals(monedasLocales, monedasDB, 0);
		assertEquals(tiempoDisponibleLocal, tiempoDisponibleDB, 0);

		usuarioLocal = usuarioDB;
		assertNotEquals(usuarioLocal.getId(), 0);
		usuarioLocal.setDineroDisponible(10);
		usuarioLocal.setTiempoDisponible(10);
		userDAO.update(usuarioLocal);

		usuarioDB = userDAO.findByUsername("UsuarioTest");
		monedasLocales = 10;
		tiempoDisponibleLocal = 10;
		monedasDB = usuarioDB.getDineroDisponible();
		tiempoDisponibleDB = usuarioDB.getTiempoDisponible();
		assertEquals(monedasLocales, monedasDB, 0);
		assertEquals(tiempoDisponibleLocal, tiempoDisponibleDB, 0);

		userDAO.delete(usuarioLocal);
	}

}
