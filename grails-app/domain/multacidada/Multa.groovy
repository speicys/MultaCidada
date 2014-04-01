package multacidada

import java.sql.Date;

import grails.rest.*

@Resource(formats=['json', 'xml'])
class Multa {

	User user;
	
	double latitude;
	double longitude;
	String fotoURL;
	String userID; //Sera removido...

	String data;
	
	int yep = 0;
	int nope = 0;

	int tipo;
	
	String getNomeTipo() { MultaTipo.tipos.get(tipo)}
	
	static transients=['nomeTipo']

	static constraints = {
		user  nullable: true
		userID  nullable: true
	}
}
