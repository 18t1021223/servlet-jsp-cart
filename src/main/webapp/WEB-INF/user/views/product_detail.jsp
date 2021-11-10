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
    <style>
        body {
            background-color: #edf1f5;
            margin-top: 20px;
        }

        .card {
            margin-bottom: 30px;
        }

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }

        .card .card-subtitle {
            font-weight: 300;
            margin-bottom: 10px;
            color: #8898aa;
        }

        .table-product.table-striped tbody tr:nth-of-type(odd) {
            background-color: #f3f8fa !important
        }

        .table-product td {
            border-top: 0px solid #dee2e6 !important;
            color: #728299 !important;
        }
    </style>
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
    <c:if test="${product != null}">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-lg-5 col-md-5 col-sm-6">
                        <div class="white-box text-center">
                            <img src="<c:url value='/assets/user/${product.image}'/>" class="img-responsive" width="200">
                        </div>
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-6">
                        <h4 class="box-title mt-5">${product.name}</h4>
                        <h2 class="mt-5">
                            <fmt:formatNumber type="currency"
                                              currencySymbol="₫"
                                              maxFractionDigits="0"
                                              value="${product.price}"/>
                        </h2>
                        <c:url var="addItem" value="/cart/add">
                            <c:param name="id" value="${product.productId}"/>
                        </c:url>
                        <a href="${addItem}" class="btn btn-primary btn-rounded">Add to cart</a>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <h3 class="box-title mt-5">General Info</h3>
                        <div class="table-responsive">
                            <table class="table table-striped table-product">
                                <tbody>
                                <tr>
                                    <td width="390">Category id</td>
                                    <td>${product.categoryId}</td>
                                </tr>
                                <tr>
                                    <td>Product id</td>
                                    <td>${product.productId}</td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>${product.quantity}</td>
                                </tr>
                                <tr>
                                    <td>Chapter</td>
                                    <td>${product.numberChapter}</td>
                                </tr>
                                <tr>
                                    <td>Create date</td>
                                    <td>${product.createDate}</td>
                                </tr>
                                <tr>
                                    <td>Author</td>
                                    <td>${product.author}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
