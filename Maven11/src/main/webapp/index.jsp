<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form action="/login" method="post">
    Login :<input type="text" name="username"/>
    Password :<input type="password" name="pass"/>
    <input type="submit" name="enter" value="Sign In">
</form>

<c:set var="myName" value="Alex"/>
<c:out value="${myName}"/>
<h1>${myName}</h1>
<h4>user:guest;pass:10iut</h4>
</body>
</html>
