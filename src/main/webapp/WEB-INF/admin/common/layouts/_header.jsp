<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">

    <a class="navbar-brand" href="index.jsp">Start Bootstrap</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">
            <%--            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search"--%>
            <%--                   aria-describedby="basic-addon2"/>--%>
            <%--            <div class="input-group-append">--%>
            <%--                <button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>--%>
            <%--            </div>--%>
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">Settings</a>
                <c:set var="access" value="false"/>
                <c:forEach var="item" items="${admin.role}">
                    <c:if test="${item.name().equals('FULL') || item.name().equals('CREATE')}">
                        <c:set var="access" value="true"/>
                    </c:if>
                </c:forEach>
                <c:if test="${access == true}">
                    <a class="dropdown-item" href="/admin/register">Register</a>
                </c:if>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/admin/logout">Logout</a>
            </div>
        </li>
    </ul>
</nav>