<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Eventi</title>

<!-- Inclusione stile -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->

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
				<a class="navbar-brand" href="index.jsp"><img src="img\alive.png"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="eventi.jsp">Eventi</a></li>
					<li><a href="artisti.jsp">Artisti</a></li>
					<li class="dropdown"><a id="user_nickname" href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><c:if test="${empty user.nickname}">Profilo</c:if>${user.nickname}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${empty user.nickname}">
								<li><a href="login.jsp">
									Accedi
								</a></li>
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

<!------------- NON TOCCARE --------------------->
<!---------------- MAPPA --------------------->
	<div class="containermaps">
		<div id="map" class="maps"></div>
	</div>
		<div id="map"></div>
	<script>
		function initMap() {

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 6,
				center : {
					lat : 41.9102415,
					lng : 12.3959119 }			//centrato su Roma
			});
			
/* 		//Passing the xml data into the success callback function
 			$.ajax({
				type : "GET",
				async : true,
				url : "coordinates.xml",
				dataType : "xml",
				success : function(xml) {
				}
			});
*/		
			infoWindow = new google.maps.InfoWindow;

			// Try HTML5 geolocation.
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {	lat : position.coords.latitude,
								lng : position.coords.longitude
					};

					infoWindow.setPosition(pos);
					infoWindow.setContent('You');
					infoWindow.open(map);
					map.setCenter(pos);
				}, function() {
					handleLocationError(true, infoWindow, map.getCenter());
				});
			} else {
				// Browser doesn't support Geolocation
				handleLocationError(false, infoWindow, map.getCenter());
			}
						
				var locations = [
					
				 {lat: 39.3565883, lng: 16.2273367},
				 {lat: 39.3559554, lng: 16.22684},
				 {lat: 39.3572425, lng: 16.2262054},
				 {lat: 39.3580319, lng: 16.2267848},
				
				 {lat: 38.9620405, lng: 16.294082},
				 {lat: 38.961907, lng: 16.3059695},
				 {lat: 38.9746863, lng: 16.3095744},
				
				 {lat: 40.7308269, lng: 14.5330869},
				 {lat: 40.6710604, lng: 14.7179463},
				 {lat: 40.9222253, lng: 14.7601241}	 ] 

/*  	//create an array of places from the XML data
			 var locations = xml.documentElement.getElementsByTagName("Location");

			 //Loop through each marker and add it to the map
			 for (var i = 0; i < locations.length; i++) {
			 var lat = locations[i].getAttribute('Latitudine');
			 var lng = locations[i].getAttribute('Longitudine');
			 var latLng = new google.maps.LatLng(lat, lng);
			 console.log(latLng);
			 var marker = new google.maps.Marker({
			 position : latLng,
			 map : map,
			 label : locations[i].name
			 });
} */

			// Create an array of alphabetical characters used to label the markers.
			var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

			// Add some markers to the map.
			// Note: The code uses the JavaScript Array.prototype.map() method to
			// create an array of markers based on a given "locations" array.
			// The map() method here has nothing to do with the Google Maps API.
			var markers = locations.map(function(location, i) {
				return new google.maps.Marker({
					position : location,
					label : labels[i % labels.length]
				});
			});

			// Add a marker clusterer to manage the markers.
			var markerCluster = new MarkerClusterer( map, markers,
					{
						imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
					});
		}

		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			infoWindow.setPosition(pos);
			infoWindow
					.setContent(browserHasGeolocation ? 'Error: Geolocation service failed.'
							: 'Error: Your browser doesnt support geolocation.');
			infoWindow.open(map);
		}
	</script>
<!---------------- FINE MAPPA --------------------->
<div class="fade-in">
	<div class="cercaev">
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Cerca luogo, data, artista o location dell'evento..">

		<ul id="myUL" class="eventi">
			
<%-- 			<c:forEach var="a" items="${artisti}" varStatus="Loop"> --%>
<%-- 				<li class="event"><a href="#" id="${eventi[Loop.index].ID}">${a.nome},${eventi[Loop.index].data},${locations[Loop.index].nome} --%>
<!-- 				</a></li> -->
<%-- 			</c:forEach> --%>

		</ul>

	</div>

	<script>
		function myFunction() {
			// Declare variables
			var input, filter, ul, li, a, i;
			input = document.getElementById('myInput');
			filter = input.value.toUpperCase();
			ul = document.getElementById("myUL");
			li = ul.getElementsByTagName('li');

			// Loop through all list items, and hide those who don't match the search query
			for (i = 0; i < li.length; i++) {
				a = li[i].getElementsByTagName("a")[0];
				if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
					li[i].style.display = "";
				} else {
					li[i].style.display = "none";
				}
			}
		}
	</script>

	<p class="titolidiv">Experience</p>

		<ul id="myUL" class="experiences">
			<!-- riempita da ajax -->

		</ul>

	<c:if test="${not empty user.nickname}">

		<div id="creaExperience">
			<p class="titolidiv">Non sei interessato a nessuna Experience?</p>
			<p style="text-align: center">
				<a data-toggle="modal" data-target="#crea_experience"
					onclick="inserisciExperienceDisableCheck()"> Crea la tua
					Experience!</a>
			</p>
		</div>

	</c:if>

	<div class="modal fade crea_experience" id="crea_experience">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

					<form id="FormCreazione" class="form-horizontal" role="form"
						method="post" action="">
						
<!-- 						<input id="idEvento" style="display: none" name="eventoID" -->
<!-- 							value=""> -->

							
						<div class="form-group">
							<label for="Viaggio" class="col-sm-3 control-label">Info</label>
							<div class="col-sm-9">
		
								<input id="descrizione" type="text" name="descrizione"
									placeholder="Info su viaggio, soggiorno e concerto"
									class="form-experience" autofocus required="required">
		
							</div>
						</div>
						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Data</label>
							<div class="col-sm-9">
							
								<input id="data" type="date" name="data"
									placeholder="GG/MM/AAAA" class="form-control" autofocus
									required="required">
									
							</div>
						</div>

						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Posti
								Experience</label>
							<div class="col-sm-9">
							
								<input id="posti" type="number" name="numeroPosti" min="2"
									placeholder="Inserisci il numero di posti" class="form-control"
									required="required">
									
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button id="creaExp" type="submit"
									class="btn btn-primary btn-block">Crea la tua
									Experience!</button>
							</div>
						</div>
					</form>
				</div>
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
						<input style="color:black" type="text" name="posti" id="posti" readonly>
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

	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBN9KSVp5NZ5ek2odACLlh9BLOpQOAYtrM&callback=initMap">
	</script>
	<script src="js/markerclusterer.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/experience.js"></script>
	<script src="js/eventi.js"></script>

</body>
</html>