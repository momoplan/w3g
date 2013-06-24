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
import com.ruyicai.wap.util.bet.LotteryShiShiCaiUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateShiShiCaiUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/shiShiCai")
public class LotteryShiShiCaiController {
	Logger logger = Logger.getLogger(LotteryShiShiCaiController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 时时彩1星自选
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byOneStarSelf")
	public ModelAndView byOneStarSelf(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byOneStarSelf时时彩1星自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByOneStarSelf(gballs, beiShu, addNumber);
		Map<String, String> gmap = new HashMap<String, String>();
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byOneStarSelf");
			logger.info("LotteryShiShiCaiController/byOneStarSelf时时彩1星自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byOneStarSelf时时彩1星自选参数验证通过");
		Map<String, String> oneStarSelfBetMap = LotteryShiShiCaiUtil.getOneStarSelfBetMap(gballs);
		String betCode = oneStarSelfBetMap.get("betCode");
		String betCodeView = oneStarSelfBetMap.get("betCodeView");
		String zhuShu = oneStarSelfBetMap.get("zhuShu");
		String playType = oneStarSelfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("shiShiCai/byOneStarSelf");
			logger.info("LotteryShiShiCaiController/byOneStarSelf时时彩1星自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩1星随机自选
	 * @param ballNumber
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byOneStarAutoSelf")
	public ModelAndView byOneStarAutoSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byOneStarAutoSelf时时彩1星随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByOneStarAutoSelf(ballNumber);
		Map<String, String> gmap = new HashMap<String, String>();
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byOneStarSelf");
			logger.info("LotteryShiShiCaiController/byOneStarAutoSelf时时彩1星随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byOneStarAutoSelf时时彩1星随机自选参数验证通过");
		gballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		gmap.clear();
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		modelAndView.addObject("gballs", gmap);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byOneStarSelf");
		return modelAndView;
	}
	@RequestMapping("/byOneStarAutoCode")
	public ModelAndView byOneStarAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="oneStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byOneStarAutoCode时时彩一星机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByOneStarAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byOneStarAuto");
			logger.info("LotteryShiShiCaiController/byOneStarAutoCode时时彩一星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byOneStarAutoCode时时彩一星参数验证通过");
		Map<String, String> autoBetMap = LotteryShiShiCaiUtil.getOneStarAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("shiShiCai/byOneStarAuto");
		return modelAndView;
	}
	
