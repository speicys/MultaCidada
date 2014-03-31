<%@ page
	import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="${meta(name: 'app.name')}" /></title>
<meta name="description"
	content="Multa Cidadã - Scipopulis - Hackatona CET - 2014">
<meta name="author" content="Scipopulis.com">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<r:require modules="bootstrap" />
<r:require modules="jquery" />
<%--
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		--%>
<g:layoutHead />
<r:layoutResources />
<g:javascript library="application" />

<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 60px;
	background: url("${resource(dir: 'images', file: 'jean.png')}") repeat 0
		0;
}

.navbar {
	background-color: rgb(255, 152, 0);
}

.navbar .navbar-brand {
	color: rgb(0, 0, 0);
	font-size: 22px;
}

.navbar .navbar-toggle .icon-bar {
	color: rgb(255, 255, 255);
}

.navbar-default .navbar-toggle {
	border-color: rgb(255, 255, 255);
	background-color: rgb(255, 255, 255);
}

.navbar .btn {
	margin-left: -3px;
	margin-top: 9px;
}

.navbar-default .navbar-toggle:hover,.navbar-default .navbar-toggle:focus
	{
	background-color: rgb(128, 128, 128);
	border-color: rgb(128, 128, 128);
}

.navbar-default .navbar-nav>li>a {
	color: rgb(255, 255, 255);
	font-size: 22px;
	margin: 12px 0;
}

/* Custom container */
.container {
	margin: 50px auto;
	max-width: 1366px;
}

.container>hr {
	margin: 20px 0;
	border-color: rgb(255, 0, 0);
}

.painelGrafico {
	height: 300px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="${createLink(uri: '/')}"> 
				<img src="${resource(dir: 'images', file: 'chapeu.png')}" /> 
				Multa Cidadã
			</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

			</ul>
		</div>
	</nav>

	<div class="container">
		<g:layoutBody />
		<hr>
		<footer>
			<p>&copy; Scipopulis 2014</p>
		</footer>
	</div>
	<r:layoutResources />
</body>
</html>
