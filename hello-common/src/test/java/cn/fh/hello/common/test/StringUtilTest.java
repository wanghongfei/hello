package cn.fh.hello.common.test;


import org.junit.Test;
import org.junit.Assert;

import cn.fh.hello.common.component.utils.StringUtils;

public class StringUtilTest {
	@Test
	public void testTrimQuotation() {
		String before = "\"apple\"";
		String after = StringUtils.trimQuotation(before);
		
		System.out.println("before :" + before);
		Assert.assertEquals("apple", after);
	}
}
