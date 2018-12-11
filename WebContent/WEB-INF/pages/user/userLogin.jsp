<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<body>
	<div class="center--body">
	<h2>Login as an Existing User</h2>
	<form:form method="POST" modelAttribute="user" action="../login/validateUser">
		<table>
			<tr>
				<td><form:input class="input" path="username" placeholder="username" minlength="5" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="username"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="password" type="password" placeholder="password" minlength="5" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="password"/></td>
			</tr>
		</table>
		
		<div style="display:flex;">
			<input style="flex-grow:1" type="submit" value="Login"/>
			<a href="../register/user">Register</a>
		</div>
	</form:form>
	
	<c:choose>
		<c:when test="${not empty error}">
			<div class="error">${error}</div>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${not empty success}">
			<div class="success">${success}</div>
		</c:when>
	</c:choose>
	
	</div>
</body>