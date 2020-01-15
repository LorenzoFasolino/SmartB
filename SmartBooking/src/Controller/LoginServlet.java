package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Docente;
import Model.Segreteria;
import Model.Studente;
import Model.Utente;
import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;

/**
 * Servlet richiamata da Login.jsp. Vengono letti i dati del form (email,password) e viene ricercata una corrispondenza
 * in una tabella tra: ACALE.Docente, ACALE.Studente, ACALE.Segreteria. Se viene trovata una corrispondenza viene
 * fatta una redirect sul profilo corrispondente.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text");
		
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		
		System.out.println(email);
		System.out.println(password);
		
		
		Utente utente = gestioneUtenti.loginUtente(email, password);

		String risposta="";
		
		if(utente==null){
			System.out.println("utente null");


			out.write("Errore");
			response.sendRedirect("./jsp/Login.jsp?err=1");  //se le credenziali sono sbagliate l'utente viene riportato sulla pagina di login
		}
		else {
		
			request.getSession().setAttribute("Utente",utente.getMatricola());
		
			System.out.println("Settato in sessione con nome 'user': "+utente);
		
			System.out.println(utente); 
		
			if(utente instanceof Model.Docente) {
				out.write("Docente");
				request.getSession().setAttribute("docente", (Docente) utente);
			
				response.sendRedirect("./jsp/InfoDocente.jsp?mat="+utente.getMatricola());  //profilo docente
			}else if(utente instanceof Model.Studente) {
				
				out.write("Studente"); 
			
				request.getSession().setAttribute("studente",(Studente) utente);  //'Utente'in sessione restituisce la matricola
			
				response.sendRedirect("./jsp/ProfiloStudente.jsp");  //profilo studente

			}else if(utente instanceof Model.Segreteria) {
				
				out.write("Segreteria");
				
				request.getSession().setAttribute("segreteria",(Segreteria) utente);
			
				response.sendRedirect("./jsp/ProfiloSegreteria.jsp");  //profilo segreteria

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
