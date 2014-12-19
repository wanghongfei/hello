/**
 * JS for session.jsp
 */
var socket = null;

function getSessionId() {
	return $.cookie("JSESSIONID");
}

function send(content, tid) {
	if (null != socket) {
		var json = {
				sid: getSessionId(),
				text: content,
				tid: tid
		}

		socket.send(JSON.stringify(json));
	}
}

function initWebSocket() {
	if ('WebSocket' in window) {
		console.log("websocket supported");
		socket = new WebSocket("ws://localhost:8080//websocket/anonymous");
		// var socket = new WebSocket("ws://localhost:8080//websocket/login");

		console.log("connecting");

		var json = {
			sid : getSessionId(),
			// sid: "none",
			text : "first send",
			tid : "bruce"
		};

		socket.onopen = function() {
			console.log("connection to server opened!");
			socket.send(JSON.stringify(json));
		}

		socket.onclose = function() {
			console.log("connection to server closed!");
		}

		socket.onmessage = function(event) {
			var msg = event.data;
			console.log(msg);
		}

	} else {
		console.log("websocket not supported");
	}
}

$(document).ready(function() {
	initWebSocket();
	
	$("#btnSend").click(function() {
		var msg = $("#message").val();
		var tid = $("#targetSession").val();
		send(msg, tid);
		console.log("message " + msg + " sent");
	});
});