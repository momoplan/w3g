package com.ruyicai.wap.vo;

public class Present {
	private String id ;//ID
	private String mobile;//手机号赠送查询时显示被赠送者的手机号
	private String userName;//用户名被赠送查询的时候显示赠送者的用户名
	private String amt;//金额
	private String batchCode;//期号
	private String lotName;//彩种名称
	private String betType; //彩种类型
	private String beiShu;//倍数
	private String zhuShu;//注数
	private String betCode;//注码
	private String winCode;//开奖号码
	private String createTime;//时间
	private String reciveState;//是否领取彩票用于被赠送查询0:未领取1:已领取 
	private String orderPrize;//税后中奖金额
	private String prizeState;//中奖状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	
	public String getBeiShu() {
		return beiShu;
	}
	public void setBeiShu(String beiShu) {
		this.beiShu = beiShu;
	}
	public String getBetCode() {
		return betCode;
	}
	public void setBetCode(String betCode) {
		this.betCode = betCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReciveState() {
		return reciveState;
	}
	public void setReciveState(String reciveState) {
		this.reciveState = reciveState;
	}
	public String getOrderPrize() {
		return orderPrize==null|| "null".equals(orderPrize)?"0.00":orderPrize;
	}
	public void setOrderPrize(String orderPrize) {
		this.orderPrize = orderPrize;
	}
	public String getZhuShu() {
		return zhuShu;
	}
	public void setZhuShu(String zhuShu) {
		this.zhuShu = zhuShu;
	}
	public String getWinCode() {
		return winCode;
	}
	public void setWinCode(String winCode) {
		this.winCode = winCode;
	}
	public String getPrizeState() {
		return prizeState;
	}
	public void setPrizeState(String prizeState) {
		this.prizeState = prizeState;
	}
	
	
	
	
}
