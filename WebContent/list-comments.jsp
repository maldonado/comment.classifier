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
	<input type="hidden" value="${projectName}" id='projectName'>

	<div class='container'>

		<div class='button'>
			<button id="projects">Back to projects</button>
		</div>

		<div class='h3'>
			<h3>${projectName} classification progress:</h3>
		</div>

		<div class='progressbar' id="progressbar"></div>

		<div class='button'>
			<button id='previous'>Load previous</button>
		</div>

		<div class='selectclassification'>
			<select id="classification">

				<option>Other</option>
				<option>INVESTIGATE</option>
				<option>BUG_FIX_COMMENT</option>

			</select>
		</div>

		<div id="dialog" title="Comment">
			<p>${comment.text}</p>
			<input type="hidden" value="${comment.id}" id='commentid'>
		</div>


	</div>

	<script>
		var comment_id = $('#commentid').val();
		var progress_value = $('#progress').val();
		var project_name = $('#projectName').val();

		$("#classification").selectmenu({
			change : function(event, ui) {
			}
		});
		$("#classification").on("selectmenuchange",	function(event, ui) {
			var classification = $("#classification option:selected").text(); 
			window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="	+ comment_id + "&classification="+ classification});

		$("#projects").button();
		$("#projects").click(function() {
			window.location = "/comment.classifier/mvc?logic=ListProjectsLogic"
		});

		$("#previous").button();
		$("#previous").click(function() {
			window.location = "/comment.classifier/mvc?logic=ListPreviousCommentLogic&commentId="+ comment_id+ "&projectName="+ project_name
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
													+ "&classification=DESIGN"
										}
									},
									{
										id : "implementation",
										text : "Implementation",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=IMPLEMENTATION"
										}
									},
									
									{
										id : "test",
										text : "Test",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=TEST"
										}
									},
									{
										id : "defect",
										text : "Defect",
										click : function() {
											window.location = "/comment.classifier/mvc?logic=UpdateCommentLogic&commentId="
													+ comment_id
													+ "&classification=DEFECT"
										}
									},

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
