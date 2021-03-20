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
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>

    <wrong-message>
        ${errorInputData}
        ${invalidCurrentPassword}
    </wrong-message>

    <div class="card text-dark bg-light offset-3" style="margin-top: 1%;max-width: 50%; margin-bottom: 2%;">
        <div class="card-header"><h3><fmt:message key="profile.title"/></h3></div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.firstName"/></h4>
            <p class="card-text"><ctg:text text="${user.firstName}"/></p>
        </div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="profile.lastName"/></h4>
            <p class="card-text"><ctg:text text="${user.lastName}"/></p>
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
                    src="${pageContext.request.contextPath}/provide_image.do?fileName=${sessionScope.user.photoName}"
                    width="128" height="128" class="rounded" alt="..."></p>
            <form action="avatar.upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" class="form-control-file"/>
                <input type="submit" class="btn btn-outline-secondary" value="<fmt:message key="button.upload"/>"/>
            </form>
        </div>


        <div class="col-4 offset-4" style="display: flex;justify-content: center;">
            <div class="dropdown col-8 mb-4">
                <button class="btn btn-secondary dropdown-toggle button mt-3" type="button"
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
                                data-bs-toggle="modal" data-bs-target="#editProfileModal">
                            <fmt:message key="button.edit"/>
                        </button>
                    </li>

                    <li>
                        <button type="button" class="btn btn-secondary dropdown-item"
                                data-bs-toggle="modal" data-bs-target="#changePasswordModal">
                            <fmt:message key="profile.changePassword"/>
                        </button>
                    </li>

                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <button type="button" class="btn btn-secondary dropdown-item"
                                data-bs-toggle="modal" data-bs-target="#deleteAccountModal">
                            <fmt:message key="profile.deleteAccount"/>
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <a class="btn btn-secondary col-2 offset-5 mt-5"
       style="display: flex;justify-content: center; margin-bottom: 5%"
       href="home" role="button"><fmt:message
            key="button.home"/> </a>

    <!-- Modal -->
    <div class="modal fade" id="editProfileModal" data-bs-backdrop="static"
         data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="profile.editInfoTitle"/></h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form name="edit-user-info" method="POST" action="edit_user_profile.do">
                        <label for="inputFirstName"><fmt:message key="register.inputFirstName"/> </label>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputFirstName"
                                   name="firstName"
                                   value="${firstName}"
                                   placeholder=
                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                        </div>

                        <div class="mt-3">
                            <label for="inputLastName"><fmt:message key="register.inputLastName"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputLastName" name="lastName"
                                   value="${lastName}"
                                   placeholder=
                                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                                   pattern="[a-zA-Zа-яА-Я]{3,35}">
                        </div>

                        <div class="mt-3">
                            <label for="inputDate"><fmt:message key="register.inputDateOfBirth"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="date" id="inputDate" class="form-control" name="dateOfBirth"
                                   value="${dateOfBirth}"
                                   required>
                        </div>

                        <div class="mt-3">
                            <label for="inputPhoneNumber"><fmt:message
                                    key="register.inputPhoneNumber"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputPhoneNumber"
                                   name="phoneNumber"
                                   value="${phoneNumber}"
                                   placeholder=
                                           "<fmt:message key="register.inputPhoneNumberPlaceholder"/>"
                                   required
                                   pattern="(\+?(((\d+-\d+)+)|(\d{2,20})|((\d+\s\d+)+)))|(\(\+?\d+\)[-\s]?(((\d+-\d+)+)|(\d+)|((\d+\s\d+)+)))">
                        </div>

                        <div class="mt-3">
                            <label for="inputEmail"><fmt:message key="register.inputEmail"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="text" class="form-control field" id="inputEmail" name="email"
                                   value="${email}"
                                   placeholder=
                                           "<fmt:message key="register.inputEmailPlaceholder"/>" required
                                   pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-success button mt-4" style="margin-left: 100%"
                                    type="submit">
                                <fmt:message
                                        key="button.save"/></button>
                        </div>
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


    <!-- Modal -->
    <div class="modal fade" id="changePasswordModal" data-bs-backdrop="static"
         data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="profile.changePassword"/></h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form name="change-password-form" method="POST" action="change_user_password.do">
                        <label for="inputCurrentPassword"><fmt:message
                                key="profile.inputCurrentPassword"/> </label>
                        <div class="form-group mt-1">
                            <input type="password" class="form-control field" id="inputCurrentPassword"
                                   name="currentPassword"
                                   value="${currentPassword}"
                                   placeholder=
                                           "<fmt:message key="register.inputPasswordPlaceholder"/>" required
                                   pattern="[\w\s\p{Punct}]{6,80}">
                        </div>

                        <div class="mt-3">
                            <label for="inputNewPassword"><fmt:message
                                    key="profile.inputNewPassword"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="password" class="form-control field" id="inputNewPassword"
                                   name="newPassword"
                                   value="${newPassword}"
                                   placeholder=
                                           "<fmt:message key="register.inputPasswordPlaceholder"/>" required
                                   pattern="[\w\s\p{Punct}]{6,80}">
                        </div>

                        <div class="mt-3">
                            <label for="repeatNewPassword"><fmt:message
                                    key="profile.repeatNewPassword"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="password" class="form-control field" id="repeatNewPassword"
                                   name="repeatedNewPassword"
                                   value="${repeatedNewPassword}"
                                   placeholder=
                                           "<fmt:message key="register.inputPasswordPlaceholder"/>" required
                                   pattern="[\w\s\p{Punct}]{6,80}">
                        </div>

                        <div class="col-4">
                            <button class="btn btn-outline-success button mt-4" style="margin-left: 100%"
                                    type="submit">
                                <fmt:message
                                        key="button.change"/></button>
                        </div>
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

    <!-- Modal -->
    <div class="modal fade" id="deleteAccountModal" data-bs-backdrop="static"
         data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="profile.editInfoTitle"/></h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form name="edit-user-info" method="POST" action="delete_user_account.do">
                        <div class="mt-1">
                            <h5 style="text-align: center;">
                                <fmt:message key="profile.deleteAccountMessage"/></h5>
                        </div>
                        <div class="mt-3">
                            <label for="inputCurrentPassword2"><fmt:message
                                    key="profile.inputCurrentPassword"/> </label>
                        </div>
                        <div class="form-group mt-1">
                            <input type="password" class="form-control field" id="inputCurrentPassword2"
                                   name="currentPassword"
                                   placeholder=
                                           "<fmt:message key="register.inputPasswordPlaceholder"/>" required
                                   pattern="[\w\s\p{Punct}]{6,80}">
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-success button mt-4" style="margin-left: 100%"
                                    type="submit">
                                <fmt:message
                                        key="button.delete"/></button>
                        </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
