package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import DBConnection.DriverManagerConnectionPool;

public class UtenteModel {

private static final String TABLE_NAME_STUD = "ACALE.Studente";
private static final String TABLE_NAME_DOC = "ACALE.Docente";
private static final String TABLE_NAME_SEG = "ACALE.Segreteria";









	/**
	 * 
	 * @param matricola: matricola del docente da ricercare
	 * @return bean docente con matricola uguale al parametro matricola
	 * @throws SQLException
	 */
	public synchronized Utente doRetrieveByKey(String matricola) throws SQLException{
		
		StudenteModel sm = new StudenteModel();
		DocenteModel dm = new DocenteModel();
		SegreteriaModel segm = new SegreteriaModel();
		
		Utente bean = sm.doRetrieveByKey(matricola);
		System.out.println(bean);
		if(bean.getMatricola() == null){
			bean = dm.doRetrieveByKey(matricola);
			System.out.println(bean);
		}if(bean.getMatricola() == null){
			bean = segm.doRetrieveByKey(matricola);
			System.out.println(bean);
		}
		
		return bean;
	}

	
	/**
	 * 
	 * @return collezione di tutti i docenti
	 * @throws SQLException
	 */
	
	
	/**
	 * 
	 * @param nuovaPassword
	 * @param utente
	 * @return
	 * @throws SQLException
	 */
	public synchronized void changePassword(String nuovaPassword, Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		DocenteModel dm = new DocenteModel();
		StudenteModel sm = new StudenteModel();
		
		String selectSQL = "";
		
		Docente d = dm.doRetrieveByKey(utente.getMatricola());
		
		//connection = DriverManagerConnectionPool.getDbConnection();
		
		
		if(d.getMatricola() != null) {
			selectSQL = "UPDATE Docente SET password='"+nuovaPassword+"' WHERE matricola='"+utente.getMatricola()+"';";
		}
		else {
			Studente s = sm.doRetrieveByKey(utente.getMatricola());
			
			selectSQL = "UPDATE "+ TABLE_NAME_STUD + " SET password = ? WHERE matricola = ?;";
		}
		
		try {
			//connection = DriverManagerConnectionPool.createDBConnection();
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nuovaPassword);
			preparedStatement.setString(2,utente.getMatricola());
			
			System.out.println(preparedStatement);

			int a = preparedStatement.executeUpdate();
			System.out.println(a);
			
			
			connection.commit();
			Docente u = dm.doRetrieveByKey(utente.getMatricola());
			System.out.println("Utente modificato "+u);

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
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public synchronized Utente retrieveByEmail(String email) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQLStudente = "SELECT * FROM " + UtenteModel.TABLE_NAME_STUD + " WHERE email = ?";
		String selectSQLDocente = "SELECT * FROM " + UtenteModel.TABLE_NAME_DOC + " WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getDbConnection();
			preparedStatement = connection.prepareStatement(selectSQLStudente);
			preparedStatement.setString(1,email);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Studente bean = new Studente();
				
				bean.setMatricola(rs.getString("matricola"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				
				return bean;
						
				
			}
				//controlliamo nei docenti
				connection = DriverManagerConnectionPool.getDbConnection();
				preparedStatement = connection.prepareStatement(selectSQLDocente);
				preparedStatement.setString(1,email);
				
				rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Docente bean = new Docente();
					
					bean.setMatricola(rs.getString("matricola"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setPassword(rs.getString("password"));
					bean.setEmail(rs.getString("email"));
					
					return bean;
				} 
				
				return null;
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
	 * 
	 * Il metodo checkLogin(String email,String password) effettua una query nel database nelle tabelle Docente, Studente e Segreteria e cerca
	 * una tupla con la corrispondenza email/password.
	 * Se la corrispondenza viene soddisfatta restituisce l'Utente, altrimenti restituisce null.
	 * 
	 * @param email
	 * @param password
	 * @return Utente u
	 * @throws Exception
	 */
	
	
public synchronized Utente checkLogin(String email,String password) throws Exception{
		
	Connection connection = null;
	Studente s = new Studente();
	Docente d = new Docente();
	Segreteria seg = new Segreteria();
	PreparedStatement preparedStatement = null;

	String selectSQLStudente = "SELECT * FROM " + UtenteModel.TABLE_NAME_STUD + " WHERE email = ? AND password = ?";
	String selectSQLDocente = "SELECT * FROM " + UtenteModel.TABLE_NAME_DOC + " WHERE email = ? AND password = ?";
	String selectSQLSegreteria = "SELECT * FROM " + UtenteModel.TABLE_NAME_SEG + " WHERE email = ? AND password = ?";
	
	try {
		connection = DriverManagerConnectionPool.getDbConnection();
		preparedStatement = connection.prepareStatement(selectSQLStudente);
		preparedStatement.setString(1,email);
		preparedStatement.setString(2,password);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			
			s.setMatricola(rs.getString("matricola"));
			s.setNome(rs.getString("nome"));
			s.setCognome(rs.getString("cognome"));
			s.setPassword(rs.getString("password"));
			s.setEmail(rs.getString("email"));
			
		}
		
		//controlliamo nei docenti
		connection = DriverManagerConnectionPool.getDbConnection();
		preparedStatement = connection.prepareStatement(selectSQLDocente);
		preparedStatement.setString(1,email);
		preparedStatement.setString(2, password);
		rs = preparedStatement.executeQuery();
		while(rs.next()) {
				
			d.setMatricola(rs.getString("matricola"));
			d.setNome(rs.getString("nome"));
			d.setCognome(rs.getString("cognome"));
			d.setPassword(rs.getString("password"));
			d.setEmail(rs.getString("email"));
				
		} 
		
		//controllo nella segreteria
		connection = DriverManagerConnectionPool.getDbConnection();
		preparedStatement = connection.prepareStatement(selectSQLSegreteria);
		preparedStatement.setString(1,email);
		preparedStatement.setString(2, password);
		rs = preparedStatement.executeQuery();
		while(rs.next()) {
				
			seg.setMatricola(rs.getString("matricola"));
			seg.setNome(rs.getString("nome"));
			seg.setCognome(rs.getString("cognome"));
			seg.setPassword(rs.getString("password"));
			seg.setEmail(rs.getString("email"));
				
		} 
		
		if(s.getEmail()!=null && s.getPassword()!=null)
			return s;
		if(d.getEmail()!=null && d.getPassword()!=null)
			return d;
		if(seg.getEmail()!=null && seg.getPassword()!=null)
			return seg;
		else 
			return null;
		
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
}
}
