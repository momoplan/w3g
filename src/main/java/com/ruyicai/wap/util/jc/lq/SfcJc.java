package com.ruyicai.wap.util.jc.lq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.JingCaiUtil;
import com.ruyicai.wap.util.jc.SfcEnum;

/**
 * 竟彩篮球胜分差
 * @author Administrator
 *
 */
@Component
public class SfcJc {
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
				String pCode = getScoreByCode(code);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				//中奖标红
				String win = getSfcWinByScore(homeScore, guestScore);
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
			int len = betCode.length() / 2;
			for (int i = 0; i < len; i++) {
				String code = betCode.substring(2 * i, 2 * i + 2);
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
			int len = betCode.length() / 2;
			for (int i = 0; i < len; i++) {
				String code = betCode.substring(2 * i, 2 * i + 2);
				String peiLv = JingCaiUtil.getPeiLvByCode(id, peiLvString, code);
				List<String> peiLvs = new ArrayList<String>();
				peiLvs.add(peiLv);
				//解析后的注码
				String pCode = getScoreByCode(code);
				String parseCode = jingCaiUtil.getParseCodeWithPeiLv(pCode, peiLvs);
				//中奖标红
				if (isHtml) {
					endCharacter = "<br/>";
					String win = getSfcWinByScore(homeScore, guestScore);
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
	 * 根据注码得到分差
	 * 
	 * @param code
	 * @return
	 */
	public static String getScoreByCode(String code) {
		String result = "";
		if (code != null) {
			SfcEnum[] values = SfcEnum.values();
			for (SfcEnum sfcEnum : values) {
				if (code.equals(sfcEnum.value())) {
					result = sfcEnum.memo();
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 获得胜分差的开奖号码
	 * @param homeScore
	 * @param guestScore
	 * @return
	 */
	private static String getSfcWinByScore(String homeScore, String guestScore) {
		String win = "";
		if (homeScore!=null&&!"".equals(homeScore)&&!homeScore.equals("null")
				&&guestScore!=null&&!"".equals(guestScore)&&!guestScore.equals("null")) {
			Integer homeScoreInt = Integer.parseInt(homeScore);
			Integer guestScoreInt = Integer.parseInt(guestScore);
			Integer homeDifference = homeScoreInt-guestScoreInt; //主胜多少分
			Integer guestDifference = guestScoreInt-homeScoreInt; //客胜多少分
			if (homeDifference>=1&&homeDifference<=5) { //主胜1-5
				win = SfcEnum.homeWin1_5.value();
			} else if (homeDifference>=6&&homeDifference<=10) { //主胜6-10
				win = SfcEnum.homeWin6_10.value();
			} else if (homeDifference>=11&&homeDifference<=15) { //主胜11-15
				win = SfcEnum.homeWin11_15.value();
			} else if (homeDifference>=16&&homeDifference<=20) { //主胜16-20
				win = SfcEnum.homeWin16_20.value();
			} else if (homeDifference>=21&&homeDifference<=25) { //主胜21-25
				win = SfcEnum.homeWin21_25.value();
			} else if (homeDifference>=26) { //主胜26+
				win = SfcEnum.homeWin26.value();
			} else if (guestDifference>=1&&guestDifference<=5) { //客胜1-5
				win = SfcEnum.guestWin1_5.value();
			} else if (guestDifference>=6&&guestDifference<=10) { //客胜6-10
				win = SfcEnum.guestWin6_10.value();
			} else if (guestDifference>=11&&guestDifference<=15) { //客胜11-15
				win = SfcEnum.guestWin11_15.value();
			} else if (guestDifference>=16&&guestDifference<=20) { //客胜16-20
				win = SfcEnum.guestWin16_20.value();
			} else if (guestDifference>=21&&guestDifference<=25) { //客胜21-25
				win = SfcEnum.guestWin21_25.value();
			} else if (guestDifference>=26) { //客胜26+
				win = SfcEnum.guestWin26.value();
			}
		}
		return win;
	}
	
}
