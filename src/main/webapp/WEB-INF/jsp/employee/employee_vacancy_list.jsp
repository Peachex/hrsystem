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
    <c:set var="employeeVacancies" scope="request" value="${employeeVacancies}"/>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-outline-secondary mt-5" style="margin-left: 30.7%; width: 40%"
            data-bs-toggle="modal" data-bs-target="#createVacancyModal">
        <fmt:message key="button.createVacancy"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="createVacancyModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="create_vacancy_modal.title"/></h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form name="create-vacancy-form" method="POST" action="create_vacancy.do">
                        <label for="inputPosition"><fmt:message
                                key="create_vacancy_modal.inputPosition"/> </label>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputPosition" name="position"
                                   value="${position}" placeholder=
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
                                              required></textarea>
                        </div>

                        <div class="mt-3">
                            <label for="inputCountry"><fmt:message
                                    key="create_vacancy_modal.inputCountry"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputCountry" name="country"
                                   value="${country}" placeholder=
                                           "<fmt:message key="create_vacancy_modal.inputCountryPlaceholder"/>"
                                   required pattern="[a-zA-Zа-яА-я\s]{3,50}">
                        </div>

                        <div class="mt-3">
                            <label for="inputCity"><fmt:message
                                    key="create_vacancy_modal.inputCity"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputCity" name="city"
                                   value="${city}" placeholder=
                                           "<fmt:message key="create_vacancy_modal.inputCityPlaceholder"/>"
                                   required pattern="[a-zA-Zа-яА-я\s]{2,50}">
                        </div>

                        <div class="col-4">
                            <button class="btn btn-outline-success button mt-4" style="margin-left: 100%" type="submit">
                                <fmt:message
                                        key="button.create"/></button>
                        </div>
                        <input name="ctoken" type="hidden" value="${stoken}"/>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal"><fmt:message
                            key="button.close"/></button>
                </div>
            </div>
        </div>
    </div>


    <div class="row align-items-start" style="margin-top: 2%">
        <div class="col-2 offset-2" style="display: flex; justify-content: center">
            <a class="btn btn-outline-secondary" href="to_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeAllVacancies"/> </a>
        </div>
        <div class="col-2" style="display: flex;justify-content: center">
            <div class="dropdown">
                <button class="btn btn-outline-secondary dropdown-toggle" type="button" id="sortDropDown"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <fmt:message key="button.seeVacanciesWithApplicantsRequests"/>
                </button>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="sortDropDown">
                    <li><a class="dropdown-item"
                           href="see_employee_vacancies_with_active_applicants_requests.do"><fmt:message
                            key="button.seeVacanciesWithActiveApplicantsRequests"/> </a></li>
                    <li><a class="dropdown-item"
                           href="see_employee_vacancies_with_not_active_applicants_requests.do"><fmt:message
                            key="button.seeVacanciesWithNotActiveApplicantsRequests"/> </a></li>
                    <li><a class="dropdown-item"
                           href="see_employee_vacancies_with_applicants_requests.do"><fmt:message
                            key="button.seeVacanciesWithBothActiveApplicantsRequests"/> </a></li>
                </ul>
            </div>
        </div>
        <div class="col-2" style="display: flex; justify-content: center">
            <a class="btn btn-outline-secondary" href="see_active_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeActiveVacancies"/> </a>
        </div>
        <div class="col-2" style="display: flex; justify-content: center">
            <a class="btn btn-outline-secondary" href="see_deleted_employee_vacancies.do" role="button"><fmt:message
                    key="button.seeDeletedVacancies"/> </a>
        </div>
    </div>
    <div class="mt-3">
        <wrong-message>
            ${errorVacancyCreation}
        </wrong-message>
        <wrong-message>
            ${noVacancies}
        </wrong-message>
        <wrond-message>
            ${noVacancy}
        </wrond-message>
        <wrong-message>
            ${errorVacancyDelete}
            ${errorVacancyRestore}
        </wrong-message>
    </div>
    <vacancies-number><fmt:message
            key="vacancy_vacanciesAmount"/> ${vacancies.size()}</vacancies-number>

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
        <c:forEach var="vacancy" items="${employeeVacancies}">
        <tr class="table-secondary">
            <th scope="row">${vacancy.position}</th>
            <th scope="row">${vacancy.country}</th>
            <th scope="row">${vacancy.city}</th>
            <th scope="row">${vacancy.creationDate}</th>
            <th scope="row"><a href="<c:url value="to_employee_vacancy_info.do?vacancyId=${vacancy.id}"/>"><fmt:message
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
