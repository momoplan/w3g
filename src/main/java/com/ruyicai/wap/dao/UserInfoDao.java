package com.ruyicai.wap.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {
	public int selectMsgCount(String userNo);

	public int insertMsgInfo(String userNo, String userName, String mobileid,
			String bingCode);
}
