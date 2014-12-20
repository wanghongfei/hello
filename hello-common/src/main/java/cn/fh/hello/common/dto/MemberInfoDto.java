package cn.fh.hello.common.dto;

import cn.fh.hello.common.component.Constant.EmotionalStatus;
import cn.fh.hello.common.component.Constant.Gender;

public class MemberInfoDto {
	private String sessionId;
	private Integer age;
	private String province;
	private String city;
	private Gender gender;

	/**
	 * SINGLE, MARRIED, IN_LOVE, DIVORCED
	 */
	private EmotionalStatus status;
	
	public MemberInfoDto() {
		
	}

	public MemberInfoDto(String sessionId, Integer age, String province,
			String city, Gender gender, EmotionalStatus status) {
		this.sessionId = sessionId;
		this.age = age;
		this.province = province;
		this.city = city;
		this.gender = gender;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (false == obj instanceof MemberInfoDto) {
			return false;
		}
		
		MemberInfoDto other = (MemberInfoDto)obj;
		return sessionId.equals(other.sessionId) &&
				age.equals(other.age) &&
				province.equals(other.province) &&
				city.equals(other.city) &&
				gender.equals(other.gender) &&
				status.equals(other.sessionId);
	}

	@Override
	public int hashCode() {
		int code = 17;

		code = code * 31 + sessionId.hashCode();
		code = code * 31 + age.hashCode();
		code = code * 31 + province.hashCode();
		code = code * 31 + city.hashCode();
		code = code * 31 + gender.hashCode();
		code = code * 31 + status.hashCode();

		return code;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public EmotionalStatus getStatus() {
		return status;
	}
	public void setStatus(EmotionalStatus status) {
		this.status = status;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


}
