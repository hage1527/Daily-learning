<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/31
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie : cookies) {
            out.println(cookie.getName()+":"+cookie.getValue()+"<br/>");
        }
    %>

</body>
</html>
