<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Edit</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <form action="<c:url value="/app/activity/edit"/>" method="post">
        <input type="hidden" name="id" value="${requestScope.activity.id}">
        <input type="text" name="name" value="${requestScope.activity.name}">
        <input type="text" name="description" value="${requestScope.activity.description}">
        <input type="submit" value="Edit">
    </form>
</body>
</html>
