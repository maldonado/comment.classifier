<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

<link href="css/global.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
</head>

<body>
	<input type="hidden" value="${progress}" id='progress'>

	<div class='container'>

		<div class='button'>
			<button id="button">Back to projects</button>
		</div>
		
		<div class='h3'>
			<h3>${projectName} classification progress:</h3>
		</div>
		
		<div class='progressbar' id="progressbar"></div>

		<div class='selectclassification'>
			<select id="classification">
				
				<option>Other</option>
				<option>INVESTIGATE</option>
				<option>BUG_FIX_COMMENT</option>
				
			</select>
		</div>
		
		<div id="dialog" title="Comment">
			<c:forEach var="comment" items="${comments}">
				<p>${comment.text}</p>
				<input type="hidden" value="${comment.id}" id='commentid'>
			</c:forEach>
		</div>


	</div>

	<script>
		var comment_id = $('#commentid').val();
		var progress_value = $('#progress').val();
	
		$("#classification").selectmenu({change : function(event, ui) {}});
		$("#classification").on("selectmenuchange",	function(event, ui) {
			var classification = $("#classification option:selected").text();
			window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="+ comment_id+ "&classification="+ classification});
	
		$("#button").button();
		$("#button").click(function() {
			window.location = "/comment.classifier/mvc?logic=ListProjectsLogic"
		});

		$("#dialog")
				.dialog(
						{
							autoOpen : true,
							width : 950,
							buttons : [
									{
										id : "without_classification",
										text : "Without Classification",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=WITHOUT_CLASSIFICATION"
										}
									},
									{
										id : "design",
										text : "Design",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=DESIGN_RELATED"
										}
									},
									{
										id : "test",
										text : "Test",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=TEST_RELATED"
										}
									},
									{
										id : "implementation",
										text : "Implementation",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=IMPLEMENTATION_RELATED"
										}
									},
									/* {
										id : "bugfix",
										text : "Bug fix",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=BUG_FIX_RELATED"
										}
									}, */
									{
										id : "documentation",
										text : "Documentation",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=DOCUMENTATION"
										}
									} ]
						});

		$("#progressbar").progressbar({
			value : parseInt(progress_value)
		});
		
	</script>
</body>

</html>
