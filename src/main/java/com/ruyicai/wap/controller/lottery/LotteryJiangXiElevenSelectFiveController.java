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
import com.ruyicai.wap.util.bet.LotteryJiangXiElevenSelectFiveUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateJiangXiElevenSelectFiveUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/jiangXiElevenSelectFive")
public class LotteryJiangXiElevenSelectFiveController {
	Logger logger = Logger.getLogger(LotteryJiangXiElevenSelectFiveController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 江西11选5任选自选
	 * @param balls
	 * @param ballType
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byOptionSelf")
	public ModelAndView byOptionSelf(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionSelf江西11选5任选参数beiShu:"+beiShu+",ballType:"+ballType
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByOptionSelf(balls, ballType, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionSelfViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byOptionSelf江西11选5任选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionSelf江西11选5任选参数验证通过");
		Map<String, String> selfBetMap = LotteryJiangXiElevenSelectFiveUtil.getOptionSelfBetMap(balls, ballType);
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
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getOptionSelfViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byOptionSelf江西11选5任选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}

	/**
	 * 江西11选5随机任选自选
	 * @param ballNumber
	 * @param ballType
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	public ModelAndView byAutoOptionSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoOptionSelf江西11选5随机任选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByAutoOptionSelf(ballNumber, ballType);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}

		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionSelfViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byAutoOptionSelf江西11选5随机任选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoOptionSelf江西11选5随机任选参数验证通过");
		balls = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
		map.clear();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		modelAndView.addObject("balls", map);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName(getOptionSelfViewName(ballType));
		return modelAndView;
	}
	@RequestMapping("/byOptionAutoCode")
	public ModelAndView byOptionAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAutoCode江西11选5任选机选参数autoZhuShu:"+autoZhuShu+",ballType:"+ballType+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByOptionAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("ballType", ballType);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionAutoViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAutoCode江西11选5任选机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAutoCode江西11选5任选机选参数验证通过");
		Map<String, String> optionAutoBetMap = LotteryJiangXiElevenSelectFiveUtil.getOptionAutoBetMap(autoZhuShu, ballType);
		String betCode = optionAutoBetMap.get("betCode");
		String betCodeView = optionAutoBetMap.get("betCodeView");
		String playType = optionAutoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("ballType", ballType);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName(getOptionAutoViewName(ballType));
		return modelAndView;
		
	}
	@RequestMapping("/byOptionAutoBet")
	public ModelAndView byOptionAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAutoBet江西11选5任选机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByOptionAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("playType", playType);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("autoZhuShu", zhuShu);
			modelAndView.addObject("ballType", ballType);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionAutoViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAutoBet江西11选5任选机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAuto江西11选5任选机选参数验证通过");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("playType", playType);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("autoZhuShu", zhuShu);
			modelAndView.addObject("ballType", ballType);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getOptionAutoViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byOptionAuto江西11选5任选机选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}
	@RequestMapping("/byOptionAuto")
	public ModelAndView byOptionAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byOptionAutoCode(autoZhuShu, ballType, lotNo, batchCode, betType, request);
		if("提交投注".equals(submitType)) return byOptionAutoBet(zhuShu, ballType, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
		
	}
	
	
	/**
	 * 江西11选5胆拖
	 * @param dballs
	 * @param tballs
	 * @param ballType
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byDanTuo")
	public ModelAndView byDanTuo(
			@RequestParam(value="dball",required=false)List<String> dballs,
			@RequestParam(value="tball",required=false)List<String> tballs,
			@RequestParam(value="ballType",defaultValue="1")String ballType,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byDanTuo江西11选5胆拖参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByDanTuo(dballs, tballs, ballType, beiShu, addNumber);
		Map<String, String> dmap = new HashMap<String, String>();
		Map<String, String> tmap = new HashMap<String, String>();
		if(dballs!=null){
			for (String dball : dballs) {
				dmap.put("dball_"+dball, dball);
			}
		}
		if(tballs!=null){
			for (String tball : tballs) {
				tmap.put("tball_"+tball,tball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("dballs", dmap);
			modelAndView.addObject("tballs", tmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getDanTuoViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byDanTuo江西11选5胆拖参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byDanTuo江西11选5胆拖参数验证通过");
		Map<String, String> danTuoMap = LotteryJiangXiElevenSelectFiveUtil.getDanTuoBetMap(dballs, tballs, ballType);
		String betCode = danTuoMap.get("betCode");
		String betCodeView = danTuoMap.get("betCodeView");
		String zhuShu = danTuoMap.get("zhuShu");
		String playType = danTuoMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("dballs", dmap);
			modelAndView.addObject("tballs", tmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getDanTuoViewName(ballType));
			logger.info("LotteryJiangXiElevenSelectFiveController/byDanTuo江西11选5胆拖参数金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}
	/**
	 * 江西11选5前二组选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byForwardTwoGroupSelect")
	public ModelAndView byForwardTwoGroupSelect(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoGroupSelect江西11选5前二组选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByForwardTwoGroupSelect(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoGroupSelect江西11选5前二组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoGroupSelect江西11选5前二组选参数验证通过");
		Map<String, String> groupBetMap = LotteryJiangXiElevenSelectFiveUtil.getGroupBetMap(balls, "Z2");
		String betCode = groupBetMap.get("betCode");
		String betCodeView = groupBetMap.get("betCodeView");
		String zhuShu = groupBetMap.get("zhuShu");
		String playType = groupBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoGroupSelect江西11选5前二组选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}
	/**
	 * 江西11选5随机前二组选
	 * @param ballNumber
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoForwardTwoGroupSelect")
	public ModelAndView byAutoForwardTwoGroupSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoGroupSelect江西11选5随机前二组选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByAutoForwardTwoGroupSelect(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoGroupSelect江西11选5随机前二组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoGroupSelect江西11选5随机前二组选参数验证通过");
		balls = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
		map.clear();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}	
		modelAndView.addObject("balls", map);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelect");
		return modelAndView;
	}
	/**
	 * 江西11选5前三组选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byForwardThreeGroupSelect(")
	public ModelAndView byForwardThreeGroupSelect(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeGroupSelect江西11选5前三组选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByForwardThreeGroupSelect(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeGroupSelect江西11选5前三组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeGroupSelect江西11选5前三组选参数验证通过");
		Map<String, String> groupBetMap = LotteryJiangXiElevenSelectFiveUtil.getGroupBetMap(balls, "Z3");
		String betCode = groupBetMap.get("betCode");
		String betCodeView = groupBetMap.get("betCodeView");
		String zhuShu = groupBetMap.get("zhuShu");
		String playType = groupBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeGroupSelect江西11选5前三组选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");

		return modelAndView;
		
	}
	/**
	 * 江西11选5随机前三组选
	 * @param ballNumber
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoForwardThreeGroupSelect")
	public ModelAndView byAutoForwardThreeGroupSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeGroupSelect江西11选5随机前三组选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByAutoForwardThreeGroupSelect(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeGroupSelect江西11选5随机前三组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeGroupSelect江西11选5随机前三组选参数验证通过");
		balls = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
		map.clear();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}	
		modelAndView.addObject("balls", map);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelect");
		return modelAndView;
	}
	/**
	 * 江西11选5前二直选
	 * @param ball1s
	 * @param ball2s
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byForwardTwoDirectSelect")
	public ModelAndView byForwardTwoDirectSelect(
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoDirectSelect江西11选5前二直选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByForwardTwoDirectSelect(ball1s, ball2s, beiShu, addNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoDirectSelect江西11选5前二直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoDirectSelect江西11选5前二直选参数验证通过");
		Map<String, String> forwardTwoDirectSelectBetMapBetMap = LotteryJiangXiElevenSelectFiveUtil.getForwardTwoDirectSelectBetMap(ball1s, ball2s);
		String betCode = forwardTwoDirectSelectBetMapBetMap.get("betCode");
		String betCodeView = forwardTwoDirectSelectBetMapBetMap.get("betCodeView");
		String zhuShu = forwardTwoDirectSelectBetMapBetMap.get("zhuShu");
		String playType = forwardTwoDirectSelectBetMapBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardTwoDirectSelect江西11选5前二直选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}
	/**
	 * 江西11选5随机前二直选
	 * @param ballNumber
	 * @param ballType
	 * @param ball1s
	 * @param ball2s
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoForwardTwoDirectSelect")
	public ModelAndView byAutoForwardTwoDirectSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoDirectSelect江西11选5随机前二直选参数ballNumber:"+ballNumber+",ballType:"+ballType+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByAutoForwardTwoDirectSelect(ballNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("balls2", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoDirectSelect江西11选5随机前二直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardTwoDirectSelect江西11选5随机前二直选参数验证通过");
		if("1".equals(ballType)){
			ball1s = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
			map1.clear();
			if(ball1s!=null){
				for (String ball1 : ball1s) {
					map1.put("ball1_"+ball1,ball1);
				}
			}	
		}
		if("2".equals(ballType)){
			ball2s = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
			map2.clear();
			if(ball2s!=null){
				for (String ball2 : ball2s) {
					map2.put("ball2_"+ball2,ball2);
				}
			}	
		}
		
		modelAndView.addObject("ball1s", map1);
		modelAndView.addObject("ball2s", map2);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoDirectSelect");
		return modelAndView;
	}
	/**
	 * 江西11选5前三直选
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byForwardThreeDirectSelect")
	public ModelAndView byForwardThreeDirectSelect(
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeDirectSelect江西11选5前三直选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByForwardThreeDirectSelect(ball1s, ball2s, ball3s, beiShu, addNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeDirectSelect江西11选5前三直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeDirectSelect江西11选5前三直选参数验证通过");
		Map<String, String> forwardThreeDirectSelectBetMap = LotteryJiangXiElevenSelectFiveUtil.getForwardThreeDirectSelectBetMap(ball1s, ball2s, ball3s);
		String betCode = forwardThreeDirectSelectBetMap.get("betCode");
		String betCodeView = forwardThreeDirectSelectBetMap.get("betCodeView");
		String zhuShu = forwardThreeDirectSelectBetMap.get("zhuShu");
		String playType = forwardThreeDirectSelectBetMap.get("playType");
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
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byForwardThreeDirectSelect江西11选5前三直选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("gaoPinBetDetail");
		return modelAndView;
		
	}
	/**
	 * 江西11选5随机前三直选
	 * @param ballNumber
	 * @param ballType
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byAutoForwardThreeDirectSelect")
	public ModelAndView byAutoForwardThreeDirectSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeDirectSelect江西11选5随机前三直选参数ballNumber:"+ballNumber+",ballType:"+ballType+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateJiangXiElevenSelectFiveUtil.validateByAutoForwardThreeDirectSelect(ballNumber);
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeDirectSelect");
			logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeDirectSelect江西11选5随机前三直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryJiangXiElevenSelectFiveController/byAutoForwardThreeDirectSelect江西11选5随机前三直选参数验证通过");
		if("1".equals(ballType)){
			ball1s = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
			map1.clear();
			if(ball1s!=null){
				for (String ball1 : ball1s) {
					map1.put("ball1_"+ball1,ball1);
				}
			}	
		}
		if("2".equals(ballType)){
			ball2s = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
			map2.clear();
			if(ball2s!=null){
				for (String ball2 : ball2s) {
					map2.put("ball2_"+ball2,ball2);
				}
			}	
		}
		if("3".equals(ballType)){
			ball3s = LotteryJiangXiElevenSelectFiveUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 11);
			map3.clear();
			if(ball3s!=null){
				for (String ball3 : ball3s) {
					map3.put("ball3_"+ball3,ball3);
				}
			}	
		}
		modelAndView.addObject("ball1s", map1);
		modelAndView.addObject("ball2s", map2);
		modelAndView.addObject("ball3s", map3);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeDirectSelect");
		return modelAndView;
	}
	public String getOptionSelfViewName(String ballType){
		if("R1".equals(ballType)) return "jiangXiElevenSelectFive/byOptionOneSelf";
		if("R2".equals(ballType)) return "jiangXiElevenSelectFive/byOptionTwoSelf";
		if("R3".equals(ballType)) return "jiangXiElevenSelectFive/byOptionThreeSelf";
		if("R4".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFourSelf";
		if("R5".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFiveSelf";
		if("R6".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSixSelf";
		if("R7".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSevenSelf";
		if("R8".equals(ballType)) return "jiangXiElevenSelectFive/byOptionEightSelf";
		return "";
	}
	public String getOptionAutoViewName(String ballType){
		if("R1".equals(ballType)) return "jiangXiElevenSelectFive/byOptionOneAuto";
		if("R2".equals(ballType)) return "jiangXiElevenSelectFive/byOptionTwoAuto";
		if("R3".equals(ballType)) return "jiangXiElevenSelectFive/byOptionThreeAuto";
		if("R4".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFourAuto";
		if("R5".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFiveAuto";
		if("R6".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSixAuto";
		if("R7".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSevenAuto";
		if("R8".equals(ballType)) return "jiangXiElevenSelectFive/byOptionEightAuto";
		return "";
	}
	public String getDanTuoViewName(String ballType){
		if("R2".equals(ballType)) return "jiangXiElevenSelectFive/byOptionTwoDanTuo";
		if("R3".equals(ballType)) return "jiangXiElevenSelectFive/byOptionThreeDanTuo";
		if("R4".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFourDanTuo";
		if("R5".equals(ballType)) return "jiangXiElevenSelectFive/byOptionFiveDanTuo";
		if("R6".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSixDanTuo";
		if("R7".equals(ballType)) return "jiangXiElevenSelectFive/byOptionSevenDanTuo";
		if("R8".equals(ballType)) return "jiangXiElevenSelectFive/byOptionEightDanTuo";
		if("Z2".equals(ballType)) return "jiangXiElevenSelectFive/byForwardTwoGroupSelectDanTuo";
		if("Z3".equals(ballType)) return "jiangXiElevenSelectFive/byForwardThreeGroupSelectDanTuo";
		return "";
	}
	@RequestMapping("/byOptionToSelf")
	public ModelAndView byOptionToSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byOptionSelf(balls, ballType, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoOptionSelf(ballNumber, ballType, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
	@RequestMapping("/byForwardTwoGroupToSelect")
	public ModelAndView byForwardTwoGroupToSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byForwardTwoGroupSelect(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoForwardTwoGroupSelect(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;


	}
	@RequestMapping("/byForwardThreeGroupToSelect")
	public ModelAndView byForwardThreeGroupToSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byForwardThreeGroupSelect(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoForwardThreeGroupSelect(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;


	}
	@RequestMapping("/byForwardTwoDirectToSelect")
	public ModelAndView byForwardTwoDirectToSelect(
			@RequestParam(value="ballNumber1",defaultValue="")String ballNumber1,
			@RequestParam(value="ballNumber2",defaultValue="")String ballNumber2,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ballNumber1", ballNumber1);
		modelAndView.addObject("ballNumber2", ballNumber2);
		if("机选一位码".equals(submitType)) return byAutoForwardTwoDirectSelect(ballNumber1, "1", ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选二位码".equals(submitType)) return byAutoForwardTwoDirectSelect(ballNumber2, "2", ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("提交投注".equals(submitType)) return byForwardTwoDirectSelect(ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		return modelAndView;
	}
	
	@RequestMapping("/byForwardThreeDirectToSelect")
	public ModelAndView byForwardThreeDirectToSelect(
			@RequestParam(value="ballNumber1",defaultValue="")String ballNumber1,
			@RequestParam(value="ballNumber2",defaultValue="")String ballNumber2,
			@RequestParam(value="ballNumber3",defaultValue="")String ballNumber3,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="ball3",required=false)List<String> ball3s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01010")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ballNumber1", ballNumber1);
		modelAndView.addObject("ballNumber2", ballNumber2);
		modelAndView.addObject("ballNumber3", ballNumber3);
		if("机选一位码".equals(submitType)) return byAutoForwardThreeDirectSelect(ballNumber1, "1", ball1s, ball2s, ball3s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选二位码".equals(submitType)) return byAutoForwardThreeDirectSelect(ballNumber2, "2", ball1s, ball2s, ball3s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选三位码".equals(submitType)) return byAutoForwardThreeDirectSelect(ballNumber3, "3", ball1s, ball2s, ball3s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("提交投注".equals(submitType)) return byForwardThreeDirectSelect(ball1s, ball2s, ball3s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		return modelAndView;
	}

}
