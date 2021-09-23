<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop</title>
    <%@ include file="resources.jsp" %>
</head>
<body>
<div class="container">
    <form method="post"  class="p-2" action="login.html">
        <div class="form-group">
            <label for="login">Login:</label>
            <input id="login" class="form-control" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input id="password" type="password" class="form-control" name="password"/>
        </div>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
        <button type="submit" class="btn btn-primary m-2">OK</button>
    </form>
    <c:if test="${param.error != null}">
       Username or password is invalid
    </c:if>
</div>
</body>
</html>