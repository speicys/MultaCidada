package multacidada

import grails.rest.*

@Resource(uri='/multa', formats=['json', 'xml'])
class Multa {

	double latitude;
	double longitude;
	String fotoFileName;
	String userID;
	
	int yep=0;
	int nope=0;
	
	MultaTipo tipo;
	
    static constraints = {
    }
}
