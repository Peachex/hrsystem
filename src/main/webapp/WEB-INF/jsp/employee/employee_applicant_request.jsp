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
<%@ taglib prefix="ctg" uri="customtags" %>
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
    <c:set var="applicantStateLeftRequest" scope="page" value="LEFT_REQUEST"/>
    <c:set var="applicantStateReadyForTechnicalInterview" scope="page" value="READY_FOR_TECHNICAL_INTERVIEW"/>

    <div class="card text-dark bg-light offset-3" style="margin-top: 3%;max-width: 50%; margin-bottom: 2%">
        <div class="card-header" style="display: flex"><h3><fmt:message
                key="employee_applicant_request.title"/> <c:if
                test="${applicantRequest.applicantState.toString().equals(applicantStatePassed) ||
                applicantRequest.applicantState.toString().equals(applicantStateFailed)}"><fmt:message
                key="employee_applicant_request.requestNotActiveValue"/></c:if></h3>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.firstName"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicant.firstName}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.lastName"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicant.lastName}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.dateOfBirth"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicant.dateOfBirth}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.phoneNumber"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicant.phoneNumber}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.email"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicant.email}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="applicant_request.applicantState"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.applicantState}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="employee_applicant_request.applicantSummary"/></h4>
            <p class="card-text"><ctg:text text="${applicantRequest.summary}"/></p>
        </div>
        <c:if test="${applicantRequest.basicInterviewResult != null || applicantRequest.technicalInterviewResult != null}">
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="employee_applicant_request.applicantInterviewResults"/></h4>
            <div class="accordion accordion-flush" id="accordionFlushExample">
                <c:if test="${applicantRequest.basicInterviewResult != null}">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingOne">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#flush-collapseOne" aria-expanded="false"
                                    aria-controls="flush-collapseOne">
                                <fmt:message key="employee_applicant_request.basicInterviewName"/>
                            </button>
                        </h2>
                        <div id="flush-collapseOne" class="accordion-collapse collapse"
                             aria-labelledby="flush-headingOne"
                             data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                <strong><fmt:message key="employee_applicant_request.interviewResultRating"/>
                                    <ctg:text text="${applicantRequest.basicInterviewResult.rating}"/></strong>
                                <div>
                                    <ctg:text text="${applicantRequest.basicInterviewResult.comment}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${applicantRequest.technicalInterviewResult != null}">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#flush-collapseTwo" aria-expanded="false"
                                    aria-controls="flush-collapseTwo">
                                <fmt:message key="employee_applicant_request.technicalInterviewName"/>
                            </button>
                        </h2>
                        <div id="flush-collapseTwo" class="accordion-collapse collapse"
                             aria-labelledby="flush-headingTwo"
                             data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                <strong><fmt:message key="employee_applicant_request.interviewResultRating"/>
                                    <ctg:text text="${applicantRequest.technicalInterviewResult.rating}"/></strong>
                                <div>
                                    <ctg:text text="${applicantRequest.technicalInterviewResult.comment}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            </c:if>

            <c:if test="${applicantRequest.applicantState.toString().equals(applicantStateLeftRequest) ||
             (applicantRequest.applicantState.toString().equals(applicantStateReadyForTechnicalInterview) &&
             applicantRequest.technicalInterviewDate != null)}">
                <div class="col-4 offset-4" style="display: flex;justify-content: center;">
                    <button type="button" class="btn btn-outline-secondary col-8 mb-1"
                            style="display: flex;justify-content: center; margin-top: 10%; width: 100%"
                            data-bs-toggle="modal" data-bs-target="#createInterviewResultModal">
                        <fmt:message key="button.createInterviewResult"/>
                    </button>
                </div>
            </c:if>

            <c:if test="${applicantRequest.applicantState.toString().equals(applicantStateReadyForTechnicalInterview) &&
             applicantRequest.technicalInterviewDate == null}">
                <div class="col-4 offset-4" style="display: flex;justify-content: center;">
                    <button type="button" class="btn btn-outline-secondary col-8 mb-1"
                            style="display: flex;justify-content: center; margin-top: 10%; width: 100%"
                            data-bs-toggle="modal" data-bs-target="#scheduleTechnicalInterviewModal">
                        <fmt:message key="button.scheduleTechnicalInterview"/>
                    </button>
                </div>
            </c:if>

            <!-- Modal -->
            <div class="modal fade" id="createInterviewResultModal" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message
                                    key="interview_result_modal.title"/></h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <form name="create-interview-result-form" method="POST"
                                  action="create_interview_result.do">
                                <input type="hidden" name="applicantId" value="${applicantRequest.applicant.id}">
                                <input type="hidden" name="vacancyId" value="${applicantRequest.vacancy.id}">
                                <input type="hidden" name="email" value="${applicantRequest.applicant.email}">
                                <label for="inputRating"><fmt:message
                                        key="interview_result_modal.inputRating"/> </label>
                                <div class="form-group mt-1">
                                    <input type="text" class="form-control field" id="inputRating"
                                           name="rating"
                                           value="${rating}" placeholder=
                                                   "<fmt:message key="interview_result_modal.inputRatingPlaceholder"/>"
                                           required pattern="[1-9]|10">
                                </div>

                                <div class="mt-3">
                                    <label for="inputComment"><fmt:message
                                            key="interview_result_modal.inputComment"/> </label>
                                </div>
                                <div class="form-group mt-1">
                                    <textarea class="form-control" rows="5" id="inputComment"
                                              name="comment" placeholder="<fmt:message
                                             key="interview_result_modal.inputCommentPlaceholder"/>"
                                              required minlength="1" maxlength="25000">${comment}</textarea>
                                </div>

                                <div class="mt-3">
                                    <label for="inputState"><fmt:message
                                            key="interview_result_modal.updateState"/> </label>
                                </div>
                                <select class="form-select mt-1" id="inputState" name="applicantState"
                                        aria-label="Default select example" required>
                                    <option value="${applicantStateReadyForTechnicalInterview}"><fmt:message
                                            key="interview_result_modal.applicantStateReadyForTechnicalInterview"/></option>
                                    <option value="${applicantStatePassed}"><fmt:message
                                            key="interview_result_modal.applicantStatePassed"/></option>
                                    <option value="${applicantStateFailed}"><fmt:message
                                            key="interview_result_modal.applicantStateFailed"/></option>
                                </select>

                                <div class="col-4">
                                    <button class="btn btn-outline-success button mt-4"
                                            style="margin-left: 100%" type="submit">
                                        <fmt:message
                                                key="button.create"/></button>
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

            <div class="modal fade" id="scheduleTechnicalInterviewModal" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message
                                    key="schedule_technical_interview_modal.title"/></h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <form name="schedule-technical-interview-form" method="POST"
                                  action="home">
                                <label for="inputDate"><fmt:message
                                        key="schedule_technical_interview_modal.inputDate"/> </label>
                                <div class="form-group mt-1">
                                    <input type="date" id="inputDate" class="form-control" name="date"
                                           required>
                                </div>

                                <div class="col-4">
                                    <button class="btn btn-outline-success button mt-4"
                                            style="margin-left: 100%" type="submit">
                                        <fmt:message
                                                key="button.schedule"/></button>
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

            <div class="col-4 offset-4" style="display: flex;justify-content: center;">
                <a class="btn btn-outline-secondary col-8 mb-2"
                   style="display: flex;justify-content: center; margin-top: 10%; width:100%"
                   href="to_employee_vacancy_info.do?vacancyId=${applicantRequest.vacancy.id}"
                   role="button"><fmt:message
                        key="button.back"/> </a>
            </div>
        </div>
    </div>
    <div class="mt-3" style="margin-bottom: 2%">
        <wrong-message>
            ${errorInputData}
        </wrong-message>
        <wrong-message>
            ${errorInterviewResultCreation}
        </wrong-message>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
