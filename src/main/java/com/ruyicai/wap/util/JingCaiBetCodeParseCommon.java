package com.ruyicai.wap.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.jc.lq.Dxf;
import com.ruyicai.wap.util.jc.lq.Rfsf;
import com.ruyicai.wap.util.jc.lq.Sf;
import com.ruyicai.wap.util.jc.lq.SfcJc;
import com.ruyicai.wap.util.jc.zq.Bf;
import com.ruyicai.wap.util.jc.zq.BqcJc;
import com.ruyicai.wap.util.jc.zq.Jqs;
import com.ruyicai.wap.util.jc.zq.Rqspf;
import com.ruyicai.wap.util.jc.zq.Spf;

import net.sf.json.JSONObject;

/**
 * 竞彩注码解析公共类
 * @author Administrator
 *
 */
@Component
public class JingCaiBetCodeParseCommon {
	@Autowired
	Spf spf;
	@Autowired
	Rqspf rqspf;
	@Autowired
	Bf bf;
	@Autowired
	Jqs jqs;
	@Autowired
	BqcJc bqcJc;
	@Autowired
	Sf sf;
	@Autowired
	Rfsf rfsf;
	@Autowired
	SfcJc sfcJc;
	@Autowired
	Dxf dxf;
	/**
	 * 获取赛事信息
	 * @param matchesValueObject
	 * @return
	 */
	public static JSONObject getMatchInfoJson(JSONObject matchesValueObject) {
		JSONObject resultJson = new JSONObject();
		//队伍名称
		JSONObject matchesObject = matchesValueObject.getJSONObject("matches");
		String team = matchesObject.getString("team");
		String homeTeam = JingCaiUtil.getTeamName(team).getString("homeTeam"); //主队
		String guestTeam = JingCaiUtil.getTeamName(team).getString("guestTeam"); //客队
		//比赛结果
		String homeScore = ""; //主队全场比分
		String guestScore = ""; //客队全场比分
		String homeHalfScore = ""; //主队半场比分
		String guestHalfScore = ""; //客队半场比分
		String letPoint = ""; //开奖公告里的让分
		String basePoint = ""; //开奖公告里的预设总分
		String resultString = matchesValueObject.getString("result");
		if (resultString!=null&&!resultString.equals("null")) {
			JSONObject resultObject = matchesValueObject.getJSONObject("result");
			letPoint = resultObject.getString("letpoint"); //让分
			basePoint = resultObject.getString("basepoint"); //预设总分
			String result = resultObject.getString("result"); //全场比分
			homeScore = JingCaiUtil.getScoreByResult(result).getString("homeScore");
			guestScore = JingCaiUtil.getScoreByResult(result).getString("guestScore");
			
			String halfResult = resultObject.getString("firsthalfresult"); //半场比分
			homeHalfScore = JingCaiUtil.getScoreByResult(halfResult).getString("homeScore");
			guestHalfScore = JingCaiUtil.getScoreByResult(halfResult).getString("guestScore");
		}
		resultJson.put("homeTeam", homeTeam);
		resultJson.put("guestTeam", guestTeam);
		resultJson.put("homeScore", homeScore);
		resultJson.put("guestScore", guestScore);
		resultJson.put("homeHalfScore", homeHalfScore);
		resultJson.put("guestHalfScore", guestHalfScore);
		resultJson.put("letPoint", letPoint);
		resultJson.put("basePoint", basePoint);
		return resultJson;
	}
	
	/**
	 * 设置TeamMap
	 * @param lotNo
	 * @param id
	 * @param code
	 * @param peiLvString
	 * @param teamMap
	 * @param play
	 * @param letPoint
	 * @param basePoint
	 */
	public void putTeamMap(String lotNo, String id, String code, String peiLvString, Map<String, Object> teamMap, 
			String play, String letPoint, String basePoint) {
		if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_SPF)) { //竞彩足球胜平负
			spf.putMap(lotNo, id, code, peiLvString, teamMap);
		}else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_RQSPF)) { //竞彩足球胜平负
			rqspf.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_BF)) { //竞彩足球比分
			bf.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_ZJQ)) { //竞彩足球总进球数
			jqs.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_BCSPF)) { //竞彩足球半全场
			bqcJc.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_SF)) { //竞彩篮球胜负
			sf.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_RFSF)) { //竞彩篮球让分胜负
			rfsf.putMap(lotNo, id, code, peiLvString, teamMap, play, letPoint);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_SFC)) { //竞彩篮球胜分差
			sfcJc.putMap(lotNo, id, code, peiLvString, teamMap);
		} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_DXF)) { //竞彩篮球大小分
			dxf.putMap(lotNo, id, code, peiLvString, teamMap, play, basePoint);
		}
	}
	
	/**
	 * 获取解析后的注码
	 * @param lotNo
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getBetContentJson(Map<String, Object> value, boolean addRangFen) {
		JSONObject resultObject = new JSONObject();
		StringBuilder builder = new StringBuilder(); //投注内容
		String letScore = ""; //让分(竞彩篮球让分胜负)
		String totalScore = ""; //总分盘(竞彩篮球大小分)
		String endCharacter = "<br/>"; //结束符
		Map<String, Map<String, List<String>>> lotNoMap = (Map<String, Map<String, List<String>>>)value.get("code");
		if (lotNoMap!=null) {
			String betContentHtml = "";
			for (Map.Entry<String, Map<String, List<String>>> entry : lotNoMap.entrySet()) {
				String lotNo = entry.getKey();
				Map<String, List<String>> codeMap = entry.getValue();
				
				if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_SPF)) { //竞彩足球胜平负
					betContentHtml = spf.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_RQSPF)) { //竞彩足球胜平负
					betContentHtml = rqspf.getBetContentByMap(value, codeMap);
				}else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_BF)) { //竞彩足球比分
					betContentHtml = bf.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_ZJQ)) { //竞彩足球总进球数
					betContentHtml = jqs.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_ZQ_BCSPF)) { //竞彩足球半全场
					betContentHtml = bqcJc.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_SF)) { //竞彩篮球胜负
					betContentHtml = sf.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_RFSF)) { //竞彩篮球让分胜负
					JSONObject jsonObject = rfsf.getBetContentByMap(value, codeMap, addRangFen);
					betContentHtml = jsonObject.getString("betContent");
					letScore = jsonObject.getString("letScore");
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_SFC)) { //竞彩篮球胜分差
					betContentHtml = sfcJc.getBetContentByMap(value, codeMap);
				} else if (lotNo!=null&&lotNo.equals(Constants.LOTNO_JC_LQ_DXF)) { //竞彩篮球大小分
					JSONObject jsonObject = dxf.getBetContentByMap(value, codeMap);
					betContentHtml = jsonObject.getString("betContent");
					totalScore = jsonObject.getString("totalScore");
				}
				builder.append(betContentHtml);
			}
		}
		String betContentHtml = builder.toString();
		if(builder.toString().endsWith(endCharacter)) betContentHtml =  builder.toString().substring(0, builder.toString().length()-5);
		else betContentHtml = builder.toString();
				//StringUtil.removeEndCharacter(builder.toString(), endCharacter);
		System.out.println("betContentHtml:"+betContentHtml);
		resultObject.put("betContentHtml", betContentHtml);
		resultObject.put("letScore", letScore);
		resultObject.put("totalScore", totalScore);
		return resultObject;
	}
	
}
