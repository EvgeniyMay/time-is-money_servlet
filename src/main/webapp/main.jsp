<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.messages"/>

<html>
<head>
    <title>Welcome page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<body>
    <header>
        <%@include file="/WEB-INF/jsp/fragments/header.jspf"%>
    </header>
    <main>
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-5 text-center">
                    <h2 class="my-4">
                        <fmt:message key="text.welcome" />
                    </h2>
                    <c:if test="${sessionScope.authUser == null}">
                        <div class="col-auto">
                            <a class="btn btn-light btn-outline-dark" href="${pageContext.request.contextPath}/app/login">
                                <fmt:message key="nav.account.login" />
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.authUser != null}">
                        <div class="col-auto">
                            <a class="btn btn-dark" href="${pageContext.request.contextPath}/app/profile">
                                <fmt:message key="nav.account.profile" />
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
