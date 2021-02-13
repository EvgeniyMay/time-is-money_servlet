<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jsp"%>
    </header>
    <table>
        <c:forEach items="#{requestScope.users}" var="user">
            <tr>
                <td>${user.login}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
