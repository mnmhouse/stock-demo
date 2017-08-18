package com.putact.bean;

import java.io.Serializable;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 623541717003512683L;
	// {"SH000001":{"symbol":"SH000001","exchange":"SH","code":"000001",
	// "name":"上证指数","current":"3255.37","percentage":"0.27","change":"8.91","open":"3253.85",
	// "high":"3262.09","low":"3251.46","close":"3255.37","last_close":"3246.45","high52week":"3305.43",
	// "low52week":"2969.13","volume":"1.05832832E10","lot_volume":"1.05832832E10","volumeAverage":"33269612703",
	// "marketCapital":"3.1468964411953E13","eps":"0.0","pe_ttm":"","pe_lyr":"","beta":"0.0","totalShares":"7472301116",
	// "time":"Thu Aug 17 11:10:06 +0800
	// 2017","afterHours":"0.0","afterHoursPct":"0.0","afterHoursChg":"0.0",
	// "afterHoursTime":"Wed Oct 19 21:59:01 -0400
	// 2011","updateAt":"1493976615034","dividend":"0.0","yield":"0.0",
	// "turnover_rate":"","instOwn":"0.0","rise_stop":"0.0","fall_stop":"0.0","currency_unit":"CNY",
	// "amount":"1.173497220481E11","net_assets":"0.0","hasexist":"","has_warrant":"0","type":"12","flag":"1",
	// "rest_day":"","amplitude":"0.33%","market_status":"交易中","lot_size":"1","min_order_quantity":"0",
	// "max_order_quantity":"0","tick_size":"0.01","kzz_stock_symbol":"","kzz_stock_name":"","kzz_stock_current":"0.0",
	// "kzz_convert_price":"0.0","kzz_covert_value":"0.0","kzz_cpr":"0.0","kzz_putback_price":"0.0","kzz_convert_time":"",
	// "kzz_redempt_price":"0.0","kzz_straight_price":"0.0","kzz_stock_percent":"","pb":"0.0","benefit_before_tax":"0.0",
	// "benefit_after_tax":"0.0","convert_bond_ratio":"","totalissuescale":"","outstandingamt":"","maturitydate":"",
	// "remain_year":"","convertrate":"0.0","interestrtmemo":"","release_date":"","circulation":"0.0","par_value":"0.0",
	// "due_time":"0.0","value_date":"","due_date":"","publisher":"","redeem_type":"","issue_type":"","bond_type":"",
	// "warrant":"","sale_rrg":"","rate":"","after_hour_vol":"0","float_shares":"2568300000000",
	// "float_market_capital":"2.6591189699091E13","disnext_pay_date":"","convert_rate":"0.0","volume_ratio":"1.2",
	// "percent5m":"0.05","pankou_ratio":"0.0%","psr":"","rise_count":"634","flat_count":"157","fall_count":"582"}}

	private String open = "3253.85";
	private String current = "3243.85";
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
