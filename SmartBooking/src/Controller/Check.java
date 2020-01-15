package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import gestioneUtenti.GestioneUtenti;
import gestioneUtenti.GestioneUtentiConcrete;


/**
 * 
 * @author carminesorrentino
 * 
 * La classe Check è una classe contenente una serie di metodi statici per la validazione dei vari campi compilati dagli utenti.
 *
 */

public class Check {
	
	/**
	 * Costruttore della classe Check(), ottiene un riferimento al file "log.txt".
	 * @throws IOException
	 */
	
	public Check() throws IOException {
		file = new FileWriter("log.txt");
	}

	/**
	 * Il metodo checkMatricolaStudente(String matricola) verifica se all'interno del database, nella tabella ACALE.Studente esiste uno studente con matricola = String matricola passata come argomento.
	 * Se esiste restituisce la stringa "gia esiste", se la matricola passata come argomento non rispetta il formato [0-9]{10} restituisce la stringa "non corretto", altrimenti restituisce "ok".
	 * @param Sring matricola
	 * @return String result
	 */
	public static String checkMatricolaStudente(String matricola) {
		
		GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
		
		if(matricola.matches("[0-9]{10}")) {
			if(gestioneUtenti.cercaMatricola(matricola))
				return "ok";
			else
				return "gia esiste";
		}
		else
			return "non corretto";
	}
	
	/**
	 * Il metodo checkNome(String nome) verifica se String nome passato come argomento al metodo rispetta il formato definito tramite l'espressione regolare [A-Za-z]{1,10}.
	 * Se il formato è corretto, restituisce true, altrimenti false.
	 * @param nome
	 * @return boolean b
	 */
	public static boolean checkNome(String nome) {
		if(nome.matches("[A-Za-z]{1,10}"))
			return true;
		else 
			return false;
	}
	
	/**
	 * Il metodo checkCognome(String cognome) verifica se String cognome passato come argomento al metodo rispetta il formato definito tramite l'espressione regolare [A-Za-z]{1,10}.
	 * Se il formato è corretto, restituisce true, altrimenti false.
	 * @param nome
	 * @return boolean b
	 */
	public static boolean checkCognome(String cognome) {
		if(cognome.matches("[A-Za-z]{1,20}"))
			return true;
		else
			return false;
	}
	
	/**
	 * Il metodo checkStudenteMail(String email) verifica se all'interno del database, nella tabella ACALE.Studente esiste uno studente con email = String email passata come argomento.
	 * Se esiste restituisce la stringa "gia esiste", se l'indirizzo email passato come argomento non rispetta il formato \\w+([\\.-]?\\w+)*@studenti[.]{1}unisa[.]{1}it e la lunghezza 20<=length<=50 restituisce la stringa "non corretto", altrimenti restituisce "ok".
	 * @param String matricola
	 * @return String result
	 */
	public static String checkStudenteMail(String email){
		
			GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
		
			if(email.matches("\\w+([\\.-]?\\w+)*@studenti[.]{1}unisa[.]{1}it")  &&  email.length()>=20 &&  email.length() <=50) {
				if(gestioneUtenti.cercaEmail(email))
					return "ok";
				else
					return "gia esiste";
			}
			else 
				return "non corretto";
		}

	
	/**
	 * Verifica la correttezza del campo matricola
	 * @param matricola matricola da verificare
	 * @return "ok" se l'email inserita � valida, "gia esiste" se � gi� presente nel database, "non corretto" se l'email non � corretta
	 * @throws Exception 

	 */
	public static String checkMatricolaDocente(String matricola) throws Exception {
		
		GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
		
		if(matricola.matches("[0-9]{10}")) {
			if(!gestioneUtenti.checkMatricolaDocente(matricola))
				return "ok";
			else
				return "gia esiste";
		}
		else
			return "non corretto";
	}
	
	
/** Il metodo checkMailDocente(String email) verifica se all'interno del database, nella tabella ACALE.Docente esiste uno docente con email = String email passata come argomento.
* Se esiste restituisce la stringa "gia esiste", se l'indirizzo email passato come argomento non rispetta il formato \\w+([\\.-]?\\w+)*@studenti[.]{1}unisa[.]{1}it e la lunghezza 20<=length<=50 restituisce la stringa "non corretto", altrimenti restituisce "ok".
* @param String email
* @return boolean b
 * @throws Exception 
*/
	public static String checkMailDocente(String email) throws Exception{
		
		GestioneUtenti gestioneUtenti = new GestioneUtentiConcrete();
/*		
		if(email.matches("\\w+([\\.-]?\\w+)*@unisa[.]{1}it")) {
			System.out.println("******ok*********");
			System.out.println(email.length());
		}
		else {
			System.out.println("/////no//////");
			System.out.println(email.length());
		}
		email.matches("\\w+([\\.-]?\\w+)*@unisa[.]{1}it")  &&
	*/
		
		System.out.println("entro:");
		if( email.length()>=20 &&  email.length()<=50) {
			
			if(email.matches("\\w+([\\.-]?\\w+)*@unisa[.]{1}it")) {
			
			
			System.out.println("EMAIL L:"+email.length());
			if(gestioneUtenti.checkEmailDocente(email)) {
				System.out.println("OKOKOKOKOK"+email);
				return "ok";
			}	
			else{
				return "gia esiste"; 
			}
			
			}else{
				return "non corretto";
				
			}
			
			
		}else{ 
			System.out.println("Elseeeee:");
			return "non corretto";
		} 
	}

	
	/**
	 * Il metodo checkPassword(String password) verifica se String password passato come argomento al metodo rispetta il formato definito tramite l'espressione regolare [A-Za-z0-9]{8,20}.
	 * Se il formato è corretto, restituisce true, altrimenti false.
	 * @param String password
	 * @return boolean b
	 */
	public static boolean checkPassword(String password) {
		if(password.matches("[A-Za-z0-9]{8,20}"))
			return true;
		else
			return false;
	}
	
