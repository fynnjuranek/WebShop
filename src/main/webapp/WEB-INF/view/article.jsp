<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/styles/articleStyle.css">
<title>Article</title>
</head>
<body>
	<c:import url="banner.jsp"/>
	
		<c:set var="article" value="${requestScope.article}"/>
		<table>
			<tr><th colspan="2">Article info</th></tr>
			<tr><td>Article ID:</td><td><c:out value="${article.articleId}"/> </td></tr>
			<tr><td>Article-Name:</td><td><c:out value="${article.name}"/> </td></tr>
			
			<c:set var="book" value="${requestScope.book}"/>
			<c:if test="${book != null}">
				<tr><td>Author:</td> <td><c:out value="${book.author}"/></td></tr>
				<tr><td>Category: </td><td><c:out value="${book.bookCategory}"/></td></tr>
			</c:if>
			<c:set var="CD" value="${requestScope.cd}"/>
			<c:if test="${cd != null}">
				<tr><td>Musician: </td><td><c:out value="${cd.artist}"/></td></tr>
				<tr><td>Producer: </td><td><c:out value="${cd.manufactor}"/></td></tr>
			</c:if>
				<tr><td>Price:</td><td><c:out value="${article.price}"/> EURO </td></tr>
			</table>
			
		<div class ="catalogLink">
		<p><a href ="/dispatchAction/showCatalog">Show Catalog</a></p>
		</div>
		
	<c:import url="imprint.jsp"/>

</body>
</html>