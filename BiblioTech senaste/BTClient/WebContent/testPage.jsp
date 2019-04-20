<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center><br>
	<a href="books?action=login">| Home |</a>
	<a href="testPage.jsp">| Test |</a>
	<a href="books?action=about">| About |</a>
	<br>

	<p>
		Här kan du skriva in ditt TestCase eller TestSuite:<br>
	</p>
	<form action="TestServlet" method="get" name="SelectTest">
		<input type="text" name="suite" size=60 value= /> <input
			type="submit" value="Run" />
	</form>
	<hr>
	<p>
		Här kan du välja en eller flera av följande test:<br>
	</p>
	<form action="TestServlet" method="get" name="SelectTest">
		<select name="suite" size="5" multiple>
			<option value="test.BookTest">BookTest</option>
			<option value="test.UserTest">UserTest</option>
			<option value="test.AllTests">AllTests</option>
		</select> <input type="submit" value="Run" />
	</form>
</center>
</body>
</html>