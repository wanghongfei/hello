package cn.fh.hello.web.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.fh.hello.common.component.JsonWrapper;

public class WebsocketEndPoint extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		
		String jsonStr = message.getPayload();
		JsonWrapper jw = new JsonWrapper(jsonStr);
		String sId = jw.getValue("session");
		String text = jw.getValue("text");
		System.out.println(sId + "," + text);

		
	}

	/**
	 * Store WebSocketSession object to SessionCollection class
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		
		//session.getPrincipal();
	}

}
