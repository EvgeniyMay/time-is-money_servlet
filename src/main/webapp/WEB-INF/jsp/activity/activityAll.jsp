<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Activities</title>
</head>
<body>
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
                    <form action="/app/activity/delete" method="GET">
                        <input type="hidden" name="name" value="${activity.name}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
                <td>
                    <form action="/app/activity/edit" method="GET">
                        <input type="hidden" name="id" value="${activity.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/app/activity/add">Create new</a>
</body>
</html>
