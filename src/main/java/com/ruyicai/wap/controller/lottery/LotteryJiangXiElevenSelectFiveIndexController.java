package com.ruyicai.wap.controller.lottery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@Controller
@RequestMapping("/jiangXiElevenSelectFiveIndex")
public class LotteryJiangXiElevenSelectFiveIndexController {
	Logger logger = Logger.getLogger(LotteryJiangXiElevenSelectFiveIndexController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byOptionOneSelf")
	public ModelAndView byOptionOneSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionOneSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionOneSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionOneAuto")
	public ModelAndView byOptionOneAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionOneAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionOneAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoSelf")
	public ModelAndView byOptionTwoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionTwoSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionTwoSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoAuto")
	public ModelAndView byOptionTwoAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionTwoAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionTwoAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeSelf")
	public ModelAndView byOptionThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionThreeSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionThreeSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeAuto")
	public ModelAndView byOptionThreeAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionThreeAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionThreeAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourSelf")
	public ModelAndView byOptionFourSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFourSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFourSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourAuto")
	public ModelAndView byOptionFourAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFourAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFourAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveSelf")
	public ModelAndView byOptionFiveSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFiveSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFiveSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveAuto")
	public ModelAndView byOptionFiveAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFiveAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFiveAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixSelf")
	public ModelAndView byOptionSixSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSixSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSixSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixAuto")
	public ModelAndView byOptionSixAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSixAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSixAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenSelf")
	public ModelAndView byOptionSevenSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSevenSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSevenSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenAuto")
	public ModelAndView byOptionSevenAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSevenAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSevenAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightSelf")
	public ModelAndView byOptionEightSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionEightSelf");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionEightSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightAuto")
	public ModelAndView byOptionEightAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionEightAuto");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionEightAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoDanTuo")
	public ModelAndView byOptionTwoDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionTwoDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionTwoDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeDanTuo")
	public ModelAndView byOptionThreeDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionThreeDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionThreeDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourDanTuo")
	public ModelAndView byOptionFourDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFourDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFourDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveDanTuo")
	public ModelAndView byOptionFiveDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionFiveDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionFiveDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixDanTuo")
	public ModelAndView byOptionSixDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSixDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSixDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenDanTuo")
	public ModelAndView byOptionSevenDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionSevenDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionSevenDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightDanTuo")
	public ModelAndView byOptionEightDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "optionEightDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byOptionEightDanTuo");
		return modelAndView;
	}
	
	@RequestMapping("/byForwardTwoGroupSelectDanTuo")
	public ModelAndView byForwardTwoGroupSelectDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardTwoGroupSelectDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelectDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeGroupSelectDanTuo")
	public ModelAndView byForwardThreeGroupSelectDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardThreeGroupSelectDanTuo");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelectDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byForwardTwoGroupSelect")
	public ModelAndView byForwardTwoGroupSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardTwoGroupSelect");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoGroupSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeGroupSelect")
	public ModelAndView byForwardThreeGroupSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardThreeGroupSelect");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeGroupSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardTwoDirectSelect")
	public ModelAndView byForwardTwoDirectSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardTwoDirectSelect");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardTwoDirectSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeDirectSelect")
	public ModelAndView byForwardThreeDirectSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLC_JX11X5);
		modelAndView.addObject("betType", "forwardThreeDirectSelect");
		modelAndView.setViewName("jiangXiElevenSelectFive/byForwardThreeDirectSelect");
		return modelAndView;
	}
}
