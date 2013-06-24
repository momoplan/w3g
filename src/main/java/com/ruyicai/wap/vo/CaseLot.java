package com.ruyicai.wap.vo;

public class CaseLot {
	private String caseLotId;//合买方案ID
	private String starter;//发起人
	private String totalAmt;//总金额
	private String safeAmt;//保底金额
	private String buyAmt;//认购金额
	private String minAmt;//最低认购
	private String betAmt;//剩余可认购金额
	private String baodiAmt;//剩余可保底金额
	private String buyAmtByStarter;//发起人认购金额
	private String buyAmtByFollower;//参与人认购金额
	private String commisionRatio;//发起提成
	private String visibility;//是否公开
	private String description;//方案描述
	private String displayTlots;//对当前用户是否显示方案内容
	private String displayStateMemo;
	private String lotNo;//彩种编号
	private String lotName;//彩种
	private String beiShu;//
	private String batchCode;//期号
	private String betType;//彩种类型
	private String betCode;//方案内容
	private String progress;//方案进度
	private String sortState;//为8或9为置顶的
	private String effectiveAchievement;//有效战绩
	private String ineffectiveAchievement;//无效战绩
	private String achievement;//战绩有效+无效
	private String displayState;//1的时候允许撤单撤资
	private String stopTime;//彩种合买截止时间
	private String startTime;//合买发起时间
	private String winBigAmt;//方案中奖金额
	private String winCode;//开奖号码
	private String userSafeAmt;//当前用户保底总金额
	private String userTotalAmt;//当前用户购买总金额
	private String userPrizeAmt;//当前用户中奖金额
	private String starterCancel;//是否可以撤单
	private String buyCancel;//是否可以撤资
	private String prizeState;//是否中奖

	//用于显示参与合买列表
	private String buyName;//购买人昵称
	private String buyTime;//参与合买时间

	public String getCaseLotId() {
		return caseLotId;
	}
	public void setCaseLotId(String caseLotId) {
		this.caseLotId = caseLotId;
	}
	public String getStarter() {
		return starter;
	}
	public void setStarter(String starter) {
		this.starter = starter;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getSafeAmt() {
		return safeAmt;
	}
	public void setSafeAmt(String safeAmt) {
		this.safeAmt = safeAmt;
	}
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = buyAmt;
	}
	public String getMinAmt() {
		return minAmt;
	}
	public void setMinAmt(String minAmt) {
		this.minAmt = minAmt;
	}
	public String getBetAmt() {
		return betAmt;
	}
	public void setBetAmt(String betAmt) {
		this.betAmt = betAmt;
	}
	public String getBaodiAmt() {
		return baodiAmt;
	}
	public void setBaodiAmt(String baodiAmt) {
		this.baodiAmt = baodiAmt;
	}
	public String getBuyAmtByStarter() {
		return buyAmtByStarter;
	}
	public void setBuyAmtByStarter(String buyAmtByStarter) {
		this.buyAmtByStarter = buyAmtByStarter;
	}
	public String getBuyAmtByFollower() {
		return buyAmtByFollower;
	}
	public void setBuyAmtByFollower(String buyAmtByFollower) {
		this.buyAmtByFollower = buyAmtByFollower;
	}
	public String getCommisionRatio() {
		return commisionRatio;
	}
	public void setCommisionRatio(String commisionRatio) {
		this.commisionRatio = commisionRatio;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayTlots() {
		return displayTlots;
	}
	public void setDisplayTlots(String displayTlots) {
		this.displayTlots = displayTlots;
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
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getBetCode() {
		return betCode;
	}
	public void setBetCode(String betCode) {
		this.betCode = betCode;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getSortState() {
		return sortState;
	}
	public void setSortState(String sortState) {
		this.sortState = sortState;
	}
	public String getEffectiveAchievement() {
		return effectiveAchievement;
	}
	public void setEffectiveAchievement(String effectiveAchievement) {
		this.effectiveAchievement = effectiveAchievement;
	}
	public String getIneffectiveAchievement() {
		return ineffectiveAchievement;
	}
	public void setIneffectiveAchievement(String ineffectiveAchievement) {
		this.ineffectiveAchievement = ineffectiveAchievement;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getDisplayState() {
		return displayState;
	}
	public void setDisplayState(String displayState) {
		this.displayState = displayState;
	}
	public String getBeiShu() {
		return beiShu;
	}
	public void setBeiShu(String beiShu) {
		this.beiShu = beiShu;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getDisplayStateMemo() {
		return displayStateMemo;
	}
	public void setDisplayStateMemo(String displayStateMemo) {
		this.displayStateMemo = displayStateMemo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getWinBigAmt() {
		return winBigAmt;
	}
	public void setWinBigAmt(String winBigAmt) {
		this.winBigAmt = winBigAmt;
	}
	public String getWinCode() {
		return winCode;
	}
	public void setWinCode(String winCode) {
		this.winCode = winCode;
	}
	public String getUserSafeAmt() {
		return userSafeAmt;
	}
	public void setUserSafeAmt(String userSafeAmt) {
		this.userSafeAmt = userSafeAmt;
	}
	public String getUserTotalAmt() {
		return userTotalAmt;
	}
	public void setUserTotalAmt(String userTotalAmt) {
		this.userTotalAmt = userTotalAmt;
	} 
	public String getUserPrizeAmt() {
		return userPrizeAmt;
	}
	public void setUserPrizeAmt(String userPrizeAmt) {
		this.userPrizeAmt = userPrizeAmt;
	}
	public String getStarterCancel() {
		return starterCancel;
	}
	public void setStarterCancel(String starterCancel) {
		this.starterCancel = starterCancel;
	}
	public String getBuyCancel() {
		return buyCancel;
	}
	public void setBuyCancel(String buyCancel) {
		this.buyCancel = buyCancel;
	}
	public String getPrizeState() {
		return prizeState;
	}
	public void setPrizeState(String prizeState) {
		this.prizeState = prizeState;
	}
	
}
