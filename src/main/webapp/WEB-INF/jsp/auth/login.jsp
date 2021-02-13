<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jsp"%>
    </header>
    <h2>Login page</h2>
    <div>
        <p>${requestScope.error}</p>
    </div>
    <form action="<c:url value="/app/login"/>" method="POST">
        <table>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <input type="submit" value="Login">
    </form>
</body>
</html>
