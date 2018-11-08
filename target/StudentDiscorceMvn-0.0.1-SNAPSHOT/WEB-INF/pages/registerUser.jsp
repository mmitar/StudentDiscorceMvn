<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<div class="center--body">
	<h2>Register as a New User</h2>
	<br/>
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
				<td><form:input id="phoneInput" class="input" path="phone" placeholder="phone number" maxlength="14" required="true" /></td>
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
			<tr> 
				<td><input type="submit" value="Register"/></td>
				<td><a href="../login/user">Login</a></td>
			</tr>
		</table>
	</form:form>
	<div style="color: red">${error}</div>
</div>
</body>