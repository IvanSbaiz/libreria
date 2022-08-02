package it.euris.libreria.service.custom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.util.Database;

public class LibriServiceManual {

	public Libri getById(Long id)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {

		Libri libro = null;

		if (id != null) {
			String query = "SELECT"
					     + "   l.id id"
						 + "   l.titolo titolo"
					     + "   l.isbn isbn"
					     + "   a.id idautore"
					     + "   a.nome nome"
					     + "   a.cognome cognome"
					     + " FROM libri l"
					     + " INNER JOIN autori a ON l.idautore=a.id"
					     + " WHERE l.id=?";

			Connection connection = Database.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				libro = new Libri();
				libro.setId(rs.getLong("ID"));
				libro.setTitolo(rs.getString("TITOLO"));
				libro.setIsbn(rs.getString("ISBN"));
				
				Autori autore = new Autori();
				autore.setId(rs.getLong("IDAUTORE"));
				autore.setNome(rs.getString("NOME"));
				autore.setCognome(rs.getString("COGNOME"));
				
				libro.setAutore(autore);
			}

			Database.closeConnection();
		}

		return libro;
	}
	
	public List<Libri> getAll()
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {
		
		String query = "SELECT"
				     + "   l.id id"
					 + "   l.titolo titolo"
				     + "   l.isbn isbn"
				     + "   a.id idautore"
				     + "   a.nome nome"
				     + "   a.cognome cognome"
				     + " FROM libri l"
				     + " INNER JOIN autori a ON l.idautore=a.id";
		
		List<Libri> libriList = new ArrayList<>();
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Libri libro = new Libri();
			libro = new Libri();
			libro.setId(rs.getLong("ID"));
			libro.setTitolo(rs.getString("TITOLO"));
			libro.setIsbn(rs.getString("ISBN"));
			Autori autore = new Autori();
			autore.setId(rs.getLong("IDAUTORE"));
			autore.setNome(rs.getString("NOME"));
			autore.setCognome(rs.getString("COGNOME"));
			libro.setAutore(autore);
			
			libriList.add(libro);
		}
		
		Database.closeConnection();
		
		return libriList;
	}
	
	public List<Libri> getByIdAutore(Long idAutore)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {
		
		List<Libri> libriList = new ArrayList<>();
		
		if (idAutore != null) {
			
			String query = "SELECT"
					     + "   l.id id"
						 + "   l.titolo titolo"
					     + "   l.isbn isbn"
					     + "   l.idautore idautore"
					     + " FROM libri l"
					     + " WHERE a.idautore=?";

			Connection connection = Database.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, idAutore);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Libri libro = new Libri();
				libro.setId(rs.getLong("ID"));
				libro.setTitolo(rs.getString("TITOLO"));
				libro.setIsbn(rs.getString("ISBN"));
				libriList.add(libro);
			}

			Database.closeConnection();
		}
		
		return libriList;
	}
	
	public List<Libri> getByNomeCognomeAutore(Autori autore)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {
		
		List<Libri> libriList = new ArrayList<>();
		
		if (autore != null) {
			
			String query = "SELECT"
					     + "   l.id id"
						 + "   l.titolo titolo"
					     + "   l.isbn isbn"
					     + "   a.id idautore"
					     + " FROM libri l"
					     + " INNER JOIN autori a ON l.idautore = a.id"
					     + " WHERE a.nome=? AND a.cognome=?";

			Connection connection = Database.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, autore.getNome());
			pstmt.setString(2, autore.getCognome());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Libri libro = new Libri();
				libro.setId(rs.getLong("ID"));
				libro.setTitolo(rs.getString("TITOLO"));
				libro.setIsbn(rs.getString("ISBN"));
				libriList.add(libro);
			}

			Database.closeConnection();
		}
		
		return libriList;
	}

	public int insert(Libri libro, Autori autore)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {

		String query = "INSERT INTO libri (titolo, idautore, isbn) VALUES (?,?,?)";

		Connection connection = Database.getConnection();

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, libro.getTitolo());
		pstmt.setLong(2, libro.getAutore().getId());
		pstmt.setString(3, libro.getIsbn());
		int response = pstmt.executeUpdate();

		Database.closeConnection();
		return response;
	}

	public int update(Libri libro, Autori autore)
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {

		String query = "UPDATE libri SET titolo=?, idautore=?, isbn=? WHERE id=?";
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, libro.getTitolo());
		pstmt.setLong(2, libro.getAutore().getId());
		pstmt.setString(3, libro.getIsbn());
		pstmt.setLong(4, libro.getId());
		int response = pstmt.executeUpdate();

		Database.closeConnection();
		return response;
	}

	public int updateGeneric(Libri libroUpdate, Libri libriWhere, Autori autore)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, JDOMException {

		Connection connection = Database.getConnection();
		String query = "UPDATE libri <SET> WHERE 1=1 ";
		String set = " SET ";
		int cont = 1;
		Map<String, String> hs = new HashMap<>();

		if (libroUpdate.getTitolo() != null) {
			set += "titolo=?";
			hs.put("titoloUpdate", libroUpdate.getTitolo());
		}

		if (libroUpdate.getAutore().getId() > 0) {
			if (!set.trim().equalsIgnoreCase("set")) {
				set += ", ";
			}
			set += "idautore=?";
			hs.put("autoreUpdate", String.valueOf(libroUpdate.getAutore().getId()));
		}

		query = query.replace("<SET>", set);

		if (libriWhere.getTitolo() != null) {
			query += " AND titolo=?";
			hs.put("titoloWhere", libroUpdate.getTitolo());
		}

		if (libriWhere.getAutore().getId() > 0) {
			query += " AND idautore=?";
			hs.put("autoreWhere", String.valueOf(libroUpdate.getAutore().getId()));
		}

		PreparedStatement pstmt = connection.prepareStatement(query);
		for (Map.Entry<String, String> entry : hs.entrySet()) {
			pstmt.setString(cont, entry.getValue());
			cont = cont + 1;
		}

		int response = pstmt.executeUpdate();

		Database.closeConnection();

		return response;
	}
	
	public void delete(Libri libro)
			throws FileNotFoundException, ClassNotFoundException, IOException, JDOMException, SQLException {
		
		String query = "DELETE FROM libri WHERE id=?";
		Connection connection = Database.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setLong(1, libro.getId());
		pstmt.executeUpdate();
		Database.closeConnection();
	}

}
