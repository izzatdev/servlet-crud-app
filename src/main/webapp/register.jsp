<%--
  Created by IntelliJ IDEA.
  User: itoshpulatov
  Date: 5/31/2023
  Time: 6:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <a href="index.jsp">Main Menu</a>
</head>
<body>
<div style="margin: 100px 500px">
    <form action="/basic-servlet" method="post">
        <input class="form-control" required type="text" placeholder="Full Name" name="fullName">
        <br>
        <input class="form-control" required type="tel" placeholder="Phone Number" name="phoneNumber">
        <br>
        <input class="form-control" required type="date" placeholder="Birthday Date" name="birthday">
        <br>
        <input class="form-control" required type="password" placeholder="Password" name="password">
        <br>
        <input class="form-control" required type="password" placeholder="again password" name="rePassword">
        <br>
        <button class="btn btn-primary" type="submit">Register</button>
    </form>
</div>
</body>
</html>
