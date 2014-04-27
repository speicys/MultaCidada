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
			respond new ApiResponse(status:ApiStatus.ERROR,  content: "Multa não encontrada")
		}

		log.info "show(): "+multa
		respond new ApiResponse(status:ApiStatus.OK, content:multa)
	}

	def valida() {
		Multa multa = Multa.findById(params.id);
		if(!multa){
			respond new ApiResponse(status:ApiStatus.ERROR,  content: "Multa não encontrada")
		}


		def user = User.findOrSaveWhere("code":params.userCode);

		ValidacaoTipo escolha = ValidacaoTipo.valueOf(params.valida.toUpperCase());
		if (escolha.equals(ValidacaoTipo.YEP)) {
			multa.yep++;
		} else {
			multa.nope++;
		}

		if(!multa.save(flush: true, failOnError: true)){
			respond new ApiResponse(status:ApiStatus.ERROR,  content: "Gravando dados")
		}

		//LOG
		Validacao v = new Validacao(user:user, multa:multa, escolha:escolha).save(flush:true);
		println "DEBUG: "+(new Date())+": "+v.user.code+" "+v.multa.tipo+" "+v.escolha;
		
		respond new ApiResponse(status:ApiStatus.OK, content:multa)
	}

	def save() {
		log.info "save() - "+params.multa

		def user = User.findOrSaveWhere("code":params.userCode);
		def json =  new JsonSlurper().parseText(params.multa)
		MultipartFile file = request.getFile("foto")
		def imageUrl = multaService.saveFile(file);

		Multa multa = new Multa(json)
		multa.fotoURL = imageUrl
		multa.user = user;

		if(!multa.save(flush: true, failOnError: true)){
			respond new ApiResponse(status:ApiStatus.ERROR,  content: "Erro gravando dados")
		}

		respond new ApiResponse(status:ApiStatus.OK, content:multa)
	}

	def list() {
		println "list() - "+params.userCode

		def user = null;
		if (params.userCode) {
			user = User.findOrSaveWhere(code:params.userCode);
		}

		def result = Multa.withCriteria {
			user != null ? ne("user",user):null;
			maxResults(100)
			order("id", "desc")
		}

		respond new ApiResponse(status:ApiStatus.OK, content:result);

	}
}
