package cn.fh.hello.web.utils;

import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

/**
 * Store sessions for WebSocket user and HTTP user
 * @author whf
 *
 */
public class SessionCollection {
	private static Map<Integer, WebSocketSession> socketSessionMap;

	private SessionCollection() {}
	
	public static void putSocketSession(Integer userId, WebSocketSession sockSession) {
		socketSessionMap.put(userId, sockSession);
	}
	
	public static void removeSocketSession(Integer userId) {
		socketSessionMap.remove(userId);
	}
	
	public static int getSocketSessionAmount() {
		return socketSessionMap.size();
	}
	
}