	/**
	 * Il metodo checkConfermaPassword(String password, String conferma) verifica se String password passato come argomento al metodo rispetta il formato definito tramite l'espressione regolare [A-Za-z0-9]{8,20} e se coincide con la stringa 'password'.
	 * Se entrambe le condizioni sono soddisfatte restituisce true, altrimenti false.
	 * @param String password
	 * @return boolean b
	 */
	public static boolean checkConfermaPassword(String password,String conferma) {
		if(conferma.matches("[A-Za-z0-9]{8,20}") && conferma.contentEquals(password))
			return true;
		else
			return false;
	}
	
	/**
	 * Il metodo checkUfficio(String ufficio) verifica se String ufficio passato come argomento al metodo rispetta il formato definito tramite l'espressione regolare [A-Za-z0-9]{1,20}.
	 * Se entrambe le condizioni sono soddisfatte restituisce true, altrimenti false.
	 * @param String password
	 * @return boolean b
	 */
	public static boolean checkUfficio(String ufficio) {
		if(ufficio.matches("[A-Za-z0-9]{1,20}"))
			return true;
		else
			return false;
	}
	
	/**
	 * Il metodo checkGiorno(String giorno) controlla se il giorno passato come parametro è un giorno della settimana.
	 * @param String giorno
	 * @return boolean b
	 */
		public static boolean checkGiorno(String giorno){
			
			if(giorno.equalsIgnoreCase("lunedi"))
			{
				return true;
			}
			if(giorno.equalsIgnoreCase("lunedì"))
			 return true;
			
			if(giorno.equalsIgnoreCase("martedi")) {
				return true;
			}
			if(giorno.equalsIgnoreCase("martedì")) return true;
			
			if(giorno.equalsIgnoreCase("mercoledi") ){
				return true;
			}
			if(giorno.equalsIgnoreCase("mercoledì")) return true;
			
			if(giorno.equalsIgnoreCase("giovedi")){
				return true;
			}
			if(giorno.equalsIgnoreCase("giovedì")) return true;
			
			if(giorno.equalsIgnoreCase("venerdi")){
				return true;
			}
			if(giorno.equalsIgnoreCase("venerdì")) return true;
			
			if(giorno.equalsIgnoreCase("sabato"))
			{
				return true;
			}
			
			if(giorno.equalsIgnoreCase("domenica")){
				return true;
			}
			
			return false;
			
		}
		
		
		/**
		 * Il metodo checkOra(String ora) controlla se il parametro String ora passato al metodo rispetta il formato specificato dall' espressione regolare ^([01]\\d|2[0-3]):([0-5]\\d)$.
		 * @param ora
		 * @return
		 */
			public static boolean checkOra(String ora){
					
					if(ora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
						
						return true;
						
					}
					
					return false;
					
				}
			
			/**
			 * Il metodo checkOraInizioOraFine(String oraInizio, String oraFine) verifica che l'ora di inizio precede l'ora di fine.
			 * @param String oraInizio
			 * @param String oraFine
			 * @return boolean b
			 */
			public static boolean checkOraInizioOraFine(String oraInizio, String oraFine){
				
				if(oraInizio.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")  &&  oraFine.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
					
					float temp2 = Float.parseFloat(oraInizio.substring(3,5));
					float temp = Float.parseFloat(oraInizio.substring(0,2));
					
					float inizio = temp +(temp2/100);
					
					temp2 = Float.parseFloat(oraFine.substring(3,5));
					temp = Float.parseFloat(oraFine.substring(0,2));
					
					float fine = temp +(temp2/100);
					
					if(inizio < fine)
				    	return true;
					else
				    	return false;
					
					
					
				}
				
				return false;
				
				
				
			}
	
	
	
	
	 private static FileWriter file;

}
