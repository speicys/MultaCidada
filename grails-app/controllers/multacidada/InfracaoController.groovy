package multacidada


//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

class InfracaoController {

	def infracaoService

	def index() {
		render(view:"index")
	}


	def load() {
		println "Hello world CSV"

		infracaoService.loadCSV();

		render(view:"/index")
	}

	def geolocate() {
		println "Hello world Geolocate"

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

			render(view:"/index")
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
		res = res.replaceAll(/ n /, "");
		res = res.replaceFirst(/([0-9]+)/,"\$1 ");


		res = res.replaceAll("viaduto", "viaduto ");

		//		res = res.replaceAll("\\(.*\\)", " ");
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

		//		normalText=normalText.replaceAll(/\b(e|a|o|as|os|ao|aos|para|como|mas|porém|em|na|no|nas|nos|da|do|das|dos|de)\b/, "");


		res += ", Sao Paulo, SP, Brazil";

		return(res);
	}


	def String normalizeTweetText(String text) {

		def normalText=normalText.replaceAll(/\b(http:[^\s]+)\b/,"url"); 		  // replaces URLs in the middle of the text
		normalText=normalText.replaceAll(/\b(http:[^\s]+)$/,"url"); 		  // replaces URLs at the end of the text
		normalText=normalText.replaceAll(/\d\d\d[\d|\w]+\/\d\d/,"linenumber"); // replaces the bus line number
		normalText=normalText.replaceAll(/\d\d[h]\d\d/,"hour"); 	  // replaces the hour
		normalText=normalText.replaceAll(/\d\d\/\d\d/,"date"); 		  // replaces the date
		normalText=normalText.replaceAll(/@[\w|\d]+/, "mention");	  // replaces the mentions
		normalText=normalText.replaceAll(/\d+\s?km\/h/, "speed");	  // replaces the speed
		normalText=normalText.replaceAll(/\d+\s?km/, "distance");	  // replaces the distance
		normalText=normalText.replaceAll(/\d+/, "number");	  		  // replaces numbers

		normalText=normalText.replaceAll(/\b(e|a|o|as|os|ao|aos|para|como|mas|porém|em|na|no|nas|nos|da|do|das|dos|de)\b/, "");

		return(normalText);
	}
}