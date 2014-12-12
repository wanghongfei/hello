package cn.fh.hello.web.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

/**
 * Store sessions for WebSocket user and HTTP user
 * @author whf
 *
 */
public class SessionCollection {
	/**
	 * A map to store WebSocketSession. The key of this map is session id
	 */
	private static Map<String, WebSocketSession> socketSessionMap = new HashMap<String, WebSocketSession>();

	private SessionCollection() {}
	
	
	public static void putSocketSession(String sessionId, WebSocketSession sockSession) {
		socketSessionMap.put(sessionId, sockSession);
	}
	
	public static void removeSocketSession(String sessionId) {
		socketSessionMap.remove(sessionId);
	}
	
	public static boolean containsSocketSession(String sessionId) {
		return socketSessionMap.containsKey(sessionId);
	}
	
	public static int getSocketSessionAmount() {
		return socketSessionMap.size();
	}


	public static Map<String, WebSocketSession> getSocketSessionMap() {
		return socketSessionMap;
	}
	
}
