<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/25/2021
  Time: 12:49 PM
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

    <title><fmt:message key="employee_applicant_request.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="applicantRequest" scope="request" value="${applicantRequest}"/>
    <c:set var="applicantStatePassed" scope="page" value="PASSED"/>
    <c:set var="applicantStateFailed" scope="page" value="FAILED"/>

    <div class="card text-dark bg-light offset-3" style="margin-top: 3%;max-width: 50%;">
        <div class="card-header" style="display: flex"><h3><fmt:message
                key="employee_applicant_request.title"/> <c:if
                test="${applicantRequest.applicantState.toString().equals(applicantStatePassed) ||
                applicantRequest.applicantState.toString().equals(applicantStateFailed)}"><fmt:message
                key="employee_applicant_request.requestNotActiveValue"/></c:if></h3>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.firstName"/></h4>
            <p class="card-text">${applicantRequest.applicant.firstName}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.lastName"/></h4>
            <p class="card-text">${applicantRequest.applicant.lastName}}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.dateOfBirth"/></h4>
            <p class="card-text">${applicantRequest.applicant.dateOfBirth}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.phoneNumber"/></h4>
            <p class="card-text">${applicantRequest.applicant.phoneNumber}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.email"/></h4>
            <p class="card-text">${applicantRequest.applicant.email}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="applicant_request.applicantState"/></h4>
            <p class="card-text">${applicantRequest.applicantState}</p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="employee_applicant_request.applicantSummary"/></h4>
            <p class="card-text">${applicantRequest.summary}</p>
            <div class="accordion accordion-flush" id="accordionFlushExample">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                            Accordion Item #1
                        </button>
                    </h2>
                    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                            Accordion Item #2
                        </button>
                    </h2>
                    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="flush-headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                            Accordion Item #3
                        </button>
                    </h2>
                    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
                    </div>
                </div>
            </div>
        </div>

        <%--<div class="col-4 offset-4" style="display: flex;justify-content: center;">
            <div class="dropdown col-8 mb-4">
                <button class="btn btn-outline-secondary dropdown-toggle button mt-3" type="button" id="actionDropDown"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <fmt:message key="button.action"/>
                </button>
                <ul class="dropdown-menu dropdown-menu-dark" style="width: 100%" aria-labelledby="actionDropDown">
                    <li>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-outline-secondary dropdown-item"
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
                                <h4 class="modal-title"><fmt:message key="create_vacancy_modal.title"/></h4>
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
                                              required minlength="3" maxlength="10000"></textarea>
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
                                        <input type="text" class="form-control field" id="inputCity" name="city"
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
                                    <input name="ctoken" type="hidden" value="${stoken}"/>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">
                                    <fmt:message
                                            key="button.close"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>

        <div class="col-4 offset-4" style="display: flex;justify-content: center;">
            <a class="btn btn-outline-secondary col-8 mb-4"
               style="display: flex;justify-content: center"
               href="to_employee_vacancy_info.do?vacancyId=${applicantRequest.vacancy.id}.do" role="button"><fmt:message
                    key="button.back"/> </a>
        </div>
    </div>
    <div class="mt-3">
        <wrong-message>

        </wrong-message>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
