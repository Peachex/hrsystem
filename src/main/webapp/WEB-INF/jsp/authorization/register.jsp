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

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

<html>
<head>
    <title><fmt:message key="register.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>

    <form name="register-form" method="POST" action="register.do">
        <input type="hidden" name="previousUrl" value="register"/>
        <div class="row col-1 offset-1">
            <label for="inputFirstName"><fmt:message key="register.inputFirstName"/> </label>
        </div>
        <div class="row">
            <div class="col-3 offset-1">
                <input type="text" class="form-control field" id="inputFirstName" name="firstName" value="${firstName}"
                       placeholder=
                               "<fmt:message key="register.inputNamePlaceholder"/>" required
                       pattern="[a-zA-Zа-яА-Я]{3,35}">
            </div>
            <fmt:message key="register.properFirstNameFormat"/>
        </div>
        <wrong-message>
            ${errorFirstName}
        </wrong-message>

        <div class="offset-1">
            <label for="inputLastName"><fmt:message key="register.inputLastName"/> </label>
        </div>
        <div class="row">
            <input type="text" class="form-control field" id="inputLastName" name="lastName"
                   placeholder=
                           "<fmt:message key="register.inputNamePlaceholder"/>" required
                   pattern="[a-zA-Zа-яА-Я]{3,35}">
        </div>
        <div class="row">
            <fmt:message key="register.properLastNameFormat"/>
        </div>
        <wrong-message>
            ${errorLastName}
        </wrong-message>

        <div class="row">
            <label for="inputDate"><fmt:message key="register.inputDateOfBirth"/> </label>
            <input type="date" id="inputDate" class="form-control" name="dateOfBirth" value="" required>
        </div>
        <wrong-message>
            ${errorDateOfBirth}
        </wrong-message>

        <div class="offset-1">
            <div class="row">
                <label for="inputPhoneNumber"><fmt:message key="register.inputPhoneNumber"/> </label>
            </div>
            <input type="text" class="form-control field" id="inputPhoneNumber" name="phoneNumber"
                   placeholder=
                           "<fmt:message key="register.inputPhoneNumberPlaceholder"/>" required
                   pattern="(\+?(((\d+-\d+)+)|(\d{2,20})|((\d+\s\d+)+)))|(\(\+?\d+\)[-\s]?(((\d+-\d+)+)|(\d+)|((\d+\s\d+)+)))">
        </div>
        <wrong-message>
            ${errorPhoneNumber}
        </wrong-message>

        <div class="row">
            <div class="row">
                <label for="inputEmail"><fmt:message key="register.inputEmail"/> </label>
            </div>
            <input type="text" class="form-control field" id="inputEmail" name="email"
                   placeholder=
                           "<fmt:message key="register.inputEmailPlaceholder"/>" required
                   pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
        </div>
        <wrong-message>
            ${errorEmail}
        </wrong-message>

        <div class="row">
            <div class="row">
                <label for="inputPassword"><fmt:message key="register.inputPassword"/> </label>
            </div>
            <input type="password" class="form-control field" id="inputPassword" name="password"
                   placeholder="<fmt:message key="register.inputPasswordPlaceholder"/>" required pattern=".{6,}">
        </div>
        <wrong-message>
            ${errorPassword}
        </wrong-message>

        <div class="row">
            <div class="row">
                <label for="inputRepeatedPassword"><fmt:message key="register.inputRepeatedPassword"/> </label>
            </div>
            <input type="password" class="form-control field" id="inputRepeatedPassword" name="repeatedPassword"
                   required pattern=".{6,}">
        </div>
        <wrong-message>
            ${errorRepeatedPassword}
        </wrong-message>
        <div class="mb-3 row">
            <div class="col-4 offset-5">
                <button class="btn btn-primary button" type="submit"><fmt:message
                        key="button.completeRegister"/></button>
            </div>
        </div>
        <input name="ctoken" type="hidden" value="${stoken}"/>
    </form>
</div>
</body>
</html>
