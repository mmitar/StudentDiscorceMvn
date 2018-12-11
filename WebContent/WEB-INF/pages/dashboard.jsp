<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>

<div class="center--body">
<h2>Dashboard</h2>

<c:choose>
    <c:when test="${not empty user}">
       
       		<div style="text-align: center">
        	<h3>Welcome ${user.username}!</h3>
			<h4>Congratulations on successfully logging in!</h4>
			</div>
    </c:when>
</c:choose>
	<br/>
    	<div style="max-width: 390px; text-align: center">
		add a new course or access an existing courses for a plethora of totally relevant information
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
	
	<a style="margin-left:auto; margin-top:auto" href="../login/user">Log Out</a>
			
	</div>
</body>