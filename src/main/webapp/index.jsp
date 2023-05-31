<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        Main
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div style="margin: 200px auto ;width: 600px">
    <h1><%= "Demo - Servlet" %>
    </h1>
    <br/>
    <a href="register.jsp">REGISTER</a>
    <br> <br>
    <a href="/basic-servlet">SHOW</a>
    <br> <br>
    <a href="/student-info-servlet">SHOW-INFO</a>
    <br> <br>
    <a href="update.jsp">UPDATE</a>
    <br> <br>
    <a href="delete.jsp">DELETE</a>
</div>
</body>
</html>