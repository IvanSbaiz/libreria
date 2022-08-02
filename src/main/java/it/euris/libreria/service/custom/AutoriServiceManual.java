package it.euris.libreria.service.custom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.util.Database;

public class AutoriServiceManual {

	public Autori getById(Long id)
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {

		Autori autore = null;

		if (id != null) {

			String query = "SELECT * FROM autori WHERE id=?";

			Connection connection = Database.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				autore = new Autori();
				autore.setId(rs.getLong("ID"));
				autore.setNome(rs.getString("NOME"));
				autore.setCognome(rs.getString("COGNOME"));
				LibriServiceManual libriServiceManual = new LibriServiceManual();
				autore.setLibri(libriServiceManual.getByIdAutore(autore.getId()));
			}

			Database.closeConnection();
		}

		return autore;
	}

	public List<Autori> getAll()
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {

		String query = "SELECT * FROM autori";

		List<Autori> autoriList = new ArrayList<>();
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Autori autore = new Autori();
			autore.setId(rs.getLong("ID"));
			autore.setNome(rs.getString("NOME"));
			autore.setCognome(rs.getString("COGNOME"));
			LibriServiceManual libriServiceManual = new LibriServiceManual();
			autore.setLibri(libriServiceManual.getByIdAutore(autore.getId()));

			autoriList.add(autore);
		}

		Database.closeConnection();

		return autoriList;
	}

	public int insert(Autori autore)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {

		String query = "INSERT INTO autori (nome, cognome) VALUES (?,?)";

		Connection connection = Database.getConnection();

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, autore.getNome());
		pstmt.setString(2, autore.getCognome());
		int response = pstmt.executeUpdate();

		Database.closeConnection();
		return response;
	}

	public int update(Autori autore)
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {

		String query = "UPDATE autori SET nome=?, cognome=? WHERE id=?";
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, autore.getNome());
		pstmt.setString(2, autore.getCognome());
		pstmt.setLong(3, autore.getId());
		int response = pstmt.executeUpdate();

		Database.closeConnection();
		return response;
	}

	public void delete(Autori autore)
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {

		String query = "DELETE FROM autori WHERE id=?";
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setLong(1, autore.getId());
		pstmt.executeUpdate();
		Database.closeConnection();
	}

}
