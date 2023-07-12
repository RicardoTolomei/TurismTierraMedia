package DAO;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.TipoDeAtraccion;
import model.Enum.ENUMTIPO;
import persistence.PromocionDAO;
import persistence.TipoDeAtraccionDAO;
import persistence.commons.DAOFactory;

public class PromocionDAOTest {

	@Test
	public void comprobarQueExisteAlmenos1Test() {
		boolean existen = false;
		int cantidad = contarlosAtodos();
		if (cantidad > 0)
			existen = true;
		assertTrue(existen);
	}

	@Test
	public void buscarlosATodosTest() {
		List<Promocion> todos = buscarlosAtodos();
		for (Promocion i : todos) {
			assertNotNull(i);
		}
	}

	@Test
	public void contarlosAtodosTest() {
		int cantidadEsperada = contarlosAtodos();
		int cantidadObtenida = contarlosAtodosLosEncontrados();
		assertEquals(cantidadObtenida, cantidadEsperada);
	}

	@Test
	public void buscarElPrimerIdTest() {
		Promocion promocion = buscarPromocionPorId(1);
		assertNotNull(promocion);
	}

	@Test
	public void comprobarLosValoresDeLaPrimeraPromocion() {
		Promocion promocion = buscarPromocionPorId(1);
		asignarPreferencia(promocion);

		int IdEsperada = 1;
		int IdObtenida = promocion.getId();
		assertEquals(IdEsperada, IdObtenida);

		String nombreEsperado = "Recorridos por paisajes";
		String nombreObtenido = promocion.getNombre();
		assertEquals(nombreEsperado, nombreObtenido);

		int tipoEsperado = 3;
		int tipoObtenido = promocion.getTipoDePromocion();
		assertEquals(tipoEsperado, tipoObtenido);

		ENUMTIPO tipoDeAtraccionEsperado = ENUMTIPO.PAISAJE;
		ENUMTIPO tipoDeAtraccionObtenido = promocion.getPreferencia();
		assertEquals(tipoDeAtraccionEsperado, tipoDeAtraccionObtenido);

		double costoTotalEsperado = 22;
		double costoTotalObtenido = promocion.getCosto();
		assertEquals(costoTotalEsperado, costoTotalObtenido, 0);

		double duracionEsperada = 11;
		double duracionObtenida = promocion.getDuracion();
		assertEquals(duracionEsperada, duracionObtenida, 0);

		int descuentoPorcentualEsperado = 0;
		int descuentoPorcentualObtenido = promocion.getDescuentoPorcentual();
		assertEquals(descuentoPorcentualEsperado, descuentoPorcentualObtenido);

		int cantidadDeAtraccionesEsperda = 3;
		List<Atraccion> atracciones = promocion.getAtracciones();
		int cantidadDeAtraccionesObtenida = contarLaCantidadDeAtracciones(atracciones);
		assertEquals(cantidadDeAtraccionesEsperda, cantidadDeAtraccionesObtenida);
	}

	@Test
	public void buscarPorNombreDePromocionTest() {
		Promocion promocion = buscarPromocionPorNombre("Recorridos por paisajes");
		assertNotNull(promocion);
	}

