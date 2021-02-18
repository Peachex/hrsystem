<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 7:28 PM
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

    <title><fmt:message key="vacancy.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancies" scope="application" value="${vacancies}"/>

    <form name="find-vacancy-from" method="GET" action="find_vacancies_by_key_word.do">
        <div class="d-flex col-2 offset-5">
            <input class="form-control me-2" type="search" placeholder="<fmt:message key="button.find"/>"
                   aria-label="Search"
                   value="${keyWord}" name="keyWord">
            <button class="btn btn-outline-success" type="submit"><fmt:message
                    key="button.find"/></button>
        </div>
    </form>

    <div class="dropdown col-2 offset-2">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="sortDropDown">
            <fmt:message key="button.sortByDate"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="sortDropDown">
            <a class="dropdown-item" href="sort_vacancies_by_date.do?sortSequence=asc"><fmt:message
                    key="button.sortAsc"/> </a>
            <a class="dropdown-item" href="sort_vacancies_by_date.do?sortSequence=desc"><fmt:message
                    key="button.sortDesc"/> </a>
        </div>
    </div>


    ${noVacancies}
    <fmt:message key="vacancy_vacanciesAmount"/> ${vacancies.size()}
    <c:forEach var="vacancy" items="${vacancies}">
        <tr>
            <a href="<c:url value="vacancy_info.do?vacancyId=${vacancy.id}"/>"
               class="list-group-item list-group-item-action">${vacancy.position}
                    ${vacancy.creationDate}</a>
            <br>
        </tr>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>


