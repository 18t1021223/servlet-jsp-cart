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
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            margin-top: 20px;
            background: #eee;
        }

        .ui-w-40 {
            width: 40px !important;
            height: auto;
        }

        .card {
            box-shadow: 0 1px 15px 1px rgba(52, 40, 104, .08);
        }

        .ui-product-color {
            display: inline-block;
            overflow: hidden;
            margin: .144em;
            width: .875rem;
            height: .875rem;
            border-radius: 10rem;
            -webkit-box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.15) inset;
            box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.15) inset;
            vertical-align: middle;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#select-all").click(function () {
                $('input[name="id"]:checkbox').not(this).prop('checked', this.checked);
            });
        });

    </script>
</head>
<body>
<div class="container px-3 my-5 clearfix">
    <!-- Shopping cart table -->
    <div class="card">
        <div class="card-header">
            <h2>Shopping Cart</h2>
        </div>
        <c:set var="cartSession" value="${sessionScope.cart}"/>
        <c:choose>
            <c:when test="${ cartSession != null && !cartSession.isEmpty() }">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered m-0">
                            <thead>
                            <tr>
                                <!-- Set columns width -->
                                <th class="text-center py-3 px-4" style="min-width: 400px;">Product Name &amp; Details
                                </th>
                                <th class="text-right py-3 px-4" style="width: 100px;">Price</th>
                                <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
                                <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
                                <th class="text-center align-middle py-3 px-0" style="width: 40px;">
                                    <input type="checkbox" id="select-all"/>
                                </th>
                            </tr>
                            </thead>
                            <c:forEach var="item" items="${cartSession}">
                                <c:set var="product" value="${item.value.product}"/>
                                <tr>
                                    <td class="p-4">
                                        <div class="media align-items-center">
                                            <img class="d-block ui-w-40 ui-bordered mr-4"
                                                 src="<c:url value="/assets/${product.image}"/>" alt="${product.image}">
                                            <div class="media-body">
                                                <a href="/product?id=${product.productId}" class="d-block text-dark">${product.name}</a>
                                                <small>
                                                    <span class="text-muted"><b>Author:</b> ${product.author}</span>
                                                    <br>
                                                    <span class="text-muted"><b>Chapter:</b> ${product.numberChapter}</span>
                                                    <br>
                                                    <span class="text-muted"><b>Category:</b> ${product.categoryId}</span>
                                                </small>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">
                                            ${product.price}
                                    </td>
                                    <td class="align-middle p-4">
                                        <input type="hidden" form="formEdit" name="id" value="${item.key}">
                                        <input type="number" form="formEdit" class="form-control text-center"
                                               min="1" max="100" name="quantity" value="${item.value.quantity}">
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">
                                        <fmt:formatNumber type="currency"
                                                          currencySymbol="₫"
                                                          maxFractionDigits="0"
                                                          value="${ item.value.total }"/>
                                    </td>
                                    <td class="text-center align-middle px-0">
                                        <c:url var="deleteItem" value="/cart/delete">
                                            <c:param name="id" value="${item.key}"/>
                                        </c:url>
                                        <input type="checkbox" class="form-control text-center" name="id"
                                               value="${item.key}" form="formDelete">
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4" align="right">
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
                            </tbody>
                        </table>
                    </div>
                    <!-- / Shopping cart table -->

                    <div class="align-items-center pb-4">
                        <div class="">
                            <div class="text-right mt-4">
                                <label class="text-muted font-weight-normal m-0">Total price</label>
                                <div class="text-large">
                                    <strong>
                                        <c:set var="totalPrice" value="0"/>
                                        <c:forEach var="item" items="${cartSession.values()}">
                                            <c:set var="totalPrice" value="${totalPrice + item.total}"/>
                                        </c:forEach>
                                        <fmt:formatNumber type="currency"
                                                          currencySymbol="₫"
                                                          maxFractionDigits="0"
                                                          value="${totalPrice}"/>
                                    </strong>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="float-right">
                        <a href="/home" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Back to shopping</a>
                        <a href="/user/checkout" class="btn btn-lg btn-primary mt-2">Checkout</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="float-right">
                    <a href="/home" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Back to shopping</a>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

</body>
</html>
