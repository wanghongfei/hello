package cn.fh.hello.service.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the chat_record database table.
 * 
 */
@Entity
@Table(name="chat_record")
@NamedQuery(name="ChatRecord.findAll", query="SELECT c FROM ChatRecord c")
public class ChatRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Object content;

	@Column(name="target_member_email")
	private String targetMemberEmail;

	@Temporal(TemporalType.DATE)
	private Date time;

	//bi-directional many-to-one association to Member
	@ManyToOne
	private Member member;

	public ChatRecord() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getContent() {
		return this.content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getTargetMemberEmail() {
		return this.targetMemberEmail;
	}

	public void setTargetMemberEmail(String targetMemberEmail) {
		this.targetMemberEmail = targetMemberEmail;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}