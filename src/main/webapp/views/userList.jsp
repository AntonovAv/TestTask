<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title></title>
  <link href="../css/style.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Список пользователей</h1>
  <table class="userTable">
    <tr>
      <th style="width: 100px">
        First Name
      </th>
      <th>
        Last Name
      </th>
      <th>
        Email
      </th>
      <th>
        User Name
      </th>
      <th>
        Birthday
      </th>
      <th>
        Group
      </th>
    </tr>
    <c:forEach var="user" items="${sessionScope.users}">
      <tr>
        <td>
          <c:out value="${user.firstName}"/>
        </td>
        <td>
          <c:out value="${user.lastName}"/>
        </td>
        <td>
          <c:out value="${user.email}"/>
        </td>
        <td>
          <c:out value="${user.userName}"/>
        </td>
        <td>
          <c:out value="${user.birthday}"/>
        </td>
        <td>
          <c:out value="${user.group.type}"/>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>