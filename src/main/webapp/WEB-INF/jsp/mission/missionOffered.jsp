<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tim" uri="time-is-money" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Offered Missions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main class="container">
        <div class="row justify-content-center">
            <div class="col-8">

                <c:if test="${requestScope.error != null}">
                    <tim:danger-error>
                        ${requestScope.error}
                    </tim:danger-error>
                </c:if>

                <c:if test="${requestScope.addResult != null}">
                    <div class="alert alert-danger" role="alert">
                            ${requestScope.addResult}
                    </div>
                </c:if>

                <h2 class="my-3">Offered missions</h2>

                <c:if test="${requestScope.page_count == 0}">
                    <div class="alert alert-dark my-3" role="alert">No offered missions</div>
                </c:if>

                <c:if test="${requestScope.page_count > 0}">
                    <c:if test="${requestScope.page_count > 1}">
                        <nav class="my-3">
                            <ul class="pagination justify-content-center">
                                <c:forEach begin="0" end="${requestScope.page_count - 1}" step="1" var="i">
                                    <li class="page-item">
                                        <a class="page-link text-dark" href="<c:url value="/app/mission/offered?cur_page=${i}"/>">${i + 1}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </c:if>
                    <table class="table table-dark table-striped">
                        <thead>
                            <tr>
                                <th>
                                    <form class="no-padding">
                                        <input type="hidden" name="sort_field" value="${'user_id'}">
                                        <input class="btn btn-dark" type="submit" value="${'User'}">
                                    </form>
                                </th>
                                <th>
                                    <form class="no-padding">
                                        <input type="hidden" name="sort_field" value="${'activity_id'}">
                                        <input class="btn btn-dark" type="submit" value="${'Activity'}">
                                    </form>
                                </th>
                                <th>
                                    <form class="no-padding">
                                        <input type="hidden" name="sort_field" value="${'start_time'}">
                                        <input class="btn btn-dark" type="submit" value="${'Start Time'}">
                                    </form>
                                </th>
                                <th>
                                    <form class="no-padding">
                                        <input type="hidden" name="sort_field" value="${'end_time'}">
                                        <input class="btn btn-dark" type="submit" value="${'End Time'}">
                                    </form>
                                </th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <c:forEach items="${requestScope.missions}" var="mission">
                            <tr>
                                <td>${mission.user.login}</td>
                                <td>${mission.activity.name}</td>
                                <td>
                                    <fmt:parseDate value="${mission.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartTime" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedStartTime}"/>
                                </td>
                                <td>
                                    <fmt:parseDate value="${mission.endTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndTime" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedEndTime}"/>
                                </td>
                                <td>
                                    <form class="no-padding" action="${pageContext.request.contextPath}/app/mission/accept" method="POST">
                                        <input type="hidden" name="missionId" value="${mission.id}">
                                        <input class="btn btn-light" type="submit" value="Accept">
                                    </form>
                                </td>
                                <td>
                                    <form class="no-padding" action="${pageContext.request.contextPath}/app/mission/cancel" method="POST">
                                        <input type="hidden" name="missionId" value="${mission.id}">
                                        <input class="btn btn-light" type="submit" value="Deny">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
