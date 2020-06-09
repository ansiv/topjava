<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<table border=1>
    <tr>
        <th>Description</th>
        <th>Date</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
<tbody>
<jsp:useBean id="mealTos" scope="request" type="java.util.List"/>
<c:forEach var="mealTo" items="${mealTos}">
    <tr style="color:${mealTo.excess ? 'green' : 'red'}">
        <td><c:out value="${mealTo.description}"/></td>
        <td>
            <fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate"/>
            <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
        <td><c:out value="${mealTo.calories}"/></td>
        <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.id}"/>">Update</a></td>
        <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
    </tr>
</c:forEach>
</tbody>

</table>
<a href="meals?action=insert">Add meal</a>
<h3><a href="index.html">Home</a></h3>
<body>

</body>
</html>
