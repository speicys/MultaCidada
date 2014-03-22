class BootStrap {

	def multaTipoService;
	
    def init = { servletContext ->
    
		// cria tipos de multas
		multaTipoService.loadTipos();
	}
	
	
    def destroy = {
    }
}
