<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ include file="include/menu.jsp" %>

	<script>
		$(function(){
			$('#deleteButton').click(function(){
				$('#myForm').attr('action','auth/removeProduct').submit();
			});
			
			$('#addToCartButton').click(function() {
				$('#myForm').attr('action','addProductToCart').submit();
			});
		});
	</script>
	
	<h1>Liste des produits</h1>
	
	<c:if test="${empty listProducts}">
		<p>Aucun produit</p>
	</c:if>

	<ul data-role="listview" data-inset="true">
		<c:forEach items="${listProducts}" var="p">
			<c:set var="key" scope="page" value="${p.id}"/>
			<li><a id="showButton" href="showProduct?id=<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key")) %>">${p.name}</a></li>
		</c:forEach>
	</ul>
		
<jsp:include page="include/footer.jsp"></jsp:include>
