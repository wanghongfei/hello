package cn.fh.hello.web.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Used to send message to target user.
 * @author whf
 *
 */
public class WebSocketDispatcher {
	private WebSocketDispatcher() {}
	
	/**
	 * Send message to specified user.
	 * @param targetSessionId HttpSession id of the target user
	 * @param jsonMessage
	 * @return
	 */
	public static boolean dispatchMessage(String targetSessionId, String jsonMessage) {
		WebSocketSession targetSession = SessionCollection.getSocketSession(targetSessionId);
		if (null == targetSession) {
			return false;
		}
		
		try {
			targetSession.sendMessage(new TextMessage(jsonMessage));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Send message to all online users.
	 * @param msg Json String
	 */
	public static void sendToAll(String msg) {
		// traverse all session
		Map<String, WebSocketSession> sessionMap = SessionCollection.getSocketSessionMap();
		Set<Entry<String, WebSocketSession>> entrySet = sessionMap.entrySet();
		WebSocketSession session = null;
		for (Entry<String, WebSocketSession> entry : entrySet) {
			// send message
			try {
				session = entry.getValue();
				
				if (session.isOpen()) {
					session.sendMessage(new TextMessage(msg));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

/*		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				// traverse all session
				Map<String, WebSocketSession> sessionMap = SessionCollection.getSocketSessionMap();
				Set<Entry<String, WebSocketSession>> entrySet = sessionMap.entrySet();
				for (Entry<String, WebSocketSession> entry : entrySet) {
					
					try {
						entry.getValue().sendMessage(new TextMessage(msg));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		// start task
		timer.scheduleAtFixedRate(task, 0, interval);*/
	}
}
