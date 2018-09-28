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
@Table(name = "ETL_COLMAP")
public class EtlColMap {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "TableMasterId")
	long tableMasterId;
	@Column(name = "DBColName")
	String dbColName;
	@Column(name = "FileColName")
	String fileColName;
	@Column(name = "DBColDataType")
	String dbColDataType;
	@Column(name = "FileColDataType")
	String fileColDataType;
	@Column(name = "status")
	int status;
	@Column(name = "ForigenKey")
	int forigenKey;
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
	public long getTableMasterId() {
		return tableMasterId;
	}
	public void setTableMasterId(long tableMasterId) {
		this.tableMasterId = tableMasterId;
	}
	public String getDbColName() {
		return dbColName;
	}
	public void setDbColName(String dbColName) {
		this.dbColName = dbColName;
	}
	public String getFileColName() {
		return fileColName;
	}
	public void setFileColName(String fileColName) {
		this.fileColName = fileColName;
	}
	public String getDbColDataType() {
		return dbColDataType;
	}
	public void setDbColDataType(String dbColDataType) {
		this.dbColDataType = dbColDataType;
	}
	public String getFileColDataType() {
		return fileColDataType;
	}
	public void setFileColDataType(String fileColDataType) {
		this.fileColDataType = fileColDataType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getForigenKey() {
		return forigenKey;
	}
	public void setForigenKey(int forigenKey) {
		this.forigenKey = forigenKey;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
