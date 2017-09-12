<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>WaterBnB</title>
</head>
<body>

    <h2>Review of ${pool.address}</h2>
    <p><form:errors path="reviewObj.*"/></p>
    	<form:form method="POST" action="/pools/${pool.id}/review/add" modelAttribute="reviewObj">
	    <p><form:label path="review">Leave a review:  
	    <form:input path="review"/></form:label></p>
	    
	    <p><form:label path="rating">Rating: 
	    <form:input type="number" min="1" max="5" path="rating"/></form:label></p>
	    
	    <input type="submit" value="Submit Review"/>
	</form:form>
</body>
</html>