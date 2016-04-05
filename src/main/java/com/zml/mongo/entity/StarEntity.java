package com.zml.mongo.entity;

import java.io.Serializable;
import java.util.Date;


public class StarEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8259492034424759507L;

	private String id; 			//项目id
	
	private Oid _id;
	
	private String name;		//项目名称
	
	private String full_name;	//全名
	
	private String description;//描述
	
	private Boolean fork;		//是否克隆
	
	private String url;	
	
	private String forks_url;
	
//	private Date created_at;
//	
//	private Date updated_at;
	
	private Integer forks_count;
	
	private Integer watchers_count;
	
	public class Oid{
		private String $oid;
		
		public String get$oid() {
			return $oid;
		}
 
		public void set$oid(String $oid) {
			this.$oid = $oid;
		}
 
	}
	
	public class DateTime {
		private Date $date;

		public Date get$date() {
			return $date;
		}

		public void set$date(Date $date) {
			this.$date = $date;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFork() {
		return fork;
	}

	public void setFork(Boolean fork) {
		this.fork = fork;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getForks_url() {
		return forks_url;
	}

	public void setForks_url(String forks_url) {
		this.forks_url = forks_url;
	}

	public Integer getForks_count() {
		return forks_count;
	}

	public void setForks_count(Integer forks_count) {
		this.forks_count = forks_count;
	}

	public Integer getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(Integer watchers_count) {
		this.watchers_count = watchers_count;
	}

	public Oid get_id() {
		return _id;
	}

	public void set_id(Oid _id) {
		this._id = _id;
	}

}
