<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Page Title - SB Admin</title>
    <link href="<c:url value="/assets/admin/assets/css/styles.css"/>" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header">
                                <h3 class="text-center font-weight-light my-4">Create Account Admin</h3>
                            </div>
                            <div class="card-body">
                                <form action="/admin/register" method="post">
                                    <small>Roles</small>
                                    <div class="form-row">
                                        <c:forEach var="item" items="${roles}">
                                            <div class="col-md-2">
                                                <div class="form-check">
                                                    <label class="small mb-1 form-check-label">
                                                        <input type="checkbox" name="role" class="form-check-input"
                                                               value="${item}">
                                                            ${item.name().toLowerCase()}
                                                    </label>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="inputEmailAddress">Username</label>
                                        <input class="form-control py-4" id="inputEmailAddress" type="text"
                                               aria-describedby="emailHelp" placeholder="Enter username"
                                               name="username"/>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputPassword">Password</label>
                                                <input class="form-control py-4" id="inputPassword" type="password"
                                                       placeholder="Enter password" name="password"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputConfirmPassword">Confirm
                                                    Password</label>
                                                <input class="form-control py-4" id="inputConfirmPassword"
                                                       type="password" placeholder="Confirm password"
                                                       name="rePassword"/>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${message != null}">
                                        <small class="text-danger">${message}</small>
                                    </c:if>
                                    <div class="form-group mt-4 mb-0">
                                        <button class="btn btn-primary btn-block">Create Account</button>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer text-center">
                                <div class="small"><a href="/admin/login">Have an account? Go to login</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2020</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="<c:url value="/assets/admin/assets/js/scripts.js"/>"></script>
</body>
</html>
