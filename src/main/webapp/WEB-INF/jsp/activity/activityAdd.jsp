<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Activity</title>
</head>
<body>
    <h1>New activity form</h1>
    <form action="/app/activity/add" method="POST">
        <label>
            Name:
            <input type="text" name="name">
        </label>
        <label>
            Description:
            <input type="text" name="description">
        </label>
        <input type="submit" value="Add">
    </form>
</body>
</html>