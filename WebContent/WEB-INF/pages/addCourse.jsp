<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

 <body>
	<div class="center--body">
	<h2>Add Course</h2>
	<br/>
	<form:form method="POST" modelAttribute="course" action="../course/addedCourse">
		<table>
			<tr>
				<td><form:input class="input" path="courseID" placeholder="Course ID" minlength="1" maxlength="30" required="true" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="courseID"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="title" placeholder="Course Title" minlength="5" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="title"/></td>
			</tr>
			<tr>

				<td><form:input class="input" path="description" placeholder="Description" minlength="5" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="description"/></td>
			</tr>
			<tr>

				<td><form:input class="input" path="major" placeholder="Major" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="major"/></td>
			</tr>
			<tr>

				<td><form:input class="input" path="classLocation" placeholder="Class Location" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="classLocation"/></td>
			</tr>
			<tr>

				<td><form:input class="input"  path="classTimes" placeholder="Class Times" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="classTimes"/></td>
			</tr>
			<tr>

				<td><form:input class="input"  path="tutorTimes" placeholder="Tutor Times" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="tutorTimes"/></td>
			</tr>

			<tr>
				<td><form:input class="input"  path="image" placeholder="Image URL (https://...)" maxlength="500" required="false" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="image"/></td>
			</tr>
			
			<tr> 
				<td><input type="submit" value="Add Course"/></td>
				

				<td><a href="../login/user">Login</a></td>
			</tr>
		</table>
	</form:form>

	<div style="color: red">${error}</div>
</div>
</body> 

