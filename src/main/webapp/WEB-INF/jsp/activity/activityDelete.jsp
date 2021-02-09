<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Activity</title>
</head>
<body>
    <h1>Delete page</h1>
    <h2>Are you sure you want to delete <span>${requestScope.name}</span> ?</h2>
    <form action="/app/activity/delete" method="POST">
        <input type="hidden" name="id" value="${requestScope.id}">
        <input type="submit" value="Delete">
    </form>
</body>
</html>
