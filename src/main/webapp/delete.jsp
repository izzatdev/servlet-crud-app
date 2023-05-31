<%--
  Created by IntelliJ IDEA.
  User: itoshpulatov
  Date: 5/31/2023
  Time: 6:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <a href="index.jsp">Main Menu</a>
</head>
<body>
<div style="margin: 100px 600px">
    <form action="/basic-servlet" method="delete">
        <input class="form-control" required type="number" placeholder="Id of student" name="id">
        <br>
        <button class="btn btn-primary"  type="submit">Delete</button>
    </form>
</div>
</body>
</html>
