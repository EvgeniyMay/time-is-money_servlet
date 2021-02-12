<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Main</title>
</head>
<body>
    <nav>
        <a href="?lang=en"><fmt:message key="language.en" /></a>
        <a href="?lang=ru"><fmt:message key="language.ru" /></a>
    </nav>
    <h1>Main page</h1>
    <a href="/app/activity">Activity</a>
    <a href="/app/user">User</a>
    <a href="/app/registration">Sign up</a>
    <a href="/app/login">Login</a>
    <a href="/app/profile">My Profile</a>
    <a href="/app/logout">Logout</a>
    <h2><fmt:message key="welcome" /></h2>
</body>
</html>
