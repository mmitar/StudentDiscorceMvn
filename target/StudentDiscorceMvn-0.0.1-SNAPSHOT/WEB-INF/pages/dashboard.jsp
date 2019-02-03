<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="center--body">

	<div style="margin: auto;text-align: center">
		<h2>Dashboard</h2>

<c:choose>
    <c:when test="${not empty user}">
       
       		<div style="text-align: center">
        	<h3>Welcome ${user.username}!</h3>
			<h4>Congratulations on successfully logging in!</h4>
			</div>
    </c:when>
</c:choose>

    	<div style="max-width: 390px; text-align: center">
		Add a new course or access an existing courses for a plethora of totally relevant information.
		<br/></br>
		Also, the app is totally responsive, test it out!
		</div>
	
		<c:choose>
			<c:when test="${not empty error}">
			<br/>
				<div class="error">${error}</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${not empty success}">
			<br/>
				<div class="success">${success}</div>
			</c:when>
		</c:choose>
	
		</div>
		
</div>