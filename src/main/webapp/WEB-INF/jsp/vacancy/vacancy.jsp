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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

<html>
<head>
    <title><fmt:message key="vacancy.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <%-- <input type="hidden" name="previousUrl"  value="vacancy"/>
     <c:set var="vacancyId" scope="request" value="${vacancy.id}"/>
     --%>
    <c:set var="vacancies" scope="request" value="${vacancies}"/>
    <c:if test="${role.toString().equals(employee) || role.toString().equals(admin)}">
        <!-- Button to Open the Modal -->
        <div class="mb-3 row">
            <!-- Button to Open the Modal -->
            <button type="button" class="btn btn-primary button" data-toggle="modal" data-target="#createVacancyModal">
                <fmt:message key="button.createVacancy"/>
            </button>
        </div>

        <!-- The Modal -->

        <div class="modal" id="createVacancyModal">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="create_vacancy_modal.title"/></h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form name="create-vacancy-form" method="POST" action="create_vacancy.do">
                            <input type="hidden" name="employeeId" value="${user.id}">
                            <div class="offset-1">
                                <label for="inputPosition"><fmt:message
                                        key="create_vacancy_modal.inputPosition"/> </label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control field" id="inputPosition" name="position"
                                       placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputPositionPlaceholder"/>"
                                       required
                                    <%--pattern="[]{3,35}"--%>>
                            </div>

                            <div class="row">
                                <label for="inputDescription"><fmt:message
                                        key="create_vacancy_modal.inputDescription"/> </label>
                            </div>

                            <div class="form-group">
                                <textarea class="form-control" rows="5" id="inputDescription"
                                          name="description" placeholder="<fmt:message
                                        key="create_vacancy_modal.inputDescriptionPlaceholder"/>" required></textarea>
                            </div>

                            <div class="offset-1">
                                <label for="inputCountry"><fmt:message
                                        key="create_vacancy_modal.inputCountry"/> </label>
                            </div>
                            <div class="row">
                                <input type="text" class="form-control field" id="inputCountry" name="country"
                                       placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputCountryPlaceholder"/>"
                                       required
                                    <%--pattern="[]{3,35}"--%>>
                            </div>

                            <div class="offset-1">
                                <label for="inputCity"><fmt:message
                                        key="create_vacancy_modal.inputCity"/> </label>
                            </div>
                            <div class="row">
                                <input type="text" class="form-control field" id="inputCity" name="city"
                                       placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputCityPlaceholder"/>"
                                       required
                                    <%--pattern="[]{3,35}"--%>>
                            </div>
                            <div class="mb-3 row">
                                <div class="col-4 offset-5">
                                    <button class="btn btn-primary button" type="submit"><fmt:message
                                            key="button.create"/></button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message
                                key="button.close"/></button>
                    </div>

                </div>
            </div>
        </div>
    </c:if>


    ${noVacancies}
    <c:forEach var="vacancy" items="${vacancies}">
        <tr>
            <a href="<c:url value="vacancy_info.do?vacancyId=${vacancy.id}"/>"
               class="list-group-item list-group-item-action">${vacancy.position}
                    ${vacancy.creationDate}</a>
            <br>
        </tr>
    </c:forEach>
    <input name="ctoken" type="hidden" value="${stoken}"/>
</div>
</body>
</html>