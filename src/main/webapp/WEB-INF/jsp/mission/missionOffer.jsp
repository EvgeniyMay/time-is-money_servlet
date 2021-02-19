<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Offer Mission</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <c:forEach items="${requestScope.errors}" var="error">
                        <div class="alert alert-danger my-3" role="alert">${error}</div>
                </c:forEach>

                <c:if test="${requestScope.activities.size() == 0}">
                    <div class="alert alert-danger my-3" role="alert">No active activities</div>
                </c:if>
                <c:if test="${requestScope.activities.size() !=0}">
                    <form action="<c:url value="/app/mission/offer"/>" method="POST">
                        <div class="my-3">
                            <label for="activity">Activity</label>
                            <select class="form-select" id="activity" name="activityId">
                                <c:forEach items="${requestScope.activities}" var="activity">
                                    <option value="${activity.id}">${activity.name} | ${activity.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-2">
                            <label for="startTime" class="form-label">Start time</label>
                            <input type="datetime-local" class="form-control" name="startTime" id="startTime">
                        </div>
                        <div class="mb-3">
                            <label for="endTime" class="form-label">End time</label>
                            <input type="datetime-local" class="form-control" name="endTime" id="endTime">
                        </div>
                        <div class="d-grid gap-2 mt-3">
                            <button class="btn btn-dark" type="submit">Offer</button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
