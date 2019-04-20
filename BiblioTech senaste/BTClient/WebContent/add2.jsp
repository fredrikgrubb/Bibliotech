<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
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

	<%

	String userName = (String) session.getAttribute("User");
	if (null == userName) {
		request.setAttribute("Error", "Session has ended.  Please login.");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	
		LocalDate today = LocalDate.now();
		LocalDate twoWeeksFromToday = today.plus(2, ChronoUnit.WEEKS);
	%>
	
  <div align="center">
	Hello User: ${User}
	<br>
	<br>
	<nav>
	<a href="books?action=login">| Home |</a>
	<a href="testPage.jsp">| Test |</a>
	<a href="aboutPage.jsp">| About |</a>
	</nav>
	<br>
	<a href="books?action=logout"><button>Logga ut</button></a> ${error }
		<p>Available Books</p>

	<form class="login-form" method="post" action="books?action=add2">
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td>ISBN</td>
				<td><input type="text" name="bookISBN"
					value="<%=request.getParameter("isbn")%>" readonly></td>
			</tr>
			<tr>
				<td>CopyNbr</td>
				<td><input type="text" name="bookCopy"
					value="<%=request.getParameter("copyNbr")%>" readonly></td>
			</tr>
			<tr>
				<td>UserID</td>
				<td><input type="text" name="userID" value="${User}" readonly></td>
			</tr>
			<tr>
				<td>LoanDate</td>
				<td><input type="text" name="loanDate" value=<%=today%> readonly></td>
			</tr>
			<tr>
				<td>ExpiryDate</td>
				<td><input type="text" name="expiryDate" value=<%=twoWeeksFromToday%> readonly></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Låna"><br>${error }</td>

			</tr>
		</table>
	</form>
	</div>

</body>
</html>