<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

 <body>
	<div class="center--body">
	<h2>Modify Course</h2>
	<form:form method="POST" modelAttribute="course" action="../course/submitModifyCourse">
		<table>
			<tr>
				<td><form:input class="input" path="courseID" placeholder="Course ID" minlength="7" maxlength="10" required="true" readonly="true"
				oninvalid="this.setCustomValidity('Course Identification Number must be at least 7 characters.)" oninput="this.setCustomValidity('')" /></td>
			</tr>
			<tr>
				<td><form:errors class="error" path="courseID"/></td>
			</tr>
			<tr>
				<td><form:input class="input" path="title" placeholder="Course Title" maxlength="200" required="true" /></td>

			</tr>
			<tr>
				<td><form:errors class="error" path="title"/></td>
			</tr>
			<tr>

				<td><form:input class="input" path="description" placeholder="Description" maxlength="200" required="true" /></td>

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
		</table>
		
		<div style="display:flex;">
			<input style="flex-grow:1" type="submit" value="Save"/>
			<a href="../course/${course.courseID}">Cancel</a>
		</div>
		
	</form:form>
	
	<c:choose>
		<c:when test="${not empty error}">
			<div class="error">${error}</div>
		</c:when>
	</c:choose>

</div>
</body> 

