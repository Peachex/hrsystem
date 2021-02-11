<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

<html>
<head>
    <title>Header</title>
</head>
<body>
<div class="container">
    <div class="row header">
        <nav class="nav">
            <c:set var="role" scope="session" value="${currentRole}"/>
            <c:set var="guest" scope="session" value="GUEST"/>
            <c:set var="admin" scope="session" value="ADMIN"/>
            <c:set var="applicant" scope="session" value="APPLICANT"/>
            <c:set var="employee" scope="session" value="EMPLOYEE"/>

            <c:if test="${role.toString().equals(guest)}">
                <a class="nav-link header-link" href="home">Home</a>
                <a class="nav-link header-link offset-1" href="vacancy">Vacancy</a>
                <a class="nav-link header-link offset-1" href="register">Register</a>
                <a class="nav-link header-link offset-1" href="login">Login</a>
            </c:if>

            <c:if test="${role.toString().equals(applicant) || role.toString().equals(employee)}">
                <a class="nav-link header-link" href="home">Home</a>
                <a class="nav-link header-link offset-1" href="vacancy">Vacancy</a>
                <a class="nav-link header-link offset-1" href="register">Profile</a>
                <a class="nav-link header-link offset-1" href="login">Logout</a>
                User Name
            </c:if>

            <c:if test="${role.toString().equals(admin)}">
                <a class="nav-link header-link" href="home">Home</a>
                <a class="nav-link header-link offset-1" href="vacancy">Vacancy</a>
                <a class="nav-link header-link offset-1" href="register">Profile</a>
                <a class="nav-link header-link offset-1" href="register">Admin</a>
                <a class="nav-link header-link offset-1" href="login">Logout</a>
                User Name
            </c:if>

        </nav>
    </div>
</div>
</body>
</html>
