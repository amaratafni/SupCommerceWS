<%@include file="include/menu.jsp" %>

	<script>
		$(function() {
			$('#paiementForm').submit(function() {
				if(notNullOrBlank($('#numCB').val()) && notNullOrBlank($('#endValidation').val()) && notNullOrBlank($('#cryptogramme').val())) {
					var errorMessage = '';
					
					if(isNaN($('#numCB').val()) || $('#numCB').val().length != 16) {
						errorMessage += 'Votre num&eacute;ro de CB doit contenir 16 chiffres. <br/>';
					}
					if(!$('#endValidation').val().match(/0[1-9]|1[012]([ /.])\d\d/)) {
						errorMessage += "La date d'expiration de votre carte doit &ecirc;tre au format suivant : mm/aa. <br/>";
					}
					if(isNaN($('#cryptogramme').val()) || $('#cryptogramme').val().length != 3) {
						errorMessage += "Votre cryptogramme doit contenir 3 chiffres.";
					}
					
					if($.trim(errorMessage)!='') {
						printError(errorMessage);
						return false;
					}
					else $(this).attr('action','payment');
					
				} else {
					printError('Veuillez remplir tous les champs.');
					return false;
				}
			});
			
		});
	</script>
	
	<h1>Informations carte bleue</h1>
	<div id="error"></div>
	
	<form id="paiementForm" name="paiementForm" method="POST">
		<div data-role="fieldcontain">
			<label for="numCB">Num&eacute;ro de votre carte bleue (16 chiffres)</label>
			<input id="numCB" name="numCB" type="text" value=""/>
		</div>
		<div data-role="fieldcontain">
			<label for="endValidation">Expiration carte (mm/aa)</label>
			<input id="endValidation" name="endValidation" type="text" value=""/>
		</div>
		<div data-role="fieldcontain">
			<label for="cryptogramme">Cryptogramme (3 chiffres)</label>
			<input id="cryptogramme" name="cryptogramme" type="text" value=""/>
		</div>
		
		<input id="paiementButton" name="paiementButton" type="submit" value="Valider mon paiement"/>
	</form>
	
<%@include file="include/footer.jsp" %>