<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div>
        <ul>
            <c:forEach items="${requestScope.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <form action="/app/registration" method="post">
        <input type="text" name="login">
        <input type="text" name="password">
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
