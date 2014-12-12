package cn.fh.hello.web.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSender {
	private WebSocketSender() {}
	
	public static void sendToAll(String msg, int interval) {
		// traverse all session
		Map<String, WebSocketSession> sessionMap = SessionCollection.getSocketSessionMap();
		Set<Entry<String, WebSocketSession>> entrySet = sessionMap.entrySet();
		for (Entry<String, WebSocketSession> entry : entrySet) {
			// send message
			try {
				entry.getValue().sendMessage(new TextMessage(msg));
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
