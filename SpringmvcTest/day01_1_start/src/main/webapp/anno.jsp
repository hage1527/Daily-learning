<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/30
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用注解</title>
</head>
<body>
   <a href="anno/requestParam?user=haha">requestParam</a>
   <br>

   <form action="anno/requestBody" method="post">
       用户姓名：<input type="text" name="username" /><br/>
       用户年龄：<input type="text" name="age" /><br/>
       <input type="submit" value="提交" />
   </form>

   <a href="anno/testPathVariable/10">testPathVariable</a>
   <br>
   <a href="anno/testRequestHeader">testRequestHeader</a>
   <br>
   <a href="anno/testCookieValue">CookieValue</a>
   <br>

   <form action="anno/testModelAttribute" method="post">
       用户姓名：<input type="text" name="uname" /><br/>
       用户年龄：<input type="text" name="age" /><br/>
       <input type="submit" value="提交" />
   </form>

   <a href="anno/testSessionAttributes">testSessionAttributes</a>
   <a href="anno/getSessionAttributes">getSessionAttributes</a>
   <a href="anno/delSessionAttributes">delSessionAttributes</a>

</body>
</html>
