$(document).ready(function() {

$("#myUL li.experience a").on('click',function(){
	$("#myUL li.experience a").not(this).removeClass("selected");
	$(this).addClass("selected");
	var id = $(this).attr("id");
	dammiDettagliExperience(hashExp[id]);
});

	if ($(".profile-userpic img").attr('src')==='')
	{
		$(".profile-userpic img").attr('src',"img/avatar.png");	
	}


	$('ul#profile li').on('click', function() {
		$('ul#profile li.active').removeClass('active');
		$(this).addClass('active');
	});


	$(".boxinfo--experience").hide();
	$(".boxinfo--prodotti").hide();
	$(".boxinfo--posta").hide();
	$(".boxinfo--info").hide();
	$(".boxinfo--impostazioni").hide();


	$("#buttonbio").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").slideDown(800);
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonaccount").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").slideDown(800);
	});
	$("#buttonexperience").click(function(){
		$(".boxinfo--experience").slideDown(800);
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonposta").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").slideDown(800);
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttoninfo").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").slideDown(800);
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonprodotti").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").slideDown(800);
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
});


$("#inviamess").click(function(){
	alert("Messaggio inviato!");
});

function Messaggio(id, mittente, destinatario, dataInvio, oggetto, testo)
{
	this.id = id;
	this.mittente = mittente;
	this.destinatario = destinatario;
	this.dataInvio = dataInvio;
	this.oggetto = oggetto;
	this.testo = testo;
}

function Prodotto(id,nome,prezzo,colore,tipo,descrizione,quantita,realizzatore,immagine){
	
	this.id=id;
	this.nome=nome;
	this.prezzo=prezzo;
	this.colore=colore;
	this.tipo=tipo;
	this.descrizione=descrizione;
	this.quantita=quantita;
	this.realizzatore=realizzatore;
	this.immagine=immagine;
	
}

var hashMex = {};

var hashProd = {};

var nickname = $("#user_nick").text();

var experienceShow = $("#buttonexperience");

experienceShow.on('click',function(){
	dammiExperience();
});

var prodottiShow = $("#buttonprodotti");

prodottiShow.on('click',function(){
	dammiProdotti();
})

var postaShow = $("#buttonposta");

postaShow.on('click',function(){
	dammiMessaggi();
});

function dammiExperience()
{
//	alert("dammi EXP profilo")
	dammiExperienceCreate();
	dammiExperiencePartecipa();
}

