package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotterySelect5From22Util {
	/**
	 * 得到自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getSelfBetMap(List<String> balls){
		Map<String, String> selfBetMap = new HashMap<String, String>();
		List<Integer> ballIntegerList = LotteryUtil.getIntegerListForStringList(balls);
		Collections.sort(ballIntegerList);
		String ball = LotteryUtil.addZeroForBetCodeIntegerList(ballIntegerList);
		List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(ballList);
		int zhuShu = getSelect5From22ZhuShu(balls.size());
		String playType = "";
		String betCode = "";
		if(zhuShu>1){
			playType = "复式";
			betCode = getBetCode(LotteryUtil.getStringFromStringList(ballList)+"^", "FS") ;

		}else{
			playType = "单式";
			betCode = getBetCode(LotteryUtil.getStringFromStringList(ballList)+"^", "DS");

		} 
		selfBetMap.put("playType", playType);
		selfBetMap.put("betCode", betCode);
		selfBetMap.put("betCodeView", betCodeView);
		selfBetMap.put("zhuShu", zhuShu+"");
		return selfBetMap;
		
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
			ball = LotteryUtil.addZeroForBetCodeList(getAutoBetCodeAuto(5, 22));
			ballList = LotteryUtil.getStringListFromZeroString(ball);
			Collections.sort(ballList);
			betCodeView += LotteryUtil.getDouHaoStringFromStringList(ballList)+"<br/>";
			betCode += getBetCode(LotteryUtil.getStringFromStringList(ballList)+"^", "DS");
		}
		String playType = "单式";
		autoBetMap.put("playType", playType);
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
		List<Integer> dballIntegerList = LotteryUtil.getIntegerListForStringList(dballs);
		List<Integer> tballIntegerList = LotteryUtil.getIntegerListForStringList(tballs);
		Collections.sort(dballIntegerList);
		Collections.sort(tballIntegerList);
		String dball = LotteryUtil.addZeroForBetCodeIntegerList(dballIntegerList);
		String tball = LotteryUtil.addZeroForBetCodeIntegerList(tballIntegerList);
		List<String> dballList = LotteryUtil.getStringListFromZeroString(dball);
		List<String> tballList = LotteryUtil.getStringListFromZeroString(tball);
		String betCodeView = "胆码:"+LotteryUtil.getDouHaoStringFromStringList(dballList)+"<br/>拖码:"+LotteryUtil.getDouHaoStringFromStringList(tballList);
		int zhuShu = getSelect5From22DanTuoZhuShu(dballs.size(),tballs.size());
		String betCode = getBetCode( LotteryUtil.getStringFromStringList(dballList)+"*"
				+LotteryUtil.getStringFromStringList(tballList)+"^", "DT");
		String playType = "胆拖";
		danTuoBetMap.put("playType", playType);
		danTuoBetMap.put("betCode", betCode);
		danTuoBetMap.put("betCodeView", betCodeView);
		danTuoBetMap.put("zhuShu", zhuShu+"");
		return danTuoBetMap;
		
	}

	/**
	 * 得到22选5注数
	 * @param ballNumber
	 * @return
	 */
	public static int getSelect5From22ZhuShu(int ballNumber){
		long zhuShu = zuhe(5,ballNumber);
		return (int)zhuShu;
	}
	/**
	 * 得到胆拖注数
	 * @param dballsSize
	 * @param tballsSize
	 * @return
	 */
	public static int getSelect5From22DanTuoZhuShu(int dballsSize, int tballsSize) {
		int zhushu = (int) zuhe(5 - dballsSize, tballsSize);
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
	 * 22选5机选
	 * @param ballNumber注码个数
	 * @param max注码允许最大数
	 * @return
	 */
	public static List<String> getAutoBetCodeAuto(int ballNumber, int max){
		List<Integer>list = new ArrayList<Integer>();
		List<Integer>betCodeList = new ArrayList<Integer>();
		List<String> listString = new ArrayList<String>();
		for(int i=1;i<=max;i++){
			list.add(i);
		}
		for(int i=1;i<=ballNumber;i++) {
			int index = getRandomIntWithinRange(max+1-i);
			betCodeList.add(list.remove(index-1));
		}
		Collections.sort(betCodeList);
		for (Integer str : betCodeList) {
			listString.add(str+"");
		}
		return listString;
	}
	
	// 返回 1-cap之间的一个整数  [1, cap]
	public static int getRandomIntWithinRange(int cap) {
		Random r = new Random();
		return r.nextInt(cap)+1;
	}
	public static String getBetCode(String betCode, String type) {
		if ("DS".equals(type)) {
			return "0@" + betCode;
		}
		if ("FS".equals(type)) {
			return "1@" + betCode;
		}
		if ("DT".equals(type)) {
			return "2@" + betCode;
		}
		return "";
	}
}
