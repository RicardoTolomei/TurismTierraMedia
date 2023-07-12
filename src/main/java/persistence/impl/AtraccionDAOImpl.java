package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.*;
import model.Atraccion;
import persistence.AtraccionDAO;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO Atraccion (nombre, costo, duracion, cupoMaximo, posicionX, posicionY) "
					+ "VALUES (?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getDuracion());
			statement.setInt(4, atraccion.getCupoMaximo());
			statement.setInt(5, atraccion.getPosicionX());
			statement.setInt(6, atraccion.getPosicionY());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE Atraccion SET nombre = ?, costo = ?, duracion = ?, cupoActual = ?, cupoMaximo = ?, posicionX = ?, posicionY = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getDuracion());
			statement.setInt(4, atraccion.getCupoActual());
			statement.setInt(5, atraccion.getCupoMaximo());
			statement.setInt(6, atraccion.getPosicionX());
			statement.setInt(7, atraccion.getPosicionY());
			statement.setInt(8, atraccion.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM Atraccion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, atraccion.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion findByID(Integer id) {
		try {
			String sql = "SELECT * FROM Atraccion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtracciones(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion findByName(String name) {
		try {
			String sql = "SELECT * FROM Atraccion WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtracciones(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findAttractionID(String name) {
		try {
			String sql = "SELECT * FROM Atraccion WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtracciones(resultados);
			}

			return atraccion.getId();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Atraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atraccion = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atraccion.add(toAtracciones(resultados));
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Atraccion";
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

	public List<Atraccion> buscarAtraccion(int atra) throws Exception {
		try {
			String sql = "SELECT * FROM Atraccion a JOIN Atraccion u  ON WHERE CALIFICACION = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atra);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> peliculas = new ArrayList<Atraccion>();

			while (resultados.next()) {

				peliculas.add(toAtracciones(resultados));
			}
			return peliculas;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	private Atraccion toAtracciones(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3),
				resultados.getDouble(4), resultados.getInt(5), resultados.getInt(6), resultados.getInt(7),
				resultados.getInt(8));

	}

}
