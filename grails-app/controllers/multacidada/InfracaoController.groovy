package multacidada

class InfracaoController {

    def index() { 
		render(view:"index")
	}
	
	
	def load() {
		println "Hello world CSV"
		
		render(view:"/index")	
	}
}
