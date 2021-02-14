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

    <c:if test="${sessionScope.authUser.role != 'ADMIN'}">
        <a href="<c:url value="/app/mission/offer"/>">Offer mission</a>
    </c:if>

    <table>
        <c:forEach items="${sessionScope.get('authUser').missions}" var="mission">
            <tr>
                <td>${mission.activity.name}</td>
                <td>${mission.startTime}</td>
                <td>${mission.endTime}</td>
                <td>${mission.state.toString()}</td>
                <td>
                    <form action="<c:url value="/app/mission/pass"/>" method="POST">
                        <input type="hidden" name="missionId" value="${mission.id}">
                        <input type="submit" value="Pass">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
