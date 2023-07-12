package persistence;

import java.util.List;

import model.Itinerario;
import persistence.commons.GenericDAO;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByID (int idItinerario);
	
	public abstract List<Itinerario> findAllAttractionsOfUser (int idUsuario);
	
	//public abstract Itinerario findByIDAttr (Integer idAttraccion);

}

