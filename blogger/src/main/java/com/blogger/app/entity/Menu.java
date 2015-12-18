package com.blogger.app.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "menu", uniqueConstraints = {
		@UniqueConstraint(columnNames = "menu_id")})
public class Menu 
	implements java.io.Serializable {
	private String menuId = "";
	private String menuName = "";
	private String menuCommand = "";
	private String menuHead = "";
	private int sortOrder;
	private String securityCode = "";
	private String status = "";
	private String createId = "";
	private Date createDate;
	private ArrayList<Menu> submenu;
	
	public Menu() {
		submenu = new ArrayList();
	}
	@Id
	@Column(name = "menu_id", unique = true, nullable = false)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name = "menu_name")
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name = "menu_command")
	public String getMenuCommand() {
		return menuCommand;
	}
	public void setMenuCommand(String menuCommand) {
		this.menuCommand = menuCommand;
	}
	@Column(name = "menu_head")
	public String getMenuHead() {
		return menuHead;
	}
	public void setMenuHead(String menuHead) {
		this.menuHead = menuHead;
	}
	@Column(name = "sort_order")
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	@Column(name = "security_code")
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "create_id")
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	@Column(name = "create_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Transient
	public ArrayList<Menu> getSubmenu() {
		return submenu;
	}
	
	public void setSubmenu(ArrayList<Menu> submenu) {
		this.submenu = submenu;
	}
	
	public void addSubmenu(Menu submenu) {
		this.submenu.add(submenu);
	}
	
	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("Menu [menuId:");
		strbuf.append(this.menuId);
		strbuf.append("][menuName:");
		strbuf.append(this.menuName);
		strbuf.append("][menuCommand:");
		strbuf.append(this.menuCommand);
		strbuf.append("][menuHead:");
		strbuf.append(this.menuHead);			
		strbuf.append("][sortOrder:");
		strbuf.append(this.sortOrder);
		strbuf.append("][securityCode:");
		strbuf.append(this.securityCode);
		strbuf.append("]");
		return strbuf.toString();
	}
}
