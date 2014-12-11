package ch.genidea.geniweb.base.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author genidea
 * @author ariesp
 */
@Entity
@Table(name = "tifragment_user")
public class User implements Serializable {

	private static final long serialVersionUID = 8450571008467792266L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "uuid", unique = true)
	private String uuid;

	@Column(name = "username", length = 10, unique = true, nullable = false)
	private String username;

	private String password;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "full_name", length = 100)
	private String fullName;

	@Column(name = "position", nullable = false)
	private String position;

	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	
	@Column(name = "email", length = 100)
	private String email;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private SecurityCode securityCode;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
	private Role role;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updateBy;

	private boolean accountExpired;

	private boolean accountLocked;

	private boolean enabled;

	public User() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SecurityCode getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(SecurityCode securityCode) {
		this.securityCode = securityCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}