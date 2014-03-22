<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="main" />
</head>
<body>

	<div class="row">
		<div class="col-md-offset-1 col-md-2">
			<g:formRemote name="csv" url="[controller:'infracao', action:'load']">
				<button class="btn btn-default btn-lg active">Carregar CSV</button>
			</g:formRemote>
		</div>
		<div class="col-md-offset-1 col-md-2">
			<g:formRemote name="csv"
				url="[controller:'infracao', action:'geolocate']">
				<button class="btn btn-default btn-lg active">Geolocalizar dados da base</button>
			</g:formRemote>
		</div>
	</div>
</body>
</html>
