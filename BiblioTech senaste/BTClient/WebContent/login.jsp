<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>Insert title here</title>
</head>
<body>
	
	<div class="login-page">
		<div class="form">
			
			<h1>Logga in</h1>

			<form class="login-form" method="post" action="books?action=login">
				<input type="text" name="userID" placeholder="Användar ID" required
					autofocus> <input type="password" name="userPassword"
					placeholder="Lösenord" required> <input class="button" type="submit"
					value="Logga in">
				<p class="message">${error}
				</p>	
			</form>
		</div>
	</div>
</body>
</html>