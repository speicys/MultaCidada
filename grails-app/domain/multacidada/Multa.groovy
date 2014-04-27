package multacidada

import java.sql.Date;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import grails.rest.*

@Resource(formats=['json', 'xml'])
class Multa {

	User user;
	
	double latitude;
	double longitude;
	String fotoURL;

	String data;
	
	int yep = 0;
	int nope = 0;

	int tipo;
	
	String getNomeTipo() { 
		try {
			return MultaTipo.tipos.get(tipo)
		} catch (Exception e) {
			return "";
		}
	}
	
	static transients=['nomeTipo']

	static constraints = {
		user  nullable: true
	}
}
