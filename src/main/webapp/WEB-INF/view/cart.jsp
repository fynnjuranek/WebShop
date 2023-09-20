<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="/styles/cartStyle.css">
</head>
<body>
	<c:import url="banner.jsp"/>
	
	<c:set var="cart" value="${requestScope.cart}"/>
	<p> there are <c:out value="${cart.numberOfArticles}"/> Articles in cart.</p>				
	<table>
				<tr>
					<th><h3>Quantity</h3></th>
					<th><h3>Title</h3></th>
					<th><h3>Price</h3></th>
				</tr>
					<c:forEach var="cartItem" items="${requestScope.cartItems}">
					<tr>
						<td align=center><c:out value="${cartItem.quantity}"/></td>
						<td><c:out value="${cartItem.article.name}"/></td>
						<td><c:out value="${cartItem.article.price}"/> EURO </td>
						<td><a href="<c:url value="/dispatchAction/removeArticle">
										<c:param name="articleId" value="${cartItem.article.articleId}"/>
										</c:url>">Remove article</a></td>
					</tr>
					</c:forEach>			
					<tr>
						<td style=border:none></td><td align=right>Total Price: </td><td><c:out value="${cart.totalPrice}"/> EURO</td></tr>
				</table>
				
	<div class ="wrapper">
		<div class="orderLink">	
		<p><a href="/dispatchAction/orderArticles">Order Articles</a></p>
		</div>
		<div class="catalogLink">
		<p><a href="/dispatchAction/showCatalog">Show Catalog</a></p>
		</div>
	</div>

	<c:import url="imprint.jsp"/>
	
</body>
</html>