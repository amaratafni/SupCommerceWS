	<%@include file="header.jsp" %>
	
		<div data-role="header">
			<h1>SupCommerce</h1>
			
			<%-- On affiche login ou logout --%>
			<c:choose>
				<c:when test="${empty sessionScope.username}">
					<a href="login" data-role="button">Login</a>
				</c:when>
				<c:otherwise>
					<a href="logout" data-role="button">Logout</a>
				</c:otherwise>
			</c:choose>
			
			<%-- On crée un bouton 'cart' et on affiche le nombre de produits dans le panier --%>
			<c:choose>
				<c:when test="${empty sessionScope.shoppingCart}">
					<a href="showCart" data-role="button" data-iconpos="left" data-icon="cart">Cart (0)</a>
				</c:when>
				<c:otherwise>
					<a href="showCart" data-role="button" data-iconpos="left" data-icon="cart">Cart (<c:out value="${fn:length(sessionScope.shoppingCart)}"/>)</a>
				</c:otherwise>
			</c:choose>
			
			<br/>
			
			<%-- On ajuste la taille de navbar --%>
			<c:choose>
				<c:when test="${empty sessionScope.username}">
					<div data-role="navbar">
				</c:when>
				<c:otherwise>
					<div data-role="navbar" data-grid="b">
				</c:otherwise>
			</c:choose>
			
			
			<ul>
				<%-- User & Admin --%>
				<li><a href="listProduct" data-role="button">Liste des produits</a></li>
				
				<%-- Admin --%>
				<c:if test="${!empty sessionScope.username}">
					<li><a href="auth/addProduct" data-role="button" >Ajouter un produit</a></li>
					<li><a href="auth/addCategory" data-role="button">Ajouter une cat&eacute;gorie</a></li>
				</c:if>
			</ul>
			
			</div> <!-- navbar -->

		</div>
		
		<div data-role="content">