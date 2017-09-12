<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WaterBnB</title>
</head>
<body>

<a href="/guest/signin">Sign in / Sign up</a>
    
    <p>Find places to swim and sleep on water bnb!</p>
    
    <form method="POST" action="/search">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    		<p><input type="text" name="searchQuery" placeholder="location"> <input type="submit" value="Search"></p>
    	</form>
    
</body>
</html>