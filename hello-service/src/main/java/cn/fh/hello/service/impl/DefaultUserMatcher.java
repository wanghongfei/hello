package cn.fh.hello.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.fh.hello.common.dto.MemberInfoDto;
import cn.fh.hello.common.exception.unchecked.ClassNotInitializedException;
import cn.fh.hello.common.service.UserMatcher;

/**
 * Match user without restriction.
 * <p> This is the default implementation for AbstractUserMatcher
 * 
 * @author whf
 *
 */
@Component
@Scope("prototype")
public class DefaultUserMatcher extends AbstractUserMatcher implements
		UserMatcher {
	
	/**
	 * The rules for filter
	 */
	protected Predicate<MemberInfoDto> predicate;
	/**
	 * List for MemberInfoList after filtering
	 */
	protected List<MemberInfoDto> filteredMemberInfoList;
	
	private boolean isInitialized;
	
	public DefaultUserMatcher() {
		
	}
	

	/**
	 * Construct a DefaultUserMatcher object.
	 * 
	 * @param memberInfoList A List for MemberInfoList
	 * @param predicate Filter rules. memberInfoList will be trimmed using this Predicate. Pass null for no filter.
	 */
	public DefaultUserMatcher(List<MemberInfoDto> memberInfoList, Predicate<MemberInfoDto> predicate) {
		this.memberInfoList = memberInfoList;
		this.totalAvailableAmount = memberInfoList.size();
		
		if (null != predicate) {
			this.predicate = predicate;
			getMemberInfoList();
		}
		
		this.isInitialized = true;
	}

	public void init(List<MemberInfoDto> memberInfoList, Predicate<MemberInfoDto> predicate) {
		this.memberInfoList = memberInfoList;
		this.totalAvailableAmount = memberInfoList.size();

		if (null != predicate) {
			this.predicate = predicate;
			getMemberInfoList();
		}
		
		this.isInitialized = true;
	}
	
	private void checkInitialized() {
		if (false == this.isInitialized) {
			throw new ClassNotInitializedException("please class init() method before using any methods of this object");
		}
	}

	/**
	 * get the actual amount of users that can be chosen for match
	 */
	@Override
	public int getTotalAvailableAmount() {
		checkInitialized();

		if (null != this.filteredMemberInfoList) {
			return this.filteredMemberInfoList.size();
		}

		return this.totalAvailableAmount;
	}

	/**
	 * get the filtered List for MemberInfoDto
	 */
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
		checkInitialized();

		return super.nextSessionId();
	}


}
