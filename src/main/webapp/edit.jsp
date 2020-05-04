<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
<h3>Edit User</h3>
<form action="/edit" method="post">
    <label>New name</label><br>
    <input name="name"  /><br><br>
    <label>New email</label><br>
    <input name="email"  /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>
