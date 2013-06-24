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
import com.ruyicai.wap.util.bet.LotteryFcSdUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateFcSdUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/fuCai3D")
public class LotteryFuCai3DController {
	Logger logger = Logger.getLogger(LotteryFuCai3DController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 福彩3D直选
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
	@RequestMapping("/byDirect")
	public ModelAndView byDirect(
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="direct")String betType,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryFuCai3DController/byDirect福彩3D直选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateFcSdUtil.validateByDirect(bballs, sballs, gballs, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("betType", betType);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byDirect");
			logger.info("LotteryFuCai3DController/byDirect福彩3D直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byDirect福彩3D直选参数验证通过");
		Map<String, String> directBetMap = LotteryFcSdUtil.getDirectBetMap(bballs, sballs, gballs);
		String betCode = directBetMap.get("betCode");
		String betCodeView = directBetMap.get("betCodeView");
		String zhuShu = directBetMap.get("zhuShu");
		String playType = directBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("bballs", bmap);
			modelAndView.addObject("sballs", smap);
			modelAndView.addObject("gballs", gmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("betType", betType);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("fuCai3D/byDirect");
			logger.info("LotteryFuCai3DController/byDirect福彩3D直选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.addObject("betType", betType);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("betDetail");
		return modelAndView;
		
	}


	/**
	 * 福彩3D随机直选
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
	@RequestMapping("/byAutoDirect")
	public ModelAndView byAutoDirect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="direct")String betType,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryFuCai3DController/byAutoDirect福彩3D随机直选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateFcSdUtil.validateByAutoDirect(ballNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
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
			modelAndView.addObject("betType", betType);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byDirect");
			logger.info("LotteryFuCai3DController/byAutoDirect福彩3D随机直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byAutoDirect福彩3D随机直选参数验证通过");
		if("B".equals(ballType)){
			bballs = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			bmap.clear();
			if(bballs!=null){
				for (String bball : bballs) {
					bmap.put("bball_"+bball,bball);
				}
			}
			
		}
		if("S".equals(ballType)){
			sballs = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
			smap.clear();
			if(sballs!=null){
				for (String sball : sballs) {
					smap.put("sball_"+sball, sball);
				}
			}
		}
		if("G".equals(ballType)){
			gballs = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.addObject("betType", betType);
		modelAndView.setViewName("fuCai3D/byDirect");
		return modelAndView;
	}
	/**
	 * 福彩3D机选投注
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
			@RequestParam(value="autoZhuShu",defaultValue="1")String autoZhuShu,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoCode福彩3D机选参数autoZhuShu:"+autoZhuShu+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", betType);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byAuto");
			logger.info("LotteryFuCai3DController/byAutoCode福彩3D机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byAutoCode福彩3D机选参数验证通过");
		Map<String, String> autoBetMap = LotteryFcSdUtil.getAutoBetMap(autoZhuShu);
		String betCode = autoBetMap.get("betCode");
		String betCodeView = autoBetMap.get("betCodeView");
		String playType = autoBetMap.get("playType");
		modelAndView.addObject("autoZhuShu", autoZhuShu);
		modelAndView.addObject("zhuShu", autoZhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		modelAndView.setViewName("fuCai3D/byAuto");
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
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoBet福彩3D机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
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
			modelAndView.setViewName("fuCai3D/byAuto");
			logger.info("LotteryFuCai3DController/byAutoBet福彩3D机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byAutoBet福彩3D机选参数验证通过");
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
			modelAndView.setViewName("fuCai3D/byAuto");
			logger.info("LotteryFuCai3DController/byAutoBet福彩3D机选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("机选".equals(submitType)) return byAutoCode(autoZhuShu, lotNo, batchCode, betType, request);
		if("提交投注".equals(submitType)) return byAutoBet(zhuShu, beiShu, addNumber, betCode, betCodeView, playType, oneAmount, lotNo, batchCode, prizeend, betType, request);
		return modelAndView;
	}
//	/**
//	 * 组三自选单式
//	 * @param ball2s
//	 * @param ball1s
//	 * @param beiShu
//	 * @param addNumber
//	 * @param oneAmount
//	 * @param lotNo
//	 * @param batchCode
//	 * @param prizeend
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/byGroup3SelfSingle")
//	public ModelAndView byGroup3SelfSingle(
//			@RequestParam(value="ball2",required=false)List<String> ball2s,
//			@RequestParam(value="ball1",required=false)List<String> ball1s,
//			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
//			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
//			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
//			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
//			@RequestParam(value="batchCode",defaultValue="")String batchCode,
//			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
//			HttpServletRequest request){
//		logger.info("LotteryFuCai3DController/byGroup3SelfSingle福彩3D组三自选单式参数beiShu:"+beiShu
//				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
//		ModelAndView modelAndView = new ModelAndView();
//		String validateResult = ValidateFcSdUtil.validateByGroup3SelfSingle(ball2s, ball1s, beiShu, addNumber);
//		Map<String, String> b2map = new HashMap<String, String>();
//		Map<String, String> b1map = new HashMap<String, String>();
//		if(ball2s!=null){
//			for (String ball2 : ball2s) {
//				b2map.put("ball2_"+ball2,ball2);
//			}
//		}
//		if(ball1s!=null){
//			for (String ball1 : ball1s) {
//				b1map.put("ball1_"+ball1, ball1);
//			}
//		}
//		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
//		if(validateResult!=null&&!"".equals(validateResult)){
//			modelAndView.addObject("ball2s", b2map);
//			modelAndView.addObject("ball1s", b1map);
//			modelAndView.addObject("beiShu", beiShu);
//			modelAndView.addObject("addNumber", addNumber);
//			modelAndView.addObject("batchCode", batchCode);
//			modelAndView.addObject("oneAmount", oneAmount);
//			modelAndView.addObject("lotNo", lotNo);
//			modelAndView.addObject("prizeend", prizeend);
//			modelAndView.addObject("messageError", validateResult);
//			modelAndView.setViewName("byGroup3SelfSingle");
//			logger.info("LotteryFuCai3DController/byGroup3SelfSingle福彩3D组三自选单式参数参数验证失败返回validateResult:"+validateResult);
//			return modelAndView;
//
//		}
//		logger.info("LotteryFuCai3DController/byGroup3SelfSingle福彩3D组三自选单式参数参数验证通过");
//		Map<String, String> group3SelfSingleMap = LotteryFcSdUtil.getGroup3SelfSingleBetMap(ball2s, ball1s);
//		String betCode = group3SelfSingleMap.get("betCode");
//		String betCodeView = group3SelfSingleMap.get("betCodeView");
//		String zhuShu = group3SelfSingleMap.get("zhuShu");
//		int oneAmountInt = Integer.parseInt(oneAmount);
//		int zhuShuInt = Integer.parseInt(zhuShu);
//		int beiShuInt = Integer.parseInt(beiShu);
//		int addNumberInt = Integer.parseInt(addNumber);
//		int amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
//		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
//		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
//			modelAndView.addObject("ball2s", b2map);
//			modelAndView.addObject("ball1s", b1map);
//			modelAndView.addObject("beiShu", beiShu);
//			modelAndView.addObject("addNumber", addNumber);
//			modelAndView.addObject("batchCode", batchCode);
//			modelAndView.addObject("oneAmount", oneAmount);
//			modelAndView.addObject("lotNo", lotNo);
//			modelAndView.addObject("prizeend", prizeend);
//			modelAndView.addObject("messageError", validateAmountResult);
//			modelAndView.setViewName("byGroup3SelfSingle");
//			logger.info("LotteryFuCai3DController/byGroup3SelfSingle福彩3D组三自选单式参数金额验证失败返回validateAmountResult:"+validateAmountResult);
//			return modelAndView;
//
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
//		modelAndView.setViewName("betDetail");
//		return modelAndView;
//		
//	}
	/**
	 * 组三自选
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
	@RequestMapping("/byGroup3Self")
	public ModelAndView byGroup3Self(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="group3Self")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byGroup3Self福彩3D组三自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByGroup3Self(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byGroup3Self");
			logger.info("LotteryFuCai3DController/byGroup3Self福彩3D组三自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byGroup3Self福彩3D组三自选参数验证通过");
		Map<String, String> group3SelfBetMap = LotteryFcSdUtil.getGroup3SelfBetMap(balls);
		String betCode = group3SelfBetMap.get("betCode");
		String betCodeView = group3SelfBetMap.get("betCodeView");
		String playType = group3SelfBetMap.get("playType");
		String zhuShu = group3SelfBetMap.get("zhuShu");
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
			modelAndView.setViewName("fuCai3D/byGroup3Self");
			logger.info("LotteryFuCai3DController/byGroup3Self福彩3D组三自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 机选组三自选
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
	@RequestMapping("/byAutoGroup3Self")
	public ModelAndView byAutoGroup3Self(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="group3Self")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoGroup3Self福彩3D随机组三自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByAutoGroup3Self(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byGroup3Self");
			logger.info("LotteryFuCai3DController/byAutoGroup3Self福彩3D随机组三自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byAutoGroup3Self福彩3D随机组三自选参数验证通过");
		balls = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", oneAmount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("prizeend", prizeend);
		modelAndView.setViewName("fuCai3D/byGroup3Self");
		return modelAndView;
	}
	@RequestMapping("/byGroup3")
	public ModelAndView byGroup3(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="group3Self")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byGroup3Self(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		if("机选号码".equals(submitType)) return byAutoGroup3Self(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		return modelAndView;
		
	}
	/**
	 * 组六自选
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
	@RequestMapping("/byGroup6Self")
	public ModelAndView byGroup6Self(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="group6Self")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byGroup6Self福彩3D组六自选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByGroup6Self(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byGroup6Self");
			logger.info("LotteryFuCai3DController/byGroup6Self福彩3D组六自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byGroup6Self福彩3D组六自选参数验证通过");
		Map<String, String> group6SelfBetMap = LotteryFcSdUtil.getGroup6SelfBetMap(balls);
		String betCode = group6SelfBetMap.get("betCode");
		String betCodeView = group6SelfBetMap.get("betCodeView");
		String zhuShu = group6SelfBetMap.get("zhuShu");
		String playType = group6SelfBetMap.get("playType");
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
			modelAndView.setViewName("fuCai3D/byGroup6Self");
			logger.info("LotteryFuCai3DController/byGroup6Self福彩3D组六自选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 机选组六自选
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
	@RequestMapping("/byAutoGroup6Self")
	public ModelAndView byAutoGroup6Self(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="group6Self")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoGroup6Self福彩3D随机组六自选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByAutoGroup6Self(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byGroup6Self");
			logger.info("LotteryFuCai3DController/byAutoGroup6Self福彩3D随机组六自选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byAutoGroup6Self福彩3D随机组六自选参数验证通过");
		balls = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 9);
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
		modelAndView.setViewName("fuCai3D/byGroup6Self");
		return modelAndView;
	}
	@RequestMapping("/byGroup6")
	public ModelAndView byGroup6(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="group6Self")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byGroup6Self(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		if("机选号码".equals(submitType)) return byAutoGroup6Self(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		return modelAndView;
		
	}
	
	/**
	 * 直选和值
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
	@RequestMapping("/byDirectHeZhi")
	public ModelAndView byDirectHeZhi(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="directHeZhi")String betType,			
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byDirectHeZhi福彩3D直选和值参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByDirectHeZhi(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byDirectHeZhi");
			logger.info("LotteryFuCai3DController/byDirectHeZhi福彩3D直选和值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byGroup3Self福彩3D直选和值参数验证通过");
		Map<String, String> directHeZhiBetMap = LotteryFcSdUtil.getDirectHeZhiBetMap(balls);
		String betCode = directHeZhiBetMap.get("betCode");
		String betCodeView = directHeZhiBetMap.get("betCodeView");
		String zhuShu = directHeZhiBetMap.get("zhuShu");
		String playType = directHeZhiBetMap.get("playType");
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
			modelAndView.setViewName("fuCai3D/byDirectHeZhi");
			logger.info("LotteryFuCai3DController/byDirectHeZhi福彩3D直选和值金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 机选直选和值
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
	@RequestMapping("/byAutoDirectHeZhi")
	public ModelAndView byAutoDirectHeZhi(
			@RequestParam(value="ballNumber",defaultValue="1")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="directHeZhi")String betType,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoDirectHeZhi福彩3D随机直选和值参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		balls = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),0, 27);
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
		modelAndView.setViewName("fuCai3D/byDirectHeZhi");
		return modelAndView;
	}
	@RequestMapping("/byHeZhi")
	public ModelAndView byHeZhi(
			@RequestParam(value="ballNumber",defaultValue="1")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="betType",defaultValue="directHeZhi")String betType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byDirectHeZhi(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		if("机选和值".equals(submitType)) return byAutoDirectHeZhi(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request);
		return modelAndView;
		
	}
	/**
	 * 组三和值
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
	@RequestMapping("/byGroup3HeZhi")
	public ModelAndView byGroup3HeZhi(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byGroup3HeZhi福彩3D组三和值参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByGroup3HeZhi(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("byGroup3HeZhi");
			logger.info("LotteryFuCai3DController/byGroup3HeZhi福彩3D组三和值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byGroup3HeZhi福彩3D组三和值参数验证通过");
		Map<String, String> group3HeZhiBetMap = LotteryFcSdUtil.getGroup3HeZhiBetMap(balls);
		String betCode = group3HeZhiBetMap.get("betCode");
		String betCodeView = group3HeZhiBetMap.get("betCodeView");
		String zhuShu = group3HeZhiBetMap.get("zhuShu");
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
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("byGroup3HeZhi");
			logger.info("LotteryFuCai3DController/byGroup3HeZhi福彩3D组三和值金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("betDetail");
		return modelAndView;
		
	}
	/**
	 * 机选组三和值
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
	@RequestMapping("/byAutoGroup3HeZhi")
	public ModelAndView byAutoGroup3HeZhi(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoGroup3HeZhi福彩3D随机组三和值参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		balls = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 26);
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
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
		modelAndView.setViewName("byGroup3HeZhi");
		return modelAndView;
	}
	/**
	 * 组六和值
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
	@RequestMapping("/byGroup6HeZhi")
	public ModelAndView byGroup6HeZhi(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byGroup6HeZhi福彩3D组六和值参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByGroup6HeZhi(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("byGroup6HeZhi");
			logger.info("LotteryFuCai3DController/byGroup6HeZhi福彩3D组六和值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byGroup6HeZhi福彩3D组六和值参数验证通过");
		Map<String, String> group6HeZhiBetMap = LotteryFcSdUtil.getGroup6HeZhiBetMap(balls);
		String betCode = group6HeZhiBetMap.get("betCode");
		String betCodeView = group6HeZhiBetMap.get("betCodeView");
		String zhuShu = group6HeZhiBetMap.get("zhuShu");
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
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("byGroup6HeZhi");
			logger.info("LotteryFuCai3DController/byGroup6HeZhi福彩3D组六和值金额验证失败返回validateAmountResult:"+validateAmountResult);
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
		modelAndView.setViewName("betDetail");
		return modelAndView;
		
	}
	/**
	 * 机选组六和值
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
	@RequestMapping("/byAutoGroup6HeZhi")
	public ModelAndView byAutoGroup6HeZhi(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byAutoGroup6HeZhi福彩3D随机组六和值参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		balls = LotteryFcSdUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),3, 24);
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
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
		modelAndView.setViewName("byGroup3HeZhi");
		return modelAndView;
	}
	/**
	 * 福彩3D胆拖投注
	 * @param dballs
	 * @param tballs
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
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryFuCai3DController/byDanTuo福彩3D胆拖参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateFcSdUtil.validateByDanTuo(dballs, tballs, beiShu, addNumber);
		Map<String, String> dmap = new HashMap<String, String>();
		Map<String, String> tmap = new HashMap<String, String>();
		if(dballs!=null){
			for (String dball : dballs) {
				dmap.put("dball_"+dball, dball);
			}
		}
		if(tballs!=null){
			for (String tball : tballs) {
				tmap.put("tball_"+tball, tball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("dballs", dmap);
			modelAndView.addObject("tballs", tmap);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("fuCai3D/byDanTuo");
			logger.info("LotteryFuCai3DController/byDanTuo福彩3D胆拖参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryFuCai3DController/byDanTuo福彩3D胆拖参数验证通过");
		Map<String, String> danTuoBetMap = LotteryFcSdUtil.getDanTuoBetMap(dballs, tballs);
		String betCode = danTuoBetMap.get("betCode");
		String betCodeView = danTuoBetMap.get("betCodeView");
		String zhuShu = danTuoBetMap.get("zhuShu");
		String playType = danTuoBetMap.get("playType");
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
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("fuCai3D/byDanTuo");
			logger.info("LotteryFuCai3DController/byDanTuo福彩3D金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	@RequestMapping("/byLottery")
	public ModelAndView byLottery(
			@RequestParam(value="gballNumber",defaultValue="")String gballNumber,
			@RequestParam(value="sballNumber",defaultValue="")String sballNumber,
			@RequestParam(value="bballNumber",defaultValue="")String bballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="gball",required=false)List<String> gballs,
			@RequestParam(value="sball",required=false)List<String> sballs,
			@RequestParam(value="bball",required=false)List<String> bballs,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="F47103")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="betType",defaultValue="")String betType,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gballNumber", gballNumber);
		modelAndView.addObject("sballNumber", sballNumber);
		modelAndView.addObject("bballNumber", bballNumber);
		if("提交投注".equals(submitType)) return byDirect(gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request,modelAndView);
		if("机选百位".equals(submitType)) return byAutoDirect(bballNumber, "B", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request,modelAndView);
		if("机选十位".equals(submitType)) return byAutoDirect(sballNumber, "S", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request,modelAndView);
		if("机选个位".equals(submitType)) return byAutoDirect(gballNumber, "G", gballs, sballs, bballs, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, betType, request,modelAndView);
		return modelAndView;
	}
}
