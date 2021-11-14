<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Product</title>
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
                <h1 class="mt-4">Product</h1>
                <c:if test="${message != null}">
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active text-danger">${message}</li>
                    </ol>
                </c:if>
                <form method="post"
                        <c:if test="${product == null}">
                            action="/admin/product/add"
                        </c:if>
                        <c:if test="${product != null}">
                            action="/admin/product/update"
                        </c:if>
                      enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4 mb-3">
                            <label for="name" class="mr-sm-2">Name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"
                                   required
                            <c:if test="${product != null}">
                                   value="${product.name}"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-2 mb-3">
                            <label for="quantity" class="mr-sm-2">Quantity:</label>
                            <input type="number" id="quantity" class="form-control" placeholder="Enter quantity"
                                   name="quantity"
                                   min="0"
                                   required
                            <c:if test="${product != null}">
                                   value="${product.quantity}"
                            </c:if>
                            <c:if test="${product == null}">
                                   value="1"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-2 mb-3">
                            <label for="Price" class="mr-sm-2">Price:</label>
                            <input type="number" id="Price" class="form-control" placeholder="Price" name="price"
                                   required min="1"
                            <c:if test="${product != null}">
                                   value="${product.price}"
                            </c:if>
                            <c:if test="${product == null}">
                                   value="1"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-2 mb-3">
                            <label for="Chapter" class="mr-sm-2">Chapter:</label>
                            <input type="number" id="Chapter" class="form-control" placeholder="Chapter"
                                   name="numberChapter"
                                   required
                                   min="1"
                            <c:if test="${product != null}">
                                   value="${product.numberChapter}"
                            </c:if>
                            <c:if test="${product == null}">
                                   value="1"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-4 mb-3">
                            <label for="Author" class="mr-sm-2">Chapter:</label>
                            <input type="text" id="Author" class="form-control" placeholder="Author" name="author"
                            <c:if test="${product != null}">
                                   value="${product.author}"
                            </c:if>
                            >
                        </div>

                        <div class="col-lg-2 mb-3">
                            <label for="category">Category:</label>
                            <select class="form-control" name="categoryId" id="category" required>
                                <c:forEach var="item" items="${categories}">
                                    <option value="${item.categoryId}"
                                            <c:if test="${product != null && product.categoryId == item.categoryId}">
                                                selected
                                            </c:if>
                                    >
                                            ${item.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-lg-3 mb-3">
                            <label for="file" class="mr-sm-2">Image:</label>
                            <input type="file" id="file" class="form-control" placeholder="File" name="image" required>
                        </div>
                        <div class="col-lg-3 mb-3">
                            <c:if test="${product != null}">
                                <img src="<c:url value='/assets/user/${product.image}'/>" width="50">
                            </c:if>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <c:if test="${product != null}">
                            <input type="hidden" name="productId" value="${product.productId}">
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
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Chapter</th>
                                    <th>Author</th>
                                    <th>Create date</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Chapter</th>
                                    <th>Author</th>
                                    <th>Create date</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="item" items="${products}">
                                    <tr>
                                        <td>${item.productId}</td>
                                        <td>
                                            <img src="/assets/user/${item.image}" width="50">
                                                ${item.name}
                                        </td>
                                        <td>${item.quantity}</td>
                                        <td>${item.price}</td>
                                        <td>${item.numberChapter}</td>
                                        <td>${item.author}</td>
                                        <td>${item.createDate}</td>
                                        <td>
                                            <form action="/admin/product/delete" method="post">
                                                <input name="id" type="hidden" value="${item.productId}">
                                                <button>delete</button>
                                            </form>
                                            <a href="<c:url value="/admin/product/update?id=${item.productId}"/>">
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
