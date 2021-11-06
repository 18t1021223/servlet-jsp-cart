<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 15/10/2021
  Time: 11:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1> Cart </h1>
<br>
<c:set var="cartSession" value="${sessionScope.cart}"/>
<c:choose>
    <c:when test="${ cartSession != null && !cartSession.isEmpty() }">
        <table border="1" width="70%">
            <tr>
                <th>id</th>
                <th>image</th>
                <th>name</th>
                <th>price</th>
                <th>quantity</th>
                <th>total</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${cartSession}">
                <c:set var="product" value="${item.value.product}"/>
                <tr>
                    <td>${item.key}</td>
                    <td align="center">
                        <img src="<c:url value="/assets/images/${product.image}"/>" alt="${product.image}">
                    </td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>
                        <input type="number" min="1" max="100" name="quantity" value="${item.value.quantity}"
                               form="formEdit">
                        <input type="hidden" form="formEdit" name="id" value="${item.key}">
                    </td>
                    <td><fmt:formatNumber type="currency"
                                          currencySymbol="₫"
                                          maxFractionDigits="0"
                                          value="${ item.value.total }"/>
                    </td>

                    <td align="center">
                        <c:url var="deleteItem" value="/cart/delete">
                            <c:param name="id" value="${item.key}"/>
                        </c:url>
                        <input type="checkbox" name="id" value="${item.key}" form="formDelete">
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="5"><a href="/home">add more</a></td>
                <td align="center">
                    <form action="/cart/edit" method="get" id="formEdit">
                        <input type="submit" value="update">
                    </form>
                </td>
                <td align="center">
                    <form action="/cart/delete" method="get" id="formDelete">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
            <tr style="height: 50px;">
                <td colspan="5" align="right">
                    total price
                </td>
                <td colspan="2" align="right">
                    <c:set var="totalPrice" value="0"/>
                    <c:forEach var="item" items="${cartSession.values()}">
                        <c:set var="totalPrice" value="${totalPrice + item.total}"/>
                    </c:forEach>
                    <b>
                        <fmt:formatNumber type="currency"
                                          currencySymbol="₫"
                                          maxFractionDigits="0"
                                          value="${totalPrice}"/>
                    </b>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <a href="/home">add more</a>
    </c:otherwise>
</c:choose>

</body>
</html>
