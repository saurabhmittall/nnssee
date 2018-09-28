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

@Entity
@Table(name = "CorporateAction")
public class CorporateAction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
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
	Date ndstartDate;
	@Column(name = "ND_END_DT")
	Date ndEndDate;
	@Column(name = "PURPOSE")
	String purpose;
	@Column(name = "CREATEDATE")
	Date createDate;

	public CorporateAction(com.nse.csv.CorporateAction animeDTO) {
		// TODO Auto-generated constructor stub
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

	public Date getNdstartDate() {
		return ndstartDate;
	}

	public void setNdstartDate(Date ndstartDate) {
		this.ndstartDate = ndstartDate;
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
