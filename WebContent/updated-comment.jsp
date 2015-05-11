<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>


<script type="text/javascript" src="js/jquery.js"></script>

</head>

<body>
	<input type="hidden" value="${projectName}" id='projectname'>
	
	
	<script>
		 var projectName = $('#projectname').val();
		 window.location = "/comment.classifier/mvc?logic=ListCommentsLogic&projectName=" + projectName + "&getWithoutClassification=true"
	</script>
	
	
</body>

</html>