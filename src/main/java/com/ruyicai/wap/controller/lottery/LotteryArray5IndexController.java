package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@RequestMapping("/array5Index")
@Controller
public class LotteryArray5IndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		modelAndView.addObject("betType", "self");
		modelAndView.setViewName("array5/bySelf");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL5);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("array5/byAuto");
		return modelAndView;
	}
}
