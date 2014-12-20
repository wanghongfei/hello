package cn.fh.hello.service.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@Table(name="member")
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int age;

	@Column(name="avatar_path")
	private String avatarPath;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String city;

	private int credits;

	private String email;

	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name="last_seen_time")
	private Date lastSeenTime;

	private String nickname;

	private String province;

	private String status;

	//bi-directional many-to-one association to ChatRecord
	@OneToMany(mappedBy="member")
	private List<ChatRecord> chatRecordList = new ArrayList<ChatRecord>();

	//uni-directional many-to-many association to Member
	@ManyToMany
	@JoinTable(
		name="member_has_friend"
		, joinColumns={
			@JoinColumn(name="friend_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="member_id")
			}
		)
	private List<Member> friendList = new ArrayList<Member>();

	public Member() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAvatarPath() {
		return this.avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLastSeenTime() {
		return this.lastSeenTime;
	}

	public void setLastSeenTime(Date lastSeenTime) {
		this.lastSeenTime = lastSeenTime;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ChatRecord> getChatRecordList() {
		return this.chatRecordList;
	}

	public void setChatRecordList(List<ChatRecord> chatRecordList) {
		this.chatRecordList = chatRecordList;
	}

	public ChatRecord addChatRecordList(ChatRecord chatRecordList) {
		getChatRecordList().add(chatRecordList);
		chatRecordList.setMember(this);

		return chatRecordList;
	}

	public ChatRecord removeChatRecordList(ChatRecord chatRecordList) {
		getChatRecordList().remove(chatRecordList);
		chatRecordList.setMember(null);

		return chatRecordList;
	}

	public List<Member> getFriendList() {
		return this.friendList;
	}

	public void setFriendList(List<Member> friendList) {
		this.friendList = friendList;
	}

}