package multacidada

import java.util.logging.Logger;

import grails.transaction.Transactional

import org.springframework.web.multipart.MultipartFile

import grails.converters.JSON

@Transactional
class MultaService {

	def storagePath = "fotos";
	
	def servletContext;

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
				userId: m.userID]
		}
	}
	def load() {

		if (Multa.count() == 0) {
			User user1 = User.findOrSaveWhere("code":"user1");
			User user2 = User.findOrSaveWhere("code":"user2");
			User user3 = User.findOrSaveWhere("code":"user3");
			new Multa(user:user1, latitude:-23.5, longitude:-46.566,fotoURL:"/fotos/cet3.jpg",tipo:1, data:"2014-03-15 10:00").save(failOnError: true);
			new Multa(user:user1, latitude:-23.51, longitude:-46.688,fotoURL:"/fotos/kombi.png", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user1, latitude:-23.52, longitude:-46.732,fotoURL:"/fotos/cet2.jpg", tipo:2, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.53, longitude:-46.71,fotoURL:"/fotos/fina-educativa.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.54, longitude:-46.73,fotoURL:"/fotos/ciclovia-bebado.jpg", tipo:8, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user2, latitude:-23.55, longitude:-46.74,fotoURL:"/fotos/farolvermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.53, longitude:-46.72,fotoURL:"/fotos/busao-farol-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.59, longitude:-46.63,fotoURL:"/fotos/ferrari-deficiente.jpg", tipo:4, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.52, longitude:-46.65,fotoURL:"/fotos/duplo-vermelho.jpg", tipo:0, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.546, longitude:-46.66,fotoURL:"/fotos/paulista.jpg", tipo:3, data:"2014-03-15 15:00").save(failOnError: true);
			new Multa(user:user3, latitude:-23.55, longitude:-46.69,fotoURL:"/fotos/fusca.jpg", tipo:7, data:"2014-03-15 15:00").save(failOnError: true);
		}
	}


	//TODO! alterar isso... esta gravando no diretorio web-app, trocar para folder externo (ou S3)
	def saveFile(MultipartFile file)  {
		def rand = Math.round(Math.random() * Long.MAX_VALUE)
		def filename = "foto-"+rand+".jpg";

		def realPath = servletContext.getRealPath( storagePath )
	
		def storageDir = new File( realPath )
		if( !storageDir.exists() ){
			log.info "creating directory ${storageDir}"
			if(!storageDir.mkdirs()){
				log.info "FAILED creating dir: "+storageDir
			}
		}

		// Store file
		file.transferTo( new File("${realPath}/${filename}") )
		return "/"+storagePath+"/"+filename;
	}
}
