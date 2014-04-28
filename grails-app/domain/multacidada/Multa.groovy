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
	
	Collection validacoes;
	static hasMany = [validacoes: Validacao]
	
	Date dateCreated;
	Date lastUpdated;
	
	String getNomeTipo() { 
		try {
			return MultaTipo.tipos.get(tipo)
		} catch (Exception e) {
			return "";
		}
	}
	
	def addValidacao(User user, ValidacaoTipo escolha)
	{
		this.addToValidacoes(new Validacao(user:user, escolha:escolha));
		if (escolha.equals(ValidacaoTipo.YEP)) {
			yep++;
		} else {
			nope++;
		}
		
	}
	
	static transients=['nomeTipo']

	static constraints = {
		user  nullable: true
	}
}
