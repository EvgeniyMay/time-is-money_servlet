<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/app/login" method="POST">
        <input type="text" name="login">
        <input type="password" name="password">
        <input type="submit" value="Login">
    </form>
</body>
</html>
