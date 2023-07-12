package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.*;
import model.Atraccion;
import model.Promocion;

public class PromocionDAOImpl implements PromocionDAO {

	public int insert(Promocion t) {
		try {
			String sql = "INSERT INTO Promocion (nombre, tipoDePromocion, costoTotal, descuentoPorcentual, atraccionA, atraccionB, atraccionP) VALUES (?,?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, t.getNombre());
			statement.setInt(2, t.getTipoDePromocion());
			statement.setDouble(3, t.getCosto());
			statement.setInt(4, t.getDescuentoPorcentual());

			int auxId;
			int contador = 5;

			List<Atraccion> atracciones = t.getAtracciones();
			for (Atraccion i : atracciones) {
				auxId = i.getId();
				statement.setInt(contador, auxId);
				contador++;
			}

			if (t.getTipoDePromocion() < 3) {
				statement.setInt(contador, 0);
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion t) {
		try {
			String sql = "UPDATE Promocion SET nombre = ?, tipoDePromocion = ?, costoTotal = ?, descuentoPorcentual = ?, atraccionA = ?, atraccionB = ?, atraccionP = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, t.getNombre());
			statement.setInt(2, t.getTipoDePromocion());
			statement.setDouble(3, t.getCosto());
			statement.setInt(4, t.getDescuentoPorcentual());

			int auxId;
			int contador = 5;

			List<Atraccion> atracciones = t.getAtracciones();
			for (Atraccion i : atracciones) {
				auxId = i.getId();
				statement.setInt(contador, auxId);
				contador++;
			}
			if (t.getTipoDePromocion() < 3) {
				statement.setInt(contador, 0);
			}
			statement.setInt(8, t.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion t) {
		try {
			String sql = "DELETE FROM Promocion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, t.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
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

	@Override
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM Promocion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromotion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion findByID(Integer id) {
		try {
			String sql = "SELECT * FROM Promocion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromotion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion findByPromotionName(String name) {
		try {
			String sql = "SELECT * FROM Promocion WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromotion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

////////////////////////////////////////////////////////////////////////////////

	private Promocion toPromotion(ResultSet resultados) throws SQLException {
		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		int tipoDeAtraccion = resultados.getInt(3);
		double costoTotal = resultados.getDouble(4);
		int descuentoPorcentual = resultados.getInt(5);

		Atraccion atraccion;
		List<Atraccion> atracciones = new ArrayList<>();

		int idAtraccionA = resultados.getInt(6);
		atraccion = buscarAtraccionSegunSuId(idAtraccionA);
		atracciones.add(atraccion);

		int idAtraccionB = resultados.getInt(7);
		atraccion = buscarAtraccionSegunSuId(idAtraccionB);
		atracciones.add(atraccion);

		int idAtraccionP = resultados.getInt(8);
		if (idAtraccionP > 0) {
			atraccion = buscarAtraccionSegunSuId(idAtraccionP);
			atracciones.add(atraccion);
		}

		return new Promocion(id, nombre, tipoDeAtraccion, costoTotal, descuentoPorcentual, atracciones);
	}

	private Atraccion buscarAtraccionSegunSuId(int Id) {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = attrctionDAO.findByID(Id);
		return atraccion;
	}

////////////////////////////////////////////////////////////////////////////////

}