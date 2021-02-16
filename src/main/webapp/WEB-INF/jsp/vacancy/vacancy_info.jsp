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
    <title><fmt:message key="vacancy_info.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <%-- <input type="hidden" name="previousUrl"  value="vacancy"/>
     --%>
    <c:set var="vacancy" scope="request" value="${vacancy}"/>
    ${vacancy.position}
    ${vacancy.description}
    ${vacancy.country}
    ${vacancy.city}
    ${user.id}
    ${vacancy.employee.id}
    <c:if test="${user.id == vacancy.employee.id || role.toString().equals(admin)}">
        <form name="see-all-vacancies" method="GET" action="see_all_vacancies.do">
            <div class="col-2 offset-4">
                <button class="btn btn-primary button" type="submit"><fmt:message
                        key="button.seeAllVacancies"/></button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
