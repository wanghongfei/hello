<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true"%>

<html>
	<head>
		<meta charset="UTF-8" />
		<title>session</title>
	</head>

	<body>
		session id:<h3 id="sessionId"></h3>
		
		message: <input type="text" id="message" /> <br />
		target session: <input type="text" id="targetSession"> <br />
		<button id="btnSend">发送</button>
	</body>
	
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.3.js" ></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery/jquery.cookie.js" ></script>

	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/page/session.js"></script>
</html>
