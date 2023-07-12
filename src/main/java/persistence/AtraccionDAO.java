package persistence;

import java.util.List;

import model.Atraccion;
import persistence.commons.GenericDAO;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByID(Integer id);
	
	public abstract Atraccion findByName(String name);
	
	public abstract int findAttractionID(String name);

	public abstract List<Atraccion> findAll();

	public abstract List<Atraccion> buscarAtraccion(int atra) throws Exception;

}
