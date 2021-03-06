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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main class="container">
        <div class="row">
            <div class="col text-center">
                <h2 class="my-2">
                    ${sessionScope.get("authUser").login}
                </h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-8 p-3">
                <h3 class="mb-2">
                    <fmt:message key="profile.mission" />
                </h3>

                <div class="d-grid gap-2">
                    <a class="btn btn-dark" href="<c:url value="/app/mission/offer"/>">
                        <fmt:message key="profile.action.mission.offer" />
                    </a>
                </div>

                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger my-3" role="alert">
                            ${requestScope.error}
                    </div>
                </c:if>

                <h4 class="mt-2">
                    <fmt:message key="profile.mission.active" />
                </h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.activity" var="activeMissionsTitle"/>
                                    <input type="hidden" name="sort_field" value="${'activity_id'}">
                                    <input class="btn btn-dark" type="submit" value="${activeMissionsTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.start" var="startTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'start_time'}">
                                    <input class="btn btn-dark" type="submit" value="${startTimeTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.end" var="endTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'end_time'}">
                                    <input class="btn btn-dark" type="submit" value="${endTimeTitle}">
                                </form>
                            </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.activeMissions}" var="mission">
                        <tr>
                            <td>${mission.activity.name}</td>
                            <td>
                                <fmt:parseDate value="${mission.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartTime" type="both"/>
                                <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedStartTime}"/>
                            </td>
                            <td>
                                <fmt:parseDate value="${mission.endTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndTime" type="both"/>
                                <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedEndTime}"/>
                            </td>
                            <td>
                                <form class="no-padding" action="${pageContext.request.contextPath}/app/mission/pass" method="POST">
                                    <input type="hidden" name="missionId" value="${mission.id}">
                                    <fmt:message key="profile.action.mission.pass" var="actionPass"/>
                                    <input class="btn btn-light" type="submit" value="${actionPass}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h4 class="mt-2">
                    <fmt:message key="profile.mission.offered" />
                </h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.activity" var="activeMissionsTitle"/>
                                    <input type="hidden" name="sort_field" value="${'activity_id'}">
                                    <input class="btn btn-dark" type="submit" value="${activeMissionsTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.start" var="startTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'start_time'}">
                                    <input class="btn btn-dark" type="submit" value="${startTimeTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.end" var="endTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'end_time'}">
                                    <input class="btn btn-dark" type="submit" value="${endTimeTitle}">
                                </form>
                            </th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.offeredMissions}" var="mission">
                            <tr>
                                <td>${mission.activity.name}</td>
                                <td>
                                    <fmt:parseDate value="${mission.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartTime" type="both"/>
                                    <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedStartTime}"/>
                                </td>
                                <td>
                                    <fmt:parseDate value="${mission.endTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndTime" type="both"/>
                                    <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedEndTime}"/>
                                </td>
                                <td>
                                    <form class="no-padding" action="<c:url value="/app/mission/cancel"/>" method="POST">
                                        <input type="hidden" name="missionId" value="${mission.id}">
                                        <fmt:message key="profile.action.mission.cancel" var="actionCancel"/>
                                        <input class="btn btn-light" type="submit" value="${actionCancel}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h4 class="mt-2">
                    <fmt:message key="profile.mission.passed" />
                </h4>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.activity" var="activeMissionsTitle"/>
                                    <input type="hidden" name="sort_field" value="${'activity_id'}">
                                    <input class="btn btn-dark" type="submit" value="${activeMissionsTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.start" var="startTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'start_time'}">
                                    <input class="btn btn-dark" type="submit" value="${startTimeTitle}">
                                </form>
                            </th>
                            <th>
                                <form class="no-padding">
                                    <fmt:message key="profile.mission.table.time.end" var="endTimeTitle"/>
                                    <input type="hidden" name="sort_field" value="${'end_time'}">
                                    <input class="btn btn-dark" type="submit" value="${endTimeTitle}">
                                </form>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.passedMissions}" var="mission">
                            <tr>
                                <td>${mission.activity.name}</td>
                                <td>
                                    <fmt:parseDate value="${mission.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartTime" type="both"/>
                                    <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedStartTime}"/>
                                </td>
                                <td>
                                    <fmt:parseDate value="${mission.endTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndTime" type="both"/>
                                    <fmt:formatDate pattern="MM/dd | HH:mm" value="${parsedEndTime}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr/>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
