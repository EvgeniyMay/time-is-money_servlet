<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
    <h2>Are you sure you want logout ?</h2>
    <form action="/app/logout" method="POST">
        <input type="submit" value="Yes">
    </form>
    <form action="/" method="GET">
        <input type="submit" value="No">
    </form>
</body>
</html>
