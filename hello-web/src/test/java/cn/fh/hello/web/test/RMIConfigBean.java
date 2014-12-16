package cn.fh.hello.web.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import cn.fh.hello.common.service.NameService;

@Configuration
public class RMIConfigBean {
	
	@Bean
	public NameService nameService() {
		RmiProxyFactoryBean rmi = new RmiProxyFactoryBean();
		rmi.setServiceUrl("rmi://localhost:8000/NameService");
		rmi.setServiceInterface(NameService.class);
		
		return (NameService) rmi.getObject();
	}
}
