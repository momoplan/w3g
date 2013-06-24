package com.ruyicai.wap.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.NewsService;
import com.ruyicai.wap.vo.ActivityNews;
import com.ruyicai.wap.vo.News;

@Controller
@RequestMapping("/news")
public class NewsController {
	private static final Logger logger = Logger.getLogger(NewsController.class);
	@Autowired
	NewsService newsService;
	/**
	 * 查询新闻列表
	 * @param type
	 * @param startLine
	 * @param endLine
	 * @param nowPage
	 * @return
	 */
	@RequestMapping("/selectNewsList")
	public ModelAndView selectNewsList(
			@RequestParam(value ="type",defaultValue="2") String type,
			@RequestParam(value ="startLine",defaultValue="0") int startLine,
			@RequestParam(value ="endLine",defaultValue="10") int endLine,
			@RequestParam(value ="nowPage",defaultValue="1") int nowPage
			){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("NewsController/selectNewsList查询新闻列表参数type:"+type+",nowPage:"+nowPage);
		List<News> newsList = new ArrayList<News>();
		int maxLine = 10;
		newsList = newsService.selectNewsList(type, startLine, maxLine);
		int count = newsService.selectNewsCount(type);
		int totalPage = count%maxLine==0 ? count/maxLine : count/maxLine +1;
		logger.info("count:"+count+"totalPage:"+totalPage+"nowPage:"+nowPage+"type:"+type+"startLine:"+startLine+"endLine:"+endLine);
		String typeName = getTypeName(type);
		modelAndView.addObject("startLine", startLine);
		modelAndView.addObject("maxLine", maxLine);
		modelAndView.addObject("nowPage", nowPage);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("newsList", newsList);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.setViewName("newsList");
		return modelAndView;
	}
	/**
	 * 查询新闻内容
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/selectNews")
	public ModelAndView selectNews(
		@RequestParam(value="id",defaultValue="") String id,
		@RequestParam(value="type",defaultValue="2") String type
		){
		logger.info("NewsController/selectNews查询新闻内容参数id:"+id+",type:"+type);
		ModelAndView modelAndView = new ModelAndView();
		News news = new News();
		news = newsService.selectNews(id);
		String updatetime = news.getUpdatetime();
		updatetime = updatetime.substring(0, 10);
		news.setUpdatetime(updatetime);
		String createTime = news.getCreatetime();
		createTime = createTime.substring(0, 10);
		news.setCreatetime(createTime);
		String typeName = getTypeName(type);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("news", news);
		modelAndView.setViewName("newsContent");
		return modelAndView;
	}
	/**
	 * 查询新闻活动列表
	 * @param type
	 * @param startLine
	 * @param endLine
	 * @param nowPage
	 * @return
	 */
	@RequestMapping("/selectActivityNewsList")
	public ModelAndView selectActivityNewsList(
			@RequestParam(value ="type",defaultValue="6") String type,
			@RequestParam(value ="startLine",defaultValue="0") int startLine,
			@RequestParam(value ="endLine",defaultValue="10") int endLine,
			@RequestParam(value ="nowPage",defaultValue="1") int nowPage){
		logger.info("NewsController/selectActivityNewsList查询新闻活动列表参数type:"+type+",nowPage:"+nowPage);
		ModelAndView modelAndView = new ModelAndView();
		List<ActivityNews> activityNewsList = new ArrayList<ActivityNews>();
		int maxLine = 10;
		activityNewsList = newsService.selectActivityNewsList(type, startLine, maxLine);
		int count = newsService.selectActivityNewsCount(type);
		int totalPage = count%maxLine==0 ? count/maxLine : count/maxLine +1;
		logger.info("count:"+count+"totalPage:"+totalPage+"nowPage:"+nowPage+"type:"+type+"startLine:"+startLine+"endLine:"+endLine);
		String typeName = getTypeName(type);
		modelAndView.addObject("startLine", startLine);
		modelAndView.addObject("maxLine", maxLine);
		modelAndView.addObject("nowPage", nowPage);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("activityNewsList", activityNewsList);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.setViewName("newsList");
		return modelAndView;
	}
	/**
	 * 查询新闻活动内容
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/selectActivityNews")
	public ModelAndView selectActivityNews(
		@RequestParam(value="id",defaultValue="") String id,
		@RequestParam(value="type",defaultValue="6") String type){
		logger.info("NewsController/selectActivityNews查询新闻活动内容参数id:"+id+",type:"+type);
		ModelAndView modelAndView = new ModelAndView();
		ActivityNews activityNews = new ActivityNews();
		activityNews = newsService.selectActivityNews(id);
//		String activitytime = activityNews.getActivitytime();
//		activitytime = activitytime.substring(0, 10);
//		activityNews.setActivitytime(activitytime);
		String createTime = activityNews.getCreatetime();
		createTime = createTime.substring(0, 10);
		activityNews.setCreatetime(createTime);
		String typeName = getTypeName(type);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("activityNews", activityNews);
		modelAndView.setViewName("newsContent");
		return modelAndView;
	}
	public String getTypeName(String type){
		String typeName = "";
		if("1".equals(type)){
			typeName = "彩民趣闻";
		}else if("2".equals(type)){
			typeName = "专家推荐";
		}else if("3".equals(type)){
			typeName = "足彩天地";
		}else if("4".equals(type)){
			typeName = "如意公告";
		}else if("6".equals(type)){//自己定义
		typeName = "如意活动";
		}
		return typeName;
	}
}
