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
<a href="/host/dashboard">Go Back</a>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>

<p>Host email: ${currentUser.email}</p>
<p>Host: ${currentUser.firstName} ${currentUser.lastName}</p>
<h2>${pool.address}</h2>

    <p><form:errors path="pool.*"/></p>
    	<form:form method="POST" action="/host/pools/edit" modelAttribute="pool">
	    <form:hidden path="id" value="${pool.id}"/>
	    <form:hidden path="address" value="${pool.address}"/>
	    
	    	<p><form:label path="description">Description: 
	    <form:input path="description"/></form:label></p>
	    
	    <p><form:label path="cost">Cost per night: $
	    <form:input type="number" step="0.01" path="cost"/></form:label></p>
	    
	    <p><form:label path="size">Pool Size: 
	    <form:select path="size">  
	    		<option selected disabled>Size</option>
	    		<form:option value="small">Small</form:option>
	    		<form:option value="medium">Medium</form:option>
	    		<form:option value="large">Large</form:option>
	    	</form:select>
	    </form:label></p>
	    <input type="submit" value="Save Changes"/>
	</form:form>
	
	<h2>Reviews (${pool.avg_rating}/5)</h2>
	<c:forEach var="row" items="${poolReviews}" varStatus="loop">
		<p>${row[0]} ${row[1]}</p>
		<p>${row[2]}</p>
		<p>${row[3]}</p>
		<p><c:if test="${!loop.last}"><hr></c:if></p>
	</c:forEach>
</body>
</html>