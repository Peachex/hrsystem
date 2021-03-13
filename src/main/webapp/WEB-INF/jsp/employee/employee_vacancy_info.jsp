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
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title><fmt:message key="vacancy_info.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancy" scope="request" value="${vacancy}"/>

    <div class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="btn btn-secondary col-2 offset-5 mb-1 collapsed" style="margin-top: 3%"
                        type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseOne" aria-expanded="false"
                        aria-controls="flush-collapseOne">
                    <fmt:message key="vacancy_info.title"/>
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse"
                 aria-labelledby="flush-headingOne"
                 data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                    <div class="card text-dark bg-light offset-3" style="margin-top: 1%;max-width: 50%;">
                        <div class="card-header" style="display: flex"><h3><fmt:message
                                key="vacancy_info.title"/> #${vacancy.id} <c:if
                                test="${!vacancy.isAvailable}"><fmt:message
                                key="vacancy_deleted"/></c:if></h3>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="vacancy_position"/></h4>
                            <p class="card-text"><ctg:text text="${vacancy.position}"/></p>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="vacancy_description"/></h4>
                            <p class="card-text"><ctg:text text="${vacancy.description}"/></p>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="vacancy_country"/></h4>
                            <p class="card-text"><ctg:text text="${vacancy.country}"/></p>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="vacancy_city"/></h4>
                            <p class="card-text"><ctg:text text="${vacancy.city}"/></p>
                        </div>
                        <div class="col-4 offset-4" style="display: flex;justify-content: center;">
                            <div class="dropdown col-8 mb-4">
                                <button class="btn btn-outline-secondary dropdown-toggle button mt-3" type="button"
                                        id="actionDropDown"
                                        data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                    <fmt:message key="button.action"/>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" style="width: 100%"
                                    aria-labelledby="actionDropDown">
                                    <li>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-secondary dropdown-item"
                                                data-bs-toggle="modal" data-bs-target="#editVacancyModal">
                                            <fmt:message key="button.edit"/>
                                        </button>
                                    </li>
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
                                <!-- Modal -->
                                <div class="modal fade" id="editVacancyModal" data-bs-backdrop="static"
                                     data-bs-keyboard="false" tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title"><fmt:message
                                                        key="create_vacancy_modal.title"/></h4>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>

                                            <div class="modal-body">
                                                <form name="edit-vacancy-form" method="POST"
                                                      action="edit_vacancy_info.do?vacancyId=${vacancy.id}">
                                                    <label for="inputPosition"><fmt:message
                                                            key="create_vacancy_modal.inputPosition"/> </label>
                                                    <div class="form-group mt-1">
                                                        <input type="text" class="form-control field" id="inputPosition"
                                                               name="position"
                                                               value="${vacancy.position}" placeholder=
                                                                       "<fmt:message key="create_vacancy_modal.inputPositionPlaceholder"/>"
                                                               required pattern="[А-Яа-я\w\s\p{Punct}]{3,1000}">
                                                    </div>

                                                    <div class="mt-3">
                                                        <label for="inputDescription"><fmt:message
                                                                key="create_vacancy_modal.inputDescription"/> </label>
                                                    </div>
                                                    <div class="form-group mt-1">
                                    <textarea class="form-control" rows="5" id="inputDescription"
                                              name="description" placeholder="<fmt:message
                                             key="create_vacancy_modal.inputDescriptionPlaceholder"/>"
                                              required minlength="3" maxlength="10000">${vacancy.description}</textarea>
                                                    </div>

                                                    <div class="mt-3">
                                                        <label for="inputCountry"><fmt:message
                                                                key="create_vacancy_modal.inputCountry"/> </label>
                                                    </div>
                                                    <div class="form-group mt-1">
                                                        <input type="text" class="form-control field" id="inputCountry"
                                                               name="country"
                                                               value="${vacancy.country}" placeholder=
                                                                       "<fmt:message key="create_vacancy_modal.inputCountryPlaceholder"/>"
                                                               required pattern="[a-zA-Zа-яА-я\s]{3,50}">
                                                    </div>

                                                    <div class="mt-3">
                                                        <label for="inputCity"><fmt:message
                                                                key="create_vacancy_modal.inputCity"/> </label>
                                                    </div>
                                                    <div class="form-group mt-1">
                                                        <input type="text" class="form-control field" id="inputCity"
                                                               name="city"
                                                               value="${vacancy.city}" placeholder=
                                                                       "<fmt:message key="create_vacancy_modal.inputCityPlaceholder"/>"
                                                               required pattern="[a-zA-Zа-яА-я\s]{2,50}">
                                                    </div>

                                                    <div class="col-4">
                                                        <button class="btn btn-outline-success button mt-4"
                                                                style="margin-left: 100%" type="submit">
                                                            <fmt:message
                                                                    key="button.save"/></button>
                                                    </div>
                                                </form>
                                            </div>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-danger"
                                                        data-bs-dismiss="modal">
                                                    <fmt:message
                                                            key="button.close"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <a class="btn btn-secondary col-2 offset-5" style="margin-top: 1%" href="to_employee_vacancies.do"
       role="button"><fmt:message
            key="button.back"/></a>

    <div class="mt-3">
        <wrong-message>
            ${errorVacancyUpdate}
            ${noApplicantRequests}
        </wrong-message>
    </div>

    <div class="mt-3">
        <vacancies-number><fmt:message
                key="applicant_request.requestsAmount"/> ${applicantRequests.size()}</vacancies-number>
    </div>

    <table class="table table-dark table-bordered border-secondary mt-5 offset-2 mb-5" style="width: 67%">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="profile.firstName"/></th>
            <th scope="col"><fmt:message key="profile.lastName"/></th>
            <th scope="col"><fmt:message key="profile.email"/></th>
            <th scope="col"><fmt:message key="applicant_request.applicantState"/></th>
            <th scope="col"><fmt:message key="table_action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="applicantRequest" items="${applicantRequests}">
        <tr class="table-secondary">
            <th scope="row"><ctg:text
                    text="${applicantRequest.applicant.firstName}"/></th>
            <th scope="row"><ctg:text
                    text="${applicantRequest.applicant.lastName}"/></th>
            <th scope="row"><ctg:text
                    text="${applicantRequest.applicant.email}"/></th>
            <th scope="row"><ctg:text
                    text="${applicantRequest.applicantState}"/></th>
            <th scope="row"><a
                    href="<c:url value="to_employee_applicant_request.do?vacancyId=${applicantRequest.vacancy.id}&applicantId=${applicantRequest.applicant.id}"/>">
                <fmt:message key="link.moreInfo"/></a></th>
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
