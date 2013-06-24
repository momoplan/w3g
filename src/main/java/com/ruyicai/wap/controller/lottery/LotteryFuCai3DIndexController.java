package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;

@Controller
@RequestMapping("/fuCai3DIndex")
public class LotteryFuCai3DIndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byDirect")
	public ModelAndView byDirect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "direct");
		modelAndView.setViewName("fuCai3D/byDirect");
		return modelAndView;
	}
	@RequestMapping("/byAuto")
	public ModelAndView byAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "auto");
		modelAndView.setViewName("fuCai3D/byAuto");
		return modelAndView;
	}
//	@RequestMapping("/byGroup3SelfSingle")
//	public ModelAndView byGroup3SelfSingle(){
//		ModelAndView modelAndView = new ModelAndView();
//		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
//		modelAndView.addObject("betType", "group3SelfSingle");
//		modelAndView.setViewName("fuCai3D/byGroup3SelfSingle");
//		return modelAndView;
//	}
	@RequestMapping("/byGroup3Self")
	public ModelAndView byGroup3Self(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "group3Self");
		modelAndView.setViewName("fuCai3D/byGroup3Self");
		return modelAndView;
	}
	@RequestMapping("/byGroup6Self")
	public ModelAndView byGroup6Self(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "group6Self");
		modelAndView.setViewName("fuCai3D/byGroup6Self");
		return modelAndView;
	}
	@RequestMapping("/byDirectHeZhi")
	public ModelAndView byDirectHeZhi(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "directHeZhi");
		modelAndView.setViewName("fuCai3D/byDirectHeZhi");
		return modelAndView;
	}
	@RequestMapping("/byGroup3HeZhi")
	public ModelAndView byGroup3HeZhi(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "group3HeZhi");
		modelAndView.setViewName("fuCai3D/byGroup3HeZhi");
		return modelAndView;
	}
	@RequestMapping("/byGroup6HeZhi")
	public ModelAndView byGroup6HeZhi(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "group6HeZhi");
		modelAndView.setViewName("fuCai3D/byGroup6HeZhi");
		return modelAndView;
	}
	@RequestMapping("/byDanTuo")
	public ModelAndView byDanTuo(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_FC3D);
		modelAndView.addObject("betType", "danTuo");
		modelAndView.setViewName("fuCai3D/byDanTuo");
		return modelAndView;
	}
}
