<%@ page import="com.example.demo1.entity.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: itoshpulatov
  Date: 5/31/2023
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <a href="index.jsp">Main Menu</a>
</head>
<body>
<div style="margin: 100px 400px">
    <table class="table table-bordered table-hover">
        <tr>
            <th>id</th>
            <th>Full Name</th>
            <th>PhoneNumber</th>
            <th>Birthday</th>
        </tr>
    <%
        ArrayList<Student> students = (ArrayList<Student>) request.getSession().getAttribute("list");
        if (students == null) {
            return;
        }
        for (Student student : students) {
    %>
        <tr>
            <td><%=student.getId()%>
            </td>
            <td><%=student.getFullName()%>
            </td>
            <td><%=student.getPhoneNumber()%>
            </td>
            <td><%=student.getBirthday()%>
            </td>
        </tr>
            <%}%>
</div>
</body>
</html>
