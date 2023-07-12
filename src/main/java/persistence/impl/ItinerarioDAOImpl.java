package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.*;
import model.Itinerario;
import persistence.ItinerarioDAO;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public int insert(Itinerario itinerario) {
		try {
			String sql = "INSERT INTO Itinerario (IdUsuario , IdAtraccion, descripcion) VALUES (?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, itinerario.getIdUsuario());
			statement.setInt(2, itinerario.getIdAtraccion());
			statement.setString(3, itinerario.getDescripcion());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Itinerario t) {
		// solo actuliza descripcion
		try {
			String sql = "UPDATE Itinerario SET descripcion = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, t.getDescripcion());
			statement.setInt(2, t.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Itinerario itinerario) {
		try {
			String sql = "DELETE FROM Itinerario WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, itinerario.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Itinerario";
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
	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT * FROM Itinerario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerarios = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerarios.add(toItinerarios(resultados));
			}

			return itinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Itinerario findByID(int idItinerario) {
		try {
			String sql = "SELECT * FROM Itinerario WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idItinerario);
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = null;

			if (resultados.next()) {
				itinerario = toItinerarios(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Itinerario> findAllAttractionsOfUser(int idUsuario) {
		try {
			String sql = "SELECT * FROM Itinerario WHERE idUsuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerarios = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerarios.add(toItinerarios(resultados));
			}

			return itinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Itinerario toItinerarios(ResultSet resultados) throws SQLException {
		return new Itinerario(resultados.getInt(1), resultados.getInt(2), resultados.getInt(3),
				resultados.getString(4));
	}

}
