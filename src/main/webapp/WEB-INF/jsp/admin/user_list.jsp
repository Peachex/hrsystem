<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 3/17/2021
  Time: 10:45 AM
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
    <title><fmt:message key="user_list.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <c:set var="users" scope="request" value="${users}"/>

    <wrong-message>
        ${noUser}
        ${errorInputData}
    </wrong-message>

    <div class="row align-items-start" style="margin-top: 3%; width:100%; padding-left: 7.8%;">
        <div class="col-2 offset-2" style="display: flex;justify-content: center">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/to_admin_user_list.do" role="button"><fmt:message
                    key="button.seeAllUsers"/> </a>
        </div>
        <div class="col-3">
            <form name="find-user-form" method="GET" action="find_users_by_key_word.do">
                <div class="d-flex">
                    <input class="form-control me-2" type="search"
                           placeholder="<fmt:message key="vacancy.inputKeyWord"/>"
                           aria-label="Search"
                           value="${keyWord}" name="keyWord">
                    <button class="btn btn-success" type="submit"><fmt:message
                            key="button.find"/></button>
                </div>
            </form>
        </div>
        <div class="col-2" style="display: flex;justify-content: center">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="filterDropDown"
                        data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <fmt:message key="button.users"/>
                </button>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="filterDropDown">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/see_active_users.do"><fmt:message
                            key="button.activeUsers"/> </a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/see_deleted_users.do"><fmt:message
                            key="button.deletedUsers"/> </a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="mt-3">
        <wrong-message>
            ${noUsers}
        </wrong-message>
    </div>

    <div class="mt-3">
        <vacancies-number><fmt:message key="user_list_usersAmount"/> ${users.size()}</vacancies-number>
    </div>

    <table class="table table-dark table-bordered border-secondary mt-4 offset-2" style="width: 67%">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="profile.firstName"/></th>
            <th scope="col"><fmt:message key="profile.lastName"/></th>
            <th scope="col"><fmt:message key="profile.phoneNumber"/></th>
            <th scope="col"><fmt:message key="profile.email"/></th>
            <th scope="col"><fmt:message key="user_info.role"/></th>
            <th scope="col"><fmt:message key="user_isActive"/></th>
            <th scope="col"><fmt:message key="table_action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
        <tr class="table-secondary">
            <th scope="row"><ctg:text text="${user.firstName}"/></th>
            <th scope="row"><ctg:text text="${user.lastName}"/></th>
            <th scope="row"><ctg:text text="${user.phoneNumber}"/></th>
            <th scope="row"><ctg:text text="${user.email}"/></th>
            <th scope="row"><ctg:text text="${user.role}"/></th>
            <th scope="row"><ctg:text text="${user.isActive}"/></th>
            <th scope="row"><a
                    href="<c:url value="to_admin_user_info.do?userId=${user.id}"/>">
                <fmt:message key="link.moreInfo"/></a></th>
        <tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
