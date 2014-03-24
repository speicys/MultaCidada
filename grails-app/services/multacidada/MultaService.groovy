package multacidada

import grails.transaction.Transactional

import org.springframework.web.multipart.MultipartFile

@Transactional
class MultaService {

	def storagePath = "fotos";

	def load() {

		new Multa(userID:"111", latitude:-23, longitude:-46,fotoURL:"/fotos/cet3.jpg",tipo:1, data:"2014-03-15 10:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/kombi.png", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/cet2.jpg", tipo:2, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/fina-educativa.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/ciclovia-bebado.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/farolvermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/busao-farol-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/ferrari-deficiente.jpg", tipo:4, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/duplo-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/paulista.jpg", tipo:3, data:"2014-03-15 15:00").save(failOnError: true);
		new Multa(userID:"222", latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/fusca.jpg", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
		
		
		
		println Multa.list()
	}


	def saveFile(MultipartFile file)  {
		def rand = Math.round(Math.random() * Long.MAX_VALUE)
		def filename = "foto-"+rand+".jpg";
		file.transferTo(new File("web-app/"+storagePath+"/"+filename))
		return "/"+storagePath+"/"+filename;
	}
}
