<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<c:forEach var="mealTo" items = "${mealTos}">
    <p style="color:${mealTo.excess ? 'green' : 'red'}">
        <fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate"/>
        <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
        <c:out value="${mealTo.description}"/>
        <c:out value="${mealTo.calories}"/>
    </p>
</c:forEach>
<h3><a href="index.html">Home</a></h3>
<body>

</body>
</html>
