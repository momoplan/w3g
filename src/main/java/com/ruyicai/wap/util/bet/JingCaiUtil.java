package com.ruyicai.wap.util.bet;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.jc.DxfEnum;
import com.ruyicai.wap.util.jc.SpfEnum;
import com.ruyicai.wap.util.jc.lq.Dxf;
@Component
public class JingCaiUtil {
	@Value(value = "${lottery}")
	String lottery;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 获取竞彩 玩法
	 * @param wanfa
	 * @return
	 */
	public JSONObject getJingCaiPlay(String wanfa) {
		JSONObject object = new JSONObject();
		if (wanfa != null && wanfa.trim().equals("500")) {
			object.put("play", "单关");
			object.put("valueType", "0");
		} else if (wanfa != null && wanfa.trim().equals("502")) {
			object.put("play", "过关2串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("503")) {
			object.put("play", "过关3串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("504")) {
			object.put("play", "过关4串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("505")) {
			object.put("play", "过关5串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("506")) {
			object.put("play", "过关6串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("507")) {
			object.put("play", "过关7串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("508")) {
			object.put("play", "过关8串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("509")) {
			object.put("play", "过关2串3");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("510")) {
			object.put("play", "过关3串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("511")) {
			object.put("play", "过关3串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("512")) {
			object.put("play", "过关4串10");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("513")) {
			object.put("play", "过关4串14");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("514")) {
			object.put("play", "过关4串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("515")) {
			object.put("play", "过关5串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("516")) {
			object.put("play", "过关5串25");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("517")) {
			object.put("play", "过关5串30");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("518")) {
			object.put("play", "过关5串31");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("519")) {
			object.put("play", "过关6串21");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("520")) {
			object.put("play", "过关6串41");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("521")) {
			object.put("play", "过关6串56");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("522")) {
			object.put("play", "过关6串62");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("523")) {
			object.put("play", "过关6串63");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("524")) {
			object.put("play", "过关7串127");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("525")) {
			object.put("play", "过关8串255");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("526")) {
			object.put("play", "过关3串3");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("527")) {
			object.put("play", "过关3串4");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("528")) {
			object.put("play", "过关4串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("529")) {
			object.put("play", "过关4串11");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("530")) {
			object.put("play", "过关5串10");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("531")) {
			object.put("play", "过关5串20");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("532")) {
			object.put("play", "过关5串26");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("533")) {
			object.put("play", "过关6串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("534")) {
			object.put("play", "过关6串35");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("535")) {
			object.put("play", "过关6串50");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("536")) {
			object.put("play", "过关6串57");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("537")) {
			object.put("play", "过关7串120");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("538")) {
			object.put("play", "过关8串247");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("539")) {
			object.put("play", "过关4串4");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("540")) {
			object.put("play", "过关4串5");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("541")) {
			object.put("play", "过关5串16");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("542")) {
			object.put("play", "过关6串20");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("543")) {
			object.put("play", "过关6串42");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("544")) {
			object.put("play", "过关5串5");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("545")) {
			object.put("play", "过关5串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("546")) {
			object.put("play", "过关6串22");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("547")) {
			object.put("play", "过关7串35");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("548")) {
			object.put("play", "过关8串70");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("549")) {
			object.put("play", "过关6串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("550")) {
			object.put("play", "过关6串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("551")) {
			object.put("play", "过关7串21");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("552")) {
			object.put("play", "过关8串56");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("553")) {
			object.put("play", "过关7串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("554")) {
			object.put("play", "过关7串8");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("555")) {
			object.put("play", "过关8串28");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("556")) {
			object.put("play", "过关8串8");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("557")) {
			object.put("play", "过关8串9");
			object.put("valueType", "1");
		} else {
			object.put("play", "未知");
			object.put("valueType", "1");
		}
		return object;
	}
	/**
	 * 判断是否胆码
	 * @param orderInfo //602@20120815|3|022|10^$20120815|3|023|1^20120815|3|024|1^
	 * @param info
	 * @return
	 */
	public JSONObject getDanMaString(String orderInfo, String info) {
		JSONObject returnJson = new JSONObject();
		String result = "";
		String isDanMa = "false";
		List<String> danMaList = new ArrayList<String>(); //胆码列表
		if (orderInfo!=null&&orderInfo.indexOf("$")>-1) { //设胆
			String[] split = orderInfo.split("@");
			String[] split2 = split[1].split("\\$");
			String[] split3 = split2[0].split("\\^");
			for (String string : split3) {
				danMaList.add(string.substring(0, string.lastIndexOf("|")));
			}
		}
		if (danMaList!=null&&danMaList.size()>0) {
			for (String string : danMaList) {
				if (string.equals(info)) {
					result = "(胆)";
					isDanMa = "true";
					break;
				}
			}
		}
		returnJson.put("result", result);
		returnJson.put("isDanMa", isDanMa);
		return returnJson;
	}
	/**
	 * 根据比分获得竟彩比赛结果
	 * @param score
	 * @return
	 */
	public String getJingCaiResultByScore(String score, boolean isAddZhu) {
		String result = "未开"; //比赛结果
		if (score!=null&&score.indexOf(":")>-1) {
			String[] scores = score.split(":");
			Integer zhuScore = Integer.parseInt(scores[0]);
			Integer keScore = Integer.parseInt(scores[1]);
			if (zhuScore > keScore) {
				//result = "胜";
				result = isAddZhu ? "主胜":"胜";
			} else if (zhuScore == keScore) {
				result = "平";
			} else {
				//result = "负";
				result = isAddZhu ? "主负":"负";
			}
		}
		return result;
	}
	/**
	 * 根据weekId获得星期(供竞彩星期转换使用)
	 * 
	 * @param weekId
	 * @return
	 */
	public String getWeekByWeekId(String weekId) {
		String week = "";
		if (weekId != null && weekId.trim().equals("1")) {
			week = "星期一";
		} else if (weekId != null && weekId.trim().equals("2")) {
			week = "星期二";
		} else if (weekId != null && weekId.trim().equals("3")) {
			week = "星期三";
		} else if (weekId != null && weekId.trim().equals("4")) {
			week = "星期四";
		} else if (weekId != null && weekId.trim().equals("5")) {
			week = "星期五";
		} else if (weekId != null && weekId.trim().equals("6")) {
			week = "星期六";
		} else if (weekId != null && weekId.trim().equals("7")) {
			week = "星期日";
		}
		return week;
	}
	/**
	 * 根据注码获取投注内容(竞彩足球胜负平)
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public String getBetContentForJ00001(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer(""); //投注内容
		if (betCode != null && betCode.length() == 1) {
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			sBuffer.append(getSPFBySingleCode(betCode));
			if (peiLv!=null&&!peiLv.equals("")) {
				sBuffer.append("("+peiLv+")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
			sBuffer.append(" ");
			
			String code3 = betCode.substring(2, 3);
			String peiLv3 = getPeiLvByCode(id, peiLvString, code3);
			sBuffer.append(getSPFBySingleCode(code3));
			if (peiLv3!=null&&!peiLv3.equals("")) {
				sBuffer.append("("+peiLv3+")");
			}
		}
		return sBuffer.toString();
	}
	/**
	 * 获取竞彩足球比分投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public String getBetContentForJ00002(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		int l = betCode.length();
		int h = l/2;
		int n = 0;
		for (int i = 0; i < h; i++) {
			String code = betCode.substring(n, n+2);
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			n = n + 2;
			if (i==h-1) {
				if (code.equals("90")) {
					resultBuffer.append("胜其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
				} else if (code.equals("99")) {
					resultBuffer.append("平其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
				} else if (code.equals("09")) {
					resultBuffer.append("负其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
				} else {
					resultBuffer.append(code.substring(0, 1)+":"+code.substring(1));
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
				}
			} else {
				if (code.equals("90")) {
					resultBuffer.append("胜其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
					resultBuffer.append(" ");
				} else if (code.equals("99")) {
					resultBuffer.append("平其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
					resultBuffer.append(" ");
				} else if (code.equals("09")) {
					resultBuffer.append("负其他");
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
					resultBuffer.append(" ");
				} else {
					resultBuffer.append(code.substring(0, 1)+":"+code.substring(1));
					if (peiLv!=null&&!peiLv.equals("")) {
						resultBuffer.append("("+peiLv+")");
					}
					resultBuffer.append(" ");
				}
			}
		}
		return resultBuffer.toString();
	}
	
	/**
	 * 获取竞彩足球总进球投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public String getBetContentForJ00003(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		for (int i = 0; i < betCode.length(); i++) {
			String code = betCode.substring(i, i+1);
			String peiLv = getPeiLvByCode(id, peiLvString, code);
			if (i==betCode.length()-1) {
				resultBuffer.append(code);
				if (peiLv!=null&&!peiLv.equals("")) {
					resultBuffer.append("("+peiLv+")");
				}
			} else {
				resultBuffer.append(code);
				if (peiLv!=null&&!peiLv.equals("")) {
					resultBuffer.append("("+peiLv+")");
				}
				resultBuffer.append(" ");
			}
		}
		return resultBuffer.toString();
	}
	
	/**
	 * 获取竞彩足球半场胜平负投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public String getBetContentForJ00004(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		int l = betCode.length();
		int h = l/2;
		int n = 0;
		for (int i = 0; i < h; i++) {
			String code = betCode.substring(n, n+2);
			String peiLv = getPeiLvByCode(id, peiLvString, code);
			n = n + 2;
			if (i==h-1) {
				resultBuffer.append(parseCodeForJ00004(code));
				if (peiLv!=null&&!peiLv.equals("")) {
					resultBuffer.append("("+peiLv+")");
				}
			} else {
				resultBuffer.append(parseCodeForJ00004(code));
				if (peiLv!=null&&!peiLv.equals("")) {
					resultBuffer.append("("+peiLv+")");
				}
				resultBuffer.append(" ");
			}
		}
		return resultBuffer.toString();
	}
	
	/**
	 * 解析竞彩足球半场胜平负的注码
	 * @param code
	 * @return
	 */
	public String parseCodeForJ00004(String code) {
		String result = "";
		if (code != null && code.equals("00")) { 
			result = "负负";
		} else if (code != null && code.equals("01")) { 
			result = "负平";
		} else if (code != null && code.equals("03")) { 
			result = "负胜";
		} else if (code != null && code.equals("11")) { 
			result = "平平";
		} else if (code != null && code.equals("10")) { 
			result = "平负";
		} else if (code != null && code.equals("13")) { 
			result = "平胜";
		} else if (code != null && code.equals("33")) { 
			result = "胜胜";
		} else if (code != null && code.equals("30")) { 
			result = "胜负";
		} else if (code != null && code.equals("31")) { 
			result = "胜平";
		}
		return result;
	}
	/**
	 * 根据注码获取投注内容(竞彩篮球胜负)
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public String getBetContentForJ00005(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer(""); //投注内容
		if (betCode != null && betCode.length() == 1) {
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			sBuffer.append(getSPFBySingleCode(betCode));
			if (peiLv!=null&&!peiLv.equals("")) {
				sBuffer.append("("+peiLv+")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
			sBuffer.append(" ");
			
			String code3 = betCode.substring(2, 3);
			String peiLv3 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCode(code3));
			if (peiLv3!=null&&!peiLv3.equals("")) {
				sBuffer.append("("+peiLv3+")");
			}
		}
		return sBuffer.toString();
	}
	
	/**
	 * 根据注码获取竞彩篮球让分胜负投注内容
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public JSONObject getBetContentForJ00006(String id, String peiLvString, String betCode) {
		JSONObject resultObject = new JSONObject();
		
		StringBuffer sBuffer = new StringBuffer(""); //投注内容
		String letPoint = ""; //让分
		if (betCode != null && betCode.length() == 1) {
			JSONObject peiLvByObject = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, betCode);
			String peiLv = peiLvByObject.getString("peiLv");
			letPoint = peiLvByObject.getString("letPoint");
			sBuffer.append(getSPFBySingleCode(betCode));
			if (peiLv!=null&&!peiLv.equals("")) {
				sBuffer.append("("+peiLv+")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			JSONObject peiLvByObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = peiLvByObject1.getString("peiLv");
			letPoint = peiLvByObject1.getString("letPoint");
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			JSONObject peiLvByObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = peiLvByObject2.getString("peiLv");
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			JSONObject peiLvByObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = peiLvByObject1.getString("peiLv");
			letPoint = peiLvByObject1.getString("letPoint");
			sBuffer.append(getSPFBySingleCode(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			JSONObject peiLvByObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = peiLvByObject2.getString("peiLv");
			sBuffer.append(getSPFBySingleCode(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
			sBuffer.append(" ");
			
			String code3 = betCode.substring(2, 3);
			JSONObject peiLvByObject3 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv3 = peiLvByObject3.getString("peiLv");
			sBuffer.append(getSPFBySingleCode(code3));
			if (peiLv3!=null&&!peiLv3.equals("")) {
				sBuffer.append("("+peiLv3+")");
			}
		}
		
		resultObject.put("betContent", sBuffer.toString());
		resultObject.put("letPoint", letPoint);
		return resultObject;
	}
	/**
	 * 获取竞彩篮球胜分差的投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public String getBetContentForJ00007(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer("");
		if (betCode != null) {
			int len = betCode.length() / 2;
			for (int i = 0; i < len; i++) {
				String code = betCode.substring(2 * i, 2 * i + 2);
				String peiLv = getPeiLvByCode(id, peiLvString, code);
				if (i == len - 1) {
					sBuffer.append(getScoreByCodeForJ00007(code));
					if (peiLv!=null&&!peiLv.equals("")) {
						sBuffer.append("("+peiLv+")");
					}
				} else {
					sBuffer.append(getScoreByCodeForJ00007(code));
					if (peiLv!=null&&!peiLv.equals("")) {
						sBuffer.append("("+peiLv+")");
					}
					sBuffer.append(" ");
				}
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 竞彩篮球胜分差：根据注码得到分差
	 * 
	 * @param code
	 * @return
	 */
	public static String getScoreByCodeForJ00007(String code) {
		String result = "";
		if (code != null) {
			if (code.equals("01")) {
				result = "主胜1-5分";
			} else if (code.equals("02")) {
				result = "主胜6-10分";
			} else if (code.equals("03")) {
				result = "主胜11-15分";
			} else if (code.equals("04")) {
				result = "主胜16-20分";
			} else if (code.equals("05")) {
				result = "主胜21-25分";
			} else if (code.equals("06")) {
				result = "主胜26+分";
			} else if (code.equals("11")) {
				result = "客胜1-5分";
			} else if (code.equals("12")) {
				result = "客胜6-10分";
			} else if (code.equals("13")) {
				result = "客胜11-15分";
			} else if (code.equals("14")) {
				result = "客胜16-20分";
			} else if (code.equals("15")) {
				result = "客胜21-25分";
			} else if (code.equals("16")) {
				result = "客胜26+分";
			}
		}
		return result;
	}

	/**
	 * 获取竞彩篮球大小分的投注内容
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public JSONObject getBetContentForJ00008(String id, String peiLvString, String betCode) {
		JSONObject resultObject = new JSONObject();
		
		StringBuffer sBuffer = new StringBuffer(""); //投注内容
		String basePoint = ""; //基本分
		if (betCode != null && betCode.length() == 1) {
			JSONObject resultObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, betCode);
			String peiLv = resultObject1.getString("peiLv");
			basePoint = resultObject1.getString("basePoint");
			sBuffer.append(getDXBySingleCodeForJC(betCode));
			if (peiLv!=null&&!peiLv.equals("")) {
				sBuffer.append("("+peiLv+")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			JSONObject resultObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = resultObject1.getString("peiLv");
			basePoint = resultObject1.getString("basePoint");
			sBuffer.append(getDXBySingleCodeForJC(code1));
			if (peiLv1!=null&&!peiLv1.equals("")) {
				sBuffer.append("("+peiLv1+")");
			}
			sBuffer.append(" ");
			
			String code2 = betCode.substring(1, 2);
			JSONObject resultObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = resultObject2.getString("peiLv");
			sBuffer.append(getDXBySingleCodeForJC(code2));
			if (peiLv2!=null&&!peiLv2.equals("")) {
				sBuffer.append("("+peiLv2+")");
			}
		}
		resultObject.put("betContent", sBuffer.toString());
		resultObject.put("basePoint", basePoint);
		return resultObject;
	}
	/**
	 * 根据注码获得赔率
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static String getPeiLvByCode(String id, String peiLvString, String code) {
		//20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^
		//20130328*4*003*|J00001|1:3.1|^20130328*4*004*|J00002|90:50.0|^
		//
		String peiLv = ""; //赔率
		System.out.println("peiLvString=:"+peiLvString);
		if (peiLvString!=null&&!"".equals(peiLvString)) {
			String[] split = peiLvString.split("\\^");
			for (String string : split) {
				String[] split2 = string.split("\\|");
				String s = "";
				if(peiLvString.indexOf("J")>-1) s = split2[0]+"|"+split2[1];
				else s = split2[0];
				if (s.equals(id)) {
					for (int i = 1; i < split2.length; i++) {
						System.out.println("===pl=:"+peiLv);
						String[] split3 = split2[i].split("\\:");
						System.out.println("===pl=split3:"+split3);
						System.out.println("===pl=split3[0]:"+split3[0]);
						System.out.println("===pl=code:"+code);
						String sp3Code = "";
						if(split3[0].indexOf("(")>-1){
							sp3Code = split3[0].substring(0, split3[0].indexOf("("));
							if (sp3Code.equals(code)) {
								peiLv = split3[1]+"|"+split3[0].substring(split3[0].indexOf("(")+1, split3[0].indexOf(")"));
								System.out.println("===pl:"+peiLv);
								break;
							}
						}else{
							sp3Code = split3[0];
							if (sp3Code.equals(code)) {
								peiLv = split3[1];
								System.out.println("===pl:"+peiLv);
								break;
							}
						}
						
					}
				}
			}
		}
		System.out.println("peiLv=:"+peiLv);
		return peiLv;
	}
	/**
	 * 根据单个注码获取竞彩胜平负
	 * 
	 * @param code
	 * @return
	 */
	public static String getSPFBySingleCode(String code) {
		String result = "";
		if (code != null && code.equals("0")) { // 负
			result = "主负";
		} else if (code != null && code.equals("1")) { // 平
			result = "平";
		} else if (code != null && code.equals("3")) { // 胜
			result = "主胜";
		}
		return result;
	}
	/**
	 * 根据注码获得赔率(竞彩篮球让分胜负,竞彩篮球大小分)
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static JSONObject getPeiLvByCodeForJ00006OrJ00008(String id, String peiLvString, String code) {
		//20120529*2*301*|2(202.5):1.750|^20120529*2*302*|2(153.5):1.700|1(153.5):1.700|^20120530*3*303*|2(180.5):1.750|^
		JSONObject resultObject = new JSONObject();
		//20130423*2*301*|J00005|0:6.30|^20130423*2*302*|J00008|1(186.5):1.75|^20130423*2*303*|J00007|16:120.0|
		String peiLv = ""; //赔率
		String letPoint = ""; //让分胜负
		String basePoint = ""; //基本分
		if (peiLvString!=null&&!"".equals(peiLvString)) {
			String[] split = peiLvString.split("\\^");
			for (String string : split) {
				String[] split2 = string.split("\\|");
				String s = "";
				int m=0;
				if(peiLvString.indexOf("J")>-1){
					s = split2[0]+"|"+split2[1];
					m=2;
				}
				else{
					s = split2[0];
					m=1;
				}
				if (s.equals(id)) {
					for (int i = m; i < split2.length; i++) {
						String[] split3 = split2[i].split(":");
						if (split3[0].substring(0, split3[0].indexOf("(")).equals(code)) {
							peiLv = split3[1];
							Pattern pattern = Pattern.compile(".*\\((.*)\\).*");
							Matcher matcher = pattern.matcher(split3[0]);
							if(matcher.find()) {
								letPoint = matcher.group(1);
								basePoint = matcher.group(1);
							}
							break;
						}
					}
				}
			}
		}
		
		resultObject.put("peiLv", peiLv);
		resultObject.put("letPoint", letPoint);
		resultObject.put("basePoint", basePoint);
		return resultObject;
	}
	/**
	 * 竞彩篮球大小分:根据单个注码获得大小分
	 * 
	 * @param code
	 * @return
	 */
	public static String getDXBySingleCodeForJC(String code) {
		String result = "";
		if (code != null && code.equals("1")) {
			result = "大";
		} else if (code != null && code.equals("2")) {
			result = "小";
		}
		return result;
	}
	/**
	 * 获得球队名称
	 * @param teamString
	 * @return
	 */
	public static JSONObject getTeamName(String teamString) {
		JSONObject object = new JSONObject();
		String homeTeam = ""; //主队
		String guestTeam = ""; //客队
		if (teamString!=null&&!teamString.trim().equals("null")&&teamString.indexOf(":")>-1) {
			String[] split = teamString.split(":");
			homeTeam = split[0];
			guestTeam = split[1];
		}
		object.put("homeTeam", homeTeam);
		object.put("guestTeam", guestTeam);
		return object;
	}
	
	/**
	 * 根据结果获得比分
	 * @param result
	 * @return
	 */
	public static JSONObject getScoreByResult(String result) {
		JSONObject object = new JSONObject();
		String homeScore = ""; //主队比分
		String guestScore = ""; //客队比分
		if (result!=null&&!result.equals("null")) {
			if (result!=null&&result.indexOf(":")>-1) {
				String[] scores = result.split(":");
				homeScore = scores[0];
				guestScore = scores[1];
			}
		}
		object.put("homeScore", homeScore);
		object.put("guestScore", guestScore);
		return object;
	}
	/**
	 * 根据列表获得玩法
	 * @param list
	 * @return
	 */
	public String getJingCaiPlayByList(List<String> list) {
		StringBuilder builder = new StringBuilder();
		if (list!=null&&list.size()>0) {
			for (String wanfa : list) {
				JSONObject wanfaObject = getJingCaiPlay(wanfa);
				String play = wanfaObject.getString("play");
				builder.append(play).append(",");
			}
		}
		String result = "";
		if(builder.toString().endsWith(",")) result = builder.toString().substring(0, builder.toString().length()-1);
		else result = builder.toString();
		return result;
	}
	/**
	 * 获得解析注码
	 * @param code
	 * @param peiLv
	 * @param isHtml
	 * @return
	 */
	public String getParseCodeWithPeiLv(String parseCode, List<String> peiLvs) {
		StringBuilder builder = new StringBuilder();
		builder.append(parseCode);
		//将多个赔率连起来
//		String peiLv = StringUtil.joinStringArrayWithCharacter(peiLvs, ",");
		String peiLv = LotteryUtil.getDouHaoStringFromStringList(peiLvs);
		System.out.println("peiLv===="+peiLv);
		if (peiLv!=null&&!"".equals(peiLv)) {
			builder.append("("+peiLv+")");
		}
		return builder.toString();
	}
	
	/**
	 * 将注码和赔率放在Map里
	 * @param teamMap
	 * @param code
	 * @param peiLv
	 */
	@SuppressWarnings("unchecked")
	public void putCodeToCodeMap(String lotNo, Map<String, Object> teamMap, String code, String peiLv) {
		Object object = teamMap.get("code");
		Map<String, Map<String, List<String>>> lotNoMap = null;
		Map<String, List<String>> codeMap = null;
		List<String> peiLvs = null;
		if (object==null) {
			lotNoMap = new HashMap<String, Map<String, List<String>>>();
			codeMap = new HashMap<String, List<String>>();
			peiLvs = new ArrayList<String>();
		} else {
			lotNoMap = (Map<String, Map<String, List<String>>>)object;
			codeMap = lotNoMap.get(lotNo);
			if (codeMap==null) {
				codeMap = new HashMap<String, List<String>>();
				peiLvs = new ArrayList<String>();
			} else {
				peiLvs = codeMap.get(code);
				peiLvs = (peiLvs==null ? new ArrayList<String>() : peiLvs);
			}
		}
		if (peiLv!=null&&!peiLvs.contains(peiLv)) {
			peiLvs.add(peiLv);
		}
		System.out.println("codeMap1:"+code+",2:"+peiLvs);
		codeMap.put(code, peiLvs);
		lotNoMap.put(lotNo, codeMap);
		teamMap.put("code", lotNoMap);
	} 
	/**
	 * 根据单个注码获取竞彩胜平负(带"主")
	 * @param code
	 * @return
	 */
	public String getSpfHomeBySingleCode(String code, boolean addRangFen) {
		String result = getSpfBySingleCode(code);
		if (result!=null&&!"".equals(result)) {
			if (!result.equals("平")) {
				result = "主" + result;
			}
			if (addRangFen) {
				result = "让分" + result;
			}
		}
		return result;
	}
	
	/**
	 * 根据单个注码获取竞彩胜平负
	 * @param code
	 * @return
	 */
	public String getSpfBySingleCode(String code) {
		String result = "";
		if (code!=null) {
			SpfEnum[] values = SpfEnum.values();
			for (SpfEnum spfEnum : values) {
				if (code.equals(spfEnum.value())) {
					result = spfEnum.memo();
					break;
				}
			}
		}
		return result;
	}
	/**
	 * 获得胜平负的开奖号码和进球数
	 * @param homeScore
	 * @param guestScore
	 * @return
	 */
	public JSONObject getSpfWinByScore(String homeScore, String guestScore) {
		JSONObject resultObject = new JSONObject();
		String win = ""; //开奖号码
		String goals = ""; //总进球数
		if (homeScore!=null&&!"".equals(homeScore)&&!homeScore.equals("null")
				&&guestScore!=null&&!"".equals(guestScore)&&!guestScore.equals("null")) {
			BigDecimal homeScoreB = new BigDecimal(homeScore);
			BigDecimal guestScoreB = new BigDecimal(guestScore);
			if (homeScoreB.compareTo(guestScoreB)==1) { //胜
				win = SpfEnum.win.value();
			} else if (homeScoreB.compareTo(guestScoreB)==0) { //平
				win = SpfEnum.standoff.value();
			} else if (homeScoreB.compareTo(guestScoreB)==-1) { //负
				win = SpfEnum.loss.value();
			}
			goals = homeScoreB.add(guestScoreB).toString();
		}
		resultObject.put("win", win);
		resultObject.put("goals", goals);
		return resultObject;
	}
	/**
	 * 获得大小分的赛果
	 * @param homeScore
	 * @param guestScore
	 * @param totalScore
	 * @return
	 */
	public String getResultDx(String homeScore, String guestScore, String totalScore) {
		String win = getDxfWinByScore(homeScore, guestScore, totalScore);
		String result = Dxf.parseSingleCode(win);
		return result;
	}
	
	/**
	 * 获得大小分的开奖号码
	 * @param homeScore
	 * @param guestScore
	 * @param totalScore
	 * @return
	 */
	public String getDxfWinByScore(String homeScore, String guestScore, String totalScore) {
		String win = "";
		if (homeScore!=null&&!"".equals(homeScore)&&!homeScore.equals("null")&&guestScore!=null&&!"".equals(guestScore)&&!guestScore.equals("null")
				&&totalScore!=null&&!"".equals(totalScore)&&!totalScore.equals("null")) {
			BigDecimal homeScoreB = new BigDecimal(homeScore);
			BigDecimal guestScoreB = new BigDecimal(guestScore);
			BigDecimal totalScoreB = new BigDecimal(totalScore);
			BigDecimal total = homeScoreB.add(guestScoreB);
			if (total.compareTo(totalScoreB)==1) { //大
				win = DxfEnum.big.value();
			} else if (total.compareTo(totalScoreB)==-1) { //小
				win = DxfEnum.small.value();
			}
		}
		return win;
	}
	/**
	 * 获得让分胜负的赛果
	 * @param homeScore
	 * @param guestScore
	 * @param letScore
	 * @return
	 */
	public String getResultRfsf(String homeScore, String guestScore, String letScore) {
		String win = getRfsfWinByScore(homeScore, guestScore, letScore);
		String result = getSpfBySingleCode(win); //赛果
		return result;
	}
	
	/**
	 * 获得让分胜负的开奖号码
	 * @param homeScore
	 * @param guestScore
	 * @param letScore
	 * @return
	 */
	public String getRfsfWinByScore(String homeScore, String guestScore, String letScore) {
		String win = "";
		if (homeScore!=null&&!"".equals(homeScore)&&!homeScore.equals("null")&&guestScore!=null&&!"".equals(guestScore)&&!guestScore.equals("null")
				&&letScore!=null&&!"".equals(letScore)&&!letScore.equals("null")) {
			BigDecimal homeScoreB = new BigDecimal(homeScore);
			BigDecimal guestScoreB = new BigDecimal(guestScore);
			if (letScore.startsWith("+")) {
				BigDecimal letScoreB = new BigDecimal(letScore.replaceFirst("\\+", ""));
				homeScoreB = homeScoreB.add(letScoreB);
			}
			if (letScore.startsWith("-")) {
				BigDecimal letScoreB = new BigDecimal(letScore.replaceFirst("\\-", ""));
				homeScoreB = homeScoreB.subtract(letScoreB);
			}
			if (homeScoreB.compareTo(guestScoreB)==1) { //胜
				win = SpfEnum.win.value();
			} else if (homeScoreB.compareTo(guestScoreB)==0) { //平
				win = SpfEnum.standoff.value();
			} else if (homeScoreB.compareTo(guestScoreB)==-1) { //负
				win = SpfEnum.loss.value();
			}
		}
		return win;
	}
	public static void main(String[] args) {
		String s = "123(45)67";
		System.out.println(s.substring(s.indexOf("(")+1, s.indexOf(")")));
	}
}
