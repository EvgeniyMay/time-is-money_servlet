<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Activities</title>
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
        <div class="row justify-content-center">
            <div class="col-8">
                <h2 class="my-3">
                    <fmt:message key="activity.activities.archived" />
                </h2>

                <c:if test="${requestScope.page_count == 0}">
                    <div class="alert alert-danger my-3" role="alert">
                        <fmt:message key="activity.activities.archived.absent" />
                    </div>
                </c:if>

                <c:if test="${requestScope.page_count > 0}">
                    <nav class="my-3">
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="0" end="${requestScope.page_count - 1}" step="1" var="i">
                                <li class="page-item">
                                    <a class="page-link text-dark" href="<c:url value="/app/activity/archived?cur_page=${i}"/>">${i + 1}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                    <table class="table table-dark table-striped">
                        <thead>
                            <tr>
                                <th><fmt:message key="activity.table.name" /></th>
                                <th><fmt:message key="activity.table.description" /></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.activities}" var="activity">
                                <tr>
                                    <td>${activity.name}</td>
                                    <td>${activity.description}</td>
                                    <td>
                                        <form class="no-padding" action="<c:url value="/app/activity/activate"/>" method="POST">
                                            <input type="hidden" name="id" value="${activity.id}">
                                            <fmt:message key="activity.action.activate" var="activateActivity" />
                                            <input class="btn btn-outline-light" type="submit" value="${activateActivity}">
                                        </form>
                                    </td>
                                    <td>
                                        <form class="no-padding" action="${pageContext.request.contextPath}/app/activity/edit" method="GET">
                                            <input type="hidden" name="id" value="${activity.id}">
                                            <fmt:message key="activity.action.edit" var="editActivity" />
                                            <input class="btn btn-outline-light" type="submit" value="${editActivity}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
