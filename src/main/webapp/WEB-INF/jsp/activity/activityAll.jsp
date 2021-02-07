
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Activities</title>
</head>
<body>
    <h1>Activities</h1>
    <ul>
       <c:forEach items="${requestScope.activities}" var="activity">
           <li>${activity.name}</li>
       </c:forEach>
    </ul>
</body>
</html>
