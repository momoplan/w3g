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
import com.ruyicai.wap.util.bet.LotteryGuangDongHappyTenMinutesUtil;
import com.ruyicai.wap.util.bet.LotteryGuangDongHappyTenMinutesUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateGuangDongHappyTenMinutesUtil;
import com.ruyicai.wap.util.validate.ValidateGuangDongHappyTenMinutesUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;

@Controller
@RequestMapping("/guangDongHappyTenMinutes")
public class LotteryGuangDongHappyTenMinutesController {
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	private Logger logger = Logger.getLogger(this.getClass());
	@RequestMapping("/byOptionSelf")
	public ModelAndView byOptionSelf(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionSelf广东快乐十分任选参数beiShu:"+beiShu+",ballType:"+ballType
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByOptionSelf(balls, ballType, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionSelfViewName(ballType));
			logger.info("LotteryGuangDongHappyTenMinutesController/byOptionSelf广东快乐十分任选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionSelf广东快乐十分任选参数验证通过");
		Map<String, String> selfBetMap = LotteryGuangDongHappyTenMinutesUtil.getOptionSelfBetMap(balls, ballType);
		String betCode = selfBetMap.get("betCode");
		String betCodeView = selfBetMap.get("betCodeView");
		String zhuShu = selfBetMap.get("zhuShu");
		String playType = selfBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getOptionSelfViewName(ballType));
			logger.info("LotteryGuangDongHappyTenMinutesController/byOptionSelf广东快乐十分任选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分随机任选自选
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
	@RequestMapping("/byAutoOptionSelf")
	public ModelAndView byAutoOptionSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoOptionSelf广东快乐十分随机任选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByAutoOptionSelf(ballNumber, ballType);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}

		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
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
			logger.info("LotteryGuangDongHappyTenMinutesController/byAutoOptionSelf广东快乐十分随机任选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoOptionSelf广东快乐十分随机任选参数验证通过");
		if("S1".equals(ballType)){
			balls = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 18);
		}else if("H1".equals(ballType)){
			balls = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),19, 20);
		}else{
			balls = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
		}
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="betType",defaultValue="auto")String betType,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAutoCode广东快乐十分任选机选参数autoZhuShu:"+autoZhuShu+",ballType:"+ballType+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByOptionAuto(autoZhuShu, "1", "1","1");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("autoZhuShu", autoZhuShu);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("ballType", ballType);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getOptionSingleAutoViewName(ballType));
			logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAutoCode广东快乐十分任选机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAutoCode广东快乐十分任选机选参数验证通过");
		Map<String, String> optionAutoBetMap = LotteryGuangDongHappyTenMinutesUtil.getOptionAutoBetMap(autoZhuShu, ballType);
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
		modelAndView.setViewName(getOptionSingleAutoViewName(ballType));
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAutoBet广东快乐十分任选机选参数zhuShu:"+zhuShu+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByOptionAuto(zhuShu, beiShu, addNumber,betCodeView);
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
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
			modelAndView.setViewName(getOptionSingleAutoViewName(ballType));
			logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAutoBet广东快乐十分任选机选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAuto广东快乐十分任选机选参数验证通过");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
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
			modelAndView.setViewName(getOptionSingleAutoViewName(ballType));
			logger.info("LotteryGuangDongHappyTenMinutesController/byOptionAuto广东快乐十分任选机选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分胆拖
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byDanTuo广东快乐十分胆拖参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByDanTuo(dballs, tballs, ballType, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
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
			logger.info("LotteryGuangDongHappyTenMinutesController/byDanTuo广东快乐十分胆拖参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byDanTuo广东快乐十分胆拖参数验证通过");
		Map<String, String> danTuoMap = LotteryGuangDongHappyTenMinutesUtil.getDanTuoBetMap(dballs, tballs, ballType);
		String betCode = danTuoMap.get("betCode");
		String betCodeView = danTuoMap.get("betCodeView");
		String zhuShu = danTuoMap.get("zhuShu");
		String playType = danTuoMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
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
			logger.info("LotteryGuangDongHappyTenMinutesController/byDanTuo广东快乐十分胆拖参数金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分选二连组
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
	@RequestMapping("/bySelectTwoLinkGroupSelf")
	public ModelAndView bySelectTwoLinkGroupSelf(
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkGroupSelf广东快乐十分前二连组参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateBySelectTwoLinkGroupSelf(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkGroupSelf广东快乐十分前二连组参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/SelectTwoLinkGroupSelf广东快乐十分前二连组参数验证通过");
		Map<String, String> groupBetMap = LotteryGuangDongHappyTenMinutesUtil.getGroupBetMap(balls, "Z2");
		String betCode = groupBetMap.get("betCode");
		String betCodeView = groupBetMap.get("betCodeView");
		String zhuShu = groupBetMap.get("zhuShu");
		String playType = groupBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkGroupSelf广东快乐十分选二连组金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分随机选二连组
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
	@RequestMapping("/byAutoSelectTwoLinkGroupSelf")
	public ModelAndView byAutoSelectTwoLinkGroupSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkGroupSelf广东快乐十分随机选二连组参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByAutoSelectTwoLinkGroupSelf(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkGroupSelf广东快乐十分随机选二连组参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkGroupSelf广东快乐十分随机选二连组参数验证通过");
		balls = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
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
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf");
		return modelAndView;
	}
	/**
	 * 广东快乐十分前三组选
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeGroupSelect广东快乐十分前三组选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByForwardThreeGroupSelect(balls, beiShu, addNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeGroupSelect广东快乐十分前三组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeGroupSelect广东快乐十分前三组选参数验证通过");
		Map<String, String> groupBetMap = LotteryGuangDongHappyTenMinutesUtil.getGroupBetMap(balls, "Z3");
		String betCode = groupBetMap.get("betCode");
		String betCodeView = groupBetMap.get("betCodeView");
		String zhuShu = groupBetMap.get("zhuShu");
		String playType = groupBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeGroupSelect广东快乐十分前三组选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分随机前三组选
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request){
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeGroupSelect广东快乐十分随机前三组选参数ballNumber:"+ballNumber+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByAutoForwardThreeGroupSelect(ballNumber);
		Map<String, String> map = new HashMap<String, String>();
		if(balls!=null){
			for (String ball : balls) {
				map.put("ball_"+ball,ball);
			}
		}
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("balls", map);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeGroupSelect广东快乐十分随机前三组选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeGroupSelect广东快乐十分随机前三组选参数验证通过");
		balls = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
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
		modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf");
		return modelAndView;
	}
	/**
	 * 广东快乐十分选二连直
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
	@RequestMapping("/bySelectTwoLinkDirectSelf")
	public ModelAndView bySelectTwoLinkDirectSelf(
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkDirectSelf广东快乐十分选二连直参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateBySelectTwoLinkDirectSelf(ball1s, ball2s, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkDirectSelf广东快乐十分选二连直参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkDirectSelf广东快乐十分选二连直参数验证通过");
		Map<String, String> SelectTwoLinkDirectSelfBetMapBetMap = LotteryGuangDongHappyTenMinutesUtil.getSelectTwoLinkDirectSelfBetMap(ball1s, ball2s);
		String betCode = SelectTwoLinkDirectSelfBetMapBetMap.get("betCode");
		String betCodeView = SelectTwoLinkDirectSelfBetMapBetMap.get("betCodeView");
		String zhuShu = SelectTwoLinkDirectSelfBetMapBetMap.get("zhuShu");
		String playType = SelectTwoLinkDirectSelfBetMapBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName("guangDongElevenSelectFive/bySelectTwoLinkDirectSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkDirectSelf广东快乐十分选二连直金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;

		}
		if(amount<2){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", "注码不正确！");
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/bySelectTwoLinkDirectSelf广东快乐十分选二连直金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分随机选二连直
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
	@RequestMapping("/byAutoSelectTwoLinkDirectSelf")
	public ModelAndView byAutoSelectTwoLinkDirectSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkDirectSelf广东快乐十分随机选二连直参数ballNumber:"+ballNumber+",ballType:"+ballType+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByAutoSelectTwoLinkDirectSelf(ballNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
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
			modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkDirectSelf广东快乐十分随机选二连直参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoSelectTwoLinkDirectSelf广东快乐十分随机选二连直参数验证通过");
		if("1".equals(ballType)){
			ball1s = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
			map1.clear();
			if(ball1s!=null){
				for (String ball1 : ball1s) {
					map1.put("ball1_"+ball1,ball1);
				}
			}	
		}
		if("2".equals(ballType)){
			ball2s = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
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
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf");
		return modelAndView;
	}
	/**
	 * 广东快乐十分前三直选
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeDirectSelect广东快乐十分前三直选参数beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByForwardThreeDirectSelect(ball1s, ball2s, ball3s, beiShu, addNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
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
			modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeDirectSelect广东快乐十分前三直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeDirectSelect广东快乐十分前三直选参数验证通过");
		Map<String, String> forwardThreeDirectSelectBetMap = LotteryGuangDongHappyTenMinutesUtil.getForwardThreeDirectSelectBetMap(ball1s, ball2s, ball3s);
		String betCode = forwardThreeDirectSelectBetMap.get("betCode");
		String betCodeView = forwardThreeDirectSelectBetMap.get("betCodeView");
		String zhuShu = forwardThreeDirectSelectBetMap.get("zhuShu");
		String playType = forwardThreeDirectSelectBetMap.get("playType");
		int oneAmountInt = Integer.parseInt(oneAmount);
		int zhuShuInt = Integer.parseInt(zhuShu);
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		long amount = oneAmountInt*zhuShuInt*beiShuInt*addNumberInt;
		String validateAmountResult = ValidateLotteryUtil.validateGDKL10MaxAmount(oneAmountInt, beiShuInt, zhuShuInt);
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
			modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeDirectSelect广东快乐十分前三直选金额验证失败返回validateAmountResult:"+validateAmountResult);
			return modelAndView;

		}
		if(amount<2){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball2s", map2);
			modelAndView.addObject("ball3s", map3);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", "注码不正确！");
			modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byForwardThreeDirectSelect广东快乐十分前三直选金额验证失败返回validateAmountResult:"+validateAmountResult);
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
	 * 广东快乐十分随机前三直选
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			HttpServletRequest request,ModelAndView modelAndView){
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeDirectSelect广东快乐十分随机前三直选参数ballNumber:"+ballNumber+",ballType:"+ballType+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",prizeend:"+prizeend+"oneAmount:"+oneAmount+",lotNo:"+lotNo+",batchCode:"+batchCode);
		String validateResult = ValidateGuangDongHappyTenMinutesUtil.validateByAutoForwardThreeDirectSelect(ballNumber);
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
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("ball1s", map1);
			modelAndView.addObject("ball3s", map2);
			modelAndView.addObject("ball2s", map3);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("ballNumber", ballNumber);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("prizeend", prizeend);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
			logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeDirectSelect广东快乐十分随机前三直选参数验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("LotteryGuangDongHappyTenMinutesController/byAutoForwardThreeDirectSelect广东快乐十分随机前三直选参数验证通过");
		if("1".equals(ballType)){
			ball1s = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
			map1.clear();
			if(ball1s!=null){
				for (String ball1 : ball1s) {
					map1.put("ball1_"+ball1,ball1);
				}
			}	
		}
		if("2".equals(ballType)){
			ball2s = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
			map2.clear();
			if(ball2s!=null){
				for (String ball2 : ball2s) {
					map2.put("ball2_"+ball2,ball2);
				}
			}	
		}
		if("3".equals(ballType)){
			ball3s = LotteryGuangDongHappyTenMinutesUtil.getAutoBetCodeAuto(Integer.parseInt(ballNumber),1, 20);
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
		modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
		return modelAndView;
	}
	public String getOptionSelfViewName(String type){
		if("S1".equals(type)){
			return "guangDongHappyTenMinutes/bySelectOneNumberSelf";
		}else if("H1".equals(type)){
			return "guangDongHappyTenMinutes/bySelectOneRedSelf";
		}else if("R2".equals(type)){
			return "guangDongHappyTenMinutes/byOptionTwoSelf";
		}else if("R3".equals(type)){
			return "guangDongHappyTenMinutes/byOptionThreeSelf";
		}else if("R4".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFourSelf";
		}else if("R5".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFiveSelf";
		}else if("Z2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf";
		}else if("Z3".equals(type)){
			return "guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf";
		}else if("Q2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf";
		}else if("Q3".equals(type)){
			return "guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf";
		}else{
			return "";
		}
	}
	public String getOptionSingleAutoViewName(String type){
		if("S1".equals(type)){
			return "guangDongHappyTenMinutes/bySelectOneNumberSingleAuto";
		}else if("H1".equals(type)){
			return "guangDongHappyTenMinutes/bySelectOneRedSingleAuto";
		}else if("R2".equals(type)){
			return "guangDongHappyTenMinutes/byOptionTwoSingleAuto";
		}else if("R3".equals(type)){
			return "guangDongHappyTenMinutes/byOptionThreeSingleAuto";
		}else if("R4".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFourSingleAuto";
		}else if("R5".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFiveSingleAuto";
		}else if("Q2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkDirectSingleAuto";
		}else if("Q3".equals(type)){
			return "guangDongHappyTenMinutes/byDirectSelectForwardThreeSingleAuto";
		}else if("Z2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkGroupSingleAuto";
		}else if("Z3".equals(type)){
			return "guangDongHappyTenMinutes/byGroupSelectForwardThreeSingleAuto";
		}else{
			return "";
		}
	}
	public String getDanTuoViewName(String type){
		if("R2".equals(type)){
			return "guangDongHappyTenMinutes/byOptionTwoDanTuoSelf";
		}else if("R3".equals(type)){
			return "guangDongHappyTenMinutes/byOptionThreeDanTuoSelf";
		}else if("R4".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFourDanTuoSelf";
		}else if("R5".equals(type)){
			return "guangDongHappyTenMinutes/byOptionFiveDanTuoSelf";
		}else if("Z2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkGroupDanTuoSelf";
		}else if("Z3".equals(type)){
			return "guangDongHappyTenMinutes/byGroupSelectForwardThreeDanTuoSelf";
		}else if("Q2".equals(type)){
			return "guangDongHappyTenMinutes/bySelectTwoLinkDirectDanTuoSelf";
		}else if("Q3".equals(type)){
			return "guangDongHappyTenMinutes/byDirectSelectForwardThreeDanTuoSelf";
		}else{
			return "";
		}
	}
	@RequestMapping("/byOptionToSelf")
	public ModelAndView byOptionToSelf(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byOptionSelf(balls, ballType, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoOptionSelf(ballNumber, ballType, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
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
	@RequestMapping("/bySelectTwoLinkGroupSelfToSelect")
	public ModelAndView bySelectTwoLinkGroupSelfToSelect(
			@RequestParam(value="ballNumber",defaultValue="")String ballNumber,
			@RequestParam(value="ballType",defaultValue="")String ballType,
			@RequestParam(value="ball",required=false)List<String> balls,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return bySelectTwoLinkGroupSelf(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoSelectTwoLinkGroupSelf(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if("提交投注".equals(submitType)) return byForwardThreeGroupSelect(balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		if("机选号码".equals(submitType)) return byAutoForwardThreeGroupSelect(ballNumber, balls, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request);
		return modelAndView;


	}
	@RequestMapping("/bySelectTwoLinkDirectToSelect")
	public ModelAndView bySelectTwoLinkDirectToSelect(
			@RequestParam(value="ballNumber1",defaultValue="")String ballNumber1,
			@RequestParam(value="ballNumber2",defaultValue="")String ballNumber2,
			@RequestParam(value="ball1",required=false)List<String> ball1s,
			@RequestParam(value="ball2",required=false)List<String> ball2s,
			@RequestParam(value="beiShu",defaultValue="1")String beiShu,
			@RequestParam(value="addNumber",defaultValue="1")String addNumber,
			@RequestParam(value="oneAmount",defaultValue="2")String oneAmount,
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
			@RequestParam(value="batchCode",defaultValue="")String batchCode,
			@RequestParam(value="prizeend",defaultValue="0")String prizeend,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ballNumber1", ballNumber1);
		modelAndView.addObject("ballNumber2", ballNumber2);
		if("机选一位码".equals(submitType)) return byAutoSelectTwoLinkDirectSelf(ballNumber1, "1", ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("机选二位码".equals(submitType)) return byAutoSelectTwoLinkDirectSelf(ballNumber2, "2", ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
		if("提交投注".equals(submitType)) return bySelectTwoLinkDirectSelf(ball1s, ball2s, beiShu, addNumber, oneAmount, lotNo, batchCode, prizeend, request, modelAndView);
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
			@RequestParam(value="lotNo",defaultValue="T01015")String lotNo,
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
