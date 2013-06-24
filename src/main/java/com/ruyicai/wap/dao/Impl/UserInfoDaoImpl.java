package com.ruyicai.wap.dao.Impl;

import java.sql.Types;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ruyicai.wap.dao.UserInfoDao;
@Repository
public class UserInfoDaoImpl implements UserInfoDao {
	@Resource
	JdbcTemplate nonJdbcTemplate;
	@Override
	public int selectMsgCount(String userNo) {
		String sql = "SELECT COUNT(id) FROM news.Phoneinfo WHERE userno=? AND TO_DAYS(creattime) = TO_DAYS(NOW())";
		int count = nonJdbcTemplate.queryForInt(sql,new Object[]{userNo});
		return count;
	}

	@Override
	public int insertMsgInfo(String userNo, String userName, String mobileid,
			String bingCode) {
		String sql = "insert into news.Phoneinfo (userno,username,bindingmobile,messagecode) values (?,?,?,?)";
		int types[] = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}; 
		int count = nonJdbcTemplate.update(sql, new Object[]{userNo, userName, mobileid, bingCode},types);
		return count;
	}

}
