package cn.fh.hello.common.security;

import java.util.List;

public interface Credential {
	Integer getId();
	String getUsername();
	void setUsername(String name);
	String getNickName();
	void setNickName(String name);
	
	Integer getCredits();
	void setCredits(Integer point);

	List<String> getRoleList();
	boolean hasRole(String roleName);
	
	void addRole(String roleName);
	void addRoles(String[] roleNames);
	
	/**
	 * 实现类的对象在ServletContext中的key名
	 */
	String CREDENTIAL_CONTEXT_ATTRIBUTE = "CURRENT_USER_CREDENTIAL";
}