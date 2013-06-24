package com.ruyicai.wap.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.UserInfoService;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.UserInfoUtil;
import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;
import com.ruyicai.wap.vo.Score;
import com.ruyicai.wap.vo.UserInfo;


@Controller
@RequestMapping("/scoreCenter")
public class ScoreCenterController {
	Logger logger = Logger.getLogger(ScoreCenterController.class);
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	UserInfoUtil userInfoUtil;
	@Autowired
	ValidateUserInfoUtil validateUserInfoUtil;
	/**
	 * 兑换积分
	 * @param tradScore兑换积分
	 * @param score总积分
	 * @param handsel彩金
	 * @return
	 */
	@RequestMapping("/transScore.do")
	public ModelAndView transScore(
			@RequestParam(value="tradScore",defaultValue="")String tradScore,
			@RequestParam(value="totalScore",defaultValue="")String totalScore,
			@RequestParam(value="handsel",defaultValue="")String handsel,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ScoreCenterController/transScore兑换积分参数tradScore:"+tradScore+",totalScore:"+totalScore+",handsel:"+handsel);
		String validateResult = validateUserInfoUtil.validateTransScore(tradScore, totalScore, handsel);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("tradScore", tradScore);
			modelAndView.addObject("totalScore", totalScore);
			modelAndView.addObject("handsel", handsel);
			modelAndView.setViewName("transScoreFail");
			logger.info("ScoreCenterController/transScore兑换积分参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ScoreCenterController/transScore兑换积分参数验证通过");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		Score score = userInfoUtil.transScore(userNo, tradScore);
		if(score!=null){
			modelAndView.addObject("score", score);
			modelAndView.addObject("messageError", "兑换积分成功！");
			modelAndView.setViewName("transScoreResult");
			logger.info("ScoreCenterController/transScore兑换积分成功");
		}else{
			modelAndView.addObject("messageError", "兑换积分失败！");
			modelAndView.setViewName("transScoreFail");
			logger.info("ScoreCenterController/transScore兑换积分失败");

		}
		return modelAndView;
	}
	/**
	 * 查询积分详细
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectScoreDetail.do")
	public ModelAndView selectScoreDetail(
			@RequestParam(value="startLine",defaultValue="0")String startLine,
			@RequestParam(value="endLine",defaultValue="10")String endLine,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		int maxLine = 10;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo"); 
		String userNo = userInfo.getUserNo();
		Score score = selectAllUtil.selectScore(userNo);
		Map<String, Object> map = selectAllUtil.selectScoreDetail(userNo, startLine, maxLine+"");
		if(map!=null){
			String currentPageNo = (String) map.get("currentPageNo");
			String totalPage = (String) map.get("totalPage");
			List<Score> scores = (List<Score>) map.get("scores");
			modelAndView.addObject("startLine",startLine);
			modelAndView.addObject("endLine",Integer.parseInt(endLine));
			modelAndView.addObject("nowPage",Integer.parseInt(currentPageNo));
			modelAndView.addObject("totalPage",Integer.parseInt(totalPage));
			modelAndView.addObject("maxLine",maxLine);
			modelAndView.addObject("scores",scores);
		}
		modelAndView.addObject("score",score);
		modelAndView.setViewName("scoreCenter");
		return modelAndView;
	}
}
