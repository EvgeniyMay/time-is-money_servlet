<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Create Mission</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <div>
        <ul>
            <c:forEach items="${requestScope.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <form action="<c:url value="/app/mission/add"/>" method="POST">
        <table>
            <tr>
                <td>User: </td>
                <td>
                    <select name="userId">
                        <c:forEach items="${requestScope.usersAndActivities.users}" var="user">
                            <option value="${user.id}">${user.login}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Mission: </td>
                <td>
                    <select name="activityId">
                        <c:forEach items="${requestScope.usersAndActivities.activities}" var="activity">
                            <option value="${activity.id}">${activity.name} | ${activity.description}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Start time:</td>
                <td><input name="startTime" type="datetime-local"></td>
            </tr>
            <tr>
                <td>End time:</td>
                <td><input name="endTime" type="datetime-local"></td>
            </tr>
        </table>
        <input type="submit" value="Create">
    </form>
</body>
</html>
