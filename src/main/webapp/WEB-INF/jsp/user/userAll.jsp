<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table>
        <c:forEach items="#{requestScope.users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>
                    <table>
                        <c:forEach items="${user.missions}" var="mission">
                            <tr>

                                <td>${mission.activity.name}</td>
                                <td>${mission.activity.description}</td>
                                <td>${mission.startTime}</td>
                                <td>${mission.endTime}</td>
                                <td>${mission.state}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
