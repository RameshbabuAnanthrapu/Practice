package com.example.exchangeRateViewer.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "EXCHANGERATES")
public class RateDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="BASE")
	private String base;
	
	@Column(name="CURRENCY")
	private String currency;
	
	@Column(name="EX_RATE")
	private BigDecimal rate;
	
	@Column(name="DATE")
	private Date date;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
