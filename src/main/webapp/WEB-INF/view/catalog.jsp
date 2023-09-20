<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   
 	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalog</title>
<link rel="stylesheet" href="/styles/catalogStyle.css">
</head>
<body>

	<c:import url="banner.jsp"/>

	<c:set var="article" value="${requestScope.article}"/>
	<c:if test="${article != null}">
		<h2>You have added following article into the cart:<br><c:out value="${article.name}"/></h2>
	</c:if>
	<c:set var="cart" value="${requestScope.cart}"/>
	<c:if test="${cart.cartItems.size() != 0}">
		<div class ="catalogLink">
		<p><a href ="/dispatchAction/showCart">Show Cart</a></p>
		</div>
	</c:if>

	<table>
		<tr><th>Article Name: </th><th>Price: </th></tr>
		
		<c:forEach var="article" items="${articles}">
			<tr>
				<td><a href ="<c:url value="/dispatchAction/showArticle">
								<c:param name="articleId" value="${article.articleId}"/>
								</c:url>">${article.name}</a></td>
				<td><c:out value="${article.price}"/> Euro</td>
				<td><a href ="<c:url value="/dispatchAction/addArticle">
								<c:param name="articleId" value="${article.articleId}"/>
								</c:url>"> Add article </a>
			</tr>
		</c:forEach>
	</table>

	<c:import url="imprint.jsp"/>
	
</body>
</html>