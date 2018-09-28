package com.nse.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ETL_TABLEMASTER")
public class ETLTableMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "DbTable")
	String dbTable;
	@Column(name = "NseNamePattern")
	String nseNamePattern;
	@Column(name = "NSEURLPattern")
	String nseUrlPattern;
	@Column(name = "NseUrlFileType")
	String nseUrlFileType;
	@Column(name = "NseNameFileType")
	String nseNameFileType;
	@Column(name = "status")
	int status;
	@Column(name = "Description")
	String description;
	@Column(name = "CreateDate")
	Date createDate;
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDbTable() {
		return dbTable;
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	public String getNseNamePattern() {
		return nseNamePattern;
	}
	public void setNseNamePattern(String nseNamePattern) {
		this.nseNamePattern = nseNamePattern;
	}
	public String getNseUrlPattern() {
		return nseUrlPattern;
	}
	public void setNseUrlPattern(String nseUrlPattern) {
		this.nseUrlPattern = nseUrlPattern;
	}
	public String getNseUrlFileType() {
		return nseUrlFileType;
	}
	public void setNseUrlFileType(String nseUrlFileType) {
		this.nseUrlFileType = nseUrlFileType;
	}
	public String getNseNameFileType() {
		return nseNameFileType;
	}
	public void setNseNameFileType(String nseNameFileType) {
		this.nseNameFileType = nseNameFileType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
