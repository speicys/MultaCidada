package multacidada

class Infracao {

	String ait;
	Date data;
	Integer codEnquadramento;
	String descEnquadramento;
	Integer codLocal;
	String descLocal;
	Integer codEquipamento;
	String serialEquipamento;
	
	String georeference;
	
    static constraints = {
		georeference(nullable:true)
    }
	
	static mapping = {
		georeference sqlType: "char", length:1200
	}
}
