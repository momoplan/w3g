package com.ruyicai.wap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.NewsService;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.ActivityNews;
import com.ruyicai.wap.vo.News;

@Controller
public class IndexController {
	Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	WinInfoController winInfoController;
	@Autowired
	NewsService newsService;
	@Autowired
	SelectAllUtil selectAllUtil;
	/**
	 * 访问首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		List<News> announcementNewsList = new ArrayList<News>();
		List<ActivityNews> activityNewsList = new ArrayList<ActivityNews>();
		List<News> informationNewsList = new ArrayList<News>();
		try {
			announcementNewsList = newsService.selectNewsList("4", 0, 1);//公告
			informationNewsList = newsService.selectNewsList("1", 0, 5);//资讯(彩民趣闻)
			activityNewsList = newsService.selectActivityNewsList("6", 0, 1);//活动
		} catch (Exception e) {
			logger.error("首页查询新闻信息异常:"+e.getMessage());
		}
		winInfoController.selectIndexWinInfo(modelAndView);
		modelAndView.addObject("informationNewsList", informationNewsList);
		modelAndView.addObject("activityNewsList", activityNewsList);
		modelAndView.addObject("announcementNewsList", announcementNewsList);
		modelAndView.setViewName("index");
		return modelAndView;
		
	}
	/**
	 * 得到首页上动态获取的信息
	 * @param modelAndView
	 * @return
	 */
	public ModelAndView getIndexModelAndView(ModelAndView modelAndView){
		List<News> announcementNewsList = new ArrayList<News>();
		List<ActivityNews> activityNewsList = new ArrayList<ActivityNews>();
		List<News> informationNewsList = new ArrayList<News>();
		try {
			announcementNewsList = newsService.selectNewsList("4", 0, 1);//公告
			informationNewsList = newsService.selectNewsList("1", 0, 5);//资讯(彩民趣闻)
			activityNewsList = newsService.selectActivityNewsList("6", 0, 1);//活动
		} catch (Exception e) {
			logger.error("首页查询新闻信息异常:"+e.getMessage());
		}
		winInfoController.selectIndexWinInfo(modelAndView);
		modelAndView.addObject("informationNewsList", informationNewsList);
		modelAndView.addObject("activityNewsList", activityNewsList);
		modelAndView.addObject("announcementNewsList", announcementNewsList);
		return modelAndView;
	}
}
