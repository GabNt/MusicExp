<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profilo Utente</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

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
		<div class="row profile">
			<div class="col-sm-3">
				<div class="profile-sidebar">
					<!-- sidebar utente -->
					<div class="profile-userpic">
						<img src="${utente_trovato.avatar}" class="img-responsive"
							id="avatar" alt="">
					</div>
					<div class="profile-usertitle">
						<div class="profile-usertitle-name" id="user_nick">${utente_trovato.nickname}</div>
						<div class="profile-usertitle-job">Rocker</div>
					</div>
					<!-- bottoni sidebar -->
					<div class="profile-userbuttons">
						<button type="button" class="btn btn-danger btn-sm"
							data-toggle="modal" data-target="#inviamessaggio">Messaggio</button>
					</div>
					<!-- menu sidebar-->
					<div class="profile-usermenu">
											<ul id="profile" class="nav">
							<li class="active"><a href="#" id="buttonbio"> <i
									class="glyphicon glyphicon-home"></i> Bio
							</a></li>
							<li><a href="#" id="buttonexperience"> <i
									class="glyphicon glyphicon-road"></i> Experiences
							</a></li>
							<li><a href="#" id="buttonprodotti"> <i
									class="glyphicon glyphicon-euro"></i> Prodotti
							</a></li>
							<li><a href="#" id="buttoninfo"> <i
									class="glyphicon glyphicon-pencil"></i> Info
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="col-sm-9 ">
					<div class="boxinfo">
						<div class="boxinfo--bio">${utente_trovato.bio}</div>
						<div class="boxinfo--experience">
							<ul id="myUL" class="experiencesCreate">
<%-- 								<c:forEach var="exp" items="${experiences}"> --%>
<%-- 									<li class="experience"><a href="#">${exp.ID},${exp.limite_adesione},${exp.posti}</a></li> --%>
<%-- 								</c:forEach> --%>
							</ul>
						</div>
						<div class="boxinfo--prodotti">
							<ul>
								<c:forEach var="p" items="${prodotti}">
									<li><a href="#">${p.nome}</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="boxinfo--info">
							<div style="min-height: 115px">Nome:
								"${utente_trovato.nome}"</div>
							<div style="min-height: 115px">Cognome:
								"${utente_trovato.cognome}"</div>
							<div style="min-height: 115px">Data:
								"${utente_trovato.data_nascita}"</div>
							<div style="min-height: 115px">Citta' residenza:
								"${utente_trovato.citta_residenza}"</div>
							<!-- <div>Nome: "${utente_trovato.nome}"</div>  -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="utente_trovato_nick" style="display: none">${utente_trovato.nickname}</div>
	</div>


	<!----------------------------------------------- MESSAGGIO ------------------------------>

	<div class="modal fade crea_experience" id="inviamessaggio">
		<div class="modal-dialog">

			<div class="modal-body">
				<form class="form-horizontal" role="form" method="get"
					action="InviaMessaggio">
					<div class=""></div>

					<div class="form-group">
						<a href="#" data-dismiss="modal" class="class pull-right"><span
							class="glyphicon glyphicon-remove"></span></a> <label for="Viaggio"
							class="col-sm-3 control-label"></label> <input type="text"
							id="nome" name="oggetto"
							placeholder="Digita l'oggetto del messaggio"
							class="form-experience" autofocus>
					</div>

					<div class="form-group">
						<a href="#" data-dismiss="modal" class="class pull-right"><span
							class="glyphicon glyphicon-remove"></span></a> <label for="Viaggio"
							class="col-sm-3 control-label"></label> <input type="text"
							id="nome" name="testo"
							placeholder="Digita il testo del messaggio..."
							class="form-experience" autofocus>
					</div>

					<div class="form-group">
						<button type="submit" id="inviamess"
							class="btn btn-primary btn-block">Invia messaggio</button>
					</div>
				</form>
				<!-- /form -->
				<!-- ./container -->
			</div>
		</div>
	</div>

	<div class="modal fade " id="mostra_experience">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="dati" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

					<div>
						<p>Posti rimasti:</p>
						<input style="color:black" type="number" name="posti" id="posti" readonly>
					</div>

					<div>
						<p>Limite Adesione:</p>
						<input style="color:black" type="datetime" id="dataLimiteAdesione" readonly>
					</div>

					<div>
						<p>Descrizione:</p>
						<input style="color:black" type="text" id="descrizione" readonly>
					</div>

					<div>
						<p>Organizzatore:</p>
						<p id="organizzatore"></p>
					</div>

					<div>
						<p>Partecipanti:</p>
						<ul id="partecipanti">
<!-- 							ajax ci mette i li con i nick dei partecipanti -->
						</ul>

					</div>
 			<button style="float:right" type="submit" id="partecipa" class="btn btn-success">Partecipa</button>
 			<button style="float:right" type="submit" id="annulla_partecipa" class="btn btn-success">Annulla Partecipa</button>
 			<button style="float:right" type="submit" id="modifica" class="btn btn-success">Modifica</button>
 			<button style="float:right" type="submit" id="elimina" class="btn btn-success">Elimina</button>

				</div>
			</div>
		</div>
	</div>
</div>

	<script type="text/javascript" src="js/experience.js"></script>
	<script type="text/javascript" src="js/profilo.js"></script>
	<script type="text/javascript" src="js/profiloAltroUtente.js"></script>
	


</body>
</html>