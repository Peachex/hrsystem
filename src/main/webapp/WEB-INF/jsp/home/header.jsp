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

<html>
<head>
    <title><fmt:message key="header.title"/></title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

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
            <c:set var="userPhotoName" scope="session" value="${userPhotoName}"/>

            <a class="nav-link header-link" href="home"><fmt:message key="header.home"/> </a>
            <a class="nav-link header-link offset-1" href="to_vacancies.do"><fmt:message key="header.vacancy"/></a>

            <li class="nav-item dropdown offset-1">
                <a class="nav-link dropdown-toggle header-link" id="navbarDropdown" data-bs-toggle="dropdown"
                   aria-expanded="false">
                    <fmt:message key="header.changeLanguage"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="change_language.do?newLocale=ru_RU"><fmt:message
                            key="header.russianLanguage"/></a></li>
                    <li><a class="dropdown-item" href="change_language.do?newLocale=en_EN"><fmt:message
                            key="header.englishLanguage"/></a></li>
                </ul>
            </li>

            <c:if test="${role.toString().equals(guest)}">
                <a class="nav-link header-link offset-1" href="to_register.do"><fmt:message
                        key="header.register"/></a>
                <a class="nav-link header-link offset-1" href="to_login.do"><fmt:message
                        key="header.login"/></a>
            </c:if>

            <c:if test="${!role.toString().equals(guest)}">
                <li class="nav-item dropdown offset-1">
                    <a class="nav-link dropdown-toggle header-link" id="navbarDropdownUser" data-bs-toggle="dropdown"
                       aria-expanded="false">
                            ${user.firstName} ${user.lastName}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                        <li><a class="dropdown-item" href="home"><fmt:message
                                key="user.profile"/></a></li>
                        <c:if test="${role.toString().equals(applicant)}">
                            <li><a class="dropdown-item" href="home"><fmt:message
                                    key="user.requests"/></a></li>
                        </c:if>
                        <c:if test="${!role.toString().equals(applicant)}">
                            <li><a class="dropdown-item" href="home"><fmt:message
                                    key="user.employeeVacancies"/></a></li>
                        </c:if>
                        <c:if test="${role.toString().equals(admin)}">
                            <li><a class="dropdown-item" href="home"><fmt:message
                                    key="header.admin"/></a></li>
                        </c:if>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="logout.do"><fmt:message
                                key="header.logout"/></a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${userPhotoName == null}">
                        <img src="${pageContext.request.contextPath}/img/default_avatar.png" width="72" height="64"
                             class="rounded-circle avatar">
                    </c:when>
                    <c:otherwise>
                        <%--<img src="${pageContext.request.contextPath}/img/${userPhotoName}" width="128" height="128"
                             class="avatar"> --%>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </nav>
    </div>
</div>

</body>
</html>
