<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ include file="include/menu.jsp" %>

	<script>
		$(function(){
// 			$('#updateButton').click(function(){
// 				console.log("update");
// 				$('#myForm').attr('action','auth/updateProduct').submit();
// 			});
			
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
	
	
<!-- 	<div data-role="collapsible-set"> -->
<%-- 		<c:forEach items="${listProducts}" var="p"> --%>
<!-- 			<form id="myForm" name="myForm" method="POST" action=""> -->
<!-- 				<div data-role="collapsible"  data-theme="a" data-content-theme="a"> -->
<%-- 					<h3><c:out value="${p.name}"></c:out></h3> --%>
<!-- 					<div class="ui-grid-solo"> -->
<!-- 						<div class="ui-bar ui-bar-a"> -->
<!-- 							<span>Content: </span> -->
<%-- 							<span><c:out value="${p.content}"></c:out></span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="ui-grid-solo"> -->
<!-- 						<div class="ui-bar ui-bar-a"> -->
<!-- 							<span>Cat&eacute;gorie: </span> -->
<%-- 							<span><c:out value="${p.category.name}"></c:out></span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="ui-grid-solo"> -->
<!-- 						<div class="ui-bar ui-bar-a"> -->
<%-- 							<span>Price: <c:out value="${p.price}">&euro;</c:out></span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
				
<%-- 					<c:set value="${p.id}" scope="request" var="key"></c:set> --%>
<%-- 					<input type="hidden" id="id" name="id" value="<%= KeyFactory.keyToString((Key)request.getAttribute("key")) %>" /> --%>
					
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${!empty sessionScope.username}"> Admin  --%>
<!-- 							<fieldset class="ui-grid-a"> -->
<!-- 								<div class="ui-block-a"> -->
<%-- 									<a id="updateButton" data-role="button" href="auth/updateProduct?id=<%= KeyFactory.keyToString((Key)request.getAttribute("key")) %>">Mettre &agrave; jour</a> --%>
<!-- 								</div> -->
<!-- 								<div class="ui-block-b"> -->
<!-- 									<a id="deleteButton" data-role="button">Supprimer</a> -->
<!-- 								</div> -->
<!-- 							</fieldset> -->
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> User --%>
<!-- 							<a id="addToCartButton" data-role="button">Ajouter au panier</a> -->
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<!-- 				</div> -->
<!-- 			</form> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
	
	<ul data-role="listview" data-inset="true">
		<c:forEach items="${listProducts}" var="p">
			<c:set var="key" scope="page" value="${p.id}"/>
			<li><a id="showButton" href="showProduct?id=<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key")) %>">${p.name}</a></li>
		</c:forEach>
	</ul>
		
<jsp:include page="include/footer.jsp"></jsp:include>
