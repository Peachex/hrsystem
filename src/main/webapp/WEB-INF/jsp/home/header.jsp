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
<%@ taglib prefix="ctg" uri="customtags" %>
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
                    <li><a class="dropdown-item" href="change_language.do?newLocale=ru_RU">Русский</a></li>
                    <li><a class="dropdown-item" href="change_language.do?newLocale=en_EN">English</a></li>
                </ul>
            </li>

            <c:if test="${role.toString().equals(guest)}">
                <a class="nav-link header-link offset-1" href="register"><fmt:message
                        key="header.register"/></a>
                <a class="nav-link header-link offset-1" href="login"><fmt:message
                        key="header.login"/></a>
            </c:if>

            <ctg:user-name>
                <li class="nav-item dropdown offset-1">
                    <a class="nav-link dropdown-toggle header-link" id="navbarDropdownUser" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <ctg:text text="${user.firstName}"/> <ctg:text text="${user.lastName}"/> <ctg:text
                            text=" (${user.role.toString().toLowerCase()})"/>

                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                        <li><a class="dropdown-item" href="to_user_profile.do"><fmt:message
                                key="user.profile"/></a></li>
                        <c:if test="${role.toString().equals(applicant)}">
                            <li><a class="dropdown-item" href="to_applicant_requests.do"><fmt:message
                                    key="user.requests"/></a></li>
                        </c:if>
                        <c:if test="${role.toString().equals(employee)}">
                            <li><a class="dropdown-item" href="to_employee_vacancies.do"><fmt:message
                                    key="user.employeeVacancies"/></a></li>
                        </c:if>
                        <c:if test="${role.toString().equals(admin)}">
                            <li><a class="dropdown-item" href="to_admin_user_list.do"><fmt:message
                                    key="header.adminUsers"/></a></li>
                        </c:if>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="logout.do"><fmt:message
                                key="header.logout"/></a></li>
                    </ul>
                </li>
                <img src="${pageContext.request.contextPath}/provide_image.do?fileName=${sessionScope.user.photoName}"
                     width="64" height="64"
                     class="rounded-circle avatar">
            </ctg:user-name>
        </nav>
    </div>
</div>
</body>
</html>
