package cn.fh.hello.common.service;

public interface UserMatcher {
	/**
	 * get the actual amount of users that can be chosen for match
	 * @return
	 */
	int getTotalAvailableAmount();
	/**
	 * get the next matched user's session id
	 * @return
	 */
	String getMatchedSessionId();
}
