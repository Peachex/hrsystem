<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/13/2021
  Time: 10:44 AM
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
        <div class="card-header"><h3><fmt:message key="vacancy_info.title"/> #${vacancy.id}</h3></div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="vacancy_position"/></h4>
            <p class="card-text">${vacancy.position}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="vacancy_description"/></h4>
            <p class="card-text">${vacancy.description}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="vacancy_country"/></h4>
            <p class="card-text">${vacancy.country}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="vacancy_city"/></h4>
            <p class="card-text">${vacancy.city}</p>
        </div>
        <div class="col-4 offset-4" style="display: flex;justify-content: center;">
            <a class="btn btn-outline-secondary col-8 mb-4"
               style="display: flex;justify-content: center"
               href="to_vacancies.do" role="button"><fmt:message
                    key="button.back"/> </a>
        </div>
    </div>

    <c:if test="${role.toString().equals(guest)}">
        <a class="btn btn-outline-success col-2 offset-5" href="login" role="button" style="margin-top: 2%;
    margin-bottom: 2%"><fmt:message
                key="button.createApplicantRequest"/> </a>
    </c:if>

    <c:if test="${role.toString().equals(applicant)}">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary col-2 offset-5" style="margin-top: 2%; margin-bottom: 2%;"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal">
            <fmt:message key="button.createApplicantRequest"/>
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                key="applicant_request.request"/></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form name="create_applicant_request" method="POST" action="create_applicant_request.do">
                            <input type="hidden" name="vacancyId" value="${vacancy.id}">
                            <label for="inputSummary"><fmt:message
                                    key="create_applicant_request.inputSummary"/> </label>
                            <div class="form-group mt-1">
                                    <textarea class="form-control" rows="5" id="inputSummary"
                                              name="summary" placeholder="<fmt:message
                                             key="create_applicant_request.inputSummaryPlaceholder"/>"
                                              required minlength="3" maxlength="10000"></textarea>
                            </div>

                            <div class="col-4">
                                <button class="btn btn-outline-success button mt-4" style="margin-left: 100%"
                                        type="submit">
                                    <fmt:message
                                            key="button.createApplicantRequest"/></button>
                            </div>
                            <input name="ctoken" type="hidden" value="${stoken}"/>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="button.close"/></button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <wrong-message>
        ${errorApplicantRequestCreation}
    </wrong-message>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
