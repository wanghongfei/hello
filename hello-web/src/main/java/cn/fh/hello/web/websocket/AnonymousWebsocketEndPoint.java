package cn.fh.hello.web.websocket;

/**
 * Chat withoud login
 * @author whf
 *
 */
public class AnonymousWebsocketEndPoint extends DefaultWebsocketEndPoint {

	@Override
	protected boolean isLoginNeeded() {
		return false;
	}

}
