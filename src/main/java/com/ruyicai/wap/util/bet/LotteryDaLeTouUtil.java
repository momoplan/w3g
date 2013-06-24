package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryDaLeTouUtil {
	/**
	 * 得到自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getSelfBetMap(List<String> rballs,List<String> bballs){
		Map<String, String> selfBetMap = new HashMap<String, String>();
		List<Integer> rballIntegerList = LotteryUtil.getIntegerListForStringList(rballs);
		List<Integer> bballIntegerList = LotteryUtil.getIntegerListForStringList(bballs);
		Collections.sort(rballIntegerList);
		Collections.sort(bballIntegerList);
		String rball = LotteryUtil.addZeroForBetCodeIntegerList(rballIntegerList);
		String bball = LotteryUtil.addZeroForBetCodeIntegerList(bballIntegerList);
		List<String> rballList = LotteryUtil.getStringListFromZeroString(rball);
		List<String> bballList = LotteryUtil.getStringListFromZeroString(bball);
		String betCodeView = "前区:"+LotteryUtil.getDouHaoStringFromStringList(rballList)+"<br/>后区:"
				+LotteryUtil.getDouHaoStringFromStringList(bballList);
		int zhuShu = getDLTZhuShu(rballs.size(),bballs.size());
		String betCode = LotteryUtil.getKongGeStringFromStringList(rballList)+"-"
				+LotteryUtil.getKongGeStringFromStringList(bballList);
		String playType="";
		if(zhuShu>1) playType = "复式";
		else playType = "单式";
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
		String rball = "";
		String bball = "";
		List<String> rballList = new ArrayList<String>();
		List<String> bballList = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			rball = LotteryUtil.addZeroForBetCodeList(getAutoBetCodeAuto(5, 35));
			bball = LotteryUtil.addZeroForBetCodeList(getAutoBetCodeAuto(2, 12));
			rballList = LotteryUtil.getStringListFromZeroString(rball);
			bballList = LotteryUtil.getStringListFromZeroString(bball);
			Collections.sort(rballList);
			Collections.sort(bballList);
			betCodeView += LotteryUtil.getDouHaoStringFromStringList(rballList)+"|"
					+LotteryUtil.getDouHaoStringFromStringList(bballList)+"<br/>";
			betCode += LotteryUtil.getKongGeStringFromStringList(rballList)+"-"
					+LotteryUtil.getKongGeStringFromStringList(bballList)+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
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
	public static Map<String, String> getDanTuoBetMap(List<String> rdballs,List<String> rtballs,List<String> bdballs,List<String> btballs){
		Map<String, String> danTuoBetMap = new HashMap<String, String>();
		List<Integer> rdballIntegerList = new ArrayList<Integer>();
		List<Integer> rtballIntegerList = new ArrayList<Integer>();
		List<Integer> bdballIntegerList = new ArrayList<Integer>();
		List<Integer> btballIntegerList = new ArrayList<Integer>();
		String rdball = "";
		String rtball = "";
		String bdball = "";
		String btball = "";
		if(rdballs!=null){
			rdballIntegerList = LotteryUtil.getIntegerListForStringList(rdballs);
			Collections.sort(rdballIntegerList);
			rdball = LotteryUtil.addZeroForBetCodeIntegerList(rdballIntegerList);
		}
		rtballIntegerList = LotteryUtil.getIntegerListForStringList(rtballs);
		Collections.sort(rtballIntegerList);
		rtball = LotteryUtil.addZeroForBetCodeIntegerList(rtballIntegerList);
		if(bdballs!=null){
			bdballIntegerList = LotteryUtil.getIntegerListForStringList(bdballs);
			Collections.sort(bdballIntegerList);
			bdball = LotteryUtil.addZeroForBetCodeIntegerList(bdballIntegerList);
		}
		btballIntegerList = LotteryUtil.getIntegerListForStringList(btballs);
		Collections.sort(btballIntegerList);
		btball = LotteryUtil.addZeroForBetCodeIntegerList(btballIntegerList);
		List<String> rdballList = LotteryUtil.getStringListFromZeroString(rdball);
		List<String> rtballList = LotteryUtil.getStringListFromZeroString(rtball);
		List<String> bdballList = LotteryUtil.getStringListFromZeroString(bdball);
		List<String> btballList = LotteryUtil.getStringListFromZeroString(btball);
		
		String betCodeView = "前区胆码:"+LotteryUtil.getDouHaoStringFromStringList(rdballList)+"<br/>前区拖码:"
				+LotteryUtil.getDouHaoStringFromStringList(rtballList)+"<br/>后区胆码:"
				+LotteryUtil.getDouHaoStringFromStringList(bdballList)+"<br/>后区拖码:"
				+LotteryUtil.getDouHaoStringFromStringList(btballList);
		int zhuShu = getDLTDanTuoZhuShu(rdballIntegerList.size(),rtballs.size(),bdballIntegerList.size(),btballs.size());
		String betCode = LotteryUtil.getKongGeStringFromStringList(rdballList)+(rdballs==null?"":"$")
				+LotteryUtil.getKongGeStringFromStringList(rtballList)+"-"
				+LotteryUtil.getKongGeStringFromStringList(bdballList)+(bdballs==null||btballs.size()==1?"":"$")
				+LotteryUtil.getKongGeStringFromStringList(btballList);
		String playType = "胆拖";
		danTuoBetMap.put("playType", playType);
		danTuoBetMap.put("betCode", betCode);
		danTuoBetMap.put("betCodeView", betCodeView);
		danTuoBetMap.put("zhuShu", zhuShu+"");
		return danTuoBetMap;
	}
	/**
	 * 得到生肖乐betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getShengXiaoLeBetMap(List<String> balls){
		Map<String, String> shengXiaoLeBetMap = new HashMap<String, String>();
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		List<String> ballList = LotteryUtil.getStringListFromZeroString(ball);
		Collections.sort(ballList);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(ballList);
		int zhuShu = getDLTShengXiaoLeZhuShu(balls.size());
		String betCode = LotteryUtil.getKongGeStringFromStringList(ballList);
		String playType="";
		if(zhuShu>1) playType = "复式";
		else playType = "单式";
		shengXiaoLeBetMap.put("playType", playType);
		shengXiaoLeBetMap.put("betCode", betCode);
		shengXiaoLeBetMap.put("betCodeView", betCodeView);
		shengXiaoLeBetMap.put("zhuShu", zhuShu+"");
		return shengXiaoLeBetMap;
		
	}
	/**
	 * 得到大乐透自选注数
	 * @param rballsSize
	 * @param bballsSize
	 * @return
	 */
	public static int getDLTZhuShu(int rballsSize, int bballsSize){
		long C_r = zuhe(5, rballsSize);
		long C_b = zuhe(2, bballsSize);
		long doubleBallNumber = (C_r * C_b);
		return (int) doubleBallNumber;
	}

	/**
	 * 得到大乐透胆拖注数
	 * @param rdballsSize
	 * @param rtballsSize
	 * @param bdballsSize
	 * @param btballsSize
	 * @return
	 */
	public static int getDLTDanTuoZhuShu(int rdballsSize, int rtballsSize,int bdballsSize, int btballsSize){
		long C_r = zuhe(5 - rdballsSize, rtballsSize);
		long C_b = zuhe(2 - bdballsSize, btballsSize);
		long doubleBallNumber = (C_r * C_b);
		return (int) doubleBallNumber;
	}
	/**
	 * 得到大乐透生肖乐注数
	 * @param ballsSize
	 * @return
	 */
	public static int getDLTShengXiaoLeZhuShu(int ballsSize) {
		return (int) zuhe(2, ballsSize);
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
	 * 大乐透机选
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
}
