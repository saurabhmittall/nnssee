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
@Table(name = "ETF")
public class Etf {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	SecurityMaster securityMaster;
	@Column(name = "PREVIOUSCLOSEPRICE")
	double previousclosePrice;
	@Column(name = "OPENPRICE")
	double openPrice;
	@Column(name = "HIGHPRICE")
	double highPrice;
	@Column(name = "LOWPRICE")
	double lowPrice;
	@Column(name = "CLOSEPRICE")
	double closePrice;

	@Column(name = "NETTRADEDVALUE")
	double netTradedValue;
	@Column(name = "NETTRADEDQTY")
	int netTradedQty;
	@Column(name = "TRADES")
	int trades;
	@Column(name = "WEEK52HIGH")
	double week52High;
	@Column(name = "WEEK52LOW")
	double week52Low;
	@Column(name = "UNDERLYING")
	String unserLying;
	@Column(name = "CREATEDATE")
	Date createDate;
	public Etf() {}
	public Etf(SecurityMaster securityMaster, Date createDate, String[] dataCsvRow) {
		this.securityMaster = securityMaster;
		this.createDate = createDate;
		this.previousclosePrice = Utility.parseDouble(dataCsvRow[0]);
		this.openPrice = Utility.parseDouble(dataCsvRow[1]);
		this.highPrice = Utility.parseDouble(dataCsvRow[2]);
		this.lowPrice = Utility.parseDouble(dataCsvRow[3]);
		this.closePrice = Utility.parseDouble(dataCsvRow[4]);
		this.netTradedValue = Utility.parseDouble(dataCsvRow[5]);
		this.netTradedQty = Utility.parseInteger(dataCsvRow[6]);
		this.trades = Utility.parseInteger(dataCsvRow[7]);
		this.week52High = Utility.parseDouble(dataCsvRow[8]);
		this.week52Low = Utility.parseDouble(dataCsvRow[9]);
		this.unserLying = dataCsvRow[10];
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

	public double getPreviousclosePrice() {
		return previousclosePrice;
	}

	public void setPreviousclosePrice(double previousclosePrice) {
		this.previousclosePrice = previousclosePrice;
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

	public int getNetTradedQty() {
		return netTradedQty;
	}

	public void setNetTradedQty(int netTradedQty) {
		this.netTradedQty = netTradedQty;
	}

	public int getTrades() {
		return trades;
	}

	public void setTrades(int trades) {
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

	public String getUnserLying() {
		return unserLying;
	}

	public void setUnserLying(String unserLying) {
		this.unserLying = unserLying;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
