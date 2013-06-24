package com.ruyicai.wap.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.Ranking;

@RequestMapping("/winningRankingHistory")
@Controller
public class WinningRankingHistoryController {
	private static final Logger logger = Logger.getLogger(WinningRankingHistoryController.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	/**
	 * 用户中奖历史排行
	 * @param type
	 * @return
	 */
	@RequestMapping("/userRankingHistory")
	public ModelAndView userRankingHistory(
			@RequestParam(value="type",defaultValue="5") int type){
		logger.info("WinningRankingHistoryController/userRankingHistory用户中奖历史排行type:"+type);
		ModelAndView modelAndView = new ModelAndView();
		//得到时间格式
		String time = getTime(type);
		List<Ranking> userRankingHistoryList = selectAllUtil.selectUserRankingHistory(time, type);
		modelAndView.addObject("userRankingHistoryList", userRankingHistoryList);
		modelAndView.addObject("type", type);
		modelAndView.setViewName("userRankingHistory");
		return modelAndView;
	}
	/**
	 * 中奖历史排行根据排行类型得到时间格式
	 * DAY(1, "日统计"), WEEK(2, "周统计"), MONTH(3, "月统计"), YEAR(4, "年统计"), ALL(5, "总排行")
	 * 日统计和周统计。格式是yyyy-MM-dd月统计yyyy-MM年统计yyyy总排行all
	 * @param type
	 * @return
	 */
	public String getTime(int type){
		String time = "";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = dateFormat.format(date);
		if(type==1){
			time = dateTime;
		}else if(type==2){
			time = dateTime;
		}else if(type==3){
			time = dateTime.substring(0, dateTime.length()-3);
		}else if(type==4){
			time = dateTime.substring(0, dateTime.length()-6);
		}else if(type==5){
			time = "all";
		}
		return time;
	}
}
