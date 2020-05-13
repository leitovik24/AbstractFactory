<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>
<h3>Edit User</h3>
<form action="/admin/edit" method="post">
    <label>New name</label><br>
    <input type="text" name="name"  /><br><br>
    <label>New email</label><br>
    <input type="text" name="email"  /><br><br>
    <label>New role</label><br>
    <input type="text" name="role"> <br>
    <input type="submit" value="Send" />
</form>
</body>
</html>
