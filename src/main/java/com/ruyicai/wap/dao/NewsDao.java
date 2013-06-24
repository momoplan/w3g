package com.ruyicai.wap.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruyicai.wap.vo.ActivityNews;
import com.ruyicai.wap.vo.News;
@Repository
public interface NewsDao {
	/**
	 * 通新闻类型查询新闻
	 * @param type
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public List<News> selectNewsList(String type,int startLine,int endLine);
	/**
	 * 通过新闻ID查询新闻
	 * @param id
	 * @return
	 */
	public News selectNews(String id);
	/**
	 * 通过新闻类型查询新闻条数
	 * @param type
	 * @return
	 */
	public int selectNewsCount(String type);
	/**
	 * 通过类型查询新闻活动
	 * @param type
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public List<ActivityNews> selectActivityNewsList(String type,int startLine,int endLine);
	/**
	 * 通过ID查询新闻活动
	 * @param id
	 * @return
	 */
	public ActivityNews selectActivityNews(String id);
	/**
	 * 通过类型查询新闻活动条数
	 * @param type
	 * @return
	 */
	public int selectActivityNewsCount(String type);

}
