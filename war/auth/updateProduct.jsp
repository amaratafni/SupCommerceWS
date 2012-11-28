<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@include file="include/menu.jsp" %>

	<script>
		$(function() {
			$('#updateForm').submit(function() {
				if(notNullOrBlank($('#name').val()) && notNullOrBlank($('#content').val()) && notNullOrBlank($('#price').val()) && notNullOrBlank($('#category').val())) {
					$(this).attr('action','updateProduct');
				} else {
					printError('Veuillez remplir tous les champs.');
					return false;
				}
			});
		});
	</script>
	
	<h1>Mise &agrave; jour du produit</h1>
	<div id="error"></div>
	
	<form id="updateForm" name="updateForm" method="POST">
		<div data-role="fieldcontain">
			<label for="name">Nom</label>
			<input id="name" name="name" type="text" value="${product.name}"/>
		</div>
		<div data-role="fieldcontain">
			<label for="content">Contenu</label>
			<input id="content" name="content" type="text" value="${product.content}"/>
		</div>
		<div data-role="fieldcontain">
			<label for="price">Prix</label>
			<input id="price" name="price" type="text" value="${product.price}"/>
		</div>
		<div data-role="fieldcontain">
			<label for="category">Cat&eacute;gorie</label>
			<select id="category" name="category" data-native-menu="false">
				<c:forEach items="${categories}" var="c">
					<c:set value="${c.key}" scope="page" var="key"></c:set>
					<option value="<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key"))  %>">${c.name}</option>
				</c:forEach>
			</select>
		</div>
		
		<c:set var="id" scope="page" value="${product.key}"/>
		<input type="hidden" id="productId" name="productId" value="<%= KeyFactory.keyToString((Key)pageContext.getAttribute("id")) %>"/>
		
		<fieldset class="ui-grid-a">
			<div class="ui-block-a">
				<input id="updateButton" name="updateButton" type="submit" value="Appliquer"/>
			</div>
			<div class="ui-block-b">
				<a data-role="button" href="../listProduct">Annuler</a>
			</div>
		</fieldset>
	</form>
	
	<script>
		// On selectionne la categorie à laquelle appartient l'objet //
		$('#category').val("<%= request.getAttribute("categoryKey") %>");
	</script>
	
<%@include file="../include/footer.jsp" %>