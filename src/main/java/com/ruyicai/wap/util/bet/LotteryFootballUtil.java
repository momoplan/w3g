package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.digester.RegexMatcher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.HttpUtil;
import com.ruyicai.wap.vo.MatchHistory;
import com.ruyicai.wap.vo.MatchMessage;
import com.ruyicai.wap.vo.MatchOuPei;
import com.ruyicai.wap.vo.MatchRanking;
import com.ruyicai.wap.vo.MatchYaPan;


@Component
public class LotteryFootballUtil {
	private Logger logger = Logger.getLogger(LotteryFootballUtil.class);
	@Value("${jrtLot}")
	String jrtLot;
	@Value("${lottery}")
	String lottery;
	@Value("${dataanalysis}")
	String dataanalysis;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 查询足彩当前期
	 * @param lotNo
	 * @return
	 */
	public String getZCIssue(String lotNo){
		try{
			String url = lottery+"/select/getZCIssue?lotno="+lotNo;
			String result = commonUtil.setURLByGET(url);
			logger.info("LotteryFootballUtil/getZCIssue查询足彩当前期返回result:"+result);
			return result;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/getZCIssue查询足彩当前期返回异常:"+e.getMessage());
			return "";
		}
		
	}
	/**
	 * 查询足彩当前期
	 * @param lotNo
	 * @return
	 */
	public JSONArray selectZCIssue(String lotNo){
		JSONArray jsonArray = null;
		try{
			String result = getZCIssue(lotNo);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			JSONArray valueJsonArray = resultJsonObject.getJSONArray("value");
			String batchCode = "";
			String endTime = "";
			jsonArray = new JSONArray();
			for(int i=0;i<valueJsonArray.size();i++){
				JSONObject jsonObject = new JSONObject();
				JSONObject idJsonObject = valueJsonArray.getJSONObject(i).getJSONObject("id");
				batchCode = idJsonObject.getString("batchcode");
				endTime = commonUtil.getDataFormat(valueJsonArray.getJSONObject(i).getLong("endtime"), "");
				jsonObject.put("batchCode", batchCode);
				jsonObject.put("endTime", endTime);
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/selectZCIssue查询足彩当前期返回异常:"+e.getMessage());
			return jsonArray;
		}

	}
	/**
	 * 查询足彩对阵
	 * @param jsonObject
	 * @return
	 */
	public String getFlData(JSONObject jsonObject){
		try{
			String url = jrtLot+"zcAction.do?method=getFlData&jsonString="+jsonObject.toString();
			String result = HttpUtil.sendRequestByGet(url, true);
			logger.info("LotteryFootballUtil/getFlData查询足彩对阵返回result:"+result);
			return result;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/getFlData查询足彩对阵返回异常:"+e.getMessage());
			return "";
		}
		
	}
	/**
	 * 查询足彩对阵
	 * @param lotNo
	 * @param batchCode
	 * @return
	 */
	public JSONArray selectFlData(String lotNo,String batchCode){
		JSONArray jsonArray = null;
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("batchCode", batchCode);
			jsonObject.put("lotno", lotNo);
			String result = getFlData(jsonObject);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			String errorCode = resultJsonObject.getString("error_code");
			if(resultJsonObject!=null&&"000000".equals(errorCode)){
				jsonArray = resultJsonObject.getJSONArray("value");
			}
			return jsonArray;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/selectFlData查询足彩对阵返回异常:"+e.getMessage());
			return jsonArray;
		}

	}
	/**
	 * 查询足彩数据分析
	 * @param event T01003_2012178_1(彩种_期号_场次)
	 * @return
	 */
	public String getInfo(String event){
		try{
			String url = dataanalysis+"selectZc/getInfo";
			String parameter = "event="+event;
			String result = commonUtil.setUrlByPOST(url, parameter);
			logger.info("LotteryFootballUtil/getInfo查询足彩数据分析返回result:"+result);
			return result;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/getInfo查询足彩数据分析返回异常:"+e.getMessage());
			return "";
		}
		
	}
	/**
	 * @param lotNo
	 * @param batchCode
	 * @param tempId
	 * @param type(排行:ranking;历史:主10:history_z10,客10:history_k10,主5:history_z5,客5:history_k5;亚盘:yapan;欧赔:oupei)
	 * @return
	 */
	public Map<String, Object> selectDataAnalysis(String lotNo,String batchCode,String tempId,String type){
		Map<String, Object> dataAnalysisMap = null;
		List<Object> dataAnalysis= null;
		try{
			if(Constants.LOTNO_ZC_R9C.equals(lotNo)) lotNo = Constants.LOTNO_ZC_SFC_14C;
			String event = lotNo+"_"+batchCode+"_"+tempId;
			String result = getInfo(event);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			JSONObject valueJsonObject = resultJsonObject.getJSONObject("value");
			if(valueJsonObject!=null){
				dataAnalysisMap = new HashMap<String, Object>();
				dataAnalysis = new ArrayList<Object>();
				if("ranking".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("rankings");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchRanking matchRanking = new MatchRanking();
							matchRanking.setRanking(jsonObject.getInt("ranking"));
							matchRanking.setTeamID(jsonObject.getInt("teamID"));
							matchRanking.setTeamName(jsonObject.getString("teamName"));
							matchRanking.setWin(jsonObject.getInt("win"));
							matchRanking.setStandoff(jsonObject.getInt("standoff"));
							matchRanking.setLose(jsonObject.getInt("lose"));
							matchRanking.setGoinBall(jsonObject.getInt("goinBall"));
							matchRanking.setLoseBall(jsonObject.getInt("loseBall"));
							matchRanking.setGoalDifference(jsonObject.getInt("goalDifference"));
							matchRanking.setIntegral(jsonObject.getInt("integral"));
							matchRanking.setMatchCount(jsonObject.getInt("matchcount"));
							dataAnalysis.add(matchRanking);
						}
					}
				}else if(type.indexOf("history")>-1){
					JSONArray jsonArray = null;
					if("history_z10".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("homePreSchedules");
					}else if("history_k10".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("guestPreSchedules");
					}else if("history_z5".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("homeAfterSchedules");
					}else if("history_k5".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("guestAfterSchedules");
					}
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchHistory matchHistory = new MatchHistory();
							matchHistory.setLocation(jsonObject.getString("location"));
							matchHistory.setMatchSeason(jsonObject.getString("matchSeason"));
							matchHistory.setHomeTeamID(jsonObject.getString("homeTeamID"));
							matchHistory.setGuestTeamID(jsonObject.getString("guestTeamID"));
							matchHistory.setHomeTeam(jsonObject.getString("homeTeam"));
							matchHistory.setGuestTeam(jsonObject.getString("guestTeam"));
							matchHistory.setNeutrality(jsonObject.getString("neutrality"));
							matchHistory.setMatchTime(commonUtil.getDataFormat(jsonObject.getLong("matchTime"), ""));
							matchHistory.setHome_Order(jsonObject.getString("home_Order"));
							matchHistory.setGuest_Order(jsonObject.getString("guest_Order"));
							matchHistory.setMatchState(getMatchState(jsonObject.getString("matchState")));
							matchHistory.setWeather(jsonObject.getString("weather"));
							matchHistory.setTemperature(jsonObject.getString("temperature"));
							matchHistory.setHomeScore(jsonObject.getString("homeScore"));
							matchHistory.setHomeHalfScore(jsonObject.getString("homeHalfScore"));
							matchHistory.setGuestScore(jsonObject.getString("guestScore"));
							matchHistory.setGuestHalfScore(jsonObject.getString("guestHalfScore"));
							matchHistory.setHome_Red(jsonObject.getString("home_Red"));
							matchHistory.setGuest_Red(jsonObject.getString("guest_Red"));
							matchHistory.setHome_Yellow(jsonObject.getString("home_Yellow"));
							matchHistory.setGuest_Yellow(jsonObject.getString("guest_Yellow"));
							matchHistory.setSclassName(jsonObject.getString("sclassName"));
							matchHistory.setSclassName_j(jsonObject.getString("sclassName_j"));
							dataAnalysis.add(matchHistory);
						}
					}
				}else if("yapan".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("letGoals");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchYaPan matchYaPan = new MatchYaPan();
							matchYaPan.setCompanyName(jsonObject.getString("companyName"));
							if(jsonObject.getString("modifyTime")==null||"".equals(jsonObject.getString("modifyTime"))||"null".equals(jsonObject.getString("modifyTime")))
								matchYaPan.setModifyTime("");
							else
								matchYaPan.setModifyTime(commonUtil.getDataFormat(jsonObject.getLong("modifyTime"), ""));
							matchYaPan.setClosePan(jsonObject.getString("closePan"));
							matchYaPan.setFirstGoal(jsonObject.getString("firstGoal"));
							matchYaPan.setFirstUpodds(jsonObject.getString("firstUpodds"));
							matchYaPan.setFirstGoal_name(jsonObject.getString("firstGoal_name"));
							matchYaPan.setFirstDownodds(jsonObject.getString("firstDownodds"));
							matchYaPan.setGoal(jsonObject.getString("goal"));
							matchYaPan.setUpOdds(jsonObject.getString("upOdds"));
							matchYaPan.setDownOdds(jsonObject.getString("downOdds"));
							matchYaPan.setGoal_name(jsonObject.getString("goal_name"));
							matchYaPan.setZouDi(jsonObject.getString("zouDi"));
							dataAnalysis.add(matchYaPan);
						}
					}
				}else if("oupei".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("standards");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchOuPei matchOuPei = new MatchOuPei();
							matchOuPei.setFanHuanLu(jsonObject.getString("fanHuanLu"));
							matchOuPei.setHomeWinLu(jsonObject.getString("homeWinLu"));
							matchOuPei.setStandoffLu(jsonObject.getString("standoffLu"));
							matchOuPei.setGuestWinLu(jsonObject.getString("guestWinLu"));
							matchOuPei.setK_h(jsonObject.getString("k_h"));
							matchOuPei.setK_s(jsonObject.getString("k_s"));
							matchOuPei.setK_g(jsonObject.getString("k_g"));
							matchOuPei.setCompanyName(jsonObject.getString("companyName"));
							matchOuPei.setFirstHomeWin(jsonObject.getString("firstHomeWin"));
							matchOuPei.setFirstStandoff(jsonObject.getString("firstStandoff"));
							matchOuPei.setFirstGuestWin(jsonObject.getString("firstGuestWin"));
							matchOuPei.setHomeWin(jsonObject.getString("homeWin"));
							matchOuPei.setStandoff(jsonObject.getString("standoff"));
							matchOuPei.setGuestWin(jsonObject.getString("guestWin"));
							if(jsonObject.getString("modifyTime")==null||"".equals(jsonObject.getString("modifyTime"))||"null".equals(jsonObject.getString("modifyTime")))
								matchOuPei.setModifyTime("");
							else
								matchOuPei.setModifyTime(commonUtil.getDataFormat(jsonObject.getLong("modifyTime"), ""));
							dataAnalysis.add(matchOuPei);
						}
					}
				}
				JSONObject scheduleJsonObject = valueJsonObject.getJSONObject("schedule");
				MatchMessage matchMessage = new MatchMessage();
				matchMessage.setLocation(scheduleJsonObject.getString("location"));
				matchMessage.setMatchSeason(scheduleJsonObject.getString("matchSeason"));
				matchMessage.setHomeTeamID(scheduleJsonObject.getString("homeTeamID"));
				matchMessage.setGuestTeamID(scheduleJsonObject.getString("guestTeamID"));
				matchMessage.setHomeTeam(scheduleJsonObject.getString("homeTeam"));
				matchMessage.setGuestTeam(scheduleJsonObject.getString("guestTeam"));
				matchMessage.setNeutrality(scheduleJsonObject.getString("neutrality"));
				matchMessage.setMatchTime(commonUtil.getDataFormat(scheduleJsonObject.getLong("matchTime"), ""));
				matchMessage.setHome_Order(scheduleJsonObject.getString("home_Order"));
				matchMessage.setGuest_Order(scheduleJsonObject.getString("guest_Order"));
				matchMessage.setMatchState(getMatchState(scheduleJsonObject.getString("matchState")));
				matchMessage.setWeather(scheduleJsonObject.getString("weather"));
				matchMessage.setTemperature(scheduleJsonObject.getString("temperature"));
				matchMessage.setHomeScore(scheduleJsonObject.getString("homeScore"));
				matchMessage.setHomeHalfScore(scheduleJsonObject.getString("homeHalfScore"));
				matchMessage.setGuestScore(scheduleJsonObject.getString("guestScore"));
				matchMessage.setGuestHalfScore(scheduleJsonObject.getString("guestHalfScore"));
				matchMessage.setHome_Red(scheduleJsonObject.getString("home_Red"));
				matchMessage.setGuest_Red(scheduleJsonObject.getString("guest_Red"));
				matchMessage.setHome_Yellow(scheduleJsonObject.getString("home_Yellow"));
				matchMessage.setGuest_Yellow(scheduleJsonObject.getString("guest_Yellow"));
				matchMessage.setSclassName(scheduleJsonObject.getString("sclassName"));
				matchMessage.setSclassName_j(scheduleJsonObject.getString("sclassName_j"));

				dataAnalysisMap.put("dataAnalysis", dataAnalysis);
				dataAnalysisMap.put("matchMessage", matchMessage);
			}
			
			return dataAnalysisMap;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/selectDataAnalysis查询足彩数据分析返回异常:"+e.getMessage());
			return dataAnalysisMap;
		}
	}
	/**
	 * @param matchState
	 * 0:未开,1:上半场,2:中场,3:下半场,-11:待定,-12:腰斩,
	 * -13:中断,-14:推迟,-1:完场，-10取消
	 * @return
	 */
	public String getMatchState(String matchState){
		if("0".equals(matchState)) return "未开";
		if("1".equals(matchState)) return "上半场";
		if("2".equals(matchState)) return "中场";
		if("3".equals(matchState)) return "下半场";
		if("-11".equals(matchState)) return "待定";
		if("-12".equals(matchState)) return "腰斩";
		if("-13".equals(matchState)) return "中断";
		if("-14".equals(matchState)) return "推迟";
		if("-1".equals(matchState)) return "完场";
		if("-10".equals(matchState)) return "取消";
		return "";
	}
	public String getRenXuan9ZhuShu(String betCode){
		long zhuShu =1;
		
		if(betCode.indexOf("$")>-1){
			zhuShu = dantuo(betCode);
		}else{
			String str[] = betCode.split(",");
			int sum = 0;
			 for (String string : str) {
				if(string.equals("#")) sum++; 
				else zhuShu*=string.length();
			}
			if(sum<5) zhuShu = convertNine(betCode);
		}
		return zhuShu+"";
	}
	/**
	 * 足彩胆拖计算注数
	 * 
	 * d2:胆码中双选的个数
	 * d3:胆码中三选的个数
	 * t1:拖码中单选的个数
	 * t2:拖码中双选的个数
	 * t3:拖码中三选的个数
	 * choose：从拖码选择来构成整个投注号码的个数（9-胆码个数）
	 * @return 注数
	 */
	public static long dantuo(String bet) {
		String[] bets = bet.split("\\$");
		int dtotal=0,d2=0,d3=0,t1=0,t2=0,t3=0,choose=0;
		for(String s1:bets[0].split(",")) {
			if(!s1.equals("#")) {
				dtotal = dtotal + 1;
				if(s1.length()==2) {
					d2 = d2 + 1;
				}else if(s1.length()==3) {
					d3 = d3 + 1;
				}
			}
		}
		
		for(String s2:bets[1].split(",")) {
			if(!s2.equals("#")) {
				if(s2.length()==1) {
					t1 = t1 + 1;
				}else if(s2.length()==2) {
					t2 = t2 + 1;
				}else if(s2.length()==3) {
					t3 = t3 + 1;
				}
			}
		}
		choose = 9 - dtotal;
		long m = exp(2, d2);
		long n = exp(3, d3);
		long result = 0;
		for (int i = 0; i <= t1; i++) {
			for (int j = 0; j <= t2; j++) {
				int k = choose - i - j;
				if(k <= t3 && k >= 0) {
					long nk1, nk2, nk3, exp1, exp2;
					nk1 = (t1 == 0 ? 1 : nchoosek(t1, i));//
					nk2 = (t2 == 0 ? 1 : nchoosek(t2, j));
					nk3 = (t3 == 0 ? 1 : nchoosek(t3, k));
					exp1 = exp(2, j);
					exp2 = exp(3, k);
					result = result + nk1 * nk2 * nk3 * exp1 * exp2;
					System.out.println("i=" + i + ",j=" + j + ",k=" + k);
				}
				
			}
		}
		if (m > 0)
			result = result * m;
		if (n > 0)
			result = result * n;
		bets = null;
		return result;
	}
	
	
	
	
	//转九计算
	//选出全部的能组成任九场的注码，计算注数累加之和
	public static long convertNine(String betcode) {
		int z1=0,z2=0,z3=0;
		String[] zhumas = betcode.split(",");
		for(int i = 0;i < zhumas.length;i++) {
			if(!"#".equals(zhumas[i])) {
				if(zhumas[i].length()==1) {
					z1 = z1 + 1;
				}else if(zhumas[i].length()==2) {
					z2 = z2 + 1;
				}else if(zhumas[i].length()==3) {
					z3 = z3 + 1;
				}
			}
		}
		
		long result = 0;
		for(int i = 0;i <= z1;i++) {
			for(int j = 0;j <= z2;j++) {
				int k = 9 - i - j;
				if(k <= z3 && k >= 0) {
					long nk1, nk2, nk3, exp1, exp2;
					nk1 = (z1 == 0 ? 1 : nchoosek(z1, i));//
					nk2 = (z2 == 0 ? 1 : nchoosek(z2, j));
					nk3 = (z3 == 0 ? 1 : nchoosek(z3, k));
					exp1 = exp(2, j);
					exp2 = exp(3, k);
					result = result + nk1 * nk2 * nk3 * exp1 * exp2;
				}
			}
		}
		return result;
	}
	/**
	 * @param d
	 * @param z
	 * @return
	 */
	public static long exp(long d, long z) {
		long result = 1L;
		for (int i = 0; i < z; i++) {
			result = result * d;
		}
		return result;
	}
	/**
	 * 计算从n中选出k个数的组合数
	 * @param n 样本总数
	 * @param k 选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n,int k) {
		if(n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if(k == 0 || n==k) {
			return 1;
		}
		if(k > n/2) {
			k = n - k;
		}
		
//		System.out.println("n:"+n);
//		System.out.println("k:"+k);
		
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
		if(m < 0 || n < 0) {
			return -1;
		}
		
		long result = 1l;
		
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
}
