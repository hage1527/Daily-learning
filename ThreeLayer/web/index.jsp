<%@ page import="java.util.List" %>
<%@ page import="com.threelayer.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/7
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("tr:odd").css("background-color", "lightgray");
        });

    </script>
</head>
<body>

<%
    request.setCharacterEncoding("utf-8");

%>
<table border="1px">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>地址</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student student : students) {
    %>
    <tr>
        <td><a href="queryStudentInfoBySnoServlet?sno=<%=student.getSno()%>"><%=student.getSno()%>
        </td>
        <td><%=student.getSname()%>
        </td>
        <td><%=student.getSage()%>
        </td>
        <td><%=student.getSaddress()%>
        </td>
        <td><a href="deleteStudentServlet?sno=<%=student.getSno()%>">删除</a>
        </td>
    </tr>
    <%
        }

    %>
    <tr>
        <td><a href="addStudent.jsp">增加</a></td>
    </tr>
</table>

</body>
</html>
