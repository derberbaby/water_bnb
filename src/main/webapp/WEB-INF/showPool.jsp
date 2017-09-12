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
<a href="/search">Go Back</a>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>

<h1>${pool.address}</h1>
<p>${pool.description}</p>
<p>Host email: ${pool.host.email}</p>
<p>Host: ${pool.host.firstName} ${pool.host.lastName}</p>
<p>Pool Size: ${pool.size}</p>
<p>Cost: $${pool.cost}</p>
    
	<h2>Reviews (${pool.avg_rating}/5)</h2>
	<c:if test="${principal == null}">
		<a href="/guests/signin">Log in to leave a review</a>
	</c:if>
	<c:if test="${currentUser.getRole() == 'guest'}">
		<a href="/pools/${pool.id}/review">Leave a review</a>
	</c:if>
	<c:forEach var="row" items="${poolReviews}" varStatus="loop">
		<p>${row[0]} ${row[1]}</p>
		<p>${row[2]}</p>
		<p>${row[3]}</p>
		<c:if test="${!loop.last}"><hr></c:if>
	</c:forEach>
</body>
</html>