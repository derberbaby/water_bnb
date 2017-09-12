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
    <c:if test="${logoutMessage != null}">
        <c:out value="${logoutMessage}"></c:out>
    </c:if>
    <c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>
    </c:if>
    
    <h1>Welcome</h1>
    <a href="/landing">Search as guest</a>
    <p><form:errors path="userObject.*"/></p>
    
    <fieldset><legend>Register</legend>
    <form:form method="POST" action="/registration" modelAttribute="userObject">
        <p>
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName"/>
        </p>
        <p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <p>
        		<form:select path="role">
        			<option selected disabled>Access</option>
		    		<form:option value="host">Host</form:option>
		    		<form:option value="guest">Guest</form:option>
        		</form:select>
        </p>
        <input type="submit" value="Register"/>
    </form:form>
    </fieldset>
    
    <fieldset><legend>Login</legend>
    <form method="POST" action="/guest/signin">
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="username"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login"/>
    </form>
    </fieldset>
    
</body>
</html>