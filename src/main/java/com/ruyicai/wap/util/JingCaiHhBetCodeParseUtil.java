package com.ruyicai.wap.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;


/**
 * 竞彩混合过关注码解析
 * @author Administrator
 *
 */
@Component
public class JingCaiHhBetCodeParseUtil {
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	JingCaiUtil jingCaiUtil;
	@Autowired
	JingCaiBetCodeParseCommon jingCaiBetCodeParseCommon;
	@Autowired
	CommonUtil commonUtil;
	
	/**
	 * 获得注码解析数组(过滤重复的比赛)
	 * @param lotNo
	 * @param orderInfo
	 * @param tlotsValueObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getHHParseBetCodeList(JSONObject tlotsValueObject,String orderInfo) {
		JSONArray resultArray = new JSONArray();
		Map<String, Map<String, Object>> infoMap = new HashMap<String, Map<String, Object>>();

		List<String> plays = new ArrayList<String>();
		if (tlotsValueObject!=null) {
			JSONArray list = tlotsValueObject.getJSONArray("list");
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject tlotObject = list.getJSONObject(i);
					//注码(502@20130129|2|001|J00001|3^20130129|2|002|J00002|52^)
					String betCode = tlotObject.getString("betcode");
					//赔率(20130129*2*001*|J00001|3:2.700|^20130129*2*002*|J00002|52:1000|^)
					String peiLvString = tlotObject.getString("peilu");
					
					//玩法
					String[] split1 = betCode.split("@");
					String wanfa = split1[0];
					if (!plays.contains(wanfa)) {
						plays.add(wanfa);
					}
					
					String[] split2 = split1[1].split("\\^");
					for (String string : split2) {
						String[] split3 = string.split("\\|");
						String day = split3[0]; //日期
						String weekId = split3[1]; //星期
						String teamId = split3[2]; //场次
						String lotNo = split3[3]; //彩种
						String code = split3[4]; //注码
						String danMaString = jingCaiUtil.getDanMaString(orderInfo, string.substring(0, string.lastIndexOf("|"))).getString("result");

						//获取比赛信息
						JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekId, teamId);
						if (matchesValueObject==null||matchesValueObject.getString("matches")==null
								||matchesValueObject.getString("matches").equals("null")) {
							continue;
						}
						JSONObject matchInfoJson = JingCaiBetCodeParseCommon.getMatchInfoJson(matchesValueObject);
						String homeTeam = matchInfoJson.getString("homeTeam"); //主队
						String guestTeam = matchInfoJson.getString("guestTeam"); //客队
						String homeScore = matchInfoJson.getString("homeScore"); //主队全场比分
						String guestScore = matchInfoJson.getString("guestScore"); //客队全场比分
						String homeHalfScore = matchInfoJson.getString("homeHalfScore"); //主队半场比分
						String guestHalfScore = matchInfoJson.getString("guestHalfScore"); //客队半场比分
						String letPoint = matchInfoJson.getString("letPoint"); //开奖公告里的让分
						String basePoint = matchInfoJson.getString("basePoint"); //开奖公告里的预设总分
						
						String id = day+"*"+weekId+"*"+teamId+"*"; //赛事信息
						Map<String, Object> teamMap = infoMap.get(id);
						if (teamMap==null) {
							teamMap = new HashMap<String, Object>();
							teamMap.put("day", day);
							teamMap.put("weekId", weekId);
							teamMap.put("teamId", teamId);
							teamMap.put("homeTeam", homeTeam);
							teamMap.put("guestTeam", guestTeam);
							teamMap.put("homeScore", homeScore);
							teamMap.put("guestScore", guestScore);
							teamMap.put("homeHalfScore", homeHalfScore);
							teamMap.put("guestHalfScore", guestHalfScore);
							teamMap.put("danMaString", danMaString);
						}
						//竞彩混合过关的赔率有可能不带lotNo
						String peiLvPrefix = id;
						if (peiLvString!=null&&peiLvString.indexOf("J")>-1) {
							peiLvPrefix = id+"|"+lotNo;
						}
						System.out.println("peiLvString==="+peiLvString);
						System.out.println("peiLvPrefix=id="+peiLvPrefix);
						jingCaiBetCodeParseCommon.putTeamMap(lotNo, peiLvPrefix, code, peiLvString, teamMap, wanfa, letPoint, basePoint);
						infoMap.put(id, teamMap);
					}
				}
			}
		}
		//解析玩法
		String play = jingCaiUtil.getJingCaiPlayByList(plays);
		
		//循环Map
		if (infoMap!=null) {
			//排序
			infoMap = commonUtil.sortMapByKey(infoMap, false);
			for(Map.Entry<String, Map<String, Object>> entry : infoMap.entrySet()) {
				//String key = entry.getKey();
				Map<String, Object> value = entry.getValue();
				
				JSONObject betContentJson = jingCaiBetCodeParseCommon.getBetContentJson(value, true);
				String betContentHtml = betContentJson.getString("betContentHtml"); //投注内容(html)
				String letScore = betContentJson.getString("letScore"); //让分(竞彩篮球让分胜负)
				String totalScore = betContentJson.getString("totalScore"); //总分盘(竞彩篮球大小分)
				
				JSONObject object = new JSONObject();
				object.put("play", play); //玩法
				object.put("day", (String)value.get("day")); //日期
				object.put("weekId", (String)value.get("weekId")); //星期
				object.put("teamId", (String)value.get("teamId")); //赛事编号
				object.put("homeTeam", (String)value.get("homeTeam")); //主队
				object.put("guestTeam", (String)value.get("guestTeam")); //客队
				object.put("homeScore", (String)value.get("homeScore")); //主队比分
				object.put("guestScore", (String)value.get("guestScore")); //客队比分
				object.put("letScore", letScore); //让分(竞彩篮球让分胜负)
				object.put("totalScore", totalScore); //总分盘(竞彩篮球大小分)
				object.put("betContentHtml", betContentHtml); //投注内容(html)
				object.put("isDanMa", (String)value.get("danMaString")); //是否是胆码
				resultArray.add(object);
			}
		}
		return resultArray;
		
	}
	/**
	 * 获得注码解析数组(过滤重复的比赛)
	 * @param lotNo
	 * @param orderInfo
	 * @param tlotsValueObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getParseBetCodeList(JSONObject tlotsValueObject,String orderInfo,String lotNo) {
		JSONArray resultArray = new JSONArray();
		Map<String, Map<String, Object>> infoMap = new HashMap<String, Map<String, Object>>();

		List<String> plays = new ArrayList<String>();
		if (tlotsValueObject!=null) {
			JSONArray list = tlotsValueObject.getJSONArray("list");
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject tlotObject = list.getJSONObject(i);
					//注码(502@20130129|2|001|J00001|3^20130129|2|002|J00002|52^)
					String betCode = tlotObject.getString("betcode");
					//赔率(20130129*2*001*|J00001|3:2.700|^20130129*2*002*|J00002|52:1000|^)
					String peiLvString = tlotObject.getString("peilu");
					
					//玩法
					String[] split1 = betCode.split("@");
					String wanfa = split1[0];
					if (!plays.contains(wanfa)) {
						plays.add(wanfa);
					}
					
					String[] split2 = split1[1].split("\\^");
					for (String string : split2) {
						String[] split3 = string.split("\\|");
						String day = split3[0]; //日期
						String weekId = split3[1]; //星期
						String teamId = split3[2]; //场次
						String code = split3[3]; //注码
						String danMaString = jingCaiUtil.getDanMaString(orderInfo, string.substring(0, string.lastIndexOf("|"))).getString("result");

						//获取比赛信息
						JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekId, teamId);
						if (matchesValueObject==null||matchesValueObject.getString("matches")==null
								||matchesValueObject.getString("matches").equals("null")) {
							continue;
						}
						JSONObject matchInfoJson = JingCaiBetCodeParseCommon.getMatchInfoJson(matchesValueObject);
						String homeTeam = matchInfoJson.getString("homeTeam"); //主队
						String guestTeam = matchInfoJson.getString("guestTeam"); //客队
						String homeScore = matchInfoJson.getString("homeScore"); //主队全场比分
						String guestScore = matchInfoJson.getString("guestScore"); //客队全场比分
						String homeHalfScore = matchInfoJson.getString("homeHalfScore"); //主队半场比分
						String guestHalfScore = matchInfoJson.getString("guestHalfScore"); //客队半场比分
						String letPoint = matchInfoJson.getString("letPoint"); //开奖公告里的让分
						String basePoint = matchInfoJson.getString("basePoint"); //开奖公告里的预设总分
						
						String id = day+"*"+weekId+"*"+teamId+"*"; //赛事信息
						Map<String, Object> teamMap = infoMap.get(id);
						String result = getMatchesResult(lotNo, day, weekId, teamId);
						if (teamMap==null) {
							teamMap = new HashMap<String, Object>();
							teamMap.put("day", day);
							teamMap.put("weekId", weekId);
							teamMap.put("teamId", teamId);
							teamMap.put("homeTeam", homeTeam);
							teamMap.put("guestTeam", guestTeam);
							teamMap.put("homeScore", homeScore);
							teamMap.put("guestScore", guestScore);
							teamMap.put("homeHalfScore", homeHalfScore);
							teamMap.put("guestHalfScore", guestHalfScore);
							teamMap.put("danMaString", danMaString);
							teamMap.put("result", result);
						}
						//竞彩混合过关的赔率有可能不带lotNo
						String peiLvPrefix = id;
						if (peiLvString!=null&&peiLvString.indexOf("J")>-1) {
							peiLvPrefix = id+"|"+lotNo;
						}
						System.out.println("peiLvString==="+peiLvString);
						System.out.println("peiLvPrefix=id="+peiLvPrefix);
						jingCaiBetCodeParseCommon.putTeamMap(lotNo, peiLvPrefix, code, peiLvString, teamMap, wanfa, letPoint, basePoint);
						infoMap.put(id, teamMap);
					}
				}
			}
		}
		//解析玩法
		String play = jingCaiUtil.getJingCaiPlayByList(plays);
		
		//循环Map
		if (infoMap!=null) {
			//排序
			infoMap = commonUtil.sortMapByKey(infoMap, false);
			for(Map.Entry<String, Map<String, Object>> entry : infoMap.entrySet()) {
				//String key = entry.getKey();
				Map<String, Object> value = entry.getValue();
				
				JSONObject betContentJson = jingCaiBetCodeParseCommon.getBetContentJson(value, true);
				String betContentHtml = betContentJson.getString("betContentHtml"); //投注内容(html)
				String letScore = betContentJson.getString("letScore"); //让分(竞彩篮球让分胜负)
				String totalScore = betContentJson.getString("totalScore"); //总分盘(竞彩篮球大小分)
				
				JSONObject object = new JSONObject();
				object.put("play", play); //玩法
				object.put("day", (String)value.get("day")); //日期
				object.put("weekId", (String)value.get("weekId")); //星期
				object.put("teamId", (String)value.get("teamId")); //赛事编号
				object.put("homeTeam", (String)value.get("homeTeam")); //主队
				object.put("guestTeam", (String)value.get("guestTeam")); //客队
				object.put("let", (String)value.get("let")); //竞彩足球让球
				object.put("homeScore", (String)value.get("homeScore")); //主队比分
				object.put("guestScore", (String)value.get("guestScore")); //客队比分
				object.put("letScore", letScore); //让分(竞彩篮球让分胜负)
				object.put("totalScore", totalScore); //总分盘(竞彩篮球大小分)
				object.put("betContentHtml", betContentHtml); //投注内容(html)
				object.put("isDanMa", (String)value.get("danMaString")); //是否是胆码
				object.put("result", (String)value.get("result")); //赛果
				resultArray.add(object);
			}
		}
		return resultArray;
		
	}
	public String getMatchesResult(String lotNo,String day,String weekid,String teamid){
		JSONObject matchesValueObject = selectAllUtil.selectJingCaiMatchesValueObject(lotNo, day, weekid, teamid);
		if (matchesValueObject==null||matchesValueObject.getString("matches")==null
				||matchesValueObject.getString("matches").equals("null")) {
			return "";
		}
//		JSONObject matchesObject = matchesValueObject.getJSONObject("matches");
//		String team = matchesObject.getString("team");
//		String zhu = ""; //主队
//		String ke = ""; //客队
//		if (team!=null&&!team.trim().equals("null")&&team.indexOf(":")>-1) {
//			String[] teams = team.split(":");
//			zhu = teams[0];
//			ke = teams[1];
//		}
		
		//比赛结果
		String score = "未开";
		String result = "未开";
		if (matchesValueObject!=null) {
			String resultString = matchesValueObject.getString("result");
			if (resultString!=null&&!resultString.equals("null")) {
				JSONObject resultObject = matchesValueObject.getJSONObject("result");
				String scoreString = resultObject.getString("result");
				if (scoreString!=null&&!scoreString.equals("null")) {
					if (Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_BF.equals(lotNo)||Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)||Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)) { //竟彩足球
						score = scoreString;
					} else { //竟彩篮球
						if (scoreString!=null&&scoreString.indexOf(":")>-1) {
							String[] scores = scoreString.split(":");
							score = scores[1]+":"+scores[0];
						}
					}
				}
				result = jingCaiUtil.getJingCaiResultByScore(scoreString, true); //赛果
			}
		}
		return "<p>全场比分:<span>"+score+"</span></p><p>赛果:<span>"+result+";</span></p>";
	}
}
