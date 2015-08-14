<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>


<script type="text/javascript" src="js/jquery.js"></script>

</head>

<body>
	<input type="hidden" value="${reviewerName}" id='reviewername'>
	
	
	<script>
		 var reviewerName = $('#reviewername').val();
		 window.location = "/comment.classifier/mvc?logic=ListSignificativeSampleCommentsLogic&reviewerName=" + reviewerName
	</script>
	
	
</body>

</html>