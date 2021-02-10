<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 4:15 PM
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
    <title>Home</title>
</head>
<body>
<div class="main-container">
    <%@ include file="header.jsp" %>
    <br>
    <img src="${pageContext.request.contextPath}/img/portfolio.png" width="128" height="128" class="row offset-6">
    <br>
    <img src="${pageContext.request.contextPath}/img/headhunting.png" width="128" height="128" class="row offset-6">
    <br>
    <img src="${pageContext.request.contextPath}/img/chair.png" width="128" height="128" class="row offset-6">
</div>
</body>
</html>
