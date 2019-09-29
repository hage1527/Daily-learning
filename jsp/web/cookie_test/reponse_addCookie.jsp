<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/31
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <%
       Cookie cookie1 = new Cookie("name", "hage");
       Cookie cookie2 = new Cookie("passwd", "1234");
       response.addCookie(cookie1);
       response.addCookie(cookie2);
       response.sendRedirect("result.jsp");
   %>

</body>
</html>
