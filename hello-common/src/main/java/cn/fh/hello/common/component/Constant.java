package cn.fh.hello.common.component;

/**
 * Encapsulate constants
 * @author whf
 *
 */
public class Constant {
	public static class WebSocketJsonParam {
		public static String SESSION_ID = "sid";
		public static String MESSAGE_CONTENT = "text";
		public static String TARGET_USER_ID = "tid";

		public static String NOT_LOGIN = "none";
	}
	
	public static enum EmotionalStatus {
		SINGLE,
		IN_LOVE,
		MARRIED,
		DIVORCED
	}
	
	public static enum Gender {
		MALE,
		FEMALE
	}
}
