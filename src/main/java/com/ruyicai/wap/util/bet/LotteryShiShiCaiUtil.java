package com.ruyicai.wap.util.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotteryShiShiCaiUtil {
	/**
	 * 得到1星自选betCode，betCodeView，zhuShu
	 * @param gballs
	 * @return
	 */
	public static Map<String, String> getOneStarSelfBetMap(List<String> gballs){
		Map<String, String> oneStarSelfBetMap = new HashMap<String, String>();
		Collections.sort(gballs);
		String betCodeView ="-,-,-,-,"+LotteryUtil.getStringFromStringList(gballs);
		int zhuShu = gballs.size();
		String betCode = "1D|"+betCodeView;
		oneStarSelfBetMap.put("playType", "一星");
		oneStarSelfBetMap.put("betCode", betCode);
		oneStarSelfBetMap.put("betCodeView", betCodeView);
		oneStarSelfBetMap.put("zhuShu", zhuShu+"");
		return oneStarSelfBetMap;
	}
	/**
	 * 得到1星机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getOneStarAutoBetMap(String zhuShu){
		Map<String, String> oneStarAutoBetMap = new HashMap<String, String>();
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		String ball = "";
		List<String> gballs = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			gballs = getAutoBetCodeAuto(1,0,9);
			ball = "-,-,-,-,"+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += "1D|"+ball+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		oneStarAutoBetMap.put("playType", "一星");
		oneStarAutoBetMap.put("betCode", betCode);
		oneStarAutoBetMap.put("betCodeView", betCodeView);
		oneStarAutoBetMap.put("zhuShu", zhuShu);
		return oneStarAutoBetMap;
	}
	/**
	 * 得到2星自选betCode，betCodeView，zhuShu
	 * @param gballs
	 * @param sballs
	 * @return
	 */
	public static Map<String, String> getTwoStarDirectBetMap(List<String> gballs,List<String> sballs){
		Map<String, String> twoStarSelfBetMap = new HashMap<String, String>();
		Collections.sort(gballs);
		Collections.sort(sballs);
		String betCodeView ="-,-,-,"+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
		int zhuShu = gballs.size()*sballs.size();
		String betCode = "2D|"+betCodeView;
		twoStarSelfBetMap.put("playType", "二星直选");
		twoStarSelfBetMap.put("betCode", betCode);
		twoStarSelfBetMap.put("betCodeView", betCodeView);
		twoStarSelfBetMap.put("zhuShu", zhuShu+"");
		return twoStarSelfBetMap;
	}
	/**
	 * 得到2星机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getTwoStarAutoBetMap(String zhuShu){
		Map<String, String> twoStarAutoBetMap = new HashMap<String, String>();
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		String ball = "";
		List<String> gballs = new ArrayList<String>();
		List<String> sballs = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			gballs = getAutoBetCodeAuto(1,0,9);
			sballs = getAutoBetCodeAuto(1,0,9);
			ball = "-,-,-,"+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += "2D|"+ball+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		twoStarAutoBetMap.put("playType", "二星直选");
		twoStarAutoBetMap.put("betCode", betCode);
		twoStarAutoBetMap.put("betCodeView", betCodeView);
		twoStarAutoBetMap.put("zhuShu", zhuShu);
		return twoStarAutoBetMap;
	}
	/**
	 * 得到2星组选betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getTwoStarGroupBetMap(List<String> balls){
		Map<String, String> twoStarGroupBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView =LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = getShiShiCaiTwoStarGroupZhuShu(balls.size());
		String betCode = getTwoStarGroupBetCode(balls);
		twoStarGroupBetMap.put("playType", "二星组选");
		twoStarGroupBetMap.put("betCode", betCode);
		twoStarGroupBetMap.put("betCodeView", betCodeView);
		twoStarGroupBetMap.put("zhuShu", zhuShu+"");
		return twoStarGroupBetMap;
	}
	/**
	 * 得到2星和值betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getTwoStarHeZhiBetMap(List<String> balls){
		Map<String, String> twoStarHeZhiBetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView =LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = 0;
		for(int i=0;i<balls.size();i++){
			zhuShu += getShiShiCaiTwoStarHeZhiZhuShu(Integer.parseInt(balls.get(i)));

		}
		String betCode = getTwoStarHeZhiBetCode(balls);
		twoStarHeZhiBetMap.put("playType", "二星和值");
		twoStarHeZhiBetMap.put("betCode", betCode);
		twoStarHeZhiBetMap.put("betCodeView", betCodeView);
		twoStarHeZhiBetMap.put("zhuShu", zhuShu+"");
		return twoStarHeZhiBetMap;
	}
	/**
	 * 得到3星自选betCode，betCodeView，zhuShu
	 * @param gballs
	 * @param sballs
	 * @param bballs
	 * @return
	 */
	public static Map<String, String> getThreeStarDirectBetMap(List<String> gballs,List<String> sballs,List<String> bballs){
		Map<String, String> threeStarDirectBetMap = new HashMap<String, String>();
		Collections.sort(gballs);
		Collections.sort(sballs);
		Collections.sort(bballs);
		String betCodeView ="-,-,"+LotteryUtil.getStringFromStringList(bballs)+","+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
		int zhuShu = gballs.size()*sballs.size()*bballs.size();
		String betCode = "3D|"+betCodeView;
		threeStarDirectBetMap.put("playType", "三星直选");
		threeStarDirectBetMap.put("betCode", betCode);
		threeStarDirectBetMap.put("betCodeView", betCodeView);
		threeStarDirectBetMap.put("zhuShu", zhuShu+"");
		return threeStarDirectBetMap;
	}
	/**
	 * 得到3星机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getThreeStarAutoBetMap(String zhuShu){
		Map<String, String> threeStarAutoBetMap = new HashMap<String, String>();
		int zhuShuInt = Integer.parseInt(zhuShu);
		String betCode = "";
		String betCodeView = "";
		String ball = "";
		List<String> gballs = new ArrayList<String>();
		List<String> sballs = new ArrayList<String>();
		List<String> bballs = new ArrayList<String>();
		for(int i=0;i<zhuShuInt;i++){
			gballs = getAutoBetCodeAuto(1,0,9);
			sballs = getAutoBetCodeAuto(1,0,9);
			bballs = getAutoBetCodeAuto(1,0,9);
			ball = "-,-,"+LotteryUtil.getStringFromStringList(bballs)+","+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += "3D|"+ball+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		threeStarAutoBetMap.put("playType", "三星直选");
		threeStarAutoBetMap.put("betCode", betCode);
		threeStarAutoBetMap.put("betCodeView", betCodeView);
		threeStarAutoBetMap.put("zhuShu", zhuShu);
		return threeStarAutoBetMap;
	}
	/**
	 * 得到3星组3betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getThreeStarGroup3BetMap(List<String> balls){
		Map<String, String> threeStarGroup3BetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView =LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = getShiShiCaiThreeStarGroup3ZhuShu(balls.size());
		String betCode = "Z3F|"+LotteryUtil.getStringFromStringList(balls);
		threeStarGroup3BetMap.put("playType", "三星组三");
		threeStarGroup3BetMap.put("betCode", betCode);
		threeStarGroup3BetMap.put("betCodeView", betCodeView);
		threeStarGroup3BetMap.put("zhuShu", zhuShu+"");
		return threeStarGroup3BetMap;
	}
	/**
	 * 得到3星组6betCode，betCodeView，zhuShu
	 * @param balls
	 * @return
	 */
	public static Map<String, String> getThreeStarGroup6BetMap(List<String> balls){
		Map<String, String> threeStarGroup6BetMap = new HashMap<String, String>();
		Collections.sort(balls);
		String betCodeView =LotteryUtil.getDouHaoStringFromStringList(balls);
		int zhuShu = getShiShiCaiThreeStarGroup6ZhuShu(balls.size());
		String betCode = "Z6F|"+LotteryUtil.getStringFromStringList(balls);
		threeStarGroup6BetMap.put("playType", "三星组六");
		threeStarGroup6BetMap.put("betCode", betCode);
		threeStarGroup6BetMap.put("betCodeView", betCodeView);
		threeStarGroup6BetMap.put("zhuShu", zhuShu+"");
		return threeStarGroup6BetMap;
	}
	/**
	 * 得到五星自选betCode，betCodeView，zhuShu
	 * @param gballs
	 * @param sballs
	 * @param bballs
	 * @param qballs
	 * @param wballs
	 * @return
	 */
	public static Map<String, String> getFiveStarDirectBetMap(List<String> gballs,List<String> sballs,List<String> bballs,List<String> qballs,List<String> wballs){
		Map<String, String> fiveStarSelfBetMap = new HashMap<String, String>();
		Collections.sort(gballs);
		Collections.sort(sballs);
		Collections.sort(bballs);
		Collections.sort(qballs);
		Collections.sort(wballs);
		String betCodeView =LotteryUtil.getStringFromStringList(wballs)+","+LotteryUtil.getStringFromStringList(qballs)+","+LotteryUtil.getStringFromStringList(bballs)+","+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
		int zhuShu = gballs.size()*sballs.size()*bballs.size()*qballs.size()*wballs.size();
		String betCode = "5D|"+betCodeView;
		fiveStarSelfBetMap.put("playType", "五星直选");
		fiveStarSelfBetMap.put("betCode", betCode);
		fiveStarSelfBetMap.put("betCodeView", betCodeView);
		fiveStarSelfBetMap.put("zhuShu", zhuShu+"");
		return fiveStarSelfBetMap;
	}
	/**
	 * 得到时时彩五星机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getFiveStarAutoBetMap(String zhuShu){
		Map<String, String> fiveStarAutoBetMap = new HashMap<String, String>();
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
			ball = LotteryUtil.getStringFromStringList(wballs)+","
					+LotteryUtil.getStringFromStringList(qballs)+","
					+LotteryUtil.getStringFromStringList(bballs)+","
					+LotteryUtil.getStringFromStringList(sballs)+","
					+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += "5D|"+ball+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		fiveStarAutoBetMap.put("playType", "五星直选");
		fiveStarAutoBetMap.put("betCode", betCode);
		fiveStarAutoBetMap.put("betCodeView", betCodeView);
		fiveStarAutoBetMap.put("zhuShu", zhuShu);
		return fiveStarAutoBetMap;
	}
	/**
	 * 得到五星通选betCode，betCodeView，zhuShu
	 * @param gballs
	 * @param sballs
	 * @param bballs
	 * @param qballs
	 * @param wballs
	 * @return
	 */
	public static Map<String, String> getFiveStarTongSelfBetMap(List<String> gballs,List<String> sballs,List<String> bballs,List<String> qballs,List<String> wballs){
		Map<String, String> fiveStarTongSelfBetMap = new HashMap<String, String>();
		Collections.sort(gballs);
		Collections.sort(sballs);
		Collections.sort(bballs);
		Collections.sort(qballs);
		Collections.sort(wballs);
		String betCodeView =LotteryUtil.getStringFromStringList(wballs)+","+LotteryUtil.getStringFromStringList(qballs)+","+LotteryUtil.getStringFromStringList(bballs)+","+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
		int zhuShu = gballs.size()*sballs.size()*bballs.size()*qballs.size()*wballs.size();
		String betCode = "5T|"+betCodeView;
		fiveStarTongSelfBetMap.put("playType", "五星通选");
		fiveStarTongSelfBetMap.put("betCode", betCode);
		fiveStarTongSelfBetMap.put("betCodeView", betCodeView);
		fiveStarTongSelfBetMap.put("zhuShu", zhuShu+"");
		return fiveStarTongSelfBetMap;
	}
	/**
	 * 得到时时彩五星机选betCode，betCodeView，zhuShu
	 * @param zhuShu
	 * @return
	 */
	public static Map<String, String> getFiveStarTongAutoBetMap(String zhuShu){
		Map<String, String> fiveStarTongAutoBetMap = new HashMap<String, String>();
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
			ball = LotteryUtil.getStringFromStringList(wballs)+","
					+LotteryUtil.getStringFromStringList(qballs)+","
					+LotteryUtil.getStringFromStringList(bballs)+","
					+LotteryUtil.getStringFromStringList(sballs)+","
					+LotteryUtil.getStringFromStringList(gballs);
			betCodeView += ball+"<br/>";
			betCode += "5T|"+ball+";";
		}
		if (betCode.endsWith(";"))
			betCode = betCode.substring(0, betCode.length() - 1);
		fiveStarTongAutoBetMap.put("playType", "五星通选");
		fiveStarTongAutoBetMap.put("betCode", betCode);
		fiveStarTongAutoBetMap.put("betCodeView", betCodeView);
		fiveStarTongAutoBetMap.put("zhuShu", zhuShu);
		return fiveStarTongAutoBetMap;
	}
	public static Map<String, String> getDaXiaoDanShuangSelfBetMap(String gball,String sball){
		Map<String, String> daXiaoDanShuangSelfBetMap = new HashMap<String, String>();
		String betCodeView =getDaXiaoDanShuang(sball)+","+getDaXiaoDanShuang(gball);
		int zhuShu = 1;
		String betCode = "DD|"+sball+gball;
		daXiaoDanShuangSelfBetMap.put("playType", "大小单双");
		daXiaoDanShuangSelfBetMap.put("betCode", betCode);
		daXiaoDanShuangSelfBetMap.put("betCodeView", betCodeView);
		daXiaoDanShuangSelfBetMap.put("zhuShu", zhuShu+"");
		return daXiaoDanShuangSelfBetMap;
	}
	public static String getDaXiaoDanShuang(String ball){
		if("1".equals(ball)) return "小";
		if("2".equals(ball)) return "大";
		if("4".equals(ball)) return "双";
		if("5".equals(ball)) return "单";
		return "";
		
	}
