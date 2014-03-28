<%@ page import="multacidada.Multa" %>
<!DOCTYPE html>
<html lang="en" ng-app>
<head>
<meta name="layout" content="main" />
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.2/leaflet.css" />
<script src="http://cdn.leafletjs.com/leaflet-0.7.2/leaflet.js"></script>
<script src="lib/angular/angular.min.js"></script>
<script src="js/services.js"></script>
<script src="lib/angular/angular-resource.min.js"></script>
  
<style type="text/css">
	#map { height: 580px; }
</style>
	
</head>
<body>

	<div class="row">
		<!-- <div class="col-md-offset-1 col-md-2">
			<g:formRemote name="csv" url="[controller:'infracao', action:'load']">
				<button class="btn btn-default btn-lg active">Carregar CSV</button>
			</g:formRemote>
		</div>
		<div class="col-md-offset-1 col-md-2">
			<g:formRemote name="csv"
				url="[controller:'infracao', action:'geolocate']">
				<button class="btn btn-default btn-lg active">Geolocalizar dados da base</button>
			</g:formRemote>
		</div> -->
		
		<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCS2pjye_hs-5WEsGBEwzayK9nubfSRG4Q&sensor=false">
	    </script>
	    <g:javascript>
	      var mapSP;
	      function initialize() {
	        var mapOptions = {
	          center: new google.maps.LatLng(-23.5899431, -46.6351246),
	          zoom: 11
	        };
	        mapSP = new google.maps.Map(document.getElementById("map"),
	            mapOptions);
	            
	        loadMarkers();
	      }
	      function loadMarkers() {
	      	var objJSONData =  '${multas.encodeAsJSON()}'
			var parsedMulta = eval(objJSONData); 
	      	for(var i=0; i<parsedMulta.length; i++) {
	      		var image = '${createLink(uri:"/images/marker.png", absolute:true)}';
				var myLatlng = new google.maps.LatLng(parsedMulta[i].latitude, parsedMulta[i].longitude);
				var marker = new google.maps.Marker({
			    	position: myLatlng,
			    	map: mapSP,
			    	title:"Hello World!",
			    	icon: image
			});
	      	}
	      }
	      google.maps.event.addDomListener(window, 'load', initialize);
	      
	    </g:javascript>
	    
		<div id="map" class="col-md-offset-1 col-md-10">
			
		</div>	
		
	</div>
</body>
</html>
