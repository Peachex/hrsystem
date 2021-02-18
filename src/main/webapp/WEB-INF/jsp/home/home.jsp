<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

    <title><fmt:message key="home.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="header.jsp" %>
    <div id="carouselExampleInterval" class="carousel carousel-dark slide offset-5"
         style="width: 20%;margin-top: 15%"
         data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="4000">
                <img src="${pageContext.request.contextPath}/img/headhunting.png" class="d-block w-100" alt="...">
                <h1><fmt:message key="home.firstSlideName"/></h1>
                <h3><fmt:message key="home.seeVacanciesMessage"/></h3>
            </div>
            <div class="carousel-item" data-bs-interval="4000">
                <img src="${pageContext.request.contextPath}/img/portfolio.png" class="d-block w-100" alt="...">
                <h1><fmt:message key="home.secondSlideName"/></h1>
                <h3><fmt:message key="home.registerAndApplyVacanciesMessage"/></h3>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/img/chair.png" class="d-block w-100" alt="...">
                <h1><fmt:message key="home.thirdSlideName"/></h1>
                <h3><fmt:message key="home.getJobMessage"/></h3>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </a>
    </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
