package services;

import java.util.List;

import model.Atraccion;
import model.TipoDeAtraccion;
import persistence.AtraccionDAO;
import persistence.TipoDeAtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(String nombre, Double costo, Double duracion, Integer cupoActual, Integer cupoMaximo,
			Integer posicionX, Integer posicionY, String tipoDeAtraccion) {
		
		System.out.println("Valor dentro del create -> "+ tipoDeAtraccion);
		
		Atraccion atraccion = new Atraccion(-1, nombre, costo, duracion, cupoActual, cupoMaximo, posicionX, posicionY);
		atraccion.setPreferenciaString(tipoDeAtraccion);
		
		System.out.println("Valor despues del seteo -> "+ atraccion.getPreferencia());
		System.out.println("Valor despues del seteo -> "+ atraccion.getPreferenciaString());
		
		if (atraccion.isValid()) {
			
			System.out.println("Es valido ");
			
			insertarAtraccionEnDAO(atraccion);
			System.out.println("Se inserto atraccion ");
			crearTDAenDAO(atraccion);
			System.out.println("Se creo DAO ");
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public Atraccion update(Integer id, String nombre, Double costo, Double duracion, Integer cupoActual,
			Integer cupoMaximo, Integer posicionX, Integer posicionY, String tipoDeAtraccion) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
		Atraccion atraccion = atraccionDAO.findByID(id);
		
		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupoMaximo(cupoMaximo);
		atraccion.setPosicionX(posicionX);
		atraccion.setPosicionY(posicionY);
		

		atraccion.setPreferenciaString(tipoDeAtraccion);
		System.out.println("Valor despues del seteo -> "+ atraccion.getPreferencia());
		System.out.println("Valor despues del seteo -> "+ atraccion.getPreferenciaString());
		
		if (atraccion.isValid()) {
			System.out.println("Se valido");
			atraccionDAO.update(atraccion);
			System.out.println("Se actualizo Atraccion en DAO ");
			actualizarTDAenDAO(atraccion);
			System.out.println("Se actualizo TDA en DAO ");
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public void delete(Integer id) {
		deleteTipoDeAtraccion(id);
		deleteAtraccion(id);
	}

	public Atraccion find(Integer id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.findByID(id);
	}

////////////////////////////////////////////////////////////////////////////////

	private void deleteAtraccion(Integer id) {
		AtraccionDAO DAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = new Atraccion(id, null, 0, 0, 0, 0, 0, 0);
		DAO.delete(atraccion);
	}

	private void deleteTipoDeAtraccion(Integer id) {
		TipoDeAtraccionDAO DAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion = DAO.findByReferenceAndType(id, "Atraccion");
		DAO.delete(tipodeatraccion);
	}
	
	private void insertarAtraccionEnDAO(Atraccion atraccion) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.insert(atraccion);
	}
	
	private void crearTDAenDAO(Atraccion atraccion) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion1 = atraccionDAO.findByName(atraccion.getNombre());
		
		TipoDeAtraccionDAO tipodeatraccionDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion = new TipoDeAtraccion(atraccion1.getId(), "Atraccion", atraccion.getPreferenciaString());
		
		tipodeatraccionDAO.insert(tipodeatraccion);
	}
	
	private void actualizarTDAenDAO(Atraccion atraccion) {
		TipoDeAtraccionDAO tipodeatraccionDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion tipodeatraccion = tipodeatraccionDAO.findByReferenceAndType(atraccion.getId(), "Atraccion");
		
		tipodeatraccion.asignarPreferencia(atraccion.getPreferenciaString());
		
		tipodeatraccionDAO.update(tipodeatraccion);
	}

}
