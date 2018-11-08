<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<spring:url value="/resources/layouts.css" var="layoutsCSS" />
	<spring:url value="/resources/styles.css" var="stylesCSS" />
	<spring:url value="/resources/layouts.js" var="layoutsJS" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	
	<link href="${layoutsCSS}" rel="stylesheet" type="text/css" media="all"/>
	<link href="${stylesCSS}" rel="stylesheet" type="text/css" media="all"/>
	<script src="${layoutsJS}"></script>
	
	<title>CST-341 In-Class Activity</title>
</head>

<body>
	<div class="application">
		<div class="menuLeft">
			<tiles:insertAttribute name="menuLeft" />
		</div>
		<div class="container">
			<!-- Header -->
			<div class="header">
				<tiles:insertAttribute name="header" />
			</div>
			
			<!-- Body Page -->
			<div class="body">
				<tiles:insertAttribute name="body" />
			</div>
		
			<!-- Footer Page -->
			<div class="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
		<div class="menuRight">
			<tiles:insertAttribute name="menuRight" />
		</div>
	</div>
	
</body>

</html>