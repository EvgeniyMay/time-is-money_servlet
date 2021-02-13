<div class="header-content">
    <div class="localization">
        <nav>
            <a href="?lang=en"><fmt:message key="language.en" /></a>
            <a href="?lang=ru"><fmt:message key="language.ru" /></a>
        </nav>
    </div>
    <div class="profile">
        <c:if test="${sessionScope.authUser == null}">
            <nav>
                <a href="<c:url value="/app/registration"/>"><fmt:message key="nav.signup" /></a>
                <a href="<c:url value="/app/login"/>"><fmt:message key="nav.login" /></a>
            </nav>
        </c:if>
        <c:if test="${sessionScope.authUser != null}">
            <div>
                <nav>
                    <a href="<c:url value="/app/profile"/>"><fmt:message key="nav.profile" /></a>
                    <a href="<c:url value="/app/logout"/>"><fmt:message key="nav.logout" /></a>
                </nav>
            </div>
        </c:if>
    </div>
    <div class="menu">
        <c:if test="${sessionScope.authUser != null}">
            <nav>
                <a href="<c:url value="/app/"/>"><fmt:message key="nav.main" /></a>
                <c:if test="${sessionScope.authUser.role == 'ADMIN'}">
                    <a href="<c:url value="/app/activity"/>"><fmt:message key="nav.activities" /></a>
                    <a href="<c:url value="/app/user"/>"><fmt:message key="nav.users" /></a>
                    <a href="<c:url value="/app/missions"/>"><fmt:message key="nav.missions" /></a>
                </c:if>
            </nav>
        </c:if>
    </div>
</div>