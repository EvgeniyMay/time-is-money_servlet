<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Offer Mission</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
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
    <form action="<c:url value="/app/mission/offer"/>" method="POST">
        <table>
            <tr>
                <td>Mission: </td>
                <td>
                    <select name="activityId">
                        <c:forEach items="${requestScope.activities}" var="activity">
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
