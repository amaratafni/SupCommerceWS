<%@include file="include/menu.jsp" %>
	
	<script>
		$(function() {
			$('#addForm').submit(function(){
				if(notNullOrBlank($('#name').val())) {
					$('#addForm').attr('action','addCategory');
				} else {
					printError('Veuillez donner un nom &agrave; la nouvelle cat&eacute;gorie.');
					return false; // cancel submit
				}
			});
		})
	</script>
	
	<h1>Nouvelle cat&eacute;gorie</h1>
	<div id="error"></div>
	
	<form id="addForm" name="addForm" method="POST">
		<div data-role="fieldcontain">
			<label for="name">Nom </label>
			<input type="text" id="name" name="name"/>
		</div>
		<fieldset class="ui-grid-a">
			<div class="ui-block-a"><input type="submit" value="Ajouter"/></div>
			<div class="ui-block-b"><a href="../listProduct" data-role="button" >Annuler</a></div>
		</fieldset>
	</form>

<jsp:include page="../include/footer.jsp"></jsp:include>