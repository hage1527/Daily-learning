<%@ page import="com.sun.jmx.snmp.agent.SnmpErrorHandlerAgent" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/31
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String name = request.getParameter("uname");
    String passwd = request.getParameter("upwd");
   response.sendRedirect("a.jsp");
%>

</body>
</html>
