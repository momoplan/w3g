package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@RequestMapping("/daLeTouIndex")
@Controller
public class LotteryDaLeTouIndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/bySelf")
	public ModelAndView bySelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		modelAndView.addObject("betType", "self");
		modelAndView.setViewName("daLeTou/bySelf");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("daLeTou/byAuto");
		return modelAndView;
	}
	@RequestMapping("/byDanTuo")
	public ModelAndView byDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("betType", "danTuo");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		modelAndView.setViewName("daLeTou/byDanTuo");
		return modelAndView;
	}
	@RequestMapping("/byShengXiaoLe")
	public ModelAndView byShengXiaoLe(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("betType", "shengXiaoLe");
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_DLT);
		modelAndView.setViewName("daLeTou/byShengXiaoLe");
		return modelAndView;
	}
}
