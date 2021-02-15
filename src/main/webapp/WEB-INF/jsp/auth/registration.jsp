<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <h2>Registration</h2>
    <div>
        <ul>
            <c:forEach items="${requestScope.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <form action="<c:url value="/app/registration"/>" method="post">
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
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
