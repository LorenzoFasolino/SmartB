package Testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import Controller.creaRichiestaModificaOrario;
import Model.Docente;
import Model.DocenteModel;
import Model.RichiestaModOrario;
import Model.RichiestaModOrarioModel;

public class TC6_0_RichiesteModificaOrario {
	creaRichiestaModificaOrario myServlet;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	HttpSession session;
	
	@Mock
	Docente docente;
	
	@Before
	public void beforeEachTestCase() throws SQLException{
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		myServlet = new creaRichiestaModificaOrario();
		
		DocenteModel md = new DocenteModel();
		
		md.doDelete("9992634518");
		
		docente = new Docente("nome","cognome","9992634518","1234","abbdbdbd@studenti.unisa.it","uff");
		
		md.doSave(docente);
		

	}
	
	@After
	public void after() throws SQLException{
		
		DocenteModel md = new DocenteModel();
		
		md.doDelete("9992634518");
		
		RichiestaModOrarioModel rm = new RichiestaModOrarioModel();
		
		rm.doDeleteByDoc("9992634518");
		
		
	}
	
	 
	@Test
	public void tc_6_0_1() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("");
		when(request.getParameter("giornoNuovo")).thenReturn("venerdi");
		when(request.getParameter("orarioInizio")).thenReturn("11:00");
		when(request.getParameter("orarioFine")).thenReturn("12:30");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Giorno da modificare non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_2() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("sabato");
		when(request.getParameter("giornoNuovo")).thenReturn("");
		when(request.getParameter("orarioInizio")).thenReturn("11:00");
		when(request.getParameter("orarioFine")).thenReturn("12:30");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Nuovo giorno non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_3() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("domenica");
		when(request.getParameter("giornoNuovo")).thenReturn("lunedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:4");
		when(request.getParameter("orarioFine")).thenReturn("12:30");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario inizio non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_4() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("lunedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:409");
		when(request.getParameter("orarioFine")).thenReturn("12:30");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario inizio non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_5() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("lunedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:4R");
		when(request.getParameter("orarioFine")).thenReturn("12:30");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario inizio non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_6() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("sabato");
		when(request.getParameter("orarioInizio")).thenReturn("11:45");
		when(request.getParameter("orarioFine")).thenReturn("12:3");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario fine non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_7() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("venerdì");
		when(request.getParameter("orarioInizio")).thenReturn("11:45");
		when(request.getParameter("orarioFine")).thenReturn("12:309");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario fine non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_8() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("mercoledì");
		when(request.getParameter("giornoNuovo")).thenReturn("giovedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:45");
		when(request.getParameter("orarioFine")).thenReturn("12:4R");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario fine non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_9() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("lunedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:45");
		when(request.getParameter("orarioFine")).thenReturn("10:45");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("Orario fine non corretto",output.toString().toString());
		
		
	}
	
	@Test
	public void tc_6_0_10() throws IOException, ServletException{
		StringWriter output = new StringWriter();
		PrintWriter out = new PrintWriter(output);
		
		when(request.getSession()).thenReturn(session);
		
		when(session.getAttribute("docente")).thenReturn(docente);
		
		when(request.getParameter("giornoVecchio")).thenReturn("martedì");
		when(request.getParameter("giornoNuovo")).thenReturn("lunedì");
		when(request.getParameter("orarioInizio")).thenReturn("11:45");
		when(request.getParameter("orarioFine")).thenReturn("12:45");
		
		
		when(response.getWriter()).thenReturn(out);
		
		myServlet.doPost(request, response);
		
		
		System.out.println(output.toString());
		
		
		assertEquals("",output.toString().toString());
		
		
	}
	
	
	

}
