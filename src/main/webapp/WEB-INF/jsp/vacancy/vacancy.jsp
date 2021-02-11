<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/10/2021
  Time: 7:28 PM
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
    <title><fmt:message key="vacancy.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="../home/header.jsp" %>
    <input type="hidden" name="previousUrl" value="vacancy"/>
    <c:set var="vacancies" scope="application" value="${vacancies}"/>
    ${noVacancies}
    <c:forEach var="vac" items="${vacancies}">
        <tr>
            <br>
            ${vac.position}
                ${vac.creationDate}
            <br>
        </tr>
    </c:forEach>
    <input name="ctoken" type="hidden" value="${stoken}"/>
</div>
</body>
</html>