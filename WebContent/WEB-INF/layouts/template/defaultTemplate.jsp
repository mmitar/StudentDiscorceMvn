<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<spring:url value="/resources/layouts.css" var="layoutsCSS" />
	<spring:url value="/resources/styles.css" var="stylesCSS" />
	<spring:url value="/resources/layouts.js" var="layoutsJS" />
	
	<!-- JQUERY EXTERNAL RESOURCES -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	  
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
   	<script type="text/javascript" src=" https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
	<!-- JQUERY EXTERNAL RESOURCES -->
	
	<link href="${layoutsCSS}" rel="stylesheet" type="text/css" media="all"/>
	<link href="${stylesCSS}" rel="stylesheet" type="text/css" media="all"/>
	<script src="${layoutsJS}" type="text/javascript"></script>
	
	<title>CST-341 In-Class Activity</title>
	
</head>

<body>
	<div class="application">
	
		<div class="menuLeft">
			<tiles:insertAttribute name="menuLeft" />
		</div>
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
		<div class="menuRight">
			<tiles:insertAttribute name="menuRight" />
		</div>
		
	</div>
	
</body>

</html>