function dammiMessaggi()
{
//	alert("ajax messaggi ()")
	$.ajax({
		type: "POST",
		url: "DammiMessaggi",
		datatype: "json",
		
		success : function(messaggiJson)
		{
//			alert("success");
			
//			alert(messaggiJson);
			
			var messaggi = JSON.parse(messaggiJson);
			
			var messaggiInviati = messaggi[0];
			var messaggiRicevuti = messaggi[1];
			
			var mexInvList = $('ul.messaggiInviati')
			mexInvList.empty();
			for(var i=0;i<messaggiInviati.length;i++)
			{
				var li = $('<li/>')
				.addClass("messaggioInviato")
				.appendTo(mexInvList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraMessaggio")
				.text("messaggio : ID="+messaggiInviati[i].ID+" Data="+messaggiInviati[i].data_invio+" oggetto="+messaggiInviati[i].oggetto)
				.attr("id",messaggiInviati[i].ID)
				.appendTo(li);
				var mex = 
				{
						id : messaggiInviati[i].ID,
						mittente : messaggiInviati[i].mittente.nickname,
						destinatario : messaggiInviati[i].destinatario.nickname,
						dataInvio : messaggiInviati[i].data_invio,
						oggetto : messaggiInviati[i].oggetto,
						testo : messaggiInviati[i].testo			
				};
				hashMex[messaggiInviati[i].ID] = mex;
			}
	
			var mexRicList = $('ul.messaggiRicevuti')
			mexRicList.empty();
			for(var i=0;i<messaggiRicevuti.length;i++)
			{
				var li = $('<li/>')
				.addClass("messaggioRicevuto")
				.appendTo(mexRicList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraMessaggio")
				.text("messaggio : ID="+messaggiRicevuti[i].ID+" Data="+messaggiRicevuti[i].data_invio+" oggetto="+messaggiRicevuti[i].oggetto)
				.attr("id",messaggiRicevuti[i].ID)
				.appendTo(li);

				var mex = 
				{
						id : messaggiRicevuti[i].ID,
						mittente : messaggiRicevuti[i].mittente.nickname,
						destinatario : messaggiRicevuti[i].destinatario.nickname,
						dataInvio : messaggiRicevuti[i].data_invio,
						oggetto : messaggiRicevuti[i].oggetto,
						testo : messaggiRicevuti[i].testo			
				};
				hashMex[messaggiRicevuti[i].ID] = mex;
			}
			$("#myUL li.messaggioRicevuto a").on('click',function(){
				$("#myUL li.messaggioRicevuto a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#utenteTipo").text("MITTENTE");
				dati.find("#utente").text(hashMex[id].mittente);
				dati.find("#data").text(hashMex[id].dataInvio);
				dati.find("#oggetto").text(hashMex[id].oggetto);
				dati.find("#testo").text(hashMex[id].testo);
			});
		
			$("#myUL li.messaggioInviato a").on('click',function(){
				$("#myUL li.messaggioInviato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#utenteTipo").text("DESTINATARIO");
				dati.find("#utente").text(hashMex[id].destinatario);
				dati.find("#data").text(hashMex[id].dataInvio);
				dati.find("#oggetto").text(hashMex[id].oggetto);
				dati.find("#testo").text(hashMex[id].testo);
			});
		},
		error : function(messaggiJson)
		{
			alert("ERROR");
		}

	});
}

function dammiProdotti()
{
	alert("AJAX PRODOTTI UTENTE");
	$.ajax({
		type: "POST",
		url: "DammiProdottiJson",
		datatype: "json",
		
		success : function(prodottiJson)
		{
			alert(prodottiJson)
			
			var prodotti = JSON.parse(prodottiJson);
			
			var prodottiCreati = prodotti[0];
			var prodottiAcquistati = prodotti[1];
			
			var prodCreatiList = $('ul.prodottiCreati')
			prodCreatiList.empty();

			for(var i=0;i<prodottiCreati.length;i++)
			{

				var li = $('<li/>')
				.addClass("prodottoCreato")
				.appendTo(prodCreatiList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraProdotto")
				.text("prodotto : ID="+prodottiCreati[i].ID+" Nome="+prodottiCreati[i].nome+ "Descrizione="+prodottiCreati[i].descrizione + "Design=" + prodottiCreati[i].immagine)
				.attr("id",prodottiCreati[i].ID)
				.appendTo(li);

				var prod={
						id : prodottiCreati[i].ID,
						nome : prodottiCreati[i].nome,
						prezzo: prodottiCreati[i].prezzo,
						colore: prodottiCreati[i].colore,
						tipo: prodottiCreati[i].tipo,
						descrizione: prodottiCreati[i].descrizione,
						quantita: prodottiCreati[i].quantita,
//						realizzatore: prodottiCreati[i].realizzatore,
						immagine: prodottiCreati[i].immagine
				};
				hashProd[prodottiCreati[i].ID] = prod;
	
			}
	
			var prodAcquistatiList = $('ul.prodottiAcquistati')
			prodAcquistatiList.empty();
			for(var i=0;i<prodottiAcquistati.length;i++)
			{
				var li = $('<li/>')
				.addClass("prodottoAcquistato")
				.appendTo(prodAcquistatiList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraProdotto")
				.text("prodotto : ID="+prodottiAcquistati[i].ID+" Nome="+prodottiAcquistati[i].nome+ "Descrizione="+prodottiAcquistati[i].descrizione + "Design=" + prodottiAcquistati[i].immagine)
				.attr("id",prodottiAcquistati[i].ID)
				.appendTo(li);

				var prod = 
				{
						id : prodottiAcquistati[i].ID,
						nome : prodottiAcquistati[i].nome,
						prezzo: prodottiAcquistati[i].prezzo,
						colore: prodottiAcquistati[i].colore,
						tipo: prodottiAcquistati[i].tipo,
						descrizione: prodottiAcquistati[i].descrizione,
						quantita: prodottiAcquistati[i].quantita,
//						realizzatore: prodottiAcquisti[i].realizzatore,
						immagine: prodottiAcquistati[i].immagine	
				};
				
				
				hashProd[prodottiAcquistati[i].ID] = prod;
			}
			
			$("#myUL li.prodottoCreato a").on('click',function()
			{
				$("#myUL li.prodottoCreato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#id_p").text(hashProd[id].id);
				dati.find("#nome_p").text(hashProd[id].nome);
				dati.find("#descrizione_p").text(hashProd[id].descrizione);
				dati.find("#immagine_p").text(hashProd[id].immagine);
			});
		
			$("#myUL li.prodottoAcquistato a").on('click',function()
			{
				$("#myUL li.prodottoAcquistato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#id_p").text(hashProd[id].id);
				dati.find("#nome_p").text(hashProd[id].nome);
				dati.find("#descrizione_p").text(hashProd[id].descrizione);
				dati.find("#immagine_p").text(hashProd[id].immagine);
			});
		},
		error : function(prodottiJson)
		{
			alert("ERROR");
		}
	});
}


function dammiExperiencePartecipa()
{
	$.ajax({
		type: "POST",
		url: "experiencePartecipate",
		datatype: "json",
		data: {"utente":nickname},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiencesPartecipate')
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