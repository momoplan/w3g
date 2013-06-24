package com.ruyicai.wap.controller.lottery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@Controller
@RequestMapping("/guangDongElevenSelectFiveIndex")
public class LotteryGuangDongElevenSelectFiveIndexController {
	Logger logger = Logger.getLogger(LotteryGuangDongElevenSelectFiveIndexController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byOptionOneSelf")
	public ModelAndView byOptionOneSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionOneSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionOneSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionOneAuto")
	public ModelAndView byOptionOneAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionOneAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionOneAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoSelf")
	public ModelAndView byOptionTwoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionTwoSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionTwoSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoAuto")
	public ModelAndView byOptionTwoAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionTwoAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionTwoAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeSelf")
	public ModelAndView byOptionThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionThreeSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionThreeSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeAuto")
	public ModelAndView byOptionThreeAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionThreeAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionThreeAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourSelf")
	public ModelAndView byOptionFourSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFourSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFourSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourAuto")
	public ModelAndView byOptionFourAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFourAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFourAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveSelf")
	public ModelAndView byOptionFiveSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFiveSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFiveSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveAuto")
	public ModelAndView byOptionFiveAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFiveAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFiveAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixSelf")
	public ModelAndView byOptionSixSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSixSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSixSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixAuto")
	public ModelAndView byOptionSixAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSixAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSixAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenSelf")
	public ModelAndView byOptionSevenSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSevenSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSevenSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenAuto")
	public ModelAndView byOptionSevenAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSevenAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSevenAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightSelf")
	public ModelAndView byOptionEightSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionEightSelf");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionEightSelf");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightAuto")
	public ModelAndView byOptionEightAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionEightAuto");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionEightAuto");
		return modelAndView;
	}
	@RequestMapping("/byOptionTwoDanTuo")
	public ModelAndView byOptionTwoDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionTwoDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionTwoDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionThreeDanTuo")
	public ModelAndView byOptionThreeDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionThreeDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionThreeDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionFourDanTuo")
	public ModelAndView byOptionFourDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFourDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFourDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionFiveDanTuo")
	public ModelAndView byOptionFiveDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionFiveDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionFiveDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionSixDanTuo")
	public ModelAndView byOptionSixDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSixDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSixDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionSevenDanTuo")
	public ModelAndView byOptionSevenDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionSevenDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionSevenDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byOptionEightDanTuo")
	public ModelAndView byOptionEightDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "optionEightDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byOptionEightDanTuo");
		return modelAndView;
	}
	
	@RequestMapping("/byForwardTwoGroupSelectDanTuo")
	public ModelAndView byForwardTwoGroupSelectDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardTwoGroupSelectDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardTwoGroupSelectDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeGroupSelectDanTuo")
	public ModelAndView byForwardThreeGroupSelectDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardThreeGroupSelectDanTuo");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardThreeGroupSelectDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byForwardTwoGroupSelect")
	public ModelAndView byForwardTwoGroupSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardTwoGroupSelect");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardTwoGroupSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeGroupSelect")
	public ModelAndView byForwardThreeGroupSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardThreeGroupSelect");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardThreeGroupSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardTwoDirectSelect")
	public ModelAndView byForwardTwoDirectSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardTwoDirectSelect");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardTwoDirectSelect");
		return modelAndView;
	}
	@RequestMapping("/byForwardThreeDirectSelect")
	public ModelAndView byForwardThreeDirectSelect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GD11X5);
		modelAndView.addObject("betType", "forwardThreeDirectSelect");
		modelAndView.setViewName("guangDongElevenSelectFive/byForwardThreeDirectSelect");
		return modelAndView;
	}
	
}
