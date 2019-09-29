<%@ page import="com.threelayer.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/8
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>学生详细信息</title>
</head>
<body>
    <%
        Student student=(Student) request.getAttribute("student");
    %>
    <form action="alterStudentServlet" method="post">
        学号：<input type="text" name="sno"  value="<%=student.getSno()%>" ><br/>
        姓名：<input type="text" name="sname" value="<%=student.getSname()%>" ><br/>
        年龄：<input type="text" name="sage" value="<%=student.getSage()%>" ><br/>
        地址：<input type="text" name="saddress" value="<%=student.getSaddress()%>" ><br/>
        <input type="submit" value="修改信息" >
    </form>

</body>
</html>
