<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>WaterBnb</title>
</head>
<body>
	<c:if test="${principal != null}">
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" value="Logout!" />
    		</form>
	</c:if>
	
    <a href="/landing">Home</a>
    
    <h1>Find your pool!</h1>
    
   	<form method="POST" action="/search">
   	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    		<p><input type="text" name="searchQuery" placeholder="new search"> <input type="submit" value="Search"></p>
    	</form>
    	
    <table>
    		<tr>
    			<th>Address</th>
    			<th>Pool Size</th>
    			<th>Cost Per Night</th>
    			<th>Details</th>
    		</tr>
		<c:forEach var="pool" items="${foundPools}">
		<tr>
			<td>${pool.address}</td>
			<td>${pool.size}</td>
			<td>$${pool.cost}</td>
			<td><a href="/pools/${pool.id}">${pool.avg_rating}/5 - See more</a></td>
		</tr>
		</c:forEach>
    </table>
</body>
</html>