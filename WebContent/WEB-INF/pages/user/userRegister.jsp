<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<body>
	<div class="center--body">
	<h2>Register as a New User</h2>
	<form:form method="POST" modelAttribute="user" action="registerUser">
		<table>
			<tr>
				<td><form:input class="input" path="firstName" placeholder="first name" minlength="5" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="firstName"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="lastName" placeholder="last name" minlength="5" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="lastName"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="email" placeholder="email address" minlength="5" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="email"/></td>
			</tr>
			<tr>
				<td><form:input id="phoneInput" class="input" path="phone" placeholder="phone number" minlength="14" maxlength="14" required="true" 
				oninvalid="this.setCustomValidity('Phone field must be 10 numbers in length.')" oninput="this.setCustomValidity('')" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="phone"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="username" placeholder="username" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="username"/></td>
			</tr>
			<tr>
				<td><form:input class="input" type="password" path="password" placeholder="password" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="password"/></td>
			</tr>
		</table>
		
		<div style="display:flex;">
			<input style="flex-grow:1" type="submit" value="Register"/>
			<a href="../login/user">Login</a>
		</div>
	</form:form>
	
	<c:choose>
		<c:when test="${not empty error}">
			<div class="error">${error}</div>
		</c:when>
	</c:choose>
</div>
</body>