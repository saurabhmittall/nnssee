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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "analytics")
public class Analytics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	SecurityMaster securityMaster;
	@Column(name = "createDate")
	Date createDate;
	@Column(name = "AvEod3")
	double avEod3;
	@Column(name = "AvOpen3")
	double avOpen3;
	@Column(name = "AvLow3")
	double avLow3;
	@Column(name = "AvHigh3")
	double avHigh3;
	@Column(name = "Qty3")
	long qty3;
	@Column(name = "Volume3")
	double volume3;

	@Column(name = "AvEod5")
	double avEod5;
	@Column(name = "AvOpen5")
	double avOpen5;
	@Column(name = "AvLow5")
	double avLow5;
	@Column(name = "AvHigh5")
	double avHigh5;
	@Column(name = "Qty5")
	long qty5;
	@Column(name = "Volume5")
	double volume5;

	@Column(name = "AvEod15")
	double avEod15;
	@Column(name = "AvOpen15")
	double avOpen15;
	@Column(name = "AvLow15")
	double avLow15;
	@Column(name = "AvHigh15")
	double avHigh15;
	@Column(name = "Qty15")
	long qty15;
	@Column(name = "Volume15")
	double volume15;

	@Column(name = "AvEod30")
	double avEod30;
	@Column(name = "AvOpen30")
	double avOpen30;
	@Column(name = "AvLow30")
	double avLow30;
	@Column(name = "AvHigh30")
	double avHigh30;
	@Column(name = "Qty30")
	long qty30;
	@Column(name = "Volume30")
	double volume30;

	@Column(name = "AvEod90")
	double avEod90;
	@Column(name = "AvOpen90")
	double avOpen90;
	@Column(name = "AvLow90")
	double avLow90;
	@Column(name = "AvHigh90")
	double avHigh90;
	@Column(name = "Qty90")
	long qty90;
	@Column(name = "Volume90")
	double volume90;

	@Column(name = "AvEod180")
	double avEod180;
	@Column(name = "AvOpen180")
	double avOpen180;
	@Column(name = "AvLow180")
	double avLow180;
	@Column(name = "AvHigh180")
	double avHigh180;
	@Column(name = "Qty180")
	long qty180;
	@Column(name = "Volume180")
	double volume180;

	@Column(name = "traders3")
	long traders3;
	@Column(name = "traders5")
	long traders5;
	@Column(name = "traders15")
	long traders15;
	@Column(name = "traders30")
	long traders30;
	@Column(name = "traders90")
	long traders90;
	@Column(name = "traders180")
	long traders180;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getAvEod3() {
		return avEod3;
	}

	public void setAvEod3(double avEod3) {
		this.avEod3 = avEod3;
	}

	public double getAvOpen3() {
		return avOpen3;
	}

	public void setAvOpen3(double avOpen3) {
		this.avOpen3 = avOpen3;
	}

	public double getAvLow3() {
		return avLow3;
	}

	public void setAvLow3(double avLow3) {
		this.avLow3 = avLow3;
	}

	public double getAvHigh3() {
		return avHigh3;
	}

	public void setAvHigh3(double avHigh3) {
		this.avHigh3 = avHigh3;
	}

	public long getQty3() {
		return qty3;
	}

	public void setQty3(long qty3) {
		this.qty3 = qty3;
	}

	public double getVolume3() {
		return volume3;
	}

	public void setVolume3(double volume3) {
		this.volume3 = volume3;
	}

	public double getAvEod5() {
		return avEod5;
	}

	public void setAvEod5(double avEod5) {
		this.avEod5 = avEod5;
	}

	public double getAvOpen5() {
		return avOpen5;
	}

	public void setAvOpen5(double avOpen5) {
		this.avOpen5 = avOpen5;
	}

	public double getAvLow5() {
		return avLow5;
	}

	public void setAvLow5(double avLow5) {
		this.avLow5 = avLow5;
	}

	public double getAvHigh5() {
		return avHigh5;
	}

	public void setAvHigh5(double avHigh5) {
		this.avHigh5 = avHigh5;
	}

	public long getQty5() {
		return qty5;
	}

	public void setQty5(long qty5) {
		this.qty5 = qty5;
	}

	public double getVolume5() {
		return volume5;
	}

	public void setVolume5(double volume5) {
		this.volume5 = volume5;
	}

	public double getAvEod15() {
		return avEod15;
	}

	public void setAvEod15(double avEod15) {
		this.avEod15 = avEod15;
	}

	public double getAvOpen15() {
		return avOpen15;
	}

	public void setAvOpen15(double avOpen15) {
		this.avOpen15 = avOpen15;
	}

	public double getAvLow15() {
		return avLow15;
	}

	public void setAvLow15(double avLow15) {
		this.avLow15 = avLow15;
	}

	public double getAvHigh15() {
		return avHigh15;
	}

	public void setAvHigh15(double avHigh15) {
		this.avHigh15 = avHigh15;
	}

	public long getQty15() {
		return qty15;
	}

	public void setQty15(long qty15) {
		this.qty15 = qty15;
	}

	public double getVolume15() {
		return volume15;
	}

	public void setVolume15(double volume15) {
		this.volume15 = volume15;
	}

	public double getAvEod30() {
		return avEod30;
	}

	public void setAvEod30(double avEod30) {
		this.avEod30 = avEod30;
	}

	public double getAvOpen30() {
		return avOpen30;
	}

	public void setAvOpen30(double avOpen30) {
		this.avOpen30 = avOpen30;
	}

	public double getAvLow30() {
		return avLow30;
	}

	public void setAvLow30(double avLow30) {
		this.avLow30 = avLow30;
	}

	public double getAvHigh30() {
		return avHigh30;
	}

	public void setAvHigh30(double avHigh30) {
		this.avHigh30 = avHigh30;
	}

	public long getQty30() {
		return qty30;
	}

	public void setQty30(long qty30) {
		this.qty30 = qty30;
	}

	public double getVolume30() {
		return volume30;
	}

	public void setVolume30(double volume30) {
		this.volume30 = volume30;
	}

	public double getAvEod90() {
		return avEod90;
	}

	public void setAvEod90(double avEod90) {
		this.avEod90 = avEod90;
	}

	public double getAvOpen90() {
		return avOpen90;
	}

	public void setAvOpen90(double avOpen90) {
		this.avOpen90 = avOpen90;
	}

	public double getAvLow90() {
		return avLow90;
	}

	public void setAvLow90(double avLow90) {
		this.avLow90 = avLow90;
	}

	public double getAvHigh90() {
		return avHigh90;
	}

	public void setAvHigh90(double avHigh90) {
		this.avHigh90 = avHigh90;
	}

	public long getQty90() {
		return qty90;
	}

	public void setQty90(long qty90) {
		this.qty90 = qty90;
	}

	public double getVolume90() {
		return volume90;
	}

	public void setVolume90(double volume90) {
		this.volume90 = volume90;
	}

	public double getAvEod180() {
		return avEod180;
	}

	public void setAvEod180(double avEod180) {
		this.avEod180 = avEod180;
	}

	public double getAvOpen180() {
		return avOpen180;
	}

	public void setAvOpen180(double avOpen180) {
		this.avOpen180 = avOpen180;
	}

	public double getAvLow180() {
		return avLow180;
	}

	public void setAvLow180(double avLow180) {
		this.avLow180 = avLow180;
	}

	public double getAvHigh180() {
		return avHigh180;
	}

	public void setAvHigh180(double avHigh180) {
		this.avHigh180 = avHigh180;
	}

	public long getQty180() {
		return qty180;
	}

	public void setQty180(long qty180) {
		this.qty180 = qty180;
	}

	public double getVolume180() {
		return volume180;
	}

	public void setVolume180(double volume180) {
		this.volume180 = volume180;
	}

	public long getTraders3() {
		return traders3;
	}

	public void setTraders3(long traders3) {
		this.traders3 = traders3;
	}

	public long getTraders5() {
		return traders5;
	}

	public void setTraders5(long traders5) {
		this.traders5 = traders5;
	}

	public long getTraders15() {
		return traders15;
	}

	public void setTraders15(long traders15) {
		this.traders15 = traders15;
	}

	public long getTraders30() {
		return traders30;
	}

	public void setTraders30(long traders30) {
		this.traders30 = traders30;
	}

	public long getTraders90() {
		return traders90;
	}

	public void setTraders90(long traders90) {
		this.traders90 = traders90;
	}

	public long getTraders180() {
		return traders180;
	}

	public void setTraders180(long traders180) {
		this.traders180 = traders180;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Analytics(Date createDate, SecurityMaster securityMaster) {
		this.createDate = createDate;
		this.securityMaster = securityMaster;
	}

	public Analytics() {
		// TODO Auto-generated constructor stub
	}

	public void setAv3(List<MarketData> marketList, int i) {
		try {
			int low = i + 3;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod3 = avEod3 + mr.closePrice;
				avOpen3 = avOpen3 + mr.getOpenPrice();
				avLow3 = avLow3 + mr.getLowPrice();
				avHigh3 = avHigh3 + mr.getHighPrice();
				qty3 = qty3 + mr.getNetTradedQty();
				volume3 = volume3 + mr.getNetTradedValue();
				traders3 = traders3 + mr.getTrades();
			}
			avEod3 = avEod3 / 3;
			avOpen3 = avOpen3 / 3;
			avLow3 = avLow3 / 3;
			avHigh3 = avHigh3 / 3;
			qty3 = qty3 / 3;
			volume3 = volume3 / 3;
			traders3 = traders3 / 3;

		} catch (Exception e) {
			avEod3= 0;
			avOpen3 = 0;
			avLow3 = 0;
			avHigh3 = 0;
			qty3 = 0;
			volume3 = 0;
			traders3 = 0;
		//	e.printStackTrace();
		}

	}

	public void setAv5(List<MarketData> marketList, int i) {
		try {
			int low = i + 5;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod5 = avEod5 + mr.closePrice;
				avOpen5 = avOpen5 + mr.getOpenPrice();
				avLow5 = avLow5 + mr.getLowPrice();
				avHigh5 = avHigh5 + mr.getHighPrice();
				qty5 = qty5 + mr.getNetTradedQty();
				volume5 = volume5 + mr.getNetTradedValue();
				traders5 = traders5 + mr.getTrades();
			}
			avEod5 = avEod5 / 5;
			avOpen5 = avOpen5 / 5;
			avLow5 = avLow5 / 5;
			avHigh5 = avHigh5 / 5;
			qty5 = qty5 / 5;
			volume5 = volume5 / 5;
			traders5 = traders5 / 5;
		} catch (Exception e) {
			avEod5 = 0;
			avOpen5 = 0;
			avLow5 = 0;
			avHigh5 = 0;
			qty5 = 0;
			volume5 = 0;
			traders5 = 0;
		//	e.printStackTrace();
		}

	}

	public void setAv15(List<MarketData> marketList, int i) {
		try {
			int low = i + 15;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod15 = avEod15 + mr.closePrice;
				avOpen15 = avOpen15 + mr.getOpenPrice();
				avLow15 = avLow15 + mr.getLowPrice();
				avHigh15 = avHigh15 + mr.getHighPrice();
				qty15 = qty15 + mr.getNetTradedQty();
				volume15 = volume15 + mr.getNetTradedValue();
				traders15 = traders15 + mr.getTrades();
			}
			avEod15 = avEod15 / 15;
			avOpen15 = avOpen15 / 15;
			avLow15 = avLow15 / 15;
			avHigh15 = avHigh15 / 15;
			qty15 = qty15 / 15;
			volume15 = volume15 / 15;
			traders15 = traders15 / 15;
		} catch (Exception e) {
			avEod15 = 0;
			avOpen15 = 0;
			avLow15 = 0;
			avHigh15 = 0;
			qty15 = 0;
			volume15 = 0;
			traders15 = 0;
			//e.printStackTrace();
		}

	}

	public void setAv30(List<MarketData> marketList, int i) {
		try {
			int low = i + 30;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod30 = avEod30 + mr.closePrice;
				avOpen30 = avOpen30 + mr.getOpenPrice();
				avLow30 = avLow30 + mr.getLowPrice();
				avHigh30 = avHigh30 + mr.getHighPrice();
				qty30 = qty30 + mr.getNetTradedQty();
				volume30 = volume30 + mr.getNetTradedValue();
				traders30 = traders30 + mr.getTrades();
			}
			avEod30 = avEod30 / 30;
			avOpen30 = avOpen30 / 30;
			avLow30 = avLow30 / 30;
			avHigh30 = avHigh30 / 30;
			qty30 = qty30 / 30;
			volume30 = volume30 / 30;
			traders30 = traders30 / 30;
		} catch (Exception e) {
			avEod30 = 0;
			avOpen30 = 0;
			avLow30 = 0;
			avHigh30 = 0;
			qty30 = 0;
			volume30 = 0;
			traders30 = 0;
			//e.printStackTrace();
		}

	}

	public void setAv90(List<MarketData> marketList, int i) {
		try {
			int low = i + 90;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod90 = avEod90 + mr.closePrice;
				avOpen90 = avOpen90 + mr.getOpenPrice();
				avLow90 = avLow90 + mr.getLowPrice();
				avHigh90 = avHigh90 + mr.getHighPrice();
				qty90 = qty90 + mr.getNetTradedQty();
				volume90 = volume90 + mr.getNetTradedValue();
				traders90 = traders90 + mr.getTrades();
			}
			avEod90 = avEod90 / 90;
			avOpen90 = avOpen90 / 90;
			avLow90 = avLow90 / 90;
			avHigh90 = avHigh90 / 90;
			qty90 = qty90 / 90;
			volume90 = volume90 / 90;
			traders90 = traders90 / 90;
		} catch (Exception e) {
			avEod90 = 0;
			avOpen90 = 0;
			avLow90 = 0;
			avHigh90 = 0;
			qty90 = 0;
			volume90 = 0;
			traders90 = 0;
			//e.printStackTrace();
		}

	}

	public void setAv180(List<MarketData> marketList, int i) {
		try {
			int low = i + 180;
			for (int j = i; j < low; j++) {
				MarketData mr = marketList.get(j);
				avEod180 = avEod180 + mr.closePrice;
				avOpen180 = avOpen180 + mr.getOpenPrice();
				avLow180 = avLow180 + mr.getLowPrice();
				avHigh180 = avHigh180 + mr.getHighPrice();
				qty180 = qty180 + mr.getNetTradedQty();
				volume180 = volume180 + mr.getNetTradedValue();
				traders180 = traders180 + mr.getTrades();
			}
			avEod180 = avEod180 / 180;
			avOpen180 = avOpen180 / 180;
			avLow180 = avLow180 / 180;
			avHigh180 = avHigh180 / 180;
			qty180 = qty180 / 180;
			volume180 = volume180 / 180;
			traders180 = traders180 / 180;
		} catch (Exception e) {
			avEod180 = 0;
			avOpen180 = 0;
			avLow180 = 0;
			avHigh180 = 0;
			qty180 = 0;
			volume180 = 0;
			traders180 = 0;
		//	e.printStackTrace();
		}

	}

}
