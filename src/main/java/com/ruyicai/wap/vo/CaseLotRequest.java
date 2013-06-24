package com.ruyicai.wap.vo;


public class CaseLotRequest {

	private long buyAmt; // 购买金额
	private long safeAmt; // 保底金额
	private long totalAmt; // 总金额
	private int commisionRatio; // 提成
	private String title; // 方案标题
	private String desc; // 方案描述
	private int visibility; // 公开标识
	private String starter;// 发起人
	private long minAmt; // 每份金额
	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public long getBuyAmt() {
		return buyAmt;
	}

	public void setBuyAmt(long buyAmt) {
		this.buyAmt = buyAmt;
	}

	public long getSafeAmt() {
		return safeAmt;
	}

	public void setSafeAmt(long safeAmt) {
		this.safeAmt = safeAmt;
	}

	public long getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(long totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getCommisionRatio() {
		return commisionRatio;
	}

	public void setCommisionRatio(int commisionRatio) {
		this.commisionRatio = commisionRatio;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public long getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(long minAmt) {
		this.minAmt = minAmt;
	}



}
