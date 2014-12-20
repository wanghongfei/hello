package cn.fh.hello.service.impl;

import java.util.List;
import java.util.Random;

import cn.fh.hello.common.dto.MemberInfoDto;

/**
 * Encapsulate default match logic
 * @author whf
 *
 */
public abstract class AbstractUserMatcher {
	/**
	 * unfiltered List for MemberInfoDto
	 */
	protected List<MemberInfoDto> memberInfoList;
	/**
	 * the actual amount of users that can be chosen for match
	 */
	protected int totalAvailableAmount;
	
	/**
	 * get the actual amount of users that can be chosen for match
	 */
	abstract int getTotalAvailableAmount();
	/**
	 * get unfiltered List for MemberInfoDto
	 * @return
	 */
	abstract List<MemberInfoDto> getMemberInfoList();

	/**
	 * Generate an integer between 0 ~ total-1.
	 * Derived class can override this method to 
	 * define its own generation logic.
	 * @return
	 */
	protected int nextInt() {
		// getTotalAvailableAmount() is implemented by derived class
		int tot = getTotalAvailableAmount();

		Random rand = new Random(System.currentTimeMillis());
		int next = rand.nextInt(tot);
		
		return next;
	}

	
	/**
	 * Get next matched session id.
	 * @return
	 */
	protected final String nextSessionId() {
		int pos = nextInt();

		return getMemberInfoList().get(pos).getSessionId();
	}
}
