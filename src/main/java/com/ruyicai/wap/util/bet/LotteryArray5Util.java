package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryArray5Util {
	/**
	 * 得到自选betCode，betCodeView，zhuShu
	 * @param wballs
	 * @param qballs
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @return
	 */
	public static Map<String, String> getSelfBetMap(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs){
		Map<String, String> selfBetMap = new HashMap<String, String>();
		Collections.sort(wballs);
		Collections.sort(qballs);
		Collections.sort(bballs);
		Collections.sort(sballs);
		Collections.sort(gballs);
		String betCodeView = "万位:"+LotteryUtil.getDouHaoStringFromStringList(wballs)+"<br/>千位:"
				+LotteryUtil.getDouHaoStringFromStringList(qballs)+"<br/>百位:"
				+LotteryUtil.getDouHaoStringFromStringList(bballs)+"<br/>十位:"
				+LotteryUtil.getDouHaoStringFromStringList(sballs)+"<br/>个位:"
				+LotteryUtil.getDouHaoStringFromStringList(gballs);
		int zhuShu = wballs.size()*qballs.size()*bballs.size()*sballs.size()*gballs.size();
		String betCode = getSelfBetCode(wballs, qballs, bballs, sballs, gballs);
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
		List<String> wballs = new ArrayList<String>();
		List<String> qballs = new ArrayList<String>();
		List<String> bballs = new ArrayList<String>();
		List<String> sballs = new ArrayList<String>();
		List<String> gballs = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			wballs = getAutoBetCodeAuto(1,0,9);
			qballs = getAutoBetCodeAuto(1,0,9);
			bballs = getAutoBetCodeAuto(1,0,9);
			sballs = getAutoBetCodeAuto(1,0,9);
			gballs = getAutoBetCodeAuto(1,0,9);
			ball = LotteryUtil.getStringFromStringList(wballs)+"|"
					+LotteryUtil.getStringFromStringList(qballs)+"|"
					+LotteryUtil.getStringFromStringList(bballs)+"|"
					+LotteryUtil.getStringFromStringList(sballs)+"|"
					+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += getAutoBetCode(wballs, qballs, bballs, sballs, gballs)+";";
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
	public static String getSelfBetCode(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs){
		String betCode = "";
		//单式投注0,1,2,3,4 / 01234
		//复式投注09,1,2,3,4689 / 09,1,2,3,0123456789
		betCode = LotteryUtil.getStringFromStringList(wballs)+","
				+LotteryUtil.getStringFromStringList(qballs)+","
				+LotteryUtil.getStringFromStringList(bballs)+","
				+LotteryUtil.getStringFromStringList(sballs)+","
				+LotteryUtil.getStringFromStringList(gballs);
		return betCode;
	}
	/**
	 * 得到机选投注注码
	 * @param ballList
	 * @return
	 */
	public static String getAutoBetCode(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs){
		String betCode = "";
		//单式投注0,1,2,3,4 / 01234
		betCode = LotteryUtil.getStringFromStringList(wballs)+","
				+LotteryUtil.getStringFromStringList(qballs)+","
				+LotteryUtil.getStringFromStringList(bballs)+","
				+LotteryUtil.getStringFromStringList(sballs)+","
				+LotteryUtil.getStringFromStringList(gballs);
		return betCode;
	}
	/**
	 * 排列五机选
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
