<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<form method="post" action="meals">
    Meal ID : <input type="text" readonly="readonly" name="mealId" value="<c:out value = "${mealTo.id}"/>"/> <br/>
    Description : <input type="text" name="description" value="<c:out value = "${mealTo.description}"/>"/> <br/>
    Date : <input type="datetime-local" name="datetime-local" value="<c:out value = "${mealTo.dateTime}"/>"/> <br/>
    Calories : <input type="text" name="calories" value="<c:out value = "${mealTo.calories}"/>"/> <br/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
