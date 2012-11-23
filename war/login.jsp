<%@ include file="include/menu.jsp" %>

	<script>
		$(function() {
			$('#loginButton').click(function(){
				if(notNullOrBlank($('#username').val()) && notNullOrBlank($('#password').val())) {
					$('#loginForm').submit();
				} else printError('Veuillez remplir tous les champs.');
			});
		});
	</script>
	
	<h1>Connexion</h1>
	<div id="error"></div>
	
	<form id="loginForm" name="loginForm" method="POST" action="login">
		<div data-role="fieldcontain">
			<label for="username">Login</label>
			<input id="username" name="username" type="text" value=""/>
		</div>
		<div data-role="fieldcontain">
			<label for="password">Password</label>
			<input id="password" name="password" type="password" value=""/>
		</div>
		<div data-role="fieldcontain">
			<input id="loginButton" name="loginButton" type="button" value="Login"/>
		</div>
	</form>

<%@ include file="include/footer.jsp" %>