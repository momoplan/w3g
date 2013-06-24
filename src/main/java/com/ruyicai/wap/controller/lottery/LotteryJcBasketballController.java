package com.ruyicai.wap.controller.lottery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.bet.LotteryJcBasketballUtil;
import com.ruyicai.wap.util.bet.LotteryJcUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateJcUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;

@Controller
@RequestMapping("jcBasketball")
public class LotteryJcBasketballController {
	private static final Logger logger = Logger.getLogger(LotteryJcBasketballController.class); 
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	LotteryJcUtil lotteryJcUtil;
	@Autowired
	LotteryJcBasketballUtil lotteryJcBasketballUtil;
	@Autowired
	CommonUtil commonUtil;
	@RequestMapping("/betDetail")
	public ModelAndView betDetail(
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="valueType",defaultValue="") String valueType,
			@RequestParam(value="wanFa",defaultValue="") String wanFa,
			@RequestParam(value="play",required=false) List<String> plays,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="betCodeViewArrayToString",defaultValue="") String betCodeViewArrayToString,
			@RequestParam(value="submitType",defaultValue="") String submitType,
			@RequestParam(value="changCi",defaultValue="") String changCi,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("LotteryJcBasketballController/betDetail竞彩篮球参数:type:"+type
				+",valueType:"+valueType+",wanFa:"+wanFa+",beiShu:"+beiShu+",betCode:"+betCode);
		if(plays==null){
			modelAndView.addObject("type", type);
			modelAndView.addObject("valueType", valueType);
			modelAndView.addObject("wanFa", wanFa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeViewArrayToString", betCodeViewArrayToString);
			modelAndView.addObject("submitType", submitType);
			modelAndView.addObject("changCi", changCi);
			modelAndView.addObject("messageError", "请选择过关方式！");
			logger.info("LotteryJcBasketballController/betDetail竞彩篮球验证过关方式未通过plays："+plays);
			modelAndView.setViewName("jcBasketball/jcBasketballBetDetail");
			return modelAndView;
		}
		String play = "";
		for(int i=0;i<plays.size();i++){
			play+=plays.get(i)+",";
		}
		String validateBeiShuResult = ValidateJcUtil.validateBeiShu(beiShu);
		if(!"".equals(validateBeiShuResult)&&validateBeiShuResult!=null){
			modelAndView.addObject("type", type);
			modelAndView.addObject("valueType", valueType);
			modelAndView.addObject("wanFa", wanFa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeViewArrayToString", betCodeViewArrayToString);
			modelAndView.addObject("submitType", submitType);
			modelAndView.addObject("changCi", changCi);
			modelAndView.addObject("play", play);
			modelAndView.addObject("messageError", validateBeiShuResult);
			logger.info("LotteryJcBasketballController/betDetail竞彩篮球验证未通过validateBeiShuResult："+validateBeiShuResult);
			modelAndView.setViewName("jcBasketball/jcBasketballBetDetail");
			return modelAndView;

		}
		long zhuShu = 0;
		String betCodes="";
		for (String p : plays) {
			if("BSK003".equals(wanFa)){
				long zhu = lotteryJcBasketballUtil.getJclqSFCBetZhushu(p, betCode);
				zhuShu+=zhu;
				betCodes+=p+"@"+betCode+"_"+2*zhu*Integer.parseInt(beiShu)+";";
			}else{
				long zhu = lotteryJcBasketballUtil.getJclqBetZhushu(p, betCode);
				zhuShu+=zhu;
				betCodes+=p+"@"+betCode+"_"+2*zhu*Integer.parseInt(beiShu)+";";

			}
		}
		if(betCodes.endsWith(";")) betCodes = betCodes.substring(0, betCodes.length()-1);
		long amount = 2*zhuShu*Integer.parseInt(beiShu);
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(2, Integer.parseInt(beiShu), (int)zhuShu);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView.addObject("type", type);
			modelAndView.addObject("valueType", valueType);
			modelAndView.addObject("wanFa", wanFa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeViewArrayToString", betCodeViewArrayToString);
			modelAndView.addObject("submitType", submitType);
			modelAndView.addObject("changCi", changCi);
			modelAndView.addObject("play", play);
			modelAndView.addObject("messageError", validateAmountResult);
			logger.info("LotteryJcBasketballController/betDetail竞彩篮球验证金额未通过validateAmountResult："+validateAmountResult);

			modelAndView.setViewName("jcBasketball/jcBasketballBetDetail");
			return modelAndView;
		}
		logger.info("LotteryJcBasketballController/betDetail竞彩篮球验证通过");
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotNo = lotteryJcUtil.getLotNoByWanFa(wanFa);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("oneAmount", 2);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCodes);
		modelAndView.addObject("betCodeView", betCodeViewArrayToString);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("jcBetDetail");
		return modelAndView;
		
	}
}
