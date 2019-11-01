<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/testFindAll">findAll</a>

    <form action="account/testSave" method="post">
        姓名：<input type="text" name="name">
        金额：<input type="text" name="money">
        <input type="submit" value="注册">
    </form>
</body>
</html>
