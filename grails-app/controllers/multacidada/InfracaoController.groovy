package multacidada

class InfracaoController {

	def infracaoService
	
    def index() { 
		render(view:"index")
	}
	
	
	def load() {
		println "Hello world CSV"
		
		infracaoService.loadCSV();
		
		render(view:"/index")	
	}
}
