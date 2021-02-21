<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/21/2021
  Time: 7:44 PM
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

    <title><fmt:message key="vacancy_info.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancy" scope="request" value="${vacancy}"/>

    <div class="card text-dark bg-light offset-3" style="margin-top: 3%;max-width: 50%;">
        <div class="align-items-start">
            <div class="card-header" style="display: flex"><h4><fmt:message
                    key="vacancy_info.title"/> #${vacancy.id} <c:if test="${!vacancy.isAvailable}"><fmt:message
                    key="vacancy_deleted"/></c:if></h4>
                <div class="col-1 offset-6" style="display: flex;justify-content: center">
                    <div class="dropdown">
                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" id="actionDropDown"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <fmt:message key="button.action"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="actionDropDown">
                            <li><a class="dropdown-item"
                                   href="#edit"><fmt:message
                                    key="button.edit"/> </a></li>
                            <c:if test="${vacancy.isAvailable}">
                                <li><a class="dropdown-item"
                                       href="delete_vacancy.do?vacancyId=${vacancy.id}"><fmt:message
                                        key="button.delete"/> </a></li>
                            </c:if>
                            <c:if test="${!vacancy.isAvailable}">
                                <li><a class="dropdown-item"
                                       href="restore_vacancy.do?vacancyId=${vacancy.id}"><fmt:message
                                        key="button.restore"/> </a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h3 class="card-title"><fmt:message key="vacancy_position"/></h3>
                <p class="card-text">${vacancy.position}</p>
            </div>
            <div class="card-body">
                <h3 class="card-title"><fmt:message key="vacancy_description"/></h3>
                <p class="card-text">${vacancy.description}</p>
            </div>
            <div class="card-body">
                <h3 class="card-title"><fmt:message key="vacancy_country"/></h3>
                <p class="card-text">${vacancy.country}</p>
            </div>
            <div class="card-body">
                <h3 class="card-title"><fmt:message key="vacancy_city"/></h3>
                <p class="card-text">${vacancy.city}</p>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
