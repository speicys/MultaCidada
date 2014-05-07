package multacidada

import groovy.json.JsonSlurper

import org.hibernate.criterion.CriteriaSpecification
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
		multa.addValidacao(user, escolha);
		if(!multa.save(flush: true, failOnError: true)){
			respond new ApiResponse(status:ApiStatus.ERROR,  content: "Gravando dados")
		}

		println "DEBUG: "+(new Date())+": "+user.code+" "+multa.tipo+" "+escolha;

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
		println "params "+params
		
		println "list() - "+params.userCode
		if (!params.userCode) {
			respond new ApiResponse(status:ApiStatus.ERROR, content:"Identificação do usuário não encontrada.");
		}

		def user = User.findOrSaveWhere(code:params.userCode)
		def result = Multa.withCriteria  {
			ne("user",user)
			validacoes(CriteriaSpecification.LEFT_JOIN)  {
				or{
					isNull("user")
					ne("user",user)
				}
			}
			maxResults(100)
			order("id", "desc")
		}

		respond new ApiResponse(status:ApiStatus.OK, content:result);
	}
}
