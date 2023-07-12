package persistence;

import model.Promocion;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public abstract Promocion findByID(Integer id);

	public abstract Promocion findByPromotionName(String name);

}
