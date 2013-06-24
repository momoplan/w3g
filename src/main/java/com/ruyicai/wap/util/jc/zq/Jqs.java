package com.ruyicai.wap.util.jc.zq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.jc.JqsEnum;

/**
 * 竟彩足球进球数
 * @author Administrator
 *
 */
@Component
public class Jqs {
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
				//解析后的注码
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(code, peiLvs);
				//中奖标红
				String win = getJqsWinByScore(homeScore, guestScore);
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
		StringBuilder builder = new StringBuilder(); 
		String endCharacter = " "; //结束符
		if (betCode!=null&&!"".equals(betCode)) {
			for (int i = 0; i < betCode.length(); i++) {
				String code = betCode.substring(i, i+1);
				String peiLv = JingCaiUtil.getPeiLvByCode(id, peiLvString, code);
				List<String> peiLvs = new ArrayList<String>();
				peiLvs.add(peiLv);
				//解析后的注码
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(code, peiLvs);
				//中奖标红
				if (isHtml) {
					endCharacter = "<br/>";
					String win = getJqsWinByScore(homeScore, guestScore);
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
	
	/**
	 * 获得进球彩的开奖号码
	 * @param homeScore
	 * @param guestScore
	 * @return
	 */
	private static String getJqsWinByScore(String homeScore, String guestScore) {
		String win = "";
		if (homeScore!=null&&!"".equals(homeScore)&&!homeScore.equals("null")
				&&guestScore!=null&&!"".equals(guestScore)&&!guestScore.equals("null")) {
			BigDecimal homeScoreB = new BigDecimal(homeScore);
			BigDecimal guestScoreB = new BigDecimal(guestScore);
			BigDecimal totalScore = homeScoreB.add(guestScoreB);
			if (totalScore.compareTo(new BigDecimal(7))==-1) { //小于7
				win = totalScore.toString();
			} else { //大于等于7
				win = JqsEnum.seven.value();
			}
		}
		return win;
	}
	
}
