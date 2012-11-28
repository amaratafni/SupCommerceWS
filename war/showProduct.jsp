<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ include file="include/menu.jsp" %>

	<script>
		$(function() {
			$('#updateButton').click(function() {
				$('#myForm').attr('method','GET');
				$('#myForm').attr('action','auth/updateProduct').submit();
			});
			
			$('#deleteButton').click(function() {
				$('#myForm').attr('method','POST');
				$('#myForm').attr('action','auth/removeProduct');
				confirmBeforeDelete($(this),$('#myForm'));
			});
			
			$('#addToCartButton').click(function() {
				$('#myForm').attr('method','POST');
				$('#myForm').attr('action','addProductToCart').submit();
			});
		});
	</script>
	
	<c:if test="${!empty product}">
		<h1><c:out value="${product.name}"></c:out></h1>
		<p>Contenu: <c:out value="${product.content}"></c:out></p>
		<p>Prix: <c:out value="${product.price}"></c:out>&euro;</p>
		<p>Cat&eacute;gorie: <c:out value="${product.category.name}"></c:out></p>
		
		<form id="myForm" name="myForm">
			<c:set value="${product.key}" scope="page" var="key"></c:set>
			<input type="hidden" id="id" name="id" value="<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key")) %>" />
		</form>
					
		<c:choose>
			<%-- Admin --%>
			<c:when test="${!empty sessionScope.username}">
				<fieldset class="ui-grid-a">
					<div class="ui-block-a">
						<a id="updateButton" data-role="button">Mettre &agrave; jour</a>
					</div>
					<div class="ui-block-b">
						<a id="deleteButton" href="#" data-role="button">Supprimer</a>
					</div>
				</fieldset>
			</c:when>
			<%-- User --%>
			<c:otherwise>
				<a id="addToCartButton" data-role="button">Ajouter au panier</a>
			</c:otherwise>
		</c:choose>
	</c:if>

<%@ include file="include/footer.jsp" %>