package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.*;

import model.Usuario;
import persistence.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO Usuario (usuario, contrasenia, dineroDisponible, "
					+ "tiempoDisponible, posicionX, posicionY, administrador) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getContrasenia());
			statement.setDouble(3, usuario.getDineroDisponible());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setInt(5, usuario.getPosicionX());
			statement.setInt(6, usuario.getPosicionY());
			statement.setInt(7, usuario.isAdministradorInt());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE Usuario SET usuario = ?, contrasenia = ?, dineroDisponible = ?,"
					+ " tiempoDisponible = ?, posicionX = ?, posicionY = ?, administrador = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getContrasenia());
			statement.setDouble(3, usuario.getDineroDisponible());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setInt(5, usuario.getPosicionX());
			statement.setInt(6, usuario.getPosicionY());
			statement.setInt(7, usuario.isAdministradorInt());
			statement.setInt(8, usuario.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM Usuario WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findById(int id) {
		try {
			String sql = "SELECT * FROM Usuario WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuarios(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findByUsername(String usuario1) {
		try {
			String sql = "SELECT * FROM Usuario WHERE usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario1);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuarios(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findUserId(String usuario, String contrasenia) {
		try {
			String sql = "SELECT * FROM Usuario WHERE usuario = ? AND contrasenia = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario);
			statement.setString(2, contrasenia);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario1 = null;

			if (resultados.next()) {
				usuario1 = toUsuarios(resultados);
			}

			return usuario1.getId();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM Usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuarios(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuarios(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
				resultados.getDouble(4), resultados.getDouble(5), resultados.getInt(6), resultados.getInt(7),
				resultados.getInt(8));
	}

}
