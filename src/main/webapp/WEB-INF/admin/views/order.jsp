<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Order</title>
    <c:import url="/WEB-INF/admin/common/css.jsp"/>
</head>
<body class="sb-nav-fixed">
<%--header--%>
<c:import url="/WEB-INF/admin/common/layouts/_header.jsp"/>
<div id="layoutSidenav">
    <c:import url="/WEB-INF/admin/common/layouts/_nav.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <h1 class="mt-4">Order</h1>
                <c:if test="${message != null}">
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active text-danger">${message}</li>
                    </ol>
                </c:if>
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Order id</th>
                                    <th>Customer id</th>
                                    <th>Create date</th>
                                    <th>status</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Order id</th>
                                    <th>CustomerId</th>
                                    <th>Create date</th>
                                    <th>status</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="item" items="${orders}">
                                    <c:set var="totalPriceOrder" value="0"/>
                                    <tr>
                                        <td>
                                            <a data-toggle="modal" href="#order${item.orderId}">
                                                    ${item.orderId}
                                            </a>
                                        </td>
                                        <td>${item.customerId}</td>
                                        <td>${item.createDate}</td>
                                        <td>
                                            <c:if test="${item.status}">
                                                Đã xác nhận
                                            </c:if>
                                            <c:if test="${item.status == false}">
                                                Chờ xác nhận
                                            </c:if>
                                        </td>
                                        <td>
                                            <form action="<c:url value="/admin/order/delete"/>" method="post">
                                                <input name="orderId" type="hidden" value="${item.orderId}">
                                                <button>delete</button>
                                            </form>
                                            <c:if test="${item.status == false}">
                                                <form action="<c:url value="/admin/order/update"/>" method="post">
                                                    <input name="id" type="hidden" value="${item.orderId}">
                                                    <button>update status</button>
                                                </form>
                                            </c:if>

                                        </td>
                                    </tr>
                                    <!-- The Modal -->
                                    <div class="modal fade" id="order${item.orderId}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <!-- Modal Header -->
                                                <div class="modal-header">
                                                    <h4 class="modal-title">Order details</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;
                                                    </button>
                                                </div>
                                                <!-- Modal body -->
                                                <div class="modal-body">
                                                    <c:forEach var="detail" items="${item.orderDetailDtoList}">
                                                        <c:set var="product" value="${detail.productDto}"/>
                                                        <div class="media align-items-center">
                                                            <img class="d-block ui-w-40 ui-bordered mr-4"
                                                                 src="<c:url value="/assets/user/${product.image}"/>"
                                                                 alt="${product.image}" width="60">
                                                            <div class="media-body">
                                                                <a href="<c:url value="/product?id=${product.productId}"/>"
                                                                   class="d-block text-dark">${product.name}</a>
                                                                <small>
                                                                    <span class="text-muted" style="font-size: 14px">
                                                                        <b>x</b>${detail.quantity}
                                                                    </span>
                                                                    <br>
                                                                </small>
                                                            </div>
                                                        </div>
                                                        <div class="mb-5">
                                                            <b>Price</b> ${product.price}
                                                            <c:set var="totalPriceOrder"
                                                                   value="${detail.quantity * product.price + totalPriceOrder}"/>
                                                            <b>Total</b>
                                                            <fmt:formatNumber type="currency"
                                                                              currencySymbol="₫"
                                                                              maxFractionDigits="0"
                                                                              value="${detail.quantity * product.price}"/>
                                                        </div>
                                                    </c:forEach>
                                                    <div class="float-right">
                                                        <p class="mt-2">Total price:
                                                            <b class="text-danger" style='font-size:20px;'>
                                                                <fmt:formatNumber type="currency"
                                                                                  currencySymbol="₫"
                                                                                  maxFractionDigits="0"
                                                                                  value="${totalPriceOrder}"/>
                                                            </b>
                                                        </p>
                                                    </div>
                                                </div>
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                                                        Close
                                                    </button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="/WEB-INF/admin/common/layouts/_footer.jsp"/>
    </div>
</div>
<c:import url="/WEB-INF/admin/common/js.jsp"/>
</body>
</html>
