package multacidada

class Validacao {

	User user;

	ValidacaoTipo escolha;

	static belongsTo = [multa: Multa]
	
	Date dateCreated;

	static constraints = {
	}
}
