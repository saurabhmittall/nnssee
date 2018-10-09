package com.nse.model;

import java.util.Date;
import java.util.List;

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
@Table(name = "MarketData")
public class MarketData {
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
	long netTradedQty;
	
	@Column(name = "IND_SEC")
	String indSec;
	@Column(name = "CORP_IND")
	String corpInd;
	@Column(name = "TRADES")
	long trades;
	@Column(name = "HI_52_WK")
	double week52High;
	@Column(name = "LO_52_WK")
	double week52Low;

	@Column(name = "CREATEDATE")
	Date createDate;
	public MarketData() {}
	public MarketData(SecurityMaster securityMaster, Date createDate, String[] dataCsvRow) {
		this.securityMaster = securityMaster;
		this.createDate = createDate;
		this.previousClosePrice = Utility.parseDouble(dataCsvRow[0]);
		this.openPrice = Utility.parseDouble(dataCsvRow[1]);
		this.highPrice = Utility.parseDouble(dataCsvRow[2]);
		this.lowPrice = Utility.parseDouble(dataCsvRow[3]);
		this.closePrice = Utility.parseDouble(dataCsvRow[4]);
		this.netTradedValue = Utility.parseDouble(dataCsvRow[5]);
		this.netTradedQty = Utility.parseLong(dataCsvRow[6]);
		this.indSec = dataCsvRow[7];

		this.corpInd = dataCsvRow[8];
		this.trades = Utility.parseLong(dataCsvRow[9]);
		this.week52High = Utility.parseDouble(dataCsvRow[10]);
		this.week52Low = Utility.parseDouble(dataCsvRow[11]);
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

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
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

	public double getNetTradedValue() {
		return netTradedValue;
	}

	public void setNetTradedValue(double netTradedValue) {
		this.netTradedValue = netTradedValue;
	}

	public String getIndSec() {
		return indSec;
	}

	public void setIndSec(String indSec) {
		this.indSec = indSec;
	}

	public String getCorpInd() {
		return corpInd;
	}

	public void setCorpInd(String corpInd) {
		this.corpInd = corpInd;
	}

	public long getTrades() {
		return trades;
	}

	public void setTrades(long trades) {
		this.trades = trades;
	}

	public double getWeek52High() {
		return week52High;
	}

	public void setWeek52High(double week52High) {
		this.week52High = week52High;
	}

	public double getWeek52Low() {
		return week52Low;
	}

	public void setWeek52Low(double week52Low) {
		this.week52Low = week52Low;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getNetTradedQty() {
		return netTradedQty;
	}

	public void setNetTradedQty(long netTradedQty) {
		this.netTradedQty = netTradedQty;
	}

}
