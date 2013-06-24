package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryJiangXiElevenSelectFiveUtil {
	/**
	 * 得到任选自选betCode，betCodeView，zhuShu
	 * @param balls
	 * @param ballType
	 * @return
	 */
	public static Map<String, String> getOptionSelfBetMap(List<String> balls,String ballType){
		Map<String, String> optionSelfBetMap = new HashMap<String, String>();
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		balls = LotteryUtil.getStringListFromZeroString(ball);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = getOptionZhuShu(balls.size(),ballType);
		String betCode = ballType+"|"+LotteryUtil.getKongGeStringFromStringList(balls);
		String playType = "";
		if("R1".equals(ballType)) playType = "任选一";
		if("R2".equals(ballType)) playType = "任选二";
		if("R3".equals(ballType)) playType = "任选三";
		if("R4".equals(ballType)) playType = "任选四";
		if("R5".equals(ballType)) playType = "任选五";
		if("R6".equals(ballType)) playType = "任选六";
		if("R7".equals(ballType)) playType = "任选七";
		if("R8".equals(ballType)) playType = "任选八";
		optionSelfBetMap.put("playType", playType);
		optionSelfBetMap.put("betCode", betCode);
		optionSelfBetMap.put("betCodeView", betCodeView);
		optionSelfBetMap.put("zhuShu", zhuShu+"");
		return optionSelfBetMap;
		
	}
	/**
	 * 得到任选机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getOptionAutoBetMap(String zhuShu,String ballType){
		Map<String, String> optionAutoBetMap = new HashMap<String, String>();
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		List<String> balls = new ArrayList<String>();
		String ball = "";
		int ballsSize = getBallsSize(ballType);
		for(int i=0;i<zhuShuInt;i++){
			balls = getAutoBetCodeAuto(ballsSize,1,11);
			ball = LotteryUtil.addZeroForBetCodeList(balls);
			balls = LotteryUtil.getStringListFromZeroString(ball);
			betCodeView += LotteryUtil.getDouHaoStringFromStringList(balls)+"<br/>";
			betCode += "R"+ballsSize+"|"+LotteryUtil.getKongGeStringFromStringList(balls)+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		String playType = "";
		if("R1".equals(ballType)) playType = "任选一";
		if("R2".equals(ballType)) playType = "任选二";
		if("R3".equals(ballType)) playType = "任选三";
		if("R4".equals(ballType)) playType = "任选四";
		if("R5".equals(ballType)) playType = "任选五";
		if("R6".equals(ballType)) playType = "任选六";
		if("R7".equals(ballType)) playType = "任选七";
		if("R8".equals(ballType)) playType = "任选八";
		optionAutoBetMap.put("playType", playType);
		optionAutoBetMap.put("betCode", betCode);
		optionAutoBetMap.put("betCodeView", betCodeView);
		optionAutoBetMap.put("zhuShu", zhuShu);
		return optionAutoBetMap;
	}
	public static int getBallsSize(String ballType){
		if("R1".equals(ballType)) return 1;
		if("R2".equals(ballType)) return 2;
		if("R3".equals(ballType)) return 3;
		if("R4".equals(ballType)) return 4;
		if("R5".equals(ballType)) return 5;
		if("R6".equals(ballType)) return 6;
		if("R7".equals(ballType)) return 7;
		if("R8".equals(ballType)) return 8;
		return 1;
	}
	/**
	 * 得到胆拖betCode，betCodeView，zhuShu
	 * @param dballs
	 * @param tballs
	 * @param ballType
	 * @return
	 */
	public static Map<String, String> getDanTuoBetMap(List<String> dballs,List<String> tballs,String ballType){
		Map<String, String> danTuoBetMap = new HashMap<String, String>();
		String dball = LotteryUtil.addZeroForBetCodeList(dballs);
		String tball = LotteryUtil.addZeroForBetCodeList(tballs);
		dballs = LotteryUtil.getStringListFromZeroString(dball);
		tballs = LotteryUtil.getStringListFromZeroString(tball);
		String betCodeView = "胆码:"+LotteryUtil.getDouHaoStringFromStringList(dballs)+"<br/>拖码:"
				+LotteryUtil.getDouHaoStringFromStringList(tballs);
		int zhuShu = getDanTuoZhuShu(dballs.size(),tballs.size(),ballType);
		String betCode = ballType+"|"+LotteryUtil.getKongGeStringFromStringList(dballs)+"$"
				+LotteryUtil.getKongGeStringFromStringList(tballs);
		String playType = "";
		if("R2".equals(ballType)) playType = "任选二胆拖";
		if("R3".equals(ballType)) playType = "任选三胆拖";
		if("R4".equals(ballType)) playType = "任选四胆拖";
		if("R5".equals(ballType)) playType = "任选五胆拖";
		if("R6".equals(ballType)) playType = "任选六胆拖";
		if("R7".equals(ballType)) playType = "任选七胆拖";
		if("R8".equals(ballType)) playType = "任选八胆拖";
		if("Z2".equals(ballType)) playType = "前二组选胆拖";
		if("Z3".equals(ballType)) playType = "前三组选胆拖";
		danTuoBetMap.put("playType", playType);
		danTuoBetMap.put("betCode", betCode);
		danTuoBetMap.put("betCodeView", betCodeView);
		danTuoBetMap.put("zhuShu", zhuShu+"");
		return danTuoBetMap;
		
	}
	/**
	 * 得到组二组三betCode，betCodeView，zhuShu
	 * @param balls
	 * @param ballType
	 * @return
	 */
	public static Map<String, String> getGroupBetMap(List<String> balls,String ballType){
		Map<String, String> groupBetMap = new HashMap<String, String>();
		String ball = LotteryUtil.addZeroForBetCodeList(balls);
		balls = LotteryUtil.getStringListFromZeroString(ball);
		String betCodeView = LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = getGroupZhuShu(balls.size(),ballType);
		String betCode = ballType+"|"+LotteryUtil.getKongGeStringFromStringList(balls);
		String playType = "";
		if("Z2".equals(ballType)) playType = "前二组选";
		if("Z3".equals(ballType)) playType = "前三组选";
		groupBetMap.put("playType", playType);
		groupBetMap.put("betCode", betCode);
		groupBetMap.put("betCodeView", betCodeView);
		groupBetMap.put("zhuShu", zhuShu+"");
		return groupBetMap;
		
	}
	/**
	 * 得到前二直选betCode，betCodeView，zhuShu
	 * @param ball1s
	 * @param ball2s
	 * @param ballType
	 * @return
	 */
	public static Map<String, String> getForwardTwoDirectSelectBetMap(List<String> ball1s,List<String> ball2s){
		Map<String, String> forwardTwoDirectSelectBetMap = new HashMap<String, String>();
		String ball1 = LotteryUtil.addZeroForBetCodeList(ball1s);
		String ball2 = LotteryUtil.addZeroForBetCodeList(ball2s);
		ball1s = LotteryUtil.getStringListFromZeroString(ball1);
		ball2s = LotteryUtil.getStringListFromZeroString(ball2);
		String betCodeView = "第一位:"+LotteryUtil.getDouHaoStringFromStringList(ball1s)+"<br/>第二位:"+LotteryUtil.getDouHaoStringFromStringList(ball2s);
		int zhuShu = ball1s.size()*ball2s.size();
		String betCode = "Q2|"+LotteryUtil.getKongGeStringFromStringList(ball1s)+","
				+LotteryUtil.getKongGeStringFromStringList(ball2s);
		forwardTwoDirectSelectBetMap.put("playType", "前二直选");
		forwardTwoDirectSelectBetMap.put("betCode", betCode);
		forwardTwoDirectSelectBetMap.put("betCodeView", betCodeView);
		forwardTwoDirectSelectBetMap.put("zhuShu", zhuShu+"");
		return forwardTwoDirectSelectBetMap;
	}
	/**
	 * 得到前三直选betCode，betCodeView，zhuShu
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param ballType
	 * @return
	 */
	public static Map<String, String> getForwardThreeDirectSelectBetMap(List<String> ball1s,List<String> ball2s,List<String> ball3s){
		Map<String, String> forwardThreeDirectSelectBetMap = new HashMap<String, String>();
		String ball1 = LotteryUtil.addZeroForBetCodeList(ball1s);
		String ball2 = LotteryUtil.addZeroForBetCodeList(ball2s);
		String ball3 = LotteryUtil.addZeroForBetCodeList(ball3s);
		ball1s = LotteryUtil.getStringListFromZeroString(ball1);
		ball2s = LotteryUtil.getStringListFromZeroString(ball2);
		ball3s = LotteryUtil.getStringListFromZeroString(ball3);
		String betCodeView = "第一位:"+LotteryUtil.getDouHaoStringFromStringList(ball1s)+"<br/>第二位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball2s)+"<br/>第三位:"
				+LotteryUtil.getDouHaoStringFromStringList(ball3s);
		int zhuShu = ball1s.size()*ball2s.size()*ball3s.size();
		String betCode = "Q3|"+LotteryUtil.getKongGeStringFromStringList(ball1s)+","
				+LotteryUtil.getKongGeStringFromStringList(ball2s)+","
				+LotteryUtil.getKongGeStringFromStringList(ball3s);
		forwardThreeDirectSelectBetMap.put("playType", "前三直选");
		forwardThreeDirectSelectBetMap.put("betCode", betCode);
		forwardThreeDirectSelectBetMap.put("betCodeView", betCodeView);
		forwardThreeDirectSelectBetMap.put("zhuShu", zhuShu+"");
		return forwardThreeDirectSelectBetMap;
	}
	/**
	 * 得到任选注数
	 * @param ballsSize
	 * @param ballType
	 * @return
	 */
	public static int getOptionZhuShu(int ballsSize,String ballType){
		if("R1".equals(ballType)) return ballsSize;
		if("R2".equals(ballType)) return (int)nchoosek(ballsSize, 2);
		if("R3".equals(ballType)) return (int)nchoosek(ballsSize, 3);
		if("R4".equals(ballType)) return (int)nchoosek(ballsSize, 4);
		if("R5".equals(ballType)) return (int)nchoosek(ballsSize, 5);
		if("R6".equals(ballType)) return (int)nchoosek(ballsSize, 6);
		if("R7".equals(ballType)) return (int)nchoosek(ballsSize, 7);
		if("R8".equals(ballType)) return (int)nchoosek(ballsSize, 8);
		return 1;
		
	}
	/**
	 * 得到组二组三注数
	 * @param ballsSize
	 * @param ballType
	 * @return
	 */
	public static int getGroupZhuShu(int ballsSize,String ballType){
		if("Z2".equals(ballType)) return (int)nchoosek(ballsSize, 2);
		if("Z3".equals(ballType)) return (int)nchoosek(ballsSize, 3);
		return 1;
	}
	/**
	 * 得到胆拖注数
	 * @param dballsSize
	 * @param tballsSize
	 * @param ballType
	 * @return
	 */
	public static int getDanTuoZhuShu(int dballsSize,int tballsSize,String ballType){
		if("R2".equals(ballType)||"Z2".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 1);
			return 0;
		}
		if("R3".equals(ballType)||"Z3".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R4".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 3);
			if(dballsSize==2) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R5".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 4);
			if(dballsSize==2) return (int)nchoosek(tballsSize, 3);
			if(dballsSize==3) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R6".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 5);
			if(dballsSize==2) return (int)nchoosek(tballsSize, 4);
			if(dballsSize==3) return (int)nchoosek(tballsSize, 3);
			if(dballsSize==4) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R7".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 6);
			if(dballsSize==2) return (int)nchoosek(tballsSize, 5);
			if(dballsSize==3) return (int)nchoosek(tballsSize, 4);
			if(dballsSize==4) return (int)nchoosek(tballsSize, 3);
			if(dballsSize==5) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R8".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 7);
			if(dballsSize==2) return (int)nchoosek(tballsSize, 6);
			if(dballsSize==3) return (int)nchoosek(tballsSize, 5);
			if(dballsSize==4) return (int)nchoosek(tballsSize, 4);
			if(dballsSize==5) return (int)nchoosek(tballsSize, 3);
			if(dballsSize==6) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R3".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		if("R3".equals(ballType)){
			if(dballsSize==1) return (int)nchoosek(tballsSize, 2);
			return tballsSize;
		}
		return 1;
		
	}
	/**
	 * 计算从n中选出k个数的组合数
	 * 
	 * @param n
	 *            样本总数
	 * @param k
	 *            选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n, int k) {
		// 验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if (n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if (k == 0 || n == k) {
			return 1;
		}
		// 按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if (k > n / 2) {
			k = n - k;
		}
		// 通过组合数公式求组合数
		long result = multiplyByStep(n, k) / multiplyByStep(k, k);

		return result;
	}
	/**
	 * A(m,n) 算法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long multiplyByStep(int a, int b) {

		if (b <= 0 || b > a) {
			return -1;
		}
		int m = a;
		int n = a - b + 1;
		// 定义默认返回值
		long result = 1l;

		// m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if (m >= n) {
			for (int i = n; i <= m; i++) {
				result = result * i;
			}
		} else {
			for (int i = m; i <= n; i++) {
				result = result * i;
			}
		}
		return result;
	}
	/**
	 * 江西11选5机选
	 * @param ballNumber注码个数
	 * @param min注码允许最小数
	 * @param max注码允许最大数
	 * @return
	 */
	public static List<String> getAutoBetCodeAuto(int ballNumber, int min ,int max){
		List<Integer>list = new ArrayList<Integer>();
		List<String>listString = new ArrayList<String>();
		List<Integer>betCodeList = new ArrayList<Integer>();
		for(int i=min;i<=max;i++){
			list.add(i);
		}
		for(int i=0;i<ballNumber;i++) {
			int index = getRandomIntWithinRange(max-min+1-i);
			betCodeList.add(list.remove(index-1));
		}
		Collections.sort(betCodeList);
		for (Integer str : betCodeList) {
			listString.add(str+"");
		}
		return listString;	}
	
	// 返回 1-cap之间的一个整数  [1, cap]
	public static int getRandomIntWithinRange(int cap) {
		Random r = new Random();
		return r.nextInt(cap)+1;
	}

}
