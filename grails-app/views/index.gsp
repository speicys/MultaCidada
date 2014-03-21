<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>De Olho no Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" type="text/css">
    
    <g:javascript src="jquery.js" />
    <g:javascript src="bootstrap.min.js" />
    
    
    <style type="text/css">
      body {
        padding-top: 50px;
        padding-bottom: 60px;
        background: url("${resource(dir: 'images', file: 'grey.png')}") repeat 0 0;
      }

      .navbar {
        background-color: rgb(124,15,191);
      }

      .navbar .navbar-brand {
        color: rgb(255,255,255);
        font-size: 22px;
      }
      
      .navbar .navbar-toggle .icon-bar {
        color: rgb(255,255,255);
      }
    
    .navbar-default .navbar-toggle {
      border-color: rgb(255,255,255);
      background-color: rgb(255,255,255);

    }

    .navbar .btn {
      margin-left: -3px;
      margin-top: 9px;
    }

    .navbar-default .navbar-toggle:hover,
    .navbar-default .navbar-toggle:focus {
      background-color: rgb(128,128,128);
      border-color: rgb(128,128,128);
    }

      .navbar-default .navbar-nav > li > a {
        color: rgb(255,255,255);
        font-size: 22px;
        margin: 12px 0;
      }

      /* Custom container */
      .container {
        margin: 50px auto;
        max-width: 1366px;
      }
      .container > hr {
        margin: 20px 0;
        border-color: rgb(255,0,0);
      } 
	.painelGrafico{
		height: 300px;
	}

      @font-face{
        font-family:'FontAwesome';
        src:url("${resource(dir: 'fonts', file: 'fontawesome-webfont.eot?#iefix&v=3.0.1')}") format('embedded-opentype'),
        url('/fonts/fontawesome-webfont.svg?v=3.0.1') format('svg'),
        url('/fonts/fontawesome-webfont.woff?v=3.0.1') format('woff'),
        url('/fonts/fontawesome-webfont.ttf?v=3.0.1') format('truetype');
        font-weight:normal;
        font-style:normal 
      }

    </style>
    
  </head>

  <body>
  	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="navbar-header">
	  <a class="navbar-brand" href="${createLink(uri: '/')}">
		<img src="${resource(dir: 'images', file: 'note.jpg')}"/>
		 Multa Cidadã
	  </a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
		  <li><g:link controller="tweet" action="label">Label</g:link>
		  <li><g:link controller="tweet" action="showCount">Count</g:link>
	  </ul>
	</div>
	</nav>
  </body>
</html>
