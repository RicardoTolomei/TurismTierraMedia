package persistence;

import model.Usuario;
import persistence.commons.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findById(int id);

	public abstract Usuario findByUsername(String username);

	public abstract int findUserId(String username, String password);

}
