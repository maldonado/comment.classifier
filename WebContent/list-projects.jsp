<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

  <head>
  	
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
  
  </head>

  <body>
    
	<select id="selectmenu">
	  	<option>Select Project</option>		
	  	<c:forEach var="project" items="${projects}">
			<option>${project}</option>
	 	</c:forEach>
	</select>		
	
	<script>
		$( "#selectmenu" ).selectmenu({
			  change: function( event, ui ) {}
		});
		
		
		$( "#selectmenu" ).on( "selectmenuchange", function( event, ui ) {
			alert($("#selectmenu option:selected").text());	
		} );
		
	</script>
  </body>
  
</html>
