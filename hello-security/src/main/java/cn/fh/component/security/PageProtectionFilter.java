package cn.fh.component.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fh.hello.common.component.utils.StringUtils;
import cn.fh.hello.common.security.Credential;
import cn.fh.hello.common.security.CredentialUtils;

public class PageProtectionFilter implements Filter {
	public static Logger logger = LoggerFactory.getLogger(PageProtectionFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = null;
		
		// 从URI中去掉上下文名
		url = StringUtils.trimContextFromUrl(req.getContextPath(), req.getRequestURI());

		
		if (logger.isDebugEnabled()) {
			logger.debug("请求url:" + url);
		}
		
		// 请求的是资源文件，无需处理
		if (url.startsWith("/resources")) {
			chain.doFilter(request, response);
		}
		
		// 检查role
		// role不满足
		if (false == checkRole(url, req.getSession())) {
			HttpServletResponse resp = (HttpServletResponse) response;

			OutputStream out = resp.getOutputStream();

			resp.setContentType("text/plain");
			resp.setStatus(403); // 无权访问

			out.write("bad role".getBytes());
			out.close();
			
			return;

		}
		
		
		
		chain.doFilter(request, response);


	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 判断是否允许用户访问该页面
	 * @param requestURL
	 * @param session
	 * @return
	 */
	private boolean checkRole(String requestURL, HttpSession session) {
		//List<String> roleList = PageProtectionFilter.requestRoleListMap.get(requestURL);
		List<String> roleList = PageProtectionServlet.rcm.get(requestURL);
		
		// 该请求不需要role
		if (null == roleList) {
			return true;
		}
		
		// 得到Credential
		Credential credential = CredentialUtils.getCredential(session);
		// 未登陆
		if (null == credential) {
			return false;
		}
		
		// 处理最后一个字符为 '*' 的情况
		/*char last = requestURL.charAt(requestURL.length() - 1);
		if ('*' == last) {
			int lastSplash = requestURL.lastIndexOf('/');
			String parentURL = requestURL.substring(0, lastSplash);
			
			if (requestURL.startsWith(parentURL)) {
				if (false == checkRole(roleList, credential) ) {
					return false;
				}
			}
		}*/
		
		// 检查role是否满足
		checkRole(roleList, credential);

		
		return true;
	}
	
	/**
	 * 检查当前已经登陆用户的role是否满足条件
	 * @param roleList
	 * @param credential
	 * @return
	 */
	private boolean checkRole(List<String> roleList, Credential credential) {
		return roleList.stream()
			.anyMatch( (roleName) -> credential.hasRole(roleName) );

/*		for (String role : roleList) {
			if (false == credential.hasRole(role)) {
				return false;
			}
		}
		
		return true;*/
	}

}
