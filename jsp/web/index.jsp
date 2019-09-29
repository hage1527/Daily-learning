<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/27
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hage</title>
</head>
<body>

  <%!
      String  name = "hage";
      public void getName(){
          name = "sunbowen";
      }
  %>
  <%
      out.print("hello局部 "+name+"</br>");
      getName();
  %>
  <%="hello局部 "+name%>

</body>
</html>
