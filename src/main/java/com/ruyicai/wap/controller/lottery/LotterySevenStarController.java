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
import com.ruyicai.wap.util.bet.LotterySevenStarUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
import com.ruyicai.wap.util.validate.ValidateSevenStarUtil;
@Controller
@RequestMapping("/sevenStar")
public class LotterySevenStarController {
	Logger logger = Logger.getLogger(LotterySevenStarController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 七星彩自选
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param ball4s
	 * @param ball5s
	 * @param ball6s
	 * @param ball7s
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="ball4",required=false)List<String> ball4s,
			@RequestParam(value="ball5",required=false)List<String> ball5s,
			@RequestParam(value="ball6",required=false)List<String> ball6s,
			@RequestParam(value="ball7",required=false)List<String> ball7s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotterySevenStarController/bySelf七星彩自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateSevenStarUtil.validateBySelf(ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		Map<String, String> map4 = new HashMap<String, String>();
		Map<String, String> map5 = new HashMap<String, String>();
		Map<String, String> map6 = new HashMap<String, String>();
		Map<String, String> map7 = new HashMap<String, String>();
		if(ball1s!=null){
			for (String ball1 : ball1s) {
				map1.put("ball1_"+ball1,ball1);
			}
		}
		if(ball2s!=null){
			for (String ball2 : ball2s) {
				map2.put("ball2_"+ball2,ball2);
			}
		}
		if(ball3s!=null){
			for (String ball3 : ball3s) {
				map3.put("ball3_"+ball3,ball3);
			}
		}
		if(ball4s!=null){
			for (String ball4 : ball4s) {
				map4.put("ball4_"+ball4,ball4);
			}
		}
		if(ball5s!=null){
			for (String ball5 : ball5s) {
				map5.put("ball5_"+ball5,ball5);
			}
		}
		if(ball6s!=null){
			for (String ball6 : ball6s) {
				map6.put("ball6_"+ball6,ball6);
			}
		}
		if(ball7s!=null){
			for (String ball7 : ball7s) {
				map7.put("ball7_"+ball7,ball7);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_QXC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("ball4s", map4);
			modelAndView.addObject("ball5s", map5);
			modelAndView.addObject("ball6s", map6);
			modelAndView.addObject("ball7s", map7);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("sevenStar/bySelf");
			logger.info("LotterySevenStarController/bySelf七星彩自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotterySevenStarController/bySelf七星彩自选参数验证通过");
		Map<String, String> selfBetMap = LotterySevenStarUtil.getSelfBetMap(ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s);
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
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("ball4s", map4);
			modelAndView.addObject("ball5s", map5);
			modelAndView.addObject("ball6s", map6);
			modelAndView.addObject("ball7s", map7);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("sevenStar/bySelf");
			logger.info("LotterySevenStarController/bySelf七星彩自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 七星彩随机自选
	 * @param ballNumber
	 * @param ballType
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param ball4s
	 * @param ball5s
	 * @param ball6s
	 * @param ball7s
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
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="ball4",required=false)List<String> ball4s,
			@RequestParam(value="ball5",required=false)List<String> ball5s,
			@RequestParam(value="ball6",required=false)List<String> ball6s,
			@RequestParam(value="ball7",required=false)List<String> ball7s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotterySevenStarController/byAutoSelf七星彩随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateSevenStarUtil.validateByAutoSelf(ballNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		Map<String, String> map4 = new HashMap<String, String>();
		Map<String, String> map5 = new HashMap<String, String>();
		Map<String, String> map6 = new HashMap<String, String>();
		Map<String, String> map7 = new HashMap<String, String>();
		if(ball1s!=null){
			for (String ball1 : ball1s) {
				map1.put("ball1_"+ball1,ball1);
			}
		}
		if(ball2s!=null){
			for (String ball2 : ball2s) {
				map2.put("ball2_"+ball2,ball2);
			}
		}
		if(ball3s!=null){
			for (String ball3 : ball3s) {
				map3.put("ball3_"+ball3,ball3);
			}
		}
		if(ball4s!=null){
			for (String ball4 : ball4s) {
				map4.put("ball4_"+ball4,ball4);
			}
		}
		if(ball5s!=null){
			for (String ball5 : ball5s) {
				map5.put("ball5_"+ball5,ball5);
			}
		}
		if(ball6s!=null){
			for (String ball6 : ball6s) {
				map6.put("ball6_"+ball6,ball6);
			}
		}
		if(ball7s!=null){
			for (String ball7 : ball7s) {
				map7.put("ball7_"+ball7,ball7);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_QXC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("ball4s", map4);
			modelAndView.addObject("ball5s", map5);
			modelAndView.addObject("ball6s", map6);
			modelAndView.addObject("ball7s", map7);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("sevenStar/bySelf");
			logger.info("LotterySevenStarController/byAutoSelf七星彩随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotterySevenStarController/byAutoSelf七星彩随机自选参数验证通过");
		if("1".equals(ballType)){
			modelAndView.addObject("ballNumber1", ballNumber);
			ball1s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map1.clear();
			if(ball1s!=null){
				for (String ball1 : ball1s) {
					map1.put("ball1_"+ball1,ball1);
				}
			}
			
		}
		if("2".equals(ballType)){
			modelAndView.addObject("ballNumber2", ballNumber);
			ball2s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map2.clear();
			if(ball2s!=null){
				for (String ball2 : ball2s) {
					map2.put("ball2_"+ball2,ball2);
				}
			}
			
		}
		if("3".equals(ballType)){
			modelAndView.addObject("ballNumber3", ballNumber);
			ball3s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map3.clear();
			if(ball3s!=null){
				for (String ball3 : ball3s) {
					map3.put("ball3_"+ball3,ball3);
				}
			}
			
		}
		if("4".equals(ballType)){
			modelAndView.addObject("ballNumber4", ballNumber);
			ball4s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map4.clear();
			if(ball4s!=null){
				for (String ball4 : ball4s) {
					map4.put("ball4_"+ball4,ball4);
				}
			}
			
		}
		if("5".equals(ballType)){
			modelAndView.addObject("ballNumber5", ballNumber);
			ball5s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map5.clear();
			if(ball5s!=null){
				for (String ball5 : ball5s) {
					map5.put("ball5_"+ball5,ball5);
				}
			}
			
		}
		if("6".equals(ballType)){
			modelAndView.addObject("ballNumber6", ballNumber);
			ball6s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map6.clear();
			if(ball6s!=null){
				for (String ball6 : ball6s) {
					map6.put("ball6_"+ball6,ball6);
				}
			}
			
		}
		if("7".equals(ballType)){
			modelAndView.addObject("ballNumber7", ballNumber);
			ball7s = LotterySevenStarUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			map7.clear();
			if(ball7s!=null){
				for (String ball7 : ball7s) {
					map7.put("ball7_"+ball7,ball7);
				}
			}
			
		}
		
		modelAndView.addObject("ball1s", map1);
		modelAndView.addObject("ball2s", map2);
		modelAndView.addObject("ball3s", map3);
		modelAndView.addObject("ball4s", map4);
		modelAndView.addObject("ball5s", map5);
		modelAndView.addObject("ball6s", map6);
		modelAndView.addObject("ball7s", map7);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("sevenStar/bySelf");
		return modelAndView;
	}
	/**
	 * 七星彩机选投注
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
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotterySevenStarController/byAutoCode七星彩机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateSevenStarUtil.validateByAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_QXC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("sevenStar/byAuto");
			logger.info("LotterySevenStarController/byAutoCode七星彩机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotterySevenStarController/byAutoCode七星彩机选参数验证通过");
		Map<String, String> autoBetMap = LotterySevenStarUtil.getAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("sevenStar/byAuto");
		return modelAndView;
	}
	
	@RequestMapping("/byAutoBet")
	public ModelAndView byAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotterySevenStarController/byAutoBet七星彩机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateSevenStarUtil.validateByAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_QXC);
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
			modelAndView.setViewName("sevenStar/byAuto");
			logger.info("LotterySevenStarController/byAutoBet七星彩机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotterySevenStarController/byAutoBet七星彩机选参数验证通过");
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
			modelAndView.setViewName("sevenStar/byAuto");
			logger.info("LotterySevenStarController/byAutoBet七星彩机选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
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
			@RequestParam(value="ballNumber1",defaultValue="")String ballNumber1,
			@RequestParam(value="ballNumber2",defaultValue="")String ballNumber2,
			@RequestParam(value="ballNumber3",defaultValue="")String ballNumber3,
			@RequestParam(value="ballNumber4",defaultValue="")String ballNumber4,
			@RequestParam(value="ballNumber5",defaultValue="")String ballNumber5,
			@RequestParam(value="ballNumber6",defaultValue="")String ballNumber6,
			@RequestParam(value="ballNumber7",defaultValue="")String ballNumber7,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="ball4",required=false)List<String> ball4s,
			@RequestParam(value="ball5",required=false)List<String> ball5s,
			@RequestParam(value="ball6",required=false)List<String> ball6s,
			@RequestParam(value="ball7",required=false)List<String> ball7s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01009")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选一位码".equals(submitType)) return byAutoSelf(ballNumber1, "1", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选二位码".equals(submitType)) return byAutoSelf(ballNumber2, "2", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选三位码".equals(submitType)) return byAutoSelf(ballNumber3, "3", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选四位码".equals(submitType)) return byAutoSelf(ballNumber4, "4", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选五位码".equals(submitType)) return byAutoSelf(ballNumber5, "5", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选六位码".equals(submitType)) return byAutoSelf(ballNumber6, "6", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选七位码".equals(submitType)) return byAutoSelf(ballNumber7, "7", ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);

		if("提交投注".equals(submitType)) return bySelf(ball1s, ball2s, ball3s, ball4s, ball5s, ball6s, ball7s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
}
