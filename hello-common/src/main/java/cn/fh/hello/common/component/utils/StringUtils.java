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
}
