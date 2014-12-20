package cn.fh.hello.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.fh.hello.common.dto.MemberInfoDto;
import cn.fh.hello.common.service.UserMatcher;

/**
 * Match user without restriction.
 * @author whf
 *
 */
@Component
public class DefaultUserMatcher extends AbstractUserMatcher implements
		UserMatcher {
	
	protected Predicate<MemberInfoDto> predicate;
	protected List<MemberInfoDto> filteredMemberInfoList;

	public DefaultUserMatcher(List<MemberInfoDto> memberInfoList, Predicate<MemberInfoDto> predicate) {
		this.memberInfoList = memberInfoList;
		this.totalAvailableAmount = memberInfoList.size();
		
		if (null != predicate) {
			this.predicate = predicate;
			getMemberInfoList();
		}
	}

	@Override
	public int getTotalAvailableAmount() {
		if (null != this.filteredMemberInfoList) {
			return this.filteredMemberInfoList.size();
		}

		return this.totalAvailableAmount;
	}

	@Override
	protected List<MemberInfoDto> getMemberInfoList() {
		// filter original List
		if (null != this.predicate) {
			if (null == this.filteredMemberInfoList) {
				this.filteredMemberInfoList = this.memberInfoList
						.stream()
						.filter(this.predicate)
						.collect(Collectors.toList());
			
			}

			return this.filteredMemberInfoList;
		}
		
		// no need to filter, return original List
		return this.memberInfoList;
	}

	@Override
	public String getMatchedSessionId() {
		return nextSessionId();
	}


}
