<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="locale.page_content"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title><fmt:message key="register.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>

    <form name="register-form" method="POST" action="register.do">
        <div class="mt-5 col-2 offset-5">
            <label for="inputFirstName"><fmt:message key="register.inputFirstName"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="text" class="form-control field" id="inputFirstName" name="firstName" value="${firstName}"
                   placeholder=
                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                   pattern="[a-zA-Zа-яА-Я]{3,35}">
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputLastName"><fmt:message key="register.inputLastName"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="text" class="form-control field" id="inputLastName" name="lastName" value="${lastName}"
                   placeholder=
                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                   pattern="[a-zA-Zа-яА-Я]{3,35}">
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputDate"><fmt:message key="register.inputDateOfBirth"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="date" id="inputDate" class="form-control" name="dateOfBirth" value="${dateOfBirth}"
                   required>
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputPhoneNumber"><fmt:message key="register.inputPhoneNumber"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="text" class="form-control field" id="inputPhoneNumber" name="phoneNumber"
                   value="${phoneNumber}"
                   placeholder=
                           "<fmt:message key="register.inputPhoneNumberPlaceholder"/>" required
                   pattern="(\+?(((\d+-\d+)+)|(\d{2,20})|((\d+\s\d+)+)))|(\(\+?\d+\)[-\s]?(((\d+-\d+)+)|(\d+)|((\d+\s\d+)+)))">
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputEmail"><fmt:message key="register.inputEmail"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="text" class="form-control field" id="inputEmail" name="email" value="${email}"
                   placeholder=
                           "<fmt:message key="register.inputEmailPlaceholder"/>" required
                   pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputPassword"><fmt:message key="register.inputPassword"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="password" class="form-control field" id="inputPassword" name="password" value="${password}"
                   placeholder="<fmt:message key="register.inputPasswordPlaceholder"/>" required
                   pattern="[\w\s\p{Punct}]{6,80}">
        </div>

        <div class="mt-3 col-2 offset-5">
            <label for="inputRepeatedPassword"><fmt:message key="register.inputRepeatedPassword"/> </label>
        </div>
        <div class="col-2 offset-5">
            <input type="password" class="form-control field" id="inputRepeatedPassword" name="repeatedPassword"
                   value="${repeatedPassword}" placeholder="<fmt:message key="register.inputPasswordPlaceholder"/>"
                   required pattern="[\w\s\p{Punct}]{6,80}">
        </div>
        <div class="mt-3">
            <wrong-message>
                ${errorInputData}
            </wrong-message>
        </div>
        <div class="mt-5 col-2 offset-5">
            <button class="btn btn-primary" style="width: 100%" type="submit"><fmt:message
                    key="button.completeRegister"/></button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
