package cn.fh.hello.common.component.utils;

public class StringUtils {
	private StringUtils() {}
	
	/**
	 * "apple" --> apple
	 * @param str
	 * @return
	 */
	public static String trimQuotation(String str) {
		return str.substring(1, str.length() - 1);
	}
	
	/**
	 * 截掉URL的最后一段字符串
	 * <p>eg. 传入 /backstage/manage, 返回 /backstage
	 * @param url
	 * @return
	 */
	public static String trimLastUrlToken(String url) {
		int lastSplash = url.lastIndexOf('/');
		return url.substring(0, lastSplash);
	}
	
	/**
	 * 得到去掉上下文的URL路径。
	 * <p> 例如, "/ezoo/home" --> "/home", "/home" --> "/home"
	 * 
	 * @param contextPath
	 * @param uri
	 * @return
	 */
	public static String trimContextFromUrl(String contextPath, String uri) {
		String url = null;

		if (false == "/".equals(contextPath)) {
			int ctxLen = contextPath.length();
			url = uri.substring(ctxLen);
		} else {
			url = uri;
		}
		
		return url;
	}
}
