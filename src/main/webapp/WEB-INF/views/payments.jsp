<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop</title>
    <%@ include file="resources.jsp" %>
</head>
<body>
<div class="container">
    <h1>Payments</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payment" items="${resultPage.data}">
            <tr>
                <td>${payment.id}</td>
                <td>${payment.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
