package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Controller.CambioPasswordServlet;
import Controller.RipristinoPassword;
import DBConnection.DriverManagerConnectionPool;
import Model.Docente;
import Model.DocenteModel;
import Model.Studente;
import Model.StudenteModel;
import Model.Utente;
import Model.UtenteModel;

public class tc3_0_Ripristino_Password {
	
	static RipristinoPassword myServlet;
	
	@Mock
	static HttpServletRequest request;
	
	@Mock
	static HttpServletResponse response;
	
	@Mock
	static HttpSession session;
	@Mock
	static Utente utente;

	@BeforeAll
	static public void beforeEachTestCase() throws SQLException{
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		myServlet = new RipristinoPassword();
		UtenteModel md = new UtenteModel();	
		utente = md.doRetrieveByKey("05121");
	}
	
	@AfterAll
	static public void afterTestCase() throws SQLException{
		
		StudenteModel sm = new StudenteModel();
		
		sm.doDeleteByEmail("carminesorrentino51@studenti.unisa.it");
		
	}
	
	
	
	@Test
	public void tc_3_0_1() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
	
		
		when(request.getParameter("email")).thenReturn("c@libero.it");
		when(response.getWriter()).thenReturn(out);
		
		
		myServlet.doPost(request, response);
		
		System.out.println(output.toString());
		
		
		assertEquals("non corretto",output.toString().toString());
	}
	
	
	@Test
	public void tc_3_0_2() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
	
		
		when(request.getParameter("email")).thenReturn("carminesorrentino50carminesorrentin@studenti.unisa.it");
		when(response.getWriter()).thenReturn(out);
		
		
		myServlet.doPost(request, response);
		
		System.out.println(output.toString());
		
		
		assertEquals("non corretto",output.toString().toString());
	}
	
	
	@Test
	public void tc_3_0_3() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
	
		
		when(request.getParameter("email")).thenReturn("carminesorrentino50@gmail.com");
		when(response.getWriter()).thenReturn(out);
		
		
		myServlet.doPost(request, response);
		
		System.out.println(output.toString());
		
		
		assertEquals("non corretto",output.toString().toString());
	}
	
	
	@Test
	public void tc_3_0_4() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
	
		
		when(request.getParameter("email")).thenReturn("carminesorrentino50@studenti.unisa.it");
		when(response.getWriter()).thenReturn(out);
		
		 
		myServlet.doGet(request, response);
		
		System.out.println(output.toString());
		
		
		assertEquals("L'indirizzo email non esiste nel db",output.toString().toString());
	}
	
	@Test
	public void tc_3_0_5() throws IOException, ServletException, SQLException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		StudenteModel sm = new StudenteModel();
		
		
		
		Studente s = new Studente();
		
		s.setEmail("carminesorrentino51@studenti.unisa.it");
		
		s.setMatricola("0000055555");
		sm.doDeleteByEmail("carminesorrentino51@studenti.unisa.it");
		
		sm.doDeleteByEmail("carminesorrentino51@studenti.unisa.it");
		
		sm.doSave(s);
		
			
	
		when(request.getParameter("email")).thenReturn("carminesorrentino51@studenti.unisa.it");
		when(response.getWriter()).thenReturn(out);
		
		
		myServlet.doPost(request, response);
		
		System.out.println(output.toString());
		
		sm.doDeleteByEmail("carminesorrentino51@studenti.unisa.it");
		
		
		assertEquals("email inviato",output.toString().toString());
	}
}