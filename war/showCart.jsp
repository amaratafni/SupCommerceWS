<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ include file="include/menu.jsp" %>

<script>
	$(function() {
	});
</script>

<h1>Mon panier</h1>

<c:choose>
	<c:when test="${empty sessionScope.shoppingCart}">
		<p>Votre panier est vide.</p>
	</c:when>
	<c:otherwise>
		<ul data-role="listview" data-inset="true">
			<c:forEach items="${sessionScope.shoppingCart}" var="p">
				<c:set var="key" scope="page" value="${p.id}"/>
				<li data-icon="delete"><a id="delete" href="#" onClick="confirmBeforeDeleteWithoutForm(this,'removeProductToCart?id=<%= KeyFactory.keyToString((Key)pageContext.getAttribute("key")) %>')">${p.name} - ${p.price}&euro;</a></li>
			</c:forEach>
		</ul>
		
		<br/>
		
		<p>Total: <c:out value="${totalCart}"/>&euro;</p>
		
		<div data-role="fieldcontain">
			<a id="paiementButton" data-role="button" data-theme="a">Payer</a>
			<a id="emptyCartButton" href="#" data-role="button" onClick="confirmBeforeDeleteWithoutForm(this,'emptyCart');">Vider le panier</a>
		</div>
		
	</c:otherwise>
</c:choose>

<jsp:include page="include/footer.jsp"></jsp:include>