$(document).ready(function() {

	$("#creaExperience").hide();

	dammiTuttiEventi();

	$("#myUL li.event a").on('click',function(e){
		$("#myUL li.event a").not(this).removeClass("selected");
		$(this).addClass("selected");
		$("#creaExperience").show();
		$("#idEvento").val($(this).attr("id"));
		dammiExperienceAssociate($(this).attr("id"));
		e.preventDefault();
	});


	
	

});

var idEventoCliccato;


function dammiExperienceAssociate(idEvento)
{
	idEventoCliccato = idEvento;
//	alert("ajax experienceAssociate (idevento) ");
	$.ajax({
		type: "POST",
		url: "DammiExperience",
		datatype: "json",
		data: {"eventoID" : idEvento},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiences')
			expList.empty();
			for (var i = 0, len = objs.length; i < len; i++)
			{
				var li = $('<li/>')
				.addClass("experience")
				.appendTo(expList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostra_experience")
				.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
				.attr("id",objs[i].ID)
				.appendTo(li);		
				var exp = 
				{
						id : objs[i].ID,
						limiteAdesione : objs[i].limite_adesione,
						posti : objs[i].posti,
						descrizione : objs[i].descrizione,
						eventoId : idEvento
				};		
				hashExp[objs[i].ID] = exp;
			}
			$("#myUL li.experience a").on('click',function(){
				$("#myUL li.experience a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				dammiDettagliExperience(hashExp[id]);
			});

		},
		error : function(experiencesJson)
		{
			alert("ERROR");
		}

	});
}

//function inserisciExperienceDisableCheck()
//{
//$("#creaExp").prop("disabled", true);

//var form = $("#creazione");

//alert(form.find("#descrizione").val());
//alert(form.find("#data").val());
//aler(form.find("#posti").val());

//if( form.find("#descrizione").val() && form.find("#data").val() && form.find("#posti").val() )
//{
//alert("pieno");
//$("#creaExp").prop("disabled", false);
//}
//else
//$("#creaExp").prop("disabled", true);
//}


function dammiTuttiEventi()
{
//	alert("ajax tutti eventi");
	$.ajax({
		type: "GET",
		url: "eventi",
		datatype: "json",
		async: false,

		success : function(eventiJson)
		{

			var objs = JSON.parse(eventiJson);

			var eventList = $("ul.eventi");
			eventList.empty();
			for(var i=0; i< objs.length; i+=3)
			{
				var li = $('<li/>')
				.addClass("event")
				.appendTo(eventList);

				var artisti = "";
				for(var ai= 0;ai<objs[i+2].length;ai++)
				{
					if(! artisti=="")
						artisti += ", ";
					artisti += (objs[i+2][ai].nome);
				}
				var aaa = $('<a/>')
				
				.text("data="+new Date(objs[i].data)+"-> "+objs[i].data+" location="+objs[i+1].nome+" artisti="+artisti)

				.attr("id",objs[i].ID)
				.appendTo(li);
			}

		},
		error : function(eventiJson)
		{
			alert("ERROR");
		}

	});

}


var modalMostraExp = $("#mostra_experience");
var modalInserisciExp = $("#crea_experience");


$("#FormCreazione").submit(function(e) {

	$.ajax({
		type: "POST",
		url: "InserisciExperience",
		datatype: "json",
		data: $("#FormCreazione").serialize()+"&"+"eventoID="+idEventoCliccato , 
		async:false,
		success: function(data)
		{
			var expObj = JSON.parse(data);
			
			var exp = 
			{
					id : expObj.ID,
					limiteAdesione : expObj.limite_adesione,
					posti : expObj.posti,
					descrizione : expObj.descrizione,
			};
			modalInserisciExp.modal("hide");
			dammiExperienceAssociate(idEventoCliccato);
			$('#FormCreazione').trigger("reset");
			dammiDettagliExperience(exp);			
			modalMostraExp.modal("show");

		},
		error: function(data)
		{
			alert("ERROR");
		}
	});

	e.preventDefault();

});