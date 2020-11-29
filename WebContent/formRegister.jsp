
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
</head>

<body>
	<form action="Servlet" method="get">
		<h2>Sign Up</h2>

		<div class="form-group">
			<label for="email-ip">Email: </label> <input type="email"
				name="email" id="email-ip">
		</div>

		<div class="form-group">
			<label for="user-name">Username: </label> <input type="text"
				name="userName" id="user-name">
		</div>

		<div class="form-group">
			<label for="passwd">Password: </label> <input type="password"
				name="pass" id="passwd">
		</div>

		<div class="form-group">
			<label for="cf-passwd">Confirm password: </label> <input
				type="password" name="cfPass" id="cf-passwd">
		</div>

		<div class="form-group">
			<span>Gender: </span>
			<div>
				<label for="gender-m">Male</label> <input type="radio" name="gender"
					id="gender-m" value="m" checked="checked">
			</div>
			<div>
				<label for="gender-f">Female</label> <input type="radio"
					name="gender" id="gender-f" value="f">
			</div>
		</div>
		
		<div class="form-group">
			<label for="city">Province/City</label> <select name="city" id="city">
				<option value="HCM" disabled="disabled" selected="selected">Choose
				</option>
				<option value="HCM">TP HCM</option>
				<option value="DN">Ca Mau</option>
				<option value="NT">Dong Nai</option>
				<option value="BT">Ha Noi</option>
				<option value="BD">Ben Tre</option>
			</select>
		</div>

		<div class="form-group so-thich no-flex">
			<div>Interests:</div>
			<div>
				<input type="checkbox" name="hobby" value="An" id="an"> <label
					for="an">Eating</label>
			</div>
			<div>
				<input type="checkbox" name="hobby" value="Xem Phim" id="xem-phim">
				<label for="xem-phim">Watching Youtube</label>
			</div>
			<div>
				<input type="checkbox" name="hobby" value="cafe" id="cafe">
				<label for="cafe">Drinking coffee</label>
			</div>
			<div>
				<input type="checkbox" name="hobby" value="nghe nhac" id="music">
				<label for="music">Listening to music</label>
			</div>
		</div>

		<div class="form-group no-flex">
			<p>Introduce yourself:</p>
			<textarea name="info" rows="5" cols="50"></textarea>
		</div>

		<button type="submit" class='btn-submit'>Register</button>
	</form>
</body>
</html>