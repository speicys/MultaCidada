class UrlMappings {

	static excludes = ["/fotos/*"]

	static mappings = {

		"/multa/init"(controller: "multa", action: "init")
		"/multa/$id/valida/$valida"(controller: "multa", method:"GET", action: "valida")
		"/multa/$id?"(resource: "multa")

//		"/$controller/$action?/$id?(.$format)?"{ constraints { // apply constraints here
//			} }

		"/"(controller: "infracao", action: "index")
		"500"(view:'/error')
	}
}
