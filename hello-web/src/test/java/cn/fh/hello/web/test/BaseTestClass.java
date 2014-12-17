package cn.fh.hello.web.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"} )
//@ContextConfiguration(classes = {WebConfigBean.class})
//@TransactionConfiguration
//@Transactional(readOnly = true)
@WebAppConfiguration
//@ComponentScan(basePackages = {"com.ejoy.ezoo.model" })
public class BaseTestClass {

}
