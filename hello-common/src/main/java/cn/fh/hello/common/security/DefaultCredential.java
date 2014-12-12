package cn.fh.hello.common.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of Credential interface
 * @author whf
 *
 */
public class DefaultCredential implements Credential {
	protected List<String> roleList = new ArrayList<String>();
	protected String username;
	protected String nickName;
	protected Integer credites;
	protected Integer id;

	@Override
	public Integer getCredits() {
		return this.credites;
	}

	
	public DefaultCredential(Integer id, String username, String nickName, Integer credits) {
		this.username = username;
		this.nickName = nickName;
		this.credites = credits;
	}
	

	@Override
	public void setUsername(String name) {
		this.username = name;
	}


	@Override
	public void setNickName(String name) {
		this.nickName = name;
	}


	@Override
	public void setCredits(Integer point) {
		this.credites = point;
	}


	@Override
	public Integer getId() {
		return this.id;
	}
	

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getNickName() {
		return this.nickName;
	}
	
	@Override
	public final void addRole(String roleName) {
		this.roleList.add(roleName);
	}
	
	@Override
	public final void addRoles(String[] roleNames) {
		for (String role : roleNames) {
			this.roleList.add(role);
		}
	}

	/**
	 * 得到该用户所有role
	 */
	@Override
	public final List<String> getRoleList() {
		return this.roleList;
	}

	/**
	 * 判断该用户是否具有指定role
	 */
	@Override
	public final boolean hasRole(String roleName) {
		return this.roleList.contains(roleName);
	}

}
