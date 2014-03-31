package multacidada

import java.awt.GraphicsConfiguration.DefaultBufferCapabilities;
import java.awt.event.ItemEvent;
import java.lang.ref.ReferenceQueue.Null;
import java.sql.ResultSet;
import java.util.regex.Pattern.Neg;

import groovy.json.JsonSlurper

import org.apache.jasper.compiler.Node.ParamsAction;
import org.grails.datastore.mapping.core.Session;
import org.grails.datastore.mapping.query.Query
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
			respond new ApiResponse(status:"Erro",  msg: "Multa não encontrada")
		}

		log.info "show(): "+multa
		respond multa
	}

	def valida() {
		Multa multa = Multa.findById(params.id);
		if(!multa){
			respond new ApiResponse(status:"Erro",  msg: "Multa não encontrada")
		}

		def user = User.findOrSaveWhere("code":params.userCode);
		
		if (params.valida.equals("yep")) {
			multa.yep++;
		} else {
			multa.nope++;
		}

		if(!multa.save(flush: true, failOnError: true)){
			respond new ApiResponse(status:"Erro",  msg: "Gravando dados")
		}

		respond multa
	}

	def save() {
		log.info "save() - "+params.multa
		
		//def user = User.findOrSaveWhere("code":params.userCode);
		def json =  new JsonSlurper().parseText(params.multa)
		MultipartFile file = request.getFile("foto")
		def imageUrl = multaService.saveFile(file);

		Multa multa = new Multa(json)
		multa.fotoURL = imageUrl
		//multa.user = user;

		if(!multa.save(flush: true, failOnError: true)){
			respond new ApiResponse(status:"Erro",  msg: "Gravando dados")
		}

		respond multa
	}

	def list() {
		println "list()"

		def user = null;
		if (params.userCode) {
			user = User.findOrSaveWhere(code:params.userCode);
		}

		def result = Multa.withCriteria {
			user != null ? ne("user",user):null;
			maxResults(100)
			order("id", "desc")
		}
		respond result
	}
}
