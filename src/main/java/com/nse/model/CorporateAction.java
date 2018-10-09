package com.nse.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nse.constant.Constant;

import sm.nse.util.Utility;

@Entity
@Table(name = "CorporateAction")
public class CorporateAction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
	SecurityMaster securityMaster;
	@Column(name = "RECORD_DT")
	Date recordDT;
	@Column(name = "BC_STRT_DT")
	Date startDate;
	@Column(name = "BC_END_DT")
	Date endDate;
	@Column(name = "EX_DT")
	Date exDate;
	
	@Column(name = "ND_STRT_DT")
	Date ndStartDate;
	@Column(name = "ND_END_DT")
	Date ndEndDate;
	@Column(name = "PURPOSE")
	String purpose;
	@Column(name = "CREATEDATE")
	Date createDate;


	public CorporateAction() {}
	public CorporateAction(SecurityMaster securityMaster,Date createDate,String[] dataCsvRow) {
		this.securityMaster = securityMaster;
		this.createDate = createDate;
		this.recordDT= Utility.parseDate(dataCsvRow[0],Constant.DateFormat.sf);
		this.startDate=Utility.parseDate(dataCsvRow[1],Constant.DateFormat.sf);
		this.endDate=Utility.parseDate(dataCsvRow[2],Constant.DateFormat.sf);
		this.exDate=Utility.parseDate(dataCsvRow[3],Constant.DateFormat.sf);
		this.ndStartDate=Utility.parseDate(dataCsvRow[4],Constant.DateFormat.sf);
		this.ndEndDate=Utility.parseDate(dataCsvRow[5],Constant.DateFormat.sf);
		this.purpose=dataCsvRow[6];
		
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

	public Date getRecordDT() {
		return recordDT;
	}

	public void setRecordDT(Date recordDT) {
		this.recordDT = recordDT;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getExDate() {
		return exDate;
	}

	public void setExDate(Date exDate) {
		this.exDate = exDate;
	}

	

	public Date getNdStartDate() {
		return ndStartDate;
	}

	public void setNdStartDate(Date ndStartDate) {
		this.ndStartDate = ndStartDate;
	}

	public Date getNdEndDate() {
		return ndEndDate;
	}

	public void setNdEndDate(Date ndEndDate) {
		this.ndEndDate = ndEndDate;
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
