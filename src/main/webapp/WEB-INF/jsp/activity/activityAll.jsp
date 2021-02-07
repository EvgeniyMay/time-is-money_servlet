<%@ page import="com.myLearning.timeIsMoney.entity.Activity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activities</title>
</head>
<body>
    <h1>Activities</h1>
    <ul>
        <%
            for(Activity a : (List<Activity>) request.getAttribute("activities")) {
                out.println("<li>" + a.getName() + "</li>");
            }
        %>
    </ul>
</body>
</html>
