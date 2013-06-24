package com.ruyicai.wap.util.jc.zq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;

import net.sf.json.JSONObject;

/**
 * 竟彩足球胜平负
 * @author Administrator
 *
 */
@Component
public class Spf {
	@Autowired
	JingCaiUtil jingCaiUtil;
	/**
	 * 解析Map
	 * @param valueMap
	 * @param homeScore
	 * @param guestScore
	 * @return
	 */
	public String getBetContentByMap(Map<String, Object> value, Map<String, List<String>> codeMap) {
		String homeScore = (String)value.get("homeScore"); //主队比分
		String guestScore = (String)value.get("guestScore"); //客队比分
		
		StringBuilder builder = new StringBuilder(); //投注内容
		String endCharacter = "<br/>"; //结束符
		if (codeMap!=null) {
			for(Map.Entry<String, List<String>> entry : codeMap.entrySet()) {
				String code = entry.getKey();
				List<String> peiLvs = entry.getValue();
				String pCode = jingCaiUtil.getSpfHomeBySingleCode(code, false); //解析后的注码
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				
				JSONObject jsonObject = jingCaiUtil.getSpfWinByScore(homeScore, guestScore);
				String win = jsonObject.getString("win"); //开奖号码
				if (win!=null&&win.equals(code)) {
//					parseCode = "<font color=\"red\">"+parseCode+"</font>";
					parseCode = "<span>"+parseCode+"</span>";
				}
				builder.append(parseCode).append(endCharacter);
			}
		}
		return builder.toString();
	}
	
	/**
	 * 将赛事信息放在Map里
	 * @param info
	 * @param peiLvString
	 * @param infoMap
	 */
	public void putMap(String lotNo, String id, String betCode, String peiLvString, Map<String, Object> teamMap) {
		System.out.println("betCode==="+betCode);
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				String peiLv = JingCaiUtil.getPeiLvByCode(id, peiLvString, code);
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
	public String getBetContent(String id, String peiLvString, String betCode, boolean isHtml, 
			String homeScore, String guestScore) {
		StringBuilder builder = new StringBuilder(""); //投注内容
		String endCharacter = " "; //结束符
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				String peiLv = JingCaiUtil.getPeiLvByCode(id, peiLvString, code);
				List<String> peiLvs = new ArrayList<String>();
				peiLvs.add(peiLv);
				//解析后的注码
				String pCode = jingCaiUtil.getSpfHomeBySingleCode(code, false); 
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				//中奖标红
				if (isHtml) {
					endCharacter = "<br/>";
					JSONObject object = jingCaiUtil.getSpfWinByScore(homeScore, guestScore);
					String win = object.getString("win"); //开奖号码
					if (win!=null&&win.equals(code)) {
//						parseCode = "<font color=\"red\">"+parseCode+"</font>";
						parseCode = "<span>"+parseCode+"</span>";
					}
				}
				builder.append(parseCode).append(endCharacter);
			}
		}
		String result = "";
		if(builder.toString().endsWith(endCharacter)) return builder.toString().substring(0, builder.toString().length()-5);
		else result = builder.toString();
		return result;	
	}
	
}
