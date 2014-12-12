package cn.fh.hello.web.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.fh.hello.web.utils.SessionCollection;

public class DefaultHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		SessionCollection.addHttpSession(e.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		// remove from HTTP session List
		SessionCollection.removeHttpSession(e.getSession());
		
		// remove from WebSocket session Map
		SessionCollection.removeSocketSession(e.getSession().getId());
	}

}
