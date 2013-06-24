package com.ruyicai.wap.controller.lottery;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryUtil;

@Controller
@RequestMapping("/guangDongHappyTenMinutesIndex")
public class LotteryGuangDongHappyTenMinutesIndexController {
	Logger logger = Logger.getLogger(LotteryGuangDongHappyTenMinutesIndexController.class);
	@Autowired
	LotteryUtil lotteryUtil;
	/**
	 * 选一数投
	 * @return
	 */
	@RequestMapping("/bySelectOneNumberSelf")
	public ModelAndView bySelectOneNumberSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "S1");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectOneNumberSelf");
		return modelAndView;
	}
	/**
	 * 选一红投
	 * @return
	 */
	@RequestMapping("/bySelectOneRedSelf")
	public ModelAndView bySelectOneRedSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "H1");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectOneRedSelf");
		return modelAndView;
	}
	/**
	 * 任选二自选
	 * @return
	 */
	@RequestMapping("/byOptionTwoSelf")
	public ModelAndView byOptionTwoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R2");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionTwoSelf");

		return modelAndView;
	}
	/**
	 * 任选三自选
	 * @return
	 */
	@RequestMapping("/byOptionThreeSelf")
	public ModelAndView byOptionThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionThreeSelf");
		return modelAndView;
	}
	/**
	 * 任选四自选
	 * @return
	 */
	@RequestMapping("/byOptionFourSelf")
	public ModelAndView byOptionFourSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R4");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFourSelf");
		return modelAndView;
	}
	/**
	 * 任选五自选
	 * @return
	 */
	@RequestMapping("/byOptionFiveSelf")
	public ModelAndView byOptionFiveSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R5");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFiveSelf");
		return modelAndView;
	}
	/**
	 * 选二连直
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkDirectSelf")
	public ModelAndView bySelectTwoLinkDirectSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Q2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSelf");
		return modelAndView;
	}
	/**
	 * 选二连组
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkGroupSelf")
	public ModelAndView bySelectTwoLinkGroupSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Z2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSelf");
		return modelAndView;
	}
	/**
	 * 直选前三
	 * @return
	 */
	@RequestMapping("/byDirectSelectForwardThreeSelf")
	public ModelAndView byDirectSelectForwardThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Q3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSelf");
		return modelAndView;
	}
	/**
	 * 组选前三
	 * @return
	 */
	@RequestMapping("/byGroupSelectForwardThreeSelf")
	public ModelAndView byGroupSelectForwardThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Z3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSelf");
		return modelAndView;
	}
	/**
	 * 任选二胆拖自选
	 * @return
	 */
	@RequestMapping("/byOptionTwoDanTuoSelf")
	public ModelAndView byOptionTwoDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|R2");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionTwoDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选三胆拖自选
	 * @return
	 */
	@RequestMapping("/byOptionThreeDanTuoSelf")
	public ModelAndView byOptionThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|R3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionThreeDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选四胆拖自选
	 * @return
	 */
	@RequestMapping("/byOptionFourDanTuoSelf")
	public ModelAndView byOptionFourDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|R4");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFourDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选五胆拖自选
	 * @return
	 */
	@RequestMapping("/byOptionFiveDanTuoSelf")
	public ModelAndView byOptionFiveDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|R5");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFiveDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 选二连直胆拖
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkDirectDanTuoSelf")
	public ModelAndView bySelectTwoLinkDirectDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|Q2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 选二连组胆拖
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkGroupDanTuoSelf")
	public ModelAndView bySelectTwoLinkGroupDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|Z2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 直选前三胆拖
	 * @return
	 */
	@RequestMapping("/byDirectSelectForwardThreeDanTuoSelf")
	public ModelAndView byDirectSelectForwardThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|Q3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 组选前三胆拖
	 * @return
	 */
	@RequestMapping("/byGroupSelectForwardThreeDanTuoSelf")
	public ModelAndView byGroupSelectForwardThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "D|Z3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeDanTuoSelf");
		return modelAndView;
	}
	/***************************机选**************************************/
	/**
	 * 选一数投单式机选
	 * @return
	 */
	@RequestMapping("/bySelectOneNumberSingleAuto")
	public ModelAndView bySelectOneNumberSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "S1");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectOneNumberSingleAuto");
		return modelAndView;
	}
	/**
	 * 选一红投单式机选
	 * @return
	 */
	@RequestMapping("/bySelectOneRedSingleAuto")
	public ModelAndView bySelectOneRedSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "H1");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectOneRedSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选二单式机选
	 * @return
	 */
	@RequestMapping("/byOptionTwoSingleAuto")
	public ModelAndView byOptionTwoSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R2");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionTwoSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选三单式机选
	 * @return
	 */
	@RequestMapping("/byOptionThreeSingleAuto")
	public ModelAndView byOptionThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionThreeSingleAuto");
		return modelAndView;
	}
	/**任选四单式机选
	 * @return
	 */
	@RequestMapping("/byOptionFourSingleAuto")
	public ModelAndView byOptionFourSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R4");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFourSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选五单式机选
	 * @return
	 */
	@RequestMapping("/byOptionFiveSingleAuto")
	public ModelAndView optionFiveSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "R5");
		modelAndView.setViewName("guangDongHappyTenMinutes/byOptionFiveSingleAuto");
		return modelAndView;
	}
	/**
	 * 选二连直单式机选
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkDirectSingleAuto")
	public ModelAndView bySelectTwoLinkDirectSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Q2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkDirectSingleAuto");
		return modelAndView;
	}
	/**
	 * 选二连组单式机选
	 * @return
	 */
	@RequestMapping("/bySelectTwoLinkGroupSingleAuto")
	public ModelAndView bySelectTwoLinkGroupSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Z2");
		modelAndView.setViewName("guangDongHappyTenMinutes/bySelectTwoLinkGroupSingleAuto");
		return modelAndView;
	}
	/**
	 * 直选前三单式机选
	 * @return
	 */
	@RequestMapping("/byDirectSelectForwardThreeSingleAuto")
	public ModelAndView byDirectSelectForwardThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Q3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byDirectSelectForwardThreeSingleAuto");
		return modelAndView;
	}
	/**
	 * 组选前三单式机选
	 * @return
	 */
	@RequestMapping("/byGroupSelectForwardThreeSingleAuto")
	public ModelAndView byGroupSelectForwardThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		lotteryUtil.getModelAndView(modelAndView, Constants.LOTNO_GDKL10F);
		modelAndView.addObject("betType", "Z3");
		modelAndView.setViewName("guangDongHappyTenMinutes/byGroupSelectForwardThreeSingleAuto");
		return modelAndView;
	}
}
