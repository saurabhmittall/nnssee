package com.nse.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nse.constant.Constant;

import sm.nse.util.Utility;

@Entity
@Table(name = "BoardMeetings")
public class BoardMeetings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
	SecurityMaster securityMaster;
	@Column(name = "BMDATE")
	Date bmDate;
	@Column(name = "PURPOSE")
	String purpose;

	@Column(name = "CREATEDATE")
	Date createDate;
	public BoardMeetings() {}
	public BoardMeetings(SecurityMaster securityMaster, Date createDate,String[] dataCsvRow) {
		this.securityMaster = securityMaster;
		this.createDate = createDate;
		this.bmDate = Utility.parseDate(dataCsvRow[0],Constant.DateFormat.sf);
		this.purpose = dataCsvRow[1];
		
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SecurityMaster getSecurityMaster() {
		return securityMaster;
	}

	public void setSecurityMaster(SecurityMaster securityMaster) {
		this.securityMaster = securityMaster;
	}

	

	public Date getBmDate() {
		return bmDate;
	}

	public void setBmDate(Date bmDate) {
		this.bmDate = bmDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
