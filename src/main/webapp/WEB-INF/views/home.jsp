<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link href="<c:url value="/assets/css/shop-homepage.css"/>" rel="stylesheet"/>
</head>
<body class="bg-light">
<!-- Navigation -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Trang chủ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/guest/register">Đăng ký</a>
            </li>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/guest/login">Đăng nhập</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="/user/logout">Đăng xuất</a>
                </li>
                <li class="nav-item">
                    <p class="nav-link">${user.name}</p>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/order">Đơn mua</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="/cart/view" style="font-weight: bold;">
                    <i class="fa fa-shopping-cart" aria-hidden="false"></i>
                    Giỏ Hàng (${cart == null ? 0 : cart.size()})
                </a>
            </li>
        </ul>
    </div>
</nav>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <p class="lead">CHỦ ĐỀ SÁCH</p>
            <div class="list-group">
                <c:forEach var="item" items="${categories}">
                    <a href="/product/category?categoryId=${item.categoryId}" class="list-group-item">
                            ${item.name}
                    </a>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-9">
            <div>
                <h3 style="Text-align:Center; color:red"> SÁCH MỚI </h3>
                <div class="row">
                    <%--@elvariable id="products" type="java.util.List"--%>
                    <c:forEach var="item" items="${products}">
                        <div class="col-sm-4 col-lg-4 col-md-4" style="margin-bottom: 20px">
                            <div class="thumbnail">
                                <a href="/product?id=${item.productId}" class="text-dark">
                                    <c:if test="${item.image == null}">
                                        <img src="https://i.pinimg.com/564x/f9/11/d3/f911d38579709636499618b6b3d9b6f6.jpg"
                                             width="200" height="226">
                                    </c:if>
                                    <c:if test="${item.image != null}">
                                        <img src="<c:url value="/assets/${item.image}"/>" width="200">
                                    </c:if>
                                    <div>
                                        <br/>
                                        <h4>${item.name}</h4>
                                    </div>
                                </a>
                            </div>
                            <div>
                                <fmt:formatNumber type="currency"
                                                  currencySymbol="₫"
                                                  maxFractionDigits="0"
                                                  value="${item.price}"/>
                            </div>
                            <c:url var="addItem" value="/cart/add">
                                <c:param name="id" value="${item.productId}"/>
                            </c:url>
                            <a href="${addItem}">add to cart</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
