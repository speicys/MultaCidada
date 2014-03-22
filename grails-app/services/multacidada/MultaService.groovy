package multacidada

import grails.transaction.Transactional

import org.springframework.web.multipart.MultipartFile

@Transactional
class MultaService {

	def storagePath = "fotos";

	def load() {

		new Multa(userID:"111", latitude:-23, longitude:-46,fotoURL:"/fotos/cet.jpg",tipo:1).save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/cet2.jpg", tipo:2).save(failOnError: true);
		//new Teste(title:"The Stand").save()
		//new Teste(title:"The Shining").save()

		println Multa.list()
	}


	def saveFile(MultipartFile file)  {
		def rand = Math.round(Math.random() * Long.MAX_VALUE)
		def filename = "foto-"+rand+".jpg";
		file.transferTo(new File("web-app/"+storagePath+"/"+filename))
		return "/"+storagePath+"/"+filename;
	}
}
