<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Forma Registratsii</title>

</head>

<body>

<h1>Java Mentor!</h1>

<h2>БД пользователей</h2><br/>

<form method="POST" action="/admin/add">
    <br>
    Login: <input type="text" name="name"> <br>
    <br>
    Email: <input type="text" name="email"> <br>
    <br>
    Role <input type="text" name="role"> <br>
    <br>
    <button type="submit">Add user</button>
    <input type="hidden" name="trigger" value="1">
</form>


<table  border="1" >
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Email</td>
        <td>Role</td>
        <td>Action</td></tr>
    <c:set var="users" scope="request" value="${users}"/>
    <c:forEach items="${users}" var="user">
        <tr><td>${user.id}</td><td>${user.name}</td><td>${user.email}</td><td>${user.role}</td>
            <td>
                <form action="/admin/delete" method="post">
                    <button name="id" value="${user.id}" type="submit" onclick='this.form.submit()'>Delete</button>
                </form>
                <form action="/admin/edit" method="get">
                    <button name = "id" value = "${user.id}" type="submit" onclick='this.form.submit()'>Edit</button>
                </form>
            </td></tr>
    </c:forEach>
    <tr>

    </tr>

<%--    </c:forEach>--%>
</table>


</body>
</html>
