<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>New Activity</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h1>New activity form</h1>
    <form action="<c:url value="/app/activity/add"/>" method="POST">
        <label>
            Name:
            <input type="text" name="name">
        </label>
        <label>
            Description:
            <input type="text" name="description">
        </label>
        <input type="submit" value="Add">
    </form>
</body>
</html>
