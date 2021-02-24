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

    <title><fmt:message key="applicant_request.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>

    <div class="mt-5">
        <wrong-message>
            ${noApplicantRequests}
        </wrong-message>
    </div>

    <vacancies-number><fmt:message
            key="applicant_request.requestsAmount"/> ${applicantRequests.size()}</vacancies-number>

    <table class="table table-dark table-bordered border-secondary mt-5 offset-2" style="width: 67%">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="vacancy_position"/></th>
            <th scope="col"><fmt:message key="vacancy_country"/></th>
            <th scope="col"><fmt:message key="vacancy_city"/>
            <th scope="col"><fmt:message key="vacancy_creationDate"/></th>
            <th scope="col"><fmt:message key="applicant_request.applicantState"/></th>
            <th scope="col"><fmt:message key="table_action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="applicantRequest" items="${applicantRequests}">
        <tr class="table-secondary">
            <th scope="row">${applicantRequest.vacancy.position}</th>
            <th scope="row">${applicantRequest.vacancy.country}</th>
            <th scope="row">${applicantRequest.vacancy.city}</th>
            <th scope="row">${applicantRequest.vacancy.creationDate}</th>
            <th scope="row">${applicantRequest.applicantState}</th>
            <th scope="row"><a href="<c:url value="to_vacancy_info.do?vacancyId=${applicantRequest.vacancy.id}"/>"><fmt:message
                    key="link.moreInfo"/></a></th>
        <tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="col-4 offset-4" style="display: flex;justify-content: center; margin-top: 5%">
        <a class="btn btn-outline-secondary col-8 mb-4"
           style="display: flex;justify-content: center"
           href="home" role="button"><fmt:message
                key="button.home"/> </a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
