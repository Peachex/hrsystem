<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 3/20/2021
  Time: 5:22 PM
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
    <title><fmt:message key="user_info.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>

    <c:set var="user" scope="request" value="${requestScope.user}"/>

    <wrong-message>
        ${errorInputData}
        ${errorUserBlocking}
    </wrong-message>

    <div class="card text-dark bg-light offset-3" style="margin-top: 1%;max-width: 50%; margin-bottom: 2%;">
        <div class="card-header"><h3><fmt:message key="user_info.title"/> <c:if
                test="${!user.isActive}"><fmt:message
                key="user_deleted"/></c:if></h3></div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.firstName"/></h4>
            <p class="card-text"><ctg:text text="${user.firstName}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.lastName"/></h4>
            <p class="card-text"><ctg:text text="${user.lastName}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="user_info.role"/></h4>
            <p class="card-text"><ctg:text text="${user.role}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.dateOfBirth"/></h4>
            <p class="card-text"><ctg:text text="${user.dateOfBirth}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.phoneNumber"/></h4>
            <p class="card-text"><ctg:text text="${user.phoneNumber}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.email"/></h4>
            <p class="card-text"><ctg:text text="${user.email}"/></p>
        </div>

        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.avatar"/></h4>
            <p class="card-text"><img
                    src="${pageContext.request.contextPath}/provide_image.do?fileName=${user.photoName}"
                    width="128" height="128" class="rounded" alt="..."></p>
        </div>

        <div class="modal fade" id="changeUserRoleModal" data-bs-backdrop="static"
             data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message
                                key="button.changeRole"/></h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <form name="change-user-role-form" method="POST"
                              action="change_user_role.do">
                            <input type="hidden" name="userId" value="${user.id}">
                            <label for="selectUserRole"><fmt:message
                                    key="user_info_change_user_role_modal.selectRole"/> </label>
                            <select class="form-select mt-1" id="selectUserRole" name="userRole"
                                    aria-label="Default select example" required>
                                <option value="${applicant}"><fmt:message
                                        key="user_info.roleApplicant"/></option>
                                <option value="${employee}"><fmt:message
                                        key="user_info.roleEmployee"/></option>
                                <option value="${admin}"><fmt:message
                                        key="user_info.roleAdmin"/></option>
                            </select>
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

        <div class="col-4 offset-4 mt-3" style="display: flex;justify-content: center;">
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
                                data-bs-toggle="modal" data-bs-target="#changeUserRoleModal">
                            <fmt:message key="button.changeRole"/>
                        </button>
                    </li>
                    <c:if test="${user.isActive}">
                        <li><a class="dropdown-item"
                               href="block_user.do?userId=${user.id}"><fmt:message
                                key="button.block"/> </a></li>
                    </c:if>

                    <c:if test="${!user.isActive}">
                        <li><a class="dropdown-item"
                               href="unblock_user.do?userId=${user.id}"><fmt:message
                                key="button.unblock"/> </a></li>
                    </c:if>
                </ul>
            </div>
        </div>

        <c:if test="${sessionScope.reportId == null}">
            <a class="btn btn-secondary col-4 offset-4 mt-3"
               style="display: flex;justify-content: center; margin-bottom: 5%"
               href="to_admin_user_list.do" role="button"><fmt:message
                    key="button.backToUserList"/> </a>
        </c:if>

        <c:if test="${sessionScope.reportId != null}">
            <a class="btn btn-secondary col-4 offset-4 mt-3"
               style="display: flex;justify-content: center; margin-bottom: 5%"
               href="to_admin_user_report_info.do?reportId=${sessionScope.reportId}" role="button"><fmt:message
                    key="button.backToReportList"/> </a>
        </c:if>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
