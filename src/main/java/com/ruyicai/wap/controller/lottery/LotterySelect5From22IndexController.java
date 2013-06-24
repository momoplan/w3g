package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@RequestMapping("/select5From22Index")
@Controller
public class LotterySelect5From22IndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_22X5);
		modelAndView.addObject("betType", "self");
		modelAndView.setViewName("select5From22/bySelf");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_22X5);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("select5From22/byAuto");
		return modelAndView;
	}
	@RequestMapping("/byDanTuo")
	public ModelAndView byDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("betType", "danTuo");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_22X5);
		modelAndView.setViewName("select5From22/byDanTuo");
		return modelAndView;
	}
}
