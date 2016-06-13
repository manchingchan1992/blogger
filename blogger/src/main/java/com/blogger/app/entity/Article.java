package com.blogger.app.entity;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "articles", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Article
implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String ctitle;

	private Integer categoryID ;
	
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	
	private String content;
	
	private String tag;
	private ArrayList tagList;
	
	private boolean enabled = true;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "ctitle")
	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	@Column(name = "category_id")
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	@Column(name = "content")
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "create_user")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Column(name = "update_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name = "update_user")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
		if (tag != null && tag.trim().length() > 0){
			String[] tags = tag.split(",");
			ArrayList list = new ArrayList();
			for (String strTag: tags){
				list.add(strTag);
			}
			setTagList(list);
		}
	}
	@Transient
	public ArrayList getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList tagList) {
		this.tagList = tagList;
	}

	//Check if this is for New of Update
	@Transient
	public boolean isNew() {
		return (this.id == null);
	}
	
}
