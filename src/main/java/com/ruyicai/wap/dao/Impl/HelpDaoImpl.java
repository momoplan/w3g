package com.ruyicai.wap.dao.Impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruyicai.wap.dao.HelpDao;
import com.ruyicai.wap.vo.Help;
@Repository
public class HelpDaoImpl implements HelpDao {
	Logger logger = Logger.getLogger(HelpDaoImpl.class);
	@Resource
	JdbcTemplate noJdbcTemplate;
	@Override
	public List<Help> selectHelpType(String type) {
		List<Help> helpList = new ArrayList<Help>();
		String sql = "select * from client.helpcenter where type=? and platform='wap'";
		List<Map<String, Object>> helpMapList = noJdbcTemplate.queryForList(sql,new Object[]{type});
		logger.info("SQL=="+sql);
		for (Map<String, Object> helpMap : helpMapList) {
			Help help = new Help();
			help.setId(helpMap.get("id")+"");
			help.setType(helpMap.get("type")+"");
			help.setPlatform(helpMap.get("platform")+"");
			help.setTitle(helpMap.get("title")+"");
			help.setContent(helpMap.get("content")+"");
			helpList.add(help);
		}
		return helpList;
	}

	@Override
	public Help selectHelp(String id) {
		String sql = "select * from client.helpcenter where id=?";
		Map<String, Object> helpMap = noJdbcTemplate.queryForMap(sql,new Object[]{id});
		logger.info("SQL=="+sql);
		Help help = new Help();
		help.setId(helpMap.get("id")+"");
		help.setType(helpMap.get("type")+"");
		help.setPlatform(helpMap.get("platform")+"");
		help.setTitle(helpMap.get("title")+"");
		help.setContent(helpMap.get("content")+"");
		return help;
	}
}
