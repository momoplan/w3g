package com.ruyicai.wap.util.jc.lq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.jc.JcPlayEnum;

import net.sf.json.JSONObject;

/**
 * 竟彩篮球让分胜负
 * @author Administrator
 *
 */
@Component
public class Rfsf {
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
	public JSONObject getBetContentByMap(Map<String, Object> value, Map<String, List<String>> codeMap, boolean addRangFen) {
		JSONObject resultObject = new JSONObject();
		
		String homeScore = (String)value.get("homeScore"); //主队比分
		String guestScore = (String)value.get("guestScore"); //客队比分
		List<String> letPoints = (List<String>)value.get("letPoints"); //让分
		
		StringBuilder builder = new StringBuilder(); //投注内容
		String endCharacter = "<br/>"; //结束符
		if (codeMap!=null) {
			for(Map.Entry<String, List<String>> entry : codeMap.entrySet()) {
				String code = entry.getKey();
				List<String> peiLvs = entry.getValue();
				//解析后的注码
				String pCode = jingCaiUtil.getSpfHomeBySingleCode(code, addRangFen);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs); 
				//中奖标红
				for (String letPoint : letPoints) {
					String win = jingCaiUtil.getRfsfWinByScore(homeScore, guestScore, letPoint);
					if (win!=null&&win.equals(code)) {
//						parseCode = "<font color=\"red\">"+parseCode+"</font>";
						parseCode = "<span>"+parseCode+"</span>";
						break;
					}
				}
				builder.append(parseCode).append(endCharacter);
			}
		}
		resultObject.put("betContent", builder.toString());
		resultObject.put("letScore", LotteryUtil.getDouHaoStringFromStringList(letPoints));
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
			Map<String, Object> teamMap, String play, String letScore) {
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				JSONObject jsonObject = JingCaiUtil.getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code);
				String peiLv = jsonObject.getString("peiLv");
				String letPoint = ""; //让分
				//J00001,J00003,J00004,J00005,J00006,J00008单关没有赔率,所以让分要从开奖公告里获取
				if (play!=null&&play.equals(JcPlayEnum.danGuan.value())) { //单关
					letPoint = (letScore!=null&&!"".equals(letScore)||letScore.equals("null")) ? "" : letScore;
				} else { //过关
					letPoint = jsonObject.getString("letPoint");
				}
				//让分
				List<String> letPoints = null;
				Object obj = teamMap.get("letPoints");
				if (obj==null) {
					letPoints = new ArrayList<String>();
				} else {
					letPoints = (List<String>)obj;
				}
				if (!letPoints.contains(letPoint)) {
					letPoints.add(letPoint);
				}
				teamMap.put("letPoints", letPoints);
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
			String homeScore, String guestScore, String play, String letScore) {
		JSONObject resultObject = new JSONObject();
		
		StringBuilder builder = new StringBuilder(); //投注内容
		String letPoint = ""; //让分
		String endCharacter = " "; //结束符
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				JSONObject object = JingCaiUtil.getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code);
				String peiLv = object.getString("peiLv");
				//J00001,J00003,J00004,J00005,J00006,J00008单关没有赔率,所以让分要从开奖公告里获取
				if (play!=null&&play.equals(JcPlayEnum.danGuan.value())) { //单关
					letPoint = (letScore!=null&&!"".equals(letScore)||letScore.equals("null")) ? "" : letScore;
				} else { //过关
					letPoint = object.getString("letPoint");
				}
				List<String> peiLvs = new ArrayList<String>();
				peiLvs.add(peiLv);
				//解析后的注码
				String pCode = jingCaiUtil.getSpfHomeBySingleCode(code, false);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs); 
				//中奖标红
				if (isHtml) {
					endCharacter = "<br/>";
					String win = jingCaiUtil.getRfsfWinByScore(homeScore, guestScore, letPoint);
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
		resultObject.put("letPoint", letPoint);
		return resultObject;
	}
	
}
