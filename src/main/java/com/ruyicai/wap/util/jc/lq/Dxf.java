package com.ruyicai.wap.util.jc.lq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.jc.DxfEnum;
import com.ruyicai.wap.util.jc.JcPlayEnum;

import net.sf.json.JSONObject;

/**
 * 竟彩篮球大小分
 * @author Administrator
 *
 */@Component
public class Dxf {
	@Autowired
	JingCaiUtil jingCaiUtil;
	/**
	 * 解析Map
	 * @param valueMap
	 * @param homeScore
	 * @param guestScore
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getBetContentByMap(Map<String, Object> value, Map<String, List<String>> codeMap) {
		JSONObject resultObject = new JSONObject();
		
		String homeScore = (String)value.get("homeScore"); //主队比分
		String guestScore = (String)value.get("guestScore"); //客队比分
		List<String> basePoints = (List<String>)value.get("basePoints"); //预设总分
		
		StringBuilder builder = new StringBuilder(); //投注内容
		String endCharacter = "<br/>"; //结束符
		if (codeMap!=null) {
			for(Map.Entry<String, List<String>> entry : codeMap.entrySet()) {
				String code = entry.getKey();
				List<String> peiLvs = entry.getValue();
				//解析后的注码
				String pCode = parseSingleCode(code);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				//中奖标红
				for (String basePoint : basePoints) {
					String win = jingCaiUtil.getDxfWinByScore(homeScore, guestScore, basePoint);
					if (win!=null&&win.equals(code)) {
//						parseCode = "<font color=\"red\">"+parseCode+"</font>";
						parseCode = "<span>"+parseCode+"</span>";
					}
				}
				builder.append(parseCode).append(endCharacter);
			}
		}
		resultObject.put("betContent", builder.toString());
		resultObject.put("totalScore", LotteryUtil.getDouHaoStringFromStringList(basePoints));
		return resultObject;
	}
	
	/**
	 * 将赛事信息放在Map里
	 * @param info
	 * @param peiLvString
	 * @param infoMap
	 */
	@SuppressWarnings("unchecked")
	public void putMap(String lotNo, String id, String betCode, String peiLvString, 
			Map<String, Object> teamMap, String play, String baseScore) {
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				JSONObject jsonObject = JingCaiUtil.getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code);
				String peiLv = jsonObject.getString("peiLv");
				String basePoint = ""; //基本分
				//J00001,J00003,J00004,J00005,J00006,J00008单关没有赔率,所以预设总分要从开奖公告里获取
				if (play!=null&&play.equals(JcPlayEnum.danGuan.value())) { //单关
					basePoint = (baseScore==null||"".equals(baseScore)||baseScore.equals("null")) ? "" : baseScore;
				} else { //过关
					basePoint = jsonObject.getString("basePoint");
				}
				//预设总分
				List<String> basePoints = null;
				Object obj = teamMap.get("basePoints");
				if (obj==null) {
					basePoints = new ArrayList<String>();
				} else {
					basePoints = (List<String>)obj;
				}
				if (!basePoints.contains(basePoint)) {
					basePoints.add(basePoint);
				}
				teamMap.put("basePoints", basePoints);
				//将注码和赔率放在Map里
				jingCaiUtil.putCodeToCodeMap(lotNo, teamMap, code, peiLv);
			}
		}
	}
	
	/**
	 * 获得投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public JSONObject getBetContent(String id, String peiLvString, String betCode, boolean isHtml, 
			String homeScore, String guestScore, String play, String baseScore) {
		JSONObject resultObject = new JSONObject();
		
		StringBuilder builder = new StringBuilder(); //投注内容
		String basePoint = ""; //基本分
		String endCharacter = " "; //结束符
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				JSONObject object = JingCaiUtil.getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code);
				String peiLv = object.getString("peiLv");
				//J00001,J00003,J00004,J00005,J00006,J00008单关没有赔率,所以预设总分要从开奖公告里获取
				if (play!=null&&play.equals(JcPlayEnum.danGuan.value())) { //单关
					basePoint = (baseScore==null||"".equals(baseScore)||baseScore.equals("null")) ? "" : baseScore;
				} else { //过关
					basePoint = object.getString("basePoint");
				}
				List<String> peiLvs = new ArrayList<String>();
				peiLvs.add(peiLv);
				//解析后的注码
				String pCode = parseSingleCode(code);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				//中奖标红
				if (isHtml) {
					endCharacter = "<br/>";
					String win = jingCaiUtil.getDxfWinByScore(homeScore, guestScore, basePoint);
					if (win!=null&&win.equals(code)) {
//						parseCode = "<font color=\"red\">"+parseCode+"</font>";
						parseCode = "<span>"+parseCode+"</span>";
					}
				}
				builder.append(parseCode).append(endCharacter);
			}
		}
		String betContent = "";
		if(builder.toString().endsWith(endCharacter)) betContent =  builder.toString().substring(0, builder.toString().length()-5);
		else betContent = builder.toString();
		resultObject.put("betContent", betContent);
		resultObject.put("basePoint", basePoint);
		return resultObject;
	}
	
	/**
	 * 根据单个注码获得大小分
	 * 
	 * @param code
	 * @return
	 */
	public static String parseSingleCode(String code) {
		String result = "";
		if (code!=null) {
			DxfEnum[] values = DxfEnum.values();
			for (DxfEnum dxfEnum : values) {
				if (code.equals(dxfEnum.value())) {
					result = dxfEnum.memo();
					break;
				}
			}
		}
		return result;
	}
	
}