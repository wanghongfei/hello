package cn.fh.hello.service.impl;

import org.springframework.stereotype.Component;

import cn.fh.hello.service.NameService;

@Component
public class DefaultNameService implements NameService {

	@Override
	public String greeting(String name) {
		return "hello, " + name;
	}

}
