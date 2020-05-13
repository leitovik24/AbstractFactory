<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staartuem nahoj</title>
</head>
<body>
<h1>Java Mentor!</h1>
<form method="POST" action="/login">
    <br>
    Login: <input type="text" name="name"> <br>
    <br>
    Email: <input type="text" name="email"> <br>
    <br>



    <br>
    <button type="submit">Login user</button>
    <input type="hidden" name="trigger" value="1">
</form>


</body>
</html>
