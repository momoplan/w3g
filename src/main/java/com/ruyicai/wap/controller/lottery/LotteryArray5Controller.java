package com.ruyicai.wap.controller.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryArray5Util;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateArray5Util;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/array5")
public class LotteryArray5Controller {
	Logger logger = Logger.getLogger(LotteryArray5Controller.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryArray5Controller/bySelf排列五自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateArray5Util.validateBySelf(wballs, qballs, bballs, sballs, gballs, beiShu, addNumber);
		Map<String, String> wmap = new HashMap<String, String>();
		Map<String, String> qmap = new HashMap<String, String>();
		Map<String, String> bmap = new HashMap<String, String>();
		Map<String, String> smap = new HashMap<String, String>();
		Map<String, String> gmap = new HashMap<String, String>();
		if(wballs!=null){
			for (String wball : wballs) {
				wmap.put("wball_"+wball,wball);
			}
		}
		if(qballs!=null){
			for (String qball : qballs) {
				qmap.put("qball_"+qball,qball);
			}
		}
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball,bball);
			}
		}
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("wballs", wmap);
			modelAndView.addObject("qballs", qmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("array5/bySelf");
			logger.info("LotteryArray5Controller/bySelf排列五自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryArray5Controller/bySelf排列五自选参数验证通过");
		Map<String, String> selfBetMap = LotteryArray5Util.getSelfBetMap(wballs, qballs, bballs, sballs, gballs);
		String betCode = selfBetMap.get("betCode");
		String betCodeView = selfBetMap.get("betCodeView");
		String zhuShu = selfBetMap.get("zhuShu");
		String playType = selfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("wballs", wmap);
			modelAndView.addObject("qballs", qmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("array5/bySelf");
			logger.info("LotteryArray5Controller/bySelf排列五自选金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;

		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("betDetail");
		return modelAndView;
		
	}

	/**
	 * 排列五随机自选
	 * @param ballNumber
	 * @param ballType
	 * @param gballs
	 * @param sballs
	 * @param bballs
	 * @param qballs
	 * @param wballs
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoSelf")
	public ModelAndView byAutoSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryArray5Controller/byAutoSelf排列五随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateArray5Util.validateByAutoSelf(ballNumber);
		Map<String, String> wmap = new HashMap<String, String>();
		Map<String, String> qmap = new HashMap<String, String>();
		Map<String, String> bmap = new HashMap<String, String>();
		Map<String, String> smap = new HashMap<String, String>();
		Map<String, String> gmap = new HashMap<String, String>();
		if(wballs!=null){
			for (String wball : wballs) {
				wmap.put("wball_"+wball,wball);
			}
		}
		if(qballs!=null){
			for (String qball : qballs) {
				qmap.put("qball_"+qball,qball);
			}
		}
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball,bball);
			}
		}
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("wballs", wmap);
			modelAndView.addObject("qballs", qmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("array5/bySelf");
			logger.info("LotteryArray5Controller/byAutoSelf排列五随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryArray5Controller/byAutoSelf排列五随机自选参数验证通过");
		if("W".equals(ballType)){
			modelAndView.addObject("wballNumber", ballNumber);
			wballs = LotteryArray5Util.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			wmap.clear();
			if(wballs!=null){
				for (String wball : wballs) {
					wmap.put("wball_"+wball,wball);
				}
			}
			
		}
		if("Q".equals(ballType)){
			modelAndView.addObject("qballNumber", ballNumber);
			qballs = LotteryArray5Util.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			qmap.clear();
			if(qballs!=null){
				for (String qball : qballs) {
					qmap.put("qball_"+qball,qball);
				}
			}
			
		}
		if("B".equals(ballType)){
			modelAndView.addObject("bballNumber", ballNumber);
			bballs = LotteryArray5Util.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			bmap.clear();
			if(bballs!=null){
				for (String bball : bballs) {
					bmap.put("bball_"+bball,bball);
				}
			}
			
		}
		if("S".equals(ballType)){
			modelAndView.addObject("sballNumber", ballNumber);
			sballs = LotteryArray5Util.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			smap.clear();
			if(sballs!=null){
				for (String sball : sballs) {
					smap.put("sball_"+sball, sball);
				}
			}
		}
		if("G".equals(ballType)){
			modelAndView.addObject("gballNumber", ballNumber);
			gballs = LotteryArray5Util.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			gmap.clear();
			if(gballs!=null){
				for (String gball : gballs) {
					gmap.put("gball_"+gball, gball);
				}
			}
			
		}		
		modelAndView.addObject("wballs", wmap);
		modelAndView.addObject("qballs", qmap);
		modelAndView.addObject("bballs", bmap);
		modelAndView.addObject("sballs", smap);
		modelAndView.addObject("gballs", gmap);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("array5/bySelf");
		return modelAndView;
	}
	/**
	 * 排列五机选投注
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoCode")
	public ModelAndView byAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryArray5Controller/byAutoCode排列五机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateArray5Util.validateByAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("array5/byAuto");
			logger.info("LotteryArray5Controller/byAutoCode排列五参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryArray5Controller/byAutoCode排列五参数验证通过");
		Map<String, String> autoBetMap = LotteryArray5Util.getAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("array5/byAuto");
		return modelAndView;
	}
	
	/**
	 * 排列五机选投注
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoBet")
	public ModelAndView byAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryArray5Controller/byAutoBet排列五机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateArray5Util.validateByAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("playType", playType);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("autoZhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("betType", betType);
			modelAndView.setViewName("array5/byAuto");
			logger.info("LotteryArray5Controller/byAutoBet排列五参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryArray5Controller/byAutoBet排列五参数验证通过");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("autoZhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("playType", playType);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.addObject("betType", betType);
			modelAndView.setViewName("array5/byAuto");
			logger.info("LotteryArray5Controller/byAutoBet排列五金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;
		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("betDetail");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byAutoCode(autoZhuShu, lotNo, batchCode,betType, request);
		if("提交投注".equals(submitType)) return byAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType,request);
		return modelAndView;
	}
	@RequestMapping("/byLottery")
	public ModelAndView byLottery(
			@RequestParam(value="wballNumber",defaultValue="")String wballNumber,
			@RequestParam(value="qballNumber",defaultValue="")String qballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01011")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选万位".equals(submitType)) return byAutoSelf(wballNumber, "W", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选千位".equals(submitType)) return byAutoSelf(qballNumber, "Q", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选百位".equals(submitType)) return byAutoSelf(bballNumber, "B", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选十位".equals(submitType)) return byAutoSelf(sballNumber, "S", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选个位".equals(submitType)) return byAutoSelf(gballNumber, "G", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);

		if("提交投注".equals(submitType)) return bySelf(gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
}
