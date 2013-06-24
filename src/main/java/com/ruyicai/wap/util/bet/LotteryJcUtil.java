package com.ruyicai.wap.util.bet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.HttpUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
import com.ruyicai.wap.vo.JcMatchAgainst;
import com.ruyicai.wap.vo.MatchHistory;
import com.ruyicai.wap.vo.MatchLetGoal;
import com.ruyicai.wap.vo.MatchMessage;
import com.ruyicai.wap.vo.MatchOuPei;
import com.ruyicai.wap.vo.MatchRanking;
import com.ruyicai.wap.vo.MatchTotalScore;
import com.ruyicai.wap.vo.MatchYaPan;

@Component
public class LotteryJcUtil {
	private Logger logger = Logger.getLogger(LotteryFootballUtil.class);
	@Value("${lottery}")
	String lottery;
	@Autowired
	CommonUtil commonUtil;
	@Value("${dataanalysis}")
	String dataanalysis;
	/**
	 * 查询竞彩对阵
	 * @param type查询类别 (0 篮彩 1足彩 )
	 * @return
	 */
	public String getJcMatchsAgainst(String type){
		try{
			String url = lottery+"select/getjingcaiduizhen";
			String parameter = "type=" + type;
			String result = commonUtil.setUrlByPOST(url, parameter);
//			logger.info("LotteryJcUtil/getJcMatchsAgainst查询竞彩对阵返回result:"+result);

			return result;
		}catch (Exception e) {
			logger.error("LotteryJcUtil/getJcMatchsAgainst查询竞彩对阵异常:"+e.getMessage());
			return "";
		}
	}
	public List<JcMatchAgainst> selectJcMatchsAgainst(String type,String valueType,String wanFa){
		List<JcMatchAgainst> jcMatchAgainsts = null;
		try{
			jcMatchAgainsts = new ArrayList<JcMatchAgainst>();
			String result = getJcMatchsAgainst(type);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			JSONArray jsonArray = resultJsonObject.getJSONArray("value");
			outer: for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JcMatchAgainst jcMatchAgainst = new JcMatchAgainst();
				jcMatchAgainst.setState(jsonObject.getString("state"));
				jcMatchAgainst.setType(jsonObject.getString("type"));
				jcMatchAgainst.setTime(jsonObject.getString("time"));
				jcMatchAgainst.setDay(jsonObject.getString("day"));
				jcMatchAgainst.setShortName(jsonObject.getString("shortname"));
				jcMatchAgainst.setEndTime(jsonObject.getString("endtime"));
				jcMatchAgainst.setTeam(jsonObject.getString("team"));
				jcMatchAgainst.setWeekId(jsonObject.getString("weekid"));
				jcMatchAgainst.setTeamId(jsonObject.getString("teamid"));
				jcMatchAgainst.setLeague(jsonObject.getString("league"));
				jcMatchAgainst.setSaleFlag(jsonObject.getString("saleflag"));
				String unsupport = jsonObject.getString("unsupport");
				jcMatchAgainst.setUnsupport(unsupport);
				jcMatchAgainst.setAudit(jsonObject.getString("audit"));
				jcMatchAgainst.setCtstate(jsonObject.getString("ctstate"));
				if(jsonObject.getString("time")!=null&&!"null".equals(jsonObject.getString("time"))){
					String timeView = commonUtil.getDataFormat(jsonObject.getLong("time"), "");
					jcMatchAgainst.setTimeView(timeView);
				}
				jcMatchAgainst.setDayView(getDayView(jsonObject.getString("day")));
				if(jsonObject.getString("endtime")!=null&&!"null".equals(jsonObject.getString("endtime"))){
					String endTimeView = commonUtil.getDataFormat(jsonObject.getLong("endtime"), "");
					jcMatchAgainst.setEndTimeView(endTimeView);
				}
				if(jsonObject.getString("team").indexOf(":")>-1){
					String teams[] = jsonObject.getString("team").split("\\:");
					jcMatchAgainst.setHomeTeam(teams[0]);
					jcMatchAgainst.setGuestTeam(teams[1]);
				}
				jcMatchAgainst.setWeek(getWeek(jsonObject.getString("weekid")));
				if(!"".equals(unsupport)&&!"null".equals(unsupport)&&unsupport!=null){
					if(unsupport.indexOf(",")>-1){
						String su[] = unsupport.split("\\,");
						for(int m=0;m<su.length;m++){
								String str[]= su[m].split("\\_");
								String ln= str[0];
								String s = str[1];
								if(getLotNoByWanFa(wanFa).equals(ln)&&valueType.equals(s)){
									continue outer ;
								}

							}
					}else{
						String str[]= unsupport.split("\\_");
						String ln= str[0];
						String s = str[1];
						if(getLotNoByWanFa(wanFa).equals(ln)&&valueType.equals(s)){
							continue outer ;
						}
					}
				}
				jcMatchAgainsts.add(jcMatchAgainst);
			}
			return jcMatchAgainsts;
		}catch (Exception e) {
			logger.error("LotteryJcUtil/selectJcMatchsAgainst查询竞彩对阵异常:"+e.getMessage());
			return jcMatchAgainsts;
		}
	}
	/**
	 * 按日期查询竞彩对阵
	 * @param type查询类别 (0 篮彩 1足彩 )
	 * @return
	 */
	public String getJcMatchsAgainstByDay(String type){
		try{
			String url = lottery+"select/getjingcaiduizhenlimit";
			String parameter = "type=" + type;
			String result = commonUtil.setUrlByPOST(url, parameter);
//			logger.info("LotteryJcUtil/getJcMatchsAgainstByDay按日期查询竞彩对阵返回result:"+result);

			return result;
		}catch (Exception e) {
			logger.error("LotteryJcUtil/getJcMatchsAgainstByDay按日期查询竞彩对阵异常:"+e.getMessage());
			return "";
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectJcMatchsAgainstByDay(String type,String valueType,String wanFa){
		List<Map<String, Object>> maps = null;
		Map<String, Object> map = null;
		List<JcMatchAgainst> jcMatchAgainsts = null;
		try{
			maps = new ArrayList<Map<String,Object>>();
			String result = getJcMatchsAgainstByDay(type);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			JSONObject valueJsonObject = resultJsonObject.getJSONObject("value");
			Iterator<String> iterator = valueJsonObject.keys();
			while(iterator.hasNext()){
				jcMatchAgainsts = new ArrayList<JcMatchAgainst>();
				map = new HashMap<String, Object>();
				JSONArray jsonArray = valueJsonObject.getJSONArray(iterator.next());
				outer: for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JcMatchAgainst jcMatchAgainst = new JcMatchAgainst();
					jcMatchAgainst.setState(jsonObject.getString("state"));
					jcMatchAgainst.setType(jsonObject.getString("type"));
					jcMatchAgainst.setTime(jsonObject.getString("time"));
					jcMatchAgainst.setDay(jsonObject.getString("day"));
					jcMatchAgainst.setShortName(jsonObject.getString("shortname"));
					jcMatchAgainst.setEndTime(jsonObject.getString("endtime"));
					jcMatchAgainst.setTeam(jsonObject.getString("team"));
					jcMatchAgainst.setWeekId(jsonObject.getString("weekid"));
					jcMatchAgainst.setTeamId(jsonObject.getString("teamid"));
					jcMatchAgainst.setLeague(jsonObject.getString("league"));
					jcMatchAgainst.setSaleFlag(jsonObject.getString("saleflag"));
					String unsupport = jsonObject.getString("unsupport");
					jcMatchAgainst.setUnsupport(unsupport);
					jcMatchAgainst.setAudit(jsonObject.getString("audit"));
					jcMatchAgainst.setCtstate(jsonObject.getString("ctstate"));
					if(jsonObject.getString("time")!=null&&!"null".equals(jsonObject.getString("time"))){
						String timeView = commonUtil.getDataFormat(jsonObject.getLong("time"), "HH:mm");
						jcMatchAgainst.setTimeView(timeView);
					}
					jcMatchAgainst.setDayView(getDayView(jsonObject.getString("day")));
					if(jsonObject.getString("endtime")!=null&&!"null".equals(jsonObject.getString("endtime"))){
						String endTimeView = commonUtil.getDataFormat(jsonObject.getLong("endtime"), "HH:mm");
						jcMatchAgainst.setEndTimeView(endTimeView);
					}
					if(jsonObject.getString("team").indexOf(":")>-1){
						String teams[] = jsonObject.getString("team").split("\\:");
						jcMatchAgainst.setHomeTeam(teams[0]);
						jcMatchAgainst.setGuestTeam(teams[1]);
					}
					jcMatchAgainst.setWeek(getWeek(jsonObject.getString("weekid")));
					if(!"".equals(unsupport)&&!"null".equals(unsupport)&&unsupport!=null){
						if(unsupport.indexOf(",")>-1){
							String su[] = unsupport.split("\\,");
							for(int m=0;m<su.length;m++){
									String str[]= su[m].split("\\_");
									String ln= str[0];
									String s = str[1];
									if(getLotNoByWanFa(wanFa).equals(ln)&&valueType.equals(s)){
										continue outer ;
									}

								}
						}else{
							String str[]= unsupport.split("\\_");
							String ln= str[0];
							String s = str[1];
							if(getLotNoByWanFa(wanFa).equals(ln)&&valueType.equals(s)){
								continue outer ;
							}
						}
					}
					
					JSONObject jcMatchsPeiLv = selectJcMatchsPeiLv(type, valueType);
					if(!"".equals(jcMatchsPeiLv)&&!"null".equals(jcMatchsPeiLv)&&jcMatchsPeiLv!=null){
						String peiLvKey = jcMatchAgainst.getDay()+"_"+jcMatchAgainst.getWeekId()+"_"+jcMatchAgainst.getTeamId();
						JSONObject peiLvJsonObject = jcMatchsPeiLv.getJSONObject(peiLvKey);
						jcMatchAgainst.setPeiLv(peiLvJsonObject);
					}

					jcMatchAgainsts.add(jcMatchAgainst);
				}
				map.put("jcMatchAgainsts", jcMatchAgainsts);
				map.put("totalMatch", jcMatchAgainsts.size()+"场比赛");
				map.put("weekTop", jcMatchAgainsts.get(0).getWeek());
				map.put("dayTop", jcMatchAgainsts.get(0).getDayView());
				maps.add(map);
			}
			return maps;
		}catch (Exception e) {
			logger.error("LotteryJcUtil/selectJcMatchsAgainst查询竞彩对阵异常:"+e.getMessage());
			return maps;
		}
	}
	/**
	 * 查询竞彩赔率
	 * @param type查询类别 (0 篮彩 1足彩 )
	 * @param valueType单关0/过关1赔率
	 * @return
	 */
	public String getJcMatchsPeiLv(String type,String valueType){
		try{
			String url = lottery+"select/findjincaipeilu";
			String parameter = "type=" + type+ "&valueType="+ valueType;
			String result = commonUtil.setUrlByPOST(url, parameter);
			logger.info("LotteryJcUtil/getJcMatchsPeiLv查询竞彩赔率返回result:"+result);

			return result;
		}catch (Exception e) {
			logger.error("LotteryJcUtil/getJcMatchsPeiLv查询竞彩赔率异常:"+e.getMessage());
			return "";
		}
	}
	public JSONObject selectJcMatchsPeiLv(String type,String valueType){
		JSONObject jcMatchsPeiLv = null;
		try{
			String result = getJcMatchsPeiLv(type, valueType);
			JSONObject resultJsonObject = JSONObject.fromObject(result);
			String errorCode = resultJsonObject.getString("errorCode");
			if("0".equals(errorCode)){
				String value = resultJsonObject.getString("value");
				if("0".equals(type)){//篮球
					jcMatchsPeiLv = LqpeilvDOM(value);
				}else if("1".equals(type)){//足球
					jcMatchsPeiLv = peilvDOM(value);
				}
			}
			logger.info("LotteryJcUtil/selectJcMatchsPeiLv查询竞彩赔率errorCode:"+errorCode+",jcMatchsPeiLv:"+jcMatchsPeiLv);
			return jcMatchsPeiLv.getJSONObject("body").getJSONObject("matchList");
		}catch (Exception e) {
			logger.error("LotteryJcUtil/selectJcMatchsPeiLv查询竞彩赔率异常:"+e.getMessage());
			return jcMatchsPeiLv;
		}
	}
	/**
	 * 竞彩足球赔率解析
	 * 
	 * @return <vs> ,<score>,<goal>,<half> 对应足彩胜负 比分，总进 比分，总进球，半全场胜负
	 *         此方法应用DOM4j来解析赔率的XML字符串
	 * @param obj
	 *            XML字符串
	 */
	@SuppressWarnings("rawtypes")
	public JSONObject peilvDOM(String obj) {
		JSONObject jsonAll = null;
		try {
			// 后台返回的是XML字符串，采用dom4j 解析
			Document doc = DocumentHelper.parseText(obj);
			// 获取根节点的名称
			Element rootElt = doc.getRootElement();
			// 创建MAP储存list. 遍历缓存数据
			jsonAll = new JSONObject();
			// 获取message 子节点head属性数组
			Iterator Ihead = rootElt.elementIterator("head");
			while (Ihead.hasNext()) {
				// 遍历head 获取数据并且储存
				JSONObject json = new JSONObject();
				Element head = (Element) Ihead.next();
				String messageId = head.elementTextTrim("messageId");
				String result = head.elementTextTrim("result");
				String md = head.elementTextTrim("md");
				json.put("messageId", messageId);
				json.put("result", result);
				json.put("md", md);
				jsonAll.put("head", json);
			}
			// 获取message子节点body 数据
			Iterator Ibody = rootElt.elementIterator("body");
			// 遍历head节点
			JSONObject jsonmatchList = null;
			while (Ibody.hasNext()) {
				jsonmatchList = new JSONObject();
				Element body = (Element) Ibody.next();
				// body 下没有要获取的值 next获取body的子节点matchList
				Iterator ImatchList = body.elementIterator("matchList");
				Element matchList = (Element) ImatchList.next();
				Iterator Iitem = matchList.elementIterator("item");
				JSONObject jsonItem = null;
				Map<String, JSONObject> m = new HashMap<String, JSONObject>();
				while (Iitem.hasNext()) {
					jsonItem = new JSONObject();
					Element item = (Element) Iitem.next();
					String id = item.elementTextTrim("id");
					jsonItem.put("id", id);
					Iterator vslist = item.elementIterator("vs");
					while (vslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element vsele = (Element) vslist.next();
						String v0 = vsele.elementTextTrim("v0");
						String v1 = vsele.elementTextTrim("v1");
						String v3 = vsele.elementTextTrim("v3");
						String letPoint = vsele.elementTextTrim("letPoint");
						json.put("v0", v0);
						json.put("v1", v1);
						json.put("v3", v3);
						json.put("letPoint", letPoint);
						jsonItem.put("vs", json);
					}
					Iterator letVslist = item.elementIterator("letVs");
					while (letVslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element vsele = (Element) letVslist.next();
						String v0 = vsele.elementTextTrim("v0");
						String v1 = vsele.elementTextTrim("v1");
						String v3 = vsele.elementTextTrim("v3");
						String letPoint = vsele.elementTextTrim("letPoint");
						json.put("v0", v0);
						json.put("v1", v1);
						json.put("v3", v3);
						json.put("letPoint", letPoint);
						jsonItem.put("letVs", json);
					}
					// 遍历score结点下的数据浮动奖金 浮动奖金 (赔率 )
					Iterator scorelist = item.elementIterator("score");
					while (scorelist.hasNext()) {
						Element scorele = (Element) scorelist.next();
						JSONObject ddd = getVsElement(scorele);
						jsonItem.put("score", ddd);
					}
					// 遍历goal结点下的数据
					Iterator goallist = item.elementIterator("goal");
					while (goallist.hasNext()) {
						Element goalele = (Element) goallist.next();
						JSONObject li = getGoalElement(goalele);
						jsonItem.put("goal", li);
					}
					// 遍历half结点数据
					Iterator halflist = item.elementIterator("half");
					while (halflist.hasNext()) {
						Element goalele = (Element) halflist.next();
						// 因跨度太大没有用for循环
						String v00 = goalele.elementTextTrim("v00");
						String v01 = goalele.elementTextTrim("v01");
						String v03 = goalele.elementTextTrim("v03");
						String v10 = goalele.elementTextTrim("v10");
						String v11 = goalele.elementTextTrim("v11");
						String v13 = goalele.elementTextTrim("v13");
						String v30 = goalele.elementTextTrim("v30");
						String v31 = goalele.elementTextTrim("v31");
						String v33 = goalele.elementTextTrim("v33");
						JSONObject jsHalf = new JSONObject();
						jsHalf.put("v00", v00);
						jsHalf.put("v01", v01);
						jsHalf.put("v03", v03);
						jsHalf.put("v10", v10);
						jsHalf.put("v11", v11);
						jsHalf.put("v13", v13);
						jsHalf.put("v30", v30);
						jsHalf.put("v31", v31);
						jsHalf.put("v33", v33);
						jsonItem.put("half", jsHalf);
					}
					m.put(id, jsonItem);
				}
				jsonmatchList.put("matchList", m);
			}
			jsonAll.put("body", jsonmatchList);
			// 存放到body
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.debug("将字符串转为XML出现异常！");
		}
		return jsonAll;
	}
	/**
	 * 篮球赔率解析
	 * 
	 * @return *<vs>，<letVs>，<bs> , <diff > 对应篮彩胜负，让分胜负，大小分，分差
	 *         此方法应用DOM4j来解析赔率的XML字符串
	 * @param obj
	 *            XML字符串
	 */
	@SuppressWarnings("rawtypes")
	public JSONObject LqpeilvDOM(String obj) {
		JSONObject jsonAll = null;
		try {
			// 后台返回的是XML字符串，采用dom4j 解析
			Document doc = DocumentHelper.parseText(obj);
			// 获取根节点的名称
			Element rootElt = doc.getRootElement();
			// 创建MAP储存list. 遍历缓存数据
			jsonAll = new JSONObject();
			// 获取message 子节点head属性数组
			Iterator Ihead = rootElt.elementIterator("head");
			while (Ihead.hasNext()) {
				// 遍历head 获取数据并且储存
				JSONObject json = new JSONObject();
				Element head = (Element) Ihead.next();
				String messageId = head.elementTextTrim("messageId");
				String result = head.elementTextTrim("result");
				String md = head.elementTextTrim("md");
				json.put("messageId", messageId);
				json.put("result", result);
				json.put("md", md);
				jsonAll.put("head", json);
			}
			// 获取message子节点body 数据
			Iterator Ibody = rootElt.elementIterator("body");
			// 遍历head节点
			JSONObject jsonmatchList = null;
			while (Ibody.hasNext()) {
				jsonmatchList = new JSONObject();
				Element body = (Element) Ibody.next();
				// body 下没有要获取的值 next获取body的子节点matchList
				Iterator ImatchList = body.elementIterator("matchList");
				Element matchList = (Element) ImatchList.next();
				Iterator Iitem = matchList.elementIterator("item");
				JSONObject jsonItem = null;
				Map<String, JSONObject> m = new HashMap<String, JSONObject>();
				while (Iitem.hasNext()) {
					jsonItem = new JSONObject();
					Element item = (Element) Iitem.next();
					String id = item.elementTextTrim("id");
					jsonItem.put("id", id);
					Iterator vslist = item.elementIterator("vs");
					while (vslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element vsele = (Element) vslist.next();
						String v0 = vsele.elementTextTrim("v0");// 主负
						String v3 = vsele.elementTextTrim("v3");// 主胜
						json.put("v0", v0);
						json.put("v3", v3);
						jsonItem.put("vs", json);
					}
					// 遍历letVs结点下的数据让分胜负的赔率
					Iterator letVslist = item.elementIterator("letVs");
					while (letVslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element letVs = (Element) letVslist.next();
						String v0 = letVs.elementTextTrim("v0");// 主负
						String v3 = letVs.elementTextTrim("v3");// 主胜
						String letPoint = letVs.elementTextTrim("letPoint");// 让分
						json.put("v0", v0);
						json.put("v3", v3);
						json.put("letPoint", letPoint);
						jsonItem.put("letVs", json);
					}
					// 遍历bs结点下的数据
					Iterator bslist = item.elementIterator("bs");
					while (bslist.hasNext()) {
						JSONObject json = new JSONObject();
						Element bs = (Element) bslist.next();
						String g = bs.elementTextTrim("g");// 大于预设总分浮动奖金(赔率)
						String l = bs.elementTextTrim("l");// 小于预设总分浮动奖金(赔率)
						String basePoint = bs.elementTextTrim("basePoint");// 预设总分
						json.put("g", g);
						json.put("l", l);
						json.put("basePoint", basePoint);
						jsonItem.put("bs", json);
					}
					// 遍历diff结点数据
					Iterator difflist = item.elementIterator("diff");
					while (difflist.hasNext()) {
						Element diff = (Element) difflist.next();
						String v01 = diff.elementTextTrim("v01");
						String v02 = diff.elementTextTrim("v02");
						String v03 = diff.elementTextTrim("v03");
						String v04 = diff.elementTextTrim("v04");
						String v05 = diff.elementTextTrim("v05");
						String v06 = diff.elementTextTrim("v06");
						String v11 = diff.elementTextTrim("v11");
						String v12 = diff.elementTextTrim("v12");
						String v13 = diff.elementTextTrim("v13");
						String v14 = diff.elementTextTrim("v14");
						String v15 = diff.elementTextTrim("v15");
						String v16 = diff.elementTextTrim("v16");

						JSONObject json = new JSONObject();
						json.put("v01", v01);
						json.put("v02", v02);
						json.put("v03", v03);
						json.put("v04", v04);
						json.put("v05", v05);
						json.put("v06", v06);
						json.put("v11", v11);
						json.put("v12", v12);
						json.put("v13", v13);
						json.put("v14", v14);
						json.put("v15", v15);
						json.put("v16", v16);
						jsonItem.put("diff", json);
					}
					m.put(id, jsonItem);
				}
				jsonmatchList.put("matchList", m);
			}
			jsonAll.put("body", jsonmatchList);
			// 存放到body
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.debug("将字符串转为XML出现异常！");
		}
		return jsonAll;
	}
	/**
	 * @param scorele
	 * @return
	 */
	public JSONObject getVsElement(Element scorele) {
		String var = "";
		String scoreValue = "";
		JSONObject js = new JSONObject();
		for (int i = 0; i < 100; i++) {
			if (i < 10) {
				var = "v0" + String.valueOf(i);
			} else {
				var = "v" + String.valueOf(i);
			}
			scoreValue = scorele.elementTextTrim(var);
			js.put(var, scoreValue);
		}
		return js;

	}

	/**
	 * @param goal
	 * @return
	 */
	public JSONObject getGoalElement(Element goal) {
		String var = "";
		String goalValue = "";
		JSONObject js = new JSONObject();
		for (int i = 0; i < 100; i++) {
			var = "v" + String.valueOf(i);
			goalValue = goal.elementTextTrim(var);
			js.put(var, goalValue);
		}
		return js;

	}
	/**
	 * 得到星期
	 * @param weekId
	 * @return
	 */
	public String getWeek(String weekId){
		if("1".equals(weekId)) return "星期一";
		if("2".equals(weekId)) return "星期二";
		if("3".equals(weekId)) return "星期三";
		if("4".equals(weekId)) return "星期四";
		if("5".equals(weekId)) return "星期五";
		if("6".equals(weekId)) return "星期六";
		if("7".equals(weekId)) return "星期日";
		return "";
	}
	/**
	 * 得到星期对应ID
	 * @param week
	 * @return
	 */
	public String getWeekId(String week){
		if("星期一".equals(week)) return "1";
		if("星期二".equals(week)) return "2";
		if("星期三".equals(week)) return "3";
		if("星期四".equals(week)) return "4";
		if("星期五".equals(week)) return "5";
		if("星期六".equals(week)) return "6";
		if("星期日".equals(week)) return "7";
		return "";
	}
	/**
	 * 得到日期显示2013-04-07
	 * @param day
	 * @return
	 */
	public String getDayView(String day){
		if(!ValidateLotteryUtil.validateIsNull(day))
			return day.substring(0, 4)+"-"+day.substring(4,6)+"-"+day.substring(6, 8);
		else 
			return "";
	}
	/**
	 * 得到原始日期格式20130407
	 * @param dayView
	 * @return
	 */
	public String getDay(String dayView){
		if(!ValidateLotteryUtil.validateIsNull(dayView))
			return dayView.substring(0, 4)+dayView.substring(5,7)+dayView.substring(8, 10);
		else 
			return "";
	}
	/**
	 * 通过玩法得到lotNo
	 * @param wanFa
	 * @return
	 */
	public String getLotNoByWanFa(String wanFa){
		if ("BSK001".equals(wanFa)) {
			return "J00005";
		}
		if ("BSK002".equals(wanFa)) {
			return "J00006";
		}
		if ("BSK003".equals(wanFa)) {
			return "J00007";
		}
		if ("BSK004".equals(wanFa)) {
			return "J00008";
		}
		if ("FT001".equals(wanFa)) {
			return "J00001";
		}
		if ("FT002".equals(wanFa)) {
			return "J00002";
		}
		if ("FT003".equals(wanFa)) {
			return "J00003";
		}
		if ("FT004".equals(wanFa)) {
			return "J00004";
		}
		return wanFa;
	}
	/**
	 * 查询竞彩数据分析
	 * @param even 1_20120806_1_001 (类型_day_weekid_teamid)  类型：1足球  0蓝球
	 * @return
	 */
	public String getJcFootballInfo(String event){
		try{
			String url = dataanalysis+"select/getInfo";
			String parameter = "event="+event;
//			String result = commonUtil.setUrlByPOST(url, parameter);
			String result = HttpUtil.sendRequestByPost(url, parameter, true);
			logger.info("LotteryFootballUtil/getInfo查询竞彩足球数据分析返回result:"+result);
			return result;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/getInfo查询竞彩足球数据分析返回异常:"+e.getMessage());
			return "";
		}
		
	}
	/**
	 * 查询竞彩数据分析
	 * @param even 1_20120806_1_001 (类型_day_weekid_teamid)  类型：1足球  0蓝球
	 * @return
	 */
	public String getJcBasketballInfo(String event){
		try{
			String url = dataanalysis+"selectJcl/getInfo";
			String parameter = "event="+event;
//			String result = commonUtil.setUrlByPOST(url, parameter);
			String result = HttpUtil.sendRequestByPost(url, parameter, true);
			logger.info("LotteryFootballUtil/getInfo查询竞彩篮球数据分析返回result:"+result);
			return result;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/getInfo查询竞彩篮球数据分析返回异常:"+e.getMessage());
			return "";
		}
		
	}
	/**
	 * @param ballType 类型：1足球  0篮球
	 * @param day
	 * @param weekId
	 * @param tempId
	 * @param type(排行:ranking;历史:主10:history_z10,客10:history_k10,主5:history_z5,客5:history_k5;亚盘:yapan;欧赔:oupei)
	 * @return
	 */
	public Map<String, Object> selectDataAnalysis(String ballType,String day,String weekId,String tempId,String type){
		Map<String, Object> dataAnalysisMap = null;
		List<Object> dataAnalysis= null;
		try{
			//1_20120806_1_001(类型_day_weekid_teamid)类型：1足球  0篮球
			String event = ballType+"_"+day+"_"+weekId+"_"+tempId;
			String result = getJcFootballInfo(event);
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
					}else if("history_jf".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("preClashSchedules");
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
			logger.error("LotteryFootballUtil/selectDataAnalysis查询竞彩数据分析返回异常:"+e.getMessage());
			return dataAnalysisMap;
		}
	}
	/**
	 * @param ballType 类型：1足球  0篮球
	 * @param day
	 * @param weekId
	 * @param tempId
	 * @param type(排行:ranking;历史:主10:history_z10,客10:history_k10,主5:history_z5,客5:history_k5;亚盘:yapan;欧赔:oupei)
	 * @return
	 */
	public Map<String, Object> selectJcBasketballDataAnalysis(String ballType,String day,String weekId,String tempId,String type){
		Map<String, Object> dataAnalysisMap = null;
		List<Object> dataAnalysis= null;
		try{
			//1_20120806_1_001(类型_day_weekid_teamid)类型：1足球  0篮球
			String event = ballType+"_"+day+"_"+weekId+"_"+tempId;
			String result = getJcBasketballInfo(event);
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
							matchRanking.setTeamID(jsonObject.getInt("teamId"));
							matchRanking.setTeamName(jsonObject.getString("teamName"));
							matchRanking.setWinLv(jsonObject.getString("winLv"));
							matchRanking.setMatchCount(jsonObject.getInt("matchCount"));
							matchRanking.setWinCount(jsonObject.getInt("winCount"));
							matchRanking.setLoseCount(jsonObject.getInt("loseCount"));
							matchRanking.setGainScore(jsonObject.getString("gainScore"));
							matchRanking.setLoseScore(jsonObject.getString("loseScore"));
							matchRanking.setScoreDifference(jsonObject.getString("scoreDifference"));
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
					}else if("history_jf".equals(type)){
						jsonArray = valueJsonObject.getJSONArray("preClashSchedules");
					}
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchHistory matchHistory = new MatchHistory();
							matchHistory.setEvent(jsonObject.getString("event"));
							matchHistory.setHomeTeamID(jsonObject.getString("homeTeamId"));
							matchHistory.setGuestTeamID(jsonObject.getString("guestTeamId"));
							matchHistory.setHomeTeam(jsonObject.getString("homeTeam"));
							matchHistory.setGuestTeam(jsonObject.getString("guestTeam"));
							matchHistory.setMatchTime(commonUtil.getDataFormat(jsonObject.getLong("matchTime"), "yyyy-MM-dd"));
							matchHistory.setMatchState(getMatchState(jsonObject.getString("matchState")));
							matchHistory.setHomeScore(jsonObject.getString("homeScore"));
							matchHistory.setGuestScore(jsonObject.getString("guestScore"));
							matchHistory.setSclassName(jsonObject.getString("sclassName"));
							matchHistory.setScheduleId(jsonObject.getString("scheduleId"));
							matchHistory.setSclassId(jsonObject.getString("sclassId"));
							matchHistory.setSclassType(jsonObject.getString("sclassType"));
							matchHistory.setSclassShortName(jsonObject.getString("sclassShortName"));
							matchHistory.setHomeOne(jsonObject.getString("homeOne"));
							matchHistory.setGuestOne(jsonObject.getString("guestOne"));
							matchHistory.setHomeTwo(jsonObject.getString("homeTwo"));
							matchHistory.setGuestTwo(jsonObject.getString("guestTwo"));
							matchHistory.setHomeThree(jsonObject.getString("homeThree"));
							matchHistory.setGuestThree(jsonObject.getString("guestThree"));
							matchHistory.setHomeFour(jsonObject.getString("homeFour"));
							matchHistory.setGuestFour(jsonObject.getString("guestFour"));
							matchHistory.setLetScore(jsonObject.getString("letScore"));
							matchHistory.setTotalScore(jsonObject.getString("totalScore"));
							dataAnalysis.add(matchHistory);
						}
					}
				}else if("oupei".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("standards");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchOuPei matchOuPei = new MatchOuPei();
							matchOuPei.setFanHuanLu(jsonObject.getString("fanHuanLv"));
							matchOuPei.setHomeWinLu(jsonObject.getString("homeWinLv"));
							matchOuPei.setGuestWinLu(jsonObject.getString("guestWinLv"));
							matchOuPei.setK_h(jsonObject.getString("k_h"));
							matchOuPei.setK_g(jsonObject.getString("k_g"));
							matchOuPei.setCompanyName(jsonObject.getString("companyName"));
							matchOuPei.setFirstHomeWin(jsonObject.getString("firstHomeWin"));
							matchOuPei.setFirstGuestWin(jsonObject.getString("firstGuestWin"));
							matchOuPei.setHomeWin(jsonObject.getString("homeWin"));
							matchOuPei.setGuestWin(jsonObject.getString("guestWin"));
							if(jsonObject.getString("modifyTime")==null||"".equals(jsonObject.getString("modifyTime"))||"null".equals(jsonObject.getString("modifyTime")))
								matchOuPei.setModifyTime("");
							else
								matchOuPei.setModifyTime(commonUtil.getDataFormat(jsonObject.getLong("modifyTime"), ""));
							dataAnalysis.add(matchOuPei);
						}
					}
				}else if("zongfen".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("totalScores");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchTotalScore matchTotalScore = new MatchTotalScore();
							matchTotalScore.setCompanyId(jsonObject.getString("companyId"));
							matchTotalScore.setCompanyName(jsonObject.getString("companyName"));
							matchTotalScore.setDownOdds(jsonObject.getString("downOdds"));
							matchTotalScore.setFirstDownodds(jsonObject.getString("firstDownodds"));
							matchTotalScore.setFirstGoal(jsonObject.getString("firstGoal"));
							matchTotalScore.setFirstUpodds(jsonObject.getString("firstUpodds"));
							matchTotalScore.setGoal(jsonObject.getString("goal"));
							matchTotalScore.setOddsId(jsonObject.getString("oddsId"));
							matchTotalScore.setScheduleId(jsonObject.getString("scheduleId"));
							matchTotalScore.setUpOdds(jsonObject.getString("upOdds"));
							dataAnalysis.add(matchTotalScore);
						}
					}
				}else if("rangfen".equals(type)){
					JSONArray jsonArray = valueJsonObject.getJSONArray("letGoals");
					if(jsonArray!=null){
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							MatchLetGoal matchLetGoal = new MatchLetGoal();
							matchLetGoal.setCompanyId(jsonObject.getString("companyId"));
							matchLetGoal.setCompanyName(jsonObject.getString("companyName"));
							matchLetGoal.setDownOdds(jsonObject.getString("downOdds"));
							matchLetGoal.setFirstDownodds(jsonObject.getString("firstDownodds"));
							matchLetGoal.setFirstGoal(jsonObject.getString("firstGoal"));
							matchLetGoal.setFirstUpodds(jsonObject.getString("firstUpodds"));
							matchLetGoal.setGoal(jsonObject.getString("goal"));
							matchLetGoal.setOddsId(jsonObject.getString("oddsId"));
							matchLetGoal.setScheduleId(jsonObject.getString("scheduleId"));
							matchLetGoal.setUpOdds(jsonObject.getString("upOdds"));
							dataAnalysis.add(matchLetGoal);
						}
					}
				}
				JSONObject scheduleJsonObject = valueJsonObject.getJSONObject("schedule");
				MatchMessage matchMessage = new MatchMessage();
				matchMessage.setEvent(scheduleJsonObject.getString("event"));
				matchMessage.setHomeTeamID(scheduleJsonObject.getString("homeTeamId"));
				matchMessage.setGuestTeamID(scheduleJsonObject.getString("guestTeamId"));
				matchMessage.setHomeTeam(scheduleJsonObject.getString("homeTeam"));
				matchMessage.setGuestTeam(scheduleJsonObject.getString("guestTeam"));
				matchMessage.setMatchTime(commonUtil.getDataFormat(scheduleJsonObject.getLong("matchTime"), ""));
				matchMessage.setMatchState(getMatchState(scheduleJsonObject.getString("matchState")));
				matchMessage.setHomeScore(scheduleJsonObject.getString("homeScore"));
				matchMessage.setGuestScore(scheduleJsonObject.getString("guestScore"));
				matchMessage.setSclassName(scheduleJsonObject.getString("sclassName"));
				matchMessage.setScheduleId(scheduleJsonObject.getString("scheduleId"));
				matchMessage.setSclassId(scheduleJsonObject.getString("sclassId"));
				matchMessage.setSclassType(scheduleJsonObject.getString("sclassType"));
				matchMessage.setSclassShortName(scheduleJsonObject.getString("sclassShortName"));
				matchMessage.setHomeOne(scheduleJsonObject.getString("homeOne"));
				matchMessage.setGuestOne(scheduleJsonObject.getString("guestOne"));
				matchMessage.setHomeTwo(scheduleJsonObject.getString("homeTwo"));
				matchMessage.setGuestTwo(scheduleJsonObject.getString("guestTwo"));
				matchMessage.setHomeThree(scheduleJsonObject.getString("homeThree"));
				matchMessage.setGuestThree(scheduleJsonObject.getString("guestThree"));
				matchMessage.setHomeFour(scheduleJsonObject.getString("homeFour"));
				matchMessage.setGuestFour(scheduleJsonObject.getString("guestFour"));
				matchMessage.setLetScore(scheduleJsonObject.getString("letScore"));
				matchMessage.setTotalScore(scheduleJsonObject.getString("totalScore"));

				dataAnalysisMap.put("dataAnalysis", dataAnalysis);
				dataAnalysisMap.put("matchMessage", matchMessage);
			}
			
			return dataAnalysisMap;
		}catch (Exception e) {
			logger.error("LotteryFootballUtil/selectDataAnalysis查询竞彩数据分析返回异常:"+e.getMessage());
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
}
