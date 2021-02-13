<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>My Profile</title>
    <meta charset="utf-8">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h2>Hello, ${sessionScope.get("authUser").login}</h2>
    <h3>Missions: </h3>
    <table>
        <c:forEach items="${sessionScope.get('authUser').missions}" var="mission">
            <tr>
                <td>${mission.activity.name}</td>
                <td>${mission.startTime}</td>
                <td>${mission.endTime}</td>
                <td>${mission.state.toString()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
