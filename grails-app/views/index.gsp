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
		
		<div id="map" class="col-md-offset-1 col-md-10">
			
		</div>
		
		<g:javascript>
			var map = L.map('map').setView([-23.5899431,-46.6351246], 11);

			// add an OpenStreetMap tile layer
			L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
			}).addTo(map);

		</g:javascript>
		
		<g:each in="${multas}" status="i" var="multa">
				<g:javascript>
					var url='${createLink(uri:"${multa.fotoURL}", absolute:true)}';
					var icon = L.icon({
					    //iconUrl:"http://localhost:8080/MultaCidada/${multa.fotoURL}",
					    iconUrl:'${createLink(uri:"/images/marker.png", absolute:true)}',
					    shadowUrl: '',
					
					    iconSize:     [30, 30], // size of the icon
					    shadowSize:   [0, 0], // size of the shadow
					    iconAnchor:   [20, 40], // point of the icon which will correspond to marker's location
					    shadowAnchor: [0, 0],  // the same for the shadow
					    popupAnchor:  [0, -44] // point from which the popup should open relative to the iconAnchor
					});
					
					var marker = L.marker([${multa.latitude}, ${multa.longitude}], {icon: icon}).addTo(map);
					marker.bindPopup('<img src="'+url+'" height="120" width="120"></img><br><p style="font-family:courier">${multa.data}</p><p style="font-family:courier">${multa.nomeTipo}</p>');
				</g:javascript>
		</g:each>
			
		
	</div>
</body>
</html>
