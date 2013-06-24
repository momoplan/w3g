package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotterySevenStarUtil {

	/**
	 * 得到自选betCode，betCodeView，zhuShu
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param ball4s
	 * @param ball5s
	 * @param ball6s
	 * @param ball7s
	 * @return
	 */
	public static Map<String, String> getSelfBetMap(List<String> ball1s,List<String> ball2s,List<String> ball3s,List<String> ball4s,List<String> ball5s,List<String> ball6s,List<String> ball7s){
		Map<String, String> selfBetMap = new HashMap<String, String>();
		Collections.sort(ball1s);
		Collections.sort(ball2s);
		Collections.sort(ball3s);
		Collections.sort(ball4s);
		Collections.sort(ball5s);
		Collections.sort(ball6s);
		Collections.sort(ball7s);
		String betCodeView = "第一位:"+LotteryUtil.getDouHaoStringFromStringList(ball1s)+"<br/>第二位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball2s)+"<br/>第三位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball3s)+"<br/>第四位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball4s)+"<br/>第五位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball5s)+"<br/>第六位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball6s)+"<br/>第七位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball7s);
		int zhuShu = ball1s.size()*ball2s.size()*ball3s.size()*ball4s.size()*ball5s.size()*ball6s.size()*ball7s.size();
		String betCode = getSelfBetCode(ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s);
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
		String ball = "";
		List<String> ball1s = new ArrayList<String>();
		List<String> ball2s = new ArrayList<String>();
		List<String> ball3s = new ArrayList<String>();
		List<String> ball4s = new ArrayList<String>();
		List<String> ball5s = new ArrayList<String>();
		List<String> ball6s = new ArrayList<String>();
		List<String> ball7s = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			ball1s = getAutoBetCodeAuto(1,0,9);
			ball2s = getAutoBetCodeAuto(1,0,9);
			ball3s = getAutoBetCodeAuto(1,0,9);
			ball4s = getAutoBetCodeAuto(1,0,9);
			ball5s = getAutoBetCodeAuto(1,0,9);
			ball6s = getAutoBetCodeAuto(1,0,9);
			ball7s = getAutoBetCodeAuto(1,0,9);
			ball = LotteryUtil.getStringFromStringList(ball1s)+"|"
					+LotteryUtil.getStringFromStringList(ball2s)+"|"
					+LotteryUtil.getStringFromStringList(ball3s)+"|"
					+LotteryUtil.getStringFromStringList(ball4s)+"|"
					+LotteryUtil.getStringFromStringList(ball5s)+"|"
					+LotteryUtil.getStringFromStringList(ball6s)+"|"
					+LotteryUtil.getStringFromStringList(ball7s);
			betCodeView += ball+"<br/>";
			betCode += getAutoBetCode(ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s)+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		autoBetMap.put("playType", "单式");
		autoBetMap.put("betCode", betCode);
		autoBetMap.put("betCodeView", betCodeView);
		autoBetMap.put("zhuShu", zhuShu);
		return autoBetMap;
	}
	/**
	 * 得到自选投注注码
	 * @param wballs
	 * @param qballs
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @return
	 */
	public static String getSelfBetCode(List<String> ball1s,List<String> ball2s,List<String> ball3s,List<String> ball4s,List<String> ball5s,List<String> ball6s,List<String> ball7s){
		String betCode = "";
		//单式投注0,1,2,3,4,5,6 / 0123456
		//复式投注09,1,2,3,4689,5,6 / 09,1,2,3,4689,5,6789
		betCode = LotteryUtil.getStringFromStringList(ball1s)+","
				+LotteryUtil.getStringFromStringList(ball2s)+","
				+LotteryUtil.getStringFromStringList(ball3s)+","
				+LotteryUtil.getStringFromStringList(ball4s)+","
				+LotteryUtil.getStringFromStringList(ball5s)+","
				+LotteryUtil.getStringFromStringList(ball6s)+","
				+LotteryUtil.getStringFromStringList(ball7s);
		return betCode;
	}
	/**
	 * 得到机选投注注码
	 * @param ballList
	 * @return
	 */
	public static String getAutoBetCode(List<String> ball1s,List<String> ball2s,List<String> ball3s,List<String> ball4s,List<String> ball5s,List<String> ball6s,List<String> ball7s){
		String betCode = "";
		//单式投注0,1,2,3,4,5,6 / 0123456
		betCode = LotteryUtil.getStringFromStringList(ball1s)+","
				+LotteryUtil.getStringFromStringList(ball2s)+","
				+LotteryUtil.getStringFromStringList(ball3s)+","
				+LotteryUtil.getStringFromStringList(ball4s)+","
				+LotteryUtil.getStringFromStringList(ball5s)+","
				+LotteryUtil.getStringFromStringList(ball6s)+","
				+LotteryUtil.getStringFromStringList(ball7s);
		return betCode;
	}
	/**
	 * 七星彩机选
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
