package cn.fh.hello.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.fh.hello.common.service.UserMatcher;

/**
 * Match user without restriction.
 * @author whf
 *
 */
@Component
public class DefaultUserMatcher extends AbstractUserMatcher implements
		UserMatcher {
	

	public DefaultUserMatcher(List<String> sessionIdList) {
		this.sessionIdList = sessionIdList;
		this.totalAvailableAmount = sessionIdList.size();
	}

	@Override
	public int getTotalAvailableAmount() {
		return this.totalAvailableAmount;
	}

	@Override
	protected List<String> getSessionIdList() {
		return this.sessionIdList;
	}

	@Override
	public String getMatchedSessionId() {
		return nextSessionId();
	}

}
