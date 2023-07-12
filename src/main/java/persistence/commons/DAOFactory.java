package persistence.commons;

import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.TipoDeAtraccionDAO;
import persistence.UsuarioDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.TipoDeAtraccionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}

	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	  public static TipoDeAtraccionDAO getTipoDeAtraccionDAO() { return new
	  TipoDeAtraccionDAOImpl(); }
	  
	 

	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}

}
