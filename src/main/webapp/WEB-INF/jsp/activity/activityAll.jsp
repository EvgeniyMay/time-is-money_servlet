<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Activities</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h1>Activities</h1>
    <table>
        <tr>
            <th>Name:</th>
            <th>Description:</th>
        </tr>
        <c:forEach items="${requestScope.activities}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <td>${activity.description}</td>
                <td>
                    <form action="<c:url value="/app/activity/delete"/>" method="GET">
                        <input type="hidden" name="id" value="${activity.id}">
                        <input type="hidden" name="name" value="${activity.name}">
                        <input type="submit" value="Delete">
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
    <a href="${pageContext.request.contextPath}/app/activity/add">Create new</a>
</body>
</html>
