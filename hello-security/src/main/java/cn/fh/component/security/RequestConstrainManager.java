package cn.fh.component.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fh.hello.common.component.utils.StringUtils;

public class RequestConstrainManager {
	public static Logger logger = LoggerFactory
			.getLogger(RequestConstrainManager.class);

	/**
	 * 保存不包含通匹符的URL
	 */
	private Map<String, List<String>> roleMap = new HashMap<String, List<String>>();
	/**
	 * 保存包含通匹符的URL
	 */
	private Map<String, List<String>> wildcardRoleMap = new HashMap<String, List<String>>();

	public List<String> get(String url) {
		// 先进行精确匹配，如果找到对应的roleList则返回
		List<String> roleList = this.roleMap.get(url);
		if (null != roleList) {
			return roleList;
		}

		// 如果精确匹配失败，则进行'*'匹配
		// 即确定请求的URL是否符合/home/*的形式
		String wildcardUrl = StringUtils.trimLastUrlToken(url) + "/*";
		roleList = this.wildcardRoleMap.get(wildcardUrl);

		return roleList;
	}

	/**
	 * 添加URL和其对应的roleList
	 * 
	 * @param url
	 *            带通配符的URL或不带通配符的URL均可
	 * @param roleList
	 */
	public void put(String url, List<String> roleList) {
		char lastChar = url.charAt(url.length() - 1);

		// 分类存放
		// 最后一个字符为 * 的放到 wildcardRoleMap中
		if ('*' == lastChar) {
			this.wildcardRoleMap.put(url, roleList);
		} else {
			// 普通URL放到 roleMap中
			this.roleMap.put(url, roleList);
		}
	}
}
