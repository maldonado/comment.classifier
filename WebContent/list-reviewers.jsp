<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link href="css/global.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

</head>

<body>
	<div class='container'>

		<div class='selectreviewers'>
			<select id="selectmenu">
				<option>Select Reviewer</option>
				<c:forEach var="reviewer" items="${reviewers}">
					<option>${reviewer}</option>
				</c:forEach>
			</select>
		</div>

	</div>

	<script>
		$("#selectmenu").selectmenu({change : function(event, ui) {}});

		$("#selectmenu").on("selectmenuchange",	function(event, ui) {
							var reviewer = $("#selectmenu option:selected").text();
							window.location = "/comment.classifier/mvc?logic=ListSignificativeSampleCommentsLogic&reviewerName="+ reviewer 
						});
	</script>
</body>

</html>