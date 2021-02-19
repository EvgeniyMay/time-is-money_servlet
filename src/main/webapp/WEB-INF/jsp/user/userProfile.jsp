<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>My Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main class="container">
        <div class="row">
            <div class="col text-center p-3">
                <h2>Hello, ${sessionScope.get("authUser").login}</h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-8 p-3">
                <h3>Missions</h3>

                <div class="d-grid gap-2">
                    <a class="btn btn-dark" href="<c:url value="/app/mission/offer"/>">Offer mission</a>
                </div>

                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger my-3" role="alert">${requestScope.error}</div>
                </c:if>

                <h4>Active</h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Activity</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.activeMissions}" var="mission">
                        <tr>
                            <td>${mission.activity.name}</td>
                            <td>${mission.startTime}</td>
                            <td>${mission.endTime}</td>
                            <td>
                                <form action="<c:url value="/app/mission/pass"/>" method="POST">
                                    <input type="hidden" name="missionId" value="${mission.id}">
                                    <input class="btn btn-light" type="submit" value="Pass">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr/>

                <h4>Offered</h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Activity</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.offeredMissions}" var="mission">
                            <tr>
                                <td>${mission.activity.name}</td>
                                <td>${mission.startTime}</td>
                                <td>${mission.endTime}</td>
                                <td>
                                    <form action="<c:url value="/app/mission/cancel"/>" method="POST">
                                        <input type="hidden" name="missionId" value="${mission.id}">
                                        <input class="btn btn-light" type="submit" value="Cancel">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h4>Passed</h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Activity</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.passedMissions}" var="mission">
                            <tr>
                                <td>${mission.activity.name}</td>
                                <td>${mission.startTime}</td>
                                <td>${mission.endTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr/>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
