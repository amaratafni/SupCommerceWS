<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@include file="include/menu.jsp" %>

	<script>
		$(function() {
			$("#addForm").submit(function(){
				if(notNullOrBlank($('#name').val()) && notNullOrBlank($('#content').val()) && notNullOrBlank($('#price').val()) && notNullOrBlank($('#category').val())) {
					if(isNaN($('#price').val())) {
						printError("Le prix doit &ecirc;tre un nombre.");
						return false;
					} else $(this).attr('action','addProduct');
				} else {
					printError("Veuillez remplir tous les champs du formulaire.");
					return false;
				}
			});
		});
	</script>
	
	<h1>Ajout d'un produit</h1>
	<div id="error"></div>
	
	<c:choose>
		<c:when test="${empty categories}">
			<p>Vous devez ajouter au moins une cat&eacute;gorie avant de pouvoir ajouter un produit.</p>
			<a href="addCategory" data-role="button">Ajouter une cat&eacute;gorie</a>
		</c:when>
		<c:otherwise>
			<form id="addForm" name="addForm" method="POST">
				<div data-role="fieldcontain">
					<label for="name">Nom </label>
					<input id="name" name="name" type="text"/>
				</div>
				<div data-role="fieldcontain">
					<label for="content">Contenu </label>
					<input id="content" name="content" type="text"/>
				</div>
				<div data-role="fieldcontain">
					<label for="price">Prix </label>
					<input id="price" name="price" type="text"/>
				</div>
				<div data-role="fieldcontain">
					<label for="category">Cat&eacute;gorie </label>
					<select id="category" name="category" data-native-menu="false">
						<c:forEach items="${requestScope.categories}" var="c">
							<c:set var="key" scope="page" value="${c.key}"></c:set>
							<option value="<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key")) %>"><c:out value="${c.name}"></c:out></option>
						</c:forEach>
					</select>
				</div>
				
				<fieldset class="ui-grid-a">
					<div class="ui-block-a"><input id="addButton" name="addButton" type="submit" value="Ajouter"/></div>
					<div class="ui-block-b"><a href="../listProduct" data-role="button">Annuler</a></div>
				</fieldset>
			</form>
		</c:otherwise>
	</c:choose>
	
<%@ include file="../include/footer.jsp" %>