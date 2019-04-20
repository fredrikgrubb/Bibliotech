<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<p>Available Books</p>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ISBN</th>
			<th>Title</th>

			<th>Author</th>
			<th>BookCopy</th>

			<th>Option</th>
		</tr>
		<c:forEach var="book" items="${listBooks}">
			<tr>
				<td>${book.id.isbn}</td>
				<td>${book.title}</td>

				<td>${book.author.authorName}</td>
				<td>${book.id.copyNbr}</td>

				<td><a
					href="books?action=add2&isbn=${book.id.isbn }&copyNbr=${book.id.copyNbr }">Låna</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>