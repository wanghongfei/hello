package cn.fh.hello.service.test.nonespring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.fh.hello.common.component.Constant.EmotionalStatus;
import cn.fh.hello.common.component.Constant.Gender;
import cn.fh.hello.common.dto.MemberInfoDto;
import cn.fh.hello.common.service.UserMatcher;
import cn.fh.hello.service.impl.DefaultUserMatcher;

public class UserMatcherTest {
	private List<MemberInfoDto> infoList;
	
	@Before
	public void initData() {
		this.infoList = new ArrayList<MemberInfoDto>( Arrays.asList(new MemberInfoDto[] {
				new MemberInfoDto("aaa", 18, "山东", "临沂", Gender.FEMALE, EmotionalStatus.SINGLE),
				new MemberInfoDto("bbb", 20, "山东", "临沂", Gender.FEMALE, EmotionalStatus.SINGLE),
				new MemberInfoDto("ccc", 35, "山东", "临沂", Gender.FEMALE, EmotionalStatus.SINGLE),
				new MemberInfoDto("ddd", 14, "山东", "临沂", Gender.FEMALE, EmotionalStatus.SINGLE),
				new MemberInfoDto("eee", 22, "云南", "昆明", Gender.MALE, EmotionalStatus.IN_LOVE),
				new MemberInfoDto("fff", 22, "山东", "临沂", Gender.MALE, EmotionalStatus.IN_LOVE),
				new MemberInfoDto("ggg", 22, "山东", "临沂", Gender.FEMALE, EmotionalStatus.DIVORCED)
		}) );

	}

	@Test
	public void testMatchWithoutFilter() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, null);
		
		Assert.assertEquals(7, matcher.getTotalAvailableAmount());
		
		// perform match for 10 times
		for (int ix = 0 ; ix < 10 ; ++ix) {
			String sid = matcher.getMatchedSessionId();
			Assert.assertTrue(containsSid(infoList, sid));
			
			System.out.println("Match " + ix + " times, sid=" + sid + ", done");
		}
		System.out.println("all done");

	}
	
	@Test
	public void testMatchWithGender() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, (d) -> d.getGender() == Gender.MALE);
		Assert.assertEquals(2, matcher.getTotalAvailableAmount());

		// perform match for 10 times
		for (int ix = 0 ; ix < 10 ; ++ix) {
			String sid = matcher.getMatchedSessionId();
			Assert.assertTrue( sid.equals("eee") || sid.equals("fff") );
			
			System.out.println("Match " + ix + " times, sid=" + sid + ", done");
		}
		System.out.println("all done");
		
	}
	
	@Test
	public void testMatchWithAge() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, (d) -> d.getAge().intValue() <= 20);
		Assert.assertEquals(3, matcher.getTotalAvailableAmount());
		
	}
	
	@Test
	public void teatMatchWithStatus() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, (d) -> d.getStatus() == EmotionalStatus.DIVORCED);
		Assert.assertEquals(1, matcher.getTotalAvailableAmount());
	}

	@Test
	public void teatMatchWithProvince() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, (d) -> d.getProvince().equals("山东"));
		Assert.assertEquals(6, matcher.getTotalAvailableAmount());
	}

	@Test
	public void teatMatchWithCity() {
		UserMatcher matcher = new DefaultUserMatcher(infoList, (d) -> d.getCity().equals("昆明"));
		Assert.assertEquals(1, matcher.getTotalAvailableAmount());
	}
	
	private boolean containsSid(List<MemberInfoDto> infoList, String sid) {
		return infoList.stream()
			.anyMatch( (info) -> info.getSessionId().equals(sid) );
	}
}