//	/**
//	 * 得到1星自选投注注码
//	 * @param gballs
//	 * @return
//	 */
//	public static String getOneStarSelfBetCode(List<String> gballs){
//		String betCode = "";
//		//单式投注1D|-,-,-,-,8
//		//复式投注1D|-,-,-,-,168
//		betCode = "1D|-,-,-,-,"+LotteryUtil.getStringFromStringList(gballs);
//		return betCode;
//	}
//	/**
//	 * 得到2星自选投注注码
//	 * @param gballs
//	 * @param sballs
//	 * @return
//	 */
//	public static String getTwoStarSelfBetCode(List<String> gballs,List<String> sballs){
//		String betCode = "";
//		//单式投注2D|-,-,-,1,8
//		//复式投注2D|-,-,-,168,8
//		betCode = "2D|-,-,-,"+LotteryUtil.getStringFromStringList(sballs)+","+LotteryUtil.getStringFromStringList(gballs);
//		return betCode;
//	}
	/**
	 * 得到2星组选投注注码
	 * @param balls
	 * @return
	 */
	public static String getTwoStarGroupBetCode(List<String> balls){
		String betCode = "";
		//F2|0268
		betCode = "F2|"+LotteryUtil.getStringFromStringList(balls);
		return betCode;
	}
	/**
	 * 得到2星和值投注注码
	 * @param balls
	 * @return
	 */
	public static String getTwoStarHeZhiBetCode(List<String> balls){
		String betCode = "";
		//S2|5  S2|5,6,14,16
		betCode = "S2|"+LotteryUtil.getDouHaoStringFromStringList(balls);
		return betCode;
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
	 * 时时彩机选
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
	
	/**
	 * 得到时时彩2星组选注数
	 * @param ballsSize
	 * @return
	 */
	public static int getShiShiCaiTwoStarGroupZhuShu(int ballsSize) {
		return (int) nchoosek(ballsSize,2);
	}
	/**
	 * 得到时时彩2星和值注数
	 * @param ballsSize
	 * @return
	 */
	public static int getShiShiCaiTwoStarHeZhiZhuShu(int ballsSize) {
		return twoStarHeZhi[ballsSize];
	}
	//二星和值表
	private static int[] twoStarHeZhi = {1,1,2,2,3,3,4,4,5,5,5,4,4,3,3,2,2,1,1};
		
	//三星和值表
//	private static int[] threeStarHeZhi = {1,3,6,10,15,21,28,36,45,55,63,69,73,
//											75,75,73,69,63,55,45,36,28,21,15,10,6,3,1};
	
	/**
	 * 得到时时彩3星组3注数
	 * @param ballsSize
	 * @return
	 */
	public static int getShiShiCaiThreeStarGroup3ZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(2, ballsSize) * 2;
		return zhushu;
	}
	/**
	 * 得到时时彩3星组6注数
	 * @param ballsSize
	 * @return
	 */
	public static int getShiShiCaiThreeStarGroup6ZhuShu(int ballsSize) {
		int zhushu = (int) zuhe(3, ballsSize);
		return zhushu;
	}
	/**
	 * 计算从n中选出k个数的组合数
	 * @param n 样本总数
	 * @param k 选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n,int k) {
		//验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if(n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if(k == 0 || n==k) {
			return 1;
		}
		//按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if(k > n/2) {
			k = n - k;
		}		
		//通过组合数公式求组合数
		long result = multiplyByStep(n,n-k+1)/multiplyByStep(k,1);
		
		return result;
	}
	
	/**
	 * 计算从m到n,以1为步长的成绩(m:1:n),m、n为正整数
	 * @param m 正整数
	 * @param n 正整数
	 * @return 步长为1，m到n的乘机，-1表示传入的mn为负数
	 */
	public static long multiplyByStep(int m,int n) {
		//数据验证，规定m和n不能小于0
		if(m < 0 || n < 0) {
			return -1;
		}
		
		//定义默认返回值
		long result = 1l;
		
		//m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if(m >= n) {
			for(int i = n;i <= m;i++) {
				result = result * i;
			}
		}else {
			for(int i = m;i <= n;i++) {
				result = result * i;
			}
		}
		return result;
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
}
