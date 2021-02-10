<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="nav-link header-link" href="home">Home</a>
            <a class="nav-link header-link offset-1" href="vacancy">Vacancy</a>
            <a class="nav-link header-link offset-1" href="login">Login</a>
            <a class="nav-link header-link offset-1" href="register">Register</a>
        </nav>
    </div>
</div>
</body>
</html>
