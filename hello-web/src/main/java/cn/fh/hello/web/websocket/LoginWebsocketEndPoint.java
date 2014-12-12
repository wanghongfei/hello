package cn.fh.hello.web.websocket;

/**
 * User must login first.
 * @author whf
 *
 */
public class LoginWebsocketEndPoint extends DefaultWebsocketEndPoint {

	@Override
	protected boolean isLoginNeeded() {
		return true;
	}

}
