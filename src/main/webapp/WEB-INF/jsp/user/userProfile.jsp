<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>My Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h2>Hello, ${sessionScope.get("authUser").login}</h2>
    <h3>Missions: </h3>

    <a href="<c:url value="/app/mission/offer"/>">Offer mission</a>

    <c:if test="${requestScope.error != null}">
        <div>${requestScope.error}</div>
    </c:if>
    <h4>Active:</h4>
    <table>
        <c:forEach items="${requestScope.activeMissions}" var="mission">
            <tr>
                <td>${mission.activity.name}</td>
                <td>${mission.startTime}</td>
                <td>${mission.endTime}</td>
                <td>
                    <form action="<c:url value="/app/mission/pass"/>" method="POST">
                        <input type="hidden" name="missionId" value="${mission.id}">
                        <input type="submit" value="Pass">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr/>

    <h4>Passed:</h4>
    <table>
        <c:forEach items="${requestScope.passedMissions}" var="mission">
            <tr>
                <td>${mission.activity.name}</td>
                <td>${mission.startTime}</td>
                <td>${mission.endTime}</td>
            </tr>
        </c:forEach>
    </table>
    <hr/>

    <h4>Offered:</h4>
    <table>
        <c:forEach items="${requestScope.offeredMissions}" var="mission">
            <tr>
                <td>${mission.activity.name}</td>
                <td>${mission.startTime}</td>
                <td>${mission.endTime}</td>
                <td>
                    <form action="<c:url value="/app/mission/cancel"/>" method="POST">
                        <input type="hidden" name="missionId" value="${mission.id}">
                        <input type="submit" value="Cancel">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
