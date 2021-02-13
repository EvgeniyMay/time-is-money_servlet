<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Delete Activity</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h1>Delete activity page</h1>
    <p>Are you sure you want to delete <span><b>${requestScope.name}</b></span> ?</p>
    <form action="<c:url value="/app/activity/delete"/>" method="POST">
        <input type="hidden" name="id" value="${requestScope.id}">
        <input type="submit" value="Delete">
    </form>
</body>
</html>
