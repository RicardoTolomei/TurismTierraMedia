package services;

import java.util.HashMap;
import java.util.Map;

import model.Promocion;
import model.Usuario;

import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class ComprarPromocionesService {

	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
	public Map<String, String> buy(Integer userId, Integer promocionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.findById(userId);
		Promocion promocion = promocionDAO.findByID(promocionId);

		
		if (!user.puedeComprarPromocion(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.tieneTiempo(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			

			// no grabamos para no afectar la base de pruebas
			promocionDAO.update(promocion);
			userDAO.update(user);
		}

		return errors;

	}

}

