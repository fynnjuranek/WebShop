<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/styles/orderStyle.css">
<title>Order</title>
</head>
<body>
	<c:import url="banner.jsp"/>
	
	<c:set var="cart" value="${requestScope.cart}"/>
	<div class="wrapper">
		<div class="cart">
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
				</tr>
			</c:forEach>
				<tr>
					<td style=border:none></td>
					<td align=right>Total Price: </td>
					<td><c:out value="${cart.totalPrice}"/> EURO</td>
				</tr>
			</table>
			
		<div class="catalogLink">	
		<p><a href="/dispatchAction/showCatalog">Show Catalog</a></p>
		</div>
	</div>
		<div class="submitForm">
		<p>The following information is needed for ordering:</p>
			<form name="submitForm" method="post" action="showReceipt" id="order">
				<table>
				<tr><td><label for="name">Name: </label></td>
				<td><input type="text" name="name" id="name" maxlength="30"></td></tr>	
					<tr><td><label for="iban">Credit card number: </label></td>
					<td><input type="text" name="iban" id="iban" maxLength="40"></td></tr>
				
				<tr><td><td><button type="submit">Submit</button></td></tr>				
				</table>
			</form>
		</div>
	</div>

	<c:import url="imprint.jsp"/>

</body>
</html>