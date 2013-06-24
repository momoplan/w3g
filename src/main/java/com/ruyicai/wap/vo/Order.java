package com.ruyicai.wap.vo;

public class Order {
	private String orderId;
	private String lotNo;
	private String lotName;
	private String betCode;
	private String orderPrize;//税后中奖金额
	private String orderPreprizeAmt;//税前中奖金额
	private String beiShu;
	private String zhuShu;
//	private String modifyTime;//
	private String encashTime;//兑奖时间
	private String prizeState;//0,"准备进入等待开奖状态"；1,"等待开奖状态"；
							  //2,"派奖处理中"，3,"完成派奖"，4,"中得大奖";5,"中得小奖"
	private String betType;
	private String prizeInfo;
	private String createTime;//投注时间
	private String amount;//投注金额
	private String batchCode;
	private String flowNo;//
	private String batchNum;//购买期数
	private String lastNum;//剩余期数
	private String failNum;//撤销期数
	private String nowNum;//已追期数
	private String state;//追号状态
	private String totalAmt;//剩余追号总额
	private String beginBatch;//开始期号
	private String winBaseCode;//开奖号码
	private String totalAmount;//追号总金额
	private String successAmount;//成功总金额
	private String beginTime;//开始时间
	private String prizeend;;//是否中奖后停止追号0:不停止,1:停止
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getWinBaseCode() {
		return winBaseCode;
	}
	public void setWinBaseCode(String winBaseCode) {
		this.winBaseCode = winBaseCode;
	}
	public String getBeginBatch() {
		return beginBatch;
	}
	public void setBeginBatch(String beginBatch) {
		this.beginBatch = beginBatch;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public String getBetCode() {
		return betCode;
	}
	public void setBetCode(String betCode) {
		this.betCode = betCode;
	}
	public String getOrderPrize() {
		return orderPrize;
	}
	public void setOrderPrize(String orderPrize) {
		this.orderPrize = orderPrize;
	}
	public String getBeiShu() {
		return beiShu;
	}
	public void setBeiShu(String beiShu) {
		this.beiShu = beiShu;
	}
//	public String getModifyTime() {
//		return modifyTime;
//	}
//	public void setModifyTime(String modifyTime) {
//		this.modifyTime = modifyTime;
//	}
	
	public String getPrizeState() {
		return prizeState;
	}
	public String getEncashTime() {
		return encashTime;
	}
	public void setEncashTime(String encashTime) {
		this.encashTime = encashTime;
	}
	public void setPrizeState(String prizeState) {
		this.prizeState = prizeState;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getPrizeInfo() {
		return prizeInfo;
	}
	public void setPrizeInfo(String prizeInfo) {
		this.prizeInfo = prizeInfo;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getLastNum() {
		return lastNum;
	}
	public void setLastNum(String lastNum) {
		this.lastNum = lastNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getNowNum() {
		return nowNum;
	}
	public void setNowNum(String nowNum) {
		this.nowNum = nowNum;
	}
	public String getOrderPreprizeAmt() {
		return orderPreprizeAmt;
	}
	public void setOrderPreprizeAmt(String orderPreprizeAmt) {
		this.orderPreprizeAmt = orderPreprizeAmt;
	}
	public String getZhuShu() {
		return zhuShu;
	}
	public void setZhuShu(String zhuShu) {
		this.zhuShu = zhuShu;
	}
	public String getFailNum() {
		return failNum;
	}
	public void setFailNum(String failNum) {
		this.failNum = failNum;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getPrizeend() {
		return prizeend;
	}
	public void setPrizeend(String prizeend) {
		this.prizeend = prizeend;
	}
	public String getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(String successAmount) {
		this.successAmount = successAmount;
	}
	
}
