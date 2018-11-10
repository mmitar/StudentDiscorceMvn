<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<body>
	<div class="center--body">
	<h2>Login as an Existing User</h2>
	<br/>
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
			<tr>
				<td><input type="submit" value="Login"/></td>
				<td><a href="../register/user">Register</a></td>
				
			</tr>
		</table>
	</form:form>
	
	<div style="color: red">${error}</div>
	<div style="color: green">${success}</div>
	</div>
</body>