	/**
	 * 时时彩一星机选投注
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
	@RequestMapping("/byOneStarAutoBet")
	public ModelAndView byOneStarAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="oneStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byOneStarAutoBet时时彩一星机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByOneStarAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byOneStarAuto");
			logger.info("LotteryShiShiCaiController/byOneStarAutoBet时时彩一星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byOneStarAutoBet时时彩一星参数验证通过");
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
			modelAndView.setViewName("shiShiCai/byOneStarAuto");
			logger.info("LotteryShiShiCaiController/byOneStarAutoBet时时彩一星金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	@RequestMapping("/byOneStarAuto")
	public ModelAndView byOneStarAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="oneStarAuto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byOneStarAutoCode(autoZhuShu, lotNo, batchCode,betType, request);
		if("提交投注".equals(submitType)) return byOneStarAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType,request);
		return modelAndView;
	}
	/**
	 * 时时彩2星自选
	 * @param gballs
	 * @param sballs
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byTwoStarDirect")
	public ModelAndView byTwoStarDirect(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byTwoStarDirect时时彩2星自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarDirect(gballs, sballs, beiShu, addNumber);
		Map<String, String> gmap = new HashMap<String, String>();
		Map<String, String> smap = new HashMap<String, String>();
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarDirect");
			logger.info("LotteryShiShiCaiController/byTwoStarDirect时时彩2星自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarDirect时时彩2星自选参数验证通过");
		Map<String, String> twoStarSelfBetMap = LotteryShiShiCaiUtil.getTwoStarDirectBetMap(gballs, sballs);
		String betCode = twoStarSelfBetMap.get("betCode");
		String betCodeView = twoStarSelfBetMap.get("betCodeView");
		String zhuShu = twoStarSelfBetMap.get("zhuShu");
		String playType = twoStarSelfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("shiShiCai/byTwoStarDirect");
			logger.info("LotteryShiShiCaiController/byTwoStarDirect时时彩2星自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩2星随机自选
	 * @param ballNumber
	 * @param ballType
	 * @param gballs
	 * @param sballs
	 * @param beiShu
	 * @param addNumber
	 * @param oneAmount
	 * @param lotNo
	 * @param batchCode
	 * @param prizeend
	 * @param request
	 * @return
	 */
	@RequestMapping("/byTwoStarAutoDirect")
	public ModelAndView byTwoStarAutoDirect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byTwoStarAutoDirect时时彩2星随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarAutoDirect(ballNumber);
		Map<String, String> smap = new HashMap<String, String>();
		Map<String, String> gmap = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarDirect");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoDirect时时彩2星随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarAutoDirect时时彩2星随机自选参数验证通过");
		if("S".equals(ballType)){
			sballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			smap.clear();
			if(sballs!=null){
				for (String sball : sballs) {
					smap.put("sball_"+sball, sball);
				}
			}
		}
		if("G".equals(ballType)){
			gballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			gmap.clear();
			if(gballs!=null){
				for (String gball : gballs) {
					gmap.put("gball_"+gball, gball);
				}
			}
			
		}		
		modelAndView.addObject("sballs", smap);
		modelAndView.addObject("gballs", gmap);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byTwoStarDirect");
		return modelAndView;
	}
	@RequestMapping("/byTwoStarAutoCode")
	public ModelAndView byTwoStarAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="twoStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarAutoCode时时二星机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarAuto");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoCode时时彩二星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarAutoCode时时彩二星参数验证通过");
		Map<String, String> autoBetMap = LotteryShiShiCaiUtil.getTwoStarAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("shiShiCai/byTwoStarAuto");
		return modelAndView;
	}
	
	/**
	 * 时时彩二星机选投注
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
	@RequestMapping("/byTwoStarAutoBet")
	public ModelAndView byTwoStarAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="twoStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarAutoBet时时彩二星机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byTwoStarAuto");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoBet时时彩二星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarAutoBet时时彩二星参数验证通过");
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
			modelAndView.setViewName("shiShiCai/byTwoStarAuto");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoBet时时彩二星金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	@RequestMapping("/byTwoStarAuto")
	public ModelAndView byTwoStarAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="twoStarAuto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byTwoStarAutoCode(autoZhuShu, lotNo, batchCode,betType, request);
		if("提交投注".equals(submitType)) return byTwoStarAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType,request);
		return modelAndView;
	}
	/**
	 * 时时彩2星组选
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
	@RequestMapping("/byTwoStarGroup")
	public ModelAndView byTwoStarGroup(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarGroup时时彩2星组选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarGroup(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarGroup");
			logger.info("LotteryShiShiCaiController/byTwoStarGroup时时彩2星组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarGroup时时彩2星组选参数验证通过");
		Map<String, String> twoStarGroupBetMap = LotteryShiShiCaiUtil.getTwoStarGroupBetMap(balls);
		String betCode = twoStarGroupBetMap.get("betCode");
		String betCodeView = twoStarGroupBetMap.get("betCodeView");
		String zhuShu = twoStarGroupBetMap.get("zhuShu");
		String playType = twoStarGroupBetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byTwoStarGroup");
			logger.info("LotteryShiShiCaiController/byTwoStarGroup时时彩2星组选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩2星随机组选
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
	@RequestMapping("/byTwoStarAutoGroup")
	public ModelAndView byTwoStarAutoGroup(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarAutoGroup时时彩2星随机组选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarAutoGroup(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarGroup");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoGroup时时彩2星随机组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarAutoGroup时时彩2星随机组选参数验证通过");
		balls = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byTwoStarGroup");
		return modelAndView;
	}
	/**
	 * 时时彩2星和值
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
	@RequestMapping("/byTwoStarHeZhi")
	public ModelAndView byTwoStarHeZhi(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarHeZhi时时彩2星和值参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarHeZhi(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarHeZhi");
			logger.info("LotteryShiShiCaiController/byTwoStarHeZhi时时彩2星和值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarHeZhi时时彩2星和值参数验证通过");
		Map<String, String> twoStarHeZhiBetMap = LotteryShiShiCaiUtil.getTwoStarHeZhiBetMap(balls);
		String betCode = twoStarHeZhiBetMap.get("betCode");
		String betCodeView = twoStarHeZhiBetMap.get("betCodeView");
		String zhuShu = twoStarHeZhiBetMap.get("zhuShu");
		String playType = twoStarHeZhiBetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byTwoStarHeZhi");
			logger.info("LotteryShiShiCaiController/byTwoStarHeZhi时时彩2星和值金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩2星随机和值
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
	@RequestMapping("/byTwoStarAutoHeZhi")
	public ModelAndView byTwoStarAutoHeZhi(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byTwoStarAutoHeZhi时时彩2星随机和值参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByTwoStarAutoHeZhi(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byTwoStarHeZhi");
			logger.info("LotteryShiShiCaiController/byTwoStarAutoHeZhi时时彩2星随机和值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byTwoStarAutoHeZhi时时彩2星随机和值参数验证通过");
		balls = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 18);
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
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byTwoStarHeZhi");
		return modelAndView;
	}
	/**
	 * 时时彩3星自选
	 * @param gballs
	 * @param sballs
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
	@RequestMapping("/byThreeStarDirect")
	public ModelAndView byThreeStarDirect(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byThreeStarDirect时时彩3星自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarDirect(gballs, sballs, bballs, beiShu, addNumber);
		Map<String, String> gmap = new HashMap<String, String>();
		Map<String, String> smap = new HashMap<String, String>();
		Map<String, String> bmap = new HashMap<String, String>();
		if(gballs!=null){
			for (String gball : gballs) {
				gmap.put("gball_"+gball, gball);
			}
		}
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball, bball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarDirect");
			logger.info("LotteryShiShiCaiController/byThreeStarDirect时时彩3星自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarSelf时时彩3星自选参数验证通过");
		Map<String, String> threeStarSelfBetMap = LotteryShiShiCaiUtil.getThreeStarDirectBetMap(gballs, sballs, bballs);
		String betCode = threeStarSelfBetMap.get("betCode");
		String betCodeView = threeStarSelfBetMap.get("betCodeView");
		String zhuShu = threeStarSelfBetMap.get("zhuShu");
		String playType = threeStarSelfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("shiShiCai/byThreeStarDirect");
			logger.info("LotteryShiShiCaiController/byThreeStarDirect时时彩3星自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩3星随机自选
	 * @param ballNumber
	 * @param ballType
	 * @param gballs
	 * @param sballs
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
	@RequestMapping("/byThreeStarAutoDirect")
	public ModelAndView byThreeStarAutoDirect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byThreeStarAutoDirect时时彩3星随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarAutoDirect(ballNumber);
		Map<String, String> bmap = new HashMap<String, String>();
		Map<String, String> smap = new HashMap<String, String>();
		Map<String, String> gmap = new HashMap<String, String>();
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
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
			modelAndView.setViewName("shiShiCai/byThreeStarDirect");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoDirect时时彩3星随机自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarAutoDirect时时彩3星随机自选参数验证通过");
		if("B".equals(ballType)){
			bballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			bmap.clear();
			if(bballs!=null){
				for (String bball : bballs) {
					bmap.put("bball_"+bball,bball);
				}
			}
			
		}
		if("S".equals(ballType)){
			sballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			smap.clear();
			if(sballs!=null){
				for (String sball : sballs) {
					smap.put("sball_"+sball, sball);
				}
			}
		}
		if("G".equals(ballType)){
			gballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			gmap.clear();
			if(gballs!=null){
				for (String gball : gballs) {
					gmap.put("gball_"+gball, gball);
				}
			}
			
		}		
		modelAndView.addObject("bballs", bmap);
		modelAndView.addObject("sballs", smap);
		modelAndView.addObject("gballs", gmap);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("ballNumber", ballNumber);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byThreeStarDirect");
		return modelAndView;
	}
	@RequestMapping("/byThreeStarAutoCode")
	public ModelAndView byThreeStarAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="threeStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarAutoCode时时三星机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarAuto");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoCode时时彩三星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarAutoCode时时彩三星参数验证通过");
		Map<String, String> autoBetMap = LotteryShiShiCaiUtil.getThreeStarAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("shiShiCai/byThreeStarAuto");
		return modelAndView;
	}
	
	/**
	 * 时时彩三星机选投注
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
	@RequestMapping("/byThreeStarAutoBet")
	public ModelAndView byThreeStarAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="threeStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarAutoBet时时彩三星机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byThreeStarAuto");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoBet时时彩三星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarAutoBet时时彩三星参数验证通过");
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
			modelAndView.setViewName("shiShiCai/byThreeStarAuto");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoBet时时彩三星金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	@RequestMapping("/byThreeStarAuto")
	public ModelAndView byThreeStarAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="threeStarAuto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byThreeStarAutoCode(autoZhuShu, lotNo, batchCode,betType, request);
		if("提交投注".equals(submitType)) return byThreeStarAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType,request);
		return modelAndView;
	}
	
	/**
	 * 时时彩3星组三
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
	@RequestMapping("/byThreeStarGroup3")
	public ModelAndView byThreeStarGroup3(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarGroup3时时彩3星组3参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarGroup3(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarGroup3");
			logger.info("LotteryShiShiCaiController/byThreeStarGroup3时时彩3星组3参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarGroup3时时彩3星组3参数验证通过");
		Map<String, String> threeStarGroup3BetMap = LotteryShiShiCaiUtil.getThreeStarGroup3BetMap(balls);
		String betCode = threeStarGroup3BetMap.get("betCode");
		String betCodeView = threeStarGroup3BetMap.get("betCodeView");
		String zhuShu = threeStarGroup3BetMap.get("zhuShu");
		String playType = threeStarGroup3BetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byThreeStarGroup3");
			logger.info("LotteryShiShiCaiController/byThreeStarGroup3时时彩3星组3金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩3星随机组三
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
	@RequestMapping("/byThreeStarAutoGroup3")
	public ModelAndView byThreeStarAutoGroup3(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup3时时彩3星随机组3参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarAutoGroup3(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarGroup3");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup3时时彩3星随机组3参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup3时时彩3星随机组3参数验证通过");
		balls = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byThreeStarGroup3");
		return modelAndView;
	}
	/**
	 * 时时彩3星组六
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
	@RequestMapping("/byThreeStarGroup6")
	public ModelAndView byThreeStarGroup6(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarGroup6时时彩3星组6参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarGroup6(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarGroup6");
			logger.info("LotteryShiShiCaiController/byThreeStarGroup6时时彩3星组6参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarGroup6时时彩3星组6参数验证通过");
		Map<String, String> threeStarGroup6BetMap = LotteryShiShiCaiUtil.getThreeStarGroup6BetMap(balls);
		String betCode = threeStarGroup6BetMap.get("betCode");
		String betCodeView = threeStarGroup6BetMap.get("betCodeView");
		String zhuShu = threeStarGroup6BetMap.get("zhuShu");
		String playType = threeStarGroup6BetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byThreeStarGroup6");
			logger.info("LotteryShiShiCaiController/byThreeStarGroup6时时彩3星组6金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 时时彩3星随机组六
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
	@RequestMapping("/byThreeStarAutoGroup6")
	public ModelAndView byThreeStarAutoGroup6(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup6时时彩3星随机组6参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByThreeStarAutoGroup6(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball, ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byThreeStarGroup6");
			logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup6时时彩3星随机组6参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byThreeStarAutoGroup6时时彩3星随机组6参数验证通过");
		balls = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("shiShiCai/byThreeStarGroup6");
		return modelAndView;
	}
	/**
	 * 时时彩五星自选
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
	@RequestMapping("/byFiveStarDirect")
	public ModelAndView byFiveStarDirect(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byFiveStarDirect时时彩五星自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByFiveStarDirect(wballs, qballs, bballs, sballs, gballs, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byFiveStarDirect");
			logger.info("LotteryShiShiCaiController/byFiveStarDirect时时彩五星自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byFiveStarDirect时时彩五星自选参数验证通过");
		Map<String, String> fiveStarSelfBetMap = LotteryShiShiCaiUtil.getFiveStarDirectBetMap(gballs, sballs, bballs, qballs, wballs);
		String betCode = fiveStarSelfBetMap.get("betCode");
		String betCodeView = fiveStarSelfBetMap.get("betCodeView");
		String zhuShu = fiveStarSelfBetMap.get("zhuShu");
		String playType = fiveStarSelfBetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byFiveStarSelf");
			logger.info("LotteryShiShiCaiController/byFiveStarDirect时时彩五星自选金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;

		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);

		modelAndView.addObject("wballs", wmap);
		modelAndView.addObject("qballs", qmap);
		modelAndView.addObject("bballs", bmap);
		modelAndView.addObject("sballs", smap);
		modelAndView.addObject("gballs", gmap);
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
 * 时时彩五星随机自选
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
@RequestMapping("/byFiveStarAutoDirect")
public ModelAndView byFiveStarAutoDirect(
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
		@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
		@RequestParam(value="batchCode",defaultValue="")String batchCode,
		@RequestParam(value="prizeend",defaultValue="0")String prizeend,
		HttpServletRequest request,ModelAndView modelAndView){
	logger.info("LotteryShiShiCaiController/byFiveStarAutoDirect时时彩五星随机自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
			+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
	String validateResult = ValidateShiShiCaiUtil.validateByFiveStarAutoDirect(ballNumber);
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
	lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
		modelAndView.setViewName("shiShiCai/byFiveStarDirect");
		logger.info("LotteryShiShiCaiController/byFiveStarAutoDirect时时彩五星随机自选参数验证失败返回validateResult:"+validateResult);
		return modelAndView;

	}
	logger.info("LotteryShiShiCaiController/byFiveStarAutoDirect时时彩五星随机自选参数验证通过");
	if("W".equals(ballType)){
		wballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		wmap.clear();
		if(wballs!=null){
			for (String wball : wballs) {
				wmap.put("wball_"+wball,wball);
			}
		}
		
	}
	if("Q".equals(ballType)){
		qballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		qmap.clear();
		if(qballs!=null){
			for (String qball : qballs) {
				qmap.put("qball_"+qball,qball);
			}
		}
		
	}
	if("B".equals(ballType)){
		bballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		bmap.clear();
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball,bball);
			}
		}
		
	}
	if("S".equals(ballType)){
		sballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		smap.clear();
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
	}
	if("G".equals(ballType)){
		gballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
	modelAndView.setViewName("shiShiCai/byFiveStarDirect");
	return modelAndView;
}
	@RequestMapping("/byFiveStarAutoCode")
	public ModelAndView byFiveStarAutoCode(
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="threeStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byFiveStarAutoCode时时五星机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByFiveStarAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byFiveStarAuto");
			logger.info("LotteryShiShiCaiController/byFiveStarAutoCode时时彩五星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byFiveStarAutoCode时时彩五星参数验证通过");
		Map<String, String> autoBetMap = LotteryShiShiCaiUtil.getFiveStarAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("shiShiCai/byFiveStarAuto");
		return modelAndView;
	}
	
	/**
	 * 时时彩三星机选投注
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
	@RequestMapping("/byFiveStarAutoBet")
	public ModelAndView byFiveStarAutoBet(
			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="fiveStarAuto")String betType,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byFiveStarBet时时彩五星机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByFiveStarAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byFiveStarAuto");
			logger.info("LotteryShiShiCaiController/byFiveStarAutoBet时时彩五星参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byFiveStarAutoBet时时彩五星参数验证通过");
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
			modelAndView.setViewName("shiShiCai/byFiveStarAuto");
			logger.info("LotteryShiShiCaiController/bybyFiveStarAutoBet时时彩五星金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	@RequestMapping("/byFiveStarAuto")
	public ModelAndView byFiveStarAuto(
			@RequestParam(value="zhuShu",defaultValue="")String zhuShu,
			@RequestParam(value="autoZhuShu",defaultValue="")String autoZhuShu,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="betCode",defaultValue="")String betCode,
			@RequestParam(value="betCodeView",defaultValue="")String betCodeView,
			@RequestParam(value="playType",defaultValue="")String playType,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="fiveStarAuto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byFiveStarAutoCode(autoZhuShu, lotNo, batchCode,betType, request);
		if("提交投注".equals(submitType)) return byFiveStarAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType,request);
		return modelAndView;
	}
	/**
	 * 时时彩五星通选
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
	@RequestMapping("/byFiveStarTongSelf")
	public ModelAndView byFiveStarTongSelf(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryShiShiCaiController/byFiveStarTongSelf时时彩五星通选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateShiShiCaiUtil.validateByFiveStarTongSelf(wballs, qballs, bballs, sballs, gballs, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
			modelAndView.setViewName("shiShiCai/byFiveStarTongSelf");
			logger.info("LotteryShiShiCaiController/byFiveStarTongSelf时时彩五星通选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byFiveStarTongSelf时时彩五星通选参数验证通过");
		Map<String, String> fiveStarSelfBetMap = LotteryShiShiCaiUtil.getFiveStarTongSelfBetMap(gballs, sballs, bballs, qballs, wballs);
		String betCode = fiveStarSelfBetMap.get("betCode");
		String betCodeView = fiveStarSelfBetMap.get("betCodeView");
		String zhuShu = fiveStarSelfBetMap.get("zhuShu");
		String playType = fiveStarSelfBetMap.get("playType");
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
			modelAndView.setViewName("shiShiCai/byFiveStarTongSelf");
			logger.info("LotteryShiShiCaiController/byFiveStarTongSelf时时彩五星通选金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;

		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);

		modelAndView.addObject("wballs", wmap);
		modelAndView.addObject("qballs", qmap);
		modelAndView.addObject("bballs", bmap);
		modelAndView.addObject("sballs", smap);
		modelAndView.addObject("gballs", gmap);
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
 * 时时彩五星通选随机
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
@RequestMapping("/byFiveStarTongAutoSelf")
public ModelAndView byFiveStarTongAutoSelf(
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
		@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
		@RequestParam(value="batchCode",defaultValue="")String batchCode,
		@RequestParam(value="prizeend",defaultValue="0")String prizeend,
		HttpServletRequest request,ModelAndView modelAndView){
	logger.info("LotteryShiShiCaiController/byFiveStarTongAutoSelf时时彩五星通选随机参数ballNumber:"+ballNumber+",beiShu:"+beiShu
			+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
	String validateResult = ValidateShiShiCaiUtil.validateByFiveStarTongAutoSelf(ballNumber);
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
	lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
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
		modelAndView.setViewName("shiShiCai/byFiveStarTongSelf");
		logger.info("LotteryShiShiCaiController/byFiveStarTongAutoSelf时时彩五星通选随机参数验证失败返回validateResult:"+validateResult);
		return modelAndView;

	}
	logger.info("LotteryShiShiCaiController/byFiveStarTongAutoSelf时时彩五星通选随机参数验证通过");
	if("W".equals(ballType)){
		wballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		wmap.clear();
		if(wballs!=null){
			for (String wball : wballs) {
				wmap.put("wball_"+wball,wball);
			}
		}
		
	}
	if("Q".equals(ballType)){
		qballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		qmap.clear();
		if(qballs!=null){
			for (String qball : qballs) {
				qmap.put("qball_"+qball,qball);
			}
		}
		
	}
	if("B".equals(ballType)){
		bballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		bmap.clear();
		if(bballs!=null){
			for (String bball : bballs) {
				bmap.put("bball_"+bball,bball);
			}
		}
		
	}
	if("S".equals(ballType)){
		sballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
		smap.clear();
		if(sballs!=null){
			for (String sball : sballs) {
				smap.put("sball_"+sball, sball);
			}
		}
	}
	if("G".equals(ballType)){
		gballs = LotteryShiShiCaiUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
	modelAndView.setViewName("shiShiCai/byFiveStarTongSelf");
	return modelAndView;
}
//	/**
//	 * 时时彩五星通选机选投注
//	 * @param zhuShu
//	 * @param beiShu
//	 * @param addNumber
//	 * @param oneAmount
//	 * @param lotNo
//	 * @param batchCode
//	 * @param prizeend
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/byFiveStarTongAuto")
//	public ModelAndView byFiveStarTongAuto(
//			@RequestParam(value="zhuShu",defaultValue="1")String zhuShu,
//			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
//			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
//			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
//			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
//			@RequestParam(value="batchCode",defaultValue="")String batchCode,
//			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
//			HttpServletRequest request){
//		logger.info("LotteryShiShiCaiController/byFiveStarTongAuto时时彩五星通选机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
//				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
//		ModelAndView modelAndView = new ModelAndView();
//		String validateResult = ValidateShiShiCaiUtil.validateByFiveStarAuto(zhuShu, beiShu, addNumber);
//		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
//		if(validateResult!=null&&!"".equals(validateResult)){
//			modelAndView.addObject("zhuShu", zhuShu);
//			modelAndView.addObject("beiShu", beiShu);
//			modelAndView.addObject("addNumber", addNumber);
//			modelAndView.addObject("batchCode", batchCode);
//			modelAndView.addObject("oneAmount", oneAmount);
//			modelAndView.addObject("lotNo", lotNo);
//			modelAndView.addObject("prizeend", prizeend);
//			modelAndView.addObject("messageError", validateResult);
//			modelAndView.setViewName("byFiveStarTongAuto");
//			logger.info("LotteryShiShiCaiController/byFiveStarTongAuto时时彩五星通选机选参数验证失败返回validateResult:"+validateResult);
//			return modelAndView;
//
//		}
//		logger.info("LotteryShiShiCaiController/byFiveStarTongAuto时时彩五星通选机选参数验证通过");
//		Map<String, String> fiveStarAutoBetMap = LotteryShiShiCaiUtil.getFiveStarTongAutoBetMap(zhuShu);
//		String betCode = fiveStarAutoBetMap.get("betCode");
//		String betCodeView = fiveStarAutoBetMap.get("betCodeView");
//		int oneAmountInt = Integer.parseInt(oneAmount);
//		int zhuShuInt = Integer.parseInt(zhuShu);
//		int beiShuInt = Integer.parseInt(beiShu);
//		int addNumberInt = Integer.parseInt(addNumber);
//		int amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
//		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
//		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
//			modelAndView.addObject("zhuShu", zhuShu);
//			modelAndView.addObject("beiShu", beiShu);
//			modelAndView.addObject("addNumber", addNumber);
//			modelAndView.addObject("batchCode", batchCode);
//			modelAndView.addObject("oneAmount", oneAmount);
//			modelAndView.addObject("lotNo", lotNo);
//			modelAndView.addObject("prizeend", prizeend);
//			modelAndView.addObject("messageError", validateAmountResult);
//			modelAndView.setViewName("byFiveStarTongAuto");
//			logger.info("LotteryShiShiCaiController/byFiveStarTongAuto时时彩五星通选机选金额验证失败返回validateAmountResult:"+validateAmountResult);
//			return modelAndView;
//		}
//		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
//		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
//		modelAndView.addObject("beiShu", beiShu);
//		modelAndView.addObject("addNumber", addNumber);
//		modelAndView.addObject("batchCode", batchCode);
//		modelAndView.addObject("oneAmount", oneAmount);
//		modelAndView.addObject("zhuShu", zhuShu);
//		modelAndView.addObject("lotNo", lotNo);
//		modelAndView.addObject("prizeend", prizeend);
//		modelAndView.addObject("amountView", amountView);
//		modelAndView.addObject("amount", amount);
//		modelAndView.addObject("lotName", lotName);
//		modelAndView.addObject("betCode", betCode);
//		modelAndView.addObject("betCodeView", betCodeView);
//		modelAndView.setViewName("gaoPinBetDetail");
//		return modelAndView;
//	}
	@RequestMapping("/byDaXiaoDanShuangSelf")
	public ModelAndView byDaXiaoDanShuangSelf(
			@RequestParam(value="gball",defaultValue="")String gball,
			@RequestParam(value="sball",defaultValue="")String sball,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryShiShiCaiController/byDaXiaoDanShuangSelf时时彩大小单双自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateShiShiCaiUtil.validateByDaXiaoDanShuangSelf(gball, sball, beiShu, addNumber);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("gball", gball);
			modelAndView.addObject("sball", sball);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("shiShiCai/byDaXiaoDanShuangSelf");
			logger.info("LotteryShiShiCaiController/byDaXiaoDanShuangSelf时时彩大小单双自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryShiShiCaiController/byDaXiaoDanShuangSelf时时彩大小单双自选参数验证通过");
		Map<String, String> daXiaoDanShuangSelfBetMap = LotteryShiShiCaiUtil.getDaXiaoDanShuangSelfBetMap(gball, sball);
		String betCode = daXiaoDanShuangSelfBetMap.get("betCode");
		String betCodeView = daXiaoDanShuangSelfBetMap.get("betCodeView");
		String zhuShu = daXiaoDanShuangSelfBetMap.get("zhuShu");
		String playType = daXiaoDanShuangSelfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("gball", gball);
			modelAndView.addObject("sball", sball);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("shiShiCai/byDaXiaoDanShuangSelf");
			logger.info("LotteryShiShiCaiController/byTwoStarSelf时时彩2星自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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

	@RequestMapping("/byOneStar")
	public ModelAndView byOneStar(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byOneStarAutoSelf(ballNumber, gballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byOneStarSelf(gballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);

		return modelAndView;
	}
	@RequestMapping("/byTwoStarToDirect")
	public ModelAndView byTwoStarToDirect(
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gballNumber", gballNumber);
		modelAndView.addObject("sballNumber", sballNumber);
		if("机选个位码".equals(submitType)) return byTwoStarAutoDirect(gballNumber, "G", gballs, sballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		if("机选十位码".equals(submitType)) return byTwoStarAutoDirect(sballNumber, "S", gballs, sballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		if("提交投注".equals(submitType)) return byTwoStarDirect(gballs, sballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		return modelAndView;
	}
	@RequestMapping("/byTwoStarToGroup")
	public ModelAndView byTwoStarToGroup(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byTwoStarAutoGroup(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byTwoStarGroup(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
	@RequestMapping("/byTwoStarToHeZhi")
	public ModelAndView byTwoStarToHeZhi(
			@RequestParam(value="ballNumber",defaultValue="1")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byTwoStarAutoHeZhi(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byTwoStarHeZhi(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
	@RequestMapping("/byThreeStarToDirect")
	public ModelAndView byThreeStarToDirect(
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gballNumber", gballNumber);
		modelAndView.addObject("sballNumber", sballNumber);
		modelAndView.addObject("bballNumber", bballNumber);
		if("机选个位码".equals(submitType)) return byThreeStarAutoDirect(gballNumber, "G", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		if("机选十位码".equals(submitType)) return byThreeStarAutoDirect(sballNumber, "S", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		if("机选百位码".equals(submitType)) return byThreeStarAutoDirect(bballNumber, "B", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		if("提交投注".equals(submitType)) return byThreeStarDirect(gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request,modelAndView);
		return modelAndView;
	}
	@RequestMapping("/byThreeStarToGroup3")
	public ModelAndView byThreeStarToGroup3(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byThreeStarAutoGroup3(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byThreeStarGroup3(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
	@RequestMapping("/byThreeStarToGroup6")
	public ModelAndView byThreeStarToGroup6(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选号码".equals(submitType)) return byThreeStarAutoGroup6(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("提交投注".equals(submitType)) return byThreeStarGroup6(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;
	}
	@RequestMapping("/byFiveStarToDirect")
	public ModelAndView byFiveStarToDirect(
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="qballNumber",defaultValue="")String qballNumber,
			@RequestParam(value="wballNumber",defaultValue="")String wballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gballNumber", gballNumber);
		modelAndView.addObject("sballNumber", sballNumber);
		modelAndView.addObject("bballNumber", bballNumber);
		modelAndView.addObject("qballNumber", qballNumber);
		modelAndView.addObject("wballNumber", wballNumber);
		if("机选万位码".equals(submitType)) return byFiveStarAutoDirect(wballNumber, "W", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选千位码".equals(submitType)) return byFiveStarAutoDirect(qballNumber, "Q", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选百位码".equals(submitType)) return byFiveStarAutoDirect(bballNumber, "B", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选十位码".equals(submitType)) return byFiveStarAutoDirect(sballNumber, "S", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选个位码".equals(submitType)) return byFiveStarAutoDirect(gballNumber, "G", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("提交投注".equals(submitType)) return byFiveStarDirect(gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		return modelAndView;
	}
	@RequestMapping("/byFiveStarToTongSelf")
	public ModelAndView byFiveStarToTongSelf(
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="qballNumber",defaultValue="")String qballNumber,
			@RequestParam(value="wballNumber",defaultValue="")String wballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="qball",required=false)List<String> qballs,
			@RequestParam(value="wball",required=false)List<String> wballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01007")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gballNumber", gballNumber);
		modelAndView.addObject("sballNumber", sballNumber);
		modelAndView.addObject("bballNumber", bballNumber);
		modelAndView.addObject("qballNumber", qballNumber);
		modelAndView.addObject("wballNumber", wballNumber);
		if("机选万位码".equals(submitType)) return byFiveStarTongAutoSelf(wballNumber, "W", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选千位码".equals(submitType)) return byFiveStarTongAutoSelf(qballNumber, "Q", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选百位码".equals(submitType)) return byFiveStarTongAutoSelf(bballNumber, "B", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选十位码".equals(submitType)) return byFiveStarTongAutoSelf(sballNumber, "S", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选个位码".equals(submitType)) return byFiveStarTongAutoSelf(gballNumber, "G", gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("提交投注".equals(submitType)) return byFiveStarTongSelf(gballs, sballs, bballs, qballs, wballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		return modelAndView;

	}
}
