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
@Table(name = "categories", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Category
implements java.io.Serializable {

	private Integer id;
	private String name;
	private String cname;

	private String parentID;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "cname")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "parent_id")
	public String getParentID() {
		return parentID;
	}
	
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	
	
}
