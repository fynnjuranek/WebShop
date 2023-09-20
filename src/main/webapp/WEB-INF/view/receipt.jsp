<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/styles/receiptStyle.css">
<title>Receipt</title>
</head>
<body>
	<c:import url="banner.jsp"/>

		<c:set var="cart" value="${requestScope.cart}"/>
		<p>The following <c:out value="${cart.numberOfArticles}"/> are orderd for the user <c:out value="${requestScope.name}"/>
		  Credit card number: <c:out value="${requestScope.iban}"/></p>
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
			</tr>
		</c:forEach>
			<tr>
				<td style=border:none></td><td align=right>Total Price: </td><td><c:out value="${cart.totalPrice}"/> EURO</td>
			</tr>
		</table>
		
		<p>Thank you!</p>
		<p><a href="/dispatchAction/showCatalog">Back to Catalog</a></p>

	<c:import url="imprint.jsp"/>
	
</body>
</html>