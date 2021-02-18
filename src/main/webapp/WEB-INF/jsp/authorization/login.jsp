<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 4:19 PM
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

    <title><fmt:message key="login.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <form name="register-form" method="POST" action="login.do">
        <input type="hidden" name="previousUrl" value="login"/>
        <div class="row col-1 offset-1">
            <label for="inputEmail"><fmt:message key="login.inputEmail"/> </label>
        </div>
        <div class="row">
            <div class="col-3 offset-1">
                <input type="text" class="form-control field" id="inputEmail" name="email"
                       required
                       pattern="((\w)([-.](\w))?)+@((\w)([-.](\w))?)+.[a-zA-Zа-яА-Я]{2,4}">
            </div>
        </div>
        <div class="offset-1">
            <label for="inputPassword"><fmt:message key="login.inputPassword"/> </label>
        </div>
        <div class="row">
            <input type="password" class="form-control field" id="inputPassword" name="password"
                   required pattern=".{6,}"/>
        </div>
        <div class="mb-3 row">
            <div class="col-4 offset-5">
                <button class="btn btn-primary button" type="submit"><fmt:message
                        key="button.login"/></button>
            </div>
        </div>
        <wrong-message>
            ${errorData}
        </wrong-message>
        <input name="ctoken" type="hidden" value="${stoken}"/>
    </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>