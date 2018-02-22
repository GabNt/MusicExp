$(document).ready(function(){

	$("#registrati").prop("disabled", true);
	
	

	$("#termini").on("click",function(){

		if($("#termini").is(':checked'))
			$("#registrati").prop("disabled", false);
		else
			$("#registrati").prop("disabled", true);

	});


});




$("#FormRegister").submit(function(e) {

	$.ajax({
		type: "POST",
		url: "RegistraUtente",
		data: $("#FormRegister").serialize(), 
		async:false,
		success: function(data)
		{
			alert(data);
			if(data.valueOf() == "puoiLoggare")
			{
				alert("Dall'email inserita risulti gia' registrato, effettua il login");
				$(location).attr('href', "/AliveMusic/login.jsp");
			}
			else if(data.valueOf() == "nicknameUguale")
			{
				alert("Il nickname che hai scelto appartiene già ad un altro utente, scegline un altro");
				$(location).attr('href', "/AliveMusic/register.jsp");
			}
			else
			{
				alert("Complimenti "+data.valueOf()+", sei dentro");
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