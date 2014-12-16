package cn.fh.hello.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fh.hello.common.service.NameService;

@Controller
public class HomeController {
	public static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private NameService nameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		if (logger.isDebugEnabled()) {
			logger.debug(nameService.greeting("Neo"));
		}
		
		return "dog";
	}
}
