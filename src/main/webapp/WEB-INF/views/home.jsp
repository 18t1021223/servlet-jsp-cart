<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 15/10/2021
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1> Products </h1>
<br>
<a href="/cart/view">view cart</a>
<br>
<table border="1" width="70%">
    <tr>
        <th>id</th>
        <th>image</th>
        <th>name</th>
        <th>price</th>
        <th></th>
    </tr>
    <%--@elvariable id="products" type="java.util.List"--%>
    <c:forEach var="item" items="${products}">
        <tr>
            <td>${item.id}</td>
            <td align="center">
                <img src="<c:url value="/assets/images/${item.image}"/>" alt="${item.image}">
            </td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td align="center">
                <c:url var="addItem" value="/cart/add">
                    <c:param name="id" value="${item.id}"/>
                    <c:param name="name" value="${item.name}"/>
                    <c:param name="image" value="${item.image}"/>
                    <c:param name="price" value="${item.price}"/>
                </c:url>
                <a href="${addItem}">add to cart</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
