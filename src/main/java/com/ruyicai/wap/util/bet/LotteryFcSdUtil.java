package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryFcSdUtil {
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
		String bball = LotteryUtil.addZeroForBetCodeList(bballs);
		String sball = LotteryUtil.addZeroForBetCodeList(sballs);
		String gball = LotteryUtil.addZeroForBetCodeList(gballs);
		List<String> bballList = LotteryUtil.getStringListFromZeroString(bball);
		List<String> sballList = LotteryUtil.getStringListFromZeroString(sball);
		List<String> gballList = LotteryUtil.getStringListFromZeroString(gball);
		Collections.sort(bballList);
		Collections.sort(sballList);
		Collections.sort(gballList);
		String betCodeView = "百位:"+LotteryUtil.getDouHaoStringFromStringList(bballs)+"<br/>十位:"
				+LotteryUtil.getDouHaoStringFromStringList(sballs)+"<br/>个位:"
				+LotteryUtil.getDouHaoStringFromStringList(gballs);
		int zhuShu = bballs.size()*sballs.size()*gballs.size();
		String betCode = getDirectBetCode(bballList, sballList, gballList);
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
		String ball = "";
		List<String> ballList = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			List<String> bballs = getAutoBetCodeAuto(1,0,9);
			List<String> sballs = getAutoBetCodeAuto(1,0,9);
			List<String> gballs = getAutoBetCodeAuto(1,0,9);
			ball = LotteryUtil.addZeroForBetCodeList(bballs)
					+LotteryUtil.addZeroForBetCodeList(sballs)
					+LotteryUtil.addZeroForBetCodeList(gballs);
			ballList = LotteryUtil.getStringListFromZeroString(ball);
			betCodeView += LotteryUtil.getStringFromStringList(bballs)+","
					+LotteryUtil.getStringFromStringList(sballs)+","
					+LotteryUtil.getStringFromStringList(gballs)+"<br/>";
			betCode += getAutoBetCode(ballList);
		}
		autoBetMap.put("playType","单选单式");
		autoBetMap.put("betCode", betCode);
		autoBetMap.put("betCodeView", betCodeView);
		autoBetMap.put("zhuShu", zhuShu);
		return autoBetMap;
	}
	/**
	 * 得到胆拖betCode，betCodeView，zhuShu
	 * @param rdballs
	 * @param rtballs
	 * @param bballs
	 * @return
	 */
	public static Map<String, String> getDanTuoBetMap(List<String> dballs,List<String> tballs){
		Map<String, String> danTuoBetMap = new HashMap<String, String>();
		Collections.sort(dballs);
		Collections.sort(tballs);
		String dball = LotteryUtil.addZeroForBetCodeList(dballs);
		String tball = LotteryUtil.addZeroForBetCodeList(tballs);
		List<String> dballList = LotteryUtil.getStringListFromZeroString(dball);
		List<String> tballList = LotteryUtil.getStringListFromZeroString(tball);
		Collections.sort(dballList);
		Collections.sort(tballList);
		String betCodeView = "胆码:"+LotteryUtil.getDouHaoStringFromStringList(dballs)+"<br/>拖码:"+LotteryUtil.getDouHaoStringFromStringList(tballs);
		int zhuShu = getFcSdDanTuoZhuShu(dballs.size(),tballs.size());
		String betCode = getDanTuoBetCode(dballList, tballList);
		danTuoBetMap.put("playType","胆拖");
		danTuoBetMap.put("betCode", betCode);
		danTuoBetMap.put("betCodeView", betCodeView);
		danTuoBetMap.put("zhuShu", zhuShu+"");
		return danTuoBetMap;
		
	}
	/**
	 * 得到组三单式betCode，betCodeView，zhuShu
	 * @param ball2s
	 * @param ball1s
	 * @return
	 */
	public static Map<String, String> getGroup3SelfSingleBetMap(List<String> ball2s,List<String> ball1s){
		Map<String, String> group3SelfSingleBetMap = new HashMap<String, String>();
		String ball2 = LotteryUtil.addZeroForBetCodeList(ball2s);
		String ball1 = LotteryUtil.addZeroForBetCodeList(ball1s);
		List<String> ball2List = LotteryUtil.getStringListFromZeroString(ball2);
		List<String> ball1List = LotteryUtil.getStringListFromZeroString(ball1);
		ball1s.addAll(ball2s);
		ball1s.addAll(ball2s);
		Collections.sort(ball1s);
		ball1List.addAll(ball2List);
		ball1List.addAll(ball2List);
		Collections.sort(ball1List);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(ball1s);
		int zhuShu = 1;
		String betCode = getGroup3SelfSingleBetCode(ball1List);
		group3SelfSingleBetMap.put("betCode", betCode);
		group3SelfSingleBetMap.put("betCodeView", betCodeView);
		group3SelfSingleBetMap.put("zhuShu", zhuShu+"");
		return group3SelfSingleBetMap;
		
	}
	/**
	 * 得到组三自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getGroup3SelfBetMap(List<String> balls){
		Map<String, String> group3SelfBetMap = new HashMap<String, String>();
		String betCodeView = "";
		String betCode = "";
		int zhuShu = 1;
//		if(balls.size()==2){
//			List<String> ball1s = new ArrayList<String>();
//			List<String> ball2s = new ArrayList<String>();
//			List<String> ball1List = new ArrayList<String>();
//			List<String> ball2List = new ArrayList<String>();
//			ball1s.add(balls.get(0));
//			ball1s.add(balls.get(0));
//			ball1s.add(balls.get(1));
//			ball2s.add(balls.get(0));
//			ball2s.add(balls.get(1));
//			ball2s.add(balls.get(1));
//			Collections.sort(ball2s);
//			Collections.sort(ball1s);
//			String ball1 = LotteryUtil.addZeroForBetCodeList(ball1s);
//			String ball2 = LotteryUtil.addZeroForBetCodeList(ball2s);
//			ball1List = LotteryUtil.getStringListFromZeroString(ball1);
//			ball2List = LotteryUtil.getStringListFromZeroString(ball2);
//			Collections.sort(ball1List);
//			Collections.sort(ball2List);
//			betCodeView = LotteryUtil.getDouHaoStringFromStringList(ball1s)+"<br/>"+LotteryUtil.getDouHaoStringFromStringList(ball2s);
//			betCode = getGroup3SelfSingleBetCode(ball1List)+getGroup3SelfSingleBetCode(ball2List);
//			zhuShu = 2;
//			group3SelfBetMap.put("playType","组三单式");
//		}else{
			Collections.sort(balls);
			String ball = LotteryUtil.addZeroForBetCodeList(balls);
			List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
			Collections.sort(ballList);
			betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
			betCode = getGroup3SelfMultipleBetCode(ballList);
			zhuShu = getFcSdGroup3MultipleZhuShu(balls.size());
			group3SelfBetMap.put("playType","组三复式");
//		}
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
		String betCodeView = "";
		String betCode = "";
		int zhuShu = 1;
		Collections.sort(balls);
		if(balls.size()==3){
			String ball = LotteryUtil.addZeroForBetCodeList(balls);
			List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
			Collections.sort(ballList);
			betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
			betCode = getGroup6SelfSingleBetCode(ballList);
			zhuShu = 1;
			group6SelfBetMap.put("playType","组六单式");
		}else{
			String ball = LotteryUtil.addZeroForBetCodeList(balls);
			List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
			Collections.sort(ballList);
			betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
			betCode = getGroup6SelfMultipleBetCode(ballList);
			zhuShu = getFcSdGroup6MultipleZhuShu(balls.size());
			group6SelfBetMap.put("playType","组六复式");
		}
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
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
		Collections.sort(ballList);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getDirectHeZhiBetCode(ballList);
		int zhuShu = getFcSdDirectHeZhiZhuShu(Integer.parseInt(balls.get(0)));
		directHeZhiBetMap.put("betCode", betCode);
		directHeZhiBetMap.put("betCodeView", betCodeView);
		directHeZhiBetMap.put("zhuShu", zhuShu+"");
		directHeZhiBetMap.put("playType", "直选和值");
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
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
		Collections.sort(ballList);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup3HeZhiBetCode(ballList);
		int zhuShu = getFcSdGroup3HeZhiZhuShu(Integer.parseInt(balls.get(0)));
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
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
		Collections.sort(ballList);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		String betCode = getGroup6HeZhiBetCode(ballList);
		int zhuShu = getFcSdGroup6HeZhiZhuShu(Integer.parseInt(balls.get(0)));
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
	public static String getDirectBetCode(List<String> bballList,List<String> sballList,List<String> gballList){
		String betCode = "";
		//单选按位包号，可实现直选复式（按位排序）
		//2001050102030405^06060708091000^050102030405^
		//每位号码最少一个，最多9个
		betCode = "2001"+(bballList.size()<10?("0"+bballList.size()):bballList.size())+LotteryUtil.getStringFromStringList(bballList)+"^"
			+(sballList.size()<10?("0"+sballList.size()):sballList.size())+LotteryUtil.getStringFromStringList(sballList)+"^"
			+(gballList.size()<10?("0"+gballList.size()):gballList.size())+LotteryUtil.getStringFromStringList(gballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到机选投注注码
	 * @param ballList
	 * @return
	 */
	public static String getAutoBetCode(List<String> ballList){
		String betCode = "";
		//单选单式，0001010203^
		betCode = "0001"+LotteryUtil.getStringFromStringList(ballList)+"^";
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
		//　　单选单胆拖（排序）
		//540101*020304^
		betCode = "5401"+LotteryUtil.getStringFromStringList(dballList)+"*"
					+LotteryUtil.getStringFromStringList(tballList)+"^";
		return betCode;
		
	}

	/**
	 * 得到组三单式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3SelfSingleBetCode(List<String> ballList){
		String betCode = "";
		//组三单式（排序）0101010202^
		//球中两个相同
		betCode = "0101"+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到组三复式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3SelfMultipleBetCode(List<String> ballList){
		String betCode = "";
		//组三复式（排序）31010403040506^
		//号码最多9个
		betCode = "3101"+(ballList.size()<10?("0"+ballList.size()):ballList.size())+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到组六单式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6SelfSingleBetCode(List<String> ballList){
		String betCode = "";
		//组六单式（排序）0201040506^
		betCode = "0201"+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到组六复式投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6SelfMultipleBetCode(List<String> ballList){
		String betCode = "";
		//组六复式（排序）320106030405060708^
		//号码最多9个
		betCode = "3201"+(ballList.size()<10?("0"+ballList.size()):ballList.size())+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到直选和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getDirectHeZhiBetCode(List<String> ballList){
		String betCode = "";
		//单选包点100118^
		betCode = "1001"+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到组三和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup3HeZhiBetCode(List<String> ballList){
		String betCode = "";
		//组三包点110118^
		betCode = "1101"+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到组六和值投注注码
	 * @param ballList
	 * @return
	 */
	public static String getGroup6HeZhiBetCode(List<String> ballList){
		String betCode = "";
		//组六包点120118^
		betCode = "1201"+LotteryUtil.getStringFromStringList(ballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到福彩3D组三复式注数
	 * @param ballsSize
	 * @return
	 */
	public static int getFcSdGroup3MultipleZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(2, ballsSize) * 2;
		return zhushu;
	}
	/**
	 * 得到福彩3D组六复式注数
	 * @param ballsSize
	 * @return
	 */
	public static int getFcSdGroup6MultipleZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(3, ballsSize);
		return zhushu;
	}
	/**
	 * 取到福彩3D直选和值的注数
	 * @param num
	 * @return
	 */
	public static int getFcSdDirectHeZhiZhuShu(int ball) {
		int zxHHH[] = {1,3,6,10,15,21,28,36,45,55,63,69,73,75,75,73,69,63,55,45,36,28,21,15,10,6,3,1};
		return zxHHH[ball];
	}
	/**
	 * 得到福彩3D组3和值的注数
	 * @param num
	 * @return
	 */
	public static int getFcSdGroup3HeZhiZhuShu(int ball) {
		int z3HHH[] = {1,2,1,3,3,3,4,5,4,5,5,4,5,5,4,5,5,4,5,4,3,3,3,1,2,1};
		return z3HHH[ball-1];
	}
	
	/**
	 * 得到福彩3D组6和值的注数
	 * @param num
	 * @return
	 */
	public static int getFcSdGroup6HeZhiZhuShu(int ball) {
		int z6HHH[] = {0,0,1,1,2,3,4,5,7,8,9,10,10,10,10,9,8,7,5,4,3,2,1,1,0,0};
		return z6HHH[ball-1];
	}
	/**
	 * 得到福彩3D胆拖注数
	 * @param dballListSize
	 * @param tballListSize
	 * @return
	 */
	public static int getFcSdDanTuoZhuShu(int dballsSize,int tballsSize){
		long zhuShu = zuhe(3 - dballsSize, tballsSize) * 6;
		return (int)zhuShu;
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
	 * 福彩3D机选
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
