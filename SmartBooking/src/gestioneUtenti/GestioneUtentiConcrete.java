package gestioneUtenti;

import java.sql.SQLException;

import Model.Docente;
import Model.Utente;

public class GestioneUtentiConcrete implements GestioneUtenti {

	@Override
	public Boolean cambiaPassword(Utente u, String vecchiaPassword, String nuovaPassword){
		try {
		password.changePassword(u,vecchiaPassword, nuovaPassword);
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean ripristinaPasswordEmail(String email) {
		try {
			password.sendResetPasswordEmail(email);
			return true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return false;
	}
	
	@Override
	public Boolean ripristinaPassword(Utente u , String nuovaPassword){
		
		try {
			password.changePassword(u,nuovaPassword);
			return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	/**
	 * Registrazione docente (segreteria pu� aggiungere un nuovo docente)
	 */
	@Override
	public Boolean registrazioneDocente(Docente doc) {
		try {
			docente.registrazioneDocente(doc);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private GestionePassword password = new GestionePassword();
	private GestioneDocente docente = new GestioneDocente();
}
