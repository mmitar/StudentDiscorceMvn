<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>

<div class="center--body">
<h2>Dashboard</h2>
<br/><br/>
		<c:choose>
    <c:when test="${empty user}">
    <div align="center">add new classrooms to access resources and 
        <br/>chat with other students</div>
        
    </c:when>
    <c:otherwise>
        <h3>Welcome ${user.username}!</h3>
			<br/>
			<h4>Congratulations on successfully logging in!</h4>
			<br/>
			<div align="center">add new classrooms to access resources and 
        <br/>chat with other students</div>
    </c:otherwise>
</c:choose>
		
			<tr>
			<td><a href="../course/addCourse">Add Course</a></td>
			</tr>
			
		</div>
</body>