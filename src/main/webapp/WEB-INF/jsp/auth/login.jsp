<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tim" uri="time-is-money"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Login</title>
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
                <img class="my-5" src="${pageContext.request.contextPath}/img/logo.png" alt="logo" height="250">
                <h2>Please, login</h2>

                <c:if test="${requestScope.error != null}">
                    <tim:danger-error>
                        ${requestScope.error}
                    </tim:danger-error>
                </c:if>

                <form action="<c:url value="/app/login"/>" method="POST">
                   <div class="form-floating mt-3">
                        <input class="form-control" id="login" type="text" name="login" placeholder="Login">
                        <label for="login">Login</label>
                    </div>
                    <div class="form-floating mt-3">
                        <input class="form-control" id="password" type="password" name="password" placeholder="Password">
                        <label for="password">Password</label>
                    </div>
                    <div class="d-grid gap-2 mt-3">
                        <button class="btn btn-dark" type="submit">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
