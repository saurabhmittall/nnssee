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

import sm.nse.util.Utility;

@Entity
@Table(name = "CorporateBonds")
public class CorporateBonds {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	SecurityMaster securityMaster;
	@Column(name = "PREV_CL_PR")
	double previousClosePrice;
	@Column(name = "OPEN_PRICE")
	double openPrice;
	@Column(name = "HIGH_PRICE")
	double highPrice;
	@Column(name = "LOW_PRICE")
	double lowPrice;

	@Column(name = "CLOSE_PRICE")
	double closePrice;
	@Column(name = "NET_TRDVAL")
	double netTradedValue;
	@Column(name = "NET_TRDQTY")
	long tradedQty;
	@Column(name = "CORP_IND")
	double corpInd;
	@Column(name = "TRADES")
	long trades;
	@Column(name = "HI_52_WK")
	double hi52wk;
	@Column(name = "LO_52_WK")
	double lo52wk;
	@Column(name = "CREATEDATE")
	Date createDate;
	public CorporateBonds() {}
	public CorporateBonds(SecurityMaster securityMaster, Date createDate, String[] dataCsvRow) {
		this.securityMaster = securityMaster;
		this.createDate = createDate;
		this.previousClosePrice = Utility.parseDouble(dataCsvRow[0]);
		this.openPrice = Utility.parseDouble(dataCsvRow[1]);
		this.highPrice = Utility.parseDouble(dataCsvRow[2]);
		this.lowPrice = Utility.parseDouble(dataCsvRow[3]);
		this.closePrice = Utility.parseDouble(dataCsvRow[4]);
		this.netTradedValue = Utility.parseDouble(dataCsvRow[5]);
		this.tradedQty = Utility.parseLong(dataCsvRow[6]);
		this.corpInd = Utility.parseDouble(dataCsvRow[7]);
		this.trades = Utility.parseLong(dataCsvRow[8]);
		this.hi52wk = Utility.parseDouble(dataCsvRow[9]);
		this.lo52wk = Utility.parseDouble(dataCsvRow[10]);

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

	public double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	
	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getNetTradedValue() {
		return netTradedValue;
	}

	public void setNetTradedValue(double netTradedValue) {
		this.netTradedValue = netTradedValue;
	}

	public long getTradedQty() {
		return tradedQty;
	}

	public void setTradedQty(long tradedQty) {
		this.tradedQty = tradedQty;
	}

	public double getCorpInd() {
		return corpInd;
	}

	public void setCorpInd(double corpInd) {
		this.corpInd = corpInd;
	}

	public long getTrades() {
		return trades;
	}

	public void setTrades(long trades) {
		this.trades = trades;
	}

	public double getHi52wk() {
		return hi52wk;
	}

	public void setHi52wk(double hi52wk) {
		this.hi52wk = hi52wk;
	}

	public double getLo52wk() {
		return lo52wk;
	}

	public void setLo52wk(double lo52wk) {
		this.lo52wk = lo52wk;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
