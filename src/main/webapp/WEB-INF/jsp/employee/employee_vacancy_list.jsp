<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/20/2021
  Time: 5:31 PM
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

    <title><fmt:message key="employee_vacancy_list.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancies" scope="request" value="${vacancies}"/>

    <div class="row align-items-start" style="margin-top: 3%">
        <div class="col-2 offset-3" style="display: flex; justify-content: center">
            <a class="btn btn-secondary" href="to_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeAllVacancies"/> </a>
        </div>
        <div class="col-2" style="display: flex; justify-content: center">
            <a class="btn btn-secondary" href="see_active_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeActiveVacancies"/> </a>
        </div>
        <div class="col-2" style="display: flex; justify-content: center">
            <a class="btn btn-secondary" href="see_deleted_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeDeletedVacancies"/> </a>
        </div>
    </div>
    <div class="mt-3">
        <wrong-message>
            ${noVacancies}
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
        <c:forEach var="vacancy" items="${vacancies}">
        <tr class="table-secondary">
            <th scope="row">${vacancy.position}</th>
            <th scope="row">${vacancy.country}</th>
            <th scope="row">${vacancy.city}</th>
            <th scope="row">${vacancy.creationDate}</th>
            <th scope="row"><a href="<c:url value="vacancy_info.do?vacancyId=${vacancy.id}"/>"><fmt:message
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
