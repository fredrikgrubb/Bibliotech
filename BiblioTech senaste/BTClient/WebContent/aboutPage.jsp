<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="about.css">
<title>BiblioTech /About</title>
</head>
<body>

<%
String authorName = "";
String title = "";
%>
	<center>
		<br> <a href="books?action=login">| Home |</a> <a href="testPage.jsp">|
			Test |</a> <a href="books?action=about">| About |</a> <br>


		<div class="table-title">
			<h3>Böcker</h3>
		</div>
		<form  method="post" action="books?action=about">
			<table cellpadding="2" cellspacing="2">
				<tr>
					<td>Titel</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Sök"><br>${error }</td>

				</tr>
			</table>
		</form>
		<form  method="post" action="books?action=about">
			<table cellpadding="2" cellspacing="2">
				<tr>
					<td>Författare</td>
					<td><input type="text" name="authorName"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Sök" ><br>${error }</td>
					
					<td>&nbsp;</td>
					<td><a href="books?action=about"><button>Visa alla</button></a></td>

				</tr>
			</table>
		</form>

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
				<c:forEach var="book" items="${booksByAuthor}">
					<tr>
						<td class="text-left">${book.id.isbn}</td>
						<td class="text-left">${book.title}</td>

						<td class="text-left">${book.author.authorName}</td>
						<td class="text-left">${book.id.copyNbr}</td>

						<td class="text-left"><a
							href="books?action=add2&isbn=${book.id.isbn }&copyNbr=${book.id.copyNbr }">Låna</a>
						</td>
					</tr>
				</c:forEach>
			<tbody class="table-hover">
				<c:forEach var="book" items="${booksByTitle}">
					<tr>
						<td class="text-left">${book.id.isbn}</td>
						<td class="text-left">${book.title}</td>

						<td class="text-left">${book.author.authorName}</td>
						<td class="text-left">${book.id.copyNbr}</td>

						<td class="text-left"><a
							href="books?action=add2&isbn=${book.id.isbn }&copyNbr=${book.id.copyNbr }">Låna</a>
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="book" items="${listBooks}">
					<tr>
						<td class="text-left">${book.id.isbn}</td>
						<td class="text-left">${book.title}</td>

						<td class="text-left">${book.author.authorName}</td>
						<td class="text-left">${book.id.copyNbr}</td>

						<td class="text-left"><a
							href="books?action=add2&isbn=${book.id.isbn }&copyNbr=${book.id.copyNbr }">Låna</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>


	</center>
</body>
</html>