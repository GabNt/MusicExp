$(document).ready(function() {

	$("#FormLogin").submit(function(e) {

		$.ajax({
			type: "POST",
			url: "LoginCheck",
			data: $("#FormLogin").serialize(), 
			async:false,
			success: function(data)
			{
				if(data.valueOf() == "fallito")
				{
					alert("DATI ERRATI");
					$(location).attr('href', "/AliveMusic/login.jsp");
				}
				else
				{
					$(location).attr('href', "/AliveMusic/profilo.jsp");
				}

			},
			error: function(data)
			{
				alert("ERROR");
			}
		});

		e.preventDefault();

	});


});