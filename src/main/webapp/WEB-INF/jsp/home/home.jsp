<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 4:15 PM
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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title><fmt:message key="home.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="header.jsp" %>

    <h1 class="display-5" style="margin-top: 5%; justify-content: center; display: flex; font-family: cursive;
    color: #2e2e2e;"><fmt:message key="home.projectName"/></h1>

    <div id="carouselExampleInterval" class="carousel carousel-dark slide"
         style="width: 20%;margin-top: 3%; margin-left: 40%;"
         data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="4000">
                <img src="${pageContext.request.contextPath}/img/carousel/headhunting.png" class="d-block w-100"
                     alt="...">
                <h1><fmt:message key="home.firstSlideName"/></h1>
                <h3><fmt:message key="home.seeVacanciesMessage"/></h3>
            </div>
            <div class="carousel-item" data-bs-interval="4000">
                <img src="${pageContext.request.contextPath}/img/carousel/portfolio.png" class="d-block w-100"
                     alt="...">
                <h1><fmt:message key="home.secondSlideName"/></h1>
                <h3><fmt:message key="home.registerAndApplyVacanciesMessage"/></h3>
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/img/carousel/chair.png" class="d-block w-100" alt="...">
                <h1><fmt:message key="home.thirdSlideName"/></h1>
                <h3><fmt:message key="home.getJobMessage"/></h3>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </a>
    </div>

    <wrong-message>
        ${errorDuplicate}
        ${errorInputData}
    </wrong-message>

    <c:if test="${sessionScope.role.toString().equals(applicant) || sessionScope.role.toString().equals(employee)}">
        <button type="button" class="btn btn-secondary col-2 offset-5"
                style="margin-top: 3%; display: flex; justify-content: center;"
                data-bs-toggle="modal" data-bs-target="#createUserReportModal">
            <fmt:message key="home.userReportTitle"/>
        </button>
    </c:if>

    <div class="modal fade" id="createUserReportModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="home.userReportTitle"/></h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form name="create-user-report-form" method="POST" action="create_user_report.do">
                        <label for="inputSubject"><fmt:message
                                key="home.userReportInputSubject"/> </label>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputSubject" name="subject"
                                   value="${subject}" placeholder=
                                           "<fmt:message key="home.userReportInputSubjectPlaceholder"/>"
                                   required pattern="[А-Яа-я\w\s\p{Punct}]{1,100}">
                        </div>

                        <div class="mt-3">
                            <label for="inputComment"><fmt:message
                                    key="home.userReportInputComment"/> </label>
                        </div>
                        <div class="form-group mt-1">
                                    <textarea class="form-control" rows="5" id="inputComment"
                                              name="comment" placeholder="<fmt:message
                                             key="home.userReportInputCommentPlaceholder"/>"
                                              required minlength="1" maxlength="25000">${comment}</textarea>
                        </div>

                        <div class="col-4">
                            <button class="btn btn-outline-success button mt-4" style="margin-left: 100%" type="submit">
                                <fmt:message
                                        key="button.create"/></button>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal"><fmt:message
                            key="button.close"/></button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
