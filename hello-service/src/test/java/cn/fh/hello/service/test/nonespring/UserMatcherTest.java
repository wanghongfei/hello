package cn.fh.hello.service.test.nonespring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import cn.fh.hello.common.service.UserMatcher;
import cn.fh.hello.service.impl.DefaultUserMatcher;

public class UserMatcherTest {
	@Test
	public void testGetSessionId() {
		List<String> sidList = new ArrayList<String>( Arrays.asList(new String[] {"bruce", "Neo", "dog"}) );
		UserMatcher matcher = new DefaultUserMatcher(sidList);
		
		Assert.assertEquals(3, matcher.getTotalAvailableAmount());
		
		// perform match for 10 times
		for (int ix = 0 ; ix < 10 ; ++ix) {
			String sid = matcher.getMatchedSessionId();
			Assert.assertTrue(sidList.contains(sid));
			
			System.out.println("Match " + ix + " times:done");
		}
	}
}
