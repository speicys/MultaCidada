<%@ page import="multacidada.Multa" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="main" />
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.2/leaflet.css" />
<script src="http://cdn.leafletjs.com/leaflet-0.7.2/leaflet.js"></script>

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
					var marker = L.marker([${multa.latitude}, ${multa.longitude}]).addTo(map);
				</g:javascript>
		</g:each>
			
		
	</div>
</body>
</html>
