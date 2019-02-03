<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:choose>
<c:when test="${not empty courses}">

	<a style="visibility: hidden;margin-right: auto;">
	<i class='fas'>&#xf2f6;</i>
	</a>
	<h1>Student Discorce</h1>
	<a style="background: none;margin-left: auto;" href="../login/user"
	><i class='fas'>&#xf2f6;</i>
	</a>

</c:when>
<c:otherwise>
	<h1>Student Discorce</h1>
</c:otherwise>
</c:choose>