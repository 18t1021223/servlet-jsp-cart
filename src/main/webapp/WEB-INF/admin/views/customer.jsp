<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Customer</title>
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
                <h1 class="mt-4">Customer</h1>
                <c:if test="${message != null}">
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active text-danger">${message}</li>
                    </ol>
                </c:if>
                <form method="post"
                        <c:if test="${customer == null}">
                            action="/admin/customer/add"
                        </c:if>
                        <c:if test="${customer != null}">
                            action="/admin/customer/update"
                        </c:if>
                >
                    <div class="row">
                        <div class="col-lg-4 mb-3">
                            <label for="name" class="mr-sm-2">Name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"
                                   required
                            <c:if test="${customer != null}">
                                   value="${customer.name}"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-2 mb-3">
                            <label for="username" class="mr-sm-2">Username:</label>
                            <input id="username" class="form-control" placeholder="Enter username"
                                   name="username"
                                   required
                            <c:if test="${customer != null}">
                                   value="${customer.username}"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-4 mb-3">
                            <label for="Price" class="mr-sm-2">Password:</label>
                            <input type="text" id="Price" class="form-control" placeholder="password" name="password"
                                   required
                            <c:if test="${customer != null}">
                                   value="${customer.password}"
                            </c:if>
                            >
                        </div>
                    </div>
                    <div class="row mb-4">
                        <c:if test="${customer != null}">
                            <input type="hidden" name="customerId" value="${customer.customerId}">
                        </c:if>
                        <div class="col-lg-2">
                            <button type="submit" class="btn btn-primary mb-2">Save</button>
                        </div>
                    </div>
                </form>
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="item" items="${customers}">
                                    <tr>
                                        <td>${item.customerId}</td>
                                        <td>${item.name}</td>
                                        <td>${item.username}</td>
                                        <td>${item.password}</td>
                                        <td>
                                            <form action="/admin/customer/delete" method="post">
                                                <input name="id" type="hidden" value="${item.customerId}">
                                                <button>delete</button>
                                            </form>
                                            <a href="<c:url value="/admin/customer/update?id=${item.customerId}"/>">
                                                update
                                            </a>
                                        </td>
                                    </tr>
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
