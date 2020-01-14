package Testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Controller.*;
import Model.*;
import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;


class AccettaRicevimentoTesting {
	
AccettaRicevimentoServlet myServlet;

StudenteModel sm= new StudenteModel();
DocenteModel dm= new DocenteModel();
RicevimentoModel rm= new	RicevimentoModel();

	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	HttpSession session;
	
	@Mock
	ServletContext context;

   Docente docente;
	@BeforeEach 
	public void beforeEachTestCase() throws SQLException{
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		

		myServlet = new AccettaRicevimentoServlet();
		
	
		 docente= new Docente("Alessandro","Leopardi", "0512105470", "alex", "a.leopardi@unisa.it","stanza D");
		Studente studente = new Studente("Alessandro","Leopardi", "0512105477", "alex", "a.leopardi@studenti.unisa.it");
		Date d= new Date();
		Date d2= new Date();
		dm.doSave(docente);
		sm.doSave(studente);
		Ricevimento r= new Ricevimento("Non acettato", d, d2, 21, docente.getMatricola(),studente.getMatricola());
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		c.set(2023, 4, 23);
		rm.doSave(r, c);
	}
	
	@AfterEach
	 public void afterEachTestCase() throws SQLException {
		
		
		
		dm.doDelete("0512105470");
		sm.doDelete("0512105477");
		//Ricevimento r= (Ricevimento) rm.doRetrieveAllByDoc("0512105470");
		//rm.doDelete(r.getId());
		
		
		
		
		
	}
	
	@Test
	public void tc_8_0_1() throws SQLException, ServletException, IOException
	{
		

		Collection<Ricevimento> r=  rm.doRetrieveAllByDoc("0512105470");
		LinkedList<Ricevimento> l=(LinkedList<Ricevimento>)r;
		when(request.getParameter("id")).thenReturn(l.get(0).getId()+"");
		when(request.getParameter("operazione")).thenReturn("1");
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("docente")).thenReturn(docente);
		
		myServlet.doPost(request, response);
	}

	@Test
	public void tc_8_0_2() throws SQLException, ServletException, IOException
	{
		Collection<Ricevimento> r=  rm.doRetrieveAllByDoc("0512105470");
		LinkedList<Ricevimento> l=(LinkedList<Ricevimento>)r;
		when(request.getParameter("id")).thenReturn(l.get(0).getId()+"");
		when(request.getParameter("operazione")).thenReturn("2");
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("docente")).thenReturn(docente);
		myServlet.doPost(request, response);
	}
}
