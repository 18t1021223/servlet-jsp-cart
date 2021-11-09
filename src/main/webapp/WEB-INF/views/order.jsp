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
    <!-- Shopping cart table -->
    <div class="card">
        <div class="card-header">
            <h2>Order</h2>
        </div>

        <div class="card-body">
            <c:forEach var="item" items="${orders}">
                <p>${item.order.createDate} - status: ${item.order.status}</p>
                <div class="table-responsive" style="margin-bottom: 20px;">
                    <table class="table table-bordered m-0">
                        <thead>
                        <tr>
                            <!-- Set columns width -->
                            <th class="text-center py-3 px-4" style="min-width: 400px;">Product id</th>
                            <th class="text-right py-3 px-4" style="width: 100px;">Quantity</th>
                        </tr>
                        </thead>
                        <c:forEach var="detail" items="${item.orderDetails}">
                            <tr>
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
                </div>
            </c:forEach>

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
            </div>
        </div>
    </div>
</div>

</body>
</html>
