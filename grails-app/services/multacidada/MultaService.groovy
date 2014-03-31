package multacidada

import grails.transaction.Transactional

import org.springframework.web.multipart.MultipartFile

import grails.converters.JSON

@Transactional
class MultaService {

	def storagePath = "fotos";

	def registerJSON() {
		JSON.registerObjectMarshaller(Multa) {	Multa m ->
			return [
				id:  m.id,
				latitude: m.latitude,
				longitude: m.longitude,
				fotoURL: m.fotoURL,
				data: m.data,
				yep: m.yep,
				nope: m.nope,
				tipo: m.tipo,
				nomeTipo: m.getNomeTipo(),
				userId: m.user.id]
		}
	}
	def load() {

		if (Multa.count() == 0) {
			User user1 = User.findOrSaveWhere("code":"user1");
			User user2 = User.findOrSaveWhere("code":"user2");
			User user3 = User.findOrSaveWhere("code":"user3");
			new Multa(user:user1, latitude:-23, longitude:-46,fotoURL:"/fotos/cet3.jpg",tipo:1, data:"2014-03-15 10:00").save(failOnError: true);
			new Multa(user:user1, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/kombi.png", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user1, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/cet2.jpg", tipo:2, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/fina-educativa.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/ciclovia-bebado.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/farolvermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/busao-farol-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/ferrari-deficiente.jpg", tipo:4, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/duplo-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/paulista.jpg", tipo:3, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.2, longitude:-46.2,fotoURL:"/fotos/fusca.jpg", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
		}
	}


	def saveFile(MultipartFile file)  {
		def rand = Math.round(Math.random() * Long.MAX_VALUE)
		def filename = "foto-"+rand+".jpg";
		file.transferTo(new File("web-app/"+storagePath+"/"+filename))
		return "/"+storagePath+"/"+filename;
	}
}
