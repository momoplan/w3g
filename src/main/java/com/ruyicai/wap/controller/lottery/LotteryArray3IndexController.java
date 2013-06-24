package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;
@RequestMapping("/array3Index")
@Controller
public class LotteryArray3IndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byDirect")
	public ModelAndView byDirect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL3);
		modelAndView.addObject("betType", "direct");
		modelAndView.setViewName("array3/byDirect");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL3);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("array3/byAuto");
		return modelAndView;
	}
	@RequestMapping("/byGroup3Self")
	public ModelAndView byGroup3Self(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL3);
		modelAndView.addObject("betType", "group3Self");
		modelAndView.setViewName("array3/byGroup3Self");
		return modelAndView;
	}
	@RequestMapping("/byGroup6Self")
	public ModelAndView byGroup6Self(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL3);
		modelAndView.addObject("betType", "group6Self");
		modelAndView.setViewName("array3/byGroup6Self");
		return modelAndView;
	}
	@RequestMapping("/byDirectHeZhi")
	public ModelAndView byDirectHeZhi(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_PL3);
		modelAndView.addObject("betType", "directHeZhi");
		modelAndView.setViewName("array3/byDirectHeZhi");
		return modelAndView;
	}
}
