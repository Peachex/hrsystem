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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

    <title><fmt:message key="vacancy.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="vacancies" scope="application" value="${vacancies}"/>
    <c:if test="${role.toString().equals(employee) || role.toString().equals(admin)}">

        <wrongMessage>
                ${errorVacancyCreation}
        </wrongMessage>

        <!-- Button to Open the Modal -->
        <button type="submit" class="btn btn-primary button" data-toggle="modal" data-target="#createVacancyModal">
            <fmt:message key="button.createVacancy"/>
        </button>

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
                            <div class="offset-1">
                                <label for="inputPosition"><fmt:message
                                        key="create_vacancy_modal.inputPosition"/> </label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control field" id="inputPosition" name="position"
                                       value="${position}" placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputPositionPlaceholder"/>"
                                       required pattern="[\w\s\p{Punct}]{3,1000}">
                            </div>

                            <div class="row">
                                <label for="inputDescription"><fmt:message
                                        key="create_vacancy_modal.inputDescription"/> </label>
                            </div>

                            <div class="form-group">
                                    <textarea class="form-control" rows="5" id="inputDescription"
                                              name="description" placeholder="<fmt:message
                                             key="create_vacancy_modal.inputDescriptionPlaceholder"/>"
                                              required></textarea>
                            </div>

                            <div class="offset-1">
                                <label for="inputCountry"><fmt:message
                                        key="create_vacancy_modal.inputCountry"/> </label>
                            </div>
                            <div class="row">
                                <input type="text" class="form-control field" id="inputCountry" name="country"
                                       value="${country}" placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputCountryPlaceholder"/>"
                                       required pattern="[a-zA-Zа-яА-я\s]{3,50}">
                            </div>

                            <div class="offset-1">
                                <label for="inputCity"><fmt:message
                                        key="create_vacancy_modal.inputCity"/> </label>
                            </div>
                            <div class="row">
                                <input type="text" class="form-control field" id="inputCity" name="city"
                                       value="${city}" placeholder=
                                               "<fmt:message key="create_vacancy_modal.inputCityPlaceholder"/>"
                                       required pattern="[a-zA-Zа-яА-я\s]{2,50}">
                            </div>
                            <div class="mb-3 row">
                                <div class="col-4 offset-5">
                                    <button class="btn btn-primary button" type="submit"><fmt:message
                                            key="button.create"/></button>
                                </div>
                            </div>
                            <input name="ctoken" type="hidden" value="${stoken}"/>
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

    <form name="create-vacancy-form" method="GET" action="find_vacancies_by_key_word.do">
        <div class="form-group col-4">
            <input type="text" class="form-control-plaintext" placeholder="<fmt:message key="vacancy.inputKeyWord"/> "
                   value="${keyWord}" name="keyWord">
        </div>
        <div class="mb-3 row">
            <div class="col-4 offset-5">
                <button class="btn btn-primary button" type="submit"><fmt:message
                        key="button.find"/></button>
            </div>
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

    <c:if test="${role.toString().equals(admin)}">
        <form name="see-all-vacancies" method="GET" action="see_all_vacancies.do">
            <div class="col-2 offset-4">
                <button class="btn btn-primary button" type="submit"><fmt:message
                        key="button.seeAllVacancies"/></button>
            </div>
        </form>
    </c:if>

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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
        integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
        integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
        crossorigin="anonymous"></script>
</body>
</html>


