<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Completed missions</title>
</head>
<body>
<header>
    <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
</header>
<h2>Completed missions:</h2>
<table>
    <tr>
        <th>User</th>
        <th>Activity</th>
        <th>Start time</th>
        <th>End time</th>
    </tr>
    <c:forEach items="${requestScope.missions}" var="mission">
        <tr>
            <td>${mission.user.login}</td>
            <td>${mission.activity.name}</td>
            <td>${mission.startTime}</td>
            <td>${mission.endTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
