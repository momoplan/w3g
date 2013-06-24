package com.ruyicai.wap.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.wap.dao.NewsDao;
import com.ruyicai.wap.service.NewsService;
import com.ruyicai.wap.vo.ActivityNews;
import com.ruyicai.wap.vo.News;
@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsDao newsDao;

	@Override
	public List<News> selectNewsList(String type, int startLine, int endLine) {
		return newsDao.selectNewsList(type, startLine, endLine);
	}

	@Override
	public News selectNews(String id) {
		return newsDao.selectNews(id);
	}

	@Override
	public int selectNewsCount(String type) {
		return newsDao.selectNewsCount(type);
	}

	@Override
	public List<ActivityNews> selectActivityNewsList(String type,
			int startLine, int endLine) {
		return newsDao.selectActivityNewsList(type, startLine, endLine);
	}

	@Override
	public ActivityNews selectActivityNews(String id) {
		return newsDao.selectActivityNews(id);
	}

	@Override
	public int selectActivityNewsCount(String type) {
		return newsDao.selectActivityNewsCount(type);
	}
	

}
