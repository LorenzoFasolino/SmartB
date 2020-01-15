package Testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import DBConnection.DriverManagerConnectionPool;
import Model.Docente;
import Model.DocenteModel;
import Model.Studente;
import Model.StudenteModel;

class StudenteModel_WB {
	
	
	
	@Test
	  void testDoSaveDoDelete() throws Exception {
	    StudenteModel sm = new StudenteModel();
	    
	    Studente studente = new Studente("Alfredo", "Raimondo", "0112105211", "12345678", "a.raimondo12@unisa.it");
	    
	    sm.doSave(studente);
	    
	   
	    
	    assertEquals(sm.doRetrieveByKey(studente.getMatricola()).getMatricola(),"0112105211");

	   sm.doDelete(studente.getMatricola());
	    
	  }
	
	

	
	@Test
	  void testDoDeleteByEmail() throws Exception {

	    StudenteModel sm = new StudenteModel();
	    
	    Studente studente = new Studente("Alfredo", "Raimondo", "0512133212", "12345678", "a.lraimondo12@unisa.it");
	    sm.doSave(studente);
	    sm.doDeleteByEmail(studente.getEmail());
	    
	  
	    
	  }
	
	
	 @Test
	  void testExistMail() throws Exception {
		StudenteModel sm = new StudenteModel();
	    
	    Studente studente = new Studente("Alfredo", "Raimondo", "0512109215", "12345678", "a.raimondo12@unisa.it");
	    
	    sm.doSave(studente);
	    
	    assertTrue(sm.existIntoDB(studente.getEmail()));
	    
	    sm.doDelete(studente.getMatricola());
	    
	  }
	 
	 /*	 @Test
	  void testExistMail2() throws Exception {
		StudenteModel sm = new StudenteModel();
	    
	    Studente studente = new Studente("Alfredo", "Raimondo", "0512105215", "12345678", "a.raimondo12@unisa.it");
	    
	    
	    assertTrue(sm.existIntoDB(studente.getEmail()));
	    
	    
	  }
	*/ 
	 @Test
	  void testExistMatricola() throws Exception {
		StudenteModel sm = new StudenteModel();
	    
	    Studente studente = new Studente("Alfredo", "Raimondo", "0512705115", "12345678", "a.raimondo12@unisa.it");
	    
	    sm.doSave(studente);
	    
	    assertTrue(sm.existMatricolaIntoDB(studente.getMatricola()));
	    
	    sm.doDelete(studente.getMatricola());
	    
	  }
	 
	 
	 @Test
	  void testDoRetrieveAll() throws Exception {
	    StudenteModel sm = new StudenteModel();
	    
	    LinkedList<Studente> studentiList = (LinkedList<Studente>) sm.doRetrieveAll();
	    
	    
	    Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String countSQL = "SELECT * FROM ACALE.Studente";
		
		connection = DriverManagerConnectionPool.getDbConnection();
		preparedStatement = connection.prepareStatement(countSQL);
		
		
		
		ResultSet rs = preparedStatement.executeQuery();
		
		DriverManagerConnectionPool.releaseConnection(connection);
		int num = 0;
		while(rs.next()){
		 num = rs.getRow();
			
		}
	
		System.out.println(num);	    
	    assertEquals(studentiList.size(),num);
	    
	  }
	 
	 
	 @Test
	  void testDoRetrieveByKey() throws Exception {
	    StudenteModel sm = new StudenteModel();
	    Studente studente = new Studente("Alfredo", "Raimondo", "0512985999", "12345678", "a.raimondo12@unisa.it");
	    sm.doSave(studente);
	    		
	    Studente st = sm.doRetrieveByKey(studente.getMatricola());
	    
	 

	
	    assertEquals(studente.getMatricola(), st.getMatricola());
	    sm.doDelete(studente.getMatricola());
	    
	  }
	  

}
