package com.ruyicai.wap.controller.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.bet.LotteryFootballUtil;
import com.ruyicai.wap.vo.MatchMessage;
@Controller
@RequestMapping("/footballIndex")
public class LotteryFootballIndexController {
	@Autowired
	LotteryFootballUtil lotteryFootballUtil;
	@Autowired
	SelectAllUtil selectAllUtil;
	@RequestMapping("/byFootball")
	public ModelAndView byFootball(
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="batchCodeEndTime",defaultValue="") String batchCodeEndTime
			){
		ModelAndView modelAndView = new ModelAndView();
		JSONArray batchCodes = lotteryFootballUtil.selectZCIssue(lotNo);
		if(batchCodes==null||batchCodes.size()<1){
			
			modelAndView.addObject("messageError", "官方尚未公布对阵信息！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
//		Map<String, String> map = new HashMap<String, String>();
//		for(int i=0;i<batchCodes.size();i++){
//			JSONObject jsonObject = batchCodes.getJSONObject(i);
//			map.put(jsonObject.getString("batchCode"), jsonObject.getString("endTime"));
//		}
		if(batchCode==null||"".equals(batchCode)) 
			batchCode = batchCodes.getJSONObject(0).getString("batchCode");
		Map<String, String> map = selectAllUtil.selectEndTime(lotNo, batchCode);
		String betEndTime = "";
		String heMaiEndTime = "";
		if(map!=null){
			betEndTime = map.get("betEndTime");
			heMaiEndTime = map.get("heMaiEndTime");
		}
		
		JSONArray jsonArray = lotteryFootballUtil.selectFlData(lotNo, batchCode);
		if(jsonArray==null||jsonArray.size()<1){
			modelAndView.addObject("messageError", "官方尚未公布对阵信息！");
			modelAndView.addObject("beiShu", "1");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betEndTime", betEndTime);
		modelAndView.addObject("heMaiEndTime", heMaiEndTime);
		modelAndView.addObject("batchCodes", batchCodes);
		modelAndView.addObject("duizhens", jsonArray);
		modelAndView.addObject("beiShu", "1");
		modelAndView.setViewName(getViewName(lotNo));
		return modelAndView;
	}
	public String getViewName(String lotNo){
		if(Constants.LOTNO_ZC_SFC_14C.equals(lotNo)) return "football/byShengFuCai";
		if(Constants.LOTNO_ZC_R9C.equals(lotNo)) return "football/byRenXuan9Chang";
		if(Constants.LOTNO_ZC_4C_JQC.equals(lotNo)) return "football/by4ChangJinQiu";
		if(Constants.LOTNO_ZC_6C_BQC.equals(lotNo)) return "football/by6ChangBanQuanChang";
		return "";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisRanking")
	public ModelAndView selectDataAnalysisRanking(
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="tempId",defaultValue="") String tempId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryFootballUtil.selectDataAnalysis(lotNo, batchCode, tempId, "ranking");
		List<Object> matchRankings = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchRankings", matchRankings);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("tempId", tempId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("football/dataAnalysis_Ranking");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisHistory")
	public ModelAndView selectDataAnalysisHistory(
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="tempId",defaultValue="") String tempId,
			@RequestParam(value="type",defaultValue="") String type){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryFootballUtil.selectDataAnalysis(lotNo, batchCode, tempId, type);
		List<Object> matchHistorys = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");

		modelAndView.addObject("matchHistorys", matchHistorys);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("tempId", tempId);
		modelAndView.addObject("type", type);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("football/dataAnalysis_History");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisYaPan")
	public ModelAndView selectDataAnalysisYaPan(
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="tempId",defaultValue="") String tempId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryFootballUtil.selectDataAnalysis(lotNo, batchCode, tempId, "yapan");
		List<Object> matchYaPans = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchYaPans", matchYaPans);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("tempId", tempId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("football/dataAnalysis_YaPan");
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectDataAnalysisOuPei")
	public ModelAndView selectDataAnalysisOuPei(
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="tempId",defaultValue="") String tempId){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> dataAnalysisMap = lotteryFootballUtil.selectDataAnalysis(lotNo, batchCode, tempId, "oupei");
		List<Object> matchOuPeis = (List<Object>) dataAnalysisMap.get("dataAnalysis");
		MatchMessage matchMessage = (MatchMessage) dataAnalysisMap.get("matchMessage");
		modelAndView.addObject("matchOuPeis", matchOuPeis);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("tempId", tempId);
		modelAndView.addObject("matchMessage", matchMessage);
		modelAndView.setViewName("football/dataAnalysis_OuPei");
		return modelAndView;
	}
}
