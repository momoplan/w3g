package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@Controller
@RequestMapping("/doubleBallIndex")
public class LotteryDoubleBallIndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSQ);
		modelAndView.addObject("betType", "self");
		modelAndView.setViewName("doubleBall/bySelf");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSQ);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("doubleBall/byAuto");
		return modelAndView;
	}
	@RequestMapping("/byDanTuo")
	public ModelAndView byDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("betType", "danTuo");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSQ);
		modelAndView.setViewName("doubleBall/byDanTuo");
		return modelAndView;
	}
}
