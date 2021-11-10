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

        .card {
            box-shadow: 0 1px 15px 1px rgba(52, 40, 104, .08);
        }

    </style>
</head>
<body>
<div class="container px-3 my-5 clearfix">
    <!-- Order table -->
    <div id="accordion">
        <div class="card">
            <div class="card-header">
                <h2>Order</h2>
            </div>

            <div class="card-body">
                <c:forEach var="item" items="${orders}">
                    <table>
                        <tr>
                            <th>Id</th>
                            <th>Create date</th>
                            <th>Total price</th>
                            <th>Status</th>
                        </tr>
                        <tr>
                            <td>
                                <a data-toggle="collapse" href="#order${}" class="collapsed card-link">
                                        ${}
                                </a>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                    <%--                    <p>${item.order.createDate} - status: ${item.order.status}</p>--%>
                    <div id="order${}" class="collapse table-responsive" data-parent="#accordion"
                         style="margin-bottom: 20px;">
                        <table class="table table-bordered m-0">
                            <thead>
                            <tr>
                                <!-- Set columns width -->
                                <th class="text-center py-3 px-4" style="min-width: 400px;">Info</th>
                                <th class="text-center py-3 px-4" style="min-width: 400px;">Price</th>
                                <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
                            </tr>
                            </thead>
                            <c:forEach var="detail" items="${item.orderDetails}">
                                <tr>
                                    <td class="p-4">
                                        <div class="media align-items-center">
                                            <img class="d-block ui-w-40 ui-bordered mr-4"
                                                 src="<c:url value="/assets/${product.image}"/>" alt="${product.image}">
                                            <div class="media-body">
                                                <a href="#" class="d-block text-dark">${product.name}</a>
                                                <small>
                                                    <span class="text-muted"><b>x</b> ${soluong}</span>
                                                    <br>
                                                </small>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">
                                            ${detail.productId}
                                    </td>
                                    <td class="text-center align-middle px-0">
                                            ${detail.quantity}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="float-right">
                            <p class="mt-2">Total price:
                                <b class="text-danger" style='font-size:27px;'>
                                    <fmt:formatNumber type="currency"
                                                      currencySymbol="₫"
                                                      maxFractionDigits="0"
                                                      value="${product.price}"/>
                                </b>
                            </p>
                        </div>
                    </div>
                </c:forEach>

                <div class="float-right">
                    <a href="/home" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3">Back to shopping</a>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
