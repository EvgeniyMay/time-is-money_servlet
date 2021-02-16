<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Activities</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h1>Active Activities</h1>
    <a href="${pageContext.request.contextPath}/app/activity/add">Create new</a>
    <table>
        <tr>
            <th>Name:</th>
            <th>Description:</th>
        </tr>
        <nav>
            <c:forEach begin="0" end="${requestScope.page_count - 1}" step="1" var="i">
                <a href="<c:url value="/app/activity/active?cur_page=${i}"/>">${i + 1}</a>
            </c:forEach>
        </nav>
        <c:forEach items="${requestScope.activities}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <td>${activity.description}</td>
                <td>
                    <form action="<c:url value="/app/activity/archive"/>" method="GET">
                        <input type="hidden" name="id" value="${activity.id}">
                        <input type="hidden" name="name" value="${activity.name}">
                        <input type="submit" value="Archive">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/app/activity/edit" method="GET">
                        <input type="hidden" name="id" value="${activity.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
