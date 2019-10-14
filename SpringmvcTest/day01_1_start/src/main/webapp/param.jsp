<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/30
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 请求参数绑定 --%>

<%--1.基本数据类型--%>
<a href="param/testParam?username=hage&password=1234">基本类型数据绑定</a>

<%-- 2.user实体类型绑定 --%>
<h3>javabean的封装</h3>
<form action="param/saveUser">
    姓名：<input type="text" name="name"/><br/>
    年龄：<input type="text" name="age"/><br/>
    生日：<input type="text" name="date"/><br/>
    <input type="submit" value="提交"/>
</form>
<%-- 3.Account实体类型绑定(包含user --%>
<h3>Account实体类型绑定(包含user)</h3>
<form action="param/saveAccount">
    余额：<input type="text" name="money"/><br/>
    姓名：<input type="text" name="user.name"/><br/>
    年龄：<input type="text" name="user.age"/><br/>
    生日：<input type="text" name="user.date"/><br/>
    <input type="submit" value="提交"/>
</form>
<%-- 3.Account实体类型绑定(包含user --%>
<h3>Account实体类型绑定(包含list和map)</h3>
<form action="param/saveListAndMap">
    余额：<input type="text" name="money"/><br/>
    <%--   list  --%>
    姓名：<input type="text" name="list[0].name"/><br/>
    年龄：<input type="text" name="list[0].age"/><br/>
    生日：<input type="text" name="list[0].date"/><br/>
    姓名：<input type="text" name="list[1].name"/><br/>
    年龄：<input type="text" name="list[1].age"/><br/>
    生日：<input type="text" name="list[1].date"/><br/>
    <%--   map  --%>
    姓名：<input type="text" name="map['one'].name"/><br/>
    年龄：<input type="text" name="map['one'].age"/><br/>
    生日：<input type="text" name="map['one'].date"/><br/>
    姓名：<input type="text" name="map['two'].name"/><br/>
    年龄：<input type="text" name="map['two'].age"/><br/>
    生日：<input type="text" name="map['two'].date"/><br/>
    <input type="submit" value="提交"/>
</form>


</body>
</html>
