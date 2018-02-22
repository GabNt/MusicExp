function dammiExperienceCreate()
{
	alert("ajax experience Create ()");
	$.ajax({
		type: "POST",
		url: "experienceCreate",
		datatype: "json",
		data: {"utente":nickname},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiencesCreate')
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
						descrizione : objs[i].descrizione
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