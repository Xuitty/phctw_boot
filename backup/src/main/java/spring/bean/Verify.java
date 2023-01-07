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
@Table(name = "verify", uniqueConstraints = { @UniqueConstraint(columnNames = { "sno" }) })
@Component
@EntityListeners(AuditingEntityListener.class)

public class Verify {

	@Id
	@Column
	private String sno;
	@Column
	private String verify;

	public Verify() {
	}

	public Verify(String sno, String verify) {
		this.sno = sno;
		this.verify = verify;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return "Verify [sno=" + sno + ", verify=" + verify + "]";
	}

}
