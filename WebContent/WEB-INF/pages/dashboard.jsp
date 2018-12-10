<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>

<div class="center--body">
<h2>Dashboard</h2>
<br/>
		<c:choose>
    <c:when test="${empty user}">
    <div align="center">add new classrooms to access resources and 
        <br/>chat with other students</div>
        
    </c:when>
    <c:otherwise>
        <h3>Welcome ${user.username}!</h3>
			<br/>
			<div align="center">
				<h4>Congratulations on successfully logging in!</h4>
			</div>
			<br/>
			<div align="center">
			add new classrooms to access resources and chat with other students
        	</div>
        <br/>
    </c:otherwise>
</c:choose>
		
		<div style="color: red">${error}</div>
		<div style="color: green">${success}</div>

		<a href="../course/addCourse">Add Course</a>
			
		</div>
</body>