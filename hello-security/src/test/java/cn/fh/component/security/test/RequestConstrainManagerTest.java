package cn.fh.component.security.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.fh.component.security.RequestConstrainManager;

public class RequestConstrainManagerTest {
	RequestConstrainManager rcm;
	
	@Before
	public void init() {
		this.rcm = new RequestConstrainManager();
	}
	
	@Test
	public void test() {
		rcm.put("/hello/kitty", Arrays.asList(new String[] {"USER"} ));
		rcm.put("/hello/kitty/doggy", Arrays.asList(new String[] {"USER"} ));
		rcm.put("/hello", Arrays.asList(new String[] {"USER"} ));

		rcm.put("/hello/hi/*", Arrays.asList(new String[] {"ADMIN"} ));
		rcm.put("/hello/hi/doggy", Arrays.asList(new String[] {"NONE"} ));
		rcm.put("/hello/hi/cat", Arrays.asList(new String[] {"NONE"} ));
		
		List<String> roleList = null;
		roleList = rcm.get("/hello/hi/abcad");
		Assert.assertFalse(roleList.isEmpty());
		Assert.assertEquals("ADMIN", roleList.get(0));

		roleList = rcm.get("/hello/hi/abcad/ef");
		Assert.assertNull(roleList);

		roleList = rcm.get("/hello/kitty");
		Assert.assertFalse(roleList.isEmpty());
		Assert.assertEquals("USER", roleList.get(0));

		roleList = rcm.get("/hello");
		Assert.assertFalse(roleList.isEmpty());
		Assert.assertEquals("USER", roleList.get(0));

		roleList = rcm.get("/hello/hi/doggy");
		Assert.assertFalse(roleList.isEmpty());
		Assert.assertEquals("NONE", roleList.get(0));

		roleList = rcm.get("/hello/hi/cat");
		Assert.assertFalse(roleList.isEmpty());
		Assert.assertEquals("NONE", roleList.get(0));
	}

}
