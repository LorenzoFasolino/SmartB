package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import DBConnection.DriverManagerConnectionPool;

public class StudenteModel {
	
private static final String TABLE_NAME = "Studente";
	
	/**
	 * @param Studente
	 * @throws SQLException
	 */
	public synchronized void doSave(Studente Studente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + StudenteModel.TABLE_NAME
				+ " (matricola, nome, cognome, email,password) VALUES (?, ?, ?, ?, ? )";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, Studente.getMatricola());
			preparedStatement.setString(2, Studente.getNome());
			preparedStatement.setString(3, Studente.getCognome());	
			preparedStatement.setString(4, Studente.getEmail());
			preparedStatement.setString(5, Studente.getPassword());	

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	/**
	 * @param matricola
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean doDelete(String matricola) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + StudenteModel.TABLE_NAME + " WHERE matricola = ? ";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, matricola);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	
	public synchronized boolean doDeleteByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + StudenteModel.TABLE_NAME + " WHERE email = ? ";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	
	
	/**
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public synchronized Collection<Studente> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Studente> utenti = new LinkedList<Studente>();

		String selectSQL = "SELECT * FROM " + StudenteModel.TABLE_NAME;

		

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Studente bean = new Studente();

				bean.setMatricola("matricola");
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}
	
	
	/**
	 * @param matricola
	 * @return
	 * @throws SQLException
	 */
	public synchronized Studente doRetrieveByKey(String matricola) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Studente bean = new Studente();

		String selectSQL = "SELECT * FROM " + StudenteModel.TABLE_NAME + " WHERE matricola = ?";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, matricola);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setMatricola(rs.getString("matricola"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	
	/**
	 * @return
	 * @throws SQLException
	 */
	
	
	public synchronized Boolean existIntoDB(String email) throws Exception{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Studente bean = new Studente();

		String selectSQL = "SELECT * FROM " + StudenteModel.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setMatricola("matricola");
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		if(bean.getEmail()==null)
			return false;
		else
			return true;
		
	}
	
	
	/**
	 * Metodo che prende in input la matricola di uno studente e restituisce true nel caso in cui lo studente è già presente nel DB, false altrimenti.
	 * @param matricola
	 * @return boolean
	 * @throws SQLException 
	 */

	public boolean existMatricolaIntoDB(String matricola) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Studente bean = new Studente();

		String selectSQL = "SELECT * FROM " + StudenteModel.TABLE_NAME + " WHERE matricola = ?";

		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, matricola);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setMatricola("matricola");
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		if(bean.getMatricola()==null)
			return false;
		else
			return true;
		
	}

}
