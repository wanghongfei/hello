package cn.fh.component.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.fh.hello.common.exception.unchecked.InvalidXmlFileException;

/**
 * Load page security configuration at startup.
 * 
 * @author whf
 *
 */
public class PageProtectionServlet implements ServletContextListener {
	public static Logger logger = LoggerFactory.getLogger(PageProtectionServlet.class);
	public static RequestConstrainManager rcm;
	
	public static final String SECURITY_CONFIG_PATH = "/WEB-INF/security-page.xml";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Load page security configuration
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("载入页面security配置文件");
		}

		try {
			Document doc = getXmlDocumentObject(event.getServletContext());
			Element root = doc.getDocumentElement();

			// 检查根标签名是否正确
			if (false == "page".equals(root.getTagName())) {
				throw new InvalidXmlFileException("标签<" + root.getTagName()
						+ ">不存在");
			}

			// PageProtectionFilter.requestRoleListMap = parseConstrain(root);
			PageProtectionServlet.rcm = parseConstrain(root);

		} catch (SAXException e) {
			e.printStackTrace();
			throw new InvalidXmlFileException("xml语法错误");
		} catch (ParserConfigurationException e) {
			throw new InvalidXmlFileException("xml解析出错");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("页面security载入完毕");
		}

	}
	
	private RequestConstrainManager parseConstrain(Element root) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		RequestConstrainManager manager = new RequestConstrainManager();
		
		if (root.hasChildNodes()) {
			NodeList nodes = root.getChildNodes();
			for (int ix = 0 ; ix < nodes.getLength() ; ++ix) {
				Node node = nodes.item(ix);
				if (node instanceof Element) {
					Element tag = (Element) node;
					
					// 检查标签名是否正确
					if (false == "request".equals(tag.getTagName())) {
						throw new InvalidXmlFileException("标签<" + tag.getTagName() + ">不存在");
					}
					
					// 检查属性是否存在
					String url = tag.getAttribute("url");
					String roles = tag.getAttribute("role");
					if (url.isEmpty()) {
						throw new InvalidXmlFileException("标签<" + tag.getTagName() + ">缺少'url'属性");
					}
					if (roles.isEmpty()) {
						throw new InvalidXmlFileException("标签<" + tag.getTagName() + ">缺少'role'属性");
					}
					
					// 构造roleList数据结构
					List<String> roleList = new ArrayList<String>();
					String[] roleArray = roles.split(" ");
					for (String r : roleArray) {
						roleList.add(r);
						
						if (logger.isDebugEnabled()) {
							logger.debug("找到role限制:<" + url + "> : " + r);
						}
					}
					
					// 构造map数据结构
					map.put(url, roleList);
					manager.put(url, roleList);
				}
			}
		}
		
		//return map;
		return manager;
	}
	
	private Document getXmlDocumentObject(ServletContext ctx) throws SAXException, IOException, ParserConfigurationException {
		InputStream in = ctx.getResourceAsStream(PageProtectionServlet.SECURITY_CONFIG_PATH);
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
	}

}
