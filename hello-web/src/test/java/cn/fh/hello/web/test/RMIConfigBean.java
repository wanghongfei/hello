package cn.fh.hello.web.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import cn.fh.hello.common.service.NameService;

/**
 * @deprecated
 * @author whf
 *
 */
@Configuration
public class RMIConfigBean {
	
	@Bean
	public NameService nameService() {
		RmiProxyFactoryBean rmi = new RmiProxyFactoryBean();
		rmi.setServiceUrl("rmi://localhost:8000/NameService");
		rmi.setServiceInterface(NameService.class);
		
		System.out.println(rmi.getObjectType().getName());
		System.out.println(rmi.getObject());
		
		return (NameService) rmi.getObject();
	}
}
