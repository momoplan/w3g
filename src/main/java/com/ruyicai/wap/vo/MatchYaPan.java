package com.ruyicai.wap.vo;

public class MatchYaPan {
	private String companyName;//公司名
	private String modifyTime;//修改时间
	private String closePan;//是否封盘 0否  1是
	private String firstGoal;//实盘盘口
	private String firstUpodds;//主队初盘赔率
	private String firstDownodds;//客队初盘赔率
	private String firstGoal_name;
	private String goal;//即时盘口
	private String upOdds;//主队即时赔率
	private String downOdds;//客队即时赔率
	private String goal_name;
	private String zouDi;//是否走地 0否  1是
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getClosePan() {
		return closePan;
	}
	public void setClosePan(String closePan) {
		this.closePan = closePan;
	}
	public String getFirstGoal() {
		return firstGoal;
	}
	public void setFirstGoal(String firstGoal) {
		this.firstGoal = firstGoal;
	}
	public String getFirstUpodds() {
		return firstUpodds;
	}
	public void setFirstUpodds(String firstUpodds) {
		this.firstUpodds = firstUpodds;
	}
	public String getFirstDownodds() {
		return firstDownodds;
	}
	public void setFirstDownodds(String firstDownodds) {
		this.firstDownodds = firstDownodds;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getUpOdds() {
		return upOdds;
	}
	public void setUpOdds(String upOdds) {
		this.upOdds = upOdds;
	}
	public String getDownOdds() {
		return downOdds;
	}
	public void setDownOdds(String downOdds) {
		this.downOdds = downOdds;
	}
	public String getZouDi() {
		return zouDi;
	}
	public void setZouDi(String zouDi) {
		this.zouDi = zouDi;
	}
	public String getFirstGoal_name() {
		return firstGoal_name;
	}
	public void setFirstGoal_name(String firstGoal_name) {
		this.firstGoal_name = firstGoal_name;
	}
	public String getGoal_name() {
		return goal_name;
	}
	public void setGoal_name(String goal_name) {
		this.goal_name = goal_name;
	}
	
}
