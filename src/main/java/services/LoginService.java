package services;
import model.Usuario;
import model.nullobjects.*;
import persistence.UsuarioDAO;
import persistence.commons.*;

public class LoginService {
	public Usuario login(String usuario,String contrasenia) {
		UsuarioDAO userDao=DAOFactory.getUsuarioDAO();
		Usuario user= userDao.findByUsername(usuario);
		if(!user.checkPassword(contrasenia)) {
			user=NullUser.build();
		}
		return user;
		
	}

}
