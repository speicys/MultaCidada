package multacidada

import grails.rest.*

@Resource(formats=['json', 'xml'])
class Multa {

	double latitude;
	double longitude;
	String fotoURL;
	String userID;

	int yep = 0;
	int nope = 0;

	int tipo;

	static constraints = {
	}
}
