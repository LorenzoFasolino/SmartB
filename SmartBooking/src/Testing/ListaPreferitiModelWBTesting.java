package Testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.GestisciRischiesteModOrarioServlet;
import DBConnection.DriverManagerConnectionPool;
import Model.Docente;
import Model.DocenteModel;
import Model.ListaPreferitiModel;
import Model.RichiestaModOrario;
import Model.RichiestaModOrarioModel;
import Model.Studente;
import Model.StudenteModel;
import gestioneOrari.GestioneOrari;
import gestioneOrari.GestioneOrariConcrete;
import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;

class ListaPreferitiModelWBTesting {
	
	static Docente doc = new Docente("Alfredo", "Raimondo", "0512105334", "12345678", "a.raimondo52@unisa.it", "uff01");
	
	static Studente s = new Studente("Alfredo", "Raimondo", "0512995215", "12345678", "a.raimondo12@studenti.unisa.it");
	
	static ListaPreferitiModel lm = new ListaPreferitiModel();
	
	@BeforeAll
	static public void beforeEachTestCase1() throws SQLException{
		DocenteModel dm = new DocenteModel();
		
		dm.doSave(doc); 
		
		StudenteModel sm = new StudenteModel();
		
		sm.doSave(s);
		
		
		
	}
	
	@AfterAll
	static public void afterall() throws SQLException{
		DocenteModel dm = new DocenteModel();
		
		dm.doDelete(doc.getMatricola());
		
		StudenteModel sm = new StudenteModel();
		
		sm.doDelete(s.getMatricola());
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "DELETE FROM Lista_preferiti WHERE matricola_studente = ?";
		
		connection = DriverManagerConnectionPool.getDbConnection();
		preparedStatement = connection.prepareStatement(insertSQL);
		
		preparedStatement.setString(1,"0512105215");

		preparedStatement.executeUpdate();
		
		connection.commit();
		
		DriverManagerConnectionPool.releaseConnection(connection);
		
		
		
	}

	@Test
	void tc1() throws SQLException {
		
		lm.addDocente(doc, s);
		
		LinkedList<Docente> docenti = (LinkedList<Docente>) lm.getAllDocenti(s);
		
		assertEquals(docenti.get(0).getMatricola(),doc.getMatricola());
		
	}
	
	@Test
	void tc2() throws Exception {
		
		assertEquals(true,lm.existIntoDB(doc.getMatricola(), s.getMatricola()));
		
	}
	
	@Test
	void tc3() throws Exception {
		
		assertEquals(false,lm.existIntoDB(doc.getMatricola(), "67890"));
		
	}
	
	@Test
	void tc4() throws Exception {
		
		lm.removeDocente(doc, s);
		
		LinkedList<Docente> docenti = (LinkedList<Docente>) lm.getAllDocenti(s);
		
		assertEquals(docenti.size(),0);
		
	}
	
	

}
