<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Missions</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <a href="<c:url value="/app/mission/add"/>">Create new</a>
    <nav>
        <a href="<c:url value="/app/mission/active"/>">Active</a>
        <a href="<c:url value="/app/mission/offered"/>">Offered</a>
    </nav>
</body>
</html>
