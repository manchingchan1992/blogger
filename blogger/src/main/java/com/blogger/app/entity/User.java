package com.blogger.app.entity;

import java.sql.Date;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userid")})
public class User
implements java.io.Serializable {
	private final static String GUEST = "Guest";

	private String id;
	private String loginName;
	private String userGroup;
	private String userName;
	private String rights;
	private String securityCode;
	private String deptCode;
	private String postCode;
	private int disclaimer;
	private String regionDescription;
	private String regionID;
	private Date pwdModifyDate;
	private int enabled;

	@Id
	@Column(name = "userid", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Transient
	public Date getPwdModifyDate() {
		return pwdModifyDate;
	}

	public void setPwdModifyDate(Date pwdModifyDate) {
		this.pwdModifyDate = pwdModifyDate;
	}
	
	@Column(name = "username")
	public String getLoginName() {
		return loginName == null
				? GUEST
						: loginName;
	}

	public void setLoginName(String inLoginName) {
		loginName = inLoginName;
	}
	@Transient
	public String getUserGroup() {
		return userGroup == null
				? GUEST
						: userGroup;
	}

	public void setUserGroup(String inUserGroup) {
		userGroup = inUserGroup;
	}
	@Transient
	public String getRights() {
		return rights;
	}

	public void setRights(String inRights) {
		rights = inRights;
	}
	/**
	 * @return
	 */
	@Transient
	public String getUserName() {
		return userName;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	/**
	 * @return
	 */
	@Transient
	public String getSecurityCode() {
		return securityCode;
	}

	/**
	 * @param string
	 */
	public void setSecurityCode(String string) {
		securityCode = string;
	}

	@Transient
	public String getDeptCode() {
		return deptCode;
	}

	public String setDeptCode(String deptCode) {
		return this.deptCode = deptCode;
	}
	@Transient
	public String getPostCode() {
		return postCode;
	}

	public String setPostCode(String postCode) {
		return this.postCode = postCode;
	}
	@Transient
	public String getRegionDescription() {
		return regionDescription;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}
	@Transient
	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	@Transient
	public int getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(int disclaimer) {
		this.disclaimer = disclaimer;
	}

	@Column(name = "enabled")
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}
