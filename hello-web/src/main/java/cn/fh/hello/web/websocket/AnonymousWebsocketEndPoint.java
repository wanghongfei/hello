package cn.fh.hello.web.websocket;

/**
 * Chat without login
 * @author whf
 *
 */
public class AnonymousWebsocketEndPoint extends DefaultWebsocketEndPoint {

	@Override
	protected boolean isLoginNeeded() {
		return false;
	}

}
