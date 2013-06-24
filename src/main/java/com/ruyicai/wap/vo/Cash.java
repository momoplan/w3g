package com.ruyicai.wap.vo;

public class Cash {
	private String platTime;//提现时间
	private String amt;//提现金额
	private String state;//提现状态
	private String rejectReason;//失败原因
	private String id;//

	public String getPlatTime() {
		return platTime;
	}
	public void setPlatTime(String platTime) {
		this.platTime = platTime;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
