package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryArray3Util {
	/**
	 * 得到直选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getDirectBetMap(List<String> bballs,List<String> sballs,List<String> gballs){
		Map<String, String> directBetMap = new HashMap<String, String>();
		Collections.sort(bballs);
		Collections.sort(sballs);
		Collections.sort(gballs);
		String betCodeView = "百位:"+LotteryUtil.getDouHaoStringFromStringList(bballs)+"<br/>十位:"
				+LotteryUtil.getDouHaoStringFromStringList(sballs)+"<br/>个位:"
				+LotteryUtil.getDouHaoStringFromStringList(gballs);
		int zhuShu = bballs.size()*sballs.size()*gballs.size();
		String betCode = getDirectBetCode(bballs, sballs, gballs);
		directBetMap.put("betCode", betCode);
		directBetMap.put("betCodeView", betCodeView);
		directBetMap.put("zhuShu", zhuShu+"");
		directBetMap.put("playType", "直选");
		return directBetMap;
		
	}
	/**
	 * 得到机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getAutoBetMap(String zhuShu){
		Map<String, String> autoBetMap = new HashMap<String, String>();
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		List<String> bballs = new ArrayList<String>();
		List<String> sballs = new ArrayList<String>();
		List<String> gballs = new ArrayList<String>();
		String ball = "";
		for(int i=0;i<zhuShuInt;i++){
			bballs = getAutoBetCodeAuto(1,0,9);
			sballs = getAutoBetCodeAuto(1,0,9);
			gballs =	getAutoBetCodeAuto(1,0,9);
			ball = LotteryUtil.getStringFromStringList(bballs)+","
					+LotteryUtil.getStringFromStringList(sballs)+","
					+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += getAutoBetCode(bballs, sballs, gballs)+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		autoBetMap.put("playType","单式");
		autoBetMap.put("betCode", betCode);
		autoBetMap.put("betCodeView", betCodeView);
		autoBetMap.put("zhuShu", zhuShu);
		return autoBetMap;
	}
	/**
	 * 得到组选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroupBetMap(List<String> balls){
		Map<String, String> groupBetMap = new HashMap<String, String>();
		int zhuShu = 1;
		String betCodeView = "";
		String betCode = "";
		if(balls.size()==2){
			List<String> ball1List = new ArrayList<String>();
			List<String> ball2List = new ArrayList<String>();
			ball1List.add(balls.get(0));
			ball1List.add(balls.get(0));
			ball1List.add(balls.get(1));
			Collections.sort(ball1List);
			ball2List.add(balls.get(0));
			ball2List.add(balls.get(1));
			ball2List.add(balls.get(1));
			Collections.sort(ball2List);
			betCodeView = LotteryUtil.getDouHaoStringFromStringList(ball1List)+"<br/>"
					+ LotteryUtil.getDouHaoStringFromStringList(ball2List);
			betCode = getGroupBetCode(ball1List)+";"+getGroupBetCode(ball1List);
			zhuShu = 2;
		}else{
			Collections.sort(balls);
			betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
			betCode = getGroupBetCode(balls);
			zhuShu = 1;
		}
		groupBetMap.put("betCode", betCode);
		groupBetMap.put("betCodeView", betCodeView);
		groupBetMap.put("zhuShu", zhuShu+"");
		return groupBetMap;
		
	}
	/**
	 * 得到组三自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup3SelfBetMap(List<String> balls){
		Map<String, String> group3SelfBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup3SelfBetCode(balls);
		int zhuShu = getArray3Group3ZhuShu(balls.size());
		group3SelfBetMap.put("playType","组三复式");
		group3SelfBetMap.put("betCode", betCode);
		group3SelfBetMap.put("betCodeView", betCodeView);
		group3SelfBetMap.put("zhuShu", zhuShu+"");
		return group3SelfBetMap;
		
	}
	/**
	 * 得到组六自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup6SelfBetMap(List<String> balls){
		Map<String, String> group6SelfBetMap = new HashMap<String, String>();
		int zhuShu = 1;
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup6SelfBetCode(balls);
		zhuShu = getArray3Group6ZhuShu(balls.size());
		group6SelfBetMap.put("playType", "组六复式");
		group6SelfBetMap.put("betCode", betCode);
		group6SelfBetMap.put("betCodeView", betCodeView);
		group6SelfBetMap.put("zhuShu", zhuShu+"");
		return group6SelfBetMap;
	}
	/**
	 * 得到直选和值betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getDirectHeZhiBetMap(List<String> balls){
		Map<String, String> directHeZhiBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getDirectHeZhiBetCode(balls);
		int zhuShu = 0;
		for (String ball : balls) {
			zhuShu+=getArray3DirectHeZhiZhuShu(Integer.parseInt(ball));
		}
		directHeZhiBetMap.put("playType", "直选和值");
		directHeZhiBetMap.put("betCode", betCode);
		directHeZhiBetMap.put("betCodeView", betCodeView);
		directHeZhiBetMap.put("zhuShu", zhuShu+"");
		return directHeZhiBetMap;
		
	}
	/**
	 * 得到组三和值betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup3HeZhiBetMap(List<String> balls){
		Map<String, String> group3HeZhiBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup3HeZhiBetCode(balls);
		int zhuShu = 0;
		for (String ball : balls) {
			zhuShu+=getArray3Group3HeZhiZhuShu(Integer.parseInt(ball));
		}
		group3HeZhiBetMap.put("betCode", betCode);
		group3HeZhiBetMap.put("betCodeView", betCodeView);
		group3HeZhiBetMap.put("zhuShu", zhuShu+"");
		return group3HeZhiBetMap;
		
	}
	/**
	 * 得到组六和值betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup6HeZhiBetMap(List<String> balls){
		Map<String, String> group6HeZhiBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup6HeZhiBetCode(balls);
		int zhuShu = 0;
		for (String ball : balls) {
			zhuShu+=getArray3Group6HeZhiZhuShu(Integer.parseInt(ball));
		}
		group6HeZhiBetMap.put("betCode", betCode);
		group6HeZhiBetMap.put("betCodeView", betCodeView);
		group6HeZhiBetMap.put("zhuShu", zhuShu+"");
		return group6HeZhiBetMap;
		
	}
	/**
	 * 得到组三包号betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup3BaoHaoBetMap(List<String> balls){
		Map<String, String> group3BaoHaoBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup3BaoHaoBetCode(balls);
		int zhuShu = getArray3Group3BaoHaoZhuShu(balls.size());
		group3BaoHaoBetMap.put("betCode", betCode);
		group3BaoHaoBetMap.put("betCodeView", betCodeView);
		group3BaoHaoBetMap.put("zhuShu", zhuShu+"");
		return group3BaoHaoBetMap;
		
	}
	/**
	 * 得到组六包号betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup6BaoHaoBetMap(List<String> balls){
		Map<String, String> group6HeZhiBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup6BaoHaoBetCode(balls);
		int zhuShu =getArray3Group6BaoHaoZhuShu(balls.size());
		group6HeZhiBetMap.put("betCode", betCode);
		group6HeZhiBetMap.put("betCodeView", betCodeView);
		group6HeZhiBetMap.put("zhuShu", zhuShu+"");
		return group6HeZhiBetMap;
		
	}
	/**
	 * 得到直选投注注码
	 * @param bballList
	 * @param sballList
	 * @param gballList
	 * @return
	 */
	public static String getDirectBetCode(List<String> bballs,List<String> sballs,List<String> gballs){
		String betCode = "";
		//直选单式1|5,1,8
		//直选复式1|25,168,8
		betCode = "1|"+LotteryUtil.getStringFromStringList(bballs)+","
				+LotteryUtil.getStringFromStringList(sballs)+","
				+LotteryUtil.getStringFromStringList(gballs);
		return betCode;
		
	}
	/**
	 * 得到机选投注注码
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @return
	 */
	public static String getAutoBetCode(List<String> bballs,List<String> sballs,List<String> gballs){
		String betCode = "";
		//直选单式1|5,1,8
		//直选复式1|25,168,8
		betCode = "1|"+LotteryUtil.getStringFromStringList(bballs)+","
				+LotteryUtil.getStringFromStringList(sballs)+","
				+LotteryUtil.getStringFromStringList(gballs);
		return betCode;
		
	}
	/**
	 * 得到组选投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroupBetCode(List<String> ballList){
		String betCode = "";
		//6|5,1,8(组六)
		//6|5,8,8(组三)
		betCode = "6|"+LotteryUtil.getStringFromStringList(ballList);
		return betCode;
		
	}
	/**
	 * 得到组三复式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3SelfBetCode(List<String> ballList){
		String betCode = "";
		//组三复式（排序）Z3|1,2,3
		//号码2～9个
		betCode = "Z3|"+LotteryUtil.getDouHaoStringFromStringList(ballList);
		return betCode;
		
	}
	/**
	 * 得到组六复式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6SelfBetCode(List<String> ballList){
		String betCode = "";
		//组六复式（排序）Z6|1,2,3,4,5,6
		//号码3～9个
		betCode = "Z6|"+LotteryUtil.getDouHaoStringFromStringList(ballList);
		return betCode;
		
	}
	/**
	 * 得到胆拖投注注码
	 * @param dballList
	 * @param tballList
	 * @return
	 */
	public static String getDanTuoBetCode(List<String> dballList,List<String> tballList){
		String betCode = "";
		//单式复式胆拖：可以为单式，也可以为复式（排序）
		//2001010203*04050607080910^
		betCode = "2001"+LotteryUtil.getStringFromStringList(dballList)+"*"
					+LotteryUtil.getStringFromStringList(tballList)+"^";
		return betCode;
		
	}

	/**
	 * 得到直选和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getDirectHeZhiBetCode(List<String> balls){
		String betCode = "";
		//S1|12  S1|8,18,19
		//[0..27] 1～9个
		betCode = "S1|"+LotteryUtil.getDouHaoStringFromStringList(balls);
		return betCode;
		
	}
	/**
	 * 得到组三和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3HeZhiBetCode(List<String> balls){
		String betCode = "";
		//S3|12 S3|8,18
		//[1..26]1～9
		betCode = "S3|"+LotteryUtil.getDouHaoStringFromStringList(balls);
		return betCode;
		
	}
	/**
	 * 得到组六和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6HeZhiBetCode(List<String> balls){
		String betCode = "";
		//S6|12 S6|8,18,19,22
		//[3..24]1～9
		betCode = "S6|"+LotteryUtil.getDouHaoStringFromStringList(balls);
		return betCode;
		
	}
	/**
	 * 得到组三包号投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3BaoHaoBetCode(List<String> balls){
		String betCode = "";
		//F3|0268
		//[0..9]2～10
		betCode = "F3|"+LotteryUtil.getStringFromStringList(balls);
		return betCode;
		
	}
	/**
	 * 得到组六包号投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6BaoHaoBetCode(List<String> balls){
		String betCode = "";
		//F6|0268
		//[0..9]4～9
		betCode = "F6|"+LotteryUtil.getStringFromStringList(balls);
		return betCode;
		
	}
	/**
	 * 得到排列三组三复式注数
	 * @param ballsSize
	 * @return
	 */
	public static int getArray3Group3ZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(2, ballsSize) * 2;
		return zhushu;
	}
	/**
	 * 得到排列三组六复式注数
	 * @param ballsSize
	 * @return
	 */
	public static int getArray3Group6ZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(3, ballsSize);
		return zhushu;
	}
	/**
	 * 取到排列三直选和值的注数
	 * @param ball
	 * @return
	 */
	public static int getArray3DirectHeZhiZhuShu(int ball) {
		int zxHHH[] = {1,3,6,10,15,21,28,36,45,55,63,69,73,75,75,73,69,63,55,45,36,28,21,15,10,6,3,1};
		return zxHHH[ball];
	}
	/**
	 * 得到排列三组3和值的注数
	 * @param ball
	 * @return
	 */
	public static int getArray3Group3HeZhiZhuShu(int ball) {
		int z3HHH[] = {1,2,1,3,3,3,4,5,4,5,5,4,5,5,4,5,5,4,5,4,3,3,3,1,2,1};
		return z3HHH[ball-1];
	}
	
	/**
	 * 得到排列三组6和值的注数
	 * @param ball
	 * @return
	 */
	public static int getArray3Group6HeZhiZhuShu(int ball) {
		int z6HHH[] = {0,0,1,1,2,3,4,5,7,8,9,10,10,10,10,9,8,7,5,4,3,2,1,1,0,0};
		return z6HHH[ball-1];
	}
	/**
	 * 得到排列三组三包号注数
	 * @param ballsSize
	 * @return
	 */
	public static int getArray3Group3BaoHaoZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(2, ballsSize) * 2;
		return zhushu;
	}
	/**
	 * 得到排列三组六包号注数
	 * @param ballsSize
	 * @return
	 */
	public static int getArray3Group6BaoHaoZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(3, ballsSize);
		return zhushu;
	}
	/**
	 * 组合计算公式
	 * @param m
	 * @param n
	 * @return  返回 C(n,m)
	 */
	public static long zuhe(int m,int n){
		long t_a = 0;
		t_a = (jiec(n).divide((jiec(n-m).multiply(jiec(m))))).longValue();
		return t_a;
	}
	/**
	 * 阶乘计算公式
	 * @param a 
	 * @return  返回a的阶乘   a!
	 */
	private static BigDecimal jiec(long a){
		BigDecimal t_a = new BigDecimal(1);
		for (int i = 1; i <= a; i++) {
			t_a = t_a.multiply(new BigDecimal(i));
		}
		return t_a;
	}
	/**
	 * 排列三机选
	 * @param ballNumber注码个数
	 * @param min注码允许最小数
	 * @param max注码允许最大数
	 * @return
	 */
	public static List<String> getAutoBetCodeAuto(int ballNumber, int min ,int max){
		List<String>list = new ArrayList<String>();
		List<String>betCodeList = new ArrayList<String>();
		for(int i=min;i<=max;i++){
			list.add(i+"");
		}
		for(int i=0;i<ballNumber;i++) {
			int index = getRandomIntWithinRange(max-min+1-i);
			betCodeList.add(list.remove(index-1));
		}
		Collections.sort(betCodeList);
		return betCodeList;
	}
	
	// 返回 1-cap之间的一个整数  [1, cap]
	public static int getRandomIntWithinRange(int cap) {
		Random r = new Random();
		return r.nextInt(cap)+1;
	}

}
