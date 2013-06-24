package com.ruyicai.wap.controller.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;

@Controller
@RequestMapping("/shiShiCaiIndex")
public class LotteryShiShiCaiIndexController {
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byOneStarSelf")
	public ModelAndView byOneStarSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "oneStarSelf");
		modelAndView.setViewName("shiShiCai/byOneStarSelf");
		return modelAndView;
	}
	@RequestMapping("/byOneStarAuto")
	public ModelAndView byOneStarAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "oneStarAuto");
		modelAndView.setViewName("shiShiCai/byOneStarAuto");
		return modelAndView;
	}
	@RequestMapping("/byTwoStarDirect")
	public ModelAndView byTwoStarDirect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "twoStarDirect");
		modelAndView.setViewName("shiShiCai/byTwoStarDirect");
		return modelAndView;
	}
	@RequestMapping("/byTwoStarAuto")
	public ModelAndView byTwoStarAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "twoStarAuto");
		modelAndView.setViewName("shiShiCai/byTwoStarAuto");
		return modelAndView;
	}
	@RequestMapping("/byTwoStarGroup")
	public ModelAndView byTwoStarGroup(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "twoStarGroup");
		modelAndView.setViewName("shiShiCai/byTwoStarGroup");
		return modelAndView;
	}
	@RequestMapping("/byTwoStarHeZhi")
	public ModelAndView byTwoStarHeZhi(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "twoStarHeZhi");
		modelAndView.setViewName("shiShiCai/byTwoStarHeZhi");
		return modelAndView;
	}
	@RequestMapping("/byThreeStarDirect")
	public ModelAndView byThreeStarDirect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "threeStarDirect");
		modelAndView.setViewName("shiShiCai/byThreeStarDirect");
		return modelAndView;
	}
	@RequestMapping("/byThreeStarAuto")
	public ModelAndView byThreeStarAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "threeStarAuto");
		modelAndView.setViewName("shiShiCai/byThreeStarAuto");
		return modelAndView;
	}
	@RequestMapping("/byThreeStarGroup3")
	public ModelAndView byThreeStarGroup3(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "threeStarGroup3");
		modelAndView.setViewName("shiShiCai/byThreeStarGroup3");
		return modelAndView;
	}
	@RequestMapping("/byThreeStarGroup6")
	public ModelAndView byThreeStarGroup6(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "threeStarGroup6");
		modelAndView.setViewName("shiShiCai/byThreeStarGroup6");
		return modelAndView;
	}
	@RequestMapping("/byFiveStarDirect")
	public ModelAndView byFiveStarDirect(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "fiveStarDirect");
		modelAndView.setViewName("shiShiCai/byFiveStarDirect");
		return modelAndView;
	}
	@RequestMapping("/byFiveStarAuto")
	public ModelAndView byFiveStarAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "fiveStarAuto");
		modelAndView.setViewName("shiShiCai/byFiveStarAuto");
		return modelAndView;
	}
	@RequestMapping("/byFiveStarTongSelf")
	public ModelAndView byFiveStarTongSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "fiveStarTongSelf");
		modelAndView.setViewName("shiShiCai/byFiveStarTongSelf");
		return modelAndView;
	}
	@RequestMapping("/byFiveStarTongAuto")
	public ModelAndView byFiveStarTongAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "fiveStarTongAuto");
		modelAndView.setViewName("shiShiCai/byFiveStarTongAuto");
		return modelAndView;
	}
	@RequestMapping("/byDaXiaoDanShuangSelf")
	public ModelAndView byDaXiaoDanShuangSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_SSC);
		modelAndView.addObject("betType", "daXiaoDanShuangSelf");
		modelAndView.setViewName("shiShiCai/byDaXiaoDanShuangSelf");
		return modelAndView;
	}
}
