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
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title><fmt:message key="vacancy.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancies" scope="session" value="${vacancies}"/>

    <div class="row align-items-start" style="margin-top: 3%; width:100%; padding-left: 7.8%;">
        <div class="col-2 offset-2" style="display: flex;justify-content: center">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/to_vacancies.do" role="button"><fmt:message
                    key="button.seeAllVacancies"/> </a>
        </div>
        <div class="col-3">
            <form name="find-vacancy-from" method="GET" action="find_vacancies_by_key_word.do">
                <div class="d-flex">
                    <input class="form-control me-2" type="search"
                           placeholder="<fmt:message key="vacancy.inputKeyWord"/>"
                           aria-label="Search"
                           value="${keyWord}" name="keyWord">
                    <button class="btn btn-success" type="submit"><fmt:message
                            key="button.find"/></button>
                </div>
            </form>
        </div>
        <div class="col-2" style="display: flex;justify-content: center">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropDown"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <fmt:message key="button.sortByDate"/>
                </button>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="sortDropDown">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/sort_vacancies_by_date.do?sortSequence=desc"><fmt:message
                            key="button.sortDesc"/> </a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/sort_vacancies_by_date.do?sortSequence=asc"><fmt:message
                            key="button.sortAsc"/> </a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="mt-3">
        <wrong-message>
            ${noVacancies}
            ${noVacancy}
            ${errorInputData}
        </wrong-message>
    </div>

    <div class="mt-3">
        <vacancies-number><fmt:message key="vacancy_vacanciesAmount"/> ${vacancies.size()}</vacancies-number>
    </div>

    <table class="table table-dark table-bordered border-secondary mt-4 offset-2" style="width: 70%">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="vacancy_position"/></th>
            <th scope="col"><fmt:message key="vacancy_country"/></th>
            <th scope="col"><fmt:message key="vacancy_city"/></th>
            <th scope="col"><fmt:message key="vacancy_creationDate"/></th>
            <th scope="col"><fmt:message key="table_action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vac" items="${vacancies}">
        <tr class="table-secondary">
            <th scope="row"><ctg:text text="${vac.position}"/></th>
            <th scope="row"><ctg:text text="${vac.country}"/></th>
            <th scope="row"><ctg:text text="${vac.city}"/></th>
            <th scope="row"><ctg:text text="${vac.creationDate}"/></th>
            <th scope="row"><a href="<c:url value="to_vacancy_info.do?vacancyId=${vac.id}"/>"><fmt:message
                    key="link.moreInfo"/></a></th>
        <tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
