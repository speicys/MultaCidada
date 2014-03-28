<%@ page import="multacidada.Multa"%>
<!DOCTYPE html>
<html lang="en" ng-app>
<head>
<meta name="layout" content="main" />
<script src="lib/angular/angular.min.js"></script>
<script src="js/services.js"></script>
<script src="lib/angular/angular-resource.min.js"></script>

<style type="text/css">
#map {
	height: 580px;
}
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

	      var parsedMulta = [];	      
	      var markers = [];
	      
	      function initialize() {
	        var mapOptions = {
	          center: new google.maps.LatLng(-23.5899431, -46.6351246),
	          zoom: 11
	        };
	        mapSP = new google.maps.Map(document.getElementById("map"),mapOptions);
	        
	        downloadData();
		    window.setInterval(downloadData, 5000);
	      }
	      
	      function clearOverlays() {
			  for (var i = 0; i < markers.length; i++ ) {
    			markers[i].setMap(null);
  			  }
			  markers.length = 0;
		  }
	      
	      
	      function loadMarkers() {
	      	console.log("loadMarkers count:"+parsedMulta.length+" markers:"+markers.length)
   	        clearOverlays();
	      	
	      	for(var i=0; i < parsedMulta.length ; i++) {
	      		var image={
					url: '${createLink(uri:"/images/marker.png", absolute:true)}',
				    scaledSize:new google.maps.Size(20, 20)
			   }
	      		var myLatlng = new google.maps.LatLng(parsedMulta[i].latitude, parsedMulta[i].longitude);
				var marker=new google.maps.Marker({
			    	position: myLatlng,
			    	map: mapSP,
			    	title:"Hello World!",
			    	icon: image
				});
				markers.push(marker);
	
				var infowindow = new google.maps.InfoWindow();
  				
  				google.maps.event.addListener(marker, 'click', (function(marker, i) {
            		return function() {
            			var baseURL=parsedMulta[i].fotoURL; 
            			var url='${createLink(uri:"/", absolute:true)}' +baseURL;
						var contentString='<img src="'+url+'" height="120" width="120"></img><br><p style="font-family:courier">'+parsedMulta[i].data+
						'</p> <p style="font-family:courier">'+parsedMulta[i].nomeTipo+'</p>';
				
              			infowindow.setContent(contentString);
              			infowindow.open(mapSP, marker);
            		}
          		})(marker, i));
	      	}
	      }
	      google.maps.event.addDomListener(window, 'load', initialize);
	      
	      function downloadData() {	      
  	        $(function() { 
			  $.getJSON('${createLink(uri:"/multa", absolute:true)}', function(data) {
			      parsedMulta = data;
			      loadMarkers();
	  		    });
		    });
		  }
	    </g:javascript>

		<div id="map" class="col-md-offset-1 col-md-10"></div>

	</div>
</body>
</html>
