<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Offered Missions</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h2>Offers</h2>
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
                <td>
                    <form action="${pageContext.request.contextPath}/app/mission/accept" method="POST">
                        <input type="hidden" name="missionId" value="${mission.id}">
                        <input type="submit" value="Accept">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/app/mission/cancel" method="POST">
                        <input type="hidden" name="missionId" value="${mission.id}">
                        <input type="submit" value="Deny">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
