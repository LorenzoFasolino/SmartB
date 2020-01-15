package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import Model.*;
class DisponibiliàModelTesting {
	DisponibilitaModel dm= new DisponibilitaModel();
	Docente d= new Docente("Nome","Cognome","0512105101","alex","ale@unisa.it","stanza d");
	@Test
	void test() throws SQLException {
		DocenteModel docm=new DocenteModel();
		docm.doSave(d);
		dm.aggiungiOrario(d.getMatricola(), "lunedi", "8:00", "9:00");
		
		
	}
	
	@Test
	void test2() throws SQLException
	{
		Collection<Disponibilita> orari=dm.doRetrieveByKey(d.getMatricola());
		LinkedList<Disponibilita> oo= (LinkedList<Disponibilita>) orari;
		dm.doDelete(d.getMatricola());
		DocenteModel dc=new DocenteModel();
		dc.doDelete(d.getMatricola());
		assertEquals(oo.size(),5);
		
	}

}
