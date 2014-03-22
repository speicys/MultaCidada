package multacidada

import groovy.json.JsonSlurper

import org.springframework.web.multipart.MultipartFile

class MultaController  {
	static responseFormats = ['json', 'xml']

	def servletContext

	def multaService

	def init() {
		println "loading"
		multaService.load()
		list()
	}

	def index() {
		list()
	}

	def show() {
		if(!params.id) {
			list()
			return;
		}
		Multa multa = Multa.findById(params.id);
		if (!multa) {
			return("Erro. Multa nao encontrada");
		}
		
		println "show() "+multa
		respond multa
	}

	def valida() {
		Multa multa = Multa.findById(params.id);
		println "valida() "+params
		if(!multa){
			return("Erro. Multa nao encontrada");
		}


		if (params.valida.equals("yep")) {
			multa.yep++;
		} else {
			multa.nope++;
		}

		if(!multa.save(flush: true, failOnError: true)){
			return("erro");
		}

		respond multa
	}


	def save() {
		println "save() - "+params.multa

		MultipartFile file = request.getFile("foto")
		def imageUrl = multaService.saveFile(file);

		def json =  new JsonSlurper().parseText(params.multa)

		Multa multa = new Multa(json)
		multa.fotoURL=imageUrl
		if(!multa.save(flush: true, failOnError: true)){
			return("erro");
		}

		respond multa
	}


	def list() {
		println "list()"
		respond Multa.list(), model:[multaCount: Multa.count()]
	}
}
