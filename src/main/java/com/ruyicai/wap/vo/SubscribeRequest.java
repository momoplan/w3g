package com.ruyicai.wap.vo;

import java.math.BigDecimal;

public class SubscribeRequest {
	private BigDecimal lotmulti;//追号的倍数	
	private BigDecimal amt;//追号的金额	
	private String batchcode;//期号
	public BigDecimal getLotmulti() {
		return lotmulti;
	}
	public void setLotmulti(BigDecimal lotmulti) {
		this.lotmulti = lotmulti;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

}
