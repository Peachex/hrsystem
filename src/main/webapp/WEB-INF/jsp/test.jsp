<%--
  Created by IntelliJ IDEA.
  User: Peachex
  Date: 2/26/2021
  Time: 8:50 PM
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

    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<div class="main-container">
    <%@ include file="home/header.jsp" %>

    <form action="avatar.upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" class="form-control-file"/>
        <input type="submit" class="btn btn-primary m-1" value="<fmt:message key="button.save"/>"/>
    </form>

    <img src="${pageContext.request.contextPath}/provide_image.do?fileName=${sessionScope.avatar}" class="img-fluid" alt="...">

    <div class="mt-3">
        <wrong-message>
            ${error}
        </wrong-message>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
