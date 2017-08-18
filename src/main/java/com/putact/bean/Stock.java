package com.putact.bean;

import java.io.Serializable;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 623541717003512683L;

	private String open = "3253.85";
	private String current = "3254.85";
	private String last_close = "3246.45";
	private String hign = "3262.09";
	private String low = "3251.46";
	private String high52week = "3305.43";
	private String low52week = "2969.13";
	private String totalSum = "115.87";

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getLast_close() {
		return last_close;
	}

	public void setLast_close(String last_close) {
		this.last_close = last_close;
	}

	public String getHign() {
		return hign;
	}

	public void setHign(String hign) {
		this.hign = hign;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getHigh52week() {
		return high52week;
	}

	public void setHigh52week(String high52week) {
		this.high52week = high52week;
	}

	public String getLow52week() {
		return low52week;
	}

	public void setLow52week(String low52week) {
		this.low52week = low52week;
	}

	public String getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}
