<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 08/11/2021
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Register</title>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;margin-top: 50px;">Register</h2>
    <form action="/guest/register" style="margin: 0 auto;width: 50%;" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <div class="form-group">
            <label for="re-pwd">Re-Password:</label>
            <input type="password" class="form-control" id="re-pwd" name="rePassword">
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <c:if test="${message != null}">
            <p class="bg-danger text-white">${message}</p>
        </c:if>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
