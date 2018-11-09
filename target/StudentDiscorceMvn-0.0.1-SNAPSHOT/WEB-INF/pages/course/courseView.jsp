<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<body>

<c:choose>
	<c:when test="${not empty course}">
<div class="course-container">

	<div class="course--content">
	
		<div class="course--header">
			
			<img src="<c:url value="${course.image}"/>" alt="Picture" width="500px" height="200px">
						
			<div class="course--id">
				${course.courseID}
				${course.title}
			</div>
			<div class="course--major">
				${course.major}
			</div>
		</div>
		<div class="course--time">
		<h4>Class Information:</h4>
			Room: ${course.classLocation}, Time: ${course.classTimes}
		
		<h4>Tutoring Information:</h4>
			Room: ${course.classLocation}, Time: ${course.tutorTimes}
		</div>
		
		<div class="course--description">
			<h4>Description:</h4>
			${course.description}
		</div>
		
	</div>
	
	<div class="course--users">
		
		<h3>Instructors:</h3>
		<hr/>
		<c:forEach items="${course.instructors}" var="instructor">
			${instructor.lastName}, ${instructor.firstName}<br/>
		</c:forEach>
		
		<h3>Tutors:</h3>
		<hr/>
		<c:forEach items="${course.tutors}" var="tutor">
			${tutor.lastName}, ${tutor.firstName}<br/>
		</c:forEach>
		
		<h3>Students:</h3>
		<hr/>
		<c:forEach items="${course.students}" var="student">
			${student.lastName}, ${student.firstName}<br/>
		</c:forEach>
		
	</div>

</div>

	</c:when>
	<c:otherwise>
	
	Class Does not exist
	</c:otherwise>

</c:choose>


</body>