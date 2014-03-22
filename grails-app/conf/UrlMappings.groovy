class UrlMappings {

	static mappings = {
		"/multa/init"(controller: "multa", action: "init")
		"/multas"(controller: "multa", action: "list")
		"/multa/$id?"(resource: "multa")

		"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		
		"/"(view:"/index")
        "500"(view:'/error')
	}
}
