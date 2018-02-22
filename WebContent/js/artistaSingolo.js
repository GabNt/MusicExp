$(document).ready(function() {

//	dammiArtisti();

	takeArtista();
});


function takeArtista()
{
	

//	alert("ajax take artista ()");
	$.ajax({
		type: "GET",
		url: "PassaArtista",
		datatype: "json",

		success : function(artistaJson)
		{
			var artista = JSON.parse(artistaJson);

			$("#nome").text(artista.nome);
			$("#genere").text(artista.genere);
			$("#sitoWeb").text(artista.sito_web);
			$("#immagineBand").attr("src",artista.immagine_band);
			$("#immagineCopertina").attr("src",artista.copertina_band);


			
			//SOLO ID DELLEVENTO INVECE MO RICHIAMA QUELLO USATO IN EVENTI.jsp
//			var listaEv = $("ul.eventi")
//			for(var i=0;i<artista.eventi.length;i++)
//			{
//				var li = $('<li/>')
//				.addClass("event")
//				.appendTo(listaEv);
//				var a = $('<a/>')
//				.attr("id",artista.eventi[i].ID)
//				.text(artista.eventi[i].ID)
//				.appendTo(li);
//			}
//
//			$("#myUL li.event a").on('click',function(){
//				$("#myUL li.event a").not(this).removeClass("selected");
//				$(this).addClass("selected");
//				$("#creaExperience").show();
//				$("#idEvento").val($(this).attr("id"));
//				dammiExperienceAssociate($(this).attr("id"));
//			});

		},
		error : function(artistiJson)
		{
			alert("ERROR");
		}

	});
}
