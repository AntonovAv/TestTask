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

<div class="TTWForm-container">

  <h1>Создание нового пользователя</h1>


  <form action="/createServlet" class="TTWForm" method="post" novalidate="">


    <div id="field1-container" class="field f_50">
      <label for="field1">
        First Name
      </label>
      <input type="text" name="fName" id="field1" required="required">
    </div>


    <div id="field2-container" class="field f_50">
      <label for="field2">
        Last Name
      </label>
      <input type="text" name="lName" id="field2" required="required">
    </div>


    <div id="field3-container" class="field f_50">
      <label for="field3">
        User Name
      </label>
      <input type="text" name="uName" id="field3" required="required">
    </div>


    <div id="field4-container" class="field f_50">
      <label for="field4">
        Password
      </label>
      <input type="password" name="uPassword" id="field4" required="required">
    </div>


    <div id="field5-container" class="field f_50">
      <label for="field5">
        Email
      </label>
      <input type="text" name="uEmail" id="field5" required="required">
    </div>


    <div id="field6-container" class="field f_50">
      <label for="field6">
        Birthday
      </label>
      <input type="text" name="uBirthday" id="field6" required="required">
    </div>


    <div id="field7-container" class="field f_50">
      <label for="field7">
        Zip
      </label>
      <input type="text" name="aZip" id="field7" required="required">
    </div>


    <div id="field8-container" class="field f_50">
      <label for="field8">
        Country
      </label>
      <input type="text" name="aCountry" id="field8" required="required">
    </div>


    <div id="field9-container" class="field f_50">
      <label for="field9">
        City
      </label>
      <input type="text" name="aCity" id="field9" required="required">
    </div>


    <div id="field10-container" class="field f_50">
      <label for="field10">
        District
      </label>
      <input type="text" name="aDistrict" id="field10" required="required">
    </div>


    <div id="field11-container" class="field f_50">
      <label for="field11">
        Street
      </label>
      <input type="text" name="aStreet" id="field11" required="required">
    </div>


    <div id="field12-container" class="field f_50">
      <label for="field12">
        Group
      </label>
      <select name="uGroup" id="field12" required="required">
        <c:forEach var="gr" items="${sessionScope.groups}">
          <option id="${gr.id}" value="${gr.id}">
            <c:out value="${gr.type}"/>
          </option>
        </c:forEach>
      </select>
    </div>


    <div id="form-submit" class="field f_100 clearfix submit">
      <input type="submit" value="Create">
    </div>
  </form>
  <c:if test="${sessionScope.err != null}">
    <c:out value="${sessionScope.err}"/>
  </c:if>
</div>

</body>
</html>