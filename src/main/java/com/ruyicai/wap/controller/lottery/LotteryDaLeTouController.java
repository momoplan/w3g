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
import com.ruyicai.wap.util.bet.LotteryDaLeTouUtil;
import com.ruyicai.wap.util.bet.LotteryQiLeCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateDaLeTouUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/daLeTou")
public class LotteryDaLeTouController {
	Logger logger = Logger.getLogger(LotteryDaLeTouController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 自选投注
	 * @param rballs
	 * @param bballs
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
			@RequestParam(value="rball",required=false)List<String> rballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="self")String betType,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/bySelf大乐透自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateBySelf(rballs, bballs, beiShu, addNumber);
		Map<String, String> rmap = new HashMap<String, String>();
		Map<String, String> bmap = new HashMap<String, String>();
		if(rballs!=null){
			for (String rball : rballs) {
				rmap.put("rball_"+rball, rball);
			}
		}
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball, bball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("rballs", rmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("betType", betType);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/bySelf");
			logger.info("LotteryDaLeTouController/bySelf大乐透自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryDaLeTouController/bySelf大乐透自选参数验证通过");
		Map<String, String> selfBetMap = LotteryDaLeTouUtil.getSelfBetMap(rballs, bballs);
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
			modelAndView.addObject("rballs", rmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("betType", betType);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("daLeTou/bySelf");
			logger.info("LotteryDaLeTouController/bySelf大乐透自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 大乐透随机自选
	 * @param ballNumber
	 * @param ballType
	 * @param rballs
	 * @param bballs
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
			@RequestParam(value="rball",required=false)List<String> rballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byAutoSelf大乐透随机自选参数ballNumber:"+ballNumber+",ballType:"+ballType+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByAutoSelf(ballNumber,ballType);
		Map<String, String> rmap = new HashMap<String, String>();
		Map<String, String> bmap = new HashMap<String, String>();
		if(rballs!=null){
			for (String rball : rballs) {
				rmap.put("rball_"+rball, rball);
			}
		}
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball, bball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("rballs", rmap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/bySelf");
			logger.info("LotteryDaLeTouController/byAutoSelf大乐透随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("LotteryDaLeTouController/byAutoSelf大乐透随机自选参数验证通过");
		if("R".equals(ballType)){
			modelAndView.addObject("rballNumber", ballNumber);
			rballs =LotteryQiLeCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),35);
			rmap.clear();
			if(rballs!=null){
				for (String rball : rballs) {
					rmap.put("rball_"+rball, rball);
				}
			}
		}
		if("B".equals(ballType)){
			modelAndView.addObject("bballNumber", ballNumber);
			bballs =LotteryQiLeCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),12);
			bmap.clear();
			if(bballs!=null){
				for (String bball : bballs) {
					bmap.put("bball_"+bball, bball);
				}
			}
		}
		modelAndView.addObject("rballs", rmap);
		modelAndView.addObject("bballs", bmap);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("daLeTou/bySelf");
		return modelAndView;
	}
	@RequestMapping("/byAutoCode")
	public ModelAndView byAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byAutoCode大乐透机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		modelAndView.addObject("betType", betType);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/byAuto");
			logger.info("LotteryDaLeTouController/byAutoCode大乐透机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryDaLeTouController/byAutoCode大乐透机选参数验证通过");
		Map<String, String> autoBetMap = LotteryDaLeTouUtil.getAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("daLeTou/byAuto");
		return modelAndView;
		
	}
	/**
	 * 大乐透机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param betCode
	 * @param betCodeView
	 * @param playType
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param betType
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
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byAutoBet大乐透机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode
				+",betCode:"+betCode+",betCodeView:"+betCodeView+",playType:"+playType);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
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
			modelAndView.setViewName("daLeTou/byAuto");
			logger.info("LotteryDaLeTouController/byAutoBet大乐透机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryDaLeTouController/byAutoBet大乐透机选参数验证通过");
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
			modelAndView.setViewName("daLeTou/byAuto");
			logger.info("LotteryDaLeTouController/byAutoBet大乐透机选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 大乐透机选
	 * @param zhuShu
	 * @param autoZhuShu
	 * @param beiShu
	 * @param addNumber
	 * @param betCode
	 * @param betCodeView
	 * @param playType
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param submitType
	 * @param betType
	 * @param request
	 * @return
	 */
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
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
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
	
	
	/**
	 * 胆拖投注
	 * @param rdballs
	 * @param rtballs
	 * @param bdballs
	 * @param btballs
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
			@RequestParam(value="rdball",required=false)List<String> rdballs,
			@RequestParam(value="rtball",required=false)List<String> rtballs,
			@RequestParam(value="bdball",required=false)List<String> bdballs,
			@RequestParam(value="btball",required=false)List<String> btballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byDanTuo大乐透胆拖参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByDanTuo(rdballs, rtballs, bdballs, btballs, beiShu, addNumber);
		Map<String, String> rdmap = new HashMap<String, String>();
		Map<String, String> rtmap = new HashMap<String, String>();
		Map<String, String> bdmap = new HashMap<String, String>();
		Map<String, String> btmap = new HashMap<String, String>();
		if(rdballs!=null){
			for (String rdball : rdballs) {
				rdmap.put("rdball_"+rdball, rdball);
			}
		}
		if(rtballs!=null){
			for (String rtball : rtballs) {
				rtmap.put("rtball_"+rtball, rtball);
			}
		}
		if(bdballs!=null){
			for (String bdball : bdballs) {
				bdmap.put("bdball_"+bdball, bdball);
			}
		}
		if(btballs!=null){
			for (String btball : btballs) {
				btmap.put("btball_"+btball, btball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("rdballs", rdmap);
			modelAndView.addObject("rtballs", rtmap);
			modelAndView.addObject("bdballs", bdmap);
			modelAndView.addObject("btballs", btmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/byDanTuo");
			logger.info("LotteryDaLeTouController/byDanTuo大乐透胆拖参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryDaLeTouController/byDanTuo大乐透胆拖参数验证通过");
		Map<String, String> danTuoBetMap = LotteryDaLeTouUtil.getDanTuoBetMap(rdballs, rtballs, bdballs, btballs);
		String betCode = danTuoBetMap.get("betCode");
		String betCodeView = danTuoBetMap.get("betCodeView");
		String zhuShu = danTuoBetMap.get("zhuShu");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("rdballs", rdmap);
			modelAndView.addObject("rtballs", rtmap);
			modelAndView.addObject("bdballs", bdmap);
			modelAndView.addObject("btballs", btmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("daLeTou/byDanTuo");
			logger.info("LotteryDaLeTouController/byDanTuo大乐透胆拖金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("betDetail");
		return modelAndView;
	}
	@RequestMapping("/byShengXiaoLe")
	public ModelAndView byShengXiaoLe(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byShengXiaoLe大乐透生肖乐参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByShengXiaoLe(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/byShengXiaoLe");
			logger.info("LotteryDaLeTouController/byShengXiaoLe大乐透生肖乐参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryDaLeTouController/byShengXiaoLe大乐透生肖乐参数验证通过");
		Map<String, String> selfBetMap = LotteryDaLeTouUtil.getShengXiaoLeBetMap(balls);
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
			modelAndView.setViewName("daLeTou/byShengXiaoLe");
			logger.info("LotteryDaLeTouController/byShengXiaoLe大乐透生肖乐金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 随机生肖乐
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
	@RequestMapping("/byAutoShengXiaoLe")
	public ModelAndView byAutoShengXiaoLe(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryDaLeTouController/byAutoShengXiaoLe大乐透随机生肖乐参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateDaLeTouUtil.validateByAutoShengXiaoLe(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("daLeTou/byShengXiaoLe");
			logger.info("LotteryDaLeTouController/byAutoShengXiaoLe大乐透随机生肖乐参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("LotteryDaLeTouController/byAutoShengXiaoLe大乐透随机生肖乐参数验证通过");
		balls =LotteryQiLeCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),12);
		map.clear();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		modelAndView.addObject("balls", map);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("daLeTou/byShengXiaoLe");
		return modelAndView;
	}
	@RequestMapping("/byLottery")
	public ModelAndView byLottery(
			@RequestParam(value="rballNumber",defaultValue="")String rballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="rball",required=false)List<String> rballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="self")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选前区".equals(submitType)) return byAutoSelf(rballNumber, "R", rballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选后区".equals(submitType)) return byAutoSelf(bballNumber, "B", rballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);

		if("提交投注".equals(submitType)) return bySelf(rballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		return modelAndView;
	}
	@RequestMapping("/byShengXiaoLeLottery")
	public ModelAndView byShengXiaoLeLottery(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01001")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="shengXiaoLe")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byAutoShengXiaoLe(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byShengXiaoLe(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
}
