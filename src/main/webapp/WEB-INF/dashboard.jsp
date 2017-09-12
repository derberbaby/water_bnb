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
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    <a href="/landing">Search other listings</a>
    
    <h2>Content Listings</h2>
    <table>
    		<tr>
    			<th>Address</th>
    			<th>Pool Size</th>
    			<th>Cost Per Night</th>
    			<th>Details</th>
    		</tr>
    		<c:forEach var="row" items="${userPools}">
    			<tr>
    				<td>${row.address}</td>
    				<td>${row.size}</td>
    				<td>$${row.cost}</td>
    				<td><a href="/host/pools/${row.id}">0.0/5 - edit</a></td>
    			</tr>
    		</c:forEach>
    </table>
    
    <h3>New Listing</h3>
    <p><form:errors path="pool.*"/></p>
    	<form:form method="POST" action="/host/pools/new" modelAttribute="pool">
	    <p><form:label path="address">Address: 
	    <form:input path="address"/></form:label></p>
	    	
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
	    <input type="submit" value="Add Listing"/>
	</form:form>
</body>
</html>