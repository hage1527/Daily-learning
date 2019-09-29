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
    if (name.equals("hage")&&passwd.equals("1234"))
    {
//        //request的请求转发 保留数据 请求一次 地址栏不变
//        request.getRequestDispatcher("success.jsp").forward(request, response);
          //reponse的重定位 不保留数据 请求两次 地址栏要改变
          response.sendRedirect("success.jsp");
    }
    else
        out.println("用户名或密码错误");
%>

</body>
</html>
