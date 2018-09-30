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
@Table(name = "SECURITYMASTER")
public class SecurityMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name")
	String name;
	@Column(name = "SERIES")
	String series;
	@Column(name = "SYMBOL")
	String symbol;
	@Column(name = "MARKET")
	String market;
	@Column(name = "CREATEDATE")
	Date createDate;
	@Column(name = "SecType")
	String secType;
	public SecurityMaster()
	{
		
	}
	public SecurityMaster(String secType, Date createDate, String[] dataCsvRow) {

		this.createDate = createDate;
		this.name = dataCsvRow[0];
		this.series = dataCsvRow[1];
		this.symbol = dataCsvRow[2];
		this.market = dataCsvRow[3];
		this.secType = secType;
	}

	public SecurityMaster(Date createDate, String series, String name, String symbol) {
		this.createDate = createDate;
		this.name = name;
		this.series = series;
		this.symbol = symbol;
	}

	public SecurityMaster(Date createDate, String series, String name, String symbol, String market) {
		this.createDate = createDate;
		this.name = name;
		this.series = series;
		this.symbol = symbol;
		this.market=market;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

}
