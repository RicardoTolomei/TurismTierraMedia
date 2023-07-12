package persistence;

import java.util.List;

import model.TipoDeAtraccion;
import persistence.commons.GenericDAO;

public interface TipoDeAtraccionDAO extends GenericDAO<TipoDeAtraccion> {

	public abstract TipoDeAtraccion findByID(int IdTipoAtraccion);

	public abstract TipoDeAtraccion findByReferenceAndType(int reference, String type);

	public abstract int countOnlyObjectsOfOneType(String objctType);
	// ejemplo, cuenta la cantidad de usuarios/ atracciones/ promociones.
	
	public abstract List<TipoDeAtraccion> findOnlyObjectsOfOneType(String objctType);

}
