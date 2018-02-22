<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registrazione</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/registrazione.js"></script>

<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>


	<nav class="navbar navbar-default navbar-fixed-top">
		<!--Il container-flud serve per farlo adattare a tutto lo schermo, se togliamo fluid di default e circa 1200-->
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img
					src="img\alive.png"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
					<li><a href="eventi.jsp">Eventi</a></li>
					<li><a href="artisti.jsp">Artisti</a></li>
					<li class="dropdown"><a id="user_nickname" href="#"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false"><c:if
								test="${empty user.nickname}">Profilo</c:if>${user.nickname}<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${empty user.nickname}">
								<li><a href="login.jsp"> Accedi </a></li>
								<li><a href="register.jsp">Registrati</a></li>
							</c:if>
							<c:if test="${not empty user.nickname}">
								<li><a href="profilo.jsp">Visualizza profilo</a></li>
								<li><a href="Logout">Logout</a></li>
							</c:if>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" method="get"
					action="RisultatiRicerca">
					<div class="form-group">
						<input name="cerca" type="text" class="form-control"
							placeholder="Digita">
					</div>
					<button type="submit" class="btn btn-default">Cerca</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="shop.jsp">Shop</a></li>
					<li><a href="info.jsp">Info</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

<div class="fade-in">
	<div class="container">
		<form id="FormRegister" action="RegistraUtente" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Indirizzo
					Mail</label>
				<div class="col-sm-9">
					<input name="mail" type="text" id="nome" placeholder="Mail"
						class="form-control" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input name="password" type="password" id="nome" placeholder="Password"
						class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Nome</label>
				<div class="col-sm-9">
					<input name="nome" type="text" id="nome" placeholder="Nome"
						class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="cognome" class="col-sm-3 control-label">Cognome</label>
				<div class="col-sm-9">
					<input name="cognome" type="text" id="cognome"
						placeholder="Cognome" class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="nickname" class="col-sm-3 control-label">Nickname</label>
				<div class="col-sm-9">
					<input name="nickname" type="text" id="Nickname"
						placeholder="Nickname" class="form-control" required> <span
						class="help-block">Scegliere un nickname qualsiasi, es:
						Gabriele_94</span>
				</div>
			</div>
			<div class="form-group">
				<label for="data" class="col-sm-3 control-label">Data
					Nascita</label>
				<div class="col-sm-9">
					<input name="data" type="date" id="dataNascita"
						class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="citta" class="col-sm-3 control-label">Città di
					residenza</label>
				<div class="col-sm-9">
					<input name="citta" type="text" id="cittaResidenza"
						class="form-control" required>
				</div>
				<div class="form-group">
					<div class="col-sm-9 col-sm-offset-3">
						<div class="checkbox">
							<label> <input id="termini" type="checkbox">Accetto
								i <a href="#">termini</a>
							</label>
						</div>
					</div>
				</div>
				<!-- /.form-group -->
			</div>
			<button id="registrati" class="btn btn-lg btn-primary btn-block btn-signin"
				type="submit">Registrati</button>

			<div class="row omb_row-sm-offset-3 omb_socialButtons">
				<div class="btn-group">
					<button type="button" class="btn btn-facebook">Registrati
						con Facebook</button>
				</div>
			</div>
		</form>
		<!-- /form -->
	</div>
</div>
	<!-- ./container -->
	<!-- </div>
    </div> -->


<script type="text/javascript" src="js/registrazione.js"></script>
	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

</body>
</html>