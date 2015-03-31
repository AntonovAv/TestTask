<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Главное меню</h1>
    <div class="button_field">
        <b>Create User</b><br>
        <a href="/forwardToCreate"><input type = "button" class = "button" value = "Create" /></a><br>
        <b>User List</b><br>
        <a href="/userListServlet"><input type = "button" class = "button" value = "List" /></a><br>
    </div>
</body>
</html>