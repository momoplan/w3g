package com.ruyicai.wap.controller.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.bet.LotteryJcUtil;
import com.ruyicai.wap.util.validate.ValidateJcUtil;
import com.ruyicai.wap.vo.MatchMessage;

@Controller
@RequestMapping("jcBasketballIndex")
public class LotteryJcBasketballIndexController {
	private static final Logger logger = Logger
			.getLogger(LotteryJcBasketballIndexController.class);
	@Autowired
	LotteryJcUtil lotteryJcUtil;
	/**
	 * 显示比赛
	 * @param type查询类别 (0 篮彩 1足彩 )
	 * @param valueType单关0/过关1赔率
	 * @param wanFa
	 * @return
	 */
	@RequestMapping("/byJcBasketBall")
	public ModelAndView byJcBasketBall(
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="valueType",defaultValue="") String valueType,
			@RequestParam(value="wanFa",defaultValue="") String wanFa){
		ModelAndView modelAndView = new ModelAndView();
		List<Map<String, Object>> jcMatchAgainstsMaps = lotteryJcUtil.selectJcMatchsAgainstByDay(type, valueType, wanFa);
		modelAndView.addObject("jcMatchAgainstsMaps", jcMatchAgainstsMaps);
		modelAndView.addObject("type", type);
		modelAndView.addObject("valueType", valueType);
		modelAndView.addObject("wanFa", wanFa);
		modelAndView.setViewName(getViewName(wanFa));
		return modelAndView;
		
	}
	@RequestMapping("/byJcBasketballToSelect")
	public ModelAndView byJcBasketballToSelect(
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="valueType",defaultValue="") String valueType,
			@RequestParam(value="wanFa",defaultValue="") String wanFa,
			@RequestParam(value="hiddenType",required=false) List<String> hiddenTypes,
			@RequestParam(value="zhuMa",required=false) List<String> zhuMas,
			@RequestParam(value="submitType",defaultValue="") String submitType,
			@RequestParam(value="playType",defaultValue="") String playType,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if(submitType.indexOf("赛事")>-1) return saiShiAndYinCang(type, valueType, wanFa, hiddenTypes, zhuMas, submitType);
		if(submitType.indexOf("隐藏")>-1) return saiShiAndYinCang(type, valueType, wanFa, hiddenTypes, zhuMas, submitType);
		if("自由过关".equals(submitType)&&"1".equals(valueType)) return guoGuan(type, valueType, wanFa, hiddenTypes, zhuMas, submitType, request);
		if("多串过关".equals(submitType)&&"1".equals(valueType)) return guoGuan(type, valueType, wanFa, hiddenTypes, zhuMas, submitType, request);
		if("提交投注".equals(submitType)&&"0".equals(valueType)) return guoGuan(type, valueType, wanFa, hiddenTypes, zhuMas, submitType, request);
		return modelAndView;
	}
	public ModelAndView saiShiAndYinCang(
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="valueType",defaultValue="") String valueType,
			@RequestParam(value="wanFa",defaultValue="") String wanFa,
			@RequestParam(value="hiddenType",required=false) List<String> hiddenTypes,
			@RequestParam(value="zhuMa",required=false) List<String> zhuMas,
			@RequestParam(value="submitType",defaultValue="") String submitType){
		ModelAndView modelAndView = new ModelAndView();
		String zhuMaStr = "";
		if(zhuMas!=null){
			zhuMaStr = getZhuMaStr(zhuMas);
		}
		String hiddenStr = "";
		if(hiddenTypes!=null){
			hiddenStr = getHiddenStr(hiddenTypes, submitType);
		}
		List<Map<String, Object>> jcMatchAgainstsMaps = lotteryJcUtil.selectJcMatchsAgainstByDay(type, valueType, wanFa);
		modelAndView.addObject("jcMatchAgainstsMaps", jcMatchAgainstsMaps);
		modelAndView.addObject("type", type);
		modelAndView.addObject("valueType", valueType);
		modelAndView.addObject("wanFa", wanFa);
		modelAndView.addObject("hiddenStr", hiddenStr);
		modelAndView.addObject("zhuMaStr", zhuMaStr);
		modelAndView.setViewName(getViewName(wanFa));
		
		return modelAndView;
	}
	public ModelAndView guoGuan(
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="valueType",defaultValue="") String valueType,
			@RequestParam(value="wanFa",defaultValue="") String wanFa,
			@RequestParam(value="hiddenType",required=false) List<String> hiddenTypes,
			@RequestParam(value="zhuMa",required=false) List<String> zhuMas,
			@RequestParam(value="submitType",defaultValue="") String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String zhuMaStr = "";
		if(zhuMas!=null){
			zhuMaStr = getZhuMaStr(zhuMas);
		}
		String hiddenStr = "";
		if(hiddenTypes!=null){
			hiddenStr = getHiddenStr(hiddenTypes, submitType);
		}
		if(zhuMas==null){
			List<Map<String, Object>> jcMatchAgainstsMaps = lotteryJcUtil.selectJcMatchsAgainstByDay(type, valueType, wanFa);
			modelAndView.addObject("jcMatchAgainstsMaps", jcMatchAgainstsMaps);
			modelAndView.addObject("type", type);
			modelAndView.addObject("valueType", valueType);
			modelAndView.addObject("wanFa", wanFa);
			modelAndView.addObject("hiddenStr", hiddenStr);
			modelAndView.addObject("zhuMaStr", zhuMaStr);
			modelAndView.addObject("messageError", "请选择比赛！");
			modelAndView.setViewName(getViewName(wanFa));
			return modelAndView;
		}
		String betCode = "";
		JSONArray betCodeViewArray = new JSONArray();
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list.addAll(zhuMas);
		list2.addAll(zhuMas);
		//${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${infos.homeTeam }|${jcMatchAgainst.peiLv.vs.letPoint}|${jcMatchAgainst.guestTeam}|胜|${jcMatchAgainst.peiLv.vs.v3}
		for (int m=0;m<list2.size();m++) {
			String zhuMa = list2.get(m);
			if(zhuMa!=null&&!"".equals(zhuMa)&&!" ".equals(zhuMa)){
				JSONObject betCodeViewObject = new JSONObject();
				String zm[] = zhuMa.split("\\|");
				String str=zm[0]+"|"+zm[1]+"|"+zm[2];
				betCode += zm[0]+"|"+zm[1]+"|"+zm[2]+"|"+zm[3];
				String betCodeView = "";
				if(zm.length>10)
					betCodeView = zm[9]+zm[10];
				else 
					betCodeView = zm[9];
				for(int n=m+1;n<list.size();n++){
					String zhu = list.get(n);
					if(zhu!=null&&!"".equals(zhu)&&!" ".equals(zhu)){
						String z[] = zhu.split("\\|");
						String s=z[0]+"|"+z[1]+"|"+z[2];
						if(str.equals(s)){
							betCode+=z[3];
							if(z.length>10)
								betCodeView += "<br/>"+z[9]+z[10];
							else 
								betCodeView += "<br/>"+z[9];
							
							list.set(n, "");
							list2.set(n, "");
						} 
					}
				}
				betCodeViewObject.put("day",lotteryJcUtil.getDayView(zm[0]));
				betCodeViewObject.put("teamId", zm[2]);
				betCodeViewObject.put("week", zm[4]);
				betCodeViewObject.put("league", zm[5]);
				betCodeViewObject.put("homeTeam", zm[6]);
				betCodeViewObject.put("letPoint", zm[7]);
				betCodeViewObject.put("guestTeam", zm[8]);
				betCodeViewObject.put("betCodeView", betCodeView);
				betCodeViewArray.add(betCodeViewObject);

			}else{
				continue;
			}
			betCode += "^";
		}
		logger.info("LotteryJcBasketballIndexController/guoGuan竞彩足球投注内容betCode:"+betCode+",betCodeView:"+betCodeViewArray.toString());
		String validateMatchsTotalResult = ValidateJcUtil.validateMatchsTotal(submitType, betCodeViewArray.size());
		if(validateMatchsTotalResult!=null&&!"".equals(validateMatchsTotalResult)){
			List<Map<String, Object>> jcMatchAgainstsMaps = lotteryJcUtil.selectJcMatchsAgainstByDay(type, valueType, wanFa);
			modelAndView.addObject("jcMatchAgainstsMaps", jcMatchAgainstsMaps);
			modelAndView.addObject("type", type);
			modelAndView.addObject("valueType", valueType);
			modelAndView.addObject("wanFa", wanFa);
			modelAndView.addObject("hiddenStr", hiddenStr);
			modelAndView.addObject("zhuMaStr", zhuMaStr);
			modelAndView.addObject("messageError", validateMatchsTotalResult);
			modelAndView.setViewName(getViewName(wanFa));
			return modelAndView;

		}
		String betCodeViewArrayToString = "";
		for(int i=0;i<betCodeViewArray.size();i++){
			JSONObject betCodeViewObject = betCodeViewArray.getJSONObject(i);
			betCodeViewArrayToString+="<div><p class='zucai'><em>"+betCodeViewObject.getString("teamId")
            +".</em>&nbsp;<span>"+betCodeViewObject.getString("league")+"</span>&nbsp;&nbsp;"
			+betCodeViewObject.getString("day")+"</p><table width='260' border='1' cellspacing='0' cellpadding='0' class='jifen-table playball-table'><tr><td width='110'>"
			+betCodeViewObject.getString("guestTeam")+"</td><td><span>"
			+betCodeViewObject.getString("letPoint")+"</span></td><td width='110'>"
			+betCodeViewObject.getString("homeTeam")+"(主)</td></tr></table><p class='zucai-kind'>投注：<br/>"
            +betCodeViewObject.getString("betCodeView")+"</p></div>";
		}
		modelAndView.addObject("wanFa", wanFa);
		modelAndView.addObject("type", type);
		modelAndView.addObject("valueType", valueType);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("submitType", submitType);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("betCodeViewArray", betCodeViewArray);
		modelAndView.addObject("changCi", betCodeViewArray.size());

		modelAndView.addObject("betCodeViewArrayToString", betCodeViewArrayToString);
		modelAndView.setViewName("jcBasketball/jcBasketballBetDetail");
		return modelAndView;
	}
	/**
	 * 得到用户选择的赛果
	 * @param zhuMas
	 * @return
	 */
	public String getZhuMaStr(List<String> zhuMas){
		String zhuMaStr = "";
		for (String zhuMa : zhuMas) {
			String zm[] = zhuMa.split("\\|");
			zhuMaStr+=zm[0]+"|"+zm[1]+"|"+zm[2]+"|"+zm[3]+",";
		}
		return zhuMaStr;
	}
	/**
	 * 得到用户是否隐藏赛事
	 * @param hiddenTypes
	 * @param submitType
	 * @return
	 */
	public String getHiddenStr(List<String> hiddenTypes,String submitType){
		String hiddenStr = "";
		for (String hiddenType : hiddenTypes) {
			String ht[] = hiddenType.split("\\|");
			hiddenStr+=ht[0]+",";
		}
		String str[] = submitType.split("\\|");
		if(submitType.indexOf("赛事")>-1){
			if(hiddenStr.indexOf(str[0])==-1)
				hiddenStr+=str[0];
		}else if(submitType.indexOf("隐藏")>-1){
			if(hiddenStr.indexOf(str[0])>-1)
				hiddenStr=hiddenStr.replace(str[0], "");
		}
		return hiddenStr;
	}
	public String getViewName(String wanFa){
		if("BSK001".equals(wanFa)) return "jcBasketball/jcBasketball_SF";
		if("BSK002".equals(wanFa)) return "jcBasketball/jcBasketball_RFSF";
		if("BSK003".equals(wanFa)) return "jcBasketball/jcBasketball_SFC";
		if("BSK004".equals(wanFa)) return "jcBasketball/jcBasketball_DXF";
		return "";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisRanking")
	public ModelAndView selectDataAnalysisRanking(
			@RequestParam(value="day",defaultValue="") String day,
			@RequestParam(value="weekId",defaultValue="") String weekId,
			@RequestParam(value="teamId",defaultValue="") String teamId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryJcUtil.selectJcBasketballDataAnalysis("0", day,weekId, teamId, "ranking");
		List<Object> matchRankings = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchRankings", matchRankings);
		modelAndView.addObject("day", day);
		modelAndView.addObject("weekId", weekId);
		modelAndView.addObject("teamId", teamId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("jcBasketball/dataAnalysis_Ranking");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisHistory")
	public ModelAndView selectDataAnalysisHistory(
			@RequestParam(value="day",defaultValue="") String day,
			@RequestParam(value="weekId",defaultValue="") String weekId,
			@RequestParam(value="teamId",defaultValue="") String teamId,
			@RequestParam(value="type",defaultValue="") String type){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryJcUtil.selectJcBasketballDataAnalysis("0", day,weekId,teamId, type);
		List<Object> matchHistorys = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");

		modelAndView.addObject("matchHistorys", matchHistorys);
		modelAndView.addObject("day", day);
		modelAndView.addObject("weekId", weekId);
		modelAndView.addObject("teamId", teamId);
		modelAndView.addObject("type", type);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("jcBasketball/dataAnalysis_History");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisOuPei")
	public ModelAndView selectDataAnalysisOuPei(
			@RequestParam(value="day",defaultValue="") String day,
			@RequestParam(value="weekId",defaultValue="") String weekId,
			@RequestParam(value="teamId",defaultValue="") String teamId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryJcUtil.selectJcBasketballDataAnalysis("0", day,weekId, teamId, "oupei");
		List<Object> matchOuPeis = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchOuPeis", matchOuPeis);
		modelAndView.addObject("day", day);
		modelAndView.addObject("weekId", weekId);
		modelAndView.addObject("teamId", teamId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("jcBasketball/dataAnalysis_OuPei");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisLetScore")
	public ModelAndView selectDataAnalysisLetScore(
			@RequestParam(value="day",defaultValue="") String day,
			@RequestParam(value="weekId",defaultValue="") String weekId,
			@RequestParam(value="teamId",defaultValue="") String teamId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryJcUtil.selectJcBasketballDataAnalysis("0", day,weekId, teamId, "rangfen");
		List<Object> matchLetScores = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchLetScores", matchLetScores);
		modelAndView.addObject("day", day);
		modelAndView.addObject("weekId", weekId);
		modelAndView.addObject("teamId", teamId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("jcBasketball/dataAnalysis_LetScore");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisTotalScore")
	public ModelAndView selectDataAnalysisTotalScore(
			@RequestParam(value="day",defaultValue="") String day,
			@RequestParam(value="weekId",defaultValue="") String weekId,
			@RequestParam(value="teamId",defaultValue="") String teamId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryJcUtil.selectJcBasketballDataAnalysis("0", day,weekId, teamId, "zongfen");
		List<Object> matchTotalScores = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchTotalScores", matchTotalScores);
		modelAndView.addObject("day", day);
		modelAndView.addObject("weekId", weekId);
		modelAndView.addObject("teamId", teamId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("jcBasketball/dataAnalysis_TotalScore");
		return modelAndView;
	}
}
