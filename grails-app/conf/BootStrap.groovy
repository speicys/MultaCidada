import grails.converters.JSON
import multacidada.ApiResponse
import multacidada.ApiStatus


class BootStrap {

	def multaTipoService;

	def multaService;

	def init = { servletContext ->

		// cria tipos de multas
		multaTipoService.loadTipos();

		multaService.load();
		multaService.registerJSON();

		JSON.registerObjectMarshaller(ApiResponse) {
			return [
				status:  it.status,
				content: it.content]
		}
		JSON.registerObjectMarshaller(ApiStatus) { return it.name(); }
	}


	def destroy = {
	}
}
