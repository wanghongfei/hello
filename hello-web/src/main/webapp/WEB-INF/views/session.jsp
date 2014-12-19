<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
	<head>
		<title>session</title>
	</head>

	<body>
		session id:<h3 id="sessionId"></h3>
	</body>
	
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-2.1.3.js" ></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery.cookie.js" ></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var sId = $.cookie("JSESSIONID");
			console.log("sId" + sId);
			$("#sessionId").html(sId);
		});
	</script>
</html>
