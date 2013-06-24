package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryDoubleBallUtil {
	/**
	 * 得到自选betCode，betCodeView，zhuShu
	 * @param rballs
	 * @param bballs
	 * @return
	 */
	public static Map<String, String> getSelfBetMap(List<String> rballs,List<String> bballs){
		Map<String, String> selfBetMap = new HashMap<String, String>();
		String playType = "";
		List<Integer> rballIntegerList = LotteryUtil.getIntegerListForStringList(rballs);
		List<Integer> bballIntegerList = LotteryUtil.getIntegerListForStringList(bballs);
		Collections.sort(rballIntegerList);
		Collections.sort(bballIntegerList);
		String rball = LotteryUtil.addZeroForBetCodeIntegerList(rballIntegerList);
		String bball = LotteryUtil.addZeroForBetCodeIntegerList(bballIntegerList);
		List<String> rballList = LotteryUtil.getStringListFromZeroString(rball);
		List<String> bballList = LotteryUtil.getStringListFromZeroString(bball);
		String betCodeView = "红球:"+LotteryUtil.getDouHaoStringFromStringList(rballList)+"<br/>蓝球:"+LotteryUtil.getDouHaoStringFromStringList(bballList);
		int zhuShu = getSSQZhuShu(rballs.size(), bballs.size());
		String betCode = getBetCode(rballList, bballList);
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
		String playType = "";
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		String rball = "";
		String bball = "";
		List<String> rballList = new ArrayList<String>();
		List<String> bballList = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			rball = LotteryUtil.addZeroForBetCodeList(getAutoBetCodeAuto(6, 33));
			bball = LotteryUtil.addZeroForBetCodeList(getAutoBetCodeAuto(1, 16));
			rballList = LotteryUtil.getStringListFromZeroString(rball);
			bballList = LotteryUtil.getStringListFromZeroString(bball);
			betCodeView += LotteryUtil.getDouHaoStringFromStringList(rballList)+"|"+LotteryUtil.getDouHaoStringFromStringList(bballList)+"<br/>";
			betCode += getBetCode(rballList, bballList);
		}
		playType = "单式";
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
	public static Map<String, String> getDanTuoBetMap(List<String> rdballs,List<String> rtballs,List<String> bballs){
		Map<String, String> danTuoBetMap = new HashMap<String, String>();
		String playType = "";
		List<Integer> rdballIntegerList = LotteryUtil.getIntegerListForStringList(rdballs);
		List<Integer> rtballIntegerList = LotteryUtil.getIntegerListForStringList(rtballs);
		List<Integer> bballIntegerList = LotteryUtil.getIntegerListForStringList(bballs);
		Collections.sort(rdballIntegerList);
		Collections.sort(rtballIntegerList);
		Collections.sort(bballIntegerList);
		String rdball = LotteryUtil.addZeroForBetCodeIntegerList(rdballIntegerList);
		String rtball = LotteryUtil.addZeroForBetCodeIntegerList(rtballIntegerList);
		String bball = LotteryUtil.addZeroForBetCodeIntegerList(bballIntegerList);
		List<String> rdballList = LotteryUtil.getStringListFromZeroString(rdball);
		List<String> rtballList = LotteryUtil.getStringListFromZeroString(rtball);
		List<String> bballList = LotteryUtil.getStringListFromZeroString(bball);
		String betCodeView = "红球胆码:"+LotteryUtil.getDouHaoStringFromStringList(rdballList)+"<br/>红球拖码:"+LotteryUtil.getDouHaoStringFromStringList(rtballList)+"<br/>蓝球:"+LotteryUtil.getDouHaoStringFromStringList(bballList);
		int zhuShu = getSSQDanTuoZhuShu(rdballs.size(),rtballs.size(), bballs.size());
		String betCode = getDanTuoBetCode(rdballList, rtballList, bballList);
		playType = "胆拖";
		danTuoBetMap.put("playType", playType);
		danTuoBetMap.put("betCode", betCode);
		danTuoBetMap.put("betCodeView", betCodeView);
		danTuoBetMap.put("zhuShu", zhuShu+"");
		return danTuoBetMap;
		
	}
	/**
	 * 得到普通玩法投注注码
	 * @param rballList
	 * @param bballList
	 * @return
	 */
	public static String getBetCode(List<String> rballList,List<String> bballList){
		String betCode = "";
		//单式：红球单式，篮球单式0001010203040506~07^
		if(rballList.size()==6&&bballList.size()==1)
			betCode = "0001"+LotteryUtil.getStringFromStringList(rballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		//红复式：红球复式，篮球单式1001*01020304050607080910~01^
		//红色球最多20个
		else if(rballList.size()>6&&bballList.size()==1)
			betCode = "1001*"+LotteryUtil.getStringFromStringList(rballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		//蓝复式：红球单式，蓝球复式2001*010203040506~010203040506^
		//蓝球最多为16个
		else if(rballList.size()==6&&bballList.size()>1)
			betCode = "2001*"+LotteryUtil.getStringFromStringList(rballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		//双复式：红球复式，蓝球复式3001*01020304050607080910~010203040506^
		//红色球最多20个，蓝色为2-16个
		else if(rballList.size()>6&&bballList.size()>1)
			betCode = "3001*"+LotteryUtil.getStringFromStringList(rballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到胆拖投注注码
	 * @param rdballList
	 * @param rtballList
	 * @param bballList
	 * @return
	 */
	public static String getDanTuoBetCode(List<String> rdballList,List<String> rtballList,List<String> bballList){
		String betCode = "";
		//红胆蓝单式：红球胆，蓝球单式40010102030405*06070809101112~01^
		//红色球胆码托码最多20个
		if(bballList.size()==1)
			betCode = "4001"+LotteryUtil.getStringFromStringList(rdballList)+"*"
					+LotteryUtil.getStringFromStringList(rtballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		//红胆蓝复式：红球胆，蓝球复式50010102030405*06070809101112~010203040506^
		//红球托码最多20个，蓝球为2-8个
		else if(bballList.size()>1)
			betCode = "5001"+LotteryUtil.getStringFromStringList(rdballList)+"*"
					+LotteryUtil.getStringFromStringList(rtballList)+"~"
					+LotteryUtil.getStringFromStringList(bballList)+"^";
		return betCode;
		
	}
	/**
	 * 得到双色球注数
	 * @param rballNumber
	 * @param bballNumber
	 * @return
	 */
	public static int getSSQZhuShu(int rballsSize, int bballsSize){
		long C_r = zuhe(6,rballsSize);
		long C_b = zuhe(1,bballsSize);
		long zhuShu = (C_r * C_b);
		return (int)zhuShu;
	}
	/**
	 * 得到双色球胆拖注数
	 * @param rdballsSize
	 * @param rtballsSize
	 * @param bballsSize
	 * @return
	 */
	public static int getSSQDanTuoZhuShu(int rdballsSize,int rtballsSize, int bballsSize) {
		int zhushu = (int) (zuhe(6 - rdballsSize, rtballsSize) * zuhe(
				1, bballsSize));
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
	 * 双色球机选
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