	@Test
	public void comprobarLosValoresDeLaPromocionBuscadaPorNombre() {
		Promocion promocion = buscarPromocionPorNombre("Recorridos por paisajes");
		asignarPreferencia(promocion);

		int IdEsperada = 1;
		int IdObtenida = promocion.getId();
		assertEquals(IdEsperada, IdObtenida);

		String nombreEsperado = "Recorridos por paisajes";
		String nombreObtenido = promocion.getNombre();
		assertEquals(nombreEsperado, nombreObtenido);

		int tipoEsperado = 3;
		int tipoObtenido = promocion.getTipoDePromocion();
		assertEquals(tipoEsperado, tipoObtenido);

		ENUMTIPO tipoDeAtraccionEsperado = ENUMTIPO.PAISAJE;
		ENUMTIPO tipoDeAtraccionObtenido = promocion.getPreferencia();
		assertEquals(tipoDeAtraccionEsperado, tipoDeAtraccionObtenido);

		double costoTotalEsperado = 22;
		double costoTotalObtenido = promocion.getCosto();
		assertEquals(costoTotalEsperado, costoTotalObtenido, 0);

		double duracionEsperada = 11;
		double duracionObtenida = promocion.getDuracion();
		assertEquals(duracionEsperada, duracionObtenida, 0);

		int descuentoPorcentualEsperado = 0;
		int descuentoPorcentualObtenido = promocion.getDescuentoPorcentual();
		assertEquals(descuentoPorcentualEsperado, descuentoPorcentualObtenido);

		int cantidadDeAtraccionesEsperda = 3;
		List<Atraccion> atracciones = promocion.getAtracciones();
		int cantidadDeAtraccionesObtenida = contarLaCantidadDeAtracciones(atracciones);
		assertEquals(cantidadDeAtraccionesEsperda, cantidadDeAtraccionesObtenida);
	}

	@Test
	public void insertarYdeletearUnaPromocionTest() {

		int cantidadDePromocionesInicial = contarlosAtodos();

		List<Atraccion> atracciones = buscarLasAtraccionesDeUnaPromocionPorId(1);
		Promocion promocion = new Promocion(0, "PromocionTest", 3, 0, 0, atracciones);
		promocion.setPreferencia(ENUMTIPO.AVENTURA);

		insertarPromocion(promocion);

		int cantidadDePromocionesActual = contarlosAtodos();
		assertEquals(cantidadDePromocionesInicial + 1, cantidadDePromocionesActual);
		assertNotEquals(cantidadDePromocionesInicial, cantidadDePromocionesActual);

		promocion = buscarPromocionPorNombre("PromocionTest");

		deletearPromocion(promocion);

		cantidadDePromocionesActual = contarlosAtodos();
		assertEquals(cantidadDePromocionesInicial, cantidadDePromocionesActual);
	}

////////////////////////////////////////////////////////////////////////////////

	private List<Promocion> buscarlosAtodos() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> todos = promocionDAO.findAll();
		return todos;
	}

	private int contarlosAtodos() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		int cantidad = promocionDAO.countAll();
		return cantidad;
	}

	private int contarlosAtodosLosEncontrados() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> todos = promocionDAO.findAll();
		int cantidad = 0;
		for (@SuppressWarnings("unused") Promocion i : todos)
			cantidad++;
		return cantidad;
	}

	private Promocion buscarPromocionPorId(int Id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByID(Id);
		return promocion;
	}

	private Promocion buscarPromocionPorNombre(String nombre) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByPromotionName(nombre);
		return promocion;
	}

	private int contarLaCantidadDeAtracciones(List<Atraccion> atracciones) {
		int cantidad = 0;
		for (@SuppressWarnings("unused") Atraccion i : atracciones)
			cantidad++;
		return cantidad;
	}

	private ENUMTIPO buscarPreferencia(Promocion p) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion preferencia = tdaDAO.findByReferenceAndType(1, "Promocion");
		return preferencia.getPreferencia();
	}

	private void asignarPreferencia(Promocion promocion) {
		ENUMTIPO preferencia = buscarPreferencia(promocion);
		promocion.setPreferencia(preferencia);
	}

	private List<Atraccion> buscarLasAtraccionesDeUnaPromocionPorId(int id) {
		Promocion promocion = buscarPromocionPorId(id);
		List<Atraccion> atracciones = promocion.getAtracciones();
		return atracciones;
	}

	private void insertarPromocion(Promocion promocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.insert(promocion);
		System.out.println("llego a salir.");
	}

	private void deletearPromocion(Promocion promocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	@SuppressWarnings("unused")
	private void actualizarPromocion(Promocion promocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.update(promocion);
	}

}
