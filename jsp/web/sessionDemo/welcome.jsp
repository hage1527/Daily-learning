<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/1
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String name=(String)session.getAttribute("uname");
    out.println(name);
%>


</body>
</html>
