package spring.bean;


import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Persistent
@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = { "sno" }) })
@Component
@EntityListeners(AuditingEntityListener.class)

public class Student {
	@Id
	@Column(name = "sno")
	private String sno;
	@Column(name = "sname")
	private String sname;
	@Column(name = "sbday")
	private String sbday;
	@Column(name = "smail")
	private String smail;
	@Column(name = "spwd")
	private String spwd;
	@Column(name = "sid")
	private String sid;
	@Column(name = "cookie")
	private String cookie;
	@Column(name = "salt")
	private String salt;
	@Column(name = "salt_pass")
	private String salt_pass;
	@Column(name = "ssex")
	private int ssex = -1;
	@Column(name = "active")
	private int active = -1;

	public Student() {
	}

	public Student(String sno, String spwd) {
		this.sno = sno;
		this.spwd = spwd;
	}

	public Student(String sno, String sname, String sbday, int ssex, String smail, String spwd, String sid,
			int active) {
		this.sno = sno;
		this.sname = sname;
		this.sbday = sbday;
		this.ssex = ssex;
		this.smail = smail;
		this.spwd = spwd;
		this.sid = sid;
		this.active = active;
	}

	public Student(String sno) {
		this.sno = sno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSbday() {
		return sbday;
	}

	public void setSbday(String sbday) {
		this.sbday = sbday;
	}

	public int getSsex() {
		return ssex;
	}

	public void setSsex(int ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt_pass() {
		return salt_pass;
	}

	public void setSalt_pass(String salt_pass) {
		this.salt_pass = salt_pass;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sbday=" + sbday + ", smail=" + smail + ", spwd=" + spwd
				+ ", sid=" + sid + ", cookie=" + cookie + ", salt=" + salt + ", salt_pass=" + salt_pass + ", ssex="
				+ ssex + ", active=" + active + "]";
	}



}
