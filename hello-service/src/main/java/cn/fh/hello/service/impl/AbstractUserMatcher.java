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
	protected List<MemberInfoDto> memberInfoList;
	protected int totalAvailableAmount;
	
	abstract int getTotalAvailableAmount();
	abstract List<MemberInfoDto> getMemberInfoList();

	/**
	 * Generate an integer between 0 ~ total-1.
	 * Derived class can override this method to 
	 * define its own generation logic.
	 * @return
	 */
	protected int nextInt() {
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
