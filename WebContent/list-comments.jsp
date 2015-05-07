<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

<link href="css/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

</head>

<body>
	<div id="accordion">
		<c:forEach var="comment" items="${comments}">
			<h3></h3>
			<div>${comment.commenttext}</div>
		</c:forEach>
	</div>

	<script>
		$( "#accordion" ).accordion();		
	</script>
</body>

</html>
