<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/27
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <%
       //设置编码
       request.setCharacterEncoding("utf-8");
       String name=request.getParameter("uname");
       String passwd=request.getParameter("upwd");
       String age=request.getParameter("uage");
       String [] uhobbies = request.getParameterValues("uhobbies");
   %>
    注册信息如下
   用户名：<%=name%><br/>
   密 码：<%=passwd%><br/>

</body>
</html>
