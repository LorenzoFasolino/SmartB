package Testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import DBConnection.DriverManagerConnectionPool;
import Model.Docente;
import Model.DocenteModel;

public class DocenteModel_WB {
	
	@Test
	  void testDoRetrieveByNameAndSurname() throws Exception {
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doSave(docente);
	    
	    assertNotNull(dm.doRetrieveByNameAndSurname(docente.getNome(),docente.getCognome()).getMatricola());
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	
	
	@Test
	  void testDoRetrieveByNameAndSurname2() throws Exception {
		
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doDelete(docente.getMatricola());
	    
	    assertNull(dm.doRetrieveByNameAndSurname(docente.getNome(),docente.getCognome()).getMatricola());
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	
	
	  @Test
	  void testExistMail() throws Exception {
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doSave(docente);
	    
	    assertTrue(dm.existEmail(docente.getEmail()));
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	  
	  
	  @Test
	  void testExistMail2() throws Exception {
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doDelete(docente.getMatricola());
	    
	    assertFalse(dm.existEmail(docente.getEmail()));
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	  
	  
	  @Test
	  void testExistMatricola() throws Exception {
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doSave(docente);
	    
	    assertTrue(dm.existMatricola(docente.getMatricola()));
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	  
	  
	  @Test
	  void testExistMatricola2() throws Exception {
	    DocenteModel dm = new DocenteModel();
	    
	    Docente docente = new Docente("Carmine", "Raimondo", "0512104498", "9089098909", "carmine.raimondo12@unisa.it", "uff01");
	    
	    dm.doDelete(docente.getMatricola());
	    
	    assertFalse(dm.existMatricola(docente.getMatricola()));
	    
	    dm.doDelete(docente.getMatricola());
	    
	  }
	  
	  
	  
	  
	  
	  
	
	
	
	
	

}
