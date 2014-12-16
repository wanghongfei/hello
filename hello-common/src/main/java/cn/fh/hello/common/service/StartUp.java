package cn.fh.hello.common.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bootstrap class. main method is here!
 * @author whf
 *
 */
public class StartUp {
	public static void main(String[] args) {
		bootSpring("spring-config.xml");
	}
	
	/**
	 * Boot spring application context
	 */
	public static void bootSpring(String configFileName) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configFileName);
	}
}
