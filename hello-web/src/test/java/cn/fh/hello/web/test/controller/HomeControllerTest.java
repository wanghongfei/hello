package cn.fh.hello.web.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.fh.hello.web.controller.HomeController;
import cn.fh.hello.web.test.BaseTestClass;

@ContextConfiguration(classes = { HomeController.class })
public class HomeControllerTest extends BaseTestClass {
	@Autowired
	private HomeController homeController;
	
	private MockMvc mock;
	
	@Before
	public void initController() {
		this.mock = MockMvcBuilders.standaloneSetup(homeController)
				.build();
		
	}

	@Test
	public void test() throws Exception {
		mock.perform(get("/"))
        			.andExpect(status().isOk());
	}
}
