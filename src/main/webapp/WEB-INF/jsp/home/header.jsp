<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 7:31 PM
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

<html>
<head>
    <title><fmt:message key="header.title"/></title>
</head>
<body>
<div class="container">
    <div class="row header">
        <nav class="nav">
            <c:set var="role" scope="session" value="${currentRole}"/>
            <c:set var="guest" scope="session" value="GUEST"/>
            <c:set var="admin" scope="session" value="ADMIN"/>
            <c:set var="applicant" scope="session" value="APPLICANT"/>
            <c:set var="employee" scope="session" value="EMPLOYEE"/>
            <c:set var="user" scope="session" value="${user}"/>

            <a class="nav-link header-link" href="home"><fmt:message key="header.home"/> </a>
            <a class="nav-link header-link offset-1" href="vacancy.do"><fmt:message key="header.vacancy"/></a>
            <a class="nav-link header-link offset-1" href="change_language.do"><fmt:message
                    key="header.changeLanguage"/></a>
            <c:if test="${role.toString().equals(guest)}">
                <a class="nav-link header-link offset-1" href="register"><fmt:message key="header.register"/></a>
                <a class="nav-link header-link offset-1" href="login"><fmt:message key="header.login"/></a>
            </c:if>

            <c:if test="${role.toString().equals(applicant) || role.toString().equals(employee)}">
                <a class="nav-link header-link offset-1" href="register">${user.firstName} ${user.lastName}</a>
                <a class="nav-link header-link offset-1" href="logout.do"><fmt:message key="header.logout"/></a>
            </c:if>

            <c:if test="${role.toString().equals(admin)}">
                <a class="nav-link header-link offset-1" href="register"><fmt:message key="header.admin"/></a>
                <a class="nav-link header-link offset-1" href="register">${user.firstName} ${user.lastName}</a>
                <a class="nav-link header-link offset-1" href="logout.do"><fmt:message key="header.logout"/></a>
            </c:if>
        </nav>
        <input name="ctoken" type="hidden" value="${stoken}"/>
    </div>
</div>
</body>
</html>
