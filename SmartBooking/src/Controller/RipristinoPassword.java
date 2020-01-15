package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneMail.GestioneMail;
import gestioneMail.GestioneMailConcrete;
import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;

/**
 * Servlet implementation class RipristinoPassword
 */
@WebServlet("/RipristinoPassword")
public class RipristinoPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
	private static GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RipristinoPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		
		String risposta = "";
		String risposta2 = "";
		
		String email = request.getParameter("email");
		
		
		
		

		risposta = Check.checkStudenteMail(email);	
		
		System.out.println("Risposta: "+risposta);
		
		try {
			System.out.println("try: ");
			risposta2 = Check.checkMailDocente(email);
			System.out.println("Risposta2: "+risposta2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if(risposta.equals("gia esiste") || risposta2.equals("gia esiste")){
			
			risposta = "";
			
		}else if(risposta.equals("ok") || risposta2.equals("ok")){
			
			risposta = "ok";
			 
		}else {
			
			risposta = "non corretto";
			
		}
		
		
		

		if(risposta.equals("ok")) {
			String risp ="";
			risp = "L'indirizzo email non esiste nel db";
			writer.write(risp);	
			return;
		}
		if(risposta.equals("non corretto")) {
			writer.write(risposta);
			return;
		}
		String url = gestioneUtenti.ripristinaPasswordEmail(email);
		
		
		
		//*******INVIO EMAIL********//
		//Invio email con credenziali
		String emailMittente="smartbookingplatform@gmail.com";
		String emailDestinatario= email; //email;
		String subject="Benvenuto Su SmartBooking";
		
		//Creazione messaggio(content) da inviare 
		String indexEmail = "Ecco il link per il ripristino della password:\n"+ url;
		
		String content= indexEmail;
		
		
		String resultMessage = "";

		try {
			gestioneMail.sendEmail(host, port, user, pass, emailMittente, emailDestinatario, "", "", subject, content); //invio email di EmailUtility
			resultMessage = "The e-mail was sent successfully"; //setta il messaggio di buona riuscita dell'invio
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage(); //altrimenti crea un messaggio di errore
		} finally {
			//**Cosa fare dopo aver fatto il sendMail***
		} 
	
		
		
		risposta = "email inviato";
		writer.write(risposta);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
