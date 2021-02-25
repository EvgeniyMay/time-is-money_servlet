<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Archive Activity</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-3 text-center">
                <h2 class="my-3">
                    <fmt:message key="activity.archive.header" />
                </h2>

                <p><fmt:message key="activity.archive.message" /> <span><b>${requestScope.name}</b></span> ?</p>

                <div class="row">
                    <div class="col-6">
                        <form action="<c:url value="/app/activity/archive"/>" method="POST">
                            <input type="hidden" name="id" value="${requestScope.id}">
                            <fmt:message key="activity.action.archive" var="archiveActivity" />
                            <input class="btn btn-dark" type="submit" value="${archiveActivity}">
                        </form>
                    </div>
                    <div class="col-6">
                        <form action="<c:url value="/app/activity/active"/>" method="GET">
                            <fmt:message key="nav.main" var="toMain" />
                            <input class="btn btn-dark" type="submit" value="${toMain}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
