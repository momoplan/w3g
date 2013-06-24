package com.ruyicai.wap.vo;

public class MatchRanking {
	private int ranking;//排名
	
	private int teamID;
	
	private String teamName;//球队名
	
	private int win;//胜次数
	
	private int standoff;//平次数
	
	private int lose;//负次数
	
	private int goinBall;//进球数
	
	private int loseBall;//失球数
	
	private int goalDifference;//净胜球
	
	private int integral;//积分
	
	//篮球
	private int matchCount;//
	private int winCount;
	private int loseCount;
	private String winLv;
	private String gainScore;
	private String loseScore;
	private String scoreDifference;
	
	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getStandoff() {
		return standoff;
	}

	public void setStandoff(int standoff) {
		this.standoff = standoff;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getGoinBall() {
		return goinBall;
	}

	public void setGoinBall(int goinBall) {
		this.goinBall = goinBall;
	}

	public int getLoseBall() {
		return loseBall;
	}

	public void setLoseBall(int loseBall) {
		this.loseBall = loseBall;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}

	public String getWinLv() {
		return winLv;
	}

	public void setWinLv(String winLv) {
		this.winLv = winLv;
	}

	public String getGainScore() {
		return gainScore;
	}

	public void setGainScore(String gainScore) {
		this.gainScore = gainScore;
	}

	public String getLoseScore() {
		return loseScore;
	}

	public void setLoseScore(String loseScore) {
		this.loseScore = loseScore;
	}

	public String getScoreDifference() {
		return scoreDifference;
	}

	public void setScoreDifference(String scoreDifference) {
		this.scoreDifference = scoreDifference;
	}
	
}
