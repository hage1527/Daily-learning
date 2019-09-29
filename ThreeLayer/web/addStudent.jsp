<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/8
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>信息查询</title>
</head>
<body>
    <form action="addStudentServlet">
        学号：<input type="text" name="sno" > <br/>
        姓名：<input type="text" name="sname" > <br/>
        年龄：<input type="text" name="sage" > <br/>
        地址：<input type="text" name="saddress" > <br/>
        <input type="submit" value="增加学生信息">
    </form>

</body>
</html>
