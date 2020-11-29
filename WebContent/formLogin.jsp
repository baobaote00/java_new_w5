<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
	<div style="text-align: center">
		<h1> Login</h1>
		<form action="UserLoginServlet" method="post" id="loginForm">
			<label for="username">Username:</label>
			<input name="username" size="30" />
			<br><br>
			<label for="password">Password:</label>
			<input type="password" name="password" size="30" />
			<br>${message}<br><br>			
			<button type="submit">Login</button>
		</form>
	</div>
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$("#loginForm").validate({
			rules: {
				username: {
					required: true,
					username: true
				},
		
				password: "required",
			},
			
			messages: {
				username: {
					required: "Please enter email",
					username: "Please enter a valid email address"
				},
				
				password: "Please enter password"
			}
		});

	});
</script>
</html>