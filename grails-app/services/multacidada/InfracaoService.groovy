package multacidada

import grails.transaction.Transactional

@Transactional
class InfracaoService {

	def servletContext;

	def loadCSV() {

		new File(servletContext.getRealPath("/"), "data/infracoes.csv").toCsvReader(['separatorChar':';']).eachLine { tokens ->
			println tokens;

			def date=new Date(tokens[2]+" "+tokens[3])

			def infracao=new Infracao(ait:tokens[1], data:date,
			codEnquadramento:tokens[4].toInteger().value,
			descEnquadramento:tokens[5],
			codLocal:tokens[6].toInteger().value,
			descLocal:tokens[7],
			codEquipamento:tokens[8].toInteger().value,
			serialEquipamento:tokens[9])

			if(!infracao.save(flush:true, failOnError:true)) {
				println "Error saving token "+tokens;
			}
		}
	}


	def geolocateAll() {

		def infracoes = Infracao.list().subList(0, 100);

		for(Infracao infracao : infracoes) {
			def endereco = normalizarEndereco(infracao.getDescLocal())

			def url = buildGeolocateURL(endereco);
			println "Endereco: "+endereco;

			//			def http = new groovyx.net.http.HTTPBuilder("http://open.mapquestapi.com")
			//			def html = http.get( path : '/geocoding/v1/address',
			//			query:[key:"Fmjtd%7Cluur2l02lu%2Crg%3Do5-9a75lz", callback:"renderGeocode",location:endereco] )

			//			def jsonText = new URL(url).getText(method:'GET');
			//			println "Maps response: "+jsonText;

			try {
				//def json = new JsonSlurper().parseText(jsonText);

				//				// checks if the quota was exceeded
				//				if(json.status=="OVER_QUERY_LIMIT") {
				//					println "*** QUOTA EXCEEDED!!! *****";
				//					break;
				//				}

				//				Street s = new Street(name:json.results[0]?.formatted_address, cache:jsonText, lat:stop.getLat(), lon:stop.getLon());
				//				if(!s.save(flush:true)) {
				//					println "error saving street "+s;
				//				}

			} catch(IOException e) {
				println e.getStackTrace();
			}
		}

	}


	def String buildGeolocateURL(String  endereco) {
		def key = "Fmjtd%7Cluur2l02lu%2Crg%3Do5-9a75lz";
		def url = "http://open.mapquestapi.com/geocoding/v1/address?key="+key+"&callback=renderGeocode&location="+endereco;

		return(url);
	}

	def String normalizarEndereco(String original) {

		String res = original.toLowerCase();
		res = res.replaceAll(/\./, " ");
		res = res.replaceFirst(/altura do numero/, "");
		res = res.replaceFirst(/altura do /, "");
		res = res.replaceFirst(/alt do /, "");
		res = res.replaceFirst(/alt. do /, "");
		res = res.replaceFirst(/numero/, "");
		res = res.replaceFirst(/ n\./, "");
		res = res.replaceFirst(/([0-9]+)/,"\$1 ");

		res = res.replaceAll(/ n /, "");
		res = res.replaceAll("viaduto", "viaduto ");
		res = res.replaceAll(/sentido.*\\b/, " ");
		res = res.replaceAll(/sent.*\b/, " ");
		res = res.replaceAll(" apos .*", " ");
		res = res.replaceAll(" sob .*", " ");
		res = res.replaceAll(" oposto .*", " ");

		res = res.replaceAll(" altura da.*", " ");
		res = res.replaceAll(" proximo a.*", " ");
		res = res.replaceAll(" prox a.*", " ");
		res = res.replaceAll(" prox\\. .*", " ");
		res = res.replaceAll(/(\n|\t|\s\s+)/, " ");
		res = res.replaceAll(/\s+,\s+/, " ");
		res = res.replaceAll(/(\(|\)|\?)/, " ");
		res = res.replaceAll(/ fx .*/, " ");

		res += ", Sao Paulo, SP, Brazil";

		return(res);
	}

}
