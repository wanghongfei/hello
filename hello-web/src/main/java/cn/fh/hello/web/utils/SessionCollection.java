package cn.fh.hello.web.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.socket.WebSocketSession;

/**
 * Store sessions for WebSocket user and HTTP user
 * @author whf
 *
 */
public class SessionCollection {
	/**
	 * A map to store WebSocketSession.
	 * <p> Key: HTTP session id, Value: WebSocketSession object
	 */
	private static Map<String, WebSocketSession> socketSessionMap = new HashMap<String, WebSocketSession>();
	/**
	 * A List to store HttpSession.
	 */
	private static List<HttpSession> httpSessionList = new LinkedList<HttpSession>();

	private SessionCollection() {}
	
	
	
	public static HttpSession getHttpSession(String sessionId) {
		Optional<HttpSession> optTargetSession = httpSessionList
				.stream()
				.filter( (HttpSession session) -> session.getId().equals(sessionId) )
				.findFirst();
		
		return optTargetSession.isPresent() ? optTargetSession.get() : null;
	}
	public static void addHttpSession(HttpSession session) {
		httpSessionList.add(session);
	}
	public static void removeHttpSession(HttpSession session) {
		httpSessionList.remove(session);
	}
	public static int getHttpSessionAmount() {
		return httpSessionList.size();
	}
	public static boolean containsHttpSession(HttpSession session) {
		return httpSessionList.contains(session);
	}
	

	
	public static WebSocketSession getSocketSession(String httpSessionId) {
		Optional<WebSocketSession> optSocketSession = socketSessionMap.entrySet()
				.stream()
				.filter( (entry) ->  entry.getKey().equals(httpSessionId) )
				.map( Entry::getValue )
				.findFirst();
		
		return optSocketSession.isPresent() ? optSocketSession.get() : null;
	}
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
	public static List<HttpSession> getHttpSessionMap() {
		return httpSessionList;
	}
	
}
