package Controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneMail.EmailUtility;
import gestioneMail.GestioneMail;
import gestioneMail.GestioneMailConcrete;
import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;;

/**
 * 
 * @author Alfredo
 *
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private String host;
	private String port;
	private String user;
	private String pass;

	/**
	 * metodo di configurazione, legge i dati dal file web.xml 
	 */
	public void init() {
		ServletContext context = getServletContext();
		host = context.getInitParameter("host"); 
		port = context.getInitParameter("port");
		user = context.getInitParameter("user"); //indirizzo email mittente (smartbookingplatform@gmail.com)
		pass = context.getInitParameter("pass"); //password email mittente (SmartAcale)
	}

	private static GestioneMail gestioneMail = new GestioneMailConcrete();

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// lettura parametri ricevuti dal form
		String emailMittente = request.getParameter("emailMittente"); //Email Mittente
		String emailDestinatario = request.getParameter("emailDestinatario"); //Email Destinatario
		String nome = request.getParameter("name"); //Nome 
		String cognome = request.getParameter("surname"); //Cognome
		String subject = request.getParameter("subject"); //Oggetto
		String mex = request.getParameter("message"); //Messaggio 

		
		String resultMessage = "";

		
		//Crea contenuto messaggio da inviare
		String indexEmail="Gentile "+emailDestinatario+" hai ricevuto una nuova mail attraverso il sistema SmartBooking, di seguito tutte le info:";
		String indexMitt="EMAIL MITTENTE: "+emailMittente;
		String indexNome="NOME: "+nome;
		String indexCognome="COGNOME: "+cognome;
		String indexOggetto="OGGETTO : "+subject;
		String indexMex="MESSAGGIO : "+mex;
		
		String content= indexEmail +"\n"+ indexMitt + "\n"+ indexNome + " " + indexCognome+ "\n" + indexOggetto +"\n\n"+indexMex;
				
		
		try {
			gestioneMail.sendEmail(host, port, user, pass, emailMittente, emailDestinatario, nome, cognome, subject, content); //invio email di EmailUtility
			resultMessage = "The e-mail was sent successfully"; //setta il messaggio di buona riuscita dell'invio
			response.sendRedirect("./jsp/FormEmail.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage(); //altrimenti crea un messaggio di errore
		} finally {
			//**Cosa fare dopo aver fatto il sendMail***
		} 
	}
}