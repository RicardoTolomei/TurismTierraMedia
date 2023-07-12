package services;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.TipoDeAtraccion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.TipoDeAtraccionDAO;
import persistence.commons.DAOFactory;

public class PromocionesService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();

	}

	public Promocion create(String nombre, Integer tipoDePromocion, Double costo, Integer descuentoPorcentual,
			int atraccion1, int atraccion2, int atraccionP, String preferencias) {

		List<Atraccion> atracciones = new ArrayList<>();
		agreagrAtraccionDeDAO(atracciones, atraccion1);
		agreagrAtraccionDeDAO(atracciones, atraccion2);
		if (tipoDePromocion.equals(3))
			agreagrAtraccionDeDAO(atracciones, atraccionP);

		Promocion promocion = new Promocion(-1, nombre, tipoDePromocion, costo, descuentoPorcentual, atracciones);

		if (promocion.isValid()) {
			crearPromocionEnDAO(promocion);
			crearTDAenDAO(promocion, preferencias);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Promocion update(Integer id, String nombre, Integer tipoDePromocion, Double costoTotal,
			Integer descuentoPorcentual, int atraccion1, int atraccion2, int atraccionP, String preferencias) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByID(id);
		
		List<Atraccion> atracciones = new ArrayList<>();
		agreagrAtraccionDeDAO(atracciones, atraccion1);
		agreagrAtraccionDeDAO(atracciones, atraccion2);
		if (tipoDePromocion.equals(3))
			agreagrAtraccionDeDAO(atracciones, atraccionP);

		promocion.setNombre(nombre);
		promocion.setTipoDePromocion(tipoDePromocion);
		promocion.setCosto(costoTotal);
		promocion.setDescuentoPorcentual(descuentoPorcentual);
		promocion.setAtracciones(atracciones);

		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			actualizarTDAenDAO(promocion.getId(), preferencias);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public void delete(Integer id) {
		deletePromocion(id);
		deleteTipoDeAtraccion(id);
	}

	public Promocion find(Integer id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.findByID(id);
	}

////////////////////////////////////////////////////////////////////////////////

	private List<Atraccion> agreagrAtraccionDeDAO(List<Atraccion> atracciones, int IdAtraccion) {
		AtraccionDAO atracionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atracionDAO.findByID(IdAtraccion);
		atracciones.add(atraccion);
		return atracciones;
	}

	private void crearPromocionEnDAO(Promocion promocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.insert(promocion);
	}

	private void crearTDAenDAO(Promocion promocion, String preferencias) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion1 = promocionDAO.findByPromotionName(promocion.getNombre());

		TipoDeAtraccionDAO tipodeatraccionDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion1 = new TipoDeAtraccion(promocion1.getId(), "Promocion", preferencias);

		tipodeatraccionDAO.insert(tipodeatraccion1);
	}

	private void actualizarTDAenDAO(Integer idDePromocion, String tipoDeAtraccion) {
		TipoDeAtraccionDAO tipodeatraccionDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion1 = tipodeatraccionDAO.findByReferenceAndType(idDePromocion, "Promocion");

		tipodeatraccion1.asignarPreferencia(tipoDeAtraccion);

		tipodeatraccionDAO.update(tipodeatraccion1);
	}

	private void deletePromocion(Integer id) {
		System.out.println("deletePromocion");
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByID(id);
		promocionDAO.delete(promocion);
	}

	private void deleteTipoDeAtraccion(Integer id) {
		System.out.println("deleteTipoDeAtraccion");
		TipoDeAtraccionDAO DAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion = DAO.findByReferenceAndType(id, "Promocion");
		DAO.delete(tipodeatraccion);
	}

}
