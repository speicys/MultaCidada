package multacidada


//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

class InfracaoController {

	def infracaoService

	def index() {
		println "entered index";
		render(view:"/index", model:[multas:Multa.list().reverse()])
	}


	def load() {
		println "Hello world CSV"

		infracaoService.loadCSV();

		render(view:"/index")
	}

	def geolocate() {
		println "Hello world Geolocate"

		infracaoService.geolocateAll();

		render(view:"/index")
	}


}