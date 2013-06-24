package com.ruyicai.wap.dao.Impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruyicai.wap.dao.NewsDao;
import com.ruyicai.wap.vo.ActivityNews;
import com.ruyicai.wap.vo.News;
@Repository
public class NewsDaoImpl implements NewsDao {
	Logger logger = Logger.getLogger(NewsDaoImpl.class);
	@Resource
	JdbcTemplate noJdbcTemplate;

	@Override
	public List<News> selectNewsList(String type, int startLine, int endLine) {
		List<News> newsList = new ArrayList<News>();
		String sql = "select * from client.vol_news where vol_typeid_fk=? order by id desc limit ?,?";
		List<Map<String, Object>> newsMapList = noJdbcTemplate.queryForList(sql,new Object[]{type,startLine,endLine});
		logger.info("SQL=="+sql);
		for (Map<String, Object> newsMap : newsMapList) {
			News news = new News();
			news.setAuthor(newsMap.get("author")+"");
			news.setCreatetime(newsMap.get("createtime")+"");
			news.setId(newsMap.get("id")+"");
			news.setLotno(newsMap.get("lotno")+"");
			news.setState(newsMap.get("state")+"");
			news.setUpdatetime(newsMap.get("updatetime")+"");
			news.setVol_content(newsMap.get("vol_content")+"");
			news.setVol_title(newsMap.get("vol_title")+"");
			news.setVol_typeid_fk(newsMap.get("vol_typeid_fk")+"");
			newsList.add(news);
		}
		return newsList;
	}


	@Override
	public News selectNews(String id) {
		String sql = "select * from client.vol_news where id=?";
		Map<String, Object> newsMap = noJdbcTemplate.queryForMap(sql, new Object[]{id});
		logger.info("SQL=="+sql);
		News news = new News();
		news.setAuthor(newsMap.get("author")+"");
		news.setCreatetime(newsMap.get("createtime")+"");
		news.setId(newsMap.get("id")+"");
		news.setLotno(newsMap.get("lotno")+"");
		news.setState(newsMap.get("state")+"");
		news.setUpdatetime(newsMap.get("updatetime")+"");
		news.setVol_content(newsMap.get("vol_content")+"");
		news.setVol_title(newsMap.get("vol_title")+"");
		news.setVol_typeid_fk(newsMap.get("vol_typeid_fk")+"");
		return news;
	}

	@Override
	public int selectNewsCount(String type) {
		String sql = "select count(*) from client.vol_news where state=1 and vol_typeid_fk=?";
		logger.info("SQL=="+sql);
		int count = noJdbcTemplate.queryForInt(sql,new Object[]{type});
		return count;
	}

	@Override
	public List<ActivityNews> selectActivityNewsList(String type,
			int startLine, int endLine) {
		List<ActivityNews> activityNewsList = new ArrayList<ActivityNews>();
		String sql = "select * from client.vol_activity where productno=1 order by id desc limit ?,?";
		List<Map<String, Object>> activityNewsMapList = noJdbcTemplate.queryForList(sql,new Object[]{startLine,endLine});
		logger.info("SQL=="+sql);
		for (Map<String, Object> map : activityNewsMapList) {
			ActivityNews activityNews = new ActivityNews();
			activityNews.setActivitytime(map.get("activitytime")+"");
			activityNews.setAmount(map.get("amount")+"");
			activityNews.setContent(map.get("content")+"");
			activityNews.setCreatetime(map.get("createtime")+"");
			activityNews.setId(map.get("id")+"");
			activityNews.setIntroduce(map.get("introduce")+"");
			activityNews.setIsend(map.get("isend")+"");
			activityNews.setTitle(map.get("title")+"");
			activityNews.setType(map.get("type")+"");
			activityNews.setUrl(map.get("url")+"");
			activityNewsList.add(activityNews);
		}
		return activityNewsList;
	}

	@Override
	public ActivityNews selectActivityNews(String id) {
		String sql = "select * from client.vol_activity where id=?";
		Map<String, Object> map = noJdbcTemplate.queryForMap(sql, new Object[]{id});
		logger.info("SQL=="+sql);
		ActivityNews activityNews = new ActivityNews();
		activityNews.setActivitytime(map.get("activitytime")+"");
		activityNews.setAmount(map.get("amount")+"");
		activityNews.setContent(map.get("content")+"");
		activityNews.setCreatetime(map.get("createtime")+"");
		activityNews.setId(map.get("id")+"");
		activityNews.setIntroduce(map.get("introduce")+"");
		activityNews.setIsend(map.get("isend")+"");
		activityNews.setTitle(map.get("title")+"");
		activityNews.setType(map.get("type")+"");
		activityNews.setUrl(map.get("url")+"");
		return activityNews;
	}

	@Override
	public int selectActivityNewsCount(String type) {
		String sql = "select count(*) from client.vol_activity where productno=1";
		logger.info("SQL=="+sql);
		int count = noJdbcTemplate.queryForInt(sql);
		return count;
	}
}
