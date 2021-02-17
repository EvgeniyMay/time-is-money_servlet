<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Completed missions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<header>
    <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
</header>
<h2>Completed missions:</h2>
<c:if test="${requestScope.page_count == 0}">
    <div> No active missions</div>
</c:if>
<c:if test="${requestScope.page_count > 0}">
    <nav>
        <c:forEach begin="0" end="${requestScope.page_count - 1}" step="1" var="i">
            <form>
                <input type="hidden" name="cur_page" value="${i}">
                <input type="hidden" name="sort_field" value="${requestScope.sort_field}">
                <input type="submit" value="${i + 1}">
            </form>
        </c:forEach>
    </nav>
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
</c:if>
</body>
</html>
