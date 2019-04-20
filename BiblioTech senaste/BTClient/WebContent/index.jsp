<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="about.css">

<title>BiblioTech</title>
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
	<center>
		<nav class="navbar navbar-inverse navbar-fixed-top"> <h1><a href="books?action=login">| Home |</a> <a
			href="testPage.jsp">| Test |</a> <a href="books?action=about">|
			About |</a> <a href="books?action=add">| Add |</a></h1> </nav>
		
		<br>
		Hello User: ${User} <br> <br> <a href="books?action=logout"><button>Logga ut</button></a>
		${error }
		
		
		<div class="table-title">
			<h3>Tillgängliga böcker</h3>
		</div>
		<table class="table-fill">
			<thead>
				<tr>
					<th class="text-left">ISBN</th>
					<th class="text-left">Titel</th>
					<th class="text-left">Författare</th>
					<th class="text-left">Bok kopia</th>
					<th class="text-left"></th>
				</tr>
			</thead>
			<tbody class="table-hover">
				<c:forEach var="book" items="${listAvailableBooks}">
					<tr>
						<td class="text-left">${book.id.isbn}</td>
						<td class="text-left">${book.title}</td>

						<td class="text-left">${book.author.authorName}</td>
						<td class="text-left">${book.id.copyNbr}</td>

						<td class="text-left"><a
							href="books?action=add2&isbn=${book.id.isbn }&copyNbr=${book.id.copyNbr }&userID=${User}">Låna</a>
						</td>
					</tr>
				</c:forEach>


			</tbody>
		</table>


		<div class="table-title">
			<h3>Mina lån</h3>
		</div>
		<table class="table-fill">
			<thead>
				<tr>
					<th class="text-left">ISBN</th>
					<th class="text-left">Titel</th>
					<th class="text-left">Författare</th>
					<th class="text-left">Senast återlämnad</th>
					<th class="text-left"></th>
				</tr>
			</thead>
			<tbody class="table-hover">
				<c:forEach var="loan" items="${listLoans}">
					<tr>
						<td class="text-left">${loan.id.bookISBN}</td>
						<td class="text-left">${loan.book.title}</td>

						<td class="text-left">${loan.book.author.authorName}</td>
						<td class="text-left">${loan.expiryDate}</td>

						<td class="text-left"><a
							href="books?action=returnBook&bookISBN=${loan.id.bookISBN}&bookCopy=${loan.id.bookCopy}&userID=${loan.id.userID}">Lämna
								tillbaka</a></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>

	</center>

</body>
</html>