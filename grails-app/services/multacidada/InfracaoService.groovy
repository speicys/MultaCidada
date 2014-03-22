package multacidada

import grails.transaction.Transactional

@Transactional
class InfracaoService {

	def servletContext;
	
    def loadCSV() {
		
		new File(servletContext.getRealPath("/"), "data/infracoes.csv").toCsvReader(['separatorChar':';']).eachLine { tokens ->
			println tokens;
			
			def date=new Date(tokens[2]+" "+tokens[3])
			
			def infracao=new Infracao(ait:tokens[1], data:date, 
				codEnquadramento:tokens[4].toInteger().value, 
				descEnquadramento:tokens[5], 
				codLocal:tokens[6].toInteger().value, 
				descLocal:tokens[7], 
				codEquipamento:tokens[8].toInteger().value, 
				serialEquipamento:tokens[9])
			
			if(!infracao.save(flush:true, failOnError:true)) {
				println "Error saving token "+tokens;
			}
		}
    }
}
