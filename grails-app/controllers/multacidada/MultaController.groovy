package multacidada

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

class MultaController extends RestfulController {
	static responseFormats = ['json', 'xml']
	
	def multaService

	def init() {
		println "loading"
		multaService.load()

		list()
	}

	def validate() {
		println "validate()"

		// retrieve multa ID

		// increase relevant counter (yep or nope)
	}

	def index() {
		list()
	}

	def show() {
		if(params.id && Multa.exists(params.id)){
			respond Multa.findById(params.id)
		}else{
			list()
		}
	}

	@Transactional
	def save(Multa multa) {
		//Multa multa = new Multa(params.multa)
		if(multa.hasErrors()) {
			respond multa.errors, view:'create'
		}
		//		def multa = new Multa(params['multa'])

		if(multa.save(flush: true, failOnError: true)){
			respond multa
		}else{
			//handle errors...
			println "error"
		}
	}

	def list() {
		println "list()"
		respond Multa.list(), model:[multaCount: Multa.count()]
	}
}
