import grails.converters.JSON


class BootStrap {

	def multaTipoService;

	def multaService;
	
	def init = { servletContext ->

		// cria tipos de multas
		multaTipoService.loadTipos();

		multaService.load();

	}


	def destroy = {
	}
